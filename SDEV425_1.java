/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdev425;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        String filename = args[0];
        BufferedReader inputStream = null;

        String fileLine;
        try {
            inputStream = new BufferedReader(new FileReader(filename));
            //inputStream = new BufferedReader(new FileReader("EmailAddresses.txt"));
            
    /*        if (!filename.contains("[A-Za-z0-9_]+" || ".txt")) {
            // if (!Pattern.matches("[A-Za-z0-9_]+", filename)) {
                inputStream.close();
            }
    */
            System.out.println("Email Addresses:");
            // Read one Line using BufferedReader
            while ((fileLine = inputStream.readLine()) != null) {
                
                //normalization
                //fileLine = Normalizer.normalize(fileLine, Form.NFKC);
                
    //          if (!fileLine.contains("[A-Za-z0-9_]+")) {
                    System.out.println(fileLine);
    //          } else {
    //              System.out.println("Invalid address.")
    //          }    
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
            } finally {
                Runtime.getRuntime().exit(1);
            }

        }
        
    }

}
