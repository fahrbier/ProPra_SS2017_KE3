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


/* ******************************************************************
   inspired by introcs.cs.princeton.edu/java/32class/Turtle.java.html
   ****************************************************************** */
package general;

import java.util.Stack;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author holger
 * 
 * A very simple class to do turtle graphics on given canvas
 * 
 */
public class Turtle {
    private Canvas canvas;
    
    private double x,y; // Position of the Turtle
    private double angle; // Degree facing to from the x-axis
    
    private double presetDeltaAngle = 0;
    private double presetDeltaSteps = 0;
    
    private Stack<TurtleGuts> nest = new Stack<>();

    
    
    public Turtle(Canvas canvas, double x, double y, double a) {
        this.canvas = canvas;
        
        this.x = x;
        this.y = y;
        this.angle = a;
        
    }

    public void setPresetDeltaAngle(double presetDeltaAngle) {
        this.presetDeltaAngle = presetDeltaAngle;
    }

    public void setPresetDeltaSteps(double presetDeltaSteps) {
        this.presetDeltaSteps = presetDeltaSteps;
    }
    
    public void moveForward() {
        this.moveForward(this.presetDeltaSteps);
    }
    
    public void moveForward(double steps) {
    
        double xFrom = x;
        double yFrom = y;
   
        x += steps * Math.cos(Math.toRadians(this.angle));
        y += steps * Math.sin(Math.toRadians(this.angle));
        
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.strokeLine(xFrom, yFrom, x, y);
        
    }
    
    
    public void turnLeft() {
        this.turnLeft(this.presetDeltaAngle);
    }
    
    public void turnLeft(double delta) {
        this.angle -= delta;
    }
    
    public void turnRight() {
        this.turnRight(this.presetDeltaAngle);
    }
    
    public void turnRight(double delta) {
        this.angle += delta;
    }    
    
    public void push() {
        TurtleGuts t = new TurtleGuts(this.x, this.y, this.angle);
        this.nest.push(t);
    }
    
    public void pop() {
        TurtleGuts t = this.nest.pop();
        this.x = t.getX();
        this.y = t.getY();
        this.angle = t.getAngle();
    }
    
    public void processRules(String rules) {
    
        for (int i=0; i < rules.length(); i++) {
            switch (rules.charAt(i)) {
               
                case 'F': 
                    this.moveForward();
                    break;
                case '-':
                    this.turnRight();
                    break;
                case '+':
                    this.turnLeft();
                    break;
                case '[':
                    this.push();
                    break;
                case ']':
                    this.pop();
                    break;                    
                         
            }
        }
        
    }
}
