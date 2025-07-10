package com.simple.task.manager;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryTaskRepository {
    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public Task save(Task task) {
        // ensure they gave you a status
        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Status must be PENDING or COMPLETED");
        }

        // always generate a fresh id server-side
        long newId = counter.incrementAndGet();
        task.setId(newId);

        tasks.put(newId, task);
        return task;
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public void deleteById(Long id) {
        tasks.remove(id);
    }
}
