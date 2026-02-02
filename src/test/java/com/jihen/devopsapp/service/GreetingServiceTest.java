package com.jihen.devopsapp.service;

import com.jihen.devopsapp.util.Clock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GreetingServiceTest {

    @Test
    void greet_shouldReturnMorning_whenHourIs9() {
        Clock clock = mock(Clock.class);
        when(clock.hour()).thenReturn(9);

        GreetingService service = new GreetingService(clock);

        assertEquals("Good morning", service.greet());
        verify(clock).hour();
    }
}
