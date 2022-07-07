package fr.edencraft.mobregionblocker;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.internal.platform.WorldGuardPlatform;
import com.sk89q.worldguard.protection.managers.RegionManager;
import fr.edencraft.mobregionblocker.manager.CommandManager;
import fr.edencraft.mobregionblocker.manager.ConfigurationManager;
import fr.edencraft.mobregionblocker.manager.RegionControlerManager;
import fr.edencraft.mobregionblocker.utils.RegionControler;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.logging.Level;

public final class MobRegionBlocker extends JavaPlugin {

    private static MobRegionBlocker INSTANCE;

    private ConfigurationManager configurationManager = null;

    private String asciiArt;
    private List<String> onlySpawnRegionMobs;

    private WorldGuardPlatform worldguard;

    {
        this.asciiArt = """   
                
                ███    ███ ██████  ██████ \t
                ████  ████ ██   ██ ██   ██\t
                ██ ████ ██ ██████  ██████ \t
                ██  ██  ██ ██   ██ ██   ██\t
                ██      ██ ██   ██ ██████ \t
                """;
    }

    @Override
    public void onEnable() {
        long delay = System.currentTimeMillis();
        Bukkit.getLogger().log(Level.INFO, "\n" + asciiArt);

        this.INSTANCE = this;
        this.configurationManager = new ConfigurationManager(this);
        this.configurationManager.setupFiles();
        new CommandManager(this);

        onlySpawnRegionMobs = configurationManager.getConfigurationFile("config.yml").getStringList("only-spawn-region");

        worldguard = WorldGuard.getInstance().getPlatform();

        BukkitTask checkRegionTask = runCheckRegionTask();

        log(Level.INFO, "✅ MobRegionBlocker enabled. (took " + (System.currentTimeMillis() - delay) + "ms)");

    }

    @Override
    public void onDisable() {
        configurationManager.saveFiles();
    }

    public void setOnlySpawnRegionMobs(List<String> onlySpawnRegionMobs) {
        this.onlySpawnRegionMobs = onlySpawnRegionMobs;
    }

    public List<String> getOnlySpawnRegionMobs() {
        return onlySpawnRegionMobs;
    }

    private BukkitTask runCheckRegionTask() {
        return getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            MythicBukkit.inst().getMobManager().getActiveMobs().forEach(am -> {
                if (onlySpawnRegionMobs.contains(am.getType().getInternalName())) {
                    Location l = BukkitAdapter.adapt(am.getLocation());
                    RegionManager regionManager = worldguard.getRegionContainer().get(com.sk89q.worldedit.bukkit.BukkitAdapter.adapt(l.getWorld()));
                    List<String> set = regionManager.getApplicableRegionsIDs(com.sk89q.worldedit.bukkit.BukkitAdapter.asBlockVector(l));
                    RegionControler rc;
                    if(!RegionControlerManager.contains(am)) rc = new RegionControler(am, set);
                    else rc = RegionControlerManager.get(am);
                    if(!rc.getRegion().equals(set)) {
                        RegionControlerManager.getrcs().remove(rc);
                        am.remove();
                    }
                }
            });
        }, 10, 10);
    }

    public static MobRegionBlocker getINSTANCE() {
        return INSTANCE;
    }

    public void log(Level level, String message) {
        switch (level.getName()) {
            default -> Bukkit.getLogger()
                    .log(level, "[" + getPlugin(MobRegionBlocker.class).getName() + "] " + message);
            case "INFO" -> Bukkit.getLogger()
                    .log(level, ChatColor.GREEN + "[" + getPlugin(MobRegionBlocker.class).getName() + "] " + message);
            case "WARNING" -> Bukkit.getLogger()
                    .log(level, ChatColor.GOLD + "[" + getPlugin(MobRegionBlocker.class).getName() + "] " + message);
            case "SEVERE" -> Bukkit.getLogger()
                    .log(level, ChatColor.RED + "[" + getPlugin(MobRegionBlocker.class).getName() + "] " + message);
        }
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }
}
