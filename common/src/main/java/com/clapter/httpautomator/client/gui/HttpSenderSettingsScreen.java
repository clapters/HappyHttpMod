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

import java.util.ArrayList;
import java.util.List;

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

    private Button saveCountButton;
    private EditBox countInput;
    private final List<EditBox> parameterFields = new ArrayList<>();
    private final List<EditBox> parameterValuesFields = new ArrayList<>();
    private int numberOfFields = 0;

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

        countInput = new EditBox(this.font, this.width / 2 - 50, 20, 100, 20, Component.literal("Count"));
        this.addRenderableWidget(countInput);
        this.saveCountButton = this.addRenderableWidget(saveCountButton.builder(
                Component.literal("Set Count"),button -> {
                    numberOfFields = Integer.parseInt(countInput.getValue());
                    createInputFields(numberOfFields);
                }).bounds(this.width / 2 - 50, 50, 100, 20).build());
    }

    private void createInputFields(int numberOfFields) {
        // Clear existing input fields
        this.clearWidgets();
        this.init(); // Re-add the initial widgets (countInput and button)

        this.parameterFields.clear();

        // Create new input fields based on count
        for (int i = 0; i < numberOfFields; i++) {
            EditBox inputField = new EditBox(this.font, this.width / 2 - 50, 80 + i * 30, 100, 20, Component.literal("Parameter " + (i + 1)));
            EditBox valueField = new EditBox(this.font, this.width / 2 - +50, 80 + i * 30, 100, 20, Component.literal("Value " + (i + 1)));
            this.parameterFields.add(inputField);
            this.parameterValuesFields.add(valueField);
            this.addRenderableWidget(inputField);
            this.addRenderableWidget(valueField);
        }
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
