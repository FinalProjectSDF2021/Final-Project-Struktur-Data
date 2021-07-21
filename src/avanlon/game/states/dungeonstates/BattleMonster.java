package avanlon.game.states.dungeonstates;

import avanlon.framework.gui.MyButton;
import avanlon.framework.gui.WindowManager;
import avanlon.framework.resources.Monsters;
import avanlon.framework.resources.Textures;
import avanlon.game.entity.Monster;
import avanlon.game.entity.Player.Player;
import avanlon.game.states.newpage.LaunchBattleMonster;
import avanlon.game.states.newpage.LaunchUseItemMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Stack;

public class BattleMonster extends JPanel implements ActionListener
{
    private Player player;
    private Player playerReset;
    private final MyButton[] buttons;
    private int selected;
    private int totalMonster;
    private Stack <Monster> spawnMob;
    private Random random = new Random();

    public BattleMonster(Player player, int totalMonster, int level)
    {
        this.playerReset = player;
        spawnMob = new Stack<>();
        this.selected = 0;
        this.player = player;
        this.buttons = new MyButton[4];
        this.setSize(WindowManager.WIDTH, WindowManager.HEIGHT);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.totalMonster = totalMonster;

        int x = 10, y = 370;
        for (int i = 0; i < buttons.length; i++)
        {
            if(i == 0)
                this.buttons[i] = new MyButton("   Attack");
            else if(i == 1)
                this.buttons[i] = new MyButton("Skill");
            else if(i == 2)
                this.buttons[i] = new MyButton("Item");
            else if(i == 3)
                this.buttons[i] = new MyButton("Flee");
            this.buttons[i].setFont(new Font("Dialog", Font.BOLD, 25));
            this.buttons[i].setMargin(new Insets(0,0,0,0));
            this.buttons[i].setBorder(null);
            this.buttons[i].setFocusPainted(false);
            this.buttons[i].setForeground(Color.WHITE);
            this.buttons[i].setBackground(Color.BLACK);
            this.buttons[i].setHoverBackgroundColor(Color.BLACK.brighter());
            this.buttons[i].setPressedBackgroundColor(Color.BLACK);
            this.buttons[i].addActionListener(this);
            if(i < 4)
            {
                this.buttons[i].setBounds(x, y, 100, 30);
                y += 30;
            }
        }

        for (MyButton button : buttons)
        {
            this.add(button);
        }
        for (int i = 0; i < totalMonster; i++)
        {
             switch (level)
             {
                 case 1 ->
                         {
                             Monster monster = Monsters.monsterDungeonCave.get(random.nextInt(Monsters.monsterDungeonCave.size()));
                             spawnMob.add(new Monster(monster.getName(), monster.getHPMax(), monster.getMPMax(), monster.getMagDef(), monster.getPhyDef(), monster.getMovSpeed(), monster.getMagAtt(), monster.getPhyAtt(), monster.getExpDrop(), monster.getGoldDrop(), monster.getLevel()));
                         }
                 case 2 ->
                         {
                             Monster monster = Monsters.monsterDungeonForest.get(random.nextInt(Monsters.monsterDungeonForest.size()));
                             spawnMob.add(new Monster(monster.getName(), monster.getHPMax(), monster.getMPMax(), monster.getMagDef(), monster.getPhyDef(), monster.getMovSpeed(), monster.getMagAtt(), monster.getPhyAtt(), monster.getExpDrop(), monster.getGoldDrop(), monster.getLevel()));
                         }
                 case 3 ->
                         {
                             Monster monster = Monsters.monsterDungeonCastle.get(random.nextInt(Monsters.monsterDungeonCastle.size()));
                             spawnMob.add(new Monster(monster.getName(), monster.getHPMax(), monster.getMPMax(), monster.getMagDef(), monster.getPhyDef(), monster.getMovSpeed(), monster.getMagAtt(), monster.getPhyAtt(), monster.getExpDrop(), monster.getGoldDrop(), monster.getLevel()));
                         }
             }
        }
    }

    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Dialog", Font.BOLD, 35));
        graphics.drawString("BATTLE", 400, 50);
        graphics.drawRoundRect(20, 60, 600, 300, 10, 10);
        graphics.drawRoundRect(640, 60, 310, 300, 10, 10);
        graphics.drawRoundRect(640, 370, 310, 130, 10, 10);

        BufferedImage sprite;
        sprite = Textures.getSprite(spawnMob.peek().getName(), "Monster");
        graphics.drawImage(sprite, 30, 70, sprite.getWidth()*2, sprite.getHeight()*2, null);
        graphics.setFont(new Font("Dialog", Font.BOLD, 25));
        int x = 325, y = 110;
        graphics.drawString("Name : " + spawnMob.peek().getName(), x, y);
        graphics.drawString("Level : " + spawnMob.peek().getLevel(), x, y+35);
        graphics.drawString("HP : " + spawnMob.peek().getHP() + "/" + spawnMob.peek().getHPMax(), x, y+70);
        graphics.drawString("Mag Def : " + spawnMob.peek().getMagDef(), x, y+105);
        graphics.drawString("Phy Def : " + spawnMob.peek().getPhyDef(), x, y+140);
        graphics.drawString("MAtt : " + spawnMob.peek().getMagAtt(), x, y+175);
        graphics.drawString("PAtt : " + spawnMob.peek().getPhyAtt(), x, y+210);
        graphics.drawString("You", x+450, y-20);
        graphics.drawString("HP : " + player.getHP() + "/" + player.getHPMax(), x+335, y+35);
        graphics.drawString("MP : " + player.getMP() + "/" + player.getMPMax(), x+335, y+70);
        graphics.drawString("Mag Def : " + player.getMagDef(), x+335, y+105);
        graphics.drawString("Phy Def : " + player.getPhyDef(), x+335, y+140);
        graphics.drawString("MAtt : " + player.getMagAtt(), x+335, y+175);
        graphics.drawString("PAtt : " + player.getPhyAtt(), x+335, y+210);

        for(int i=0; i < 4; i++)
        {
            if(i == this.selected)
                buttons[i].setForeground(Color.GREEN);
            else
                buttons[i].setForeground(Color.WHITE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == buttons[0])
            selected = 0;
        else if(e.getSource() == buttons[1])
            selected = 1;
        else if(e.getSource() == buttons[2])
            selected = 2;
        else if(e.getSource() == buttons[3])
            selected = 3;
        if(selected >= 0 && selected <= 3)
        {
            if(selected == 0)
            {
                player.giveDamage(spawnMob.peek());
                if(spawnMob.peek().isDie())
                {
                    player.addExpAndGold(spawnMob.peek());
                    spawnMob.pop();
                }
                else
                {
                    spawnMob.peek().giveDamage(player);
                    if (player.isDie())
                    {
                        JOptionPane.showMessageDialog(null, "YOU DIE", "LOSE MESSAGE", JOptionPane.WARNING_MESSAGE);
                        LaunchBattleMonster.frame.dispose();
                        WindowManager.frame.setVisible(true);
                    }
                }
            }
            if(selected == 2)
            {
                new LaunchUseItemMenu(player);
                spawnMob.peek().giveDamage(player);
                if (player.isDie())
                {
                    JOptionPane.showMessageDialog(null, "YOU DIE", "LOSE MESSAGE", JOptionPane.WARNING_MESSAGE);
                    LaunchBattleMonster.frame.dispose();
                    WindowManager.frame.setVisible(true);
                }
            }
            if(selected == 3)
            {
                selected = 0;
                LaunchBattleMonster.frame.dispose();
                WindowManager.frame.setVisible(true);
            }
        }
        repaint();
    }
}
