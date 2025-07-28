package me.teakivy.teakstweaks.utils.advancements;

import com.fren_gor.ultimateAdvancementAPI.AdvancementMain;
import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.database.impl.SQLite;
import com.fren_gor.ultimateAdvancementAPI.events.PlayerLoadingCompletedEvent;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.moremobheads.MoreMobHeads;
import me.teakivy.teakstweaks.packs.moremobheads.types.TexturedHead;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AdvancementManager {
    private AdvancementMain main;
    private UltimateAdvancementAPI ultimateAdvancementAPI;
    private final HashMap<String, AdvancementTab> tabs = new HashMap<>();
    private final HashMap<String, RootAdvancement> roots = new HashMap<>();
    private static final String ALLAY_TEXTURE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2MwMzg5MTc3ZGJhYTkyZjBkNWZmZGY4NDg4NjJjN2Y5YjM2ZGYyMjJmYmZkNzM3ZTI2MzlkYzMwNTllMGNmMyJ9fX0";

    public void load() {
        main = new AdvancementMain(TeaksTweaks.getInstance());
        main.load();
    }

    public void enable() {
        main.enable(() -> new SQLite(main, new File(TeaksTweaks.getInstance().getDataFolder(), "advancements.db")));
        ultimateAdvancementAPI = UltimateAdvancementAPI.getInstance(TeaksTweaks.getInstance());
        if (Config.getBoolean("packs.more-mob-heads.advancements.enabled")) {
            setupMoreMobHeads();
        }
    }

    public void setupMoreMobHeads() {
        AdvancementTab tab = ultimateAdvancementAPI.createAdvancementTab("more_mob_heads");

        ItemStack icon = MoreMobHeads.createhead(ALLAY_TEXTURE, "Allay Head", Sound.ENTITY_ALLAY_AMBIENT_WITH_ITEM);

        RootAdvancement root = new RootAdvancement(tab, "more_mob_heads_root", new AdvancementDisplay(icon, "More Mob Heads", AdvancementFrameType.CHALLENGE, true, true, 0, 0, "Gotta catch em' all!"), "textures/block/bricks.png");
        tabs.put("more_mob_heads", tab);
        roots.put("more_mob_heads", root);

        tab.getEventManager().register(tab, PlayerLoadingCompletedEvent.class, event -> {
            tab.showTab(event.getPlayer());
            tab.grantRootAdvancement(event.getPlayer());
        });
    }

    public MobHeadAdvancement getMobHeadAdvancement(TexturedHead entry, int index) {
        if (entry == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }

        String key = entry.key();
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Entry key cannot be null or empty");
        }

        return new MobHeadAdvancement(key, roots.get("more_mob_heads"), index);
    }

    public void registerMoreMobHeadsAdvancements(List<MobHeadAdvancement> advancements) {
        if (advancements == null || advancements.isEmpty()) {
            throw new IllegalArgumentException("Advancements list cannot be null or empty");
        }

        MobHeadAdvancement[] advancementsArray = advancements.toArray(new MobHeadAdvancement[0]);
        RootAdvancement root = roots.get("more_mob_heads");
        tabs.get("more_mob_heads").registerAdvancements(root, advancementsArray);
    }

    public void grant(Player player, AdvancementKey key) {
        Objects.requireNonNull(ultimateAdvancementAPI.getAdvancement(key)).grant(player);
    }

    public static class MobHeadAdvancement extends BaseAdvancement {

        public MobHeadAdvancement(@NotNull String key, @NotNull Advancement parent, int index) {
            super(key, getDisplay(key, index), parent);
        }

        private static AdvancementDisplay getDisplay(String key, int index) {
            ItemStack icon = MoreMobHeads.getHeadItem(key, Sound.ENTITY_PLAYER_BREATH);
            TexturedHead entry = (TexturedHead) MoreMobHeads.getHead(key);
            boolean showInChat = Config.getBoolean("packs.more-mob-heads.advancements.announce-in-chat");
            return new AdvancementDisplay(icon, entry.name(), AdvancementFrameType.GOAL, true, showInChat, 1, index, "Collect a " + entry.name());
        }
    }
}
