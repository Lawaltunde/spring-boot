package com.amigoscode.person;

import com.amigoscode.SortingOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPeople(
            @RequestParam(
                    value = "sort",
                    required = false,
                    defaultValue = "ASC"
            ) SortingOrder sort) {
        return personService.getPeople(sort);
    }


    @GetMapping("{id}")
    public ResponseEntity<Optional<Person>> getPersonById(
            @PathVariable @Positive Integer id
    ) {
        Optional<Person> person = personService.getPersonById(id);
        return ResponseEntity.ok().body(person);
    }

    @DeleteMapping("{id}")
    public void deletePersonById(@PathVariable @Positive Integer id) {
        personService.deletePersonById(id);
    }

    @PostMapping
    public void addPerson(@RequestBody @Valid NewPersonRequest person) {
        personService.addPerson(person);
    }

    @PutMapping("{id}")
    public void updatePerson(@PathVariable @Positive Integer id,
                             @Valid @RequestBody PersonUpdateRequest request) {
        personService.updatePerson(id, request);
    }

}
