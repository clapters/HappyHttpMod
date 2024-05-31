package com.clapter.httpautomator.client.gui;

import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.blockentity.HttpReceiverBlockEntity;
import com.clapter.httpautomator.blockentity.HttpSenderBlockEntity;
import com.clapter.httpautomator.network.packet.SUpdateHttpReceiverValuesPacket;
import com.clapter.httpautomator.network.packet.SUpdateHttpSenderValuesPacket;
import com.clapter.httpautomator.platform.Services;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HttpSenderSettingsScreen extends Screen {

    private static Component TITLE = Component.translatable("gui."+ Constants.MOD_ID + ".http_sender_settings_screen");
    private static Component START_TEXT = Component.translatable("gui."+ Constants.MOD_ID + ".http_sender_startbutton");

    private final int screenWidth;
    private final int screenHeight;
    private int leftPos;
    private int topPos;
    private HttpSenderBlockEntity blockEntity;

    private Button startButton;
    private EditBox endpoint;

    private String endpointText;


    public HttpSenderSettingsScreen(HttpSenderBlockEntity blockEntity) {
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
                .bounds(leftPos, topPos+10, 50, 20)
                .build()
        );
        this.endpoint = new EditBox(font, leftPos + 50, topPos + 30 - 24, 198, 20, Component.empty());
        this.endpoint.setResponder(text -> {
            endpointText = text;
        });
        endpoint.insertText(blockEntity.getValues().url);
        addRenderableWidget(endpoint);

    }

    private void handleStartButton(Button button){
        if(this.checkValues()){
            //SEND UPDATE PACKET TO SERVER
            HttpSenderBlockEntity.Values values = blockEntity.getValues();
            values.url = this.endpointText;
            Services.PACKET_HANDLER.sendPacketToServer(new SUpdateHttpSenderValuesPacket(
                    this.blockEntity.getBlockPos(),
                    values));
        }
    }

    private boolean checkValues(){
        return endpointText != null && !endpointText.isEmpty();
    }

    @Override
    public void render(GuiGraphics $$0, int $$1, int $$2, float $$3) {
        super.render($$0, $$1, $$2, $$3);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
