package io.github.therealmone.fireres.core.pressure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExcessPressureSample {

    private SamplePressure pressure;

}
