package ru.akhitev.rp.map.hyperspace;

import org.springframework.stereotype.Service;

@Service
public class VortexSpeedCalculator {
    private static final Double LIGHT_YEARS_IN_HOUR_SPEED = 0.78d;
    public Double calculate(Double distance) {
        return distance / LIGHT_YEARS_IN_HOUR_SPEED;
    }
}
