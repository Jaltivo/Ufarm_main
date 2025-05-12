package com.ufarm.ufarm;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ProduceNewDesign extends javax.swing.JFrame {

    // Navigation panels
    private JPanel Dash;
    private JPanel HomePanel;
    private JPanel ProducePanel;
    private JPanel FarmPanel;
    private JPanel CartPanel;
    private JPanel FeedbackPanel;
    private JPanel AccountPanel;
    
    // Navigation labels
    private JLabel Title;
    private JLabel Home;
    private JLabel Produce;
    private JLabel Farm;
    private JLabel CartLabel;
    private JLabel Feedback;
    private JLabel Account;
    
    // Search components
    private JTextField searchField;
    private JLabel searchIcon;
    private JLabel cartIcon;
    
    // Pagination components
    private JLabel prevButton;
    private JLabel nextButton;
    private JLabel page1Button;
    private JLabel page2Button;
    private JLabel page3Button;
    
    // Main content
    private JPanel contentPanel;
    private JSeparator jSeparator1;
    
    private List<Product> products = new ArrayList<>();

    public ProduceNewDesign() {
        initProducts();
        initComponents();
        applyModernStyling();
    }

    private void initProducts() {
        products.add(new Product("Mulberry", "images/Mulberry.jpeg", 3.99));
        products.add(new Product("Watermelon", "images/Watermelon.jpg", 5.99));
        products.add(new Product("Eggplant", "images/Eggplant.jpg", 2.49));
        products.add(new Product("Lemon", "images/Lemon.jpg", 0.99));
        products.add(new Product("Tomato", "images/Tomato.jpg", 1.99));
        products.add(new Product("Lettuce", "images/Lettuce.jpg", 2.99));
        products.add(new Product("Cabbage", "images/Cabagge.jpeg", 1.49));
        products.add(new Product("Garlic", "images/Garlic.jpg", 0.79));
        products.add(new Product("Onion", "images/Onion.jpg", 1.29));
    }

    private void applyModernStyling() {
        styleNavPanel(HomePanel, Home);
        styleNavPanel(ProducePanel, Produce);
        styleNavPanel(FarmPanel, Farm);
        styleNavPanel(CartPanel, CartLabel);
        styleNavPanel(FeedbackPanel, Feedback);
        styleNavPanel(AccountPanel, Account);

        ProducePanel.setBackground(new Color(70, 130, 80));
        styleSearchField();
        contentPanel.setBackground(new Color(245, 245, 245));
        setupProductGrid();
        stylePagination();
    }

    private void styleNavPanel(JPanel panel, JLabel label) {
        panel.setBackground(new Color(52, 152, 80));
        panel.setBorder(new EmptyBorder(15, 0, 15, 0));
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        
        panel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                panel.setBackground(new Color(40, 112, 61));
                panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent evt) {
                if (!panel.equals(ProducePanel)) {
                    panel.setBackground(new Color(52, 152, 80));
                }
            }
        });
    }

    private void styleSearchField() {
        searchField.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        try {
            ImageIcon searchIconImage = new ImageIcon(getClass().getResource("/com/ufarm/ufarm/images/search.png"));
            searchIcon.setIcon(resizeIcon(searchIconImage, 20, 20));
        } catch (Exception e) {
            System.err.println("Error loading search icon: " + e.getMessage());
        }
        
        try {
            ImageIcon cartIconImage = new ImageIcon(getClass().getResource("/com/ufarm/ufarm/images/grocery-store.png"));
            cartIcon.setIcon(resizeIcon(cartIconImage, 25, 25));
            cartIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } catch (Exception e) {
            System.err.println("Error loading cart icon: " + e.getMessage());
        }
    }

    private void setupProductGrid() {
        contentPanel.removeAll();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        for (int i = 0; i < Math.min(products.size(), 9); i++) {
            gbc.gridx = i % 3;
            gbc.gridy = i / 3;
            contentPanel.add(createProductCard(products.get(i)), gbc);
        }
        
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createProductCard(Product product) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 220, 220), 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        card.setPreferredSize(new Dimension(180, 220));
        
        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                card.setBorder(new CompoundBorder(
                    new LineBorder(new Color(35, 101, 51), 2),
                    new EmptyBorder(10, 10, 10, 10)
                ));
            }
            public void mouseExited(MouseEvent evt) {
                card.setBorder(new CompoundBorder(
                    new LineBorder(new Color(220, 220, 220), 1),
                    new EmptyBorder(10, 10, 10, 10)
                ));
            }
        });
        
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/com/ufarm/ufarm/" + product.getImagePath()));
            imageLabel.setIcon(resizeIcon(icon, 120, 120));
        } catch (Exception e) {
            imageLabel.setText("No Image");
            imageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        }
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel priceLabel = new JLabel(String.format("$%.2f", product.getPrice()));
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton addButton = new JButton("Add to Cart");
        styleAddButton(addButton);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(addButton);
        
        card.add(imageLabel, BorderLayout.CENTER);
        card.add(infoPanel, BorderLayout.SOUTH);
        
        return card;
    }

    private void styleAddButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBackground(new Color(35, 101, 51));
        button.setForeground(Color.WHITE);
        button.setBorder(new CompoundBorder(
            new LineBorder(new Color(35, 101, 51), 1),
            new EmptyBorder(5, 15, 5, 15)
        ));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(52, 152, 80));
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(35, 101, 51));
            }
        });
    }

    private void stylePagination() {
        prevButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nextButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        page1Button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        page2Button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        page3Button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        Color paginationColor = new Color(35, 101, 51);
        
        prevButton.setForeground(paginationColor);
        nextButton.setForeground(paginationColor);
        page1Button.setForeground(Color.WHITE);
        page2Button.setForeground(paginationColor);
        page3Button.setForeground(paginationColor);
        
        page1Button.setBackground(paginationColor);
        page1Button.setOpaque(true);
        
        for (JLabel label : new JLabel[]{prevButton, nextButton, page2Button, page3Button}) {
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            label.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    label.setForeground(new Color(52, 152, 80));
                }
                public void mouseExited(MouseEvent evt) {
                    label.setForeground(paginationColor);
                }
            });
        }
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    private void initComponents() {
        // Initialize all components
        Dash = new JPanel();
        Title = new JLabel();
        jSeparator1 = new JSeparator();
        
        // Navigation panels and labels
        HomePanel = new JPanel();
        Home = new JLabel("Home");
        ProducePanel = new JPanel();
        Produce = new JLabel("Produce");
        FarmPanel = new JPanel();
        Farm = new JLabel("Farm");
        CartPanel = new JPanel();
        CartLabel = new JLabel("Cart");
        FeedbackPanel = new JPanel();
        Feedback = new JLabel("Feedback");
        AccountPanel = new JPanel();
        Account = new JLabel("Account");
        
        // Search components
        searchField = new JTextField();
        searchIcon = new JLabel();
        cartIcon = new JLabel();
        
        // Pagination components
        prevButton = new JLabel("  <  ");
        nextButton = new JLabel("  >  ");
        page1Button = new JLabel("  1  ");
        page2Button = new JLabel("  2  ");
        page3Button = new JLabel("  3  ");
        
        // Main content panel
        contentPanel = new JPanel();
        
        // Main frame setup
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("UFarm - Produce");
        setMinimumSize(new Dimension(1024, 768));

        // Dashboard panel (left sidebar)
        Dash.setBackground(new Color(40, 112, 61));
        Dash.setPreferredSize(new Dimension(220, 768));

        // Title and separator
        Title.setFont(new Font("Georgia", Font.BOLD, 36));
        Title.setForeground(Color.WHITE);
        Title.setText("UFarm");
        Title.setBorder(new EmptyBorder(20, 0, 20, 0));

        jSeparator1.setForeground(Color.WHITE);

        // Add labels to navigation panels
        HomePanel.add(Home);
        ProducePanel.add(Produce);
        FarmPanel.add(Farm);
        CartPanel.add(CartLabel);
        FeedbackPanel.add(Feedback);
        AccountPanel.add(Account);

        // Footer
        JLabel footerLabel = new JLabel("Agco, Jasmine Jane @2025");
        footerLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 10));
        footerLabel.setForeground(new Color(204, 204, 204));

        // Dashboard layout
        GroupLayout DashLayout = new GroupLayout(Dash);
        Dash.setLayout(DashLayout);
        DashLayout.setHorizontalGroup(
            DashLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(DashLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DashLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(Title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HomePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ProducePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FarmPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CartPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FeedbackPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AccountPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(footerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        DashLayout.setVerticalGroup(
            DashLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(DashLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Title)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(HomePanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(ProducePanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(FarmPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(CartPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(FeedbackPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(AccountPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(footerLabel)
                .addContainerGap())
        );

        // Search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(240, 240, 240));
        searchPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel availableLabel = new JLabel("Available Produce");
        availableLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        availableLabel.setForeground(new Color(35, 101, 51));

        searchField.setPreferredSize(new Dimension(250, 30));

        GroupLayout searchPanelLayout = new GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addComponent(availableLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchIcon)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cartIcon)
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(availableLabel)
                .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(searchIcon)
                .addComponent(cartIcon))
        );

        // Pagination panel
        JPanel paginationPanel = new JPanel();
        paginationPanel.setBackground(new Color(245, 245, 245));
        paginationPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        paginationPanel.add(prevButton);
        paginationPanel.add(page1Button);
        paginationPanel.add(page2Button);
        paginationPanel.add(page3Button);
        paginationPanel.add(nextButton);

        // Main content layout
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(new JPanel()), BorderLayout.CENTER); // Placeholder
        contentPanel.add(paginationPanel, BorderLayout.SOUTH);

        // Main frame layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(Dash, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        pack();
    }

    private class Product {
        private String name;
        private String imagePath;
        private double price;

        public Product(String name, String imagePath, double price) {
            this.name = name;
            this.imagePath = imagePath;
            this.price = price;
        }

        public String getName() { return name; }
        public String getImagePath() { return imagePath; }
        public double getPrice() { return price; }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Button.arc", 20);
            UIManager.put("Component.arc", 20);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            ProduceNewDesign frame = new ProduceNewDesign();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
