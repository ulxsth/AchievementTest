package com.ulxsth.achievementtest.achievements;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BreakBlockAdv extends BaseAdvancement {
    public BreakBlockAdv(String key, AdvancementDisplay display, Advancement parent, int maxProgression) {
        super(key, display, parent, maxProgression);

        registerEvent(BlockBreakEvent.class, event -> {
            Player player = event.getPlayer();
            if (isVisible(player)) {
                incrementProgression(player);
            }
        });
    }

    @Override
    public void giveReward(Player player) {
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName("とてもたかいだいやもんど");
        item.setItemMeta(im);
        player.getInventory().addItem(item);
    }
}
