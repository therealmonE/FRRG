package io.github.fireres.heatflow.report;

import io.github.fireres.core.model.Sample;
import io.github.fireres.core.test.AbstractTest;
import io.github.fireres.heatflow.properties.HeatFlowProperties;
import io.github.fireres.heatflow.service.HeatFlowService;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;

import static io.github.fireres.core.test.TestUtils.TEST_ATTEMPTS;
import static io.github.fireres.core.test.TestUtils.assertFunctionConstantlyGrowing;
import static io.github.fireres.core.test.TestUtils.assertFunctionIsConstant;
import static io.github.fireres.core.test.TestUtils.assertFunctionNotHigher;
import static io.github.fireres.core.test.TestUtils.assertFunctionNotLower;
import static io.github.fireres.core.test.TestUtils.assertChildTemperaturesEqualsMean;
import static io.github.fireres.core.utils.FunctionUtils.constantFunction;
import static io.github.fireres.heatflow.config.TestConfig.BOUND;
import static io.github.fireres.heatflow.config.TestConfig.TIME;

public class HeatFlowReportRepeatingTest extends AbstractTest {

    @Autowired
    private HeatFlowService heatFlowService;

    @Autowired
    private HeatFlowProperties reportProperties;

    @Autowired
    private Sample sample;

    @BeforeEach
    @Before
    public void setup() {
        sample.removeAllReports();
    }

    @RepeatedTest(TEST_ATTEMPTS)
    @Test
    public void provideReportTest() {
        val report = heatFlowService.createReport(sample, reportProperties);

        val bound = report.getBound();

        assertFunctionIsConstant(BOUND, bound.getValue());

        val mean = report.getMeanTemperature();

        assertFunctionConstantlyGrowing(mean.getValue());
        assertFunctionNotLower(mean.getValue(), constantFunction(TIME, 0).getValue());
        assertFunctionNotHigher(mean.getValue(), bound.getValue());

        val sensors = report.getSensorTemperatures();

        assertChildTemperaturesEqualsMean(sensors, mean);

        sensors.forEach(sensor -> {
            assertFunctionConstantlyGrowing(sensor.getValue());
            assertFunctionNotHigher(sensor.getValue(), bound.getValue());
            assertFunctionNotLower(sensor.getValue(), constantFunction(TIME, 0).getValue());
        });
    }

}
