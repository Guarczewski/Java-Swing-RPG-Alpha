package Blocks;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MapBlock extends JButton {

    public static Random Randomizer = new Random();
    public static final int VARIANT_RADIUS = 5;

    public int Y_Value, Prev_Y_Value;
    int Variant, PrevVariant;

    public int InvaderID;
    public boolean Occuped;


    String Message;

    public static ImageIcon Tree = new ImageIcon(MapBlock.class.getResource("/Stuff/Tree.png"));

    public MapBlock(){

        Y_Value = Prev_Y_Value = 1;

        Occuped = false;

        Variant = Randomizer.nextInt(VARIANT_RADIUS);
        PrevVariant = Variant;

        Message = "";

        this.setForeground(Color.RED);
        this.setFocusPainted(false);
        this.setBorder(null);


    }

    public void UpdateBlock(){
        switch (Y_Value) {
            case -99: switch (Variant) {
                case 0: this.setBackground(new Color(35, 57, 93)); break;
                case 1: this.setBackground(new Color(32, 51, 84)); break;
                case 2: this.setBackground(new Color(28, 46, 74)); break;
                case 3: this.setBackground(new Color(25, 40, 65)); break;
                case 4: this.setBackground(new Color(21, 34, 56)); break;
            }  this.setIcon(null); break;
            case 1:
                switch (Variant) {
                    case 0: this.setBackground(new Color(128, 201, 4)); break;
                    case 1: this.setBackground(new Color(115, 181, 4)); break;
                    case 2: this.setBackground(new Color(102, 161, 3)); this.setIcon(Tree); break;
                    case 3: this.setBackground(new Color(90, 141, 3)); break;
                    case 4: this.setBackground(new Color(77, 121, 2)); break;
                } break;
            case 2: this.setBackground(Color.YELLOW); this.setIcon(null); break;
            case 3: this.setBackground(Color.ORANGE); this.setIcon(null); break;
            case 4: this.setBackground(Color.RED); this.setIcon(null); break;
            case 5: this.setBackground(Color.BLACK); this.setIcon(null); break;
            case 999: this.setBackground(Color.WHITE); this.setIcon(null); break;
            default: this.setBackground(Color.GREEN); this.setIcon(null); break;
        }
    }

}

