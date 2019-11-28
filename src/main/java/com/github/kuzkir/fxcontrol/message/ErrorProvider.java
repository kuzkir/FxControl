/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kuzkir.fxcontrol.message;

import java.util.function.Supplier;
import javafx.animation.FadeTransition;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author kuzkir
 */
public class ErrorProvider extends ImageView {
    private Tooltip tooltip;
    
    public String getMessage() {
        return tooltip.getText();
    }
    
    public void setMessage(String message) {
        Tooltip.uninstall(this, tooltip);
        this.tooltip = new Tooltip(message);
        Tooltip.install(this, tooltip);
    }
    
    public ErrorProvider() {
        super(new Image(ErrorProvider.class.getResourceAsStream("/High Importance-16.png"),16.0,16.0,true,true));
        setVisible(false);
    }
    
    public boolean verify(Supplier<Boolean> supplier, String message) {
        setVisible(false);
        if(supplier.get())
            return true;
        
        setMessage(message);
        slowShow();
        return false;
    }
    
    public boolean verify(Supplier<Boolean> supplier) {
        setVisible(false);
        if(supplier.get())
            return true;
        
        slowShow();
        return false;
    }
    
    private void slowShow() {
        setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(500), this);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.play();
        
    }
}
