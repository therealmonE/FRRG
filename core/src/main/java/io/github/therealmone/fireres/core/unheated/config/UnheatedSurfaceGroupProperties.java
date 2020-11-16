package io.github.therealmone.fireres.core.unheated.config;

import io.github.therealmone.fireres.core.common.config.Interpolated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UnheatedSurfaceGroupProperties extends Interpolated {

    private Integer thermocoupleCount;

}