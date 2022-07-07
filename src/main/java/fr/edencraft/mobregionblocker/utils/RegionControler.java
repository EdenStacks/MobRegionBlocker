package fr.edencraft.mobregionblocker.utils;

import fr.edencraft.mobregionblocker.manager.RegionControlerManager;
import io.lumine.mythic.core.mobs.ActiveMob;

import java.util.List;

public class RegionControler {

    private final ActiveMob am;
    private final List<String> region;

    public RegionControler(ActiveMob am,List<String> region) {
        this.am = am;
        this.region = region;
        RegionControlerManager.getrcs().add(this);
    }

    public ActiveMob getAm() {
        return am;
    }

    public List<String> getRegion() {
        return region;
    }


}
