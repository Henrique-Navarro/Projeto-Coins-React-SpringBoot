package com.example.backend.controllers;

import com.example.backend.models.Category;
import com.example.backend.models.Projects;
import com.example.backend.services.CategoryService;
import com.example.backend.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/projects/{id}")
    public ResponseEntity<Projects> get_project(@PathVariable("id") Long id) {
        Projects project = this.projectsService.get_by_id(id);
        if (project != null) return new ResponseEntity<Projects>(project, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("projects/add")
    @ResponseBody
    public ResponseEntity<?> adicionar_project(@RequestBody Projects project) {
        try {
            // Verificar se o campo de categoria está preenchido
            if (project.getCategoria() == null || project.getCategoria().getId() == null)
                return new ResponseEntity<>("A categoria do projeto é obrigatória", HttpStatus.BAD_REQUEST);

            // Verificar se o campo de nome está preenchido
            if (project.getName() == null || project.getName().isEmpty())
                return new ResponseEntity<>("O nome do projeto é obrigatório", HttpStatus.BAD_REQUEST);

            // Verificar se o campo de orçamento é negativo
            if (project.getOrcamento() < 0)
                return new ResponseEntity<>("O orçamento do projeto não pode ser negativo", HttpStatus.BAD_REQUEST);

            // Buscar a categoria do projeto pelo ID
            Category categoria = categoryService.get_by_id(project.getCategoria().getId());
            if (categoria == null)
                return new ResponseEntity<>("A categoria do projeto não foi encontrada", HttpStatus.BAD_REQUEST);

            project.setCategoria(categoria);
            project.setCusto(0);
            Projects created_project = this.projectsService.save(project);
            return new ResponseEntity<>(created_project, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocorreu um erro ao adicionar o projeto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("projects/{id}")
    public ResponseEntity<String> delete_project(@PathVariable Long id) {
        try {
            this.projectsService.delete(id);
            return new ResponseEntity<String>("Project with ID " + id + " deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete project", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("projects/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Projects project) {
        if (project.getId() == null)
            return new ResponseEntity<String>("ID não foi informado", HttpStatus.BAD_REQUEST);

        // Verifica se o ID do projeto no corpo da requisição é igual ao ID do caminho da URL
        if (!project.getId().equals(id))
            return new ResponseEntity<String>("ID do projeto no corpo da requisição não corresponde ao ID na URL", HttpStatus.BAD_REQUEST);

        Projects updated_project = this.projectsService.update(project);
        if (updated_project != null)
            return new ResponseEntity<Projects>(updated_project, HttpStatus.OK);
        else
            return new ResponseEntity<>("Falha na atualização do projeto", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
