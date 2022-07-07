package fr.edencraft.mobregionblocker.manager;

import fr.edencraft.mobregionblocker.utils.RegionControler;
import io.lumine.mythic.core.mobs.ActiveMob;

import java.util.ArrayList;
import java.util.List;

public class RegionControlerManager {

    private static List<RegionControler> rcs = new ArrayList<>();

    public static List<RegionControler> getrcs() {
        return rcs;
    }

    public static RegionControler get(ActiveMob am) {
        for(RegionControler rc : rcs) if(rc.getAm().equals(am)) return rc;
        return null;
    }

    public static boolean contains(ActiveMob am) {
        for(RegionControler rc : rcs) if(rc.getAm().equals(am)) return true;
        return false;
    }
}
