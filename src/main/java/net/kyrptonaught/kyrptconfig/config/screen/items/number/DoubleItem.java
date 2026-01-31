package net.kyrptonaught.kyrptconfig.config.screen.items.number;

import net.minecraft.network.chat.Component;

public class DoubleItem extends NumberItem<Double> {
    public DoubleItem(Component name, Double value, Double defaultValue) {
        super(name, value, defaultValue);
        setMinMax(Double.MIN_VALUE, Double.MAX_VALUE);
    }

    @Override
    public Double parseValue(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ignored) {
        }
        return 0d;
    }
}
