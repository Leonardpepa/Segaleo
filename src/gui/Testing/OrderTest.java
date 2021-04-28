package gui.Testing;

import order.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


//This is a simple Testing GUI.It was created for testing the order methods
public class OrderTest extends JFrame {
    private JButton addButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton calcButton;
    private JButton couponButton;

    private JList productList;
    private JList myOrder;

    private DefaultListModel productModel;
    private DefaultListModel orderModel;

    private JTextField priceText;
    private JTextField couponField;

    private JPanel panel;

    private Order order = new Order();
    private ArrayList<String> products;


    public OrderTest() {
        panel = new JPanel();

        products = new ArrayList<>();

        products.add("Chicken");
        products.add("Salad");
        products.add("Cheesecake");

        productList = new JList();
        productModel = new DefaultListModel();

        for(String product: products)
            productModel.addElement(product);
        productList.setModel(productModel);

        //Δεν εμφανιζεται στην ααρχη βαριομουν να το κανω να φαινεται απο την αρχη αμα κανεις την προσθηκη προιοντος εμφανιζεται
        myOrder = new JList();
        orderModel = new DefaultListModel();

        addButton = new JButton("Add Product");
        deleteButton = new JButton("Remove Product");
        clearButton = new JButton("Clear Order");

        ButtonListener listener = new ButtonListener();
        addButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        clearButton.addActionListener(listener);


        priceText = new JTextField("This is the price field");
        calcButton = new JButton("Calculate cost");
        calcButton.addActionListener(listener);

        couponField = new JTextField("Enter Coupon");
        couponButton = new JButton("Apply Coupon");
        couponButton.addActionListener(listener);


        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(clearButton);
        panel.add(productList);
        panel.add(myOrder);
        panel.add(calcButton);
        panel.add(priceText);
        panel.add(couponField);
        panel.add(couponButton);


        setContentPane(panel);
        setTitle("TestingOrderMethods");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }


    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(addButton)) {
                //Κανεις κλικ σε ενα απο τα προιοντα τις λιστας και μετα πατας το addProduct αλλιως σε βγαζει null ερρορ
                String name = (String)productList.getSelectedValue();

                Product p = new Food(name, "this is a disc", 20.0, "this is a path");
                order.addProduct(p);
                updateModel();
            }
            else if(e.getSource().equals(deleteButton)) {
                String name = (String) productList.getSelectedValue();

                Product p = order.isProduct(name);
                order.deleteProduct(p);

                updateModel();
            }
            else if(e.getSource().equals(clearButton)){
                order.clearOrder();
                updateModel();

                priceText.setText("0");
            }
            else if(e.getSource().equals(calcButton)){
                String sum = Double.toString(order.calcCost());

                priceText.setText(sum);
            }
            else {
                String sum = Double.toString(order.calcCost());

                String code = couponField.getText();
                Coupon c = new Coupon(code);
                sum = Double.toString(order.calcDiscount(c));

                priceText.setText(sum);
            }
        }
    }

    private void updateModel() {
        orderModel.clear();

        ArrayList<Product> temp = order.getProducts();

        for(Product product: temp)
            orderModel.addElement(product);

        myOrder.setModel(orderModel);
    }
}
