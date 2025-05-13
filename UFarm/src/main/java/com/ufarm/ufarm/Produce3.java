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
import java.util.Map; // Added import

public class Produce3 extends javax.swing.JFrame {

    // Cart items specific to Produce3 page
    private static ArrayList<Map<String, Object>> cartItems = new ArrayList<>(); // Cart items for Produce3

    /**
     * Creates new form Produce3
     */
    public Produce3() {
        initComponents();
        setLocationRelativeTo(null); // Center the window

        // Navigation Listeners
        Acc.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Login().setVisible(true);
                dispose();
            }
        });
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Home().setVisible(true);
                dispose();
            }
        });
        Farm.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // new Farm().setVisible(true); // Assuming Farm class exists
                // dispose();
                JOptionPane.showMessageDialog(Produce3.this, "Farm page not implemented yet.");
            }
        });
        CartMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Cart().setVisible(true);
                dispose();
            }
        });
        Feedback.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Feedback().setVisible(true);
                dispose();
            }
        });
        Page1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Page1MouseClicked(evt);
            }
        });
        Page2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Page2MouseClicked(evt);
            }
        });
        Prev.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrevMouseClicked(evt);
            }
        });
        // Next button on Page 3 might navigate to Page 1 or be disabled if it's the last page.
        // For this example, let's assume Next is disabled or navigates to Page 1.
        Next.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // new Produce().setVisible(true); // Or disable if it's the last page
                // dispose();
                 JOptionPane.showMessageDialog(Produce3.this, "You are on the last page.");
            }
        });


        // Hover Effects for Labels
        setupLabelHoverEffect(Home, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));
        setupLabelHoverEffect(Acc, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));
        setupLabelHoverEffect(Produce, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));
        setupLabelHoverEffect(Farm, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));
        setupLabelHoverEffect(CartMenu, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));
        setupLabelHoverEffect(Feedback, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));
        setupLabelHoverEffect(Prev, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));
        setupLabelHoverEffect(Next, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));
        setupLabelHoverEffect(Page1, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));
        setupLabelHoverEffect(Page2, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));
        // Page3 is current, so style it differently or use hover effect as well
        setupLabelHoverEffect(Page3, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));


        // Hover Effects for Panels
        setupPanelHoverEffects(HomePanel, new Color(33, 113, 0), new Color(50, 140, 0));
        setupPanelHoverEffects(AccPanel, new Color(33, 113, 0), new Color(50, 140, 0));
        setupPanelHoverEffects(ProducePanel, new Color(102, 51, 0), new Color(120, 60, 0)); // Active Page Style
        setupPanelHoverEffects(FarmPanel, new Color(33, 113, 0), new Color(50, 140, 0));
        setupPanelHoverEffects(CartPanel, new Color(33, 113, 0), new Color(50, 140, 0));
        setupPanelHoverEffects(FeedbackPanel, new Color(33,113,0), new Color(50,140,0));

        Color productNormalColor = new Color(35, 101, 51);
        Color productHoverColor = new Color(50, 130, 70);
        setupPanelHoverEffects(jPanel14, productNormalColor, productHoverColor); // Bell Pepper
        setupPanelHoverEffects(jPanel16, productNormalColor, productHoverColor); // Radish
        setupPanelHoverEffects(jPanel17, productNormalColor, productHoverColor); // Parsley
        setupPanelHoverEffects(jPanel15, productNormalColor, productHoverColor); // Lemon
        setupPanelHoverEffects(jPanel18, productNormalColor, productHoverColor); // Spring Onion
        setupPanelHoverEffects(jPanel19, productNormalColor, productHoverColor); // Pechay

        // Add to Cart button actions
        // Icons: jLabel4 (BellPepper), jLabel5 (Radish), jLabel6 (Parsley)
        // jLabel7 (Lemon), jLabel8 (SpringOnion), jLabel1 (Pechay - assuming this is jLabel9 in the image panel)

        jButton1.addActionListener(evt -> addToCart("Bell Pepper", (Integer)jSpinner1.getValue(), jLabel4.getIcon()));
        jButton2.addActionListener(evt -> addToCart("Radish", (Integer)jSpinner2.getValue(), jLabel5.getIcon()));
        jButton3.addActionListener(evt -> addToCart("Parsley", (Integer)jSpinner3.getValue(), jLabel6.getIcon()));
        jButton4.addActionListener(evt -> addToCart("Lemon", (Integer)jSpinner4.getValue(), jLabel7.getIcon()));
        jButton5.addActionListener(evt -> addToCart("Spring Onion", (Integer)jSpinner5.getValue(), jLabel8.getIcon()));
        jButton6.addActionListener(evt -> addToCart("Pechay", (Integer)jSpinner6.getValue(), jLabel1.getIcon())); // Assuming jLabel1 is for Pechay image
    }

    private void Page1MouseClicked(java.awt.event.MouseEvent evt) {
        new Produce().setVisible(true);
        this.dispose();
    }

    private void Page2MouseClicked(java.awt.event.MouseEvent evt) {
        new Produce2().setVisible(true);
        this.dispose();
    }

    private void PrevMouseClicked(java.awt.event.MouseEvent evt) {
        new Produce2().setVisible(true);
        this.dispose();
    }


    private void setupPanelHoverEffects(JPanel panel, Color normalColor, Color hoverColor) {
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel.setBackground(hoverColor);
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel.setBackground(normalColor);
                panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void setupLabelHoverEffect(javax.swing.JLabel label, Color normalColor, Color hoverColor) {
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setForeground(hoverColor);
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setForeground(normalColor);
                label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    // Helper method to add items to cart for Produce3
    private void addToCart(String itemName, int quantity, Icon itemIcon) {
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Please select a quantity greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add prices for each item on Produce3
        double price = 0.0;
        switch(itemName) {
            case "Bell Pepper":  price = 2.99; break;
            case "Radish":       price = 1.29; break;
            case "Parsley":      price = 0.99; break;
            case "Lemon":        price = 0.79; break;
            case "Spring Onion": price = 1.49; break;
            case "Pechay":       price = 1.09; break;
            default:
                JOptionPane.showMessageDialog(this, "Price for " + itemName + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        // Check if item already exists in cart
        for (Map<String, Object> item : cartItems) {
            if (item.get("name").equals(itemName)) {
                // Update quantity if item exists
                item.put("quantity", (Integer)item.get("quantity") + quantity);
                JOptionPane.showMessageDialog(this, quantity + " more " + itemName + "(s) added to cart!", "Cart Updated", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        // Add new item to cart
        Map<String, Object> newItem = new HashMap<>();
        newItem.put("name", itemName);
        newItem.put("quantity", quantity);
        newItem.put("icon", itemIcon);
        newItem.put("price", price);
        newItem.put("selected", true); // Default to selected when added

        cartItems.add(newItem);
        JOptionPane.showMessageDialog(this, quantity + " " + itemName + "(s) added to cart!", "Item Added", JOptionPane.INFORMATION_MESSAGE);
    }

    // Add a static method to access cart items from other classes
    public static ArrayList<Map<String, Object>> getCartItems() {
        return cartItems;
    }

    // Static method to clear cart items for this page
    public static void clearCartItems() {
        cartItems.clear();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSpinner4 = new javax.swing.JSpinner();
        jButton4 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jButton2 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSpinner5 = new javax.swing.JSpinner();
        jButton5 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSpinner6 = new javax.swing.JSpinner();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

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

        ProducePanel.setBackground(new java.awt.Color(102, 51, 0));

        Produce.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
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
            .addComponent(FeedbackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DashLayout.createSequentialGroup()
                .addGroup(DashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DashLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(Title))
                    .addGroup(DashLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(MyLove)))
                .addGap(0, 27, Short.MAX_VALUE))
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
                .addGap(0, 0, Short.MAX_VALUE)
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

        Search.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Ufarm_main\\UFarm\\src\\main\\java\\com\\ufarm\\ufarm\\images\\search.png")); // NOI18N

        Cart.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Ufarm_main\\UFarm\\src\\main\\java\\com\\ufarm\\ufarm\\images\\grocery-store.png")); // NOI18N

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
                .addGap(0, 13, Short.MAX_VALUE))
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

        Page2.setForeground(new java.awt.Color(255, 255, 255));
        Page2.setText("  2  ");
        Page2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Page3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
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
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Page1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Page2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(Page3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Next, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(35, 101, 51));

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Ufarm_main\\UFarm\\src\\main\\java\\com\\ufarm\\ufarm\\images\\BellPepper.jpeg")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Bell Pepper");

        jButton1.setBackground(new java.awt.Color(35, 101, 51));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("+ ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel3)
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
                .addComponent(jLabel3)
                .addGap(3, 3, 3)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(1, 1, 1))
        );

        jPanel15.setBackground(new java.awt.Color(35, 101, 51));

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Ufarm_main\\UFarm\\src\\main\\java\\com\\ufarm\\ufarm\\images\\Lemon.jpeg")); // NOI18N

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Lemon");

        jButton4.setBackground(new java.awt.Color(35, 101, 51));
        jButton4.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("+ ADD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(3, 3, 3)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(1, 1, 1))
        );

        jPanel16.setBackground(new java.awt.Color(35, 101, 51));

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Ufarm_main\\UFarm\\src\\main\\java\\com\\ufarm\\ufarm\\images\\Radish.jpg")); // NOI18N

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Radish");

        jButton2.setBackground(new java.awt.Color(35, 101, 51));
        jButton2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("+ ADD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel10)
                .addGap(3, 3, 3)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(1, 1, 1))
        );

        jPanel17.setBackground(new java.awt.Color(35, 101, 51));

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Ufarm_main\\UFarm\\src\\main\\java\\com\\ufarm\\ufarm\\images\\Parsley.jpg")); // NOI18N

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Parsley");

        jButton3.setBackground(new java.awt.Color(35, 101, 51));
        jButton3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("+ ADD");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(3, 3, 3)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(1, 1, 1))
        );

        jPanel18.setBackground(new java.awt.Color(35, 101, 51));

        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Ufarm_main\\UFarm\\src\\main\\java\\com\\ufarm\\ufarm\\images\\SpringOnion.jpeg")); // NOI18N

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Spring Onion");

        jButton5.setBackground(new java.awt.Color(35, 101, 51));
        jButton5.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("+ ADD");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(3, 3, 3)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(1, 1, 1))
        );

        jPanel19.setBackground(new java.awt.Color(35, 101, 51));

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Pechay");

        jButton6.setBackground(new java.awt.Color(35, 101, 51));
        jButton6.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("+ ADD");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Ufarm_main\\UFarm\\src\\main\\java\\com\\ufarm\\ufarm\\images\\Pechay.jpg")); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(3, 3, 3)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE)
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
                            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(288, 288, 288))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(49, 49, 49)
                                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Dash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        String searchText = jTextField1.getText();
        System.out.println("Search on Produce Page 3: " + searchText);
    }//GEN-LAST:event_jTextField1ActionPerformed

    //These event handlers were present in the original file but linked to non-existent methods.
    //They are now linked to the addToCart method.
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // addToCart("Bell Pepper", (Integer)jSpinner1.getValue(), jLabel4.getIcon()); // Already handled in constructor
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // addToCart("Radish", (Integer)jSpinner2.getValue(), jLabel5.getIcon()); // Already handled in constructor
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // addToCart("Parsley", (Integer)jSpinner3.getValue(), jLabel6.getIcon()); // Already handled in constructor
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // addToCart("Lemon", (Integer)jSpinner4.getValue(), jLabel7.getIcon()); // Already handled in constructor
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        // addToCart("Spring Onion", (Integer)jSpinner5.getValue(), jLabel8.getIcon()); // Already handled in constructor
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // addToCart("Pechay", (Integer)jSpinner6.getValue(), jLabel1.getIcon()); // Already handled in constructor
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Produce3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Produce3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Produce3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Produce3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Produce3().setVisible(true);
            }
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
    private javax.swing.JLabel jLabel1;
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
