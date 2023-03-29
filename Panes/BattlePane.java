package Panes;

import Blocks.Block;

import javax.swing.*;
import java.awt.*;

public class BattlePane extends JPanel {

    public static JTextArea HeroLog, HeroStats;
    public static JTextArea EnemyLog, EnemyStats;
    public static JPanel SpellPanel;

    public static final int SPELLS = 8;
    public static Block SpellButtons[] = new Block[SPELLS];

    public BattlePane() {

        this.setLayout(new BorderLayout());

        JPanel HeroPanel = new JPanel(new BorderLayout());
        JPanel EnemyPanel = new JPanel(new BorderLayout());
        JPanel HeroSubPanel = new JPanel(new GridLayout(2,0));
        JPanel EnemySubPanel = new JPanel(new GridLayout(2,0));

        SpellPanel = new JPanel();

        SpellButtons[0] = new Block("Light Attack");
        SpellButtons[1] = new Block("Heavy Attack");
        SpellButtons[2] = new Block("^ Defense ^");
        SpellButtons[3] = new Block("^ Damage ^");
        SpellButtons[4] = new Block("^ Spell Power ^");
        SpellButtons[5] = new Block("^ Restore HP ^");
        SpellButtons[6] = new Block("^ Restore Mana ^");
        SpellButtons[7] = new Block("^ Restore Stamina ^");

        for (int i = 0; i < SPELLS; i++) {
            SpellPanel.add(SpellButtons[i]);
        }

        JLabel HeroLogTitle = new JLabel("==#==#==#==#==#== Hero Battle Log ==#==#==#==#==#==#==");
        JLabel EnemyLogTitle = new JLabel("==#==#==#==#==#== Enemy Battle Log ==#==#==#==#==#==#==");


        ScrollPane HeroSP = new ScrollPane();
        HeroStats = new JTextArea(" Hero Stats ");
        HeroLog = new JTextArea("");

        ScrollPane EnemySP = new ScrollPane();
        EnemyStats = new JTextArea(" Enemy Stats ");
        EnemyLog = new JTextArea("");

        HeroLog.setEditable(false);
        HeroStats.setEditable(false);
        EnemyLog.setEditable(false);
        EnemyStats.setEditable(false);

        HeroPanel.add(HeroLogTitle,BorderLayout.NORTH);
        HeroPanel.add(HeroSubPanel,BorderLayout.CENTER);

        HeroSubPanel.add(HeroStats);
        HeroSubPanel.add(HeroSP);
        HeroSP.add(HeroLog);

        EnemyPanel.add(EnemyLogTitle,BorderLayout.NORTH);
        EnemyPanel.add(EnemySubPanel,BorderLayout.CENTER);

        EnemySubPanel.add(EnemyStats);
        EnemySubPanel.add(EnemySP);
        EnemySP.add(EnemyLog);

        this.add(HeroPanel,BorderLayout.EAST);
        this.add(SpellPanel,BorderLayout.SOUTH);
        this.add(EnemyPanel,BorderLayout.WEST);

    }
}
