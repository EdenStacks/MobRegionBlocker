package fr.edencraft.mobregionblocker.manager;

import co.aikar.commands.PaperCommandManager;
import fr.edencraft.mobregionblocker.MobRegionBlocker;
import fr.edencraft.mobregionblocker.command.MRBCommand;
import fr.edencraft.mobregionblocker.utils.CommandCompletionUtils;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager {

    public CommandManager(MobRegionBlocker plugin) {
        PaperCommandManager commandManager = new PaperCommandManager(plugin);
        commandManager.enableUnstableAPI("help");
        commandManager.registerCommand(new MRBCommand());
        commandManager.getCommandCompletions().registerAsyncCompletion(
                "mrbreload",
                context -> {
                    CommandSender sender = context.getSender();
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_ON, 1, 1);
                    }
                    return CommandCompletionUtils.getConfigurationFilesName();
                }
        );
    }
}
