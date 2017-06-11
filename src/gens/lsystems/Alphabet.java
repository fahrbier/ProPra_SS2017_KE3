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
    
    /**
     * Construct a new Alphabet from a String. This whole class here
     * seems to be a bit waste of code - a String goes in, and a String
     * comes out and String itself has a contains() method as well.
     * So, no win at this point - anyhow - I wanted to have a Type 
     * for the Alphabet.
     * 
     * Maybe better to extend String here...? Schau ich spaeter nochma rein.
     * 
     * @param alphabet 
     */
    public Alphabet (String alphabet) {
        for (int i =0; i < alphabet.length(); i ++) {
            characters.add(String.valueOf(alphabet.charAt(i)));
        }
        String k = new String();
        
    }
    
    /**
     * to check if a particulare character is allowed to use
     * @param c a character
     * @return Boolean
     */
    public Boolean contains(String c) {
        return this.characters.contains(c);        
    }
    
    
    /**
     * ArrayList.toString returns a comma separated and in
     * square brackets enclosed list of the characters like
     * [A,+,-] but I want just a A+- hence I'll override it
     * 
     * @return String all the characters of the Alphabet as one word
     */
    @Override
    public String toString() {   

        StringBuilder ret = new StringBuilder();
        characters.forEach(k -> ret.append(k));       
        return ret.toString();       
    }
}
