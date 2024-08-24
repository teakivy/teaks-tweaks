package me.teakivy.teakstweaks.packs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.entity.EntityDeathEvent;

public class ZombieVillagerHead extends BaseMobHead {

    public ZombieVillagerHead() {
        super(EntityType.ZOMBIE_VILLAGER, "zombie_villager", Sound.ENTITY_ZOMBIE_VILLAGER_AMBIENT);

        addHeadTexture("armorer", "Armorer Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzg2NzllMDM0NzY3ZDUxODY2MGQ5NDE2ZGM1ZWFmMzE5ZDY5NzY4MmFjNDBjODg2ZTNjMmJjOGRmYTFkZTFkIn19fQ");
        addHeadTexture("butcher", "Butcher Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWNjZThkNmNlNDEyNGNlYzNlODRhODUyZTcwZjUwMjkzZjI0NGRkYzllZTg1NzhmN2Q2ZDg5MjllMTZiYWQ2OSJ9fX0");
        addHeadTexture("cartographer", "Cartographer Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTYwODAwYjAxMDEyZTk2M2U3YzIwYzhiYTE0YjcwYTAyNjRkMTQ2YTg1MGRlZmZiY2E3YmZlNTEyZjRjYjIzZCJ9fX0");
        addHeadTexture("cleric", "Cleric Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjk1ODU3OGJlMGUxMjE3MjczNGE3ODI0MmRhYjE0OTY0YWJjODVhYjliNTk2MzYxZjdjNWRhZjhmMTRhMGZlYiJ9fX0");
        addHeadTexture("farmer", "Farmer Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjc3ZDQxNWY5YmFhNGZhNGI1ZTA1OGY1YjgxYmY3ZjAwM2IwYTJjOTBhNDgzMWU1M2E3ZGJjMDk4NDFjNTUxMSJ9fX0");
        addHeadTexture("fisherman", "Fisherman Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjkwNWQ1M2ZlNGZhZWIwYjMxNWE2ODc4YzlhYjgxYjRiZTUyYzMxY2Q0NzhjMDI3ZjBkN2VjZTlmNmRhODkxNCJ9fX0");
        addHeadTexture("fletcher", "Fletcher Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmVhMjZhYzBlMjU0OThhZGFkYTRlY2VhNThiYjRlNzZkYTMyZDVjYTJkZTMwN2VmZTVlNDIxOGZiN2M1ZWY4OSJ9fX0");
        addHeadTexture("leatherworker", "Leatherworker Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI1NTJjOTBmMjEyZTg1NWQxMjI1NWQ1Y2Q2MmVkMzhiOWNkN2UzMGU3M2YwZWE3NzlkMTc2NDMzMGU2OTI2NCJ9fX0");
        addHeadTexture("librarian", "Librarian Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIyMTFhMWY0MDljY2E0MjQ5YzcwZDIwY2E4MDM5OWZhNDg0NGVhNDE3NDU4YmU5ODhjYzIxZWI0Nzk3Mzc1ZSJ9fX0");
        addHeadTexture("mason", "Mason Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI1NTJjOTBmMjEyZTg1NWQxMjI1NWQ1Y2Q2MmVkMzhiOWNkN2UzMGU3M2YwZWE3NzlkMTc2NDMzMGU2OTI2NCJ9fX0");
        addHeadTexture("nitwit", "Nitwit Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI1NTJjOTBmMjEyZTg1NWQxMjI1NWQ1Y2Q2MmVkMzhiOWNkN2UzMGU3M2YwZWE3NzlkMTc2NDMzMGU2OTI2NCJ9fX0");
        addHeadTexture("unemployed", "Unemployed Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI1NTJjOTBmMjEyZTg1NWQxMjI1NWQ1Y2Q2MmVkMzhiOWNkN2UzMGU3M2YwZWE3NzlkMTc2NDMzMGU2OTI2NCJ9fX0");
        addHeadTexture("shepherd", "Shepherd Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjkxMzkxYmVmM2E0NmVmMjY3ZDNiNzE3MTA4NmJhNGM4ZDE3ZjJhNmIwZjgzZmEyYWMzMGVmZTkxNGI3YzI0OSJ9fX0");
        addHeadTexture("toolsmith", "Toolsmith Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI1NTJjOTBmMjEyZTg1NWQxMjI1NWQ1Y2Q2MmVkMzhiOWNkN2UzMGU3M2YwZWE3NzlkMTc2NDMzMGU2OTI2NCJ9fX0");
        addHeadTexture("weaponsmith", "Weaponsmith Zombie Villager Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM3MDg5NGI1Y2MzMDVkODdhYTA4YzNiNGIwODU4N2RiNjhmZjI5ZTdhM2VmMzU0Y2FkNmFiY2E1MGU1NTI4YiJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        ZombieVillager villager = (ZombieVillager) event.getEntity();

        Villager.Profession profession = villager.getVillagerProfession();
        if (profession == null) {
            return textures.get("unemployed");
        }
        String key = switch (VillagerProfession.fromProfession(villager.getVillagerProfession())) {
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
            default -> "unemployed";
        };

        return textures.get(key);
    }

    @Override
    public String getName(EntityDeathEvent event) {
        ZombieVillager villager = (ZombieVillager) event.getEntity();

        Villager.Profession profession = villager.getVillagerProfession();
        if (profession == null) {
            return "Unemployed Zombie Villager";
        }
        String name = switch (VillagerProfession.fromProfession(villager.getVillagerProfession())) {
            case ARMORER -> "Armorer";
            case BUTCHER -> "Butcher";
            case CARTOGRAPHER -> "Cartographer";
            case CLERIC -> "Cleric";
            case FARMER -> "Farmer";
            case FISHERMAN -> "Fisherman";
            case FLETCHER -> "Fletcher";
            case LEATHERWORKER -> "Leatherworker";
            case LIBRARIAN -> "Librarian";
            case MASON -> "Mason";
            case NITWIT -> "Nitwit";
            case SHEPHERD -> "Shepherd";
            case TOOLSMITH -> "Toolsmith";
            case WEAPONSMITH -> "Weaponsmith";
            case NONE -> "Unemployed";
        };

        return name + " Zombie Villager";
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
