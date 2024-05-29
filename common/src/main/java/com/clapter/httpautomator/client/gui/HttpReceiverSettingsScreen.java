package com.clapter.httpautomator.client.gui;

import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.blockentity.HttpReceiverBlockEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultilineTextField;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;

public class HttpReceiverSettingsScreen extends Screen {

    private static Component TITLE = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_settings_screen");
    private static Component START_TEXT = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_startbutton");

    private final int screenWidth;
    private final int screenHeight;
    private int leftPos;
    private int topPos;
    private HttpReceiverBlockEntity blockEntity;

    private Button startButton;
    private MultilineTextField textField;

    public HttpReceiverSettingsScreen(HttpReceiverBlockEntity blockEntity) {
        super(TITLE);
        screenWidth = 176;
        screenHeight = 166;
        this.blockEntity = blockEntity;
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - screenWidth) / 2;
        this.topPos = (this.height - screenHeight) / 2;
        this.startButton = addRenderableWidget(startButton.builder(
                START_TEXT, this::handleStartButton)
                .bounds(leftPos, topPos, 20, 20)
                .build()
        );
    }

    private void handleStartButton(Button button){

    }

    @Override
    public void render(GuiGraphics $$0, int $$1, int $$2, float $$3) {
        super.render($$0, $$1, $$2, $$3);
    }
}
