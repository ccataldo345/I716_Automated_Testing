package sql;

import java.util.List;

public class SelectBuilder {

    public void column(String column) {
    }

    public void from(String table) {
    }

    public String getSql() {
        return null;
    }

    public void columns(String ... columns) {
    }

    public void where(String condition, Object parameter) {
    }

    public void where(String condition) {
    }

    public List<Object> getParameters() {
        return null;
    }

    public void eqIfNotNull(String column, Object parameter) {
    }

    public void leftJoin(String table, String condition) {
    }

    public void in(String column, List<Object> parameters) {
    }

    public void from(SelectBuilder sub) {
    }
}