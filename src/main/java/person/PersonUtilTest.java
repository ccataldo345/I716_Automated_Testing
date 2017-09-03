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
        fail("not implemented");
    }

    @Test
    public void findsWomen() {
        fail("not implemented");
    }

    @Test
    public void findsPersonsLivingInSpecifiedTown() {
        fail("not implemented");
    }

    private PersonBuilder aPerson() {
        return new PersonBuilder();
    }
}
