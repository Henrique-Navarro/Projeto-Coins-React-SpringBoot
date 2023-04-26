package com.example.backend.controllers;

import com.example.backend.models.Projects;
import com.example.backend.models.Services;
import com.example.backend.services.CategoryService;
import com.example.backend.services.ProjectsService;
import com.example.backend.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ServicesController {
    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private ServicesService servicesService;

    @PostMapping("projects/{id}/services/add")
    @ResponseBody
    public ResponseEntity<?> adicionar_servico(@PathVariable("id") Long id, @RequestBody Services service) {
        Projects project = projectsService.get_by_id(id);
        if (project == null)
            return new ResponseEntity<>("Projeto não encontrado", HttpStatus.NOT_FOUND);

        if (service == null)
            return new ResponseEntity<>("Serviço não encontrado", HttpStatus.NOT_FOUND);

        service.setId_projeto(id);
        Services created_service = servicesService.save(service);
        return new ResponseEntity<>(created_service, HttpStatus.CREATED);
    }

    @GetMapping("/projects/{id}/services/all")
    public ResponseEntity<List<Services>> get_services_list(@PathVariable("id") Long id) {
        Projects project = projectsService.get_by_id(id);
        if (project == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        // Obtém a lista de serviços do projeto
        List<Services> serviceList = servicesService.get_services_list_by_project_id(id);

        return new ResponseEntity<>(serviceList, HttpStatus.OK);
    }

}
