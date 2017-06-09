/*
 * The MIT License
 *
 * Copyright 2017 Christoph Baumhardt.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gens.lsystems;

import general.GenController;
import general.GenModel;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Christoph Baumhardt
 */
public class BuilderGenController extends GenController {
    
    @FXML private TextField alphabet; 
    @FXML private TextField axiom;  
    @FXML private Label rules;
    @FXML private TextField rule;
    
    @FXML private TextField angle;    
    @FXML private TextField stroke;
    
    BuilderGenModel model;

    
    @Override
    public GenModel getModel() {
        return model;
    }
    
    /**
     * This automatically called method creates a new SimpleGenModel and 
     * links it with its view, so that changes on the view get reflected in the
     * model (if they are allowed in the model). 
     * 
     * Note that the view does not get updated if the model is changed from
     * anywhere else besides the very view.
     * 
     */      
    @Override
    public void initialize() {
        super.initialize(); // activate buttonGenerate on Enter
        
        model = new BuilderGenModel();
        
        // display values from model
        angle.textProperty().setValue(String.valueOf(model.getDeltaAngle()));
        stroke.textProperty().setValue(String.valueOf(model.getDeltaStroke()));
        alphabet.textProperty().setValue(String.valueOf(model.getAlphabet()));
        
        
        //this.addListener(angle, "setDeltaAngle");
        
        angle.focusedProperty().addListener((observableBoolean, oldValue, newValue) -> {
            if (!newValue){ // newValue=0 means no focus -> if no longer focused
                try {
                    String s = angle.textProperty().getValue();
                    double w = Double.parseDouble(s);
                    model.setDeltaAngle(w);
                    
                } catch (IllegalArgumentException ex) {
                    // display last valid value for width from model
                    angle.textProperty().setValue(String.valueOf(model.getDeltaAngle()));
                    showInputAlert("The angle requires an integer value between 1 and 360.");
                }
            }
        });
        
        stroke.focusedProperty().addListener((observableBoolean, oldValue, newValue) -> {
            if (!newValue){ // newValue=0 means no focus -> if no longer focused
                try {
                    String s = stroke.textProperty().getValue();
                    double w = Double.parseDouble(s);
                    model.setDeltaStroke(w);
                    
                } catch (IllegalArgumentException ex) {
                    // display last valid value for width from model
                    stroke.textProperty().setValue(String.valueOf(model.getDeltaStroke()));
                    showInputAlert("Stroke lenght was wrong.");
                }
            }
        });  
        
        alphabet.focusedProperty().addListener((observableBoolean, oldValue, newValue) -> {
            if (!newValue){ // newValue=0 means no focus -> if no longer focused
                try {
                    String s = alphabet.textProperty().getValue();
                    model.setAlphabet(s);
                    
                } catch (IllegalArgumentException ex) {
                    // display last valid value for width from model
                    alphabet.textProperty().setValue(model.getAlphabet());
                    showInputAlert("Illegal Alphabet");
                }
            }
        });       
        
    }
    
    @FXML
    public void handleAddRule(){
    
    }
    
    //-- TODO: das ding hier ist der Anfang eines refaktorisierungsversuch 
    //-- wenn ich am Ende noch Zeit hab geht's hier weiter
    //-- Ich muss noch irgendwie den Typ in den der inhalt des textfeldes
    //-- gecasted werden soll mit uebergeben. per <> oder so
    //-- https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
    private <T> void addListener(TextField textfield, String setterName) {
    
            textfield.focusedProperty().addListener((observableBoolean, oldValue, newValue) -> {
            if (!newValue){ // newValue=0 means no focus -> if no longer focused
                try {
                    String s = textfield.textProperty().getValue();
                    int w = Integer.parseInt(s);
                    
                    //-- per reflection den setter aufrufen um den wert aus
                    //-- textfeld in das model zu schreiben.                    
                    model.getClass().getMethod(setterName).invoke(w);
                    
                } catch (IllegalArgumentException ex) {
                    showInputAlert("Input is wrong.");
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(BuilderGenController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(BuilderGenController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(BuilderGenController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(BuilderGenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    
    }
}
