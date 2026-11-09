package com.titanmods.titanutilities.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;

// Final item with an enchantment glint and an animated rainbow name (each letter has its own hue).
public class TitanStarItem extends Item {
    private static final long CYCLE_MS = 2000L;

    public TitanStarItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        String key = getDescriptionId(stack);
        LanguageMap language = LanguageMap.getInstance();
        // A dedicated server has no mod translations: keep the normal translatable name there.
        if (!language.has(key)) {
            return super.getName(stack);
        }
        String text = language.getOrDefault(key);
        float offset = (System.currentTimeMillis() % CYCLE_MS) / (float) CYCLE_MS;
        IFormattableTextComponent name = new StringTextComponent("");
        for (int i = 0; i < text.length(); i++) {
            float hue = (offset + i / (float) text.length()) % 1.0f;
            int rgb = MathHelper.hsvToRgb(hue, 0.7f, 1.0f);
            name.append(new StringTextComponent(String.valueOf(text.charAt(i)))
                    .setStyle(Style.EMPTY.withColor(Color.fromRgb(rgb))));
        }
        return name;
    }
}
