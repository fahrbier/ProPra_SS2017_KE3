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
    private GraphicsContext gc;


    BuilderGenModel() {
        this.width = 600;
        this.height = 400;
    }
    
    @Override
    public String getGenName() {
        return "Circle Test";
    }

    @Override
    public void generate() {     
        System.out.println("Hallo");
        setGenState("Creating new canvas...");
        canvas = new Canvas(width, height);

        setGenState("Filling image background...");
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

        setGenState("Drawing red circle...");
        double diameter = Math.min(width, height);
        
        gc.setStroke(Color.RED);
        //this.strokeCircles((width)/2.,(height)/2., diameter);
        
        Turtle t = new Turtle(canvas,(width)/2.,(height)/2.,0);
        t.setPresetDeltaAngle(60);
        t.setPresetDeltaSteps(5);
        //t.processRules("F++F++F");
        
        //-- the axiom "F++F++F" only 
        /*
        t.moveForward(20);
        t.turnLeft(60);
        t.moveForward(20);
        t.turnRight(60);
        t.turnRight(60);
        t.moveForward(20);
        t.turnLeft(60);
        t.moveForward(20);
        
        t.turnLeft(60);
        t.turnLeft(60);
        t.moveForward(20);
        t.turnLeft(60);
        t.moveForward(20);
        t.turnRight(60);
        t.turnRight(60);
        t.moveForward(20);
        t.turnLeft(60);
        t.moveForward(20);   
        t.turnLeft(60);
        t.turnLeft(60);
        t.moveForward(20);
        t.turnLeft(60);
        t.moveForward(20);
        t.turnRight(60);
        t.turnRight(60);
        t.moveForward(20);
        t.turnLeft(60);
        t.moveForward(20);   
        */
        // use production rule F → F+F--F+F
        
        /*
        t.moveForward(20);
        t.turnLeft(60);
        t.moveForward(20);
        t.turnRight(60);
        t.turnRight(60);
        t.moveForward(20);
        t.turnLeft(60);
        t.moveForward(20);        
        */
        
        
        //t.processRules("F-F++F-F--F-F++F-F--F-F++F-F");
        // axiom F++F++F
        
        Builder bob = new Builder("F");
        bob.addRule("F", "F+F--F+F");
        
        System.out.println(bob.generateLsystem(4));
        
        t.processRules(bob.generateLsystem(4));
        
        // replace F with F+F--F+F
/*
        t.moveForward(20);
        t.turnLeft(60);        
        t.moveForward(20);
        t.turnRight(60);        
        t.turnRight(60);
        t.moveForward(20);
        t.turnLeft(60);        
        t.moveForward(20);
*/        
    }
    
    private void strokeCircles(double x, double y, double d) {
        //-- the most important thing, the "break" criteria for the
        //-- recursion. When it hits this, it folds together and starts the
        //-- actual drawing from the smalles cirle to the biggest
        if (d < 1) {
            return;
        }

        //- set another circle half the size of the current one onto the right edge
        this.strokeCircles(x + d/4, y, d/2);
        //- set another circle half the size of the current one onto the left edge       
        this.strokeCircles(x - d/4, y, d/2);

         //-- bring it to the canvas
        gc.strokeOval(x-d/4, y-d/4, d/2, d/2);       
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
    
}
