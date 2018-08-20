package com.devpimentel.restapi.restapi.resource;

import com.devpimentel.restapi.restapi.event.CreateResourceEvent;
import com.devpimentel.restapi.restapi.model.Launch;
import com.devpimentel.restapi.restapi.model.Person;
import com.devpimentel.restapi.restapi.repository.LaunchRepository;
import com.devpimentel.restapi.restapi.repository.filter.LaunchFilter;
import com.devpimentel.restapi.restapi.repository.projection.ReleaseSummary;
import com.devpimentel.restapi.restapi.service.LaunchService;
import com.devpimentel.restapi.restapi.service.exception.PersonNotExistOrInactiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LaunchResource {

    @Autowired
    private LaunchRepository launchRespository;

    @Autowired
    private LaunchService launchService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<Launch> search(LaunchFilter launchFilter, Pageable pageable) {
        return launchRespository.filter(launchFilter, pageable);
    }

    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<ReleaseSummary> sumUp(LaunchFilter launchFilter, Pageable pageable) {
        return launchRespository.sumUp(launchFilter, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Launch> getLaunch(@PathVariable Long id) {
        Launch launch = launchRespository.findOne(id);
        return (launch == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(launch));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Launch> createLaunch(@Valid @RequestBody Launch launch, HttpServletResponse response){
        Launch createLaunch = launchService.createLaunch(launch);

        applicationEventPublisher.publishEvent(new CreateResourceEvent(this, response, createLaunch.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(createLaunch);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
    public void deleteLaunch(@PathVariable Long id) {
    	launchRespository.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO')")
    public ResponseEntity<Launch> updateLaunch(@PathVariable Long id, @Valid @RequestBody Launch launch) {
        try {
            Launch launchSaved = launchService.updateLaunch(id, launch);
            return ResponseEntity.ok(launchSaved);
        } catch (IllegalArgumentException e) {
            return  ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler({ PersonNotExistOrInactiveException.class })
    public ResponseEntity<Object> handlePersonNotExistOrInactiveException(PersonNotExistOrInactiveException ex) {
        String messageUser = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String messageDev = ex.toString();
        List<com.devpimentel.restapi.restapi.exceptionhandler.ExceptionHandler.Error> errors = Arrays.asList(new com.devpimentel.restapi.restapi.exceptionhandler.ExceptionHandler.Error(messageUser, messageDev));
        return ResponseEntity.badRequest().body(errors);
    }

}
