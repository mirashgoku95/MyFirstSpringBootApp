package com.LearnSpring.dao;

import com.LearnSpring.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.List;


@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    private static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getID().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isEmpty()){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person difPerson) {

        return selectPersonById(id)
                .map(person -> {
                            int indecOfPersonToUpdate = DB.indexOf(person);
                            if(indecOfPersonToUpdate >= 0)
                            {
                                DB.set(indecOfPersonToUpdate, new Person(id, difPerson.getName()));
                                return 1;
                            }
                            return 0;
                            }).orElse( 0);
    }
}
