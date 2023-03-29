package Panes;

import Blocks.*;

import javax.swing.*;
import java.awt.*;

public class InventoryPane extends JPanel {

    public static final int SIZE_X = 8;
    public static final int SIZE_Y = 6;

    public InventorySlot Inventory[][] = new InventorySlot[SIZE_X][SIZE_Y];

    public InventoryPane(){

        this.setLayout(new GridLayout(SIZE_X,SIZE_Y));

        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++){
                Inventory[i][j] = new InventorySlot();
                Inventory[i][j].setBackground(Color.LIGHT_GRAY);
                this.add(Inventory[i][j]);
            }
        }
    }

}
