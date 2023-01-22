package me.sylv.duvethacks.command;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.unpackaged.ChatPacket;
import net.minecraft.unpackaged.InventoryUpdatePacket;
import net.minecraft.unpackaged.NetworkedClientPlayerEntity;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public final class Commands {
	public static final Map<String, Command> COMMANDS = new HashMap<>();
	public static String prefix = "h!";
	public static boolean flying = false;

	private Commands() {}

	public static void register(String identifier, Command command) {
		Commands.COMMANDS.put(identifier, command);
	}

	static {
	}
}
