/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ufarm.ufarm;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener; // Import the ItemListener
import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage; // For placeholder icon

public class Cart extends javax.swing.JFrame {
    
    private ArrayList<Map<String, Object>> cartItems;
    private double subtotal = 0.0;
    private final double DELIVERY_FEE = 5.00;
    // To keep track of item checkboxes for select all functionality
    private ArrayList<JCheckBox> itemCheckBoxes = new ArrayList<>();

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
            this.cartItems = (itemsFromProduce != null) ? new ArrayList<>(itemsFromProduce) : new ArrayList<>();
            // Ensure all items have a 'selected' field, defaulting to true
            if (this.cartItems != null) {
                for (Map<String, Object> item : this.cartItems) {
                    if (item != null && !item.containsKey("selected")) {
                        item.put("selected", true); // Default to selected
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching cart items: " + e.getMessage());
            this.cartItems = new ArrayList<>();
        }
        displayCartItems(); // This will now also create itemCheckBoxes
        calculateOrderSummary(); 
        updateSelectAllCheckboxState(); // Set initial state of jCheckBox1
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
        
        itemCheckBoxes.clear(); // Clear the list before repopulating

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
                        boolean selected = (Boolean) item.getOrDefault("selected", true); // Default to true if missing

                        if (name == null || quantity == null || price == null) {
                            System.err.println("Cart item has missing data: " + item);
                            continue;
                        }
                        // Pass the item map itself to createCartItemPanel
                        JPanel itemPanel = createCartItemPanel(item);
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
    
    private JPanel createCartItemPanel(Map<String, Object> item) {
        String name = (String) item.get("name");
        int quantity = (Integer) item.get("quantity");
        ImageIcon icon = (ImageIcon) item.get("icon");
        double price = (Double) item.get("price");
        boolean isSelected = (Boolean) item.getOrDefault("selected", true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 0)); // Reduced gap for checkbox
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10))
        );
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120)); 
        panel.setPreferredSize(new Dimension(450, 100)); // Slightly wider to accommodate checkbox

        // Checkbox for item selection
        JCheckBox itemCheckBox = new JCheckBox();
        itemCheckBox.setSelected(isSelected);
        itemCheckBox.setBackground(Color.WHITE);
        itemCheckBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemCheckBox.addItemListener(e -> {
            item.put("selected", itemCheckBox.isSelected());
            calculateOrderSummary();
            updateSelectAllCheckboxState();
        });
        itemCheckBoxes.add(itemCheckBox); // Add to list for "Select All"
        panel.add(itemCheckBox, BorderLayout.WEST); // Add checkbox to the left

        // Center Panel for Image and Info
        JPanel centerContentPanel = new JPanel(new BorderLayout(10,0));
        centerContentPanel.setBackground(Color.WHITE);

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(80, 80));
        if (icon != null && icon.getImage() != null && icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            imageLabel.setIcon(loadImageIcon("/com/ufarm/ufarm/images/default_product.png")); 
        }
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        centerContentPanel.add(imageLabel, BorderLayout.WEST);
        
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
        centerContentPanel.add(infoPanel, BorderLayout.CENTER);

        panel.add(centerContentPanel, BorderLayout.CENTER); // Add combined image and info to center

        // Right Panel for Quantity, Total, Remove Button
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
            item.put("quantity", newQuantitySpinnerVal); // Update quantity in the map
            itemTotalLabel.setText(String.format("$%.2f", price * newQuantitySpinnerVal)); 
            if((Boolean)item.get("selected")) { // Only recalculate if item is selected
                 calculateOrderSummary();
            }
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
                 // Also remove its checkbox from our tracking list
                int itemIndex = cartItems.indexOf(item);
                if(itemIndex != -1 && itemIndex < itemCheckBoxes.size()){
                    itemCheckBoxes.remove(itemIndex);
                }
                cartItems.remove(item);
            }
            displayCartItems(); 
            calculateOrderSummary(); 
            updateSelectAllCheckboxState();
        });
        
        controlPanel.add(itemTotalLabel);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 5))); 
        controlPanel.add(quantitySpinnerPanel);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 5))); 
        controlPanel.add(removeButton);
        
        panel.add(controlPanel, BorderLayout.EAST);
        
        return panel;
    }
    
    private void updateItemQuantity(String name, int newQuantity) {
        if (cartItems != null) {
            for (Map<String, Object> item : cartItems) {
                if (item != null && name.equals(item.get("name"))) {
                    item.put("quantity", newQuantity);
                     if((Boolean)item.get("selected")) { // Only recalculate if item is selected
                        calculateOrderSummary();
                    }
                    break; 
                }
            }
        }
    }
    
    private void calculateOrderSummary() {
        subtotal = 0.0;
        if (cartItems != null) {
            for (Map<String, Object> item : cartItems) {
                if (item != null && (Boolean) item.getOrDefault("selected", false)) { // Only add if selected
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
        
        double total = subtotal + (subtotal > 0 ? DELIVERY_FEE : 0); // Only add delivery fee if there's a subtotal
        
        jLabel5.setText("Subtotal: " + String.format("$%.2f", subtotal));
        jLabel6.setText("Delivery Fee: " + String.format("$%.2f", (subtotal > 0 ? DELIVERY_FEE : 0)));
        jLabel7.setText("Total: " + String.format("$%.2f", total));
        
        jButton1.setBackground(new Color(35, 101, 51));
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new Font("Helvetica Neue", Font.BOLD, 16)); 
        jButton1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 
        jButton1.setEnabled(subtotal > 0); // Enable button only if there are selected items
    }

    private void updateSelectAllCheckboxState() {
        if (cartItems == null || cartItems.isEmpty()) {
            jCheckBox1.setSelected(false);
            jCheckBox1.setEnabled(false); // Disable if no items
            return;
        }
        jCheckBox1.setEnabled(true); // Enable if items exist

        boolean allSelected = true;
        for (Map<String, Object> item : cartItems) {
            if (item != null && !(Boolean) item.getOrDefault("selected", false)) {
                allSelected = false;
                break;
            }
        }
        // Temporarily remove item listener to prevent feedback loop
        ItemListener[] listeners = jCheckBox1.getItemListeners();
        for(ItemListener listener : listeners) {
            jCheckBox1.removeItemListener(listener);
        }
        jCheckBox1.setSelected(allSelected);
        // Re-add item listener
        for(ItemListener listener : listeners) {
            jCheckBox1.addItemListener(listener);
        }
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

        Dash.setBackground(new java.awt.Color(35, 101, 51));

        Title.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setText("UFarm");

        HomePanel.setBackground(new java.awt.Color(33, 113, 0));

        Home.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        Home.setForeground(new java.awt.Color(255, 255, 255));
        Home.setText("Home");

        javax.swing.GroupLayout HomePanelLayout = new javax.swing.GroupLayout(HomePanel);
        HomePanel.setLayout(HomePanelLayout);
        HomePanelLayout.setHorizontalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(Home)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        HomePanelLayout.setVerticalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Home)
                .addGap(157, 157, 157))
        );

        AccPanel.setBackground(new java.awt.Color(33, 113, 0));

        Acc.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        Acc.setForeground(new java.awt.Color(255, 255, 255));
        Acc.setText("Account");

        javax.swing.GroupLayout AccPanelLayout = new javax.swing.GroupLayout(AccPanel);
        AccPanel.setLayout(AccPanelLayout);
        AccPanelLayout.setHorizontalGroup(
            AccPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(Acc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AccPanelLayout.setVerticalGroup(
            AccPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AccPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Acc)
                .addGap(157, 157, 157))
        );

        ProducePanel.setBackground(new java.awt.Color(33, 113, 0));

        Produce.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        Produce.setForeground(new java.awt.Color(255, 255, 255));
        Produce.setText("Produce");

        javax.swing.GroupLayout ProducePanelLayout = new javax.swing.GroupLayout(ProducePanel);
        ProducePanel.setLayout(ProducePanelLayout);
        ProducePanelLayout.setHorizontalGroup(
            ProducePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProducePanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(Produce)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ProducePanelLayout.setVerticalGroup(
            ProducePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProducePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Produce)
                .addGap(157, 157, 157))
        );

        FarmPanel.setBackground(new java.awt.Color(33, 113, 0));

        Farm.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        Farm.setForeground(new java.awt.Color(255, 255, 255));
        Farm.setText("Farm");

        javax.swing.GroupLayout FarmPanelLayout = new javax.swing.GroupLayout(FarmPanel);
        FarmPanel.setLayout(FarmPanelLayout);
        FarmPanelLayout.setHorizontalGroup(
            FarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FarmPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(Farm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FarmPanelLayout.setVerticalGroup(
            FarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FarmPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Farm)
                .addGap(157, 157, 157))
        );

        FeedbackPanel.setBackground(new java.awt.Color(102, 51, 0));

        Feedback.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        Feedback.setForeground(new java.awt.Color(255, 255, 255));
        Feedback.setText("Cart");

        javax.swing.GroupLayout FeedbackPanelLayout = new javax.swing.GroupLayout(FeedbackPanel);
        FeedbackPanel.setLayout(FeedbackPanelLayout);
        FeedbackPanelLayout.setHorizontalGroup(
            FeedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FeedbackPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(Feedback)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FeedbackPanelLayout.setVerticalGroup(
            FeedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FeedbackPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Feedback)
                .addGap(157, 157, 157))
        );

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Agco, Jasmine Jane @2025");

        FeedbackPanel1.setBackground(new java.awt.Color(33, 113, 0));

        Feedback1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        Feedback1.setForeground(new java.awt.Color(255, 255, 255));
        Feedback1.setText("Feedback");

        javax.swing.GroupLayout FeedbackPanel1Layout = new javax.swing.GroupLayout(FeedbackPanel1);
        FeedbackPanel1.setLayout(FeedbackPanel1Layout);
        FeedbackPanel1Layout.setHorizontalGroup(
            FeedbackPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FeedbackPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Feedback1)
                .addGap(53, 53, 53))
        );
        FeedbackPanel1Layout.setVerticalGroup(
            FeedbackPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FeedbackPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Feedback1)
                .addGap(157, 157, 157))
        );

        javax.swing.GroupLayout DashLayout = new javax.swing.GroupLayout(Dash);
        Dash.setLayout(DashLayout);
        DashLayout.setHorizontalGroup(
            DashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(AccPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ProducePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(FarmPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(FeedbackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DashLayout.createSequentialGroup()
                .addGroup(DashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DashLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(Title)))
                .addGap(0, 27, Short.MAX_VALUE))
            .addGroup(DashLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(FeedbackPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DashLayout.setVerticalGroup(
            DashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(HomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AccPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProducePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FarmPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FeedbackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FeedbackPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(35, 101, 51));
        jLabel2.setText("My Cart");

        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 101, 51), 2, true));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Ufarm_main\\UFarm\\src\\main\\java\\com\\ufarm\\ufarm\\images\\search.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(12, 12, 12))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jCheckBox1.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jCheckBox1.setText(" Select all items");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        jLabel4.setText("Order Summary");

        jSeparator2.setForeground(new java.awt.Color(35, 101, 51));

        jSeparator3.setForeground(new java.awt.Color(35, 101, 51));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel5.setText("Subtotal");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel6.setText("Delivery Fee");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(35, 101, 51));
        jLabel7.setText("TOTAL");

        jButton1.setBackground(new java.awt.Color(35, 101, 51));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Place Order");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(255, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Dash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(Dash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        String searchText = jTextField1.getText();
        System.out.println("Search text: " + searchText);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (subtotal <= 0) { // Check against calculated subtotal for selected items
            JOptionPane.showMessageDialog(this, "No items selected or cart is empty. Please select items to proceed.", "No Items Selected", JOptionPane.WARNING_MESSAGE);
        } else {
            // calculateOrderSummary(); // Already called by item selections, but can be a safeguard
            double finalTotal = subtotal + DELIVERY_FEE; // DELIVERY_FEE is now conditional in calculateOrderSummary

            String confirmationMessage = String.format(
                "Please confirm your order:\n\n" +
                "Subtotal:         $%.2f\n" +
                "Delivery Fee:  $%.2f\n" +
                "----------------------------------\n" +
                "Total:              $%.2f\n\n" +
                "Proceed with this order?",
                subtotal, (subtotal > 0 ? DELIVERY_FEE : 0), finalTotal
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
                // Remove only selected items from cart if desired, or all items. Current behavior is to clear all.
                if (cartItems != null) { 
                    // Example: Log selected items
                    for(Map<String, Object> item : cartItems) {
                        if (item != null && (Boolean)item.getOrDefault("selected", false)) {
                            System.out.println("- " + item.get("name") + " x" + item.get("quantity") + " @ $" + item.get("price"));
                        }
                    }
                     // Optionally, remove only the 'ordered' (selected) items
                    // cartItems.removeIf(item -> (Boolean) item.getOrDefault("selected", false));
                    // For now, let's stick to clearing all items as if it's a full checkout
                    cartItems.clear();
                    itemCheckBoxes.clear();
                }
                
                JOptionPane.showMessageDialog(this, "Order placed successfully! Thank you for your purchase.", "Order Success", JOptionPane.INFORMATION_MESSAGE);
                
                displayCartItems(); 
                calculateOrderSummary(); 
                updateSelectAllCheckboxState();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        boolean selectAll = jCheckBox1.isSelected();
        if (cartItems != null) {
            for (Map<String, Object> item : cartItems) {
                if (item != null) {
                    item.put("selected", selectAll);
                }
            }
            // Update all individual checkboxes
            for (JCheckBox checkBox : itemCheckBoxes) {
                checkBox.setSelected(selectAll);
            }
        }
        calculateOrderSummary();
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

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