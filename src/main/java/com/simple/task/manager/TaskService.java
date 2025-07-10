package com.simple.task.manager;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final InMemoryTaskRepository inMemoryTaskRepository;

    public TaskService(InMemoryTaskRepository inMemoryTaskRepository) {
        this.inMemoryTaskRepository = inMemoryTaskRepository;
    }

    public Task createTask(Task task) {
        return inMemoryTaskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return inMemoryTaskRepository.findAll();
    }

    public void deleteTask(Long id) {
        inMemoryTaskRepository.deleteById(id);
    }
}
