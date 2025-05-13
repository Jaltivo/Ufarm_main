/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ufarm.ufarm;

/**
 *
 * @author Jasmine
 */

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Produce2 extends javax.swing.JFrame {

    // Cart items specific to Produce2 page
    private static ArrayList<Map<String, Object>> cartItems = new ArrayList<>();

    /**
     * Creates new form Produce2
     */
    public Produce2() {
        initComponents(); // This will initialize the components

        // Navigation Listeners
        Page1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Page1MouseClicked(evt);
            }
        });

        Page3.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Page3MouseClicked(evt);
            }
        });

        Next.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NextMouseClicked(evt);
            }
        });

        Prev.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrevMouseClicked(evt);
            }
        });

        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Home().setVisible(true);
                dispose();
            }
        });

        Acc.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Login().setVisible(true); // Or your account/logout page
                dispose();
            }
        });

        Farm.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(Produce2.this, "Farm page not implemented yet.");
            }
        });

        Feedback.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Feedback().setVisible(true);
                dispose();
            }
        });

        CartMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Cart().setVisible(true);
                dispose();
            }
        });

        // Styling for current page indication (Produce section, Page 2)
        ProducePanel.setBackground(new Color(102, 51, 0)); // Highlight ProducePanel as current section
        Produce.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // Bold "Produce" label
        Produce.setForeground(new java.awt.Color(255, 255, 200)); // Slightly different color for current menu item
        
        Page2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // Bold "Page 2" label
        // Page2.setForeground(new java.awt.Color(255, 255, 200)); // Example: highlight color if needed, already white

        // Hover Effects for Labels (avoiding current page elements)
        setupLabelHoverEffect(Home, java.awt.Color.WHITE, new java.awt.Color(220, 220, 100));
        setupLabelHoverEffect(Acc, java.awt.Color.WHITE, new java.awt.Color(220, 220, 100));
        // Produce label is current, hover handled by not being in this general setup or specific conditions
        setupLabelHoverEffect(Farm, java.awt.Color.WHITE, new java.awt.Color(220, 220, 100));
        setupLabelHoverEffect(CartMenu, java.awt.Color.WHITE, new java.awt.Color(220, 220, 100));
        setupLabelHoverEffect(Feedback, java.awt.Color.WHITE, new java.awt.Color(220, 220, 100));

        setupLabelHoverEffect(Prev, java.awt.Color.WHITE, new java.awt.Color(200, 200, 200));
        setupLabelHoverEffect(Next, java.awt.Color.WHITE, new java.awt.Color(200, 200, 200));
        setupLabelHoverEffect(Page1, java.awt.Color.WHITE, new java.awt.Color(200, 200, 200));
        // Page2 label is current, hover handled by not being in this general setup
        setupLabelHoverEffect(Page3, java.awt.Color.WHITE, new java.awt.Color(200, 200, 200));
        

        // Hover Effects for Panels (avoiding current page panel)
        setupPanelHoverEffects(HomePanel, new Color(33, 113, 0), new Color(45, 125, 10));
        setupPanelHoverEffects(AccPanel, new Color(33, 113, 0), new Color(45, 125, 10));
        // ProducePanel is current, hover handled by not being in this general setup
        setupPanelHoverEffects(FarmPanel, new Color(33, 113, 0), new Color(45, 125, 10));
        setupPanelHoverEffects(CartPanel, new Color(33, 113, 0), new Color(45, 125, 10));
        setupPanelHoverEffects(FeedbackPanel, new Color(33,113,0), new Color(45,125,10));

        Color productNormalColor = new Color(35, 101, 51);
        Color productHoverColor = new Color(45, 115, 61);
        setupPanelHoverEffects(jPanel14, productNormalColor, productHoverColor); // GreenBeans
        setupPanelHoverEffects(jPanel15, productNormalColor, productHoverColor); // Ginger
        setupPanelHoverEffects(jPanel16, productNormalColor, productHoverColor); // Onion
        setupPanelHoverEffects(jPanel17, productNormalColor, productHoverColor); // Carrot
        setupPanelHoverEffects(jPanel18, productNormalColor, productHoverColor); // Chili
        setupPanelHoverEffects(jPanel19, productNormalColor, productHoverColor); // Spinach

        // Add action listeners for Add to Cart buttons using lambdas
        // These are the correct listeners and ensure no others are attached in initComponents.
        jButton1.addActionListener(evt -> addToCart("GreenBeans", (Integer)jSpinner1.getValue(), (ImageIcon)jLabel3.getIcon()));
        jButton2.addActionListener(evt -> addToCart("Ginger", (Integer)jSpinner2.getValue(), (ImageIcon)jLabel6.getIcon()));
        jButton3.addActionListener(evt -> addToCart("Onion", (Integer)jSpinner3.getValue(), (ImageIcon)jLabel4.getIcon()));
        jButton4.addActionListener(evt -> addToCart("Chili", (Integer)jSpinner4.getValue(), (ImageIcon)jLabel5.getIcon()));
        jButton5.addActionListener(evt -> addToCart("Carrot", (Integer)jSpinner5.getValue(), (ImageIcon)jLabel7.getIcon()));
        jButton6.addActionListener(evt -> addToCart("Spinach", (Integer)jSpinner6.getValue(), (ImageIcon)jLabel8.getIcon()));

        setLocationRelativeTo(null);
    }

    private void Page1MouseClicked(java.awt.event.MouseEvent evt) {
        new Produce().setVisible(true);
        this.dispose();
    }

    private void Page3MouseClicked(java.awt.event.MouseEvent evt) {
        new Produce3().setVisible(true);
        this.dispose();
    }

    private void NextMouseClicked(java.awt.event.MouseEvent evt) {
        new Produce3().setVisible(true);
        this.dispose();
    }

    private void PrevMouseClicked(java.awt.event.MouseEvent evt) {
        new Produce().setVisible(true);
        this.dispose();
    }

    private void setupPanelHoverEffects(JPanel panel, Color normalColor, Color hoverColor) {
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Avoid changing current page panel (ProducePanel) color on hover
                if (panel != ProducePanel) { 
                   panel.setBackground(hoverColor);
                }
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                 if (panel != ProducePanel) { // Only revert if it's not the current page panel
                    panel.setBackground(normalColor);
                 }
                panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void setupLabelHoverEffect(javax.swing.JLabel label, Color normalColor, Color hoverColor) {
        // Avoid changing color for current "Produce" menu label and "Page2" pagination label
        boolean isCurrentPageElement = (label == Produce || label == Page2);

        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!isCurrentPageElement) {
                    label.setForeground(hoverColor);
                }
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                 if (!isCurrentPageElement) {
                    label.setForeground(normalColor);
                 }
                label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
    
    private void addToCart(String itemName, int quantity, ImageIcon itemIcon) {
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Please select a quantity greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double price = 0.0;
        switch(itemName) {
            case "GreenBeans": price = 2.19; break;
            case "Ginger":     price = 3.29; break;
            case "Onion":      price = 1.19; break;
            case "Chili":      price = 2.09; break;
            case "Carrot":     price = 1.59; break;
            case "Spinach":    price = 2.69; break;
            default:
                JOptionPane.showMessageDialog(this, "Price for " + itemName + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }
        for (Map<String, Object> item : cartItems) {
            if (item.get("name").equals(itemName)) {
                item.put("quantity", (Integer)item.get("quantity") + quantity);
                JOptionPane.showMessageDialog(this, quantity + " more " + itemName + "(s) added to cart!", "Cart Updated", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        Map<String, Object> newItem = new HashMap<>();
        newItem.put("name", itemName);
        newItem.put("quantity", quantity);
        newItem.put("icon", itemIcon);
        newItem.put("price", price);
        newItem.put("selected", true);
        cartItems.add(newItem);
        JOptionPane.showMessageDialog(this, quantity + " " + itemName + "(s) added to cart!", "Item Added", JOptionPane.INFORMATION_MESSAGE);
    }

    public static ArrayList<Map<String, Object>> getCartItems() {
        return cartItems;
    }

    public static void clearCartItems() {
        cartItems.clear();
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
        CartPanel = new javax.swing.JPanel();
        CartMenu = new javax.swing.JLabel();
        MyLove = new javax.swing.JLabel();
        FeedbackPanel = new javax.swing.JPanel();
        Feedback = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        Search = new javax.swing.JLabel();
        Cart = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        Prev = new javax.swing.JLabel();
        Next = new javax.swing.JLabel();
        Page1 = new javax.swing.JLabel();
        Page2 = new javax.swing.JLabel();
        Page3 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jButton2 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSpinner5 = new javax.swing.JSpinner();
        jButton5 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSpinner4 = new javax.swing.JSpinner();
        jButton4 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSpinner6 = new javax.swing.JSpinner();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UFarm - Produce Page 2");

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
        Acc.setText("Logout");

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

        ProducePanel.setBackground(new java.awt.Color(102, 51, 0)); // Current page highlight

        Produce.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        Produce.setForeground(new java.awt.Color(255, 255, 255)); // Set in constructor for current page
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

        CartPanel.setBackground(new java.awt.Color(33, 113, 0));

        CartMenu.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        CartMenu.setForeground(new java.awt.Color(255, 255, 255));
        CartMenu.setText("Cart");

        javax.swing.GroupLayout CartPanelLayout = new javax.swing.GroupLayout(CartPanel);
        CartPanel.setLayout(CartPanelLayout);
        CartPanelLayout.setHorizontalGroup(
            CartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CartPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(CartMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CartPanelLayout.setVerticalGroup(
            CartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CartPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CartMenu)
                .addGap(157, 157, 157))
        );

        MyLove.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        MyLove.setForeground(new java.awt.Color(204, 204, 204));
        MyLove.setText("Agco, Jasmine Jane @2025");

        FeedbackPanel.setBackground(new java.awt.Color(33, 113, 0));

        Feedback.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        Feedback.setForeground(new java.awt.Color(255, 255, 255));
        Feedback.setText("Feedback");

        javax.swing.GroupLayout FeedbackPanelLayout = new javax.swing.GroupLayout(FeedbackPanel);
        FeedbackPanel.setLayout(FeedbackPanelLayout);
        FeedbackPanelLayout.setHorizontalGroup(
            FeedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FeedbackPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Feedback)
                .addGap(53, 53, 53))
        );
        FeedbackPanelLayout.setVerticalGroup(
            FeedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FeedbackPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Feedback)
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
            .addComponent(CartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(MyLove)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(FeedbackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(CartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FeedbackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(MyLove)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(35, 101, 51));
        jLabel2.setText("Available Produce");

        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 101, 51), 2, true));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ufarm/ufarm/images/search.png"))); // NOI18N

        Cart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ufarm/ufarm/images/grocery-store.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Search)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cart, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Cart)
                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(35, 101, 51));

        Prev.setForeground(new java.awt.Color(255, 255, 255));
        Prev.setText("  <");
        Prev.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Next.setForeground(new java.awt.Color(255, 255, 255));
        Next.setText(" > ");
        Next.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Page1.setForeground(new java.awt.Color(255, 255, 255));
        Page1.setText("  1 ");
        Page1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Page2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N // Styled in constructor
        Page2.setForeground(new java.awt.Color(255, 255, 255));     // Styled in constructor
        Page2.setText("  2  ");
        Page2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Page3.setForeground(new java.awt.Color(255, 255, 255));
        Page3.setText("  3  ");
        Page3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(Prev, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Page1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Page2)
                .addGap(0, 0, 0)
                .addComponent(Page3)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Next, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Prev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Page1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Page2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(Page3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Next, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(35, 101, 51));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ufarm/ufarm/images/GreenBeans.jpeg"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("GreenBeans");

        jButton1.setBackground(new java.awt.Color(35, 101, 51));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("+ ADD");
        // NO addActionListener for jButton1ActionPerformed(evt) here. It's handled in constructor.

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(3, 3, 3)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(1, 1, 1))
        );

        jPanel15.setBackground(new java.awt.Color(35, 101, 51));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ufarm/ufarm/images/Ginger.jpeg"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Ginger");

        jButton2.setBackground(new java.awt.Color(35, 101, 51));
        jButton2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("+ ADD");
        // NO addActionListener for jButton2ActionPerformed(evt) here.

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(3, 3, 3)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(1, 1, 1))
        );

        jPanel16.setBackground(new java.awt.Color(35, 101, 51));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ufarm/ufarm/images/Onion.jpg"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Onion");

        jButton3.setBackground(new java.awt.Color(35, 101, 51));
        jButton3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("+ ADD");
        // NO addActionListener for jButton3ActionPerformed(evt) here.

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(3, 3, 3)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(1, 1, 1))
        );

        jPanel17.setBackground(new java.awt.Color(35, 101, 51));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ufarm/ufarm/images/Carrot.jpeg"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Carrot");

        jButton5.setBackground(new java.awt.Color(35, 101, 51));
        jButton5.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("+ ADD");
        // NO addActionListener for jButton5ActionPerformed(evt) here.

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(3, 3, 3)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(1, 1, 1))
        );

        jPanel18.setBackground(new java.awt.Color(35, 101, 51));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ufarm/ufarm/images/Chili.jpg"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Chili");

        jButton4.setBackground(new java.awt.Color(35, 101, 51));
        jButton4.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("+ ADD");
        // NO addActionListener for jButton4ActionPerformed(evt) here.

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(3, 3, 3)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(1, 1, 1))
        );

        jPanel19.setBackground(new java.awt.Color(35, 101, 51));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ufarm/ufarm/images/Spinach.jpg"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Spinach");

        jButton6.setBackground(new java.awt.Color(35, 101, 51));
        jButton6.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("+ ADD");
        // NO addActionListener for jButton6ActionPerformed(evt) here.

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(3, 3, 3)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(1, 1, 1))
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
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(288, 288, 288))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Dash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
         String searchText = jTextField1.getText();
         System.out.println("Search on Produce Page 2: " + searchText);
    }//GEN-LAST:event_jTextField1ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Produce2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Produce2().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Acc;
    private javax.swing.JPanel AccPanel;
    private javax.swing.JLabel Cart;
    private javax.swing.JLabel CartMenu;
    private javax.swing.JPanel CartPanel;
    private javax.swing.JPanel Dash;
    private javax.swing.JLabel Farm;
    private javax.swing.JPanel FarmPanel;
    private javax.swing.JLabel Feedback;
    private javax.swing.JPanel FeedbackPanel;
    private javax.swing.JLabel Home;
    private javax.swing.JPanel HomePanel;
    private javax.swing.JLabel MyLove;
    private javax.swing.JLabel Next;
    private javax.swing.JLabel Page1;
    private javax.swing.JLabel Page2;
    private javax.swing.JLabel Page3;
    private javax.swing.JLabel Prev;
    private javax.swing.JLabel Produce;
    private javax.swing.JPanel ProducePanel;
    private javax.swing.JLabel Search;
    private javax.swing.JLabel Title;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}