/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sales.invoice.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ahmed Fawzy
 */
public class InvoiceData {
    private int num;
    private String customerName;
    private Date date;
    private ArrayList<InvoiceLine> lines;

    public InvoiceData(int num, String customerName, Date date) {
        this.num = num;
        this.customerName = customerName;
        this.date = date;
    }
    
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<InvoiceLine> getLines() {
      if (lines == null){
          lines = new ArrayList<>();
      }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    public double getTotal(){
        double total = 0.0;
        for (InvoiceLine InvoiceLine : getLines()){
            total += InvoiceLine.getTotal();
        }
        return total;
    }

    public String getAsCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
