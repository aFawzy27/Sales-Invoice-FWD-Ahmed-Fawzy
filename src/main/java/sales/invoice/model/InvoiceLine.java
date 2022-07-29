/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sales.invoice.model;

/**
 *
 * @author Ahmed Fawzy
 */
public class InvoiceLine {
    String itemName;
    double itemPrice;
    int count; 
    InvoiceData invoice;

    public InvoiceLine(String itemName, double itemPrice, int count, InvoiceData invoice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.invoice = invoice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InvoiceData getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceData invoice) {
        this.invoice = invoice;
    }
    
    public double getTotal(){
        return getCount() * getItemPrice();
    }

    public String getAsCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
