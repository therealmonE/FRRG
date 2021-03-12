package io.github.therealmone.fireres.unheated.surface.generator;

import io.github.therealmone.fireres.core.generator.strategy.FunctionsGenerationStrategy;

public class UnheatedSurfaceGenerationStrategy implements FunctionsGenerationStrategy {

    @Override
    public Integer resolveDelta(Integer t0, Integer time, Double coefficient) {
        return (int) ((Math.log(time + 1) / Math.log(1.08)) * coefficient);
    }

}
