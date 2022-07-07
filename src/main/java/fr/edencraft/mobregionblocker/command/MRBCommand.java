package fr.edencraft.mobregionblocker.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import fr.edencraft.mobregionblocker.MobRegionBlocker;
import fr.edencraft.mobregionblocker.manager.ConfigurationManager;
import fr.edencraft.mobregionblocker.utils.ColoredText;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

@CommandAlias("mobregionblocker|mrb")
public class MRBCommand extends BaseCommand {

    private static ConfigurationManager configurationManager = MobRegionBlocker.getINSTANCE().getConfigurationManager();
    private static final String basePermission = "mobregionblocker.command";

    @Default
    @CommandPermission(basePermission)
    public static void onCommand(CommandSender sender) {
        sender.sendMessage("HELP MESSAGE HERE");
    }

    @Subcommand("reload|rl")
    @Syntax("[fileName]")
    @CommandCompletion("@mrbreload")
    @CommandPermission(basePermission + ".reload")
    public static void onReload(CommandSender sender, @Optional String fileName){
        FileConfiguration messages = configurationManager.getConfigurationFile("Fr.yml");
        if (fileName != null && !fileName.isEmpty()) {
            if (configurationManager.getConfigurationFile(fileName) != null) {
                configurationManager.reloadFile(fileName);
                if (fileName.equalsIgnoreCase("config.yml")) updateOnlySpawnRegionList();

                sender.sendMessage(new ColoredText(
                                messages.getString("reload-file")
                                        .replaceAll("\\{filename}", fileName))
                        .treat());
            } else {
                sender.sendMessage(new ColoredText(
                        messages.getString("unknown-file")
                                .replaceAll("\\{filename}", fileName))
                        .treat());
            }
        } else {
            configurationManager.reloadFiles();
            sender.sendMessage(new ColoredText(messages.getString("reload-all")).treat());
        }
    }

    private static void updateOnlySpawnRegionList() {
        List<String> list = configurationManager.getConfigurationFile("config.yml").getStringList("only-spawn-region");
        MobRegionBlocker.getINSTANCE().setOnlySpawnRegionMobs(list);
    }

}
