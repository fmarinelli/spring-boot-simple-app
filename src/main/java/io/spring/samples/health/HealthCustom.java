package io.spring.samples.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCustom implements HealthIndicator {

    @Override
    public Health health() {
        return Health.status("OK").withDetail("app", "prova").build();
    }
}
