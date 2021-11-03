package io.github.fireres.unheated.surface.pipeline;

import io.github.fireres.core.model.Sample;
import io.github.fireres.core.pipeline.ReportEnrichPipeline;
import io.github.fireres.core.properties.GeneralProperties;
import io.github.fireres.core.test.AbstractTest;
import io.github.fireres.unheated.surface.properties.UnheatedSurfaceProperties;
import io.github.fireres.unheated.surface.service.UnheatedSurfaceService;
import io.github.fireres.unheated.surface.report.UnheatedSurfaceReport;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.github.fireres.unheated.surface.pipeline.UnheatedSurfaceReportEnrichType.MAX_ALLOWED_THERMOCOUPLE_TEMPERATURE;
import static org.junit.Assert.assertNotEquals;

public class PrimaryGroupThermocoupleBoundEnrichTest extends AbstractTest {

    @Autowired
    private GeneralProperties generalProperties;

    @Autowired
    private UnheatedSurfaceService unheatedSurfaceService;

    @Autowired
    private ReportEnrichPipeline<UnheatedSurfaceReport> reportEnrichPipeline;

    @Autowired
    private UnheatedSurfaceProperties reportProperties;

    @Autowired
    private Sample sample;

    @Test
    public void enrichThermocoupleBound() {

        val report = unheatedSurfaceService.createReport(sample, reportProperties);

        val oldThermocoupleBound = report.getMaxAllowedThermocoupleTemperature();
        val oldMeanTemperature = report.getMeanTemperature();
        val oldThermocoupleTemperatures = report.getThermocoupleTemperatures();

        generalProperties.setEnvironmentTemperature(24);
        reportEnrichPipeline.accept(report, MAX_ALLOWED_THERMOCOUPLE_TEMPERATURE);

        val newThermocoupleBound = report.getMaxAllowedThermocoupleTemperature();
        val newMeanTemperature = report.getMeanTemperature();
        val newThermocoupleTemperatures = report.getThermocoupleTemperatures();

        assertNotEquals(oldThermocoupleBound, newThermocoupleBound);
        assertNotEquals(oldMeanTemperature, newMeanTemperature);
        assertNotEquals(oldThermocoupleTemperatures, newThermocoupleTemperatures);
    }

}
