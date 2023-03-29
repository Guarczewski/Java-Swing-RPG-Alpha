package Blocks;

import javax.swing.*;
import java.awt.*;


public class ResourceBlock extends JButton{

    public String Name;
    public int Amount;
    public int FarmLevel;
    public int Production;
    private int BasicProduction;
    public int Price;
    public int Value;

    public ResourceBlock(){
        this.setBackground(Color.WHITE);
        this.setFocusPainted(false);
        this.setBorder(null);
        Name = "NULL";
        Amount = 0;
        Production = 0;
        Price = 0;
        Value = 0;
        Update();
    }

    public ResourceBlock(String Name, int Amount, int Production, int Price){
        this.setBackground(Color.WHITE);
        this.setFocusPainted(false);
        this.setBorder(null);
        this.Name = Name;
        this.Amount = Amount;
        BasicProduction = Production;
        this.Production = Production;
        this.Price = Price;
        Value = Amount * Price;
        Update();
    }

    public void UpdateProduction(){
        Production = FarmLevel * BasicProduction;
    }

    public void TriggerProduction(){
        Amount += Production;
        Value = Price * Amount;
        Update();
    }

    public void TriggerConsumption(){
        Amount -= 1;
        Value = Price * this.Amount;
        Update();
    }

    private void Update(){
        if (Name == "Population") {
            String tmp = " " + Name + ": " + Amount;
            this.setText(tmp);
        }
        else {
            String tmp = " " + Name + ": " + Amount + " || Farm Level: " + FarmLevel;
            this.setText(tmp);
        }
    }
}
