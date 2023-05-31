package com.ulxsth.achievementtest;

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.events.PlayerLoadingCompletedEvent;
import com.ulxsth.achievementtest.achievements.BreakBlockAdv;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class AchievementTest extends JavaPlugin implements Listener {
    private static AdvancementTab advancementTab;

    @Override
    public void onEnable() {
        UltimateAdvancementAPI api = UltimateAdvancementAPI.getInstance(this);

        // 進捗タブ
        advancementTab = api.createAdvancementTab("your_tab_name");

        // 進捗タブの表示設定
        AdvancementDisplay tutorialRootDisplay = new AdvancementDisplay(
                Material.GRASS_BLOCK,
                "はじまり",
                AdvancementFrameType.TASK,
                true,
                true,
                0,
                0,
                "ここを我が拠点とする"
        );
        AdvancementDisplay blockBreakAdvDisplay = new AdvancementDisplay(
                Material.WOODEN_PICKAXE,
                "破壊",
                AdvancementFrameType.TASK,
                true,
                true,
                1,
                0,
                "ストレス発散によいとされている"
        );

        // 進捗
        RootAdvancement tutorialRoot = new RootAdvancement(
                advancementTab,
                "tutorial",
                tutorialRootDisplay,
                "textures/block/stone.png"
        );
        BreakBlockAdv blockBreakAdv = new BreakBlockAdv(
                "break",
                blockBreakAdvDisplay,
                tutorialRoot,
                5
        );

        advancementTab.registerAdvancements(tutorialRoot, blockBreakAdv);
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerLoadingCompletedEvent e) {
        Player p = e.getPlayer();
        advancementTab.showTab(p);
    }
}
