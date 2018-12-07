package ru.akhitev.rp.map.hyperspace;

import org.springframework.stereotype.Service;

@Service
public class EmpireLightSpeedCalculator {
    private static final Double LIGHT_YEARS_IN_HOUR_SPEED = 12.0d;
    public Double calculate(Double distance) {
        return distance / LIGHT_YEARS_IN_HOUR_SPEED;
    }
}
