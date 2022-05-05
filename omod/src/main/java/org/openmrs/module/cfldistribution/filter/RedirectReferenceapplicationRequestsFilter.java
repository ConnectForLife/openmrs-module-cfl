package org.openmrs.module.cfldistribution.filter;

import org.openmrs.ui.framework.WebConstants;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * The Request Filter which redirects requests for OpenMRS reference application module's resources,
 * endpoints and pages.
 *
 * <p>Some of the OpenMRS OWA modules require reference modules to be included, but these modules
 * add redundant data and the CfL doesn't use them, therefore we need to redirect some of the
 * requests to CfL resources.
 *
 * <p>Redirected paths:
 *
 * <ul>
 *   <li>resource/referenceapplication/styles/referenceapplication.css resource to
 *       referenceapplication.css located in this project. Added because of the Admin View OWA uses
 *       CSS from OpenMRS reference application.
 *   <li>referenceapplication/home.page to cfldistribution/home.page. Added because of the Admin
 *       View OWA uses hard-codes URL in the "go home" button.
 * </ul>
 */
public class RedirectReferenceapplicationRequestsFilter implements Filter {
  private static final String REFERENCEAPPLICATION_CSS_RESOURCE =
      "resource/referenceapplication/styles/referenceapplication.css";
  private static final String CFL_STYLE_RESOURCE_CONTEXT_RELATIVE =
      "/ms/uiframework/resource/cfldistribution/styles/referenceapplication.css";

  private static final String REFERENCEAPPLICATION_HOME_PAGE = "referenceapplication/home.page";
  private static final String CFL_HOME_PAGE_CONTEXT_RELATIVE = "/cfldistribution/home.page";

  private static final List<RedirectMapping> REDIRECTS =
      asList(
          new RedirectMapping(
              REFERENCEAPPLICATION_CSS_RESOURCE, CFL_STYLE_RESOURCE_CONTEXT_RELATIVE),
          new RedirectMapping(REFERENCEAPPLICATION_HOME_PAGE, CFL_HOME_PAGE_CONTEXT_RELATIVE));

  @Override
  public void init(FilterConfig filterConfig) {}

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    if (servletRequest instanceof HttpServletRequest) {
      final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
      final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

      for (RedirectMapping redirectMapping : REDIRECTS) {
        if (shouldRedirect(redirectMapping, httpServletRequest)) {
          redirect(redirectMapping, httpServletResponse);
          return;
        }
      }
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {}

  private boolean shouldRedirect(
      RedirectMapping redirectMapping, HttpServletRequest httpServletRequest) {
    return URI.create(httpServletRequest.getRequestURI())
        .getPath()
        .endsWith(redirectMapping.resourceToRedirectPath);
  }

  private void redirect(RedirectMapping redirectMapping, HttpServletResponse httpServletResponse)
      throws IOException {
    final String cflStyleLocation =
        "/" + WebConstants.CONTEXT_PATH + redirectMapping.contextRelativeRedirectPath;
    httpServletResponse.sendRedirect(cflStyleLocation);
  }

  private static class RedirectMapping {
    final String resourceToRedirectPath;
    final String contextRelativeRedirectPath;

    private RedirectMapping(String resourceToRedirectPath, String contextRelativeRedirectPath) {
      this.resourceToRedirectPath = resourceToRedirectPath;
      this.contextRelativeRedirectPath = contextRelativeRedirectPath;
    }
  }
}
