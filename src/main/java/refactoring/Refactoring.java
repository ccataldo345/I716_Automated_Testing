package refactoring;

import java.util.*;

public class Refactoring {

    // 1a
    public int incrementInvoiceNumber() {
        return ++invoiceNumber;
    }

    // 1b
    public void searchFilledOrders(List<Order> orderList) {
        for (Order order : orders) {
            if (order.isFilled()) {
                orderList.add(order);
            }
        }
    }

    // 2a
    public void printStatementFor(Long invoiceId) {
        List<InvoiceRow> invoiceRows = dao.getInvoiceRowsFor(invoiceId);

        printInvoiceRows(invoiceRows);

        calculateInvoiceTotal(invoiceRows);
    }

    private void calculateInvoiceTotal(List<InvoiceRow> invoiceRows) {
        double total = 0;
        for (InvoiceRow invoiceRow : invoiceRows) {
            total += invoiceRow.getAmount();
        }

        printValue(total);
    }

    // 2b
    public String getItemsAsHtml() {
        String result = "";

        for (String i: items) {
            result+= createTag("li", i);
        }

        result = createTag("ul", result);

        return result;

        /*
        result += "<li>" + items.get(0) + "</li>";
        result += "<li>" + items.get(1) + "</li>";
        result += "<li>" + items.get(2) + "</li>";
        result += "<li>" + items.get(3) + "</li>";
        result += "</ul>";
        return result;
        */
    }

    public String createTag(String tag, String content) {

        return "<" + tag + ">" + content + "</" + tag + ">";
    }

    // 3
    public boolean isSmallOrder() {
        return order.getTotal() > 100;
        /*
        double orderTotal = order.getTotal();
        return orderTotal > 100;
        */
    }

    // 4
    public void printPrice() {

        /*
        double basePrice = getBasePrice();
        System.out.println("Price not including VAT: " + basePrice);
        System.out.println("Price including VAT: " + basePrice * 1.2);
        */

        System.out.println("Price not including VAT: " + getBasePrice());
        System.out.println("Price including VAT: " + getBasePrice() * VAT);

    }

    static final double VAT = 1.2;

    // 5
    public void calculatePayFor(Job job) {
        // on weekends at night

        final boolean weekends = job.day == 6 || job.day == 7;
        final boolean night = job.hour >20 || job.hour < 7;

        if (weekends && night) {
        }

        /*
        if ((job.day == 6 || job.day == 7)
                && (job.hour > 20 || job.hour < 7))  {

        }
        */
    }

    // 6
    public boolean canAccessResource(SessionData sessionData) {
        // is admin and has preferred status

        return (searchAdmin(sessionData) && searchPreferredStatus(sessionData));
    }

    private boolean searchAdmin(SessionData sessionData) {
        return (sessionData.getCurrentUserName().equals("Admin")
                    || sessionData.getCurrentUserName().equals("Administrator"));
    }

    private boolean searchPreferredStatus(SessionData sessionData) {
        return (sessionData.getStatus().equals("preferredStatusX")
                    || sessionData.getStatus().equals("preferredStatusY"));
    }

    // 7
    public void drawLines() {
        Space space = new Space();
        space.drawLine(new xValues(12, 2), new yValues(3, 4), new zValues(5, 6));
        space.drawLine(new xValues(2, 0), new yValues(4, 1), new zValues(6, 0));
    }

    // 8
    public int calculateWeeklyPayWithOvertime(int hoursWorked) {
        int straightTime = Math.min(40, hoursWorked);
        int overTime = Math.max(0, hoursWorked - straightTime);
        int straightPay = straightTime * hourRate;
        double overtimeRate = 1.5 * hourRate;
        int overtimePay = (int) Math.round(overTime * overtimeRate);
        return straightPay + overtimePay;
    }

    public int calculateWeeklyPayWithoutOvertime(int hoursWorked) {
        return hoursWorked * hourRate;
    }

    // //////////////////////////////////////////////////////////////////////////

    // Helper fields and methods.
    // They are here just to make the code compile

    private List<String> items = Arrays.asList("1", "2", "3", "4");
    private int hourRate = 5;
    int invoiceNumber = 0;
    private List<Order> orders = new ArrayList<>();
    private Order order = new Order();
    private Dao dao = new SampleDao();
    private double price = 0;

    void justACaller() {
        incrementInvoiceNumber();
        searchFilledOrders(null);
    }

    private void printValue(double total) {
    }

    private void printInvoiceRows(List<InvoiceRow> invoiceRows) {
    }

    class Space {
        public void drawLine(xValues xValues, yValues yValues, zValues zValues) {
        }

    }

    interface Dao {
        List<InvoiceRow> getInvoiceRowsFor(Long invoiceId);
    }

    class SampleDao implements Dao {
        @Override
        public List<InvoiceRow> getInvoiceRowsFor(Long invoiceId) {
            return null;
        }
    }

    class Order {
        public boolean isFilled() {
            return false;
        }

        public double getTotal() {
            return 0;
        }
    }

    class InvoiceRow {
        public double getAmount() {
            return 0;
        }
    }

    class Job {
        public int hour;
        public int day;
    }

    private double getBasePrice() {
        return price;
    }

    private class SessionData {

        public String getCurrentUserName() {
            return null;
        }

        public String getStatus() {
            return null;
        }

    }

}
