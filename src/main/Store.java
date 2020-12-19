package main;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Store extends JPanel {
    private Hashtable<String, Product> productList;
    private Status user;
    private JLabel remainMoney;
    private JButton exitButton;
    private GameMap map;

    public Store(GameMap mp) {
        System.out.println(mp.getClass());
        map = mp;
        productList = new Hashtable<String, Product>();
        productList.put("addAttack1", new Product("addAttack1", 50, this));
        productList.put("coffee", new Product("coffee", 50, this));
        productList.put("subAttack", new Product("subAttack", 50, this));

        productList.put("delayOne", new Product("delayOne", 1000, this));
        productList.put("redblue", new Product("redblue", 200, this));
        productList.put("addAttack2", new Product("addAttack2", 200, this));

        productList.put("special2", new Product("special2", 300, this));
        productList.put("special1", new Product("special1", 300, this));
        productList.put("special3", new Product("special3", 300, this));
        productList.put("special4", new Product("special4", 300, this));

        remainMoney = new JLabel("0");

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        formProperLayout();

        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(false);
    }

    /**
     * This method should called when clicking the lunch buttom of store.
     * 
     * @user The status of user
     */
    public void init(Status user) {
        this.user = user;
        map.setVisible(false);
        productList.forEach((k, v) -> {
            v.reset();
        });
        remainMoney.setText(String.valueOf(user.getMoney()));
        setVisible(true);
        System.out.println("init");
    }

    /**
     * This method should be called when exiting the store.
     */
    public void exit() {
        setVisible(false);
        map.setVisible(true);
        map.requestFocusInWindow();
    }

    public void buy(Product p, Integer n) {
        if (n * p.getPrice() > user.getMoney()) {
            JFrame warning = new JFrame();
            JLabel text = new JLabel("Not enough money!!");
            text.setFont(new Font(text.getFont().getFamily(), text.getFont().getStyle(), 30));
            warning.add(text);
            warning.setVisible(true);
            warning.pack();
            return;
        }
        user.updateMoney(-n * p.getPrice());
        remainMoney.setText(String.valueOf(user.getMoney()));
        if (user.skill.contains(p.getName()))
            user.skill.put(p.getName(), user.skill.get(p.getName()) + n);
        else
            user.skill.put(p.getName(), n);
    }

    private void formProperLayout() {
        JLabel l = new JLabel("Money:");

        JLabel img = new JLabel(new ImageIcon("./image/store/sale.gif"));
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        /* Horizontal */
        GroupLayout.ParallelGroup hg1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hg1.addComponent(productList.get("addAttack1"));
        hg1.addComponent(productList.get("delayOne"));
        hg1.addComponent(productList.get("special1"));

        GroupLayout.ParallelGroup hg2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hg2.addComponent(productList.get("coffee"));
        hg2.addComponent(productList.get("redblue"));
        hg2.addComponent(productList.get("special2"));

        GroupLayout.ParallelGroup hg3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hg3.addComponent(productList.get("subAttack"));
        hg3.addComponent(productList.get("addAttack2"));
        hg3.addComponent(productList.get("special4"));

        GroupLayout.ParallelGroup hg4 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hg4.addComponent(productList.get("special3"));

        GroupLayout.ParallelGroup hg5 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        hg5.addGroup(layout.createSequentialGroup().addComponent(l).addComponent(remainMoney));
        hg5.addComponent(img);
        hg5.addComponent(exitButton);

        layout.setHorizontalGroup(
                layout.createSequentialGroup().addGroup(hg1).addGroup(hg2).addGroup(hg3).addGroup(hg4).addGroup(hg5));

        /* Vertical */
        GroupLayout.ParallelGroup vg1 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vg1.addComponent(productList.get("addAttack1"));
        vg1.addComponent(productList.get("coffee"));
        vg1.addComponent(productList.get("subAttack"));
        vg1.addComponent(l);
        vg1.addComponent(remainMoney);

        GroupLayout.ParallelGroup vg2 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vg2.addComponent(productList.get("delayOne"));
        vg2.addComponent(productList.get("redblue"));
        vg2.addComponent(productList.get("addAttack2"));
        vg2.addComponent(img);

        GroupLayout.ParallelGroup vg3 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vg3.addComponent(productList.get("special1"));
        vg3.addComponent(productList.get("special2"));
        vg3.addComponent(productList.get("special4"));
        vg3.addComponent(productList.get("special3"));
        vg3.addComponent(exitButton);

        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(vg1).addGroup(vg2).addGroup(vg3));
    }

}
