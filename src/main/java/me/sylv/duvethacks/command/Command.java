package me.sylv.duvethacks.command;

import net.minecraft.client.MinecraftClient;

@FunctionalInterface
public interface Command {
	void invoke(String[] args, MinecraftClient client);
}
