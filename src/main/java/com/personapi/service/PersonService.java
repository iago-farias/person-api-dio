package com.personapi.service;

import com.personapi.dto.request.PersonDTO;
import com.personapi.entity.Person;
import com.personapi.exception.PersonNotFoundException;
import com.personapi.mapper.PersonMapper;
import com.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public List<PersonDTO> listAll(){
        return personRepository
                .findAll()
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException{
        Person person = verifyIfPersonExists(id);

        return personMapper.toDTO(person);
    }

    public PersonDTO createPerson(PersonDTO personDTO){
        Person personSaved = personRepository.save(personMapper.toModel(personDTO));

        return personMapper.toDTO(personSaved);
    }

    public PersonDTO updatePerson(Long id, PersonDTO personDTO) throws PersonNotFoundException{
        verifyIfPersonExists(id);

        personDTO.setId(id);

        Person personUpdated = personRepository.save(personMapper.toModel(personDTO));

        return personMapper.toDTO(personUpdated);
    }

    public PersonDTO deletePerson(Long id) throws PersonNotFoundException{
        Person personDeleted = verifyIfPersonExists(id);

        personRepository.deleteById(id);

        return personMapper.toDTO(personDeleted);
    }

    private Person verifyIfPersonExists(Long id) throws PersonNotFoundException {
        Optional<Person> person = personRepository.findById(id);

        if(person.isEmpty()){
            throw new PersonNotFoundException(id);
        }

        return person.get();
    }
}
