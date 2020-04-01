package com.LearnSpring.api;

import com.LearnSpring.model.Person;
import com.LearnSpring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/person")

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody @Valid @NotNull Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPerople() throws SQLException {
        return personService.getAllPerople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) throws SQLException {
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);

    }
    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id,  @RequestBody @Valid @NotNull Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }
}
