import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Component;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.*;

public class Secondpage extends Homepage {
    public static String Query;
    public static int k=0;
    public static ArrayList<String> cartItems=new ArrayList<>();
    public static ArrayList<Double> cartPrice=new ArrayList<>();
    public static ArrayList<Integer> cartQuantity=new ArrayList<>();
    JFrame frame2=new JFrame();
    public void display() {
        frame2.setTitle("MY CART");
        //setLayout(null);
        frame2.setSize(950, 900);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button2=new JButton("EXIT");
        button2.setBounds(600,750,120,30);
        button2.setBackground(Color.black);
        button2.setForeground(Color.white);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when button is clicked
                 frame2.dispose(); // Close the frame
            }
        });
        frame2.add(button2);
        JButton button3=new JButton("BACK");
        button3.setBounds(400,750,120,30);
        button3.setBackground(Color.black);
        button3.setForeground(Color.white);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when button is clicked
                frame.setVisible(true);
                frame2.dispose();
                // Close the frame
            }
        });
        //checkout button
        JButton button4=new JButton("CHECKOUT");
        button4.setBounds(800,750,120,30);
        button4.setBackground(Color.black);
        button4.setForeground(Color.white);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when button is clicked
                checkout check=new checkout();
                check.print();
                frame2.dispose();
                // Close the frame
            }
        });
        frame2.add(button4);
        frame2.add(button3);
        ImageIcon img = new ImageIcon("shopcart.png");
        frame2.setIconImage(img.getImage());
        JButton button5=new JButton("REMOVE ITEMS");
        button5.setBounds(100,750,150,30);
        button5.setBackground(Color.black);
        button5.setForeground(Color.white);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when button is clicked
                 Query="truncate itemlist";
                 k++;
                 JOptionPane.showMessageDialog(frame2, "OK");
                 frame2.dispose();
                 frame.setVisible(true);
                // Close the frame
            }
        });
        frame2.add(button5);
   JPanel panel=new JPanel();
   fetchData(panel);
   frame2.add(panel, BorderLayout.CENTER);
       
        frame2.setVisible(true);
    }
 


    void fetchData(JPanel panel) {
        try {
            String url = "jdbc:mysql://localhost:3306/shopcart";
            String user = "root";
            String pass = "0000";
            Connection con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                System.out.println("Successfully connected");

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM itemlist");
               int x=20;
               int y=30;
               int i=0;
               if(k!=0){
               st.executeUpdate(Query);
               }
                while (rs.next()) {
                    String itemName = rs.getString("itemname");
                    
                    int quantity = rs.getInt("quantity");
           
                    int price = rs.getInt("price");
               
                    String path=  rs.getString("imagepath");
                    JPanel box = createSquareBox(itemName ,quantity, price,path);
                    //JButton but=new JButton("click me");
                    i++;
                    if(i==4)
                    {
                        x=20;
                        y=y+230;
                        i=0;
                    }
                    else{
                        x+=230;
                    }
                     //but.setBounds(90, 90, 50, 50);
                   // box.add(but);
                    panel.add(box);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    JPanel createSquareBox(String itemName, int quantity, double price,String path) {
        JPanel box = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int size = 200;
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, size, size+20); // Draw white background
                // Draw item name, quantity, and price
                g.setColor(Color.BLACK);
                // Load and draw the image
                try {
                    BufferedImage image = ImageIO.read(new File(path));
                    g.drawImage(image, 10, 0, size - 20, size - 90, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.drawString("Item Name: " + itemName, 10, 125);
                g.drawString("Quantity: " + quantity, 10, 145);
                g.drawString("Price: $" + price, 10, 165);
                borderWidth(g,0,0);
                 JButton butt=new JButton();
                 butt.setBounds(10, 180, 50, 50);
                //box.add(butt);
            }
        };
        JCheckBox checkBox = new JCheckBox(); // Create a checkbox
          // Add an ActionListener to the box to track selected items
          checkBox.addActionListener(e -> {
            if (checkBox.isSelected()) {
                cartItems.add(itemName);
                cartPrice.add(price);
                cartQuantity.add(quantity);

            } 
            
        });
        box.add(checkBox);
        // Set the size of the panel
        box.setPreferredSize(new Dimension(200, 200));
        return box;
    }

   

}