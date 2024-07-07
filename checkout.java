import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.*;
public class checkout extends Secondpage{
    void print()
    {
        JFrame checkout=new JFrame();
        checkout.setTitle("CHECKOUT");
        checkout.setSize(500,500);
        checkout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkout.setVisible(true);
        checkout.setLayout(null);
        checkout.setResizable(false);
        int y=10;
        Double total=0.0;
        for(int i=0;i<cartItems.size();i++)
        {
            JLabel label=new JLabel();
            label.setText(cartItems.get(i)+":");
            JLabel label2=new JLabel();
            Double price=cartQuantity.get(i)*cartPrice.get(i);
            total+=price;
            label2.setText(Integer.toString(cartQuantity.get(i))+" X "+Double.toString(cartPrice.get(i))+"  =  "+Double.toString(price));
            label2.setBounds(150,y,200,50);
            label.setBounds(10,y,150,50);
            checkout.add(label);
            checkout.add(label2);
            y=y+20;
        }
        JLabel label=new JLabel("Total "+"                  =              "+Double.toString(total));
        label.setBounds(20,y+20,200,100);
        checkout.add(label);
        JButton button=new JButton("ORDER NOW");
        button.setBounds(100,270,120,30);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when button is clicked
                JOptionPane.showMessageDialog(checkout,"OK YOUR ORDER DONE ");
                checkout.remove(label);
                checkout.dispose();
                frame.setVisible(true);
                //Close the frame
            }
        });
        checkout.add(button);
        //home button
        JButton button4=new JButton("HOME");
        button4.setBounds(30,350,120,30);
        button4.setBackground(Color.BLACK);
        button4.setForeground(Color.white);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when button is clicked
                checkout.dispose();
                frame.setVisible(true);
                // Close the frame
            }
        });
        //back button
        checkout.add(button4);
        JButton button3=new JButton("BACK");
        button3.setBounds(200,350,120,30);
        button3.setBackground(Color.black);
        button3.setForeground(Color.white);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkout.dispose();
                Secondpage secondpage=new Secondpage();
                secondpage.display();
                // Action to perform when button is clicked
                //frame2.setVisible(true);
                // Close the frame
            }
        });
        checkout.add(button3);

    }
}
