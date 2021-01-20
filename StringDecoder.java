/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication;

import com.sun.xml.internal.ws.util.StringUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author macbookpro
 */
class StringDecoder {
 

    public static void main(String[] args) {
        String item  = "3[a2[c]"; //"3[a]2[bc]xyz" //"abc3[a]2[bc]xyz"
        String value  = "";
        String decodedString = processDecoding(item);
        System.out.println("final value: "+decodedString);
}
       
    
    
   static  String filterPreValues(String str){        
        String part = getSubstringUntilFirstNumber(str);           
        return part;
    }
    static String filterPostValues(String str){        
         String[] parts = str.split("\\]");
        String part = parts[parts.length-1];        
        return (part.contains("[")||part.contains("]"))?"":part;
    }
    
    static String getSubstringUntilFirstNumber(String source) {
    return source.split("[0-9]")[0];
}
    static String processDecoding(String input){
        String value = "";
        String item = input.replaceFirst(filterPreValues(input), "");        
        Pattern p = Pattern.compile(".*?(\\[)(.*?)(\\])");
        Matcher m = p.matcher(item);
            
        while(m.find())
        {                       
               if (m.group(2) != null) {

            String s  = m.group(0);
             String countString=Character.toString(s.charAt(0));
            int count=Integer.parseInt(countString);
            String _s = s.substring(1);            
           String innerValue = _s.replaceFirst("\\[", "").replaceFirst("\\]", "");                      
            if(innerValue.contains("[")){
                 innerValue =  filterPreValues(innerValue)+processDecoding(innerValue+"]");                 
               }            
            String fullValue  = String.join("", Collections.nCopies(count, innerValue));                
          
           value += fullValue;                      
        } else{}                        
    }
        String result   = filterPreValues(item)+value+filterPostValues(item);
        return result;
    }
    
}