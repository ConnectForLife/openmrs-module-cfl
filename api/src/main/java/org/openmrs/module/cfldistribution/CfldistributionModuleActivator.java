/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 * <p>
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 * <p>
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.cfldistribution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.module.ModuleException;
import org.openmrs.module.cfldistribution.api.activator.ModuleActivatorStep;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class CfldistributionModuleActivator extends BaseModuleActivator {

    private Log log = LogFactory.getLog(this.getClass());

    @Override
    public void started() {
        log.info("Starting Cfl Distribution Module");
        try {
            final List<ModuleActivatorStep> sortedSteps = Context
                    .getRegisteredComponents(ModuleActivatorStep.class)
                    .stream()
                    .sorted(Comparator.comparing(ModuleActivatorStep::getOrder))
                    .collect(Collectors.toList());

            for (ModuleActivatorStep step : sortedSteps) {
                step.startup(log);
            }
        } catch (Exception e) {
            throw new ModuleException("Failed to start cfldistribution module.", e);
        }
    }

    @Override
    public void stopped() {
        log.info("Shutting down Cfl Distribution Module");
    }
}
