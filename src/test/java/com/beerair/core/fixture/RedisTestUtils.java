package com.beerair.core.fixture;

import lombok.experimental.UtilityClass;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UtilityClass
public class RedisTestUtils {
    public void setDoNothing(RedisTemplate<String, Object> redisTemplate) {
        ValueOperations<String, Object> operations = mock(ValueOperations.class);
        doNothing().when(operations)
                .set(any(), any());

        when(redisTemplate.opsForValue())
                .thenReturn(operations);
    }
}
