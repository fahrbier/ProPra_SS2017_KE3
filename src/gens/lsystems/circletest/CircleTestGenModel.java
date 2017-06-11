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
package gens.lsystems.circletest;

import general.GenModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author holger
 */
public class CircleTestGenModel extends GenModel {

    private final int width = 600;
    private final int height = 400;
    private int minDiameter = 10;
    private GraphicsContext gc;


    CircleTestGenModel() {}
    
    @Override
    public String getGenName() {
        return "Circle Test";
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
        this.strokeCircles((width)/2.,(height)/2., diameter);
          
    }
    
    private void strokeCircles(double x, double y, double d) {
        //-- the most important thing, the "break" criteria for the
        //-- recursion. When it hits this, it folds together and starts the
        //-- actual drawing from the smalles cirle to the biggest
        if (d < this.minDiameter) {
            return;
        }

        //- set another circle half the size of the current one onto the right edge
        this.strokeCircles(x + d/4, y, d/2);
        //- set another circle half the size of the current one onto the left edge       
        this.strokeCircles(x - d/4, y, d/2);

         //-- bring it to the canvas
        gc.strokeOval(x-d/4, y-d/4, d/2, d/2);       
    }

    public int getMinDiameter() {
        return this.minDiameter;
    }    
    
    public void setMinDiameter(int md) {
        if (md < 1 || md > 100) {
            throw new IllegalArgumentException();
        }
        this.minDiameter = md;
    } 
    

}
