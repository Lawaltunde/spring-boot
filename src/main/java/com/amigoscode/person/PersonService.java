package com.amigoscode.person;

import com.amigoscode.SortingOrder;
import com.amigoscode.person.exception.BadRequestException;
import com.amigoscode.person.exception.ResourceNotFoundExecption;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople(
            SortingOrder sort
    ) {
        if (sort == SortingOrder.ASC) {
            return personRepository.getPeople().stream()
                    .sorted(Comparator.comparing(Person::id))
                    .collect(Collectors.toList());
        }
        return personRepository.getPeople().stream()
                .sorted(Comparator.comparing(Person::id).reversed())
                .collect(Collectors.toList());
    }


    public Person getPersonById(Integer id) {
        return personRepository.getPeople().stream()
                .filter(p -> p.id().equals(id))
                .findFirst().orElseThrow(() -> new ResourceNotFoundExecption(
                        "Person with id " + id + " does not exist"
                ));
    }

    public void deletePersonById(Integer id) {
        personRepository.getPeople().
                stream().
                filter(p -> p.id().equals(id)).
                findFirst().ifPresentOrElse(
                        p -> personRepository.getPeople().remove(p),
                        () -> {
                            throw new ResourceNotFoundExecption(
                                    "Person with id " + id + " does not exist"
                            );
                        }
                );
    }

    public void addPerson(NewPersonRequest person) {
        personRepository.getPeople().stream().
                filter(p -> p.email().equals(person.email())).
                findFirst().ifPresent(p -> {
                    throw new DuplicateResourceException(
                            "Email " + person.email() + " is taken"
                    );
                });
        personRepository.getPeople().add(
                new Person(
                        personRepository.getIdCounter().incrementAndGet(),
                        person.name(),
                        person.age(),
                        person.gender(),
                        person.email()
                )
        );
    }

    public void updatePerson(Integer id,
                             PersonUpdateRequest request) {
        Person p = personRepository.getPeople().stream()
                .filter(person -> person.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundExecption("Person with id " + id + " does not exist"));

        var index = personRepository.getPeople().indexOf(p);

        if (request.name() != null &&
                !request.name().isEmpty() &&
                !request.name().equals(p.name())) {
            Person person = new Person(
                    p.id(),
                    request.name(),
                    p.age(),
                    p.gender(),
                    p.email()

            );
            personRepository.getPeople().set(index, person);
        }
        if (request.email() != null &&
                !request.email().isEmpty() &&
                !request.email().equals(p.email())) {
            Person person = new Person(
                    p.id(),
                    p.name(),
                    p.age(),
                    p.gender(),
                    request.email()

            );
            personRepository.getPeople().set(index, person);
        }
        if (request.age() != null
                && !request.age().equals(p.age())) {
            Person person = new Person(
                    p.id(),
                    p.name(),
                    request.age(),
                    p.gender(),
                    p.email()

            );
            personRepository.getPeople().set(index, person);
        }
    }

}
