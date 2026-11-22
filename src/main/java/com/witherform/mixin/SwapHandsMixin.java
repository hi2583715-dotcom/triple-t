package com.witherform.mixin;

import com.witherform.WitherForm;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Turns the "swap hands" key (F by default) into the triple skull blast while the
 * Wither Skull is in the offhand. Hold SHIFT + F to swap normally.
 */
@Mixin(ServerGamePacketListenerImpl.class)
public abstract class SwapHandsMixin {

	@Shadow
	public ServerPlayer player;

	@Inject(method = "handlePlayerAction", at = @At("HEAD"), cancellable = true)
	private void witherform$swapHands(ServerboundPlayerActionPacket packet, CallbackInfo ci) {
		if (packet.getAction() != ServerboundPlayerActionPacket.Action.SWAP_ITEM_WITH_OFFHAND) {
			return;
		}
		// Packets arrive on the network thread first; the vanilla handler re-queues itself
		// onto the server thread, so we only act once we are on it.
		MinecraftServer server = this.player.level().getServer();
		if (server == null || !server.isSameThread()) {
			return;
		}
		if (this.player.isShiftKeyDown() || !WitherForm.isForm(this.player.getOffhandItem())) {
			return;
		}
		WitherForm.triple(this.player);
		ci.cancel();
	}
}
