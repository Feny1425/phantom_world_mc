package net.feny.phantom_world.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.networking.packet.ItemStackSyncS2CPacket;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier ITEM_SYNC = new Identifier(PhantomWorld.MOD_ID,"item_sync");

    public static void registerC2SPackets(){

    }
    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(ITEM_SYNC, ItemStackSyncS2CPacket::receive);
    }
}
