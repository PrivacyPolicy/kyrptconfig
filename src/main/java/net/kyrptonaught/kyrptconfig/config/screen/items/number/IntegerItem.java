package net.kyrptonaught.kyrptconfig.config.screen.items.number;

import net.minecraft.network.chat.Component;

public class IntegerItem extends NumberItem<Integer> {
    public IntegerItem(Component name, int value, int defaultValue) {
        super(name, value, defaultValue);
        setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public Integer parseValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ignored) {
        }
        return 0;
    }
}
