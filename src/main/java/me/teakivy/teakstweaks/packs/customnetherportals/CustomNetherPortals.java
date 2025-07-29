package me.teakivy.teakstweaks.packs.customnetherportals;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Orientable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CustomNetherPortals extends BasePack {

    int portalMaxWidth = 23;
    int portalMaxHeight = 23;
    HashSet<Material> portalFrameMaterials = new HashSet<>();
    HashSet<Biome> endBiomes = new HashSet<>();
    ConcurrentHashMap<Block, Integer> validPortalBlocksMap = new ConcurrentHashMap<>();
    Set<Block> validPortalBlocks;
    ConcurrentHashMap<Block, Integer> checkedBlocksMap;
    Set<Block> checkedBlocks;
    BlockFace leftFace;
    BlockFace rightFace;
    BlockFace upFace;
    BlockFace downFace;
    String portalAxis;

    public CustomNetherPortals() {
        super(TTPack.CUSTOM_NETHER_PORTALS, Material.FLINT_AND_STEEL);
    }

    public void ArbitraryPortals() {
        this.validPortalBlocks = this.validPortalBlocksMap.keySet(0);
        this.checkedBlocksMap = new ConcurrentHashMap<>();
        this.checkedBlocks = this.checkedBlocksMap.keySet(0);
        this.upFace = BlockFace.UP;
        this.downFace = BlockFace.DOWN;

        this.portalMaxWidth = getConfig().getInt("max-portal-width");
        this.portalMaxHeight = getConfig().getInt("max-portal-height");
    }

    public void init() {
        super.init();

        this.portalFrameMaterials.add(Material.OBSIDIAN);
        if (getConfig().getBoolean("allow-crying-obsidian")) {
            this.portalFrameMaterials.add(Material.CRYING_OBSIDIAN);
        }
        this.endBiomes.add(Biome.END_BARRENS);
        this.endBiomes.add(Biome.END_HIGHLANDS);
        this.endBiomes.add(Biome.END_MIDLANDS);
        this.endBiomes.add(Biome.SMALL_END_ISLANDS);
        this.endBiomes.add(Biome.THE_END);
        ArbitraryPortals();
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) throws NullPointerException {
        Block block = event.getBlock();
        if (!this.endBiomes.contains(block.getBiome())) {
            this.validPortalBlocks.clear();
            this.validPortalBlocksMap.clear();
            this.checkedBlocks.clear();
            this.checkedBlocksMap.clear();
            if (this.portalFrameMaterials.contains(block.getRelative(BlockFace.DOWN).getType())) {
                boolean buildPortal = true;
                this.findPortalDirection(block);
                if (this.leftFace != null) {
                    this.validPortalBlocks.add(block);

                    while(this.validPortalBlocks.size() <= this.portalMaxHeight * this.portalMaxWidth && this.checkedBlocks.size() < this.validPortalBlocks.size()) {
                        Iterator<Block> var4 = this.validPortalBlocks.iterator();

                        label79: {
                            Block validBlock;
                            do {
                                do {
                                    if (!var4.hasNext()) {
                                        break label79;
                                    }

                                    validBlock = (Block)var4.next();
                                } while(this.checkedBlocks.contains(validBlock));

                                this.checkedBlocks.add(validBlock);
                            } while(this.checkValidPortalBlock(validBlock, this.leftFace) && this.checkValidPortalBlock(validBlock, this.rightFace) && this.checkValidPortalBlock(validBlock, this.upFace) && this.checkValidPortalBlock(validBlock, this.downFace));

                            buildPortal = false;
                        }

                        if (!buildPortal) {
                            break;
                        }
                    }

                    if (buildPortal && this.validPortalBlocks.size() >= getConfig().getInt("minumum-portal-size")) {
                        int xMax = block.getX();
                        int zMax = block.getZ();
                        int yMax = block.getY();
                        int xMin = xMax;
                        int zMin = zMax;
                        int yMin = yMax;
                        int xWidth = 1;
                        int zWidth = 1;
                        int height = 1;
                        Iterator<Block> var14 = this.validPortalBlocks.iterator();

                        ArrayList<Block> portalBlocks = new ArrayList<>();

                        while(var14.hasNext()) {
                            Block validBlock = (Block)var14.next();
                            int x = validBlock.getX();
                            int y = validBlock.getY();
                            int z = validBlock.getZ();
                            xMax = Math.max(xMax, x);
                            xMin = Math.min(xMin, x);
                            zMax = Math.max(zMax, z);
                            zMin = Math.min(zMin, z);
                            yMax = Math.max(yMax, y);
                            yMin = Math.min(yMin, y);

                            portalBlocks.add(validBlock);

                        }

                        Bukkit.getScheduler().scheduleSyncDelayedTask(TeaksTweaks.getInstance(), () -> {
                            for (Block portalBlock : portalBlocks) {
                                portalBlock.setType(Material.NETHER_PORTAL);
                                if (portalAxis.equals("z")) {
                                    Orientable rotatable = (Orientable) portalBlock.getBlockData();
                                    rotatable.setAxis(Axis.Z);
                                    portalBlock.setBlockData(rotatable);
                                }
                            }
                        }, 1L);
                        event.setCancelled(buildPortal);
                    }
                }
            }

        }
    }

    public void findPortalDirection(Block fireBlock) {
        World world = fireBlock.getWorld();
        Location location = fireBlock.getLocation();
        Vector northVector = new Vector(0, 0, -1);
        Vector southVector = new Vector(0, 0, 1);
        Vector eastVector = new Vector(1, 0, 0);
        Vector westVector = new Vector(-1, 0, 0);
        RayTraceResult northResult = world.rayTraceBlocks(location, northVector, this.portalMaxWidth);
        RayTraceResult southResult = world.rayTraceBlocks(location, southVector, this.portalMaxWidth);
        RayTraceResult eastResult = world.rayTraceBlocks(location, eastVector, this.portalMaxWidth);
        RayTraceResult westResult = world.rayTraceBlocks(location, westVector, this.portalMaxWidth);
        if (this.rayTraceResultValid(northResult) && this.rayTraceResultValid(southResult)) {
            this.leftFace = BlockFace.NORTH;
            this.rightFace = BlockFace.SOUTH;
            this.portalAxis = "z";
        } else if (this.rayTraceResultValid(eastResult) && this.rayTraceResultValid(westResult)) {
            this.leftFace = BlockFace.EAST;
            this.rightFace = BlockFace.WEST;
            this.portalAxis = "x";
        }

    }

    public boolean rayTraceResultValid(RayTraceResult rayTraceResult) {
        try {
            Block rayTraceBlock = rayTraceResult.getHitBlock();
            return this.isObsidian(rayTraceBlock);
        } catch (Exception var4) {
            return false;
        }
    }

    public boolean checkValidPortalBlock(Block validBlock, BlockFace face) {
        Block newBlock = validBlock.getRelative(face);
        if (this.isAir(newBlock)) {
            this.validPortalBlocks.add(newBlock);
            return true;
        } else {
            return this.isObsidian(newBlock);
        }
    }

    public boolean isAir(Block block) {
        return block != null && (block.getType().equals(Material.AIR) || block.getType().equals(Material.FIRE));
    }

    public boolean isObsidian(Block block) {
        return block != null && this.portalFrameMaterials.contains(block.getType());
    }

}
