package PvMTickCounter;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.api.kit.KitType;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.OverlayMenuClicked;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@PluginDescriptor(name="PvM Tick Counter")

public class TickCounterPlugin extends Plugin{
    @Inject
    private OverlayManager overlayManager;
    @Inject
    private TickCounterConfig config;
    @Inject
    private Client client;

    private TickCounterUtil id;
    private Integer amount=0;

    private Integer MHCount=0;

    @Provides
    TickCounterConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(TickCounterConfig.class);
    }
    @Inject
    private TickCounterOverlay overlay;

    Map<String, Integer> activity = new HashMap<>();
    boolean instanced = false;
    boolean prevInstance = false;

    @Override
    protected void startUp() throws Exception
    {
        id = new TickCounterUtil();
        id.init();
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown() throws Exception
    {
        overlayManager.remove(overlay);
        activity.clear();
    }
    @Subscribe
    public void onHitsplatApplied(HitsplatApplied hitsplatApplied) {
        Player player = client.getLocalPlayer();
        Actor actor = hitsplatApplied.getActor();
        if (!(actor instanceof NPC)) {
            return;
        }

        Hitsplat hitsplat = hitsplatApplied.getHitsplat();

        if (Hitsplat.isMine()) {
            int hit = hitsplat.getAmount();

            amount += hit;
        }


        if (Hitsplat.isMine() && Hitsplat.getHitsplatType() == HitsplatID.DAMAGE_MAX_ME && Hitsplat.getHitsplatType() == HitsplatID.DAMAGE_MAX_ME_CYAN && Hitsplat.getHitsplatType() == HitsplatID.DAMAGE_MAX_ME_ORANGE && Hitsplat.getHitsplatType() == HitsplatID.DAMAGE_MAX_ME_YELLOW && Hitsplat.getHitsplatType() == HitsplatID.DAMAGE_MAX_ME_WHITE) {
            MHCount++;

        }
    }
    public Integer getDamage() {
           return amount;
       }
    public Integer getMH() {
       return MHCount;

     }

    @Subscribe
    public void onAnimationChanged(AnimationChanged e){
        if (!(e.getActor() instanceof Player))
            return;
        Player p = (Player) e.getActor();
        int weapon = -1;
        if (p.getPlayerComposition() != null)
            weapon = p.getPlayerComposition().getEquipmentId(KitType.WEAPON);
        int delta = 0;

        delta = id.getTicks(p.getAnimation(),weapon);

        if (delta > 0)
        {
            String name = p.getName();
            this.activity.put(name, this.activity.getOrDefault(name, 0) + delta);
        }
    }

    @Subscribe
    public void onGameTick(GameTick tick)
    {
        if (!config.instance())return;
        prevInstance = instanced;
        instanced = client.isInInstancedRegion();
        if (!prevInstance && instanced)
        {
            activity.clear();
            amount = 0;
        }
    }
    @Subscribe
    public void onOverlayMenuClicked(OverlayMenuClicked event) {
        if (event.getEntry().getMenuAction() == MenuAction.RUNELITE_OVERLAY &&
                event.getEntry().getTarget().equals("PvM Tick Counter") &&
                event.getEntry().getOption().equals("Reset")) {
            activity.clear();
            amount = 0;
        }
    }
}