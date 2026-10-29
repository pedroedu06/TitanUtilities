package com.titanmods.titanutilities.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup TITAN_UTILITIES = new ItemGroup("titanutilities") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TITAN_FACTORY.get());
        }
    };
}
