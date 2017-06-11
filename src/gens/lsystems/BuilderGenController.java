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
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Christoph Baumhardt
 */
public class BuilderGenController extends GenController {
    
    @FXML private TextField width;
    @FXML private TextField height;
    
    @FXML private TextField alphabet; 
    @FXML private TextField axiom;  
    
    @FXML private TextField rule1;
    @FXML private TextField rule2;
    @FXML private TextField rule3;
    
    @FXML private TextField angle;    
    @FXML private TextField stroke;
    
    @FXML private TextField iterations;
    
    @FXML private ComboBox lsPresets;
    
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
        width.textProperty().setValue(String.valueOf(model.getWidth()));
        height.textProperty().setValue(String.valueOf(model.getHeight()));
        
        alphabet.textProperty().setValue(model.getAlphabet().toString());
        axiom.textProperty().setValue(model.getAxiom());
        
        angle.textProperty().setValue(String.valueOf(model.getDeltaAngle()));
        stroke.textProperty().setValue(String.valueOf(model.getDeltaStroke()));
        
        iterations.textProperty().setValue(String.valueOf(model.getIterations()));


        
        this.addListener(width, "setWidth", "integer", "Width is wrong.");
        this.addListener(height, "setHeight", "integer", "Height is wrong.");

        this.addListener(axiom, "setAxiom", "string", "Axiom is wrong.");
        
        this.addListener(angle, "setDeltaAngle", "double", "The angle requires an integer value between 1 and 360");
        this.addListener(stroke, "setDeltaStroke", "double", "Stroke length was wrong");

        this.addListener(iterations, "setIterations", "integer", "Amount of iterations is wrong.");
        
        Rule presetRule = new Rule(model.getAlphabet());
        try {
            presetRule.add("F", "F+F--F+F");
        } catch (RuleException ex) {
            Logger.getLogger(BuilderGenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        model.setRule1(presetRule);
        
        rule1.focusedProperty().addListener((observableBoolean, oldValue, newValue) -> {
            if (!newValue){ // newValue=0 means no focus -> if no longer focused
                try {
                    
                    String s = rule1.textProperty().getValue();
                    String[] ruleParts = s.split("=");
                    Rule newRule = new Rule(model.getAlphabet());
                    
                    newRule.add(ruleParts[0], ruleParts[1]);
                    model.setRule1(newRule);
                    
                } catch (RuleException ex) {
                    rule1.textProperty().setValue(String.valueOf(model.getRule1().toString()));
                    showInputAlert("Rule 1 is not correct.");
                }
            }
        });

        rule2.focusedProperty().addListener((observableBoolean, oldValue, newValue) -> {
            if (!newValue){ // newValue=0 means no focus -> if no longer focused
                try {
                    
                    String s = rule2.textProperty().getValue();
                    String[] ruleParts = s.split("=");
                    Rule newRule = new Rule(model.getAlphabet());
                    
                    newRule.add(ruleParts[0], ruleParts[1]);
                    model.setRule2(newRule);
                    
                } catch (RuleException ex) {
                    rule1.textProperty().setValue(String.valueOf(model.getRule2().toString()));
                    showInputAlert("Rule 2 is not correct.");
                }
            }
        });
        
        rule3.focusedProperty().addListener((observableBoolean, oldValue, newValue) -> {
            if (!newValue){ // newValue=0 means no focus -> if no longer focused
                try {
                    
                    String s = rule3.textProperty().getValue();
                    String[] ruleParts = s.split("=");
                    Rule newRule = new Rule(model.getAlphabet());
                    
                    newRule.add(ruleParts[0], ruleParts[1]);
                    model.setRule2(newRule);
                    
                } catch (RuleException ex) {
                    rule1.textProperty().setValue(String.valueOf(model.getRule3().toString()));
                    showInputAlert("Rule 3 is not correct.");
                }
            }
        });        
        
        
        alphabet.focusedProperty().addListener((observableBoolean, oldValue, newValue) -> {
            if (!newValue){ // newValue=0 means no focus -> if no longer focused
                try {
                    
                    String s = alphabet.textProperty().getValue();
                    Alphabet a = new Alphabet(s);
                    model.setAlphabet(a);
            
                } catch (IllegalArgumentException ex) {
                    // display last valid value for width from model
                    alphabet.textProperty().setValue(model.getAlphabet().toString());
                    showInputAlert("Illegal Alphabet");
                }
            }
        });


        
          
        

        lsPresets.valueProperty().addListener(
            new ChangeListener<String>() {
                @Override 
                public void changed(ObservableValue ov, String oldValue, String newValue) {
                    System.out.println(ov);
                    
                    switch (newValue) {
                        case "Koch Snowflake":
                            width.textProperty().setValue("800");
                            height.textProperty().setValue("600");
                            alphabet.textProperty().setValue("F+-");
                            angle.textProperty().setValue("60");
                            stroke.textProperty().setValue("5");
                            iterations.textProperty().setValue("5");
                            axiom.textProperty().setValue("F--F--F");
                            rule1.textProperty().setValue("F=F+F--F+F");
                            
                            
                            break;
                    }
                    
                }    
            }
        );

    }

    
    //-- TODO: das ding hier ist der Anfang eines refaktorisierungsversuch 
    //-- wenn ich am Ende noch Zeit hab geht's hier weiter
    //-- Ich muss noch irgendwie den Typ in den der inhalt des textfeldes
    //-- gecasted werden soll mit uebergeben. per <> oder so
    //-- https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
    private void addListener(TextField textfield, String setterName, String typeFirstAndOnlyParameter, String errorMessage) {
            
           
            textfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableBoolean, Boolean oldValue, Boolean newValue) {
                    if (!newValue){ // newValue=0 means no focus -> if no longer focused
                        try {
                            String s = textfield.textProperty().getValue();
                            switch (typeFirstAndOnlyParameter) {
                                case "integer":
                                    model.getClass().getMethod(setterName, Integer.TYPE).invoke(model, Integer.parseInt(s));
                                    break;
                                case "double":
                                    model.getClass().getMethod(setterName, Double.TYPE).invoke(model, Double.parseDouble(s));
                                    break;
                                case "string":
                                    model.getClass().getMethod(setterName, String.class).invoke(model, s);
                                    break;
                            }
                            
                        } catch (IllegalArgumentException ex) {
                            showInputAlert(errorMessage);
                        } catch (NoSuchMethodException ex) {
                            Logger.getLogger(BuilderGenController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SecurityException ex) {
                            Logger.getLogger(BuilderGenController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(BuilderGenController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(BuilderGenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }   }
            });
    
    }
}
