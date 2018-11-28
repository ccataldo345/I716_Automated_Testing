package primefractors;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class PrimeFractorsTest {

    @Test
    public void findPrimeFactors() {
        assertThat(getPrimeFactorsOf(2), are(2));
        assertThat(getPrimeFactorsOf(3), are(3));
        assertThat(getPrimeFactorsOf(4), are(2, 2));
        assertThat(getPrimeFactorsOf(5), are(5));
        assertThat(getPrimeFactorsOf(6), are(2, 3));
        assertThat(getPrimeFactorsOf(7), are(7));
        assertThat(getPrimeFactorsOf(8), are(2, 2, 2));
        assertThat(getPrimeFactorsOf(9), are(3, 3));
        assertThat(getPrimeFactorsOf(23*19*17*2*7*7), are(2, 7, 7, 17, 19, 23));
        assertThat(getPrimeFactorsOf(31*101*53*7*3*3), are(3, 3, 7, 31, 53, 101));
    }

    private List<Integer> getPrimeFactorsOf(int n) {
        ArrayList<Integer> factors = new ArrayList<>();

        for (int divisor = 2; n > 1; divisor++) {
            for (; n % divisor == 0; n /= divisor) {
                factors.add(divisor);
            }
        }

        return factors;
    }

    private Matcher<List<Integer>> are(Integer ...factors) {
        return is(Arrays.asList(factors));
    }

}
