package com.devpimentel.restapi.restapi.event.listener;

import com.devpimentel.restapi.restapi.event.CreateResourceEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

public class CreateResourceListener implements ApplicationListener<CreateResourceEvent> {

    @Override
    public void onApplicationEvent(CreateResourceEvent createResourceEvent) {
        HttpServletResponse response = createResourceEvent.getResponse();
        Long codigo = createResourceEvent.getCodigo();

        addHeaderLocation(response, codigo);
    }

    private void addHeaderLocation(HttpServletResponse response, Long codigo) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(codigo).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
