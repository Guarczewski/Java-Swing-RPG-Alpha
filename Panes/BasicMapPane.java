package Panes;

import Blocks.MapBlock;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class BasicMapPane extends JPanel{

    public static Random Randomizer = new Random();
    public final int MAP_SIZE_X = 64, MAP_SIZE_Z = 96;
    public MapBlock MAP[][] = new MapBlock[MAP_SIZE_X][MAP_SIZE_Z];
    public JPanel InformationPanel;

    public BasicMapPane(){
        this.setLayout(new BorderLayout());

        JPanel MapContainer = new JPanel(new GridLayout(MAP_SIZE_X, MAP_SIZE_Z));
        InformationPanel = new JPanel();

        for (int i = 0; i < MAP_SIZE_X; i++){
            for (int j = 0; j < MAP_SIZE_Z; j++){
                MAP[i][j] = new MapBlock();
                MAP[i][j].UpdateBlock();
                MapContainer.add(MAP[i][j]);
            }
        }

        this.add(MapContainer,BorderLayout.CENTER);
        this.add(InformationPanel,BorderLayout.EAST);

    }

    public void FreeBlock(int CharX, int CharZ){
        MAP[CharX][CharZ].Y_Value = MAP[CharX][CharZ].Prev_Y_Value;
        MAP[CharX][CharZ].Occuped = false;
        MAP[CharX][CharZ].InvaderID = 0;
        MAP[CharX][CharZ].UpdateBlock();
    }

    public void InvadeBlock(int CharX, int CharZ, int CharIndex){
        MAP[CharX][CharZ].Y_Value = 999;
        MAP[CharX][CharZ].Occuped = true;
        MAP[CharX][CharZ].InvaderID = CharIndex;
        MAP[CharX][CharZ].UpdateBlock();
    }

    public void Mountains() {

        int tmp[] = new int[8];

        for (int i = 0; i < MAP_SIZE_X; i++) {
            for (int j = 0; j < MAP_SIZE_Z; j++) {

                if (j - 1 < 0) { tmp[0] = 1; tmp[1] = 1; }
                else if (j - 2 < 0) { tmp[0] = 1; tmp[1] = MAP[i][j - 1].Y_Value; }
                else {  tmp[0] = MAP[i][j - 1].Y_Value; tmp[1] = MAP[i][j - 2].Y_Value; }

                if (j + 1 >= MAP_SIZE_Z) { tmp[2] = 1; tmp[3] = 1; }
                else if (j + 2 >= MAP_SIZE_Z) { tmp[2] = MAP[i][j + 1].Y_Value; tmp[3] = 1; }
                else { tmp[2] = MAP[i][j + 1].Y_Value; tmp[3] = MAP[i][j + 2].Y_Value; }

                if (i - 1 < 0) {  tmp[4] = 1; tmp[5] = 1; }
                else if (i - 2 < 0) {  tmp[4] = 1; tmp[5] = MAP[i - 1][j].Y_Value; }
                else { tmp[4] = MAP[i - 1][j].Y_Value; tmp[5] = MAP[i - 2][j].Y_Value; }

                if (i + 1 >= MAP_SIZE_X) { tmp[6] = 1; tmp[7] = 1; }
                else if (i + 2 >= MAP_SIZE_X) { tmp[6] = MAP[i + 1][j].Y_Value; tmp[7] = 1; }
                else { tmp[6] = MAP[i + 1][j].Y_Value; tmp[7] = MAP[i + 2][j].Y_Value; }

                MAP[i][j].Y_Value = (tmp[0] + tmp[1] + tmp[2] + tmp[3] + tmp[4] + tmp[5] + tmp[6] + tmp[7]) / 6;

                if(tmp[0] > 1 || tmp[1] > 1 || tmp[2] > 1 || tmp[3] > 1 || tmp[4] > 1|| tmp[5] > 1 || tmp[6] > 1 || tmp[7] > 1)
                    if (Randomizer.nextInt(10) > 5)
                        MAP[i][j].Y_Value++;

                MAP[i][j].Prev_Y_Value = MAP[i][j].Y_Value;
                MAP[i][j].UpdateBlock();

            }
        }
    }

    public void River() {
        boolean WaterNear;

        int WBS = 7;

        for (int i = 0; i < MAP_SIZE_X; i++) {
            for (int j = 0; j < MAP_SIZE_Z; j++) {
                if (MAP[i][j].Y_Value == 1) {

                    WaterNear = false;

                    if (j - 1 < 0) { if (Randomizer.nextInt(10) > WBS){ WaterNear = true; } }
                    else { if (MAP[i][j - 1].Y_Value == -99) WaterNear = true; }

                    if (j + 1 >= MAP_SIZE_Z) { if (Randomizer.nextInt(10) > WBS){ WaterNear = true; } }
                    else { if (MAP[i][j + 1].Y_Value == -99) WaterNear = true; }

                    if (i - 1 < 0) { if (Randomizer.nextInt(10) > WBS){ WaterNear = true; } }
                    else { if (MAP[i - 1][j].Y_Value == -99) WaterNear = true; }

                    if (i + 1 >= MAP_SIZE_X) { if (Randomizer.nextInt(10) > WBS){ WaterNear = true; } }
                    else { if (MAP[i + 1][j].Y_Value == -99) WaterNear = true; }

                    if (WaterNear) { if (Randomizer.nextInt(10) > 3) {
                        MAP[i][j].Y_Value = -99;
                        MAP[i][j].Prev_Y_Value = MAP[i][j].Y_Value;
                        MAP[i][j].UpdateBlock(); }
                    }
                }
            }
        }
    }

}
