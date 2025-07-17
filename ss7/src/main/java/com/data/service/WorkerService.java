package com.data.service;

import com.data.entity.Worker;
import com.data.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Long id) {
        return workerRepository.findById(id).orElseThrow(() -> new RuntimeException("Worker not found"));
    }

    public Worker addWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker updateWorker(Long id, Worker worker) {
        Worker existingWorker = workerRepository.findById(id).orElseThrow(() -> new RuntimeException("Worker not found"));
        existingWorker.setFullname(worker.getFullname());
        existingWorker.setPhone(worker.getPhone());
        existingWorker.setAddress(worker.getAddress());
        existingWorker.setSalary(worker.getSalary());
        return workerRepository.save(existingWorker);
    }

    public void deleteWorker(Long id) {
        workerRepository.deleteById(id);
    }
}