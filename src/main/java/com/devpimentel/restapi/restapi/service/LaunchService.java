package com.devpimentel.restapi.restapi.service;

import com.devpimentel.restapi.restapi.model.Launch;
import com.devpimentel.restapi.restapi.model.Person;
import com.devpimentel.restapi.restapi.repository.LaunchRepository;
import com.devpimentel.restapi.restapi.repository.PersonRepository;
import com.devpimentel.restapi.restapi.service.exception.PersonNotExistOrInactiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaunchService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LaunchRepository launchRespository;

    public Launch createLaunch(Launch launch) {
        Person person = personRepository.findOne(launch.getPerson().getId());

        if (person == null || person.isActive()) {
            throw new PersonNotExistOrInactiveException();
        }

        return launchRespository.save(launch);
    }
}
