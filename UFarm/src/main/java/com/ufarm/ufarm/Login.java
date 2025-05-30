package com.ufarm.ufarm;

/**
 *
 * @author Jasmine
 */

import java.awt.*;
import javax.swing.*;

public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        
        GoToSignup.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GoToSignupMouseClicked(evt);  
            }
        });        
        
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeMouseClicked(evt);  
            }
        });  
        
        Feedback.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FeedbackMouseClicked(evt);  
            }
        });  
        
        setupLabelHoverEffect(Home, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));  
        setupLabelHoverEffect(Acc, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));
        setupLabelHoverEffect(Feedback, java.awt.Color.WHITE, new java.awt.Color(255, 255, 150));  
                      
        setupPanelHoverEffects(HomePanel, new Color(33, 113, 0), new Color(50, 140, 0));
        setupPanelHoverEffects(FeedbackPanel, new Color(33, 113, 0), new Color(50, 140, 0));
        
    }
    
    private void GoToSignupMouseClicked(java.awt.event.MouseEvent evt) {
        new Signup().setVisible(true);  
        this.dispose();
    }
    
    private void HomeMouseClicked(java.awt.event.MouseEvent evt) {
        new Home().setVisible(true);  
        this.dispose();
    }
    
    private void FeedbackMouseClicked(java.awt.event.MouseEvent evt) {
        new Feedback().setVisible(true);  
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
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
     
    private void initComponents() {

        HI = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        Namefield = new javax.swing.JTextField();
        Pass = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        Remember = new javax.swing.JCheckBox();
        Login = new javax.swing.JButton();
        GoToSignup = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        HI.setFont(new java.awt.Font("Georgia", 1, 30));  
        HI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        HI.setText("Hi, please log in. ");

        Name.setFont(new java.awt.Font("Georgia", 0, 18));  
        Name.setText("Email:");

        Namefield.setForeground(new java.awt.Color(35, 101, 51));
        Namefield.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 101, 51), 2, true));

        Pass.setFont(new java.awt.Font("Georgia", 0, 18));  
        Pass.setText("Password:");

        jPasswordField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 101, 51), 2, true));

        Remember.setFont(new java.awt.Font("Georgia", 0, 12));  
        Remember.setText("Remember me?");
        Remember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RememberActionPerformed(evt);
            }
        });

        Login.setBackground(new java.awt.Color(35, 101, 51));
        Login.setFont(new java.awt.Font("Georgia", 0, 24));  
        Login.setForeground(new java.awt.Color(255, 255, 255));
        Login.setText("LOG IN");
        Login.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });

        GoToSignup.setFont(new java.awt.Font("Georgia", 0, 16));  
        GoToSignup.setText("Don't have an account yet? Sign up.");

        Dash.setBackground(new java.awt.Color(35, 101, 51));

        Title.setFont(new java.awt.Font("Georgia", 1, 36));  
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setText("UFarm");

        HomePanel.setBackground(new java.awt.Color(33, 113, 0));

        Home.setFont(new java.awt.Font("Helvetica Neue", 0, 24));  
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

        AccPanel.setBackground(new java.awt.Color(102, 51, 0));

        Acc.setFont(new java.awt.Font("Helvetica Neue", 1, 24));  
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

        ProducePanel.setBackground(new java.awt.Color(35, 101, 51));

        Produce.setBackground(new java.awt.Color(153, 153, 153));
        Produce.setFont(new java.awt.Font("Helvetica Neue", 0, 24));  
        Produce.setForeground(new java.awt.Color(153, 153, 153));
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

        FarmPanel.setBackground(new java.awt.Color(35, 101, 51));

        Farm.setFont(new java.awt.Font("Helvetica Neue", 0, 24));  
        Farm.setForeground(new java.awt.Color(153, 153, 153));
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

        CartPanel.setBackground(new java.awt.Color(35, 101, 51));

        CartMenu.setFont(new java.awt.Font("Helvetica Neue", 0, 24));  
        CartMenu.setForeground(new java.awt.Color(153, 153, 153));
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
            .addGroup(CartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CartMenu)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        MyLove.setFont(new java.awt.Font("Helvetica Neue", 0, 10));  
        MyLove.setForeground(new java.awt.Color(204, 204, 204));
        MyLove.setText("Agco, Jasmine Jane @2025");

        FeedbackPanel.setBackground(new java.awt.Color(33, 113, 0));

        Feedback.setFont(new java.awt.Font("Helvetica Neue", 0, 24));  
        Feedback.setForeground(new java.awt.Color(255, 255, 255));
        Feedback.setText("Feedback");

        javax.swing.GroupLayout FeedbackPanelLayout = new javax.swing.GroupLayout(FeedbackPanel);
        FeedbackPanel.setLayout(FeedbackPanelLayout);
        FeedbackPanelLayout.setHorizontalGroup(
            FeedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FeedbackPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Feedback)
                .addGap(52, 52, 52))
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
                .addComponent(CartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FeedbackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE)
                .addComponent(MyLove)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Dash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(278, 278, 278)
                                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(217, 217, 217)
                                .addComponent(HI))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(GoToSignup)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Name)
                            .addComponent(Namefield, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Pass)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Remember))
                        .addContainerGap(146, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(HI)
                .addGap(51, 51, 51)
                .addComponent(Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Namefield, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Pass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Remember)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Login)
                .addGap(35, 35, 35)
                .addComponent(GoToSignup)
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addComponent(Dash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    } 

    private void RememberActionPerformed(java.awt.event.ActionEvent evt) { 
         
    } 

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) { 
        String email = Namefield.getText().trim();  
        String password = new String(jPasswordField1.getPassword());
        
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        AccountDatabase db = new AccountDatabase();
        if (db.login(email, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE); 
            new Produce().setVisible(true);  
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
        }        
    } 

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
         
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http: 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

     
    private javax.swing.JLabel Acc;
    private javax.swing.JPanel AccPanel;
    private javax.swing.JLabel CartMenu;
    private javax.swing.JPanel CartPanel;
    private javax.swing.JPanel Dash;
    private javax.swing.JLabel Farm;
    private javax.swing.JPanel FarmPanel;
    private javax.swing.JLabel Feedback;
    private javax.swing.JPanel FeedbackPanel;
    private javax.swing.JLabel GoToSignup;
    private javax.swing.JLabel HI;
    private javax.swing.JLabel Home;
    private javax.swing.JPanel HomePanel;
    private javax.swing.JButton Login;
    private javax.swing.JLabel MyLove;
    private javax.swing.JLabel Name;
    private javax.swing.JTextField Namefield;
    private javax.swing.JLabel Pass;
    private javax.swing.JLabel Produce;
    private javax.swing.JPanel ProducePanel;
    private javax.swing.JCheckBox Remember;
    private javax.swing.JLabel Title;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
     
}
