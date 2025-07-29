package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.packs.workstationhighlights.WorkstationHighlights;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class WorkstationHighlightCommand extends AbstractCommand {
    private static final String[] professionTypes = new String[] {
        "any",
        "armorer",
        "butcher",
        "cartographer",
        "cleric",
        "farmer",
        "fisherman",
        "fletcher",
        "leatherworker",
        "librarian",
        "mason",
        "shepherd",
        "toolsmith",
        "weaponsmith"
    };

    public WorkstationHighlightCommand() {
        super(TTCommand.WORKSTATIONHIGHLIGHT, "workstationhighlight");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("workstationhighlight")
                .requires(perm(Permission.COMMAND_SUDOKU))
                .executes(ctx -> {
                    Player player = checkPlayer(ctx);
                    if (player == null) return Command.SINGLE_SUCCESS;

                    highlight(player, "any", 8);
                    return Command.SINGLE_SUCCESS;
                })
                .then(Commands.argument("profession", StringArgumentType.word())
                        .executes(ctx -> {
                            Player player = checkPlayer(ctx);
                            if (player == null) return Command.SINGLE_SUCCESS;

                            String profession = ctx.getArgument("profession", String.class);
                            if (!Arrays.toString(professionTypes).contains(profession)) {
                                player.sendMessage(getError("invalid_profession"));
                                return Command.SINGLE_SUCCESS;
                            }

                            highlight(player, profession, 8);
                            return Command.SINGLE_SUCCESS;
                        })
                        .suggests(this::professionSuggestions)
                        .then(Commands.argument("radius", IntegerArgumentType.integer(1, 16))
                                .executes(ctx -> {
                                    Player player = checkPlayer(ctx);
                                    if (player == null) return Command.SINGLE_SUCCESS;

                                    String profession = ctx.getArgument("profession", String.class);
                                    if (!Arrays.toString(professionTypes).contains(profession)) {
                                        player.sendMessage(getError("invalid_profession"));
                                        return Command.SINGLE_SUCCESS;
                                    }

                                    int radius = ctx.getArgument("radius", Integer.class);
                                    highlight(player, profession, radius);
                                    return Command.SINGLE_SUCCESS;
                                })))
                .then(Commands.literal("clear")
                        .executes(this::clear))
                .build();
    }

    private void highlight(Player player, String profession, int radius) {
        Entity entity = null;
        double distance = Integer.MAX_VALUE;
        for (Entity e : player.getNearbyEntities(radius, radius, radius)) {
            if (e.getType() == EntityType.VILLAGER) {
                Villager villager = (Villager) e;
                if (villager.getProfession() == Villager.Profession.NONE) continue;
                if (villager.getProfession() == Villager.Profession.NITWIT) continue;

                if (!profession.equals("any") && !villager.getProfession().toString().toLowerCase().equals(profession)) continue;

                double d = e.getLocation().distanceSquared(player.getLocation());
                if (d < distance) {
                    entity = e;
                    distance = d;
                }
            }
        }

        if (entity == null) {
            player.sendMessage(getError("no_workstations_found"));
            return;
        }

        Villager villager = (Villager) entity;
        Location jobSite = villager.getMemory(MemoryKey.JOB_SITE);
        if (jobSite == null) {
            player.sendMessage(getError("no_job_site"));
            return;
        }

        villager.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 0, false, false, false));
        WorkstationHighlights.glowingBlock(jobSite, 200);
        createParticles(jobSite.add(.5, 1, .5));
        player.sendMessage(getText("found", insert("x", jobSite.getBlockX()), insert("y", jobSite.getBlockY()), insert("z", jobSite.getBlockZ())));
    }

    private int clear(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        WorkstationHighlights.clear();
        player.sendMessage(getText("cleared"));
        return Command.SINGLE_SUCCESS;
    }

    private void createParticles(Location location) {
        AreaEffectCloud e = (AreaEffectCloud) location.getWorld().spawnEntity(location, EntityType.AREA_EFFECT_CLOUD);
        e.setParticle(Particle.HEART);
        e.setRadius(.001F);
        e.setRadiusPerTick(0);
        e.setRadiusOnUse(0);
        e.setDuration(200);
        e.setWaitTime(10);
    }

    public CompletableFuture<Suggestions> professionSuggestions(final CommandContext<CommandSourceStack> ctx, final SuggestionsBuilder builder) {
        builder.restart();
        if (!(ctx.getSource().getSender() instanceof Player player)) return builder.buildFuture();
        for (String profession : professionTypes) {
            if (profession.toLowerCase().startsWith(builder.getRemainingLowerCase())) builder.suggest(profession);
        }
        return builder.buildFuture();
    }
}
