package com.clapter.httpautomator.client.gui;

import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.blockentity.HttpReceiverBlockEntity;
import com.clapter.httpautomator.enums.EnumPoweredType;
import com.clapter.httpautomator.enums.EnumTimerUnit;
import com.clapter.httpautomator.network.packet.SUpdateHttpReceiverValuesPacket;
import com.clapter.httpautomator.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class HttpReceiverSettingsScreen extends Screen {

    private static Component TITLE = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_settings_screen");
    private static Component ENDPOINT_TEXT = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_settings_screen_endpoint_text");
    private static Component TYPE_TEXT = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_settings_screen_power_type");
    private static Component START_TEXT = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_startbutton");
    private static Component REDIRECT_TEXT = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_redirect_text");

    private final int screenWidth;
    private final int screenHeight;
    private int leftPos;
    private int topPos;
    private HttpReceiverBlockEntity blockEntity;

    private Button startButton;
    private EditBox endpoint;
    private EditBox timer;
    private EditBox redirectBox;

    private String endpointText;
    private String timerText;
    private String redirectUrl;
    private EnumPoweredType poweredType = EnumPoweredType.SWITCH;
    private EnumTimerUnit timerUnit = EnumTimerUnit.TICKS;

    public HttpReceiverSettingsScreen(HttpReceiverBlockEntity blockEntity) {
        super(TITLE);
        screenWidth = 176;
        screenHeight = 166;
        this.blockEntity = blockEntity;
        this.endpointText = this.blockEntity.getValues().url;
        this.poweredType = this.blockEntity.getValues().poweredType;
        this.timerText = Float.toString(this.blockEntity.getValues().timer);
        this.timerUnit = this.blockEntity.getValues().timerUnit;
        this.redirectUrl = this.blockEntity.getValues().redirectClientUrl;
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - screenWidth) / 2;
        this.topPos = (this.height - screenHeight) / 2;
        this.startButton = addRenderableWidget(startButton.builder(
                START_TEXT, this::handleStartButton)
                .bounds(leftPos+74, topPos+100, 50, 20)
                .build()
        );

        this.endpoint = new EditBox(font, leftPos, topPos + 6, 198, 20, Component.empty());
        this.endpoint.setResponder(text -> {
            endpointText = text;
        });
        StringWidget endpointText = new StringWidget(leftPos+50, topPos + 206, ENDPOINT_TEXT, this.font);
        addRenderableWidget(endpointText);
        StringWidget typeText = new StringWidget(leftPos+50, topPos + 280, TYPE_TEXT, this.font);
        addRenderableWidget(typeText);

        CycleButton<EnumPoweredType> enumButton = CycleButton.builder(EnumPoweredType::getComponent)
                .withValues(EnumPoweredType.values())
                .withInitialValue(this.poweredType)
                .create(leftPos, topPos + 40, 100, 20, Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_settings_screen_current_type")
                , (but, value) -> { this.handlePoweredTypeSwitch(value); });
        addRenderableWidget(enumButton);
        addRenderableWidget(endpoint);

        this.redirectBox = new EditBox(font, leftPos, topPos + 74, 198, 20, Component.empty());
        this.redirectBox.setResponder(text -> {
            redirectUrl = text;
        });
        addRenderableWidget(redirectBox);
        StringWidget redirectText = new StringWidget(leftPos+50, topPos + 354, REDIRECT_TEXT, this.font);
        addRenderableWidget(redirectText);

        if(this.poweredType == EnumPoweredType.TIMER){
            //DRAW ADDITIONAL
            this.timer = new EditBox(font, leftPos+110, topPos + 40, 50, 20, Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_settings_screen_timer_input"));
            this.timer.setResponder(text -> {
                timerText = text;
            });
            addRenderableWidget(this.timer);

            CycleButton<EnumTimerUnit> enumButton1 = CycleButton.builder(EnumTimerUnit::getComponent)
                    .withValues(EnumTimerUnit.values())
                    .withInitialValue(this.timerUnit)
                    .create(leftPos+170, topPos + 40, 30, 20, Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_settings_screen_current_timer_unit")
                            , (but, value) -> { this.timerUnit = value; });
            addRenderableWidget(enumButton1);

            this.timer.insertText(this.timerText);
        }

        this.endpoint.insertText(this.endpointText);
        this.redirectBox.insertText(this.redirectUrl);
    }

    private void handlePoweredTypeSwitch(EnumPoweredType type){
        this.poweredType = type;
        //REDRAW WIDGETS
        this.clearWidgets();
        this.init();
    }

    private void handleStartButton(Button button){
        if(this.checkValues()){
            //SEND UPDATE PACKET TO SERVER
            HttpReceiverBlockEntity.Values values = blockEntity.getValues();
            values.url = this.endpointText;
            values.poweredType = this.poweredType;
            if(this.timerText != null && !this.timerText.isEmpty())
                values.timer = Float.parseFloat(this.timerText);
            values.timerUnit = this.timerUnit;
            values.redirectClientUrl = redirectUrl;
            Services.PACKET_HANDLER.sendPacketToServer(new SUpdateHttpReceiverValuesPacket(
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
