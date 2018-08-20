package com.devpimentel.restapi.restapi.service;

import com.devpimentel.restapi.restapi.model.Launch;
import com.devpimentel.restapi.restapi.model.Person;
import com.devpimentel.restapi.restapi.repository.LaunchRepository;
import com.devpimentel.restapi.restapi.repository.PersonRepository;
import com.devpimentel.restapi.restapi.service.exception.PersonNotExistOrInactiveException;
import org.springframework.beans.BeanUtils;
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

    public Launch updateLaunch(Long id, Launch launch) {
        Launch launchSaved = getLaunchExisting(id);

        if (!launch.getPerson().equals(launchSaved.getPerson())) {
            validatePerson(launch);
        }

        BeanUtils.copyProperties(launch, launchSaved, "id");

        return  launchRespository.save(launchSaved);
    }

    private void validatePerson(Launch launch) {
        Person person = null;

        if (launch.getPerson().getId() != null) {
            person = personRepository.findOne(launch.getPerson().getId());
        }

        if (person == null || person.isActive()) {
            throw new PersonNotExistOrInactiveException();
        }
    }

    private Launch getLaunchExisting(Long id) {
        Launch launchSaved = launchRespository.findOne(id);

        if (launchSaved == null) {
            throw new IllegalArgumentException();
        }

        return launchSaved;
    }
}
