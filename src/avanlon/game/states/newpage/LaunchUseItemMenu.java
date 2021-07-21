package avanlon.game.states.newpage;

import avanlon.framework.gui.WindowManager;
import avanlon.game.entity.Player.Player;
import avanlon.game.states.dungeonstates.UseItemMenu;
import avanlon.game.states.merchantstates.BuyMenu;

import javax.swing.*;

public class LaunchUseItemMenu
{
    public static JFrame frame = new JFrame();

    public LaunchUseItemMenu(Player player)
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WindowManager.WIDTH/2+20, WindowManager.HEIGHT);
        frame.setLayout(null);
        frame.add(new UseItemMenu(player, true));
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
