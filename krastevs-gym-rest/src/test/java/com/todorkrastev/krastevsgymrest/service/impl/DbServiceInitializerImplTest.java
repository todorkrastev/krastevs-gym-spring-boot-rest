package com.todorkrastev.krastevsgymrest.service.impl;

import com.todorkrastev.krastevsgymrest.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceInitializerImplTest {

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private DbServiceInitializerImpl dbServiceInitializer;

    @BeforeEach
    void setUp() {
        dbServiceInitializer = new DbServiceInitializerImpl(activityRepository);
    }

    @Test
    void testInitWhenRepositoryIsEmpty() {
        when(activityRepository.count()).thenReturn(0L);

        dbServiceInitializer.init();

        verify(activityRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testInitWhenRepositoryIsNotEmpty() {
        when(activityRepository.count()).thenReturn(10L);

        dbServiceInitializer.init();

        verify(activityRepository, never()).saveAll(anyList());
    }
}