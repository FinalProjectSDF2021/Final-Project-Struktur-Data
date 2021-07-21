package avanlon.game.states.newpage;

import avanlon.framework.gui.WindowManager;
import avanlon.game.entity.Player.Player;
import avanlon.game.states.dungeonstates.BattleMonster;

import javax.swing.*;

public class LaunchBattleMonster
{
    public static JFrame frame = new JFrame();

    public LaunchBattleMonster(Player player, int totalMonster, int level)
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WindowManager.WIDTH, WindowManager.HEIGHT);
        frame.setLayout(null);
        frame.add(new BattleMonster(player, totalMonster, level));
        frame.setResizable(false);
        frame.setVisible(true);
    }}
