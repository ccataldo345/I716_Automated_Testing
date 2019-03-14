package sql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectBuilder {

    private List<String> col = new ArrayList<>();
    private List<String> tab = new ArrayList<>();
    private List<String> cond = new ArrayList<>();  // condition (where...)
    private List<Object> param = new ArrayList<>();
    private List<String> colNotNullParam = new ArrayList<>();
    private List<String> paramAsList = new ArrayList<>();
    private List<String> leftJoinTab = new ArrayList<>();
    private List<String> subCol = new ArrayList<>();
    private List<String> subTab = new ArrayList<>();

    public void column(String column) {
        col.add(column);
    }

    public void from(String table) {
        tab.add(table);
    }

    public String getSql() {

        StringBuilder builder = new StringBuilder();

        if (!subCol.isEmpty()) {
            builder.append("select ")
                    .append(String.join(", ", col))
                    .append(" from (select ")
                    .append(String.join(", ", subCol))
                    .append(" from ")
                    .append(String.join(", ", subTab))
                    .append(")");
        }

        else {

            builder.append("select ")
                    .append(String.join(", ", col))
                    .append(" from ")
                    .append(String.join(", ", tab));

            if (!cond.isEmpty() && leftJoinTab.isEmpty()) {
                builder.append(" where ")
                        .append(String.join(" and ", cond));
            }

            if (!colNotNullParam.isEmpty()) {
                builder.append(" where ")
                        .append(String.join(" = ? and ", colNotNullParam))
                        .append(" = ?");
            }

            if (!paramAsList.isEmpty()) {
                builder.append(" where ")
                        .append(String.join(", ", paramAsList))
                        .append(" in (?, ?)");
            }

            if (!leftJoinTab.isEmpty()) {
                builder.append(" left join ")
                        .append(String.join(", ", leftJoinTab))
                        .append(" on ")
                        .append(String.join(", ", cond));
            }
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
        cond.add(condition);
    }

    public List<Object> getParameters() {
        return param;
    }

    public void eqIfNotNull(String column, Object parameter) {
        if (parameter != null) {
            colNotNullParam.add(column);
            param.add(parameter);
        }
    }

    public void leftJoin(String table, String condition) {
        leftJoinTab.add(table);
        cond.add(condition);
    }

    public void in(String column, List<Object> parameters) {

        if (parameters != Collections.emptyList()) {
            paramAsList.add(column);
            param.addAll(parameters);
        }
    }

    public void from(SelectBuilder sub) {
        subCol.addAll(sub.col);
        subTab.addAll(sub.tab);
    }
}
