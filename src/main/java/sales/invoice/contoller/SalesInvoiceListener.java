/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sales.invoice.contoller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sales.invoice.model.InvoiceData;
import sales.invoice.model.InvoiceHeaderTableModel;
import sales.invoice.model.InvoiceLine;
import sales.invoice.model.InvoiceLineTableModel;
import sales.invoice.view.CreateNewInvoiceDialog;
import sales.invoice.view.NewItemDialog;
import sales.invoice.view.SalesInvoiceFrame;

/**
 *
 * @author Ahmed Fawzy
 */
public class SalesInvoiceListener implements ActionListener, ListSelectionListener{

    private SalesInvoiceFrame frame;
    private CreateNewInvoiceDialog invoiceDialog;
    private NewItemDialog itemDialog;
    
    public SalesInvoiceListener (SalesInvoiceFrame frame){
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "New Item":
                newItem();
                break;    
            case "Delete Item":
                deleteItem();
                break;    
            case "Load File":
                loadFile(null, null);
                break;    
            case "Save File":
                saveFile();
                break;    
            case "Create New Invoice OK":
                createNewInvoiceOK();
                break;
            case "Create New Invoice Cancel":
                createNewInvoiceCancel();
                break;
            case "Create Item OK":
                createItemOK();
                break;
            case "Create Item Cancel":
                createItemCancel();
                break;           
                
        } 
        
        
       
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = frame.getInvoicesTable().getSelectedRow();
        if (row > -1 && row < frame.getInvoices().size()){
            InvoiceData inv = frame.getInvoices().get(row);
            frame.getCustomerNameLabel().setText(inv.getCustomerName());
            frame.getInvoiceDateLabel().setText(frame.sdf.format(inv.getDate()));
            frame.getInvoiceNumberLabel().setText(""+inv.getNum());
            frame.getInvoiceTotalLabel().setText(""+inv.getTotal());

            List<InvoiceLine> lines = inv.getLines();
            frame.getInvoiceItemsTable().setModel(new InvoiceLineTableModel(lines));
        } else {
            frame.getCustomerNameLabel().setText("");
            frame.getInvoiceDateLabel().setText("");
            frame.getInvoiceNumberLabel().setText("");
            frame.getInvoiceTotalLabel().setText("");
            frame.getInvoiceItemsTable().setModel(new InvoiceLineTableModel(new ArrayList<InvoiceLine>()));
        }

        
    }

    private void createNewInvoice() {
        invoiceDialog = new CreateNewInvoiceDialog(frame);
        invoiceDialog.setVisible(true);
        
    }

    private void deleteInvoice() {
        int row = frame.getInvoicesTable().getSelectedRow();
        if (row != -1){
        frame.getInvoices().remove(row);
        ((InvoiceHeaderTableModel)frame.getInvoicesTable().getModel()).fireTableDataChanged();
        }
    }        

    private void newItem () {
        itemDialog = new NewItemDialog(frame);
        itemDialog.setVisible(true);
        
    }

    
    public void loadFile(String headerPath, String linePath){
        File headerFile = null; 
        File lineFile = null;
        if (headerPath == null && linePath == null){
            JFileChooser fc = new JFileChooser();
            int result = fc.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION){
                headerFile = fc.getSelectedFile();
                result = fc.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION){
                    lineFile = fc.getSelectedFile();
                }
            }
        } else {
            headerFile = new File (headerPath);
            lineFile = new File (linePath);
        }
        
        if (headerFile != null && lineFile != null){
            try{
                List<String> headerLines = Files.lines(Paths.get(headerFile.getAbsolutePath())).collect(Collectors.toList());
                List<String> lineLines = Files.lines(Paths.get(lineFile.getAbsolutePath())).collect(Collectors.toList());

                ArrayList<InvoiceData> invoices = new ArrayList<>();
                for (String headerLine : headerLines){
                     String[] parts =  headerLine.split(",");
                     String numString = parts[0];
                     String dateString = parts[1];
                     String customerName = parts[2];
                     int num = Integer.parseInt(numString);
                     Date date = SalesInvoiceFrame.sdf.parse(dateString);
                     InvoiceData inv = new InvoiceData(num, customerName, date);
                     frame.getInvoices().add(inv);
                }
                for (String lineLine : lineLines){
                    String[] parts =  lineLine.split(",");
                    int num = Integer.parseInt(parts[0]);
                    String itemName = parts[1];
                    double itemPrice = Double.parseDouble(parts[2]);
                    int count = Integer.parseInt(parts[3]);
                    InvoiceData inv = getInvoiceDataByNum(num);
                    InvoiceLine line = new InvoiceLine(itemName, itemPrice, count, inv);
                    inv.getLines().add(line);
                }
                
                frame.getInvoicesTable().setModel(new InvoiceHeaderTableModel(frame.getInvoices()));
                
            } catch (Exception ex){
                ex.printStackTrace();
            }
            
        }
    }

    private InvoiceData getInvoiceDataByNum(int num){
        for (InvoiceData inv : frame.getInvoices()){
          if (num == inv.getNum()){
              return inv;
          }
        }
        return null;
    }
    
    
    
    private void saveFile() {
        ArrayList<InvoiceData> Invoices = frame.getInvoices();
        String Headers = "";
        String Lines = "";
        for (InvoiceData Invoice : Invoices){
            String invoiceCSV = Invoice.getAsCSV();
            Headers += invoiceCSV;
            Headers += "\n";
            
            for (InvoiceLine Line : Invoice.getLines()){
                String lineCSV = Line.getAsCSV();
                Lines += lineCSV;
                Lines += "\n";
            }
        }
        try {
        JFileChooser FC = new JFileChooser();
        int Result = FC.showSaveDialog(frame);
        if (Result == JFileChooser.APPROVE_OPTION){
            File HeaderFile = FC.getSelectedFile();
            FileWriter HeadersFW = new FileWriter(HeaderFile);
            HeadersFW.write(Headers);
            HeadersFW.flush();
            HeadersFW.close();
            
            Result = FC.showSaveDialog(frame);
            if (Result == JFileChooser.APPROVE_OPTION){
            File LineFile = FC.getSelectedFile();
            FileWriter LinesFW = new FileWriter(LineFile);
            LinesFW.write(Lines);
            LinesFW.flush();
            LinesFW.close();
            }
        }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            }
        
    }

    private void deleteItem() {
        int row = frame.getInvoiceItemsTable().getSelectedRow();
        if (row != -1){
            int headerRow = frame.getInvoicesTable().getSelectedRow();
           InvoiceLineTableModel invoiceLineTableModel = (InvoiceLineTableModel) frame.getInvoiceItemsTable().getModel();
           invoiceLineTableModel.getLines().remove(row);
           invoiceLineTableModel.fireTableDataChanged();
           ((InvoiceHeaderTableModel)frame.getInvoicesTable().getModel()).fireTableDataChanged();
           frame.getInvoicesTable().setRowSelectionInterval(headerRow, headerRow);
        }
        
    }

    private void createNewInvoiceOK() {
        String customerName = invoiceDialog.getCustomerNameField().getText();
        String date = invoiceDialog.getInviceDateField().getText();
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        int num = getNextInvoiceDataNum();
        try {
            Date invDate = frame.sdf.parse(date);
            InvoiceData inv = new InvoiceData(num, customerName, invDate);
            frame.getInvoices().add(inv);
            ((InvoiceHeaderTableModel)frame.getInvoicesTable().getModel()).fireTableDataChanged();
        } catch (ParseException pex){
            JOptionPane.showMessageDialog(frame, "Error in Date format", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private int getNextInvoiceDataNum(){
        int num = 1;
        for (InvoiceData inv : frame.getInvoices()){
            if (inv.getNum() > num){
                num = inv.getNum();
            }
        }
        return num + 1;
    }

    private void createNewInvoiceCancel() {
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
    }

    private void createItemOK() {
        String itemName = itemDialog.getItemNameField().getText();
        double itemPrice = Double.parseDouble(itemDialog.getItemPriceField().getText());
        int itemCount = Integer.parseInt(itemDialog.getItemCountField().getText());      
          
        int selectedInvoice = frame.getInvoicesTable().getSelectedRow();
        if (selectedInvoice != -1){
            InvoiceData invoice = frame.getInvoices().get(selectedInvoice);
            InvoiceLine line = new InvoiceLine(itemName, itemPrice, itemCount, invoice);
            invoice.getLines().add(line);
          
            InvoiceLineTableModel LinesTableModel = (InvoiceLineTableModel) frame.getInvoiceItemsTable().getModel();
            LinesTableModel.fireTableDataChanged();
                
            }
        
        itemDialog.setVisible(false);
        itemDialog.dispose(); 
        
        }
        
    

    private void createItemCancel() {
        itemDialog.setVisible(false);
        itemDialog.dispose();
    }

   

    
}
