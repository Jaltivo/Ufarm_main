/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ufarm.ufarm;

/**
 *
 * @author Admin
 */

import java.awt.Component;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class CartFrame extends javax.swing.JFrame {
    
    private ArrayList<Map<String, Object>> cartItems;
    private double totalPrice = 0.0;

    public CartFrame(ArrayList<Map<String, Object>> cartItems) {
        this.cartItems = cartItems;
        initComponents();
        displayCartItems();
        calculateTotal();
    }

    private void displayCartItems() {
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new java.awt.GridLayout(0, 1, 5, 5));
        
        for (Map<String, Object> item : cartItems) {
            JPanel itemPanel = createCartItemPanel(
                (String) item.get("name"),
                (Integer) item.get("quantity"),
                (ImageIcon) item.get("icon"),
                (Double) item.get("price")
            );
            itemsPanel.add(itemPanel);
        }
        
        jScrollPane1.setViewportView(itemsPanel);
    }
    
    private JPanel createCartItemPanel(String name, int quantity, ImageIcon icon, double price) {
        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        
        // Item image
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(icon);
        panel.add(imageLabel);
        
        // Item name
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        panel.add(nameLabel);
        
        // Quantity spinner
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(quantity, 1, 100, 1));
        panel.add(quantitySpinner);
        
        // Price
        JLabel priceLabel = new JLabel(String.format("$%.2f", price * quantity));
        panel.add(priceLabel);
        
        // Remove button
        javax.swing.JButton removeButton = new javax.swing.JButton("Remove");
        removeButton.addActionListener(evt -> {
            cartItems.removeIf(item -> item.get("name").equals(name));
            displayCartItems();
            calculateTotal();
        });
        panel.add(removeButton);
        
        return panel;
    }
    
    private void calculateTotal() {
        totalPrice = 0.0;
        for (Map<String, Object> item : cartItems) {
            totalPrice += (Double)item.get("price") * (Integer)item.get("quantity");
        }
        totalLabel.setText(String.format("Total: $%.2f", totalPrice));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        totalLabel = new javax.swing.JLabel();
        checkoutButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Your Shopping Cart");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cart Items"));

        totalLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        totalLabel.setText("Total: $0.00");

        checkoutButton.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        checkoutButton.setText("Checkout");
        checkoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutButtonActionPerformed(evt);
            }
        });

        closeButton.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(totalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkoutButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalLabel)
                    .addComponent(checkoutButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void checkoutButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!", "Checkout", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Thank you for your purchase!\nTotal: $" + String.format("%.2f", totalPrice), "Order Confirmed", JOptionPane.INFORMATION_MESSAGE);
            cartItems.clear();
            this.dispose();
        }
    }                                             

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.dispose();
    }                                          

    // Variables declaration - do not modify                     
    private javax.swing.JButton checkoutButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration                   
}
