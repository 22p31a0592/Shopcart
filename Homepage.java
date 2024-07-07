import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.Statement;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

public class Homepage{
    public static JFrame frame;
    public static int value;
   // public static void setpic();
    public static String itemname3,itemprice;
    public static String comboboxQuery;
    public static int[] quantities = new int[12]; // Array to store quantities for each product
   // ADDTOCAART FUNCTION
    public static void addButtonAddToCart() {
        int x = 60, y = 210, width = 120, height = 30;
        for (int i = 0; i < quantities.length; i++) {
            JButton button = new JButton("ADD TO CART");
            button.setBounds(x, y, width, height);
            frame.add(button);

            // Add ActionListener to handle button click
            int index = i; // Need to use a final or effectively final variable in the anonymous class
            String a[]={"Cake","Chocolate","Lays","Popcorn","Nachos","Muffins","Chikki","Cokkies","Lollipop","Kurkure","Chewing gums","Mad angles"};
            String price[]={"5","40","10","100","20","10","30","50","10","10","10","20"};
            String picpath[]={"CAKE.jpg" ,"CHOCOLATE.jpg","LAY.jpg","POPCORN.jpg","NACHOS.jpg","MUFFINS.jpg","CHIKKI.jpg","UNIBIC.jpg","LOLLIPOP.jpg","KURKURE.jpg","GUMS.jpg","MADANGLES.jpg"};
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Access the quantity of the corresponding product and perform actions
                    int quantity = quantities[index];
                    value=quantity;
                    
                    if(quantity==0)
                    {
                        JOptionPane.showMessageDialog(frame, "No Item selected");
                    }
                    else{   
                    comboboxQuery="insert into itemlist values("+"'"+a[index]+"'"+","+price[index]+","+Integer.toString(quantity)+","+"'"+picpath[index]+"'"+")";
                    JOptionPane.showMessageDialog(frame, quantity +" "+ a[index] + " added to your cart.");
                    }
                
                    itemname3=a[index];
                    itemprice=price[index];
                    new DataBaseConnection();
                   // value+=Integer.toString(quantity);
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
    static JLabel productName(String itemName,int x,int y,int width,int height)
    {
        String k=itemName;
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
            frame.add(spinner);

            // Add ChangeListener to update quantity value
            int index = i; // Need to use a final or effectively final variable in the anonymous class
            spinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    quantities[index] = (int) spinner.getValue();
                    //System.out.println(quantities[index]);
                   // value=quantities[index];
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

    public static void main(String[] args) {
        // Create a JFrame
     frame = new JFrame("SHOPCART");
        frame.setResizable(true);
        frame.setSize(950, 900);
       // frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon img=new ImageIcon("shopcart.png");
        frame.setIconImage(img.getImage());

        // Create a JPanel to hold the square box//  bOX1
        JPanel panel1 = new JPanel() {
          
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int size = 200;
                //box1
                g.setColor(Color.white);
                g.fillRect(20, 30, size, size+20);
                g.drawImage(new ImagePanel("CAKE.jpg").image,20,30,size,120,this);
                borderWidth(g, 20, 30);
                //box2;
                g.setColor(Color.white);
                g.fillRect(250,30,size,size+20);
                g.drawImage(new ImagePanel("CHOCOLATE.jpg").image,250,30,size,100,this);
                borderWidth(g,250,30);
               // g.drawRect(20, 30, size, size+20);
                
                //box3
                g.setColor(Color.white);
                g.fillRect(480,30,size,size+20);
                g.drawImage(new ImagePanel("LAYS.jpg").image,480,30,size,100,this);
                borderWidth(g,480,30);
                //box4
                g.setColor(Color.white);
                g.fillRect(710,30,size,size+20);
                g.drawImage(new ImagePanel("POPCORN.jpg").image,710,30,size,100,this);
                borderWidth(g, 710,30);
                //box5
                g.setColor(Color.white);
                g.fillRect(20, 260, size, size+20);
                g.drawImage(new ImagePanel("NACHOS.jpg").image,20,260,size,100,this);
                borderWidth(g, 20, 260);
                //box6
                g.setColor(Color.white);
                g.fillRect(250,260,size,size+20);
                g.drawImage(new ImagePanel("MUFFINS.jpg").image,250,260,size,100,this);
                borderWidth(g, 250, 260);
                //box7
                g.setColor(Color.white);
                g.fillRect(480,260,size,size+20);
                g.drawImage(new ImagePanel("CHIKKI.jpg").image,480,260,size,100,this);
                borderWidth(g, 480, 260);
                //box8
                g.setColor(Color.white);
                g.fillRect(710,260,size,size+20);
                g.drawImage(new ImagePanel("UNIBIC.jpg").image,710,260,size,100,this);
                borderWidth(g, 710, 260);
                //box9
                g.setColor(Color.white);
                g.fillRect(20, 490, size, size+20);
                g.drawImage(new ImagePanel("LOLLIPOP.jpg").image,20,490,size,100,this);
                borderWidth(g, 20, 490);
                //box10
                g.setColor(Color.white);
                g.fillRect(250,490,size,size+20);
                g.drawImage(new ImagePanel("KURKURE.jpg").image,250,490,size,100,this);
                borderWidth(g, 250, 490);
                //box11
                g.setColor(Color.white);
                g.fillRect(480,490,size,size+20);
                g.drawImage(new ImagePanel("GUMS.jpg").image,480,490,size,100,this);
                borderWidth(g, 480, 490);
                //box12
                g.setColor(Color.white);
                g.fillRect(710,490,size,size+20);
                g.drawImage(new ImagePanel("MADANGLES.jpg").image,710,490,size,100,this);   
                borderWidth(g, 710, 490);
            }
        };
        //buttonaddtocart
          // Create and add "Add to Cart" buttons for each product box
          addButtonAddToCart();
        //productname
        frame.add(productName("Cake", 90, 130, 50, 20));
        frame.add(productName("Chocolate", 310, 130, 100, 20));
        frame.add(productName("Lays", 550, 130, 40, 20));
        frame.add(productName("Popcorn", 780, 130, 80, 20));
        frame.add(productName("Nachos", 90, 360, 80, 20));
        frame.add(productName("Muffins",320, 360, 80, 20));
        frame.add(productName("Chikki", 550, 360, 80, 20));
        frame.add(productName("Cokkies", 780, 360, 80, 20));
        frame.add(productName("LolliPop", 90, 590, 80, 20));
        frame.add(productName("Kurkure", 320, 590, 80, 20));
        frame.add(productName("Chewing gums", 520, 590, 130, 20));
        frame.add(productName("Mad angel", 770, 590, 100, 20));
        //productprice
        frame.add(productPrice("Rs.5", 30, 150, 140, 20));
        frame.add(productPrice("Rs.40", 260, 150, 140, 20));
        frame.add(productPrice("Rs.10", 490, 150, 140, 20));
        frame.add(productPrice("Rs.100", 720, 150, 140, 20));
        frame.add(productPrice("Rs.20", 30, 380, 140, 20));
        frame.add(productPrice("Rs.10", 260, 380, 140, 20));
        frame.add(productPrice("Rs.30", 490, 380, 140, 20));
        frame.add(productPrice("Rs.50", 720, 380, 140, 20));
        frame.add(productPrice("Rs.10", 30, 610, 140, 20));
        frame.add(productPrice("Rs.10", 260, 610, 140, 20));
        frame.add(productPrice("Rs.10", 490, 610, 140, 20));
        frame.add(productPrice("Rs.20", 720, 610, 140, 20));
        //productquantity
        frame.add(productQuantity(260 ,178, 140, 20));
        frame.add(productQuantity(490, 178, 140, 20));
        frame.add(productQuantity(720, 178, 140, 20));
        frame.add(productQuantity( 30, 408, 140, 20));
        frame.add(productQuantity(260, 408, 140, 20));
        frame.add(productQuantity( 490, 408, 140, 20));
        frame.add(productQuantity(720, 408, 140, 20));
        frame.add(productQuantity( 30, 638, 140, 20));
        frame.add(productQuantity( 260, 638, 140, 20));
        frame.add(productQuantity( 490, 638, 140, 20));
        frame.add(productQuantity( 720, 638, 140, 20));
        frame.add(productQuantity( 30, 178, 140, 20));
        //QuantitySpinner
        addQuantitySpinners();

        //cart button 
        JButton button=new JButton("MY CART");
        button.setBounds(600,750,120,30);
        button.setBackground(Color.black);
        button.setForeground(Color.white);


        //exit button from the frame
        // JButton button2=new JButton("EXIT");
        // button2.setBounds(600,750,120,30);
        // button2.setBackground(Color.black);
        // button2.setForeground(Color.white);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when button is clicked
                Secondpage second=new Secondpage();// Close the frame
                second.display();
                frame.dispose();
            }
        });
       
        JButton button2=new JButton("EXIT");
        button2.setBounds(800,750,120,30);
        button2.setBackground(Color.black);
        button2.setForeground(Color.white);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when button is clicked
                frame.dispose(); // Close the frame
            }
        });

        //Mobilephone frame

        JButton mobile=new JButton("PHONES");
        mobile.setBounds(400,750,120,30);
        mobile.setBackground(Color.black);
        mobile.setForeground(Color.white);
        mobile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when button is clicked
                Mobilephone mobilephone=new Mobilephone();
                mobilephone.display();
                frame.dispose(); // Close the frame
            }
        });
        frame.add(mobile);
        // Create and add clear button
        JButton clearButton = new JButton("Clear All");
        clearButton.setBounds(60,750,120,30);
        clearButton.setBackground(Color.black);
        clearButton.setForeground(Color.white);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Iterate over all quantity spinners and set their values to zero
                for (Component component : frame.getContentPane().getComponents()) {
                    if (component instanceof JSpinner) {
                        ((JSpinner) component).setValue(0);
                    }
                }
                JOptionPane.showMessageDialog(frame,"All Qunantites clear");
              }
            });
            JButton groceryButton = new JButton("GROCERY");
        groceryButton.setBounds(200,750,120,30);
        groceryButton.setBackground(Color.black);
        groceryButton.setForeground(Color.white);
        groceryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Iterate over all quantity spinners and set their values to zero
                Grocery grocery=new Grocery();
              //  comboboxQuery=grocery.combobox;
                grocery.display();
                frame.dispose();
              }
            });
            frame.add(groceryButton);
       frame.add(clearButton);
       frame.add(button);
        frame.add(button2);
        frame.add(panel1);
        frame.setVisible(true);
        //System.out.println(value);
        //Statement sc=con.creapicatement();
    }
    
}
