package project.tastyfood.service.impl;

import org.springframework.stereotype.Service;
import project.tastyfood.service.StatsService;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StatsServiceImpl implements StatsService {
    private final AtomicInteger requestCount=new AtomicInteger(0);
    private final Instant startedOn=Instant.now();
    @Override
    public void incRequestCount() {
        requestCount.incrementAndGet();
    }

    @Override
    public int getRequestCount() {
        return requestCount.get();
    }

    @Override
    public Instant getStartedOn() {
        return startedOn;
    }
}