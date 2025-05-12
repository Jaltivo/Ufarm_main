/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ufarm.ufarm;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage; // For placeholder icon

public class Cart extends javax.swing.JFrame {
    
    private ArrayList<Map<String, Object>> cartItems;
    private double subtotal = 0.0;
    private final double DELIVERY_FEE = 5.00;

    private ImageIcon loadImageIcon(String path) {
        try {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + path);
                return new ImageIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ImageIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        }
    }
    
    public Cart() {
        initComponents();
        try {
            ArrayList<Map<String, Object>> itemsFromProduce = com.ufarm.ufarm.Produce.getCartItems();
            this.cartItems = (itemsFromProduce != null) ? itemsFromProduce : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error fetching cart items: " + e.getMessage());
            this.cartItems = new ArrayList<>();
        }
        displayCartItems();
        calculateOrderSummary(); 
        setupNavigation();
    }
    
    private void setupNavigation() {
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Home().setVisible(true);
                dispose();
            }
        });
        
        Produce.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Produce().setVisible(true);
                dispose();
            }
        });
        
        Farm.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Farm().setVisible(true);
                dispose();
            }
        });
        
        Feedback1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Feedback().setVisible(true);
                dispose();
            }
        });
        
        Acc.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Login().setVisible(true);
                dispose();
            }
        });
    }

    private void displayCartItems() {
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        itemsPanel.setBackground(Color.WHITE); 
        
        if (cartItems == null || cartItems.isEmpty()) {
            JPanel emptyPanel = new JPanel(new BorderLayout());
            emptyPanel.setBackground(Color.WHITE);
            JLabel emptyLabel = new JLabel("Your cart is empty");
            emptyLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
            emptyLabel.setForeground(new Color(35, 101, 51));
            emptyLabel.setHorizontalAlignment(JLabel.CENTER);
            emptyPanel.add(emptyLabel, BorderLayout.CENTER);
            emptyPanel.add(Box.createVerticalStrut(50), BorderLayout.NORTH);
            emptyPanel.add(Box.createVerticalStrut(50), BorderLayout.SOUTH);
            itemsPanel.add(emptyPanel);
        } else {
            for (Map<String, Object> item : cartItems) {
                 if (item != null) { 
                    try {
                        String name = (String) item.get("name");
                        Integer quantity = (Integer) item.get("quantity");
                        ImageIcon icon = (ImageIcon) item.get("icon");
                        Double price = (Double) item.get("price");

                        if (name == null || quantity == null || price == null) {
                            System.err.println("Cart item has missing data: " + item);
                            continue;
                        }

                        JPanel itemPanel = createCartItemPanel(name, quantity, icon, price);
                        itemsPanel.add(itemPanel);
                        itemsPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
                    } catch (ClassCastException cce) {
                        System.err.println("Error with item data types in cart: " + item + " - " + cce.getMessage());
                    } catch (Exception e) {
                        System.err.println("Error displaying cart item (name: " + item.get("name") + "): " + e.getMessage());
                    }
                }
            }
        }
        
        jPanel3.removeAll(); 
        jScrollPane1.setViewportView(itemsPanel);
        jScrollPane1.setBorder(null); 
        
        if (!(jPanel3.getLayout() instanceof BorderLayout)) {
             jPanel3.setLayout(new BorderLayout()); 
        }
        jPanel3.add(jScrollPane1, BorderLayout.CENTER); 

        jPanel3.revalidate();
        jPanel3.repaint();
    }
    
    private JPanel createCartItemPanel(String name, int quantity, ImageIcon icon, double price) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(15, 0));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10))
        );
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120)); 
        panel.setPreferredSize(new Dimension(400, 100)); 

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(80, 80));
        if (icon != null && icon.getImage() != null && icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            imageLabel.setIcon(loadImageIcon("/com/ufarm/ufarm/images/default_product.png")); 
        }
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(imageLabel, BorderLayout.WEST);
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        nameLabel.setForeground(new Color(35, 101, 51));
        
        JLabel priceLabelText = new JLabel(String.format("$%.2f each", price));
        priceLabelText.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5))); 
        infoPanel.add(priceLabelText);
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JLabel itemTotalLabel = new JLabel(String.format("$%.2f", price * quantity));
        itemTotalLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        itemTotalLabel.setForeground(new Color(35, 101, 51));
        itemTotalLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(quantity, 1, 100, 1));
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) quantitySpinner.getEditor();
        editor.getTextField().setColumns(3);
        editor.getTextField().setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        quantitySpinner.setMaximumSize(new Dimension(80, 30)); 
        quantitySpinner.addChangeListener(evt -> {
            int newQuantitySpinnerVal = (Integer) quantitySpinner.getValue();
            updateItemQuantity(name, newQuantitySpinnerVal); 
            itemTotalLabel.setText(String.format("$%.2f", price * newQuantitySpinnerVal)); 
        });
        
        JPanel quantitySpinnerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0,0));
        quantitySpinnerPanel.setBackground(Color.WHITE);
        quantitySpinnerPanel.add(new JLabel("Qty: "));
        quantitySpinnerPanel.add(quantitySpinner);
        quantitySpinnerPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JButton removeButton = new javax.swing.JButton("Remove");
        removeButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        removeButton.setBackground(new Color(220, 53, 69)); 
        removeButton.setForeground(Color.WHITE);
        removeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        removeButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        removeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeButton.addActionListener(evt -> {
            if (cartItems != null) {
                 cartItems.removeIf(item -> item != null && name.equals(item.get("name")));
            }
            displayCartItems(); 
            calculateOrderSummary(); 
        });
        
        controlPanel.add(itemTotalLabel);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 5))); 
        controlPanel.add(quantitySpinnerPanel);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 5))); 
        controlPanel.add(removeButton);
        
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.EAST);
        
        return panel;
    }
    
    private void updateItemQuantity(String name, int newQuantity) {
        if (cartItems != null) {
            for (Map<String, Object> item : cartItems) {
                if (item != null && name.equals(item.get("name"))) {
                    item.put("quantity", newQuantity);
                    break; 
                }
            }
        }
        calculateOrderSummary(); 
    }
    
    private void calculateOrderSummary() {
        subtotal = 0.0;
        if (cartItems != null) {
            for (Map<String, Object> item : cartItems) {
                if (item != null) { 
                    try {
                        Object priceObj = item.get("price");
                        Object quantityObj = item.get("quantity");

                        if (priceObj instanceof Number && quantityObj instanceof Number) {
                            double price = ((Number) priceObj).doubleValue();
                            int quantity = ((Number) quantityObj).intValue();
                            if (price < 0 || quantity < 0) {
                                System.err.println("Item '" + item.get("name") + "' has negative price or quantity.");
                                continue;
                            }
                            subtotal += price * quantity;
                        } else {
                             System.err.println("Item '" + item.get("name") + "' has invalid price or quantity type or is null.");
                        }
                    } catch (Exception e) { 
                        System.err.println("Error calculating subtotal for item: " + item.get("name") + " - " + e.getMessage());
                    }
                }
            }
        }
        
        double total = subtotal + DELIVERY_FEE;
        
        jLabel5.setText("Subtotal: " + String.format("$%.2f", subtotal));
        jLabel6.setText("Delivery Fee: " + String.format("$%.2f", DELIVERY_FEE));
        jLabel7.setText("Total: " + String.format("$%.2f", total));
        
        jButton1.setText("Proceed to Order"); // Ensure text is correct
        jButton1.setBackground(new Color(35, 101, 51));
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new Font("Helvetica Neue", Font.BOLD, 16)); // Consistent font
        jButton1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Consistent padding
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dash = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        HomePanel = new javax.swing.JPanel();
        Home = new javax.swing.JLabel();
        AccPanel = new javax.swing.JPanel();
        Acc = new javax.swing.JLabel();
        ProducePanel = new javax.swing.JPanel();
        Produce = new javax.swing.JLabel();
        FarmPanel = new javax.swing.JPanel();
        Farm = new javax.swing.JLabel();
        FeedbackPanel = new javax.swing.JPanel();
        Feedback = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        FeedbackPanel1 = new javax.swing.JPanel();
        Feedback1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UFarm Shopping Cart");

        Dash.setBackground(new java.awt.Color(35, 101, 51));
        Dash.setPreferredSize(new java.awt.Dimension(214, 460));

        Title.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("UFarm");

        HomePanel.setBackground(new java.awt.Color(33, 113, 0));
        HomePanel.setPreferredSize(new java.awt.Dimension(170, 41));

        Home.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        Home.setForeground(new java.awt.Color(255, 255, 255));
        Home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Home.setText("Home");
        Home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout HomePanelLayout = new javax.swing.GroupLayout(HomePanel);
        HomePanel.setLayout(HomePanelLayout);
        HomePanelLayout.setHorizontalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        HomePanelLayout.setVerticalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        AccPanel.setBackground(new java.awt.Color(33, 113, 0));
        AccPanel.setPreferredSize(new java.awt.Dimension(170, 41));

        Acc.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        Acc.setForeground(new java.awt.Color(255, 255, 255));
        Acc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Acc.setText("Account");
        Acc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout AccPanelLayout = new javax.swing.GroupLayout(AccPanel);
        AccPanel.setLayout(AccPanelLayout);
        AccPanelLayout.setHorizontalGroup(
            AccPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Acc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AccPanelLayout.setVerticalGroup(
            AccPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Acc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        ProducePanel.setBackground(new java.awt.Color(33, 113, 0));
        ProducePanel.setPreferredSize(new java.awt.Dimension(170, 41));

        Produce.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        Produce.setForeground(new java.awt.Color(255, 255, 255));
        Produce.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Produce.setText("Produce");
        Produce.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout ProducePanelLayout = new javax.swing.GroupLayout(ProducePanel);
        ProducePanel.setLayout(ProducePanelLayout);
        ProducePanelLayout.setHorizontalGroup(
            ProducePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Produce, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ProducePanelLayout.setVerticalGroup(
            ProducePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Produce, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        FarmPanel.setBackground(new java.awt.Color(33, 113, 0));
        FarmPanel.setPreferredSize(new java.awt.Dimension(170, 41));

        Farm.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        Farm.setForeground(new java.awt.Color(255, 255, 255));
        Farm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Farm.setText("Farm");
        Farm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout FarmPanelLayout = new javax.swing.GroupLayout(FarmPanel);
        FarmPanel.setLayout(FarmPanelLayout);
        FarmPanelLayout.setHorizontalGroup(
            FarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Farm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FarmPanelLayout.setVerticalGroup(
            FarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Farm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        FeedbackPanel.setBackground(new java.awt.Color(102, 51, 0)); 
        FeedbackPanel.setPreferredSize(new java.awt.Dimension(170, 41));

        Feedback.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); 
        Feedback.setForeground(new java.awt.Color(255, 255, 255));
        Feedback.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Feedback.setText("Cart");
        Feedback.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout FeedbackPanelLayout = new javax.swing.GroupLayout(FeedbackPanel);
        FeedbackPanel.setLayout(FeedbackPanelLayout);
        FeedbackPanelLayout.setHorizontalGroup(
            FeedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Feedback, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FeedbackPanelLayout.setVerticalGroup(
            FeedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Feedback, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); 
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agco, Jasmine Jane @2025");

        FeedbackPanel1.setBackground(new java.awt.Color(33, 113, 0));
        FeedbackPanel1.setPreferredSize(new java.awt.Dimension(170, 41));

        Feedback1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); 
        Feedback1.setForeground(new java.awt.Color(255, 255, 255));
        Feedback1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Feedback1.setText("Feedback");
        Feedback1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout FeedbackPanel1Layout = new javax.swing.GroupLayout(FeedbackPanel1);
        FeedbackPanel1.setLayout(FeedbackPanel1Layout);
        FeedbackPanel1Layout.setHorizontalGroup(
            FeedbackPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Feedback1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FeedbackPanel1Layout.setVerticalGroup(
            FeedbackPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Feedback1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DashLayout = new javax.swing.GroupLayout(Dash);
        Dash.setLayout(DashLayout);
        DashLayout.setHorizontalGroup(
            DashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(DashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(AccPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(ProducePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(FarmPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(FeedbackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(FeedbackPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        DashLayout.setVerticalGroup(
            DashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(HomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AccPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProducePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FarmPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FeedbackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FeedbackPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 60));

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 28)); 
        jLabel2.setForeground(new java.awt.Color(35, 101, 51));
        jLabel2.setText("My Cart");

        jTextField1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        try {
             jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ufarm/ufarm/images/search.png")));
        } catch (Exception e) {
             jLabel3.setText("S"); 
             System.err.println("Search icon not found: /com/ufarm/ufarm/images/search.png. Ensure it's in the classpath.");
        }
        jLabel3.setPreferredSize(new java.awt.Dimension(28, 28));
        jLabel3.setMaximumSize(new java.awt.Dimension(28, 28));
        jLabel3.setMinimumSize(new java.awt.Dimension(28, 28));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 349, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jCheckBox1.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); 
        jCheckBox1.setText(" Select all items");
        jCheckBox1.setOpaque(false); 
        jCheckBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(245, 245, 245)); 
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224)));
        jPanel2.setPreferredSize(new java.awt.Dimension(250, 400)); // Explicit preferred size for order summary

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", Font.BOLD, 20)); 
        jLabel4.setForeground(new java.awt.Color(51,51,51)); 
        jLabel4.setText("Order Summary");

        jSeparator2.setForeground(new java.awt.Color(224, 224, 224));

        jSeparator3.setForeground(new java.awt.Color(224, 224, 224));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", Font.PLAIN, 16)); 
        jLabel5.setForeground(new java.awt.Color(85,85,85)); 
        jLabel5.setText("Subtotal: $0.00"); // Initial text

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", Font.PLAIN, 16));
        jLabel6.setForeground(new java.awt.Color(85,85,85));
        jLabel6.setText("Delivery Fee: $0.00"); // Initial text, DELIVERY_FEE will update actual value

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", Font.BOLD, 20)); 
        jLabel7.setForeground(new java.awt.Color(35,101,51));
        jLabel7.setText("Total: $0.00"); // Initial text

        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); 
        jButton1.setText("Proceed to Order"); // Updated text
        // Colors and cursor set in calculateOrderSummary or later as needed
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE) // Adjusted flexible gap
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(25, 25, 25)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224)));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Dash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE) 
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Dash, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)) // Use PREFERRED_SIZE for jPanel2
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        String searchText = jTextField1.getText();
        System.out.println("Search text: " + searchText);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (cartItems == null || cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty. Please add items to proceed.", "Cart Empty", JOptionPane.WARNING_MESSAGE);
        } else {
            calculateOrderSummary(); 
            double finalTotal = subtotal + DELIVERY_FEE;

            String confirmationMessage = String.format(
                "Please confirm your order:\n\n" +
                "Subtotal:         $%.2f\n" +
                "Delivery Fee:  $%.2f\n" +
                "----------------------------------\n" +
                "Total:              $%.2f\n\n" +
                "Proceed with this order?",
                subtotal, DELIVERY_FEE, finalTotal
            );

            int response = JOptionPane.showConfirmDialog(
                this, 
                confirmationMessage, 
                "Order Confirmation", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("Order placed for total: $" + String.format("%.2f", finalTotal));
                if (cartItems != null) { 
                    for(Map<String, Object> item : cartItems) {
                        if (item != null) {
                            System.out.println("- " + item.get("name") + " x" + item.get("quantity") + " @ $" + item.get("price"));
                        }
                    }
                }

                JOptionPane.showMessageDialog(this, "Order placed successfully! Thank you for your purchase.", "Order Success", JOptionPane.INFORMATION_MESSAGE);
                
                if (cartItems != null) {
                    cartItems.clear(); 
                }
                
                displayCartItems(); 
                calculateOrderSummary(); 
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        System.out.println("Select all items checkbox clicked. Feature currently has no specific action.");
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.INFO, "Nimbus L&F not found, using default.", ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Acc;
    private javax.swing.JPanel AccPanel;
    private javax.swing.JPanel Dash;
    private javax.swing.JLabel Farm;
    private javax.swing.JPanel FarmPanel;
    private javax.swing.JLabel Feedback;
    private javax.swing.JLabel Feedback1;
    private javax.swing.JPanel FeedbackPanel;
    private javax.swing.JPanel FeedbackPanel1;
    private javax.swing.JLabel Home;
    private javax.swing.JPanel HomePanel;
    private javax.swing.JLabel Produce;
    private javax.swing.JPanel ProducePanel;
    private javax.swing.JLabel Title;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}