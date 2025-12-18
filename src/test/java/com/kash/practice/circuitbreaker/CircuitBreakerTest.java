package com.kash.practice.circuitbreaker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class CircuitBreakerTest {
    private CircuitBreaker breaker;

    @BeforeEach
    void setUp() {
        breaker = new CircuitBreaker(3, Duration.ofMillis(100));
    }

    @Test
    void testSuccessKeepsCircuitClosed() {
        String result = breaker.execute(() -> "Success");
        assertEquals("Success", result);
        assertEquals("CLOSED", breaker.getState());
    }

    @Test
    void testFailuresOpenCircuit() {
        for (int i = 0; i < 3; i++) {
            try {
                breaker.execute(() -> { throw new RuntimeException("fail"); });
            } catch (RuntimeException ignored) {}
        }
        assertEquals("OPEN", breaker.getState());
    }

    @Test
    void testOpenCircuitBlocksCall() {
        for (int i = 0; i < 3; i++) {
            try {
                breaker.execute(() -> { throw new RuntimeException("fail"); });
            } catch (RuntimeException ignored) {}
        }

        assertThrows(RuntimeException.class, () ->
                breaker.execute(() -> "Blocked"));
    }

    @Test
    void testHalfOpenAllowsRetryAfterTimeout() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            try {
                breaker.execute(() -> { throw new RuntimeException("fail"); });
            } catch (RuntimeException ignored) {}
        }

        Thread.sleep(150); // wait for retry period to pass

        String result = breaker.execute(() -> "Recovered");
        assertEquals("Recovered", result);
        assertEquals("CLOSED", breaker.getState());
    }
}
