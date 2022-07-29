/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sales.invoice.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Ahmed Fawzy
 */
public class CreateNewInvoiceDialog extends JDialog{
    private JTextField customerNameField;
    private JTextField invoiceDateField;
    private JLabel customerNameLabel;
    private JLabel invoiceDateLabel;
    private JButton okButton;
    private JButton cancelButton;

    public CreateNewInvoiceDialog (SalesInvoiceFrame Frame) {
        customerNameLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField(20);
        invoiceDateLabel = new JLabel("Invoice Date:");
        invoiceDateField = new JTextField(20);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("Create New Invoice OK");
        cancelButton.setActionCommand("Create New Invoice Cancel");
        
        okButton.addActionListener(Frame.getListener());
        cancelButton.addActionListener(Frame.getListener());
        setLayout(new GridLayout(3, 2));
        
        add(invoiceDateLabel);
        add(invoiceDateField);
        add(customerNameLabel);
        add(customerNameField);
        add(okButton);
        add(cancelButton);
        setModal(true);
        pack();
        
    }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getInviceDateField() {
        return invoiceDateField;
    }
    
    
}
