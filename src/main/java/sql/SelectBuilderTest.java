package sql;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SelectBuilderTest {

    @Test
    public void selectOneColumn() {
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");

        assertThat(b.getSql(), is("select a from t"));
    }

    @Test
    public void selectMultipleColumns() {
        SelectBuilder b = new SelectBuilder();
        b.columns("a", "b");
        b.from("t");

        assertThat(b.getSql(), is("select a, b from t"));
        System.out.println(b.getSql());
    }

    @Test
    public void selectMultipleColumnsAlsoAfterTable() {
        SelectBuilder b = new SelectBuilder();
        b.columns("a", "b");
        b.from("t");
        b.columns("c");

        assertThat(b.getSql(), is("select a, b, c from t"));
        System.out.println(b.getSql());
    }

    @Test
    public void selectOneConditionParameters() {
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");
        b.where("id = ?", 1);

        assertThat(b.getSql(), is("select a from t where id = ?"));
        System.out.println(b.getSql());
        assertThat(b.getParameters(), is(asList(1)));
        // asList gives values in array > [1]
        System.out.println(b.getParameters());
    }

    @Test
    public void selectMultipleConditionParameters() {
        //test 05
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");
        b.where("is_hidden = 1");
        b.where("deleted_on is null");

        assertThat(b.getSql(),
                is("select a from t where is_hidden = 1 and deleted_on is null"));
        System.out.println(b.getSql());
    }

    @Test
    public void discardNullParameter() {
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");
        b.eqIfNotNull("a", 1);
        b.eqIfNotNull("b", null);
        b.eqIfNotNull("c", 3);

        assertThat(b.getSql(), is("select a from t where a = ? and c = ?"));
        System.out.println(b.getSql());
        assertThat(b.getParameters(), is(asList(1, 3)));
        // asList(1, 3) = [1, 3]
        System.out.println(b.getParameters());
    }

    @Test
    public void twoParameterAsList() {
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");
        b.in("id", asList(1, 2));

        assertThat(b.getSql(), is("select a from t where id in (?, ?)"));
        System.out.println(b.getSql());
        assertThat(b.getParameters(), is(asList(1, 2)));
        // asList(1, 2) = [1, 2]
        System.out.println(b.getParameters());
    }

    @Test
    public void emptyList() {
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");
        b.in("id", Collections.emptyList());

        assertThat(b.getSql(), is("select a from t"));
        System.out.println(b.getSql());
    }

    @Test
    public void leftJoin() {
        // test 09
        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from("t");
        b.leftJoin("u", "u.id = t.u_id");

        assertThat(b.getSql(), is("select a from t left join u on u.id = t.u_id"));
        System.out.println(b.getSql());
    }

    @Test
    public void subSelect() {
        SelectBuilder sub = new SelectBuilder();
        sub.column("b");
        sub.from("t");

        SelectBuilder b = new SelectBuilder();
        b.column("a");
        b.from(sub);

        assertThat(b.getSql(), is("select a from (select b from t)"));
        System.out.println(b.getSql());
    }
}