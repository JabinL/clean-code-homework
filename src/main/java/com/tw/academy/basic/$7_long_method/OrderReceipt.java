package com.tw.academy.basic.$7_long_method;

/**
 * This class is a example for bad smell;
 *
 * @author Thoughtworks
 * @version 1.0
 * @since 2018-1-1
 */
public class OrderReceipt {
    private Order order;
    private static final String ORDER_RECEIPT_HEADER = "======Printing Orders======\n";
    private static final String ORDER_RECEIPT_SALES_TAX = "Sales Tax";
    private static final String ORDER_RECEIPT_TOTAL_AMOUNT = "Total Amount";
    private static final double ORDER_TAX_RATE = .10;
    private static final char TAB_CHAR = '\t';
    private static final char LINE_BREAK_CHAR = '\n';

    private StringBuilder receipt;

    public OrderReceipt(Order order) {
        this.order = order;
        this.receipt = new StringBuilder();
    }

    private void initReceiptHeader() {
        receipt.append(ORDER_RECEIPT_HEADER);
        receipt.append(order.getCustomerName());
        receipt.append(order.getCustomerAddress());
    }

    public String printReceipt() {

        initReceiptHeader();
        double totalSalesTax = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            receipt.append(lineItem.getDescription());
            receipt.append(TAB_CHAR);
            receipt.append(lineItem.getPrice());
            receipt.append(TAB_CHAR);
            receipt.append(lineItem.getQuantity());
            receipt.append(TAB_CHAR);
            receipt.append(lineItem.totalAmount());
            receipt.append(LINE_BREAK_CHAR);

            double salesTax = lineItem.totalAmount() * ORDER_TAX_RATE;
            totalSalesTax += salesTax;

            totalAmount += lineItem.totalAmount() + salesTax;
        }

        receipt.append(ORDER_RECEIPT_SALES_TAX).append(TAB_CHAR).append(totalSalesTax);

        receipt.append(ORDER_RECEIPT_TOTAL_AMOUNT).append(TAB_CHAR).append(totalAmount);
        return receipt.toString();
    }
}