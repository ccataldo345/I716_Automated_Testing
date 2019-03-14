package refactoring;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class RefactoringTests {

    private Refactoring sut = new Refactoring();

    @Test
    public void getsItemsAsHtml() {
        assertThat(sut.getItemsAsHtml(), is(
                "<ul><li>1</li><li>2</li><li>3</li><li>4</li></ul>"));
    }

    @Test
    public void calculatesWeeklyPayWithOvertime() {
        assertThat(sut.calculateWeeklyPayWithOvertime(39), is(195));
        assertThat(sut.calculateWeeklyPayWithOvertime(41), is(208));

        // testing new methods:

        // assertThat(sut.calculateWeeklyPayWithOvertime(39), is(195));
        // assertThat(sut.calculateWeeklyPayWithOvertime(41), is(208));
    }

    @Test
    public void calculatesWeeklyPayWithoutOvertime() {
        assertThat(sut.calculateWeeklyPayWithoutOvertime(39), is(195));
        assertThat(sut.calculateWeeklyPayWithoutOvertime(41), is(205));

        // testing new methods:

        // assertThat(sut.calculateWeeklyPayWithoutOvertime(39), is(195));
        // assertThat(sut.calculateWeeklyPayWithoutOvertime(41), is(205));
    }
}
