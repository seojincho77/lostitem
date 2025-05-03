package com.example.lostitem.services;

import com.example.lostitem.models.Request;
import com.example.lostitem.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Optional<Request> getRequestById(Integer id) {
        return requestRepository.findById(id);
    }

    public Request saveRequest(Request request) {
        return requestRepository.save(request);
    }

    public void deleteRequest(Integer id) {
        requestRepository.deleteById(id);
    }
}