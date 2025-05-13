/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ufarm.ufarm;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter; // Added import
import java.awt.event.MouseEvent;   // Added import
import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage; // For placeholder icon
import java.util.Iterator; // Added for safe removal

public class Cart extends javax.swing.JFrame {

    private ArrayList<Map<String, Object>> cartItems;
    private double subtotal = 0.0;
    private final double DELIVERY_FEE = 5.00;
    private ArrayList<JCheckBox> itemCheckBoxes = new ArrayList<>();

    // Labels for dynamic values in Order Summary
    private javax.swing.JLabel subtotalValueLabel;
    private javax.swing.JLabel deliveryFeeValueLabel;
    private javax.swing.JLabel totalValueLabel;

    // Panel and ScrollPane for itemized list in Order Summary
    private javax.swing.JPanel selectedItemsDisplayPanel;
    private javax.swing.JScrollPane itemsSummaryScrollPane;


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
            if (this.cartItems != null) {
                for (Map<String, Object> item : this.cartItems) {
                    if (item != null && !item.containsKey("selected")) {
                        item.put("selected", true);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching cart items: " + e.getMessage());
            this.cartItems = new ArrayList<>();
        }
        displayCartItems();
        calculateOrderSummary();
        updateSelectAllCheckboxState();
        setupNavigation();
        setLocationRelativeTo(null);
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
        itemCheckBoxes.clear();

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
                        Double price = (Double) item.get("price");
                        if (name == null || quantity == null || price == null) {
                            System.err.println("Cart item has missing data: " + item);
                            continue;
                        }
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
        jScrollPane1.setViewportView(itemsPanel);
        jScrollPane1.setBorder(null);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        if (!(jPanel3.getLayout() instanceof BorderLayout)) {
             jPanel3.setLayout(new BorderLayout());
        }
        jPanel3.removeAll();
        jPanel3.add(jScrollPane1, BorderLayout.CENTER);
        jPanel3.revalidate();
        jPanel3.repaint();
    }

    // --- MODIFIED createCartItemPanel for better width management ---
    private JPanel createCartItemPanel(Map<String, Object> item) {
        String itemNameValue = (String) item.get("name");
        int quantity = (Integer) item.get("quantity");
        ImageIcon icon = (ImageIcon) item.get("icon");
        double price = (Double) item.get("price");
        boolean isSelected = (Boolean) item.getOrDefault("selected", true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 0)); // hgap between components
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 5, 10, 5)) // Reduced horizontal padding slightly
        );
        panel.setBackground(Color.WHITE);
        // Preferred width should be less than the container's available width
        // jPanel3 (container for jScrollPane1) is ~448px wide.
        // jScrollPane1 has itemsPanel with 10px padding each side. Usable for itemsPanel: ~428px.
        // If vertical scrollbar for itemsPanel is visible, it takes ~15-20px. Effective: ~408px.
        panel.setPreferredSize(new Dimension(400, 100)); // Item panel target width
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));


        JCheckBox itemCheckBox = new JCheckBox();
        itemCheckBox.setSelected(isSelected);
        itemCheckBox.setBackground(Color.WHITE);
        itemCheckBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemCheckBox.addItemListener(e -> {
            item.put("selected", itemCheckBox.isSelected());
            calculateOrderSummary();
            updateSelectAllCheckboxState();
        });
        itemCheckBoxes.add(itemCheckBox);
        panel.add(itemCheckBox, BorderLayout.WEST);

        JPanel centerContentPanel = new JPanel(new BorderLayout(8,0)); // hgap for image and info
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

        // Use HTML to allow name label to wrap and constrain its width
        // Estimated available width for name: panelWidth(400) - checkbox(~30) - image(80) - controlPanel(115) - gaps(~15) = ~160
        int nameLabelWidth = 150; // Approximate width for the name label before it wraps
        JLabel nameLabel = new JLabel("<html><body style='width: " + nameLabelWidth + "px'>" + itemNameValue + "</body></html>");
        nameLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        nameLabel.setForeground(new Color(35, 101, 51));
        // nameLabel.setMinimumSize(new Dimension(nameLabelWidth -20, 10)); // Optional: prevent extreme shrinking

        JLabel priceLabelText = new JLabel(String.format("$%.2f each", price));
        priceLabelText.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(priceLabelText);
        centerContentPanel.add(infoPanel, BorderLayout.CENTER);
        panel.add(centerContentPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        // Give controlPanel a fixed preferred width to ensure its contents fit
        Dimension controlPanelPrefSize = new Dimension(115, 80); // Adjusted width for controls
        controlPanel.setPreferredSize(controlPanelPrefSize);
        controlPanel.setMinimumSize(controlPanelPrefSize); // Prevent shrinking below preferred
        controlPanel.setMaximumSize(new Dimension(controlPanelPrefSize.width + 10, Integer.MAX_VALUE) );


        JLabel itemTotalLabel = new JLabel(String.format("$%.2f", price * quantity));
        itemTotalLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        itemTotalLabel.setForeground(new Color(35, 101, 51));
        itemTotalLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(quantity, 1, 100, 1));
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) quantitySpinner.getEditor();
        editor.getTextField().setColumns(2);
        editor.getTextField().setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        quantitySpinner.setMaximumSize(new Dimension(70, 30));
        quantitySpinner.addChangeListener(evt -> {
            int newQuantitySpinnerVal = (Integer) quantitySpinner.getValue();
            item.put("quantity", newQuantitySpinnerVal);
            itemTotalLabel.setText(String.format("$%.2f", price * newQuantitySpinnerVal));
            if((Boolean)item.getOrDefault("selected", false)) {
                 calculateOrderSummary();
            }
        });
        JPanel quantitySpinnerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0,0)); // Tight layout
        quantitySpinnerPanel.setBackground(Color.WHITE);
        quantitySpinnerPanel.add(new JLabel("Qty:"));
        quantitySpinnerPanel.add(quantitySpinner);
        quantitySpinnerPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JButton removeButton = new javax.swing.JButton("Remove");
        removeButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        removeButton.setBackground(new Color(220, 53, 69));
        removeButton.setForeground(Color.WHITE);
        removeButton.setMargin(new Insets(5, 8, 5, 8)); // Control padding
        removeButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        removeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeButton.addActionListener(evt -> {
            if (cartItems != null) {
                int itemIndex = cartItems.indexOf(item);
                cartItems.remove(item);
                if(itemIndex != -1 && itemIndex < itemCheckBoxes.size()){
                    itemCheckBoxes.remove(itemIndex);
                }
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

    private void calculateOrderSummary() {
        subtotal = 0.0;
        int selectedItemCount = 0;
        if (selectedItemsDisplayPanel == null) {
            selectedItemsDisplayPanel = new JPanel();
            selectedItemsDisplayPanel.setLayout(new BoxLayout(selectedItemsDisplayPanel, BoxLayout.Y_AXIS));
            selectedItemsDisplayPanel.setBackground(Color.WHITE);
        }
        selectedItemsDisplayPanel.removeAll();

        if (cartItems != null) {
            for (Map<String, Object> item : cartItems) {
                if (item != null && (Boolean) item.getOrDefault("selected", false)) {
                    try {
                        Object priceObj = item.get("price");
                        Object quantityObj = item.get("quantity");
                        String name = (String) item.get("name");

                        if (priceObj instanceof Number && quantityObj instanceof Number && name != null) {
                            double price = ((Number) priceObj).doubleValue();
                            int quantity = ((Number) quantityObj).intValue();
                            if (price < 0 || quantity < 0) continue;
                            double itemLineTotal = price * quantity;
                            subtotal += itemLineTotal;
                            selectedItemCount++;

                            JPanel itemSummaryLine = new JPanel(new BorderLayout(5,0));
                            itemSummaryLine.setOpaque(false);
                            itemSummaryLine.setBackground(selectedItemsDisplayPanel.getBackground());
                            JLabel itemNameQtyLabel = new JLabel(String.format("%s (x%d)", name, quantity));
                            itemNameQtyLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                            JLabel itemPriceLabel = new JLabel(String.format("$%.2f", itemLineTotal));
                            itemPriceLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                            itemPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                            itemSummaryLine.add(itemNameQtyLabel, BorderLayout.WEST);
                            itemSummaryLine.add(itemPriceLabel, BorderLayout.EAST);
                            itemSummaryLine.setMaximumSize(new Dimension(Integer.MAX_VALUE, itemSummaryLine.getPreferredSize().height + 2));
                            selectedItemsDisplayPanel.add(itemSummaryLine);
                            selectedItemsDisplayPanel.add(Box.createRigidArea(new Dimension(0,2)));
                        } else {
                             System.err.println("Item '" + name + "' has invalid data for summary.");
                        }
                    } catch (Exception e) {
                        System.err.println("Error processing item for summary: " + item.get("name") + " - " + e.getMessage());
                    }
                }
            }
        }

        if (selectedItemCount > 0) {
            selectedItemsDisplayPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            JSeparator itemSummarySeparator = new JSeparator();
            itemSummarySeparator.setForeground(new Color(220,220,220));
            itemSummarySeparator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
            selectedItemsDisplayPanel.add(itemSummarySeparator);
        }

        double currentDeliveryFee = (selectedItemCount > 0) ? DELIVERY_FEE : 0.0;
        double total = subtotal + currentDeliveryFee;

        if (subtotalValueLabel == null) subtotalValueLabel = new JLabel(); // Should be init by initComponents
        if (deliveryFeeValueLabel == null) deliveryFeeValueLabel = new JLabel();
        if (totalValueLabel == null) totalValueLabel = new JLabel();

        subtotalValueLabel.setText(String.format("$%.2f", subtotal));
        deliveryFeeValueLabel.setText(String.format("$%.2f", currentDeliveryFee));
        totalValueLabel.setText(String.format("$%.2f", total));

        jButton1.setEnabled(selectedItemCount > 0);
        if (jButton1.isEnabled()) {
             jButton1.setBackground(new Color(35, 101, 51));
             jButton1.setForeground(Color.WHITE);
        } else {
             jButton1.setBackground(Color.LIGHT_GRAY);
             jButton1.setForeground(Color.DARK_GRAY);
        }

        selectedItemsDisplayPanel.revalidate();
        selectedItemsDisplayPanel.repaint();
        if (itemsSummaryScrollPane != null) {
            itemsSummaryScrollPane.revalidate();
            itemsSummaryScrollPane.repaint();
        }
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    private void updateSelectAllCheckboxState() {
        if (cartItems == null || cartItems.isEmpty()) {
            jCheckBox1.setSelected(false);
            jCheckBox1.setEnabled(false);
            return;
        }
        jCheckBox1.setEnabled(true);
        boolean allSelected = true;
        for (Map<String, Object> item : cartItems) {
            if (item != null && !(Boolean) item.getOrDefault("selected", false)) {
                allSelected = false;
                break;
            }
        }
        ItemListener[] listeners = jCheckBox1.getItemListeners();
        for(ItemListener listener : listeners) {
            if (listener != null) jCheckBox1.removeItemListener(listener);
        }
        jCheckBox1.setSelected(allSelected);
        for(ItemListener listener : listeners) {
             if (listener != null) jCheckBox1.addItemListener(listener);
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
        setTitle("UFarm - My Cart");
        setPreferredSize(new java.awt.Dimension(950, 600)); // Set a reasonable default window size

        Dash.setBackground(new java.awt.Color(35, 101, 51));

        Title.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("UFarm");

        HomePanel.setBackground(new java.awt.Color(35, 101, 51));
        HomePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home.setFont(new java.awt.Font("Helvetica Neue", 0, 24));
        Home.setForeground(new java.awt.Color(255, 255, 255));
        Home.setIcon(loadImageIcon("/com/ufarm/ufarm/images/home_icon.png"));
        Home.setText("Home");
        Home.setIconTextGap(15);

        javax.swing.GroupLayout HomePanelLayout = new javax.swing.GroupLayout(HomePanel);
        HomePanel.setLayout(HomePanelLayout);
        HomePanelLayout.setHorizontalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Home, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
        HomePanelLayout.setVerticalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Home, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        AccPanel.setBackground(new java.awt.Color(35, 101, 51));
        AccPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Acc.setFont(new java.awt.Font("Helvetica Neue", 0, 24));
        Acc.setForeground(new java.awt.Color(255, 255, 255));
        Acc.setIcon(loadImageIcon("/com/ufarm/ufarm/images/account_icon.png"));
        Acc.setText("Account");
        Acc.setIconTextGap(15);

        javax.swing.GroupLayout AccPanelLayout = new javax.swing.GroupLayout(AccPanel);
        AccPanel.setLayout(AccPanelLayout);
        AccPanelLayout.setHorizontalGroup(
            AccPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Acc, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
        AccPanelLayout.setVerticalGroup(
            AccPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Acc, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        ProducePanel.setBackground(new java.awt.Color(35, 101, 51));
        ProducePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Produce.setFont(new java.awt.Font("Helvetica Neue", 0, 24));
        Produce.setForeground(new java.awt.Color(255, 255, 255));
        Produce.setIcon(loadImageIcon("/com/ufarm/ufarm/images/produce_icon.png"));
        Produce.setText("Produce");
        Produce.setIconTextGap(15);

        javax.swing.GroupLayout ProducePanelLayout = new javax.swing.GroupLayout(ProducePanel);
        ProducePanel.setLayout(ProducePanelLayout);
        ProducePanelLayout.setHorizontalGroup(
            ProducePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProducePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Produce, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
        ProducePanelLayout.setVerticalGroup(
            ProducePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProducePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Produce, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        FarmPanel.setBackground(new java.awt.Color(35, 101, 51));
        FarmPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Farm.setFont(new java.awt.Font("Helvetica Neue", 0, 24));
        Farm.setForeground(new java.awt.Color(255, 255, 255));
        Farm.setIcon(loadImageIcon("/com/ufarm/ufarm/images/farm_icon.png"));
        Farm.setText("Farm");
        Farm.setIconTextGap(15);

        javax.swing.GroupLayout FarmPanelLayout = new javax.swing.GroupLayout(FarmPanel);
        FarmPanel.setLayout(FarmPanelLayout);
        FarmPanelLayout.setHorizontalGroup(
            FarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FarmPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Farm, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
        FarmPanelLayout.setVerticalGroup(
            FarmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FarmPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Farm, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        FeedbackPanel.setBackground(new java.awt.Color(65, 131, 81));
        FeedbackPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Feedback.setFont(new java.awt.Font("Helvetica Neue", 1, 24));
        Feedback.setForeground(new java.awt.Color(255, 255, 255));
        Feedback.setIcon(loadImageIcon("/com/ufarm/ufarm/images/cart_icon_selected.png"));
        Feedback.setText("Cart");
        Feedback.setIconTextGap(15);

        javax.swing.GroupLayout FeedbackPanelLayout = new javax.swing.GroupLayout(FeedbackPanel);
        FeedbackPanel.setLayout(FeedbackPanelLayout);
        FeedbackPanelLayout.setHorizontalGroup(
            FeedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FeedbackPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Feedback, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
        FeedbackPanelLayout.setVerticalGroup(
            FeedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FeedbackPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Feedback, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 10));
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Agco, Jasmine Jane @2025");

        FeedbackPanel1.setBackground(new java.awt.Color(35, 101, 51));
        FeedbackPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Feedback1.setFont(new java.awt.Font("Helvetica Neue", 0, 24));
        Feedback1.setForeground(new java.awt.Color(255, 255, 255));
        Feedback1.setIcon(loadImageIcon("/com/ufarm/ufarm/images/feedback_icon.png"));
        Feedback1.setText("Feedback");
        Feedback1.setIconTextGap(15);

        javax.swing.GroupLayout FeedbackPanel1Layout = new javax.swing.GroupLayout(FeedbackPanel1);
        FeedbackPanel1.setLayout(FeedbackPanel1Layout);
        FeedbackPanel1Layout.setHorizontalGroup(
            FeedbackPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FeedbackPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Feedback1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
        FeedbackPanel1Layout.setVerticalGroup(
            FeedbackPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FeedbackPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Feedback1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
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
            .addComponent(FeedbackPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DashLayout.createSequentialGroup()
                .addGroup(DashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(DashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
                    .addGroup(DashLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DashLayout.setVerticalGroup(
            DashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
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

        MouseAdapter navHover = new MouseAdapter() {
            final Color hoverColor = new Color(50, 120, 70);
            final Color originalColor = new Color(35, 101, 51);
            final Color selectedColor = new Color(65, 131, 81);
            @Override
            public void mouseEntered(MouseEvent me) {
                JPanel panel = (JPanel) me.getSource();
                if (!panel.getBackground().equals(selectedColor)) {
                    panel.setBackground(hoverColor);
                }
            }
            @Override
            public void mouseExited(MouseEvent me) {
                JPanel panel = (JPanel) me.getSource();
                 if (!panel.getBackground().equals(selectedColor)) {
                     panel.setBackground(originalColor);
                }
            }
        };
        HomePanel.addMouseListener(navHover);
        AccPanel.addMouseListener(navHover);
        ProducePanel.addMouseListener(navHover);
        FarmPanel.addMouseListener(navHover);
        FeedbackPanel1.addMouseListener(navHover);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jLabel2.setFont(new java.awt.Font("Georgia", 1, 24));
        jLabel2.setForeground(new java.awt.Color(35, 101, 51));
        jLabel2.setText("My Cart");
        jTextField1.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        jTextField1.addActionListener(this::jTextField1ActionPerformed);
        jLabel3.setIcon(loadImageIcon("/com/ufarm/ufarm/images/search.png"));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jCheckBox1.setFont(new java.awt.Font("Helvetica Neue", 0, 15));
        jCheckBox1.setText(" Select all items");
        jCheckBox1.setOpaque(false);
        jCheckBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBox1.addItemListener(this::jCheckBox1ItemStateChanged);

        subtotalValueLabel = new JLabel();
        deliveryFeeValueLabel = new JLabel();
        totalValueLabel = new JLabel();
        selectedItemsDisplayPanel = new JPanel();
        selectedItemsDisplayPanel.setLayout(new BoxLayout(selectedItemsDisplayPanel, BoxLayout.Y_AXIS));
        selectedItemsDisplayPanel.setBackground(Color.WHITE);
        itemsSummaryScrollPane = new JScrollPane(selectedItemsDisplayPanel);
        itemsSummaryScrollPane.setBorder(BorderFactory.createEmptyBorder());
        itemsSummaryScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        itemsSummaryScrollPane.getViewport().setBackground(selectedItemsDisplayPanel.getBackground());
        itemsSummaryScrollPane.setPreferredSize(new Dimension(230, 150)); // Increased height for item list
        itemsSummaryScrollPane.setMinimumSize(new Dimension(200, 80));

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), " Order Summary ",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Helvetica Neue", Font.BOLD, 15),
                new java.awt.Color(35, 101, 51)
            ),
            BorderFactory.createEmptyBorder(5, 10, 10, 10)
        ));
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
        jPanel2.add(itemsSummaryScrollPane);
        jPanel2.add(Box.createRigidArea(new Dimension(0, 8)));
        JSeparator itemsToTotalSeparator = new JSeparator();
        itemsToTotalSeparator.setForeground(new Color(200, 200, 200));
        itemsToTotalSeparator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        jPanel2.add(itemsToTotalSeparator);
        jPanel2.add(Box.createRigidArea(new Dimension(0, 8)));
        JPanel subtotalPanel = new JPanel(new BorderLayout(5, 0));
        subtotalPanel.setOpaque(false);
        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
        jLabel5.setText("Subtotal:");
        subtotalValueLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
        subtotalValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        subtotalValueLabel.setText("$0.00");
        subtotalPanel.add(jLabel5, BorderLayout.WEST);
        subtotalPanel.add(subtotalValueLabel, BorderLayout.EAST);
        jPanel2.add(subtotalPanel);
        jPanel2.add(Box.createRigidArea(new Dimension(0, 5)));
        JPanel deliveryPanel = new JPanel(new BorderLayout(5, 0));
        deliveryPanel.setOpaque(false);
        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
        jLabel6.setText("Delivery Fee:");
        deliveryFeeValueLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
        deliveryFeeValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        deliveryFeeValueLabel.setText("$0.00");
        deliveryPanel.add(jLabel6, BorderLayout.WEST);
        deliveryPanel.add(deliveryFeeValueLabel, BorderLayout.EAST);
        jPanel2.add(deliveryPanel);
        jPanel2.add(Box.createRigidArea(new Dimension(0, 10)));
        jSeparator3.setForeground(new java.awt.Color(35, 101, 51));
        jSeparator3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        jPanel2.add(jSeparator3);
        jPanel2.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel totalPanel = new JPanel(new BorderLayout(5, 0));
        totalPanel.setOpaque(false);
        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 18));
        jLabel7.setForeground(new java.awt.Color(35, 101, 51));
        jLabel7.setText("Total:");
        totalValueLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18));
        totalValueLabel.setForeground(new java.awt.Color(35, 101, 51));
        totalValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalValueLabel.setText("$0.00");
        totalPanel.add(jLabel7, BorderLayout.WEST);
        totalPanel.add(totalValueLabel, BorderLayout.EAST);
        jPanel2.add(totalPanel);
        jPanel2.add(Box.createRigidArea(new Dimension(0, 15)));
        jButton1.setBackground(new java.awt.Color(35, 101, 51));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 16));
        jButton1.setForeground(Color.WHITE);
        jButton1.setText("Place Order");
        jButton1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButton1.setMaximumSize(new Dimension(Integer.MAX_VALUE, jButton1.getPreferredSize().height + 5));
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel2.add(jButton1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224)));
        jPanel3.setLayout(new java.awt.BorderLayout());
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

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
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)) // Adjusted default width for jPanel3
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Dash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        String searchText = jTextField1.getText();
        System.out.println("Search text: " + searchText);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (subtotal <= 0) {
            JOptionPane.showMessageDialog(this,
                "No items selected or cart is empty. Please select items to proceed.",
                "No Items Selected", JOptionPane.WARNING_MESSAGE);
        } else {
            calculateOrderSummary();
            double currentDeliveryFee = (subtotal > 0) ? DELIVERY_FEE : 0.0;
            double finalTotal = subtotal + currentDeliveryFee;
            String confirmationMessage = String.format(
                "Please confirm your order:\n\n" +
                "Subtotal:         $%.2f\n" +
                "Delivery Fee:  $%.2f\n" +
                "----------------------------------\n" +
                "Total:              $%.2f\n\n" +
                "Proceed with this order?",
                subtotal, currentDeliveryFee, finalTotal
            );
            int response = JOptionPane.showConfirmDialog(
                this, confirmationMessage, "Order Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
            );
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("Order placed for total: $" + String.format("%.2f", finalTotal));
                System.out.println("Items ordered:");
                if (cartItems != null) {
                    Iterator<Map<String, Object>> iterator = cartItems.iterator();
                    while (iterator.hasNext()) {
                        Map<String, Object> item = iterator.next();
                        if (item != null && (Boolean)item.getOrDefault("selected", false)) {
                            System.out.println("- " + item.get("name") + " x" + item.get("quantity") + " @ $" + item.get("price"));
                            iterator.remove();
                        }
                    }
                    itemCheckBoxes.clear();
                }
                JOptionPane.showMessageDialog(this,
                    "Order placed successfully! Thank you for your purchase.",
                    "Order Success", JOptionPane.INFORMATION_MESSAGE);
                displayCartItems();
                calculateOrderSummary();
                updateSelectAllCheckboxState();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
         if (evt.getSource() == jCheckBox1) {
            boolean selectAll = jCheckBox1.isSelected();
            if (cartItems != null) {
                for (Map<String, Object> item : cartItems) {
                    if (item != null) {
                        item.put("selected", selectAll);
                    }
                }
                for (JCheckBox checkBox : itemCheckBoxes) {
                    ItemListener[] cbListeners = checkBox.getItemListeners();
                    for(ItemListener l : cbListeners) checkBox.removeItemListener(l);
                    checkBox.setSelected(selectAll);
                    for(ItemListener l : cbListeners) checkBox.addItemListener(l);
                }
            }
            calculateOrderSummary();
         }
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
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.INFO, "Nimbus L&F not available.", ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Cart().setVisible(true));
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