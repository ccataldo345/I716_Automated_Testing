package refactoring;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


import org.junit.jupiter.api.Test;

public class RefactoringTests {

    private Refactoring sut = new Refactoring();

    @Test
    public void getsItemsAsHtml() throws Exception {
        assertThat(sut.getItemsAsHtml(), is(
                "<ul><li>1</li><li>2</li><li>3</li><li>4</li></ul>"));
    }

    @Test
    public void calculatesWeeklyPayWithOvertime() {
        assertThat(sut.calculateWeeklyPay(39, true), is(195));
        assertThat(sut.calculateWeeklyPay(41, true), is(208));
    }

    @Test
    public void calculatesWeeklyPayWithoutOvertime() {
        assertThat(sut.calculateWeeklyPay(39, false), is(195));
        assertThat(sut.calculateWeeklyPay(41, false), is(205));
    }
}
