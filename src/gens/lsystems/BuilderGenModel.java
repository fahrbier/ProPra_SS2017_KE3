/*
 * The MIT License
 *
 * Copyright 2017 holger.
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

import gens.lsystems.circletest.*;
import general.GenModel;
import general.Turtle;
import gens.lsystems.Builder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author holger
 */
public class BuilderGenModel extends GenModel {

    private int width;
    private int height;
    
    private double deltaAngle = 60;
    private double deltaStroke = 10;
    
    private Alphabet alphabet = new Alphabet("F+-");
    private String axiom = "F";
    
    private Rule rule1 = new Rule(alphabet);   
    private Rule rule2 = new Rule(alphabet);
    private Rule rule3 = new Rule(alphabet);
    
    private int iterations = 4;
    
    private GraphicsContext gc;


    BuilderGenModel() {
        this.width = 600;
        this.height = 400;       
    }
    
    @Override
    public String getGenName() {
        return "L - System";
    }

    @Override
    public void generate() {     

        setGenState("Creating new canvas...");
        canvas = new Canvas(width, height);

        setGenState("Filling image background...");
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

        setGenState("Drawing red circle...");
        double diameter = Math.min(width, height);
        
        gc.setStroke(Color.RED);

        //-- create a turle to run and paint the canvas        
        Turtle t = new Turtle(canvas,(width)/2.,(height)/2.,0);
        t.setPresetDeltaAngle(this.deltaAngle);
        t.setPresetDeltaSteps(this.deltaStroke);
      
        //-- create a builder to create the string that sends the turtle on its way
        Builder bob = new Builder(this.axiom);
        bob.addRule(rule1);
        bob.addRule(rule2);
        bob.addRule(rule3);
        
        //-- run, my friend, run!
        t.processRules(bob.generateLsystem(this.iterations));
       
    }
    
  

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
        this.height = h;
    }
    
    public void setDeltaAngle(double deltaAngle) {
        this.deltaAngle = deltaAngle;
    }

    public void setDeltaStroke(double deltaStroke) {
        this.deltaStroke = deltaStroke;
    }

    public double getDeltaAngle() {
        return this.deltaAngle;
    }

    public double getDeltaStroke() {
        return this.deltaStroke;
    }  
    
    public Alphabet getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Alphabet alphabet) {
        this.alphabet = alphabet;
    }    
    
    public String getAxiom() {
        return this.axiom;
    }
    
    public void setAxiom(String s) {
        this.axiom = s;
    }

    public Rule getRule1() {
        return this.rule1;
    }

    public void setRule1(Rule r) {
        this.rule1 = r;
    }    
       
    public Rule getRule2() {
        return this.rule2;
    }
    
    public void setRule2(Rule r) {
        this.rule2 = r;
    }     

    public Rule getRule3() {
        return this.rule3;
    }
    
    public void setRule3(Rule r) {
        this.rule3 = r;
    }     

    public void setIterations(int i) {
        this.iterations = i;
    }    
    
    public int getIterations() {
        return this.iterations;
    }
    
}
