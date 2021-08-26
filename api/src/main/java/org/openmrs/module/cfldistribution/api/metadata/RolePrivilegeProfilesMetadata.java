package org.openmrs.module.cfldistribution.api.metadata;

import org.openmrs.module.ModuleFactory;
import org.openmrs.module.cfldistribution.CfldistributionConstants;
import org.openmrs.module.metadatadeploy.bundle.VersionedMetadataBundle;

import java.util.HashSet;
import java.util.Set;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.idSet;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.role;

/**
 * Metadata bundle which is used to install initial CFL role profiles.
 * <p>
 * This bundle contains global and mandatory privilege configuration, and also any optional privileges which depend on
 * whether the optional modules are started.
 * </p>
 */
public class RolePrivilegeProfilesMetadata extends VersionedMetadataBundle {

    private static final String ANALYST_PRIVILEGE_LEVEL = "Privilege Level: Analyst";
    private static final String ANALYST_PRIVILEGE_LEVEL_DESCRIPTION = "Permissions related to manging report resources.";

    public static final String DOCTOR_PRIVILEGE_LEVEL = "Privilege Level: Doctor";
    private static final String DOCTOR_PRIVILEGE_LEVEL_DESCRIPTION = "Permissions related to manging medical resources.";

    private static final String GET_USERS = "Get Users";
    private static final String MANAGE_REPORT_DEFINITIONS = "Manage Report Definitions";
    private static final String MANAGE_REPORT_DESIGNS = "Manage Report Designs";
    private static final String MANAGE_REPORTS = "Manage Reports";
    private static final String MANAGE_SCHEDULED_REPORT_TASKS = "Manage Scheduled Report Tasks";
    private static final String RUN_REPORTS = "Run Reports";
    private static final String VIEW_REPORT_OBJECTS = "View Report Objects";
    private static final String VIEW_REPORTS = "View Reports";

    private static final Set<String> ANALYST_PRIVILEGES = idSet(
            GET_USERS,
            MANAGE_REPORT_DEFINITIONS,
            MANAGE_REPORT_DESIGNS,
            MANAGE_REPORTS,
            MANAGE_SCHEDULED_REPORT_TASKS,
            RUN_REPORTS,
            VIEW_REPORT_OBJECTS,
            VIEW_REPORTS);

    private static final Set<String> DOCTOR_PRIVILEGES = idSet(
            "Add Allergies",
            "Add Cohorts",
            "Add Concept Proposals",
            "Add Encounters",
            "Add HL7 Inbound Archive",
            "Add HL7 Inbound Exception",
            "Add HL7 Inbound Queue",
            "Add HL7 Source",
            "Add Observations",
            "Add Orders",
            "Add Patient Identifiers",
            "Add Patient Programs",
            "Add Patients",
            "Add People",
            "Add Problems",
            "Add Relationships",
            "Add Users",
            "Add Visits",
            "App: attachments.attachments.page",
            "App: coreapps.activeVisits",
            "App: coreapps.findPatient",
            "App: coreapps.mergePatient",
            "App: coreapps.patientDashboard",
            "App: coreapps.patientVisits",
            "App: coreapps.summaryDashboard",
            "App: formentryapp.forms",
            "App: referenceapplication.vitals",
            "App: registrationapp.registerPatient",
            "Configure Visits",
            "Delete Cohorts",
            "Delete Concept Proposals",
            "Delete Encounters",
            "Delete HL7 Inbound Archive",
            "Delete HL7 Inbound Exception",
            "Delete HL7 Inbound Queue",
            "Delete Notes",
            "Delete Observations",
            "Delete Orders",
            "Delete Patient Identifiers",
            "Delete Patient Programs",
            "Delete Patients",
            "Delete People",
            "Delete Relationships",
            "Delete Report Objects",
            "Delete Users",
            "Delete Visits",
            "Edit Allergies",
            "Edit Cohorts",
            "Edit Concept Proposals",
            "Edit conditions",
            "Edit Encounters",
            "Edit Notes",
            "Edit Observations",
            "Edit Orders",
            "Edit Patient Identifiers",
            "Edit Patient Programs",
            "Edit Patients",
            "Edit People",
            "Edit Problems",
            "Edit Relationships",
            "Edit User Passwords",
            "Edit Users",
            "Edit Visits",
            "Form Entry",
            "Generate Batch of Identifiers",
            "Get Allergies",
            "Get Care Settings",
            "Get Concept Attribute Types",
            "Get Concept Classes",
            "Get Concept Datatypes",
            "Get Concept Map Types",
            "Get Concept Proposals",
            "Get Concept Reference Terms",
            "Get Concept Sources",
            "Get Concepts",
            "Get conditions",
            "Get Encounter Roles",
            "Get Encounter Types",
            "Get Encounters",
            "Get Field Types",
            "Get Forms",
            "Get Global Properties",
            "Get HL7 Inbound Archive",
            "Get HL7 Inbound Exception",
            "Get HL7 Inbound Queue",
            "Get HL7 Source",
            "Get Identifier Types",
            "Get Location Attribute Types",
            "Get Locations",
            "Get Notes",
            "Get Observations",
            "Get Order Frequencies",
            "Get Order Sets",
            "Get Order Types",
            "Get Orders",
            "Get Patient Cohorts",
            "Get Patient Identifiers",
            "Get Patient Programs",
            "Get Patients",
            "Get People",
            "Get Person Attribute Types",
            "Get Privileges",
            "Get Problems",
            "Get Programs",
            "Get Providers",
            "Get Relationship Types",
            "Get Relationships",
            "Get Roles",
            GET_USERS,
            "Get Visit Attribute Types",
            "Get Visit Types",
            "Get Visits",
            "Manage Address Hierarchy",
            "Manage Address Templates",
            "Manage Alerts",
            "Manage Appointment Types",
            "Manage Appointments Settings",
            "Manage Auto Generation Options",
            "Manage Cohort Definitions",
            "Manage Concept Attribute Types",
            "Manage Concept Classes",
            "Manage Concept Datatypes",
            "Manage Concept Map Types",
            "Manage Concept Name tags",
            "Manage Concept Reference Terms",
            "Manage Concept Sources",
            "Manage Concept Stop Words",
            "Manage Concepts",
            "Manage Data Set Definitions",
            "Manage Dimension Definitions",
            "Manage Encounter Roles",
            "Manage Encounter Types",
            "Manage Field Types",
            "Manage Flags",
            "Manage FormEntry XSN",
            "Manage Forms",
            "Manage Global Properties",
            "Manage HL7 Messages",
            "Manage Identifier Sources",
            "Manage Identifier Types",
            "Manage Implementation Id",
            "Manage Indicator Definitions",
            "Manage Location Attribute Types",
            "Manage Location Tags",
            "Manage Locations",
            "Manage Metadata Mapping",
            "Manage Modules",
            "Manage Order Frequencies",
            "Manage Order Sets",
            "Manage Order Types",
            "Manage OWA",
            "Manage Person Attribute Types",
            "Manage Programs",
            "Manage Providers",
            "Manage Relationship Types",
            "Manage Relationships",
            MANAGE_REPORTS,
            "Manage RESTWS",
            MANAGE_SCHEDULED_REPORT_TASKS,
            "Manage Search Index",
            "Manage Token Registrations",
            "Manage Visit Attribute Types",
            "Manage Visit Types",
            "Patient Dashboard - View Demographics Section",
            "Patient Dashboard - View Encounters Section",
            "Patient Dashboard - View Forms Section",
            "Patient Dashboard - View Graphs Section",
            "Patient Dashboard - View Overview Section",
            "Patient Dashboard - View Patient Summary",
            "Patient Dashboard - View Regimen Section",
            "Patient Overview - View Allergies",
            "Patient Overview - View Patient Actions",
            "Patient Overview - View Problem List",
            "Patient Overview - View Programs",
            "Patient Overview - View Relationships",
            "Provider Management API - Read-only",
            "Provider Management Dashboard - Edit Patients",
            "Provider Management Dashboard - Edit Providers",
            "Provider Management Dashboard - View Historical",
            "Provider Management Dashboard - View Patients",
            "Provider Management Dashboard - View Providers",
            "Purge Field Types",
            "Remove Allergies",
            "Remove Problems",
            "Request Appointments",
            RUN_REPORTS,
            "SMS module Privilege",
            "Task: coreapps.createRetrospectiveVisit",
            "Task: coreapps.createVisit",
            "Task: coreapps.deletePatient",
            "Task: coreapps.deletePatientProgram",
            "Task: coreapps.editPatientProgram",
            "Task: coreapps.endVisit",
            "Task: coreapps.enrollInProgram",
            "Task: coreapps.markPatientDead",
            "Task: coreapps.mergeVisits",
            "Task: emr.patient.encounter.delete",
            "Task: emr.patient.encounter.edit",
            "Task: Manage Condition Lists",
            "Task: Modify Allergies",
            "Task: referenceapplication.simpleAdmission",
            "Task: referenceapplication.simpleDischarge",
            "Task: referenceapplication.simpleTransfer",
            "Task: referenceapplication.simpleVisitNote",
            "Task: referenceapplication.vitals",
            "Test Flags",
            "Update Appointment Status",
            "Update HL7 Inbound Archive",
            "Update HL7 Inbound Exception",
            "Update HL7 Inbound Queue",
            "Update HL7 Source",
            "Upload Batch of Identifiers",
            "Upload XSN",
            "View Administration Functions",
            "View Appointments",
            "View Calculations",
            "View Data Entry Statistics",
            "Get Database Changes",
            "View FHIR Client",
            "View Metadata Via Mapping",
            "View Navigation Menu",
            "View Patient Appointment History",
            VIEW_REPORT_OBJECTS,
            VIEW_REPORTS,
            "View RESTWS",
            "View Token Registrations",
            "View Unpublished Forms",
            "Messages schedule privilege",
            "App: reportingui.reports"
    );

    /**
     * Additional Doctor privileges when ETL Lite is stared.
     */
    private static final Set<String> DOCTOR_PRIVILEGES_ETL_OPTIONAL = idSet("ETL Mappings Privilege");

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    protected void installEveryTime() {
        // do nothing
    }

    @Override
    protected void installNewVersion() {
        installAnalystRole();
        installDoctorRole();
    }

    private void installAnalystRole() {
        install(role(ANALYST_PRIVILEGE_LEVEL, ANALYST_PRIVILEGE_LEVEL_DESCRIPTION, idSet(), ANALYST_PRIVILEGES));
    }

    private void installDoctorRole() {
        install(role(DOCTOR_PRIVILEGE_LEVEL, DOCTOR_PRIVILEGE_LEVEL_DESCRIPTION, idSet(),
                appendOptionalPrivilegeIds(DOCTOR_PRIVILEGES)));
    }

    private Set<String> appendOptionalPrivilegeIds(final Set<String> basePrivileges) {
        final Set<String> allPrivileges = new HashSet<>(basePrivileges);

        if (moduleStarted(CfldistributionConstants.ETL_MODULE_ID)) {
            allPrivileges.addAll(DOCTOR_PRIVILEGES_ETL_OPTIONAL);
        }

        return allPrivileges;
    }

    private boolean moduleStarted(final String moduleId) {
        return ModuleFactory.getStartedModuleById(moduleId) != null;
    }

}
