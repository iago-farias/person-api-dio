package com.personapi.controller;

import com.personapi.dto.request.PersonDTO;
import com.personapi.exception.PersonNotFoundException;
import com.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> listAll(){
        List<PersonDTO> personList = personService.listAll();

        return ResponseEntity.ok(personList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable(value = "id", required = true) Long id) throws PersonNotFoundException {
        PersonDTO person = personService.findById(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody @Valid PersonDTO personDTO){
       PersonDTO personSaved = personService.createPerson(personDTO);

       return ResponseEntity.ok(personSaved);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable(value = "id", required = true) Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException{
        PersonDTO personUpdated = personService.updatePerson(id, personDTO);

        return ResponseEntity.ok(personUpdated);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> deletePerson(@PathVariable(value = "id") Long id) throws PersonNotFoundException{
        PersonDTO personDeleted = personService.deletePerson(id);

        return ResponseEntity.ok().body(personDeleted);
    }
}
