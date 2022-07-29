/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sales.invoice.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ahmed Fawzy
 */
public class InvoiceLineTableModel extends AbstractTableModel {
     private String[] columns = {"No.","Item Name", "Item Price", "Count", "Item Total"};  
    private List<InvoiceLine> lines;
    public InvoiceLineTableModel (List<InvoiceLine> lines){
        this.lines = lines;
        
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    
    
    
    @Override
    public int getRowCount() {
        return lines.size();
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
        InvoiceLine line = lines.get(rowIndex);
        switch (columnIndex) {
            case 0 : return line.getInvoice().getNum();
            case 1 : return line.getItemName();
            case 2 : return line.getItemPrice();
            case 3 : return line.getCount();
            case 4 : return line.getTotal();
        }
        return "";
    }
    
}
