package Blocks;

import javax.swing.*;

public class Block extends JButton {

    public String Name;
    private JLabel Display;

    public Block (){
        Name = "NULL";
        Display = new JLabel(Name);
        this.add(Display);
    }

    public Block (String tmp){
        Name = tmp;
        Display = new JLabel(Name);
        this.add(Display);
    }

    public void UpdateDisplay(String tmp) {
        Name = tmp;
        Display.setText(Name);
    }


}
