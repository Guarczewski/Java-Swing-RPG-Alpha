package Item;

public class GearPiece extends Item{

    public static final int BONUS_COUNT = 6;
    public int Bonuses[];
    int ReqLevel;

    // TrashBin.Armor Bonuses
    // 0 - TrashBin.Armor
    // 1 - Max HP
    // 2 - Max Mana
    // 3 - Max Stamina
    // 4 - Damage
    // 5 - Spell Power

    public GearPiece(){
        Name = "NULL";
        ReqLevel = 1;
        Bonuses = new int[BONUS_COUNT];
        for (int i = 0; i < BONUS_COUNT; i++){
            Bonuses[i] = 10;
        }
    }

}
