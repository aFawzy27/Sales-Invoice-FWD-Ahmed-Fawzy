/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sales.invoice.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sales.invoice.view.SalesInvoiceFrame;


/**
 *
 * @author Ahmed Fawzy
 */
public class InvoiceHeaderTableModel extends AbstractTableModel {
    private String[] columns = {"No.","Date", "Customer","Total"};  
    private List<InvoiceData> invoices;
    public InvoiceHeaderTableModel (List<InvoiceData> invoices){
        this.invoices = invoices;
        
    }

    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex){
        return columns[columnIndex];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceData inv = invoices.get(rowIndex);
        switch (columnIndex) {
            case 0 : return inv.getNum();
            case 1 : return inv.getCustomerName();
            case 2 : return SalesInvoiceFrame.sdf.format(inv.getDate());
            case 3 : return inv.getTotal();      
        }
        return "";
    }
    

}