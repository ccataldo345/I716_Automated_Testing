package person;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static java.util.Arrays.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

public class PersonUtilTest {

    private PersonUtil personUtil = new PersonUtil();

    @Test
    public void findsOldestPerson() {
        Person p1 = aPerson().withAge(32).build();
        Person p2 = aPerson().withAge(55).build();
        Person p3 = aPerson().withAge(21).build();

        assertThat(personUtil.getOldest(asList(p1, p2, p3)), is(p2));
    }

    @Test
    public void findsPersonsInLegalAge() {
        Person p1 = aPerson().withAge(12).build();
        Person p2 = aPerson().withAge(33).build();

        assertThat(personUtil.getPersonsInLegalAge(asList(p1, p2)), is(asList(p2)));
    }

    @Test
    public void findsWomen() {
        //fail("not implemented");

        Person p1 = aPerson().withGender("M").build();
        Person p2 = aPerson().withGender("F").build();

        assertThat(personUtil.getWomen(asList(p1, p2)), is(asList(p2)));
    }

    @Test
    public void findsPersonsLivingInSpecifiedTown() {
        // fail("not implemented");
        Person p1 = aPerson().withTown("Tallinn").build();
        Person p2 = aPerson().withTown("Tartu").build();

        assertThat(personUtil.getPersonsWhoLiveIn("Tallinn", asList(p1, p2)), is(asList(p1)));
    }

    private PersonBuilder aPerson() {
        return new PersonBuilder();
    }

}
