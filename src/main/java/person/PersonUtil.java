package person;

import java.util.ArrayList;
import java.util.List;

public class PersonUtil {

    public Person getOldest(List<Person> persons) {
        ensureNotNull(persons);
        ensureNotEmpty(persons);

        Person oldestPerson = persons.get(0);
        for (Person person : persons) {
            if (person.getAge() > oldestPerson.getAge()) {
                oldestPerson = person;
            }
        }

        return oldestPerson;
    }

    public List<Person> getPersonsInLegalAge(List<Person> persons) {
        ensureNotNull(persons);

        List<Person> matching = new ArrayList<>();
        for (Person person : persons) {
            if (person.getAge() >= Person.LEGAL_AGE) {
                matching.add(person);
            }
        }

        return matching;
    }

    public List<Person> getWomen(List<Person> persons) {
        ensureNotNull(persons);

        List<Person> matching = new ArrayList<>();
        for (Person person : persons) {
            if (Person.GENDER_FEMALE.equals(person.getGender())) {
                matching.add(person);
            }
        }

        return matching;
    }

    public List<Person> getPersonsWhoLiveIn(String town, List<Person> persons) {
        ensureNotNull(persons);
        ensureNotNull(town);

        List<Person> matching = new ArrayList<>();
        for (Person person : persons) {
            if (town.equals(person.getAddress().getTown())) {
                matching.add(person);
            }
        }

        return matching;
    }

    private void ensureNotEmpty(List<Person> persons) {
        if (persons.isEmpty()) throw new IllegalArgumentException();
    }

    private void ensureNotNull(Object object) {
        if (object == null) throw new IllegalArgumentException();
    }
}