package com.devpimentel.restapi.restapi.service;

import com.devpimentel.restapi.restapi.model.Person;
import com.devpimentel.restapi.restapi.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person updatePerson(Long id, Person person) {
        Person updatePerson = getPersonId(id);

        BeanUtils.copyProperties(person, updatePerson, "id");
        return personRepository.save(updatePerson);
    }

    public void updatePropertyActive(Long id, Boolean active) {
        Person updatePerson = getPersonId(id);
        updatePerson.setActive(active);
        personRepository.save(updatePerson);
    }

    public Person getPersonId(Long id) {
        Person updatePerson = personRepository.findOne(id);

        if (updatePerson == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return updatePerson;
    }
}
