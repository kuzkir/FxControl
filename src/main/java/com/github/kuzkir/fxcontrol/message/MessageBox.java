/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kuzkir.fxcontrol.message;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

/**
 *
 * @author kuzkir
 */
public class MessageBox {

    public static boolean askQuestion(String operation, String question) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        try {
            InputStream in = MessageBox.class.getResourceAsStream("/Help-30.png");
            ImageView iv = new ImageView(new Image(in));
            a.setGraphic(iv);
            a.initStyle(StageStyle.UTILITY);
        } catch (Exception ex) {
        }
        a.setTitle(operation);
        a.setHeaderText(question);

        ButtonType btnYes = new ButtonType("Да", ButtonData.YES);
        ButtonType btnNo = new ButtonType("Нет", ButtonData.NO);

        a.getButtonTypes().setAll(btnYes,btnNo);

        Optional<ButtonType> result = a.showAndWait();
        if (result.get().equals(btnYes)) {
            return true;
        } else {
            return false;
        }
    }

    public static void showException(String operation, Exception e) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        try {
            InputStream in = MessageBox.class.getResourceAsStream("/High Importance-30.png");
            ImageView iv = new ImageView(new Image(in));
            a.setGraphic(iv);
            a.initStyle(StageStyle.UTILITY);
        } catch (Exception ex) {
        }

        a.setHeaderText(e.getClass().getSimpleName());
        a.setContentText(e.getMessage());
        a.setTitle(operation);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 0);

        a.getDialogPane().setExpandableContent(expContent);

        a.showAndWait();
    }
    
    public static void showWarning(String operation, String text) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        try {
            InputStream in = MessageBox.class.getResourceAsStream("/Error-30.png");
            ImageView iv = new ImageView(new Image(in));
            a.setGraphic(iv);
            a.initStyle(StageStyle.UTILITY);
        } catch (Exception ex) {
        }

        a.setHeaderText(text);
        a.setTitle(operation);
        
        a.showAndWait();
    }
    
    public static void showInfo(String operation, String text) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        try {
            InputStream in = MessageBox.class.getResourceAsStream("/Info-30.png");
            ImageView iv = new ImageView(new Image(in));
            a.setGraphic(iv);
            a.initStyle(StageStyle.UTILITY);
        } catch (Exception ex) {
        }

        a.setHeaderText(text);
        a.setTitle(operation);
        
        a.showAndWait();
    }
}
