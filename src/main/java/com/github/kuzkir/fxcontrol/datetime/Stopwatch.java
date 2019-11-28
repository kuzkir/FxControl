/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kuzkir.fxcontrol.datetime;

import javafx.beans.value.WritableValue;
import javafx.css.StyleableProperty;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 *
 * @author kuzkir
 */
public class Stopwatch extends Label {
    
    private String value = "";
    private StopwatchVirtual.FormatProperties prop;

    public Stopwatch() {
        prop = new StopwatchVirtual.FormatProperties()
            .setFixLength(true)
            .setLeadPart(StopwatchVirtual.FormatProperties.LEAD_MINUTE)
            .setSeparator(":")
            .setShowNano(true);
        
        initialize();
    }
    
    public Stopwatch(String text, StopwatchVirtual.FormatProperties properties) {
        super(text);
        initialize();
    }

    public Stopwatch(String text, Node graphic) {
        super(text, graphic);
        initialize();
    }
    
    private void initialize() {
        getStyleClass().setAll("stopwatch");
        setAccessibleRole(AccessibleRole.TEXT);
        ((StyleableProperty<Boolean>)(WritableValue<Boolean>)focusTraversableProperty()).applyStyle(null, Boolean.FALSE);
    }
    
    
    

    
    
}
