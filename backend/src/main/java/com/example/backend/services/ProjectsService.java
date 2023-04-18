package com.example.backend.services;

import com.example.backend.models.Category;
import com.example.backend.models.Projects;
import com.example.backend.repositories.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {
    @Autowired
    private ProjectsRepository projectsRepository;

    public List<Projects> get_projects_list() {
        return Streamable.of(this.projectsRepository.findAll()).toList();
    }

    public Projects get_by_id(Long id) {
        return this.projectsRepository.findById(id).get();
    }

    public Projects save(Projects project) {
        projectsRepository.save(project);
        return project;
    }

    public void delete(Long id) {
        this.projectsRepository.deleteById(id);
    }

    public Projects update(Projects project) {
        return this.projectsRepository.saveAndFlush(project);
    }
}
