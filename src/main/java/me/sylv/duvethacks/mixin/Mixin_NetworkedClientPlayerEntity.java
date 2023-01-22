package me.sylv.duvethacks.mixin;

import me.sylv.duvethacks.command.Command;
import me.sylv.duvethacks.command.Commands;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientNetworkHandler;
import net.minecraft.network.LoginState;
import net.minecraft.unpackaged.ChatPacket;
import net.minecraft.unpackaged.NetworkedClientPlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.entity.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.regex.Pattern;

@Mixin(NetworkedClientPlayerEntity.class)
public final class Mixin_NetworkedClientPlayerEntity extends ClientPlayerEntity {
	private Mixin_NetworkedClientPlayerEntity(MinecraftClient minecraft, World world, LoginState loginState, int i) {
		super(minecraft, world, loginState, i);
	}

	@Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
	private void onSendChatMessage(String message, CallbackInfo ci) {
		if (message.startsWith(Commands.prefix)) {
			ci.cancel();

			String[] split = message.split(" ");
			String cmdSplit = split[0].replaceFirst(Pattern.quote(Commands.prefix), "");
			String[] argSplit = Arrays.copyOfRange(split, 1, split.length);

			Command command = Commands.COMMANDS.getOrDefault(cmdSplit, (args, client) -> client.getNetworkHandler().a(new ChatPacket("Invalid command \"" + cmdSplit + "\". Type " + Commands.prefix + "help for a list of commands.")));

			command.invoke(argSplit, this.minecraft);
		}
	}
}
