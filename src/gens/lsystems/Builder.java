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

import java.util.HashMap;

/**
 *
 * @author holger
 */
public class Builder {
    
    private String axiom;
    private HashMap<String, String> rules = new HashMap<>();
    
    public Builder (String axiom) {
        this.axiom = axiom;
    }
    
    public void addRule(String search, String replace) {
        this.rules.put(search, replace);     
    }

    public String generateLsystem(int depth) {
        String startLs = this.axiom;
        String endLs = "";
        for (int i=0; i<depth; i++){
            endLs = this.processRules(startLs);
            startLs = endLs;
        }
        return endLs;
    }

    private String processRules(String input) {
        String output = "";
        for (int i=0; i < input.length(); i++) {
            String key = String.valueOf(input.charAt(i));
            if (this.rules.containsKey(key)){
                output = output.concat(this.rules.get(key));
            }
            else {
                output = output.concat(key);            
            }
        }
        return output;
    }

}