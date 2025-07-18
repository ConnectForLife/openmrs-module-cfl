package org.openmrs.module.cfl.advice;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.lang.reflect.Method;
import java.util.List;
import org.openmrs.Location;
import org.openmrs.annotation.OpenmrsProfile;
import org.openmrs.api.context.Context;
import org.openmrs.api.context.Daemon;
import org.openmrs.module.locationbasedaccess.LocationBasedAccessConstants;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.ReportRequest;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.service.ReportService;
import org.springframework.aop.AfterReturningAdvice;

/**
 * This is a workaround for Location based access interaction with Reporting.
 *
 * <p>When location restriction is enabled, user can only see Locations they have access to
 * (Location Service level advice will stop the service from returning objects that user has no
 * access to).
 *
 * <p>In Reporting, it's possible to add Report parameter - Location - the options for that
 * parameter will be limited by location based access module. The user with access to a report,
 * will have access to complete history of the report executions - including executions made for
 * locations they have no access. In that case, they will see the report, but they will not see
 * location is was made for.
 *
 * <p>This workaround relays on the missing location parameter value and removes such executions
 * from user's view.
 */
@OpenmrsProfile(modules = "locationbasedaccess:0.2.* - 0.3.*")
public class LocationBasedReportRequests implements AfterReturningAdvice {

  @Override
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
      throws Throwable {
    if (isNotSupportedMethod(target, method) || isLocationBasedAccessDisabled()) {
      return;
    }

    final List<ReportRequest> typedReturnValue = (List<ReportRequest>) returnValue;

    typedReturnValue.removeIf(
        reportRequest -> {
          final ReportDefinition reportWithParameters =
              reportRequest.getReportDefinition().getParameterizable();

          for (Parameter reportParameter : reportWithParameters.getParameters()) {
            if (Location.class.isAssignableFrom(reportParameter.getType())
                && reportParameter.isRequired()) {
              final Object paramValueInRequest =
                  reportRequest
                      .getReportDefinition()
                      .getParameterMappings()
                      .get(reportParameter.getName());

              if (paramValueInRequest == null) {
                return true;
              }
            }
          }

          return false;
        });
  }

  private boolean isNotSupportedMethod(Object target, Method method) {
    return !(target instanceof ReportService) || !"getReportRequests".equals(method.getName());
  }

  private boolean isLocationBasedAccessDisabled() {
    if (Daemon.isDaemonThread()) {
      return true;
    }

    final String lbacRestriction =
        Context.getAdministrationService()
            .getGlobalProperty(
                LocationBasedAccessConstants.LOCATION_RESTRICTION_GLOBAL_PROPERTY_NAME);

    return isBlank(lbacRestriction) || !lbacRestriction.toLowerCase().equals("true");
  }
}
