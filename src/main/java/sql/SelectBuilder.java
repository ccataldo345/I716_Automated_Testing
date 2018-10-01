package sql;

import java.util.ArrayList;
import java.util.List;

public class SelectBuilder {

    private List<String> col = new ArrayList<>();
    private List<String> tab = new ArrayList<>();
    private List<String> cond = new ArrayList<>();  // (where)
    private List<Object> param = new ArrayList<>();

    public void column(String column) {
        col.add(column);
    }

    public void from(String table) {
        tab.add(table);
    }

    public String getSql() {

        StringBuilder builder = new StringBuilder();

        builder.append("select ")
                .append(String.join(", ", col))
                .append(" from ")
                .append(String.join(", ", tab));

        if (!cond.isEmpty()) {
            builder.append(" where ")
                    .append(String.join(" and ", cond));
        }

        return builder.toString();
    }

    public void columns(String... columns) {
        for (String c : columns) {
            col.add(c);
        }
    }

    public void where(String condition, Object parameter) {
        cond.add(condition);
        param.add(parameter);
    }

    public void where(String condition) {
    }

    public List<Object> getParameters() {
        return param;
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