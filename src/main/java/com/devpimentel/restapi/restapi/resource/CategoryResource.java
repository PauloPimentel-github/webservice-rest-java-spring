package com.devpimentel.restapi.restapi.resource;

import com.devpimentel.restapi.restapi.event.CreateResourceEvent;
import com.devpimentel.restapi.restapi.model.Category;
import com.devpimentel.restapi.restapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category, HttpServletResponse response) {
        Category createCategory = categoryRepository.save(category);

        applicationEventPublisher.publishEvent(new CreateResourceEvent(this, response, createCategory.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(createCategory);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        Category category = categoryRepository.findOne(id);
        return (category == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(category));
    }
}
