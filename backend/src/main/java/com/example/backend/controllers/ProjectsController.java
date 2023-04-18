package com.example.backend.controllers;

import com.example.backend.models.Category;
import com.example.backend.models.Projects;
import com.example.backend.services.CategoryService;
import com.example.backend.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProjectsController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProjectsService projectsService;

    @GetMapping("category/all")
    public ResponseEntity<List<Category>> get_category() {
        List<Category> category_list = this.categoryService.get_category_list();
        return new ResponseEntity<List<Category>>(category_list, HttpStatus.OK);
    }

    @GetMapping("projects/all")
    public ResponseEntity<List<Projects>> get_projects_list() {
        List<Projects> projects_list = this.projectsService.get_projects_list();
        return new ResponseEntity<List<Projects>>(projects_list, HttpStatus.OK);
    }

    @GetMapping("projects/search")
    public ResponseEntity<Projects> get_project(@RequestParam(name = "projectID") Long id) {
        Projects project = this.projectsService.get_by_id(id);
        return new ResponseEntity<Projects>(project, HttpStatus.OK);
    }

    /*@PostMapping("projects/add")
    @ResponseBody
    public ResponseEntity<Projects> adicionar_project(@RequestBody Projects project) {
        Category categoria = null;
        if (project.getCategoria() != null && project.getCategoria().getId() != null) {
            categoria = categoryService.get_by_id(project.getCategoria().getId());
        }
        project.setCategoria(categoria);
        Projects created_project = this.projectsService.save(project);
        return new ResponseEntity<Projects>(created_project, HttpStatus.CREATED);
    }*/

    @PostMapping("projects/add")
    @ResponseBody
    public ResponseEntity<?> adicionar_project(@RequestBody Projects project) {
        try {
            // Verificar se o campo de categoria está preenchido
            if (project.getCategoria() == null || project.getCategoria().getId() == null) {
                return new ResponseEntity<>("A categoria do projeto é obrigatória", HttpStatus.BAD_REQUEST);
            }

            // Verificar se o campo de nome está preenchido
            if (project.getName() == null || project.getName().isEmpty()) {
                return new ResponseEntity<>("O nome do projeto é obrigatório", HttpStatus.BAD_REQUEST);
            }

            // Verificar se o campo de orçamento é negativo
            if (project.getOrcamento() < 0) {
                return new ResponseEntity<>("O orçamento do projeto não pode ser negativo", HttpStatus.BAD_REQUEST);
            }

            // Buscar a categoria do projeto pelo ID
            Category categoria = categoryService.get_by_id(project.getCategoria().getId());
            if (categoria == null) {
                return new ResponseEntity<>("A categoria do projeto não foi encontrada", HttpStatus.BAD_REQUEST);
            }

            project.setCategoria(categoria);
            Projects created_project = this.projectsService.save(project);
            return new ResponseEntity<>(created_project, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocorreu um erro ao adicionar o projeto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("projects/delete")
    public ResponseEntity<String> delete_project(@RequestParam Long id) {
        this.projectsService.delete(id);
        return new ResponseEntity<String>("Project deleted sucessfully", HttpStatus.OK);
    }

    @PutMapping("projects/update")
    public ResponseEntity<?> update(@RequestBody Projects project) {
        if (project.getId() == null) new ResponseEntity<String>("ID não foi informado", HttpStatus.OK);
        Projects updated_project = this.projectsService.update(project);
        return new ResponseEntity<Projects>(project, HttpStatus.OK);
    }
}
