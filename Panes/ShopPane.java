package Panes;

import Blocks.*;

import javax.swing.*;
import java.awt.*;

public class ShopPane extends JPanel {

    public static final int OFFER_COUNT = 16;

    public ShopBlock ShopOffer[];

    public JButton Outcome;

    public ShopPane(){

        this.setLayout(new BorderLayout());

        JPanel Right = new JPanel(new GridLayout(OFFER_COUNT,1));

        Image X = new ImageIcon(getClass().getResource("/Stuff/Shopkeeper.png")).getImage();
        X = X.getScaledInstance(640,640, Image.SCALE_FAST);

        JImagePanel Container = new JImagePanel(X);

        Outcome = new JButton(" ");
        Outcome.setFocusPainted(false);
        Outcome.setBorder(null);

        ShopOffer = new ShopBlock[OFFER_COUNT];

        ShopOffer[0] = new ShopBlock("Health Potion ", new ImageIcon(getClass().getResource("/Stuff/Potions/RPotion.png")).getImage());
        ShopOffer[1] = new ShopBlock("Mana Potion ", new ImageIcon(getClass().getResource("/Stuff/Potions/BPotion.png")).getImage());
        ShopOffer[2] = new ShopBlock("Stamina Potion ", new ImageIcon(getClass().getResource("/Stuff/Potions/GPotion.png")).getImage());
        ShopOffer[3] = new ShopBlock("Strength Potion ", new ImageIcon(getClass().getResource("/Stuff/Potions/PPotion.png")).getImage());
        ShopOffer[4] = new ShopBlock("Sorcerer Potion ", new ImageIcon(getClass().getResource("/Stuff/Potions/PPotion.png")).getImage());
        ShopOffer[5] = new ShopBlock("Resistance Potion ", new ImageIcon(getClass().getResource("/Stuff/Potions/PPotion.png")).getImage());

        for (int i = 6; i < (OFFER_COUNT); i++){
            ShopOffer[i] = new ShopBlock();
        }
        for (int i = 0; i < OFFER_COUNT ; i++){
            Right.add(ShopOffer[i]);
        }

        this.add(Container,BorderLayout.CENTER);
        this.add(Right,BorderLayout.EAST);
        this.add(Outcome,BorderLayout.SOUTH);

    }
}
