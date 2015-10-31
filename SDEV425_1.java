/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Pattern;

/**
 *
 * @author jim
 */
public class SDEV425_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Read the filename from the command line argument          
        File filename = new File(args[0]);
        
        BufferedReader inputStream = null;
        String fileLine;
        
        try {
            
            String canonicalPath = filename.getCanonicalPath();
            
            inputStream = new BufferedReader(new FileReader(canonicalPath));

            System.out.println("Email Addresses:");
            // Read one Line using BufferedReader
            while ((fileLine = inputStream.readLine()) != null) {
                
              //Normalization
              String normFileLine = Normalizer.normalize(fileLine, Form.NFKC);
              
              //Sanitization                
              if (!Pattern.matches("[0-9A-Za-z@.]+", normFileLine)) {
                    System.out.println("Invalid email address.");
              } else {
                  System.out.println(normFileLine);
              }  
              
            } 
        } catch (IOException io) {
            System.out.println("File IO exception" + io.getMessage());
        } finally {
            // Need another catch for closing 
            // the streams          
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException io) {
                System.out.println("Issue closing the Files" + io.getMessage());
            } 
        Runtime.getRuntime().exit(1);
        }
        
    }

}
