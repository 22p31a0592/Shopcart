import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mobilephone extends Homepage{
    public static JFrame mobileFrame;
    public static int value;
    public static String itemname1,itemprice1;
    public static String combobox;
    public static int[] quantities = new int[12]; // Array to store quantities for each product
   // ADDTOCAART FUNCTION
    public static void addButtonAddToCart() {
        int x = 60, y = 210, width = 120, height = 30;
        for (int i = 0; i < quantities.length; i++) {
            JButton button = new JButton("ADD TO CART");
            button.setBounds(x, y, width, height);
            mobileFrame.add(button);

            // Add ActionListener to handle button click
            int index1 = i; // Need to use a final or effectively final variable in the anonymous class
            String a1[]={"REALME 9 (5G)","OPPO","ONE PLUS","SAMSUNG","MOTO","NOTHING","VIVO","IPHONE","REALME","GALAXY","KARBON","JIO"};
            String price1[]={"30000","25000","70000","60000","12000","25000","17000","100000","21000","50000","1000","12000"};
            String picpath1[]={"REALME9.jpg" ,"OPPO.jpg","ONEPLUS.jpg","SAMSUNG.jpg","MOTO.jpg","NOTHING.jpg","VIVO.jpg","IPHONE.jpg","REALME.jpg","GALAXY.jpg","KARBON.jpg","JIO.jpg"};
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Access the quantity of the corresponding product and perform actions
                    int quantity = quantities[index1];
                    if(quantity==0)
                    {
                        JOptionPane.showMessageDialog(mobileFrame, "No Item selected");
                    }
                    else{
                        
                    combobox="insert into itemlist values("+"'"+a1[index1]+"'"+","+price1[index1]+","+Integer.toString(quantity)+","+"'"+picpath1[index1]+"'"+")";
                    comboboxQuery=combobox;
                    DataBaseConnection data=new DataBaseConnection();
                    JOptionPane.showMessageDialog(mobileFrame, quantity +" "+ a1[index1] + " added to your cart.");
                    }
                    value=quantity;
                    itemname1=a1[index1];
                    itemprice1=price1[index1];
                    
            
                }
            });

            // Adjust position for the next button
            if (i % 4 == 3) { // Move to the next row after every 4 buttons
                x = 60;
                y += 230;
            } else {
                x += 230;
            }
        }
    }
    //productname
    static JLabel productName(String itemname1,int x,int y,int width,int height)
    {
        String k=itemname1;
        JLabel textArea=new JLabel();
        textArea.setText(k);
        textArea.setFont(new Font("Serif", Font.BOLD, 18));
        textArea.setBounds(x,y,width,height);
        return textArea;
    }
    //product price;
    static JLabel productPrice(String s,int x,int y,int width,int height)
    {
        JLabel textArea=new JLabel();
        textArea.setText("Price :    "+s);
        textArea.setFont(new Font("Serif", Font.BOLD, 15));
        textArea.setBounds(x,y,width,height);
        return textArea;
    }
    //boderwidth;
    static void borderWidth(Graphics g,int x,int y)
    {
        int borderWidth = 2; 
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(borderWidth));
        g2d.drawRect(x, y, 200, 220);
    }
    //productQuan;
    static JLabel productQuantity(int x,int y,int width,int height)
    {
         JLabel jLabel=new JLabel("Quantity:    ");
         jLabel.setBounds(x, y, width, height);
         return jLabel;
    }
    //quntaity box;
    public static void addQuantitySpinners() {
        int x = 90, y = 180, width = 40, height = 20;
        for (int i = 0; i < quantities.length; i++) {
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 9, 1));
            spinner.setBounds(x, y, width, height);
            mobileFrame.add(spinner);

            // Add ChangeListener to update quantity value
            int index1 = i; // Need to use a final or effectively final variable in the anonymous class
            spinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    quantities[index1] = (int) spinner.getValue();
                    //System.out.println(quantities[index1]);
                   // value=quantities[index1];
                }
            });

            // Adjust position for the next spinner
            if (i % 4 == 3) { // Move to the next row after every 4 spinners
                x = 90;
                y += 230;
            } else {
                x += 230;
            }
        }
    }

     void display(){
        // Create a Jframe4
        mobileFrame = new JFrame("SHOPCART");
        mobileFrame.setResizable(true);
        mobileFrame.setSize(950, 900);
       // mobileFrame.setLayout(null);
        mobileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon img=new ImageIcon("shopcart.png");
        mobileFrame.setIconImage(img.getImage());

        // Create a JPanel to hold the square box//  bOX1
        JPanel panel1 = new JPanel() {
          
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int size = 200;
                //box1
                g.setColor(Color.white);
                g.fillRect(20, 30, size, size+20);
                g.drawImage(new ImagePanel("REALME9.jpg").image,20,30,size,100,this);
                borderWidth(g, 20, 30);
                //box2;
                g.setColor(Color.white);
                g.fillRect(250,30,size,size+20);
                g.drawImage(new ImagePanel("OPPO.jpg").image,250,30,size,100,this);
                borderWidth(g,250,30);
               // g.drawRect(20, 30, size, size+20);
                
                //box3
                g.setColor(Color.white);
                g.fillRect(480,30,size,size+20);
                g.drawImage(new ImagePanel("ONEPLUS.jpg").image,480,30,size,100,this);
                borderWidth(g,480,30);
                //box4
                g.setColor(Color.white);
                g.fillRect(710,30,size,size+20);
                g.drawImage(new ImagePanel("SAMSUNG.jpg").image,710,30,size,100,this);
                borderWidth(g, 710,30);
                //box5
                g.setColor(Color.white);
                g.fillRect(20, 260, size, size+20);
                g.drawImage(new ImagePanel("MOTO.jpg").image,20,260,size,100,this);
                borderWidth(g, 20, 260);
                //box6
                g.setColor(Color.white);
                g.fillRect(250,260,size,size+20);
                g.drawImage(new ImagePanel("NOTHING.jpg").image,250,260,size,100,this);
                borderWidth(g, 250, 260);
                //box7
                g.setColor(Color.white);
                g.fillRect(480,260,size,size+20);
                g.drawImage(new ImagePanel("VIVO.jpg").image,480,260,size,100,this);
                borderWidth(g, 480, 260);
                //box8
                g.setColor(Color.white);
                g.fillRect(710,260,size,size+20);
                g.drawImage(new ImagePanel("IPHONE.jpg").image,710,260,size,100,this);
                borderWidth(g, 710, 260);
                //box9
                g.setColor(Color.white);
                g.fillRect(20, 490, size, size+20);
                g.drawImage(new ImagePanel("REALME.jpg").image,20,490,size,100,this);
                borderWidth(g, 20, 490);
                //box10
                g.setColor(Color.white);
                g.fillRect(250,490,size,size+20);
                g.drawImage(new ImagePanel("GALAXY.jpg").image,250,490,size,100,this);
                borderWidth(g, 250, 490);
                //box11
                g.setColor(Color.white);
                g.fillRect(480,490,size,size+20);
                g.drawImage(new ImagePanel("KARBON.jpg").image,480,490,size,100,this);
                borderWidth(g, 480, 490);
                //box12
                g.setColor(Color.white);
                g.fillRect(710,490,size,size+20);
                g.drawImage(new ImagePanel("JIO.jpg").image,710,490,size,100,this);   
                borderWidth(g, 710, 490);
            }
        };
        //buttonaddtocart
          // Create and add "Add to Cart" buttons for each product box
          addButtonAddToCart();
        //productname
        mobileFrame.add(productName("REALME 9 (5G)", 90, 130, 50, 20));
        mobileFrame.add(productName("OPPO", 310, 130, 100, 20));
        mobileFrame.add(productName("ONE PLUS", 550, 130, 70, 20));
        mobileFrame.add(productName("SAMSUNG", 780, 130, 80, 20));
        mobileFrame.add(productName("MOTO", 90, 360, 80, 20));
        mobileFrame.add(productName("NOTHING",320, 360, 80, 20));
        mobileFrame.add(productName("VIVO", 550, 360, 80, 20));
        mobileFrame.add(productName("IPHONE", 780, 360, 80, 20));
        mobileFrame.add(productName("REALME", 90, 590, 80, 20));
        mobileFrame.add(productName("GALAXY", 320, 590, 80, 20));
        mobileFrame.add(productName("KARBONN", 540, 590, 130, 20));
        mobileFrame.add(productName("JIO", 770, 590, 100, 20));
        //productprice
        mobileFrame.add(productPrice("Rs.30000", 30, 150, 140, 20));
        mobileFrame.add(productPrice("Rs.25000", 260, 150, 140, 20));
        mobileFrame.add(productPrice("Rs.70000", 490, 150, 140, 20));
        mobileFrame.add(productPrice("Rs.60000", 720, 150, 140, 20));
        mobileFrame.add(productPrice("Rs.12000", 30, 380, 140, 20));
        mobileFrame.add(productPrice("Rs.25000", 260, 380, 140, 20));
        mobileFrame.add(productPrice("Rs.17000", 490, 380, 140, 20));
        mobileFrame.add(productPrice("Rs.100000", 720, 380, 140, 20));
        mobileFrame.add(productPrice("Rs.21000", 30, 610, 140, 20));
        mobileFrame.add(productPrice("Rs.50000", 260, 610, 140, 20));
        mobileFrame.add(productPrice("Rs.1000", 490, 610, 140, 20));
        mobileFrame.add(productPrice("Rs.12000", 720, 610, 140, 20));
        //productquantity
        mobileFrame.add(productQuantity(260 ,178, 140, 20));
        mobileFrame.add(productQuantity(490, 178, 140, 20));
        mobileFrame.add(productQuantity(720, 178, 140, 20));
        mobileFrame.add(productQuantity( 30, 408, 140, 20));
        mobileFrame.add(productQuantity(260, 408, 140, 20));
        mobileFrame.add(productQuantity( 490, 408, 140, 20));
        mobileFrame.add(productQuantity(720, 408, 140, 20));
        mobileFrame.add(productQuantity( 30, 638, 140, 20));
        mobileFrame.add(productQuantity( 260, 638, 140, 20));
        mobileFrame.add(productQuantity( 490, 638, 140, 20));
        mobileFrame.add(productQuantity( 720, 638, 140, 20));
        mobileFrame.add(productQuantity( 30, 178, 140, 20));
        //QuantitySpinner
        addQuantitySpinners();

        //cart button 
        JButton button=new JButton("MY CART");
        button.setBounds(400,750,120,30);
        // Add ActionListener to "MY CART" button


        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when button is clicked
                Secondpage secondpage=new Secondpage();
                secondpage.display();
                mobileFrame.dispose(); // Close the mobileFrame
            }
        });
        JButton button4=new JButton("HOME");
        mobileFrame.add(button4);
        button4.setBounds(240,750,120,30);
        // Add ActionListener to "MY CART" button


        button4.setBackground(Color.black);
        button4.setForeground(Color.white);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when button is clicked
                frame.setVisible(true);
                mobileFrame.dispose(); // Close the mobileFrame
            }
        });
       
        JButton button2=new JButton("EXIT");
        button2.setBounds(600,750,120,30);
        button2.setBackground(Color.black);
        button2.setForeground(Color.white);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when button is clicked
                mobileFrame.dispose(); // Close the mobileFrame
            }
        });
        // Create and add clear button
        JButton clearButton = new JButton("Clear All");
        clearButton.setBounds(100,750,120,30);
        clearButton.setBackground(Color.black);
        clearButton.setForeground(Color.white);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Iterate over all quantity spinners and set their values to zero
                for (Component component : mobileFrame.getContentPane().getComponents()) {
                    if (component instanceof JSpinner) {
                        ((JSpinner) component).setValue(0);
                    }
                }
                JOptionPane.showMessageDialog(mobileFrame,"All Qunantites clear");
              }
            });
       mobileFrame.add(clearButton);
       mobileFrame.add(button);
        mobileFrame.add(button2);
        mobileFrame.add(panel1);
        mobileFrame.setVisible(true);
        //System.out.println(value);
        //Statement sc=con1.crea pic atement();
    }
    
}
