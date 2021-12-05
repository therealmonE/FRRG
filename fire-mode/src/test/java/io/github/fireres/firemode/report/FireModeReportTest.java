package io.github.fireres.firemode.report;

import io.github.fireres.core.model.Sample;
import io.github.fireres.core.test.AbstractTest;
import io.github.fireres.firemode.properties.FireModeProperties;
import io.github.fireres.firemode.service.FireModeService;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static io.github.fireres.core.test.TestUtils.toPointList;

public class FireModeReportTest extends AbstractTest {

    @Autowired
    private FireModeService fireModeService;

    @Autowired
    private FireModeProperties reportProperties;

    @Autowired
    private Sample sample;

    @Before
    public void setup() {
        sample.removeAllReports();
    }

    @Test
    public void generateFurnaceTemperature() {
        val report = fireModeService.createReport(sample, reportProperties);

        val expectedValues = toPointList(List.of(
                42, 350, 446, 503, 545, 577, 604,
                627, 646, 664, 679, 694, 706, 718,
                729, 740, 749, 758, 767, 775, 782,
                790, 797, 803, 810, 816, 821, 827,
                833, 838, 843, 848, 852, 857, 861,
                866, 870, 874, 878, 882, 886, 889,
                893, 897, 900, 903, 907, 910, 913,
                916, 919, 922, 925, 928, 931, 933,
                936, 939, 941, 944, 946, 949, 951,
                954, 956, 958, 961, 963, 965, 967,
                969
        ));

        val furnaceTemp = report.getFurnaceTemperature();

        Assert.assertEquals(expectedValues, furnaceTemp.getValue());
    }

    @Test
    public void generateMaxAllowedTemperature() {
        val report = fireModeService.createReport(sample, reportProperties);

        val expectedFunction = toPointList(List.of(
                48, 402, 513, 578, 627,
                664, 695, 721, 743, 764,
                781, 763, 777, 790, 802,
                814, 824, 834, 844, 853,
                860, 869, 877, 883, 891,
                898, 903, 910, 916, 922,
                927, 890, 895, 900, 904,
                909, 914, 918, 922, 926,
                930, 933, 938, 942, 945,
                948, 952, 956, 959, 962,
                965, 968, 971, 974, 978,
                980, 983, 986, 988, 991,
                993, 996, 999, 1002, 1004,
                1006, 1009, 1011, 1013, 1015,
                1017
        ));

        val maxAllowedTemp = report.getMaxAllowedTemperature();

        Assert.assertEquals(expectedFunction, maxAllowedTemp.getValue());
    }

    @Test
    public void generateMinAllowedTemperature() {
        val report = fireModeService.createReport(sample, reportProperties);

        val expectedFunction = toPointList(List.of(
                36, 298, 379, 428, 463,
                490, 513, 533, 549, 564,
                577, 625, 635, 646, 656,
                666, 674, 682, 690, 698,
                704, 711, 717, 723, 729,
                734, 739, 744, 750, 754,
                759, 806, 809, 814, 818,
                823, 827, 830, 834, 838,
                842, 845, 848, 852, 855,
                858, 862, 865, 867, 870,
                873, 876, 879, 882, 884,
                886, 889, 892, 894, 897,
                899, 902, 903, 906, 908,
                910, 913, 915, 917, 919,
                921
        ));

        val minAllowedTemp = report.getMinAllowedTemperature();

        Assert.assertEquals(expectedFunction, minAllowedTemp.getValue());
    }

    @Test
    public void generateStandardTemperature() {
        val report = fireModeService.createReport(sample, reportProperties);

        val expectedNumbers = toPointList(List.of(
                21, 329, 425, 482, 524, 556, 583, 606,
                625, 643, 658, 673, 685, 697, 708, 719,
                728, 737, 746, 754, 761, 769, 776, 782,
                789, 795, 800, 806, 812, 817, 822, 827,
                831, 836, 840, 845, 849, 853, 857, 861,
                865, 868, 872, 876, 879, 882, 886, 889,
                892, 895, 898, 901, 904, 907, 910, 912,
                915, 918, 920, 923, 925, 928, 930, 933,
                935, 937, 940, 942, 944, 946, 948
        ));

        val standardTemperature = report.getStandardTemperature();

        Assert.assertEquals(expectedNumbers, standardTemperature.getValue());
    }

}