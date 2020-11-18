package io.github.therealmone.fireres.excel.report;

import com.google.inject.Inject;
import io.github.therealmone.fireres.core.annotation.EnvironmentTemperature;
import io.github.therealmone.fireres.core.annotation.Time;
import io.github.therealmone.fireres.excel.chart.FireModeChart;
import io.github.therealmone.fireres.excel.column.Column;
import io.github.therealmone.fireres.excel.column.firemode.EightTimeColumn;
import io.github.therealmone.fireres.excel.column.firemode.EnvTempColumn;
import io.github.therealmone.fireres.excel.column.firemode.FurnaceTemperatureColumn;
import io.github.therealmone.fireres.excel.column.firemode.MaxAllowedTemperatureColumn;
import io.github.therealmone.fireres.excel.column.firemode.MinAllowedTemperatureColumn;
import io.github.therealmone.fireres.excel.column.firemode.StandardTemperatureColumn;
import io.github.therealmone.fireres.excel.column.firemode.ThermocoupleTemperatureColumn;
import io.github.therealmone.fireres.excel.column.firemode.ThermocouplesMeanTemperatureColumn;
import io.github.therealmone.fireres.excel.column.TimeColumn;
import io.github.therealmone.fireres.firemode.report.FireModeReport;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

public class FireModeExcelReportsProvider implements ExcelReportsProvider {

    @Inject
    private FireModeReport fireModeReport;

    @Inject
    @Time
    private Integer time;

    @Inject
    @EnvironmentTemperature
    private Integer environmentTemperature;

    @Override
    public List<ExcelReport> get() {
        val data = createData();

        return List.of(ExcelReport.builder()
                .data(data)
                .chart(new FireModeChart(time, data))
                .build());
    }

    protected List<Column> createData() {
        val columns = new ArrayList<Column>();

        columns.add(new TimeColumn(time));
        columns.add(new EnvTempColumn(time, environmentTemperature));
        columns.add(new FurnaceTemperatureColumn(fireModeReport.getFurnaceTemperature()));
        columns.add(new MinAllowedTemperatureColumn(fireModeReport.getMinAllowedTemperature()));
        columns.add(new MaxAllowedTemperatureColumn(fireModeReport.getMaxAllowedTemperature()));
        columns.add(new EightTimeColumn(time));
        columns.add(new StandardTemperatureColumn(fireModeReport.getStandardTemperature()));

        val samples = fireModeReport.getSamples();

        for (int s = 0; s < samples.size(); s++) {
            val sample = samples.get(s);
            val thermocoupleTemperatures = sample.getThermocoupleTemperatures();

            for (int t = 0; t < thermocoupleTemperatures.size(); t++) {
                val thermocoupleTemperature = thermocoupleTemperatures.get(t);
                columns.add(new ThermocoupleTemperatureColumn(t + 1, thermocoupleTemperature));
            }

            columns.add(new ThermocouplesMeanTemperatureColumn(s + 1, sample.getThermocoupleMeanTemperature()));
        }

        return columns;
    }

}
