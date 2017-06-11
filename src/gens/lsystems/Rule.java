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

/**
 *
 * @author holger
 */
public class Rule {
    
    private String from;
    private String to;
    private final Alphabet alphabet;
    
    /**
     * create new Rule and take in the alphabet to only allow rules
     * with the characters from it.
     * @param alphabet 
     */
    public Rule(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public void add(String from, String to) throws RuleException {
        
        if (!validWord(from) || !validWord(to)) { 
            throw new RuleException();
        }
           
        this.from = from;
        this.to = to;
            
    }
    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) throws RuleException {

        if (!validWord(from) ) { 
            throw new RuleException();
        }
        
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) throws RuleException {
        
        if (!validWord(to)) { 
            throw new RuleException();
        }
        
        this.to = to;
    }
    
    @Override
    public String toString() {
        if (this.from.isEmpty() && this.to.isEmpty()) {
            return "";
        }    
        return this.from + "=" + this.to;            
    }
    
    private Boolean validWord(String word) {
        //-- toDo: check the word (either from or to) if it's a valid rule
        return true;
    }
    
}
