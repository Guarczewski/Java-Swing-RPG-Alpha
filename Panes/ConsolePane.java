package Panes;

import javax.swing.*;
import java.awt.*;

public class ConsolePane extends JPanel {

    public static JLabel ConsoleTop;
    public static JTextArea ConsoleLog;
    public static JTextArea ConsoleInput;
    public static JButton SubmitButton;


    public ConsolePane(){

        this.setLayout(new BorderLayout());

        ConsoleTop = new JLabel("Console");

        ConsoleLog = new JTextArea("Console Log Area: \n Type Help() if You don't know Commands");
        ConsoleLog.setEditable(false);

        ConsoleInput = new JTextArea("Type Your Command Here :D");
        SubmitButton = new JButton("Submit");

        JPanel SubPanel = new JPanel(new BorderLayout());

        this.add(ConsoleTop,BorderLayout.NORTH);
        this.add(ConsoleLog,BorderLayout.CENTER);
        this.add(SubPanel,BorderLayout.SOUTH);
        SubPanel.add(ConsoleInput,BorderLayout.WEST);
        SubPanel.add(SubmitButton,BorderLayout.EAST);
    }
}
