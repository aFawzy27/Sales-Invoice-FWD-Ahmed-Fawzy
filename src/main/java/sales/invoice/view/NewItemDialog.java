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
public class NewItemDialog extends JDialog{
    private JTextField itemNameField;
    private JTextField countField;
    private JTextField itemPriceField;
    private JLabel itemNameLabel;
    private JLabel countLabel;
    private JLabel itemPriceLabel;
    private JButton okButton;
    private JButton cancelButton;
    
    public NewItemDialog(SalesInvoiceFrame Frame) {
        itemNameField = new JTextField(20);
        itemNameLabel = new JLabel("Item Name");
        countField = new JTextField(20);
        countLabel = new JLabel("Count");
        itemPriceField = new JTextField(20);
        itemPriceLabel = new JLabel("Item Price");
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("Create Item OK");
        cancelButton.setActionCommand("Create Item Cancel");
        
        okButton.addActionListener(Frame.getListener());
        cancelButton.addActionListener(Frame.getListener());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLabel);
        add(itemNameField);
        add(countLabel);
        add(countField);
        add(itemPriceLabel);
        add(itemPriceField);
        add(okButton);
        add(cancelButton);
        setModal(true);
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemCountField() {
        return countField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }
    
}
