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

import java.util.ArrayList;

/**
 *
 * @author holger
 */
public class Alphabet {
    private final ArrayList<String> characters = new ArrayList<>();
    
    public Alphabet (String alphabet) {
        for (int i =0; i < alphabet.length(); i ++) {
            System.out.println(String.valueOf(alphabet.charAt(i)));
            characters.add(String.valueOf(alphabet.charAt(i)));
        }
    }
    
    public Boolean contains(String c) {
        return this.characters.contains(c);        
    }
    
    public String toString() {        
        return characters.toString();       
    }
}
