package com.clapter.httpautomator.client.gui;

import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.blockentity.HttpReceiverBlockEntity;
import com.clapter.httpautomator.client.gui.widgets.EditBoxPair;
import com.clapter.httpautomator.client.gui.widgets.ScrollableWidget;
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
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.*;
import java.util.List;

public class HttpReceiverSettingsScreen extends Screen {

    private static final Component TITLE = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_settings_screen");
    private static final Component ENDPOINT_TEXT = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_settings_screen_endpoint_text");
    private static final Component TYPE_TEXT = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_settings_screen_power_type");
    private static final Component START_TEXT = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_startbutton");
    private static final Component REDIRECT_TEXT = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_redirect_text");
    private static final Component PARAMETERS_TEXT = Component.translatable("gui."+ Constants.MOD_ID + ".http_receiver_parameters_text");

    private final int screenWidth;
    private final int screenHeight;
    private int leftPos;
    private int topPos;
    private final HttpReceiverBlockEntity blockEntity;

    private boolean forceMapInit;
    private Button startButton;
    private EditBox endpoint;
    private EditBox timer;
    private EditBox redirectBox;
    private ScrollableWidget scrollablePanel;
    private Button saveParameterCountButton;
    private EditBox parameterCountInput;
    private final List<EditBox> parameterFields;

    private String endpointText;
    private String timerText;
    private String redirectUrl;
    private EnumPoweredType poweredType = EnumPoweredType.SWITCH;
    private EnumTimerUnit timerUnit = EnumTimerUnit.TICKS;
    private int numberOfFields = 0;
    private String numberOfFieldsAsString = "";


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
        this.parameterFields = new ArrayList<>();
        this.forceMapInit = true;
    }

    private void readParameterMap() {
        Map<String, String> parameterMap = this.blockEntity.getValues().parameterMap;
        if(!parameterMap.isEmpty()){
            for(Map.Entry<String, String> entry : parameterMap.entrySet()){
                //System.out.println(entry.getKey() + ": " + entry.getValue());
                EditBox parBox = new EditBox(this.font, leftPos+40, topPos + 100, 50, 20, Component.literal("Parameter"));
                EditBox valBox = new EditBox(this.font, leftPos + 130, topPos + 100, 50, 20, Component.literal("Value"));
                this.parameterFields.add(parBox);
                this.parameterFields.add(valBox);
                parBox.setValue(entry.getKey());
                valBox.setValue(entry.getValue());

            }
        }
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - screenWidth) / 2;
        this.topPos = (this.height - screenHeight) / 2 - 80;
        this.startButton = addRenderableWidget(startButton.builder(
                START_TEXT, this::handleStartButton)
                .bounds(leftPos+74, topPos+200, 50, 20)
                .build()
        );
        if(this.forceMapInit) {
            this.readParameterMap();
            this.forceMapInit = false;
        }

        this.endpoint = new EditBox(font, leftPos, topPos + 6, 198, 20, Component.empty());
        this.endpoint.setResponder(text -> {
            endpointText = text;
        });
        MultiLineTextWidget endpointText = new MultiLineTextWidget(leftPos-70, topPos + 13, ENDPOINT_TEXT, this.font);
        addRenderableWidget(endpointText);
        MultiLineTextWidget typeText = new MultiLineTextWidget(leftPos-70, topPos + 47, TYPE_TEXT, this.font);
        addRenderableWidget(typeText);
        MultiLineTextWidget parameterText = new MultiLineTextWidget(leftPos-70, topPos + 110, PARAMETERS_TEXT, this.font);
        addRenderableWidget(parameterText);

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
        //StringWidget redirectText = new StringWidget(leftPos+50, topPos + 354, REDIRECT_TEXT, this.font);
        MultiLineTextWidget redirectText = new MultiLineTextWidget(leftPos-70, topPos + 79, REDIRECT_TEXT, this.font);
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
        this.parameterCountInput = new EditBox(this.font, leftPos, topPos + 100, 80, 20, Component.literal("Count"));
        this.parameterCountInput.setResponder(text -> {
            numberOfFieldsAsString = text;
        });
        this.addRenderableWidget(parameterCountInput);
        this.saveParameterCountButton = this.addRenderableWidget(saveParameterCountButton.builder(
                Component.literal("Set Count"),(button) -> {
                    if(numberOfFieldsAsString.isEmpty())return;
                    numberOfFields = Integer.parseInt(numberOfFieldsAsString);
                    createInputFields(numberOfFields);
                }).bounds(leftPos+100, topPos + 100, 100, 20).build());

        if(!this.parameterFields.isEmpty()) {
            this.drawParameters();
        }

        this.endpoint.insertText(this.endpointText);
        this.redirectBox.insertText(this.redirectUrl);
    }

    private void createInputFields(int numberOfFields){
        List<EditBox> containigBox = new ArrayList<>(this.parameterFields);
        this.parameterFields.clear();
        System.out.println(this.parameterFields);
        for(int i = 0; i < numberOfFields; i++){
            EditBox parBox = new EditBox(this.font, leftPos+40, topPos + 100, 50, 20, Component.literal("Parameter"));
            EditBox valBox = new EditBox(this.font, leftPos + 130, topPos + 100, 50, 20, Component.literal("Value"));
            this.parameterFields.add(parBox);
            this.parameterFields.add(valBox);
            if(containigBox.size()/2 > i){
                parBox.setValue(containigBox.get(i).getValue());
                valBox.setValue(containigBox.get(i+1).getValue());
            }
        }
        this.clearWidgets();
        this.init();
    }

    private void repositionEditBoxes() {
        //System.out.println("REPO");
        if(!this.parameterFields.isEmpty()){
            for(int i = 0; i < this.parameterFields.size(); i+=2){
                this.parameterFields.get(i).setX(this.leftPos+40);
                this.parameterFields.get(i+1).setX(this.leftPos+130);
            }

        }
    }
    private void drawParameters(){

        this.scrollablePanel = new ScrollableWidget(leftPos, topPos + 130, 100, 60, this.parameterFields);
        this.addRenderableWidget(scrollablePanel);
    }

    @Override
    public boolean mouseScrolled(double $$0, double $$1, double $$2, double $$3) {

        return super.mouseScrolled($$0, $$1, $$2, $$3);
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
            if(this.scrollablePanel != null){
                values.parameterMap = this.getParameterValues();
            }
            Services.PACKET_HANDLER.sendPacketToServer(new SUpdateHttpReceiverValuesPacket(
                    this.blockEntity.getBlockPos(),
                    values));
        }
    }

    private Map<String, String> getParameterValues(){
        return getParameterValuesFromScrollable(this.scrollablePanel);
    }

    //TODO: MOve to base
    @NotNull
    static Map<String, String> getParameterValuesFromScrollable(ScrollableWidget scrollablePanel) {
        List<EditBox> input = scrollablePanel.getInputBoxes();
        Map<String, String> parameterMap = new HashMap<String, String>();
        for(int i = 0; i < input.size(); i+=2){
            if(!input.get(i).getValue().isEmpty() && !input.get(i+1).getValue().isEmpty()){
                parameterMap.put(input.get(i).getValue(), input.get(i+1).getValue());
            }
        }
        return parameterMap;
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

    @Override
    public void resize(Minecraft $$0, int $$1, int $$2) {
        this.repositionEditBoxes();
        super.resize($$0, $$1, $$2);

    }

}
