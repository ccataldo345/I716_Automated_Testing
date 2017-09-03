package refactoring;

import java.util.*;

public class Refactoring {

    // 1a
    public int getInvoiceNumber() {
        return ++invoiceNumber;
    }

    // 1b
    public void findFilledOrders(List<Order> orderList) {
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

        // calculate invoice total
        double total = 0;
        for (InvoiceRow invoiceRow : invoiceRows) {
            total += invoiceRow.getAmount();
        }

        printValue(total);
    }

    // 2b
    public String getItemsAsHtml() {
        String result = "";
        result += "<ul>";
        result += "<li>" + items.get(0) + "</li>";
        result += "<li>" + items.get(1) + "</li>";
        result += "<li>" + items.get(2) + "</li>";
        result += "<li>" + items.get(3) + "</li>";
        result += "</ul>";
        return result;
    }

    // 3
    public boolean isSmallOrder() {
        double orderTotal = order.getTotal();
        return (orderTotal > 100);
    }

    // 4
    public void printPrice() {

        double basePrice = getBasePrice();
        System.out.println("Price not including VAT: " + basePrice);
        System.out.println("Price including VAT: " + basePrice * 1.2);
    }

    // 5
    public void calculatePayFor(Job job) {
        // on holiday at night
        if ((job.day == 6 || job.day == 7)
                && (job.hour > 20 || job.hour < 7))  {

        }
    }

    // 6
    public boolean canAccessResource(SessionData sessionData) {
        // is admin and has preferred status

        return (
                (sessionData.getCurrentUserName().equals("Admin")
                    || sessionData.getCurrentUserName().equals("Administrator"))
                && (sessionData.getStatus().equals("preferredStatusX")
                    || sessionData.getStatus().equals("preferredStatusY"))
               );
    }

    // 7
    public void drawLines() {
        Space space = new Space();
        space.drawLine(12, 3, 5, 2, 4, 6);
        space.drawLine(2, 4, 6, 0, 1, 0);
    }

    // 8
    public int calculateWeeklyPay(int hoursWorked, boolean overtime) {
        int straightTime = Math.min(40, hoursWorked);
        int overTime = Math.max(0, hoursWorked - straightTime);
        int straightPay = straightTime * hourRate;
        double overtimeRate = overtime ? 1.5 * hourRate : 1.0 * hourRate;
        int overtimePay = (int) Math.round(overTime * overtimeRate);
        return straightPay + overtimePay;
    }

    // //////////////////////////////////////////////////////////////////////////

    // Helper fields and methods.
    // They are here just to make code compile

    private List<String> items = Arrays.asList("1", "2", "3", "4");
    private int hourRate = 5;
    int invoiceNumber = 0;
    private List<Order> orders = new ArrayList<>();
    private Order order = new Order();
    private Dao dao = new SampleDao();
    private double price = 0;

    void justACaller() {
        getInvoiceNumber();
        findFilledOrders(null);
    }

    private void printValue(double total) {
    }

    private void printInvoiceRows(List<InvoiceRow> invoiceRows) {
    }

    class Space {
        public void drawLine(int x1, int y1, int z1, int x2, int y2, int z2) {
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
