package io.github.therealmone.fireres.core.model.firemode;

import io.github.therealmone.fireres.core.model.point.IntegerPoint;
import io.github.therealmone.fireres.core.model.sequence.SmoothedIntegerPointSequence;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MaxAllowedTemperature extends SmoothedIntegerPointSequence {
    @Builder
    public MaxAllowedTemperature(List<IntegerPoint> value, List<IntegerPoint> smoothedValue) {
        super(value, smoothedValue);
    }
}
