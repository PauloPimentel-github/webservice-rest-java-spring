package com.devpimentel.restapi.restapi.resource;

import com.devpimentel.restapi.restapi.event.CreateResourceEvent;
import com.devpimentel.restapi.restapi.model.Person;
import com.devpimentel.restapi.restapi.repository.PersonRepository;
import com.devpimentel.restapi.restapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person createPerson = personRepository.save(person);

        applicationEventPublisher.publishEvent(new CreateResourceEvent(this, response, createPerson.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(createPerson);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Person person = personRepository.findOne(id);
        return (person == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(person));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
    public void deletePerson(@PathVariable Long id) {
        personRepository.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @Valid @RequestBody Person person) {
        Person updatePerson = personService.updatePerson(id, person);
        return ResponseEntity.ok(updatePerson);
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public void updatePropertyActive(@PathVariable Long id, @RequestBody Boolean active) {
        personService.updatePropertyActive(id, active);
    }
}
