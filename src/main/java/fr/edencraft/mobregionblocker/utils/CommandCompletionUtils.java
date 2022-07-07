package fr.edencraft.mobregionblocker.utils;

import fr.edencraft.mobregionblocker.MobRegionBlocker;
import fr.edencraft.mobregionblocker.manager.ConfigurationManager;

import java.util.ArrayList;
import java.util.List;

public class CommandCompletionUtils {

    public static List<String> getConfigurationFilesName() {
        List<String> cfgList = new ArrayList<>();
        ConfigurationManager configurationManager = MobRegionBlocker.getINSTANCE().getConfigurationManager();
        configurationManager.getFilesMap().forEach((file, fileConfiguration) -> cfgList.add(file.getName()));
        return cfgList;
    }

}
