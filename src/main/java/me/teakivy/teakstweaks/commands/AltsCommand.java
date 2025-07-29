package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.packs.spectatoralts.SpectatorAlts;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class AltsCommand extends AbstractCommand {

    public AltsCommand() {
        super(TTCommand.ALTS, "alts");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("alts")
                .requires(perm(Permission.COMMAND_ALTS))
                .then(Commands.literal("list")
                        .executes((ctx) -> {
                            Player player = checkPlayer(ctx);
                            if (player == null) return Command.SINGLE_SUCCESS;

                            list(player, player.getUniqueId());
                            return Command.SINGLE_SUCCESS;
                        }).then(Commands.argument("player", StringArgumentType.word()).executes((ctx) -> {
                            Player player = checkPlayer(ctx);
                            if (player == null) return Command.SINGLE_SUCCESS;

                            String name = ctx.getArgument("player", String.class);
                            UUID uuid = getUUID(name);
                            if (uuid == null) {
                                player.sendMessage(ErrorType.PLAYER_DNE.m());
                                return Command.SINGLE_SUCCESS;
                            }
                            if (uuid != player.getUniqueId() && !player.hasPermission(Permission.COMMAND_ALTS_MANAGE.getPermission())) {
                                player.sendMessage(getError("no_permission_modify_others"));
                                return Command.SINGLE_SUCCESS;
                            }

                            list(player, uuid);
                            return Command.SINGLE_SUCCESS;
                        })))
                .then(Commands.literal("add")
                        .then(Commands.argument("alt", StringArgumentType.word()).executes((ctx) -> {
                            Player player = checkPlayer(ctx);
                            if (player == null) return Command.SINGLE_SUCCESS;

                            String name = ctx.getArgument("alt", String.class);
                            UUID alt = getUUID(name);
                            add(player, player.getUniqueId(), alt);
                            return Command.SINGLE_SUCCESS;
                        })
                                .then(Commands.argument("player", StringArgumentType.word()).executes((ctx) -> {
                                    Player player = checkPlayer(ctx);
                                    if (player == null) return Command.SINGLE_SUCCESS;

                                    String altName = ctx.getArgument("alt", String.class);
                                    UUID alt = getUUID(altName);
                                    String mainName = ctx.getArgument("player", String.class);
                                    UUID main = getUUID(mainName);

                                    if (main != null && !main.equals(player.getUniqueId()) && !player.hasPermission(Permission.COMMAND_ALTS_MANAGE.getPermission())) {
                                        player.sendMessage(getError("no_permission_modify_others"));
                                        return Command.SINGLE_SUCCESS;
                                    }
                                    add(player, main, alt);
                                    return Command.SINGLE_SUCCESS;
                                }))))
                .then(Commands.literal("remove")
                        .then(Commands.argument("alt", StringArgumentType.word()).executes((ctx) -> {
                                    Player player = checkPlayer(ctx);
                                    if (player == null) return Command.SINGLE_SUCCESS;

                                    String name = ctx.getArgument("alt", String.class);
                                    UUID alt = getUUID(name);
                                    remove(player, player.getUniqueId(), alt);
                                    return Command.SINGLE_SUCCESS;
                                })
                                .then(Commands.argument("player", StringArgumentType.word()).executes((ctx) -> {
                                    Player player = checkPlayer(ctx);
                                    if (player == null) return Command.SINGLE_SUCCESS;

                                    String altName = ctx.getArgument("alt", String.class);
                                    UUID alt = getUUID(altName);
                                    String mainName = ctx.getArgument("player", String.class);
                                    UUID main = getUUID(mainName);

                                    if (main != null && !main.equals(player.getUniqueId()) && !player.hasPermission(Permission.COMMAND_ALTS_MANAGE.getPermission())) {
                                        player.sendMessage(getError("no_permission_modify_others"));
                                        return Command.SINGLE_SUCCESS;
                                    }
                                    remove(player, main, alt);
                                    return Command.SINGLE_SUCCESS;
                                }))))
                .build();
    }

    private void list(Player player, UUID uuid) {
        List<UUID> alts = SpectatorAlts.getAlts(uuid);

        if (alts.isEmpty()) {
            player.sendMessage(getError("no_alts", insert("player", getName(uuid))));
            return;
        }

        player.sendMessage(getText("list_alts", insert("player", getName(uuid))));
        for (UUID altAcc : alts) {
            player.sendMessage(getText("listed_alt", insert("alt", getName(altAcc))));
        }
    }

    private void add(Player player, UUID main, UUID alt) {
        if (main == null || alt == null) {
            player.sendMessage(ErrorType.PLAYER_DNE.m());
            return;
        }

        if (main.equals(alt)) {
            player.sendMessage(getError("self"));
            return;
        }

        if (!canAddAlt(player)) {
            player.sendMessage(getError("max_alts"));
            return;
        }
        for (OfflinePlayer whitelisted : Bukkit.getWhitelistedPlayers()) {
            if (whitelisted.getUniqueId().equals(alt)) {
                player.sendMessage(getError("whitelisted"));
                return;
            }
        }

        if (SpectatorAlts.isAlt(alt)) {
            player.sendMessage(getError("already_alt", insert("alt", getName(alt))));
            return;
        }

        SpectatorAlts.addAlt(main, alt);
        player.sendMessage(getText("added_alt", insert("alt", getName(alt)), insert("player", getName(main))));
    }

    private void remove(Player player, UUID main, UUID alt) {
        if (main == null || alt == null) {
            player.sendMessage(ErrorType.PLAYER_DNE.m());
            return;
        }

        if (!SpectatorAlts.isAlt(alt)) {
            player.sendMessage(getError("not_alt", insert("alt", getName(alt))));
            return;
        }

        SpectatorAlts.removeAlt(alt);
        player.sendMessage(getText("removed_alt", insert("alt", getName(alt)), insert("player", getName(main))));
    }

    private String getName(UUID uuid) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        if (player.getName() == null) return uuid.toString();
        return player.getName();
    }

    public boolean canAddAlt(Player player) {
        if (player.isOp()) return true;
        if (getPackConfig().getInt("max-alts") == -1) return true;
        return SpectatorAlts.getAlts(player.getUniqueId()).size() < getPackConfig().getInt("max-alts");
    }

    private UUID getUUID(String name) {
        for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
            if (p.getName() != null && p.getName().equalsIgnoreCase(name)) {
                return p.getUniqueId();
            }
        }
        return null;
    }
}
