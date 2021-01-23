package io.github.therealmone.fireres.excess.pressure.pipeline.report;

import com.google.inject.Inject;
import io.github.therealmone.fireres.core.config.GenerationProperties;
import io.github.therealmone.fireres.core.pipeline.EnrichType;
import io.github.therealmone.fireres.core.pipeline.report.ReportEnricher;
import io.github.therealmone.fireres.excess.pressure.generator.MaxAllowedPressureGenerator;
import io.github.therealmone.fireres.excess.pressure.report.ExcessPressureReport;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;

import static io.github.therealmone.fireres.excess.pressure.pipeline.report.ExcessPressureReportEnrichType.MAX_ALLOWED_PRESSURE;
import static io.github.therealmone.fireres.excess.pressure.pipeline.report.ExcessPressureReportEnrichType.SAMPLES;

@Slf4j
public class MaxAllowedPressureEnricher implements ReportEnricher<ExcessPressureReport> {

    @Inject
    private GenerationProperties generationProperties;

    @Override
    public void enrich(ExcessPressureReport report) {
        log.info("Excess pressure: enriching report with max allowed pressure");
        val time = generationProperties.getGeneral().getTime();
        val delta = generationProperties.getGeneral().getExcessPressure().getDelta();

        val maxAllowedPressure = new MaxAllowedPressureGenerator(time, delta)
                .generate();

        report.setMaxAllowedPressure(maxAllowedPressure);
    }

    @Override
    public boolean supports(EnrichType enrichType) {
        return MAX_ALLOWED_PRESSURE.equals(enrichType);
    }

    @Override
    public List<EnrichType> getAffectedTypes() {
        return List.of(SAMPLES);
    }
}