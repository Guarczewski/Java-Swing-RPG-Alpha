package Entity;


import Item.*;
import java.util.Random;

public class Entity {

    public static final int STAT_COUNT = 6;
    // Location

    public int X_Cord, Z_Cord; // Current Cords
    public int X_P_Cord, Z_P_Cord, P_Value; // Previous Cords

    // Stats
    public double Stats[][];
    public String Name, Surname;
    public int Level; // Level Stuff
    public Boolean Horse; // Does Have Horse
    public Boolean Fight; // Is in battle
    public int Money[]; // Currency

    // SPELLS
    public static final int SPELLS = 8;
    public int SpellCost[];

    public Entity(int X, int Y){

        SpellCost = new int[SPELLS];
        Stats = new double[STAT_COUNT][10];

        // # $ <- # = StatType ID $ = Variant
        // 1.$ HP || 2.$ Mana || 3.$ Stamina || 4.$ Damage || 5.$ SpellPower || 6.$ Defense
        // #.0 Basic || #.1 Max || #.2 Current || #.3 Bonus || #.4 Console || #.5 Bought ||

        for (int i = 0; i < SPELLS; i++) { SpellCost[i] = 10; }

        Level = 1;
        Horse = false;
        Fight = false;

        X_Cord = X_P_Cord = X;
        Z_Cord = Z_P_Cord = Y;

        Money = new int[1];
        Money[0] = 0; // Gold Coins

        NameGenerator();
        RefreshBonus(true);

    }

    public void RefreshBonus(boolean first){
        Stats[0][0] = Level * 250; // Basic MaxHP
        Stats[1][0] = Level * 15; // Basic Mana
        Stats[2][0] = Level * 25; // Basic Stamina
        Stats[3][0] = Level * 10; // Basic Damage
        Stats[4][0] = Level * 2.5; // Basic SpellPower
        Stats[5][0] = Level * 5; // Basic Defense

        for(int i = 0; i < STAT_COUNT; i++){
            Stats[i][1] = Stats[i][0] + Stats[i][3] + Stats[i][4] + Stats[i][5]; // Max = Basic + Bonus + Console //
        }

        if (first) {
            Stats[0][2] = Stats[0][1]; // Set Current HP to Max HP
            Stats[1][2] = Stats[1][1]; // Set Current Mana to Max Mana
            Stats[2][2] = Stats[2][1]; // Set Current Stamina to Max Stamina
        }
    }

    public void NameGenerator(){
        Random rand = new Random(); // Randomizer
        switch (rand.nextInt(5)){
            case 0: Name = "Janusz"; break;
            case 1: Name = "Andrzej"; break;
            case 2: Name = "Zdzich"; break;
            case 3: Name = "Domingo"; break;
            case 4: Name = "Kastelgandolfo"; break;
            default: Name = "NULL"; break;
        }
        switch (rand.nextInt(5)){
            case 0: Surname = "Stonefist"; break;
            case 1: Surname = "SmallPP"; break;
            case 2: Surname = "DumDumb"; break;
            case 3: Surname = "BlingBlong"; break;
            case 4: Surname = "Fafik"; break;
            default: Name = "NULL"; break;
        }
    }

    public String GiveInfo() {
        String Output = "Character Name: \n" + Name + " " + Surname + "\n" + "\nLevel: " + Level + "\n"
                + "HP: " + Stats[0][2] + " || " + Stats[0][1] + "\n" + "Mana: " + Stats[1][2] + " || " + Stats[1][1] + "\n"
                + "Stamina: " + Stats[2][2] + " || " + Stats[2][1] + "\n" + "Damage: " + Stats[3][1] + "\n"
                + "Spell Power: " + Stats[4][1] + "\n" + "Defense: " + Stats[5][1] + "\n" + "Coins: " + Money[0] + "\n"
                + "\nCoordinates: \n X: " + X_Cord + " || Z: " + Z_Cord + "\n";

        return Output;
    }

    public String BuyStats(int StatID, int Amount){
        String Output = "You have no enaugh money.";
        if (Money[0] - 20 > 0) {
            Stats[StatID][5] += Amount;
            Stats[StatID][1] = Stats[StatID][0] + Stats[StatID][3] + Stats[StatID][4] + Stats[StatID][5];
            RefreshBonus(false);
            switch (StatID){
                case 0: Output = "Succesfully bought " + Amount + " Health Boost Potion."; break;
                case 1: Output = "Succesfully bought " + Amount + " Mana Boost Potion."; break;
                case 2: Output = "Succesfully bought " + Amount + " Stamina Boost Potion."; break;
                case 3: Output = "Succesfully bought " + Amount + " Damage Boost Potion."; break;
                case 4: Output = "Succesfully bought " + Amount + " Spell Power Boost Potion."; break;
                case 5: Output = "Succesfully bought " + Amount + " Defense Boost Potion."; break;
            }
        }
        return Output;
    }

    public void TakeDamage(double Value) {
        double DMGTaken = Value - (0.1 * Stats[5][1]);
        if (DMGTaken < 0)
            DMGTaken = 0;
        Stats[0][2] -= DMGTaken;
        Stats[0][2] = Math.round(Stats[0][2]);
    }
    public String SpellRestoreHP(){
        double Restore = (0.5 * Stats[4][1]) * 10;
        Stats[0][2] += Restore;
        Stats[0][2] = Math.round(Stats[0][2]);
        if (Stats[0][2] > Stats[0][1])
            Stats[0][2] = Stats[0][1];
        return "\n" + Name + " " + Surname + " Restored: " + Restore + " Health";
    }
    public String SpellRestoreMana(){
        double Restore = (0.35 * Stats[4][1]) * 10;
        Stats[1][2] += Restore;
        Stats[1][2] = Math.round(Stats[1][2]);
        if (Stats[1][2] > Stats[1][1])
            Stats[1][2] = Stats[1][1];
        return "\n" + Name + " " + Surname + " Restored: " + Restore + " Mana";
    }
    public String SpellRestoreStamina(){
        double Restore = (0.35 * Stats[4][1]) * 10;
        Stats[2][2] += Restore;
        Stats[2][2] = Math.round(Stats[1][2]);
        if (Stats[2][2] > Stats[2][1])
            Stats[2][2] = Stats[2][1];
        return "\n" + Name + " " + Surname + " Restored: " + Restore + "Stamina";
    }
	public String SpellMagicDMG(){
        double Boost = (0.15 * Stats[4][1]);
        Stats[3][1] += Boost;
        Stats[3][1]  = Math.round(Stats[3][1] );
        return "\n" + Name + " " + Surname + " Boosted Damage By: " + Boost;
    }
	public String SpellMagicSpellPower(){
        double Boost = (0.075 * Stats[4][1]);
        Stats[4][1] += Boost;
        Stats[4][1] = Math.round(Stats[4][1]);
        return "\n" + Name + " " + Surname + " Boosted Spell Power By: " + Boost;
    }
    public String SpellMagicDef(){
        double Boost = (0.05 * Stats[4][1]);
        Stats[5][1] += Boost;
        Stats[5][1] = Math.round(Stats[5][1]);
        return "\n" + Name + " " + Surname + " Boosted Defense By: " + Boost;
    }

    public void AfterBattle(){
        RefreshBonus(true);
    }

}
