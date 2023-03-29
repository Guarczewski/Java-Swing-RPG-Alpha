package Panes;

import Blocks.*;

import javax.swing.*;
import java.awt.*;

public class VillagePane extends BasicMapPane{

    int VillageLevel;

    static public final int RES_TYPE = 4;

    public JButton VillageButtons[] = new JButton[RES_TYPE];

    private ResourceBlock Resources[] = new ResourceBlock[RES_TYPE];

    private JLabel Outcome;

    public VillagePane(){

        this.add(InformationPanel,BorderLayout.NORTH);
        InformationPanel.setLayout(new GridLayout(1,4));

        VillageLevel = 20;

        Outcome = new JLabel();

        Resources[0] = new ResourceBlock("Population",0,1,0);
        Resources[1] = new ResourceBlock("Food",0,2,2);
        Resources[2] = new ResourceBlock("Wood",0, 3,3);
        Resources[3] = new ResourceBlock("Stone",0, 4,4);

        VillageButtons[0] = new JButton(" Sell Resources ");
        VillageButtons[1] = new JButton(" ^ Upgrade Farm ^ ");
        VillageButtons[2] = new JButton(" ^ Upgrade Sawmill ^ ");
        VillageButtons[3] = new JButton(" ^ Upgrade Query ^ ");

        JPanel VillageMenagment = new JPanel(new GridLayout(0,5));
        this.add(VillageMenagment, BorderLayout.SOUTH);

        for (int i = 0; i < RES_TYPE; i++) {
            VillageMenagment.add(VillageButtons[i]);
            InformationPanel.add(Resources[i]);
        }

        VillageMenagment.add(Outcome);

        River();
        VillageLife.start();
    }

    Thread VillageLife = new Thread(() -> {
        while (true) {
            for (int i = 1; i < RES_TYPE; i++) {
                Resources[i].TriggerConsumption();
                Resources[i].TriggerProduction();
            }
            try { Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }); // Timer Thread

    public void Upgrade(int ResIndex){
        int FoodCost;
        int UpgradeCost;

        if (ResIndex == 0) {
            FoodCost = 0;
        }
        else {
            FoodCost = VillageLevel * 10;
        }

        UpgradeCost = Resources[ResIndex].FarmLevel * 15;

        if(Resources[1].Amount - FoodCost >= 0 && Resources[ResIndex].Amount - UpgradeCost >= 0) {
            Resources[ResIndex].FarmLevel++;
            Resources[ResIndex].UpdateProduction();
        }

        else {
            Outcome.setText("You don't have enough resources");
        }

    }

    public int SellResources(){

        int Coins = 0;

        for (int i = 1; i < RES_TYPE; i++) {
            Coins += Resources[i].Value;
            Resources[i].Amount = 0;
        }

        return Coins;
    }

}
