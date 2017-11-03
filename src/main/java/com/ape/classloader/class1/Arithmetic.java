package com.ape.classloader.class1;

import java.util.List;

/**
 * AngryApe created at 2017-11-03
 */
public class Arithmetic {

    public static Double max(Double max, List<Double> numbers) {
        if (numbers == null)
            return null;
        for (Double number : numbers) {
            if (number == null)
                continue;
            if (max < number)
                max = number;
        }
        return max;
    }
}
