package io.github.therealmone.fireres.excel.model;

import io.github.therealmone.fireres.excel.chart.ExcelChart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExcelReport {

    private List<Column> data;

    private ExcelChart chart;

}
