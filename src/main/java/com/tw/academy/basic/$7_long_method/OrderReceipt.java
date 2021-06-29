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
        StringBuilder receipt  = new StringBuilder();

        receipt .append(ORDER_RECEIPT_HEADER);

        receipt .append(order.getCustomerName());
        receipt .append(order.getCustomerAddress());

        double totalSalesTax = 0d;
        double totalAmount  = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            receipt .append(lineItem.getDescription());
            receipt .append(TAB_CHAR);
            receipt .append(lineItem.getPrice());
            receipt .append(TAB_CHAR);
            receipt .append(lineItem.getQuantity());
            receipt .append(TAB_CHAR);
            receipt .append(lineItem.totalAmount());
            receipt .append(LINE_BREAK_CHAR);

            double salesTax = lineItem.totalAmount() * ORDER_TAX_RATE;
            totalSalesTax += salesTax;

            totalAmount  += lineItem.totalAmount() + salesTax;
        }

        receipt .append(ORDER_RECEIPT_SALES_TAX).append(TAB_CHAR).append(totalSalesTax);

        receipt .append(ORDER_RECEIPT_TOTAL_AMOUNT).append(TAB_CHAR).append(totalAmount );
        return receipt .toString();
    }
}