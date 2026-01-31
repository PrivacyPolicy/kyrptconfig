package net.kyrptonaught.kyrptconfig.example;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;
import net.kyrptonaught.kyrptconfig.config.screen.ConfigScreen;
import net.kyrptonaught.kyrptconfig.config.screen.ConfigSection;
import net.kyrptonaught.kyrptconfig.config.screen.items.BooleanItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.ButtonItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.KeybindItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.TextItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.lists.BlockIconList;
import net.kyrptonaught.kyrptconfig.config.screen.items.lists.ItemIconList;
import net.kyrptonaught.kyrptconfig.config.screen.items.lists.StringList;
import net.kyrptonaught.kyrptconfig.config.screen.items.number.FloatItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.number.IntegerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;
import java.util.ArrayList;

public class ExampleConfig implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

        return (screen) -> {
            if (!FabricLoader.getInstance().isDevelopmentEnvironment())
                return null;

            // regex replace (\.setSaveConsumer\()([\S\h])*val\) & (options\.)([^\,])+
            ConfigScreen configScreen = new ConfigScreen(screen, Component.translatable("key.kyrptconfig.exampleconfig"));

            ConfigSection displaySection = new ConfigSection(configScreen, Component.translatable("Display"));
            displaySection.addConfigItem(new BooleanItem(Component.translatable("key.kyrptconfig.exampleconfig.displaysort"), true, true));
            displaySection.addConfigItem(new IntegerItem(Component.translatable("key.diggusmaximus.config.maxmine"), 40, 40).setMinMax(1, 2048));
            displaySection.addConfigItem(new KeybindItem(Component.translatable("key.inventorysorter.sort"), "key.keyboard.l", "key.keyboard.p"));

            FloatItem armorHudScale = (FloatItem) displaySection.addConfigItem(new FloatItem(Component.translatable("key.lemclienthelper.clientgui.armourscale"), 1f, 1f));
            armorHudScale.setMinMax(1f, 4f);
            armorHudScale.setSaveConsumer(System.out::println);
            armorHudScale.setToolTipWithNewLine("key.lemclienthelper.clientgui.armourscale.tooltip");

            for (int i = 0; i < 3; i++)
                displaySection.addConfigItem(new BooleanItem(Component.translatable("key.kyrptconfig.exampleconfig.displaytooltip"), true, true));

            ConfigSection blackListSection = new ConfigSection(configScreen, Component.translatable("Blacklist"));

            blackListSection.addConfigItem(new BooleanItem(Component.translatable("key.kyrptconfig.exampleconfig.showdebug"), true, false).setToolTipWithNewLine("key.kyrptconfig.exampleconfig.debugtooltip"));

            String DOWNLOAD_URL = "https://raw.githubusercontent.com/kyrptonaught/Inventory-Sorter/1.19/DownloadableBlacklist.json5";
            TextItem blackListURL = new TextItem(Component.translatable("key.kyrptconfig.exampleconfig.blacklistURL"), DOWNLOAD_URL, DOWNLOAD_URL).setMaxLength(1024);
            blackListSection.addConfigItem(blackListURL);

            StringList hideList = (StringList) new StringList(Component.translatable("key.kyrptconfig.exampleconfig.hidesort"), new ArrayList<>(), new ArrayList<>()).setToolTipWithNewLine("key.kyrptconfig.exampleconfig.hidetooltip");

            blackListSection.addConfigItem(new ButtonItem(Component.translatable("key.kyrptconfig.exampleconfig.downloadListButton")).setClickEvent(() -> {
                SystemToast.add(Minecraft.getInstance().getToastManager(), SystemToast.SystemToastId.NARRATOR_TOGGLE, Component.translatable("key.inventorysorter.toast.error"), Component.translatable("key.inventorysorter.toast.error2"));
            }));

            blackListSection.addConfigItem(hideList);

            blackListSection.addConfigItem(new BlockIconList(Component.translatable("key.diggusmaximus.config.blacklist"), new ArrayList<>(), new ArrayList<>(), true));

            blackListSection.addConfigItem(new ItemIconList(Component.translatable("key.diggusmaximus.config.itemList"), new ArrayList<>(), new ArrayList<>(), true));

            new ConfigSection(configScreen, Component.literal("Tab Test 1"));
            new ConfigSection(configScreen, Component.literal("Tab Test 2"));
            new ConfigSection(configScreen, Component.literal("Tab Test 3"));
            new ConfigSection(configScreen, Component.literal("Tab Test 4"));
            new ConfigSection(configScreen, Component.literal("Tab Test 5"));
            new ConfigSection(configScreen, Component.literal("Tab Test 6"));
            return configScreen;
        };
    }
}
