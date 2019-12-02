/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kuzkir.fxcontrol.datetime;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

/**
 *
 * @author kuzkir
 */
public class Stopwatch extends Label {

    private final String ID = "stopwatchView";
    private final StopwatchVirtual virt = new StopwatchVirtual();
    private StopwatchVirtual.FormatProperties prop = new StopwatchVirtual.FormatProperties()
        .setLeadZero(true)
        .setLeadPart(StopwatchVirtual.LeadPart.MINUTE)
        .setSeparator(":")
        .setShowNano(true);
    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            textProperty().set(virt.getFormated(ID, prop));
        }
    };

    ;

    public Stopwatch() {
        super();

        initialize();
    }

    public Stopwatch(StopwatchVirtual.FormatProperties properties) {
        super();
        this.prop = properties;

        initialize();
    }

    private void initialize() {
        textProperty().set(virt.getFormated(ID, prop));
        getStyleClass().setAll("stopwatch");
//        setAccessibleRole(AccessibleRole.TEXT);
//        ((StyleableProperty<Boolean>) (WritableValue<Boolean>) focusTraversableProperty()).applyStyle(null, Boolean.FALSE);
    }

    public void start() {
        timer.start();
        virt.start(ID);
    }

    public void stop() {
        virt.stop(ID);
        timer.stop();
    }

    public void lap() {
        virt.lap(ID);
    }

    public long getMillis() {
        return virt.get(ID);
    }

    public String getFormatted() {
        return virt.getFormated(ID, prop);
    }

    public final void setShowNano(boolean show) {
        prop.setShowNano(show);
        textProperty().set(virt.getFormated(ID, prop));
    }

    public final boolean isShowNano() {
        return prop.isShowNano();
    }

    public final void setLeadZero(boolean lead) {
        prop.setLeadZero(lead);
        textProperty().set(virt.getFormated(ID, prop));
    }

    public final boolean isLeadZero() {
        return prop.isLeadZero();
    }

    public final void setSeparator(String separator) {
        prop.setSeparator(separator);
        textProperty().set(virt.getFormated(ID, prop));
    }

    public final String getSeparator() {
        return prop.getSeparator();
    }
    
    public final void setLeadPart(StopwatchVirtual.LeadPart leadPart) {
        prop.setLeadPart(leadPart);
        textProperty().set(virt.getFormated(ID, prop));
    }
    
    public final StopwatchVirtual.LeadPart getLeadPart() {
        return prop.getLeadPart();
    }
}
