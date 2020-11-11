package io.github.therealmone.fireres.core.generator.firemode;

import io.github.therealmone.fireres.core.firemode.FireModeFactory;
import lombok.val;
import org.junit.Test;

import java.util.List;

import static io.github.therealmone.fireres.core.TestUtils.assertFunctionConstantlyGrowing;
import static io.github.therealmone.fireres.core.TestUtils.assertFunctionNotLower;
import static io.github.therealmone.fireres.core.TestUtils.defaultGenerationProperties;
import static io.github.therealmone.fireres.core.TestUtils.toPointList;
import static org.junit.Assert.assertEquals;

public class MinAllowedTempGeneratorTest {

    @Test
    public void generate() {
        val factory = new FireModeFactory(defaultGenerationProperties());

        val expectedFunction = toPointList(List.of(
                18, 280, 361, 410, 445,
                473, 496, 515, 531, 547,
                559, 606, 617, 627, 637,
                647, 655, 663, 671, 679,
                685, 692, 698, 704, 710,
                716, 720, 725, 731, 735,
                740, 786, 789, 794, 798,
                803, 807, 810, 814, 818,
                822, 825, 828, 832, 835,
                838, 842, 845, 847, 850,
                853, 856, 859, 862, 865,
                866, 869, 872, 874, 877,
                879, 882, 884, 886, 888,
                890, 893, 895, 897, 899,
                901
        ));

        val standardTemp = factory.standardTemperature();
        val minAllowedTemp = factory.minAllowedTemperature(standardTemp);

        assertEquals(expectedFunction, minAllowedTemp.getValue());

        val smoothedFunction = minAllowedTemp.getSmoothedValue();

        assertFunctionConstantlyGrowing(smoothedFunction);
        assertEquals(expectedFunction.size(), smoothedFunction.size());
        assertFunctionNotLower(smoothedFunction, expectedFunction);
    }

}