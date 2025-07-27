package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class ZombieVillagerHead extends AdvancedMobHead {

    public ZombieVillagerHead() {
        super(EntityType.ZOMBIE_VILLAGER, Sound.ENTITY_ZOMBIE_VILLAGER_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        ZombieVillager zombieVillager = (ZombieVillager) event.getEntity();
        String type = switch (Objects.requireNonNull(VillagerProfession.fromProfession(zombieVillager.getVillagerProfession()))) {
            case ARMORER -> "armorer";
            case BUTCHER -> "butcher";
            case CARTOGRAPHER -> "cartographer";
            case CLERIC -> "cleric";
            case FARMER -> "farmer";
            case FISHERMAN -> "fisherman";
            case FLETCHER -> "fletcher";
            case LEATHERWORKER -> "leatherworker";
            case LIBRARIAN -> "librarian";
            case MASON -> "mason";
            case NITWIT -> "nitwit";
            case SHEPHERD -> "shepherd";
            case TOOLSMITH -> "toolsmith";
            case WEAPONSMITH -> "weaponsmith";
            case NONE -> "unemployed";
        };
        return type + "_zombie_villager";
    }

    protected enum VillagerProfession {
        ARMORER(Villager.Profession.ARMORER),
        BUTCHER(Villager.Profession.BUTCHER),
        CARTOGRAPHER(Villager.Profession.CARTOGRAPHER),
        CLERIC(Villager.Profession.CLERIC),
        FARMER(Villager.Profession.FARMER),
        FISHERMAN(Villager.Profession.FISHERMAN),
        FLETCHER(Villager.Profession.FLETCHER),
        LEATHERWORKER(Villager.Profession.LEATHERWORKER),
        LIBRARIAN(Villager.Profession.LIBRARIAN),
        MASON(Villager.Profession.MASON),
        NITWIT(Villager.Profession.NITWIT),
        SHEPHERD(Villager.Profession.SHEPHERD),
        TOOLSMITH(Villager.Profession.TOOLSMITH),
        WEAPONSMITH(Villager.Profession.WEAPONSMITH),
        NONE(Villager.Profession.NONE);

        private final Villager.Profession profession;

        VillagerProfession(Villager.Profession profession) {
            this.profession = profession;
        }

        public Villager.Profession getProfession() {
            return profession;
        }

        public static VillagerProfession fromProfession(Villager.Profession profession) {
            for (VillagerProfession value : values()) {
                if (value.profession == profession) return value;
            }
            return null;
        }
    }
}
