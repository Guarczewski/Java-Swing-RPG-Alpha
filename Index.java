import Panes.*;
import Entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Index extends JFrame implements ActionListener {
    // CREATE RANDOMIZER
    public static Random Randomizer = new Random();
    // BEGIN TIMER
    public static int Time = 0;
    // Enemy ID
    public static int EnemyId = 0;
    // MAP SIZE
    public static final int MAP_SIZE_X = 64, MAP_SIZE_Z = 96;
    // SUB-PANEL SIZES
    public static final int SPELLS = 8, CHARACTERS = 6, RES_TYPE = 4;
    // CHARACTERS ARRAY
    public static Entity Char[] = new Entity[CHARACTERS];
    // INFO LABELS ARRAY

    public static JTabbedPane MainContainer;
    public static BattlePane Battle;
    public static ConsolePane Console;
    public static AdventurePane Adventure;
    public static VillagePane Village;
    public static CityPane City;
    public static CityPane Caves;
    public static ShopPane Shop;
    public static InventoryPane Inventory;

    Index() {
        super("The Guarec's Adventure!!");
        setBounds(0, 0, 1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainContainer = new JTabbedPane();

        ImageIcon icon = null;
        ImageIcon MapIco = new ImageIcon(getClass().getResource("/Stuff/NewMap.png"));
        ImageIcon SwordIco = new ImageIcon(getClass().getResource("/Stuff/Battle.png"));
        ImageIcon VillageIco = new ImageIcon(getClass().getResource("/Stuff/Village.png"));
        ImageIcon ShopIco = new ImageIcon(getClass().getResource("/Stuff/Shopkeeper.png"));
        ImageIcon CityIco = new ImageIcon(getClass().getResource("/Stuff/Castle.png"));

        Adventure = new AdventurePane();
        MainContainer.addTab("", MapIco, Adventure,"Adventure");

        Battle = new BattlePane();
        MainContainer.addTab("", SwordIco, Battle,"Battle");

        Village = new VillagePane();
        MainContainer.addTab("", VillageIco, Village,"Village");

        Shop = new ShopPane();
        MainContainer.addTab("", ShopIco, Shop, "Shop");

        //Inventory = new InventoryPane();
        //MainContainer.addTab("Inventory", icon, Inventory,"Inventory");

        // = new CityPane();
        //MainContainer.addTab("Caves", icon, Caves,"Caves");

        //City = new CityPane();
        //MainContainer.addTab("City", CityIco, City,"City");

        Console = new ConsolePane();
        MainContainer.addTab("Console", icon, Console,"Console");


        for (int i = 0; i < SPELLS; i++) {
            Battle.SpellButtons[i].addActionListener(this);
        }

        for (int i = 0; i < RES_TYPE; i++) {
            Village.VillageButtons[i].addActionListener(this);
        }

        for (int i = 0; i < 6; i++) {
            Shop.ShopOffer[i].Confirm.addActionListener(this);
        }

        Console.SubmitButton.addActionListener(this);
        Adventure.Fix.addActionListener(this);

        // DIY KEY LISTENER PART I
        MainContainer.getInputMap().put(KeyStroke.getKeyStroke("W"), "MoveUP");
        MainContainer.getInputMap().put(KeyStroke.getKeyStroke("S"), "MoveDown");
        MainContainer.getInputMap().put(KeyStroke.getKeyStroke("A"), "MoveLeft");
        MainContainer.getInputMap().put(KeyStroke.getKeyStroke("D"), "MoveRight");

        MainContainer.getInputMap().put(KeyStroke.getKeyStroke("P"), "ToTheAdventure");
        MainContainer.getInputMap().put(KeyStroke.getKeyStroke("O"), "ToTheBattle");
        MainContainer.getInputMap().put(KeyStroke.getKeyStroke("I"), "ToTheVillage");
        MainContainer.getInputMap().put(KeyStroke.getKeyStroke("U"), "ToTheShop");
        //MainContainer.getInputMap().put(KeyStroke.getKeyStroke("R"), "ToTheInventory");
        //MainContainer.getInputMap().put(KeyStroke.getKeyStroke("T"), "ToTheCaves");
        //MainContainer.getInputMap().put(KeyStroke.getKeyStroke("Y"), "ToTheCity");
        MainContainer.getInputMap().put(KeyStroke.getKeyStroke("C"), "ToTheConsole");

        MainContainer.getActionMap().put("MoveUP", TrigerMoveUP);
        MainContainer.getActionMap().put("MoveDown", TrigerMoveDown);
        MainContainer.getActionMap().put("MoveLeft", TrigerMoveLeft);
        MainContainer.getActionMap().put("MoveRight", TrigerMoveRight);

        MainContainer.getActionMap().put("ToTheAdventure", TrigerTTAdventure);
        MainContainer.getActionMap().put("ToTheBattle", TrigerTTBattle);
        MainContainer.getActionMap().put("ToTheVillage", TrigerTTVillage);
        MainContainer.getActionMap().put("ToTheShop", TrigerTTShop);
        MainContainer.getActionMap().put("ToTheInventory", TrigerTTInventory);
        MainContainer.getActionMap().put("ToTheCaves", TrigerTTCaves);
        MainContainer.getActionMap().put("ToTheCity", TrigerTTCity);
        MainContainer.getActionMap().put("ToTheConsole", TrigetTTConsole);

        // REST
        setContentPane(MainContainer);
        setVisible(true);
    }

    public static boolean CanMove(int Direction, int CharId){
        if(Char[CharId].Fight) {
            return false;
        }
        else {
            switch (Direction) {
                case 0:
                    if (Char[CharId].X_Cord - 1 >= 0)
                        if (!Adventure.MAP[Char[CharId].X_Cord - 1][Char[CharId].Z_Cord].Occuped)
                            return true;
                        else {
                            EnemyId = Adventure.MAP[Char[CharId].X_Cord - 1][Char[CharId].Z_Cord].InvaderID;
                            BeginBattle();
                        }
                    break; // UP
                case 1:
                    if (Char[CharId].X_Cord + 1 < MAP_SIZE_X)
                        if (!Adventure.MAP[Char[CharId].X_Cord + 1][Char[CharId].Z_Cord].Occuped)
                            return true;
                        else{
                            EnemyId = Adventure.MAP[Char[CharId].X_Cord + 1][Char[CharId].Z_Cord].InvaderID;
                            BeginBattle();
                        }
                    break; // Downn
                case 2:
                    if (Char[CharId].Z_Cord - 1 >= 0)
                        if (!Adventure.MAP[Char[CharId].X_Cord][Char[CharId].Z_Cord - 1].Occuped)
                            return true;
                        else {
                            EnemyId = Adventure.MAP[Char[CharId].X_Cord][Char[CharId].Z_Cord - 1].InvaderID;
                            BeginBattle();
                        }
                    break; // Left
                case 3:
                    if (Char[CharId].Z_Cord + 1 < MAP_SIZE_Z)
                        if (!Adventure.MAP[Char[CharId].X_Cord][Char[CharId].Z_Cord + 1].Occuped)
                            return true;
                        else {
                            EnemyId = Adventure.MAP[Char[CharId].X_Cord][Char[CharId].Z_Cord + 1].InvaderID;
                            BeginBattle();
                        }
                    break; // Right
                default:
                    return false;
            }
            return false;
        }
    }
    public static void MoveUp(int CharIndex) { // Move Up
        if (CanMove(0, CharIndex)) { // Check if Can move
            Adventure.FreeBlock(Char[CharIndex].X_Cord,Char[CharIndex].Z_Cord);
            Char[CharIndex].X_Cord--;
            Adventure.InvadeBlock(Char[CharIndex].X_Cord,Char[CharIndex].Z_Cord,CharIndex);
            if (CharIndex == 0)
                UpdateInfo(0);
        }
    }
    public static void MoveDown(int CharIndex){ // Move Down
        if (CanMove(1, CharIndex)) { // Check if Can move
            Adventure.FreeBlock(Char[CharIndex].X_Cord,Char[CharIndex].Z_Cord);
            Char[CharIndex].X_Cord++;
            Adventure.InvadeBlock(Char[CharIndex].X_Cord,Char[CharIndex].Z_Cord,CharIndex);
            if (CharIndex == 0)
                UpdateInfo(0);
        }
    }
    public static void MoveLeft(int CharIndex){ // Move Left
        if (CanMove(2, CharIndex)) { // Check if Can move
            Adventure.FreeBlock(Char[CharIndex].X_Cord,Char[CharIndex].Z_Cord);
            Char[CharIndex].Z_Cord--;
            Adventure.InvadeBlock(Char[CharIndex].X_Cord,Char[CharIndex].Z_Cord,CharIndex);
            if (CharIndex == 0)
                UpdateInfo(0);
        }
    }
    public static void MoveRight(int CharIndex){ // Move Right
        if (CanMove(3, CharIndex)) { // Check if Can move
            Adventure.FreeBlock(Char[CharIndex].X_Cord,Char[CharIndex].Z_Cord);
            Char[CharIndex].Z_Cord++;
            Adventure.InvadeBlock(Char[CharIndex].X_Cord,Char[CharIndex].Z_Cord,CharIndex);
            if (CharIndex == 0)
                UpdateInfo(0);
        }
    }
    public static void SetStartingPosition(int CharIndex){ // GIVE CHARACTER START POSITION
        Char[CharIndex] = new Entity(Randomizer.nextInt(MAP_SIZE_X),Randomizer.nextInt(MAP_SIZE_Z));
        Adventure.InvadeBlock(Char[CharIndex].X_Cord,Char[CharIndex].Z_Cord,CharIndex);
    }
    public static Thread Walker = new Thread(() -> {
        while (true) {
            for (int i = 1; i < CHARACTERS; i++) {
                switch (Randomizer.nextInt(4)) {
                    case 0: MoveUp(i);break;
                    case 1: MoveDown(i);break;
                    case 2: MoveLeft(i);break;
                    case 3: MoveRight(i);break;
                    default: break;
                }
            }
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    public static void UpdateInfo(int CharIndex){ // UPDATE INFO LABELS
        if (CharIndex == 0) {
            BattlePane.HeroStats.setText(Char[CharIndex].GiveInfo());
            Adventure.ShortInfo.setText(Char[CharIndex].GiveInfo());
        }
        else {
            BattlePane.EnemyStats.setText(Char[CharIndex].GiveInfo());
        }
    }
    public static void CastSpell(int SpellID, int SpellerID){ // CAST SPELL FUNCTION

        boolean CanCast = false;
        String Outcome = "\n" + Char[SpellerID].Name + " " + Char[SpellerID].Surname + " Have No Enaugh Mana To Cast Spell";

        if (SpellID == 1 || SpellID == 2) {
            if (true) {
                CanCast = true;
            }
        }
        else {
            if (Char[SpellerID].Stats[1][2] - Char[SpellerID].SpellCost[SpellID] > 0) {
                Char[SpellerID].Stats[1][2] -= Char[SpellerID].SpellCost[SpellID];
                CanCast = true;
            }
        }

        switch (SpellID) {
            case 0:
                if (SpellerID == 0) {
                    Char[EnemyId].TakeDamage(Char[0].Stats[3][1] );
                }
                else if (SpellerID == EnemyId) {
                    Char[0].TakeDamage(Char[EnemyId].Stats[3][1]);
                }
                Outcome = "\n" + Char[SpellerID].Name + " " + Char[SpellerID].Surname + " Made Light Attack";
                break;
            case 1:
                if (SpellerID == 0) {
                    Char[EnemyId].TakeDamage(Char[0].Stats[3][1] * 2);
                }
                else if (SpellerID == EnemyId) {
                    Char[0].TakeDamage(Char[EnemyId].Stats[3][1] * 2);
                }
                Outcome = "\n" + Char[SpellerID].Name + " " + Char[SpellerID].Surname + " Made Heavy Attack";
                break;
            case 2: Outcome = Char[SpellerID].SpellMagicDef(); break;
            case 3: Outcome = Char[SpellerID].SpellMagicDMG(); break;
            case 4: Outcome = Char[SpellerID].SpellMagicSpellPower(); break;
            case 5: Outcome = Char[SpellerID].SpellRestoreHP(); break;
            case 6: Outcome = Char[SpellerID].SpellRestoreMana(); break;
            case 7: Outcome = Char[SpellerID].SpellRestoreStamina(); break;
            default: System.out.printf("Error During Selecting Spell"); break;
        }

        if (SpellerID == 0) {
            String LogContent = Battle.HeroLog.getText();
            String TurnOutcome = LogContent + Outcome;
            Battle.HeroLog.setText(TurnOutcome);
        }
        else if (SpellerID == EnemyId) {
            String LogContent = Battle.EnemyLog.getText();
            String TurnOutcome = LogContent + Outcome;
            Battle.EnemyLog.setText(TurnOutcome);
        }
    }
    public static void EnemyTurn(){
        CastSpell(Randomizer.nextInt(SPELLS), EnemyId);
    }
    public static void BeginBattle(){
        if(Math.round(Math.sqrt((Math.pow(Math.abs((Char[0].X_Cord) - (Char[EnemyId].X_Cord)), 2)) + (Math.pow(Math.abs((Char[0].Z_Cord) - (Char[EnemyId].Z_Cord)), 2)))) == 1) {
            if (EnemyId != 0) {
                Char[0].Fight = true;
                Char[EnemyId].Fight = true;
                UpdateInfo(EnemyId);
                UpdateInfo(0);
                Battle.EnemyLog.setText("");
                Battle.HeroLog.setText("");
                MainContainer.setSelectedComponent(Battle);
            }
        }
    }
    public static void EndBattle(int WinnerID) {

        if (WinnerID == 0) {
            Adventure.MAP[Char[EnemyId].X_Cord][Char[EnemyId].Z_Cord].Y_Value = Adventure.MAP[Char[EnemyId].X_Cord][Char[EnemyId].Z_Cord].Prev_Y_Value;
            Adventure.MAP[Char[EnemyId].X_Cord][Char[EnemyId].Z_Cord].UpdateBlock();
            Adventure.FreeBlock(Char[EnemyId].X_Cord,Char[EnemyId].Z_Cord);
            SetStartingPosition(EnemyId);
            Adventure.MAP[Char[EnemyId].X_Cord][Char[EnemyId].Z_Cord].UpdateBlock();
            Adventure.InvadeBlock(Char[EnemyId].X_Cord,Char[EnemyId].Z_Cord,EnemyId);
        }
        else if (WinnerID == EnemyId) {
            Adventure.MAP[Char[0].X_Cord][Char[0].Z_Cord].Y_Value = Adventure.MAP[Char[0].X_Cord][Char[0].Z_Cord].Prev_Y_Value;
            Adventure.MAP[Char[0].X_Cord][Char[0].Z_Cord].UpdateBlock();
            Adventure.FreeBlock(Char[0].X_Cord,Char[0].Z_Cord);
            SetStartingPosition(0);
            Adventure.MAP[Char[0].X_Cord][Char[0].Z_Cord].UpdateBlock();
            Adventure.InvadeBlock(Char[EnemyId].X_Cord,Char[EnemyId].Z_Cord,0);
        }

        String BattleOutcom = Battle.HeroLog.getText() + "\n" + Char[WinnerID].Name + " " + Char[WinnerID].Surname + " Won the battle!!";
        Battle.HeroLog.setText(BattleOutcom);
        BattleOutcom = Battle.EnemyLog.getText() + "\n" + Char[WinnerID].Name + " " + Char[WinnerID].Surname + " Won the battle!!";
        Battle.EnemyLog.setText(BattleOutcom);

        Char[WinnerID].Level++;
        Char[0].AfterBattle();
        Char[0].Fight = false;
        Char[EnemyId].AfterBattle();
        Char[EnemyId].Fight = false;
        MainContainer.requestFocus();
    }

    // Console And Commands
    public static String Help(){
        String Output = "\n";
        Output += "\n -=#=- List of All Commands -=#=- \n ";
        Output += "\n -> Help() <- List of ALL Commands \n ";
        Output += "\n -> TriggerBattle() <- Triggers Battle \n ";
        Output += "\n -> SetLevel($1,$2) <- Sets level to typed Amount \n --> $1 Change with Character Index \n --> $2 Change with Amount \n";
        Output += "\n -> BoostStat($1,$2;$3) <- Boosts typed Stat by typed Amount \n --> $1 Change with Character Index \n --> $2 Change with StatID \n --> $3 Change with Amount \n";
        return Output;
    }
    public static String TriggerBattle(){
        String Output = "\n Successfully Trigered Fight";
        do {
            EnemyId = Randomizer.nextInt(CHARACTERS);
        } while (EnemyId == 0);
        BeginBattle();
        return Output;
    }
    public static String BoostStat(int CharIndex, int StatID, int Value){
        String Output = "";

        String StatName;

        switch (StatID) {
            case 0: StatName = "Health"; break;
            case 1: StatName = "Mana"; break;
            case 2: StatName = "Stamina"; break;
            case 3: StatName = "Damage"; break;
            case 4: StatName = "Spell Power"; break;
            case 5: StatName = "Defense"; break;
            default: StatName = "Unknown"; break;
        }

        try {
            Char[CharIndex].Stats[StatID][4] = Value;
            Char[CharIndex].RefreshBonus(true);
            Output = "\n Successfully Incresed " + Char[CharIndex].Name + " " + Char[CharIndex].Surname + "'s " + StatName + " by: " + Value;
        }
        catch (Exception e){
            Output = "\n There is no stat with id: " + StatID;
        }

        return Output;
    }
    public static String SetLevel(int CharIndex, int Value){
        String Output;
        if (CharIndex < CHARACTERS) {
            Char[CharIndex].Level = Value;
            Char[CharIndex].RefreshBonus(true);
            Output = "\n Succesfully setted " + Char[CharIndex].Name + " " + Char[CharIndex].Surname + "'s Level to: " + Value;
            UpdateInfo(CharIndex);
            return Output;
        }
        Output = "\n There is no Character with id: " + CharIndex;
        return Output;
    }
    public static String ConsoleRun(String Captured)  {
        String Output = "X", Command = "X";
        int Val1 = 0, Val2 = 0, Val3 = 0;

        try {
            Command = Captured.substring(0, Captured.indexOf('('));
            Val1 = Integer.parseInt(Captured.substring(Captured.indexOf('(') + 1, Captured.indexOf(',')));
            Val2 = Integer.parseInt(Captured.substring(Captured.indexOf(',') + 1, Captured.indexOf(';')));
            Val3 = Integer.parseInt(Captured.substring(Captured.indexOf(';') + 1, Captured.indexOf(')')));
        }
        catch (Exception e1) {
            try {
                Command = Captured.substring(0, Captured.indexOf('('));
                Val1 = Integer.parseInt(Captured.substring(Captured.indexOf('(') + 1, Captured.indexOf(',')));
                Val2 = Integer.parseInt(Captured.substring(Captured.indexOf(',') + 1, Captured.indexOf(')')));
            }
            catch (Exception e2){
                try {
                    Command = Captured.substring(0, Captured.indexOf('('));
                }
                catch (Exception e3) {
                    Output = "\n Error in Command Syntax";
                    return Output;
                }
            }
        }

        switch (Command) {
            case "SetLevel":
                Output = SetLevel(Val1, Val2);
                break;
            case "Help":
                Output = Help();
                break;
            case "BoostStat":
                Output = BoostStat(Val1, Val2, Val3);
                break;
            case "TriggerBattle":
                Output = TriggerBattle();
                break;
            default:
                Output = "\n There is no command as: " + Command;
                break;
        }

        return Output;

    }

    public static void ShopTransaction(int Index){
        int Amount;
        if(Index < 6) {
            Amount = Integer.parseInt(Shop.ShopOffer[Index].Amount.getText());
            Shop.Outcome.setText(Char[0].BuyStats(Index, Amount));
        }

        UpdateInfo(0);
    }

    public static void main(String[] args) {
        new Index();

        for (int i = 0; i < CHARACTERS; i++){ SetStartingPosition(i); }
        Walker.start();
        UpdateInfo(0);
    }

    // DIY KEY LISTENER PART II
    Action TrigerMoveUP = new AbstractAction() { public void actionPerformed(ActionEvent e) { MoveUp(0); } };
    Action TrigerMoveDown = new AbstractAction() { public void actionPerformed(ActionEvent e) { MoveDown(0); } };
    Action TrigerMoveLeft = new AbstractAction() { public void actionPerformed(ActionEvent e) { MoveLeft(0); } };
    Action TrigerMoveRight = new AbstractAction() { public void actionPerformed(ActionEvent e) { MoveRight(0); } };

    Action TrigerTTAdventure = new AbstractAction() { public void actionPerformed(ActionEvent e) { MainContainer.setSelectedComponent(Adventure); } };
    Action TrigerTTBattle = new AbstractAction() { public void actionPerformed(ActionEvent e) { MainContainer.setSelectedComponent(Battle); } };
    Action TrigerTTVillage = new AbstractAction() { public void actionPerformed(ActionEvent e) { MainContainer.setSelectedComponent(Village); } };
    Action TrigerTTShop = new AbstractAction() { public void actionPerformed(ActionEvent e) { MainContainer.setSelectedComponent(Shop); } };
    Action TrigerTTInventory = new AbstractAction() { public void actionPerformed(ActionEvent e) { MainContainer.setSelectedComponent(Inventory); } };
    Action TrigerTTCaves = new AbstractAction() { public void actionPerformed(ActionEvent e) { MainContainer.setSelectedComponent(Caves); } };
    Action TrigerTTCity = new AbstractAction() { public void actionPerformed(ActionEvent e) { MainContainer.setSelectedComponent(City); } };
    Action TrigetTTConsole= new AbstractAction() { public void actionPerformed(ActionEvent e) { MainContainer.setSelectedComponent(Console);; } };


    @Override
    public void actionPerformed(ActionEvent e) {

        

        JButton Catch = (JButton) e.getSource();

        if (Catch == Adventure.Fix) {
            MainContainer.requestFocus();
        }
        else if (Catch == ConsolePane.SubmitButton) {
            String Input = ConsolePane.ConsoleInput.getText();
            String Output = ConsolePane.ConsoleLog.getText() + ConsoleRun(Input);
            ConsolePane.ConsoleLog.setText(Output);
            MainContainer.requestFocus();
            return;
        }
        else if (Char[0].Fight) {
            if (Catch == Battle.SpellButtons[0])
                CastSpell(0, 0);
            else if (Catch == Battle.SpellButtons[1])
                CastSpell(1, 0);
            else if (Catch == Battle.SpellButtons[2])
                CastSpell(2, 0);
            else if (Catch == Battle.SpellButtons[3])
                CastSpell(3, 0);
            else if (Catch == Battle.SpellButtons[4])
                CastSpell(4, 0);
            else if (Catch == Battle.SpellButtons[5])
                CastSpell(5, 0);
            else if (Catch == Battle.SpellButtons[6])
                CastSpell(6, 0);
            else if (Catch == Battle.SpellButtons[7])
                CastSpell(7, 0);

            EnemyTurn();

            if (Char[0].Stats[0][2] <= 0)
                EndBattle(EnemyId);
            else if (Char[EnemyId].Stats[0][2] <= 0)
                EndBattle(0);

            UpdateInfo(0);
            UpdateInfo(EnemyId);
        }
        else if (!Char[0].Fight) {
            if (Catch == Village.VillageButtons[0]) {
                int Cash = Village.SellResources();
                Char[0].Money[0] += Cash;
                UpdateInfo(0);
            }
            else if (Catch == Village.VillageButtons[1])
                Village.Upgrade(1);
            else if (Catch == Village.VillageButtons[2])
                Village.Upgrade(2);
            else if (Catch == Village.VillageButtons[3])
                Village.Upgrade(3);
            else if (Catch == Shop.ShopOffer[0].Confirm)
                ShopTransaction(0);
            else if (Catch == Shop.ShopOffer[1].Confirm)
                ShopTransaction(1);
            else if (Catch == Shop.ShopOffer[2].Confirm)
                ShopTransaction(2);
            else if (Catch == Shop.ShopOffer[3].Confirm)
                ShopTransaction(3);
            else if (Catch == Shop.ShopOffer[4].Confirm)
                ShopTransaction(4);
            else if (Catch == Shop.ShopOffer[5].Confirm)
                ShopTransaction(5);

        }
        MainContainer.requestFocus();
    }
}