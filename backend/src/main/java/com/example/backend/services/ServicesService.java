package com.example.backend.services;

import com.example.backend.models.Services;
import com.example.backend.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ServicesService {
    @Autowired
    private ServicesRepository servicesRepository;

    public List<Services> get_services_list() {
        return Streamable.of(this.servicesRepository.findAll()).toList();
    }

    public Services get_by_id(Long id) {
        return this.servicesRepository.findById(id).get();
    }

    public List<Services> get_services_list_by_project_id(Long id) {
        return servicesRepository.findAllByProjectId(id);
    }

    public Services save(Services service) {
        servicesRepository.save(service);
        return service;
    }

    public void delete(Long id) {
        this.servicesRepository.deleteById(id);
    }

    public Services update(Services service) {
        return this.servicesRepository.saveAndFlush(service);
    }
}
