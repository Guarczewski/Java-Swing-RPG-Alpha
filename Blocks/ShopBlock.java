package Blocks;

import javax.swing.*;
import java.awt.*;

public class ShopBlock extends JPanel {

    String Name;

    Image Icon;

    public JTextField Amount;
    public JLabel DisplayName;
    public JButton Confirm;
    public JImagePanel DisplayIcon;

    public ShopBlock(){

        this.setLayout(new GridLayout(0,4));

        Name = "Unknown Potion";
               new ImageIcon(getClass().getResource("/Stuff/Potions/PPotion.png")).getImage();
        Icon = new ImageIcon(getClass().getResource("/Stuff/Potions/UPotion.png")).getImage();
        Icon = Icon.getScaledInstance(32,32, Image.SCALE_FAST);

        DisplayIcon = new JImagePanel(Icon);
        Amount = new JTextField("Out Of Stock");
        Amount.setEditable(false);
        DisplayName = new JLabel(Name);
        Confirm = new JButton("Out Of Stock");

        Confirm.setForeground(Color.BLACK);
        Confirm.setFocusPainted(false);
        Confirm.setBorder(null);

        this.add(DisplayIcon);
        this.add(DisplayName);
        this.add(Amount);
        this.add(Confirm);
    }

    public ShopBlock(String Name, Image img){
        this.Name = Name;
        this.setLayout(new GridLayout(0,4));

        Icon = img.getScaledInstance(32,32, Image.SCALE_FAST);

        DisplayIcon = new JImagePanel(Icon);
        Amount = new JTextField("0");
        DisplayName = new JLabel(Name);
        Confirm = new JButton("Click To Buy");

        Confirm.setForeground(Color.BLACK);
        Confirm.setFocusPainted(false);
        Confirm.setBorder(null);

        this.add(DisplayIcon);
        this.add(DisplayName);
        this.add(Amount);
        this.add(Confirm);
    }


}
