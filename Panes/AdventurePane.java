package Panes;

import javax.swing.*;
import java.awt.*;

public class AdventurePane extends BasicMapPane{

    private String Controls;

    public JButton Fix;
    public JTextArea ShortInfo;
    public JTextArea ControlsInfo;

    public AdventurePane() {

        InformationPanel.setLayout(new BorderLayout());

        ShortInfo = new JTextArea();
        ShortInfo.setEditable(false);
        InformationPanel.add(ShortInfo,BorderLayout.NORTH);

        Fix = new JButton("Can't Move?");
        InformationPanel.add(Fix,BorderLayout.SOUTH);

        ControlsInfo = new JTextArea();
        ControlsInfo.setEditable(false);
        InformationPanel.add(ControlsInfo,BorderLayout.CENTER);
        Controls = "";
        Controls += "\n Controls List";
        Controls += "\n W <-- Move UP";
        Controls += "\n S <-- Move Down";
        Controls += "\n A <-- Move Left";
        Controls += "\n D <-- Move Right";
        Controls += "\n";
        Controls += "\n P <-- Go to Map";
        Controls += "\n O <-- Go to Battle Pane";
        Controls += "\n I <-- Go to Village";
        Controls += "\n U <-- Go to Shop";
        Controls += "\n C <-- Go to Console";

        ControlsInfo.setText(Controls);

        for (int i = 0; i < (Randomizer.nextInt(3) + 1); i++) {
            MAP[Randomizer.nextInt(MAP_SIZE_X)][Randomizer.nextInt(MAP_SIZE_Z)].Y_Value = 5;
        }

        Mountains();
        River();
    }

}
