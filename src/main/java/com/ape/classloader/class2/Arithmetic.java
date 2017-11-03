package com.ape.classloader.class2;

import com.ape.utils.CommonUtils;

import java.util.List;

/**
 * AngryApe created at 2017-11-03
 */
public class Arithmetic {

    public static Double max(Double max, List<Double> numbers) {
        if (CommonUtils.isEmpty(numbers) || max == null)
            return null;
        for (Double number : numbers) {
            if (number == null)
                continue;
            if (max < number)
                max = number;
        }
        return max;
    }

    public static Double min(Double min, List<Double> numbers) {
        if (CommonUtils.isEmpty(numbers) || min == null)
            return null;
        for (Double number : numbers) {
            if (number == null)
                continue;
            if (min > number)
                min = number;
        }
        return min;
    }
}
