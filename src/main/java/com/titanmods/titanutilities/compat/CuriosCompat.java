package com.titanmods.titanutilities.compat;

import com.titanmods.titanutilities.item.RaidKingItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.InterModComms;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

// Only loaded when Curios is installed (always guard calls with ModList.isLoaded("curios")).
public class CuriosCompat {
    public static final String MOD_ID = "curios";

    // Asks Curios to create the "head" slot (Raid King crown).
    public static void sendImc() {
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
    }

    public static ItemStack findRaidKing(PlayerEntity player) {
        return CuriosApi.getCuriosHelper()
                .findEquippedCurio(stack -> stack.getItem() instanceof RaidKingItem, player)
                .map(found -> found.getRight())
                .orElse(ItemStack.EMPTY);
    }
}
