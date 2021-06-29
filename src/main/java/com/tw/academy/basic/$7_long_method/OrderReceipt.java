package com.tw.academy.basic.$7_long_method;

/**
 * This class is a example for bad smell;
 *
 * @author  Thoughtworks
 * @version 1.0
 * @since   2018-1-1
 */
public class OrderReceipt {
    private Order order;
    public static final String ORDER_RECEIPT_HEADER = "======Printing Orders======\n";
    public static final String ORDER_RECEIPT_SALES_TAX = "Sales Tax";
    public static final String ORDER_RECEIPT_TOTAL_AMOUNT = "Total Amount";
    public static final double ORDER_TAX_RATE = .10;
    public static final char TAB_CHAR = '\t';
    public static final char LINE_BREAK_CHAR = '\n';

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        output.append(ORDER_RECEIPT_HEADER);

        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());

        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription());
            output.append(TAB_CHAR);
            output.append(lineItem.getPrice());
            output.append(TAB_CHAR);
            output.append(lineItem.getQuantity());
            output.append(TAB_CHAR);
            output.append(lineItem.totalAmount());
            output.append(LINE_BREAK_CHAR);

            double salesTax = lineItem.totalAmount() * ORDER_TAX_RATE;
            totSalesTx += salesTax;

            tot += lineItem.totalAmount() + salesTax;
        }

        output.append(ORDER_RECEIPT_SALES_TAX).append(TAB_CHAR).append(totSalesTx);

        output.append(ORDER_RECEIPT_TOTAL_AMOUNT).append(TAB_CHAR).append(tot);
        return output.toString();
    }
}