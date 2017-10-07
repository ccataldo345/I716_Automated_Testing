package sql;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SelectBuilderTest {

    @Test
    public void test1() {
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");

        assertThat(b.getSql(), is("select a from t"));
    }

    @Test
    public void test2() {
        SelectBuilder b = new SelectBuilder();
        b.columns("a", "b");
        b.from("t");

        assertThat(b.getSql(), is("select a, b from t"));
    }

    @Test
    public void test3() {
        SelectBuilder b = new SelectBuilder();
        b.columns("a", "b");
        b.from("t");
        b.columns("c");

        assertThat(b.getSql(), is("select a, b, c from t"));
    }

    @Test
    public void test4() {
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");
        b.where("id = ?", 1);

        assertThat(b.getSql(), is("select a from t where id = ?"));
        assertThat(b.getParameters(), is(asList(1)));

    }

    @Test
    public void test5() {
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");
        b.where("is_hidden = 1");
        b.where("deleted_on is null");

        assertThat(b.getSql(),
                is("select a from t where is_hidden = 1 and deleted_on is null"));
    }

    @Test
    public void test6() {
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");
        b.eqIfNotNull("a", 1);
        b.eqIfNotNull("b", null);
        b.eqIfNotNull("c", 3);

        assertThat(b.getSql(), is("select a from t where a = ? and c = ?"));
        assertThat(b.getParameters(), is(asList(1, 3)));
    }

    @Test
    public void test7() {
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");
        b.in("id", asList(1, 2));

        assertThat(b.getSql(), is("select a from t where id in (?, ?)"));
        assertThat(b.getParameters(), is(asList(1, 2)));
    }

    @Test
    public void test8() {
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");
        b.in("id", Collections.emptyList());

        assertThat(b.getSql(), is("select a from t"));
    }

    @Test
    public void test9() {
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");
        b.leftJoin("u", "u.id = t.u_id");

        assertThat(b.getSql(), is("select a from t left join u on u.id = t.u_id"));
    }

    @Test
    public void test10() {
        SelectBuilder sub = new SelectBuilder();
        sub.column("b");
        sub.from("t");

        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from(sub);

        assertThat(b.getSql(), is("select a from (select b from t)"));
    }
}

