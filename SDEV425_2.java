/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author jim Adopted from Oracle's Login Tutorial Application
 * https://docs.oracle.com/javafx/2/get_started/form.htm
 */
public class SDEV425_2 extends Application {
    int failedAttempts;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SDEV425 Login");
        // Grid Pane divides your window into grids
        GridPane grid = new GridPane();
        // Align to Center
        // Note Position is geometric object for alignment
        grid.setAlignment(Pos.CENTER);
        // Set gap between the components
        // Larger numbers mean bigger spaces
        grid.setHgap(10);
        grid.setVgap(10);

        // Create some text to place in the scene
        Text scenetitle = new Text("Welcome. Log in to continue.");
        // Add text to grid 0,0 span 2 columns, 1 row
        grid.add(scenetitle, 0, 0, 2, 1);

        // Create Label
        Label userName = new Label("User Name:");
        // Add label to grid 0,1
        grid.add(userName, 0, 1);

        // Create Textfield
        TextField userTextField = new TextField();
        // Add textfield to grid 1,1
        grid.add(userTextField, 1, 1);

        // Create Label
        Label pw = new Label("Password:");
        // Add label to grid 0,2
        grid.add(pw, 0, 2);

        // Create Passwordfield
        PasswordField pwBox = new PasswordField();
        // Add Password field to grid 1,2
        grid.add(pwBox, 1, 2);
        
        //Create 2nd Passwordfield and label
        PasswordField pwBox2 = new PasswordField();
        grid.add(pwBox2, 1, 3);
        Label pw2 = new Label("Keyfob code: ");
        grid.add(pw2, 0, 3);

        // Create Login Button
        Button btn = new Button("Login");
        // Add button to grid 1,5
        grid.add(btn, 1, 5);
        
        //Create Guest Login Button
        Button btn2 = new Button("Guest Login");
        grid.add(btn2, 1, 6);
        
         // Create some text to place in the scene
        Text scenetitle2 = new Text("WARNING: This is a secure system. Usage may be monitored," +
                " recorded,\n and subject to audit. Unauthorized use of the information system" +
                " is \nprohibited and subject to criminal and civil penalties. Use of the" +
                " information \nsystem indicates consent to monitoring and recording.");
        grid.add(scenetitle2, 0, 8, 2, 1);
        scenetitle2.setFill(Color.FIREBRICK);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        // Set the Action when button is clicked
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //If no recent login attempts, reset lockout counter
                String username = userTextField.getText();
                boolean recentAttempt = checkLog(username);
                if (recentAttempt == false) {
                    failedAttempts = 0;
                }
                if (failedAttempts < 3) {
                    // Authenticate the user
                    long codeInt = Long.parseLong(pwBox2.getText());
                    boolean isValid = authenticate(username, pwBox.getText(), codeInt);
                    // If valid clear the grid and Welcome the user
                    if (isValid) {
                        grid.setVisible(false);
                        GridPane grid2 = new GridPane();
                        // Align to Center
                        // Note Position is geometric object for alignment
                        grid2.setAlignment(Pos.CENTER);
                        // Set gap between the components
                        // Larger numbers mean bigger spaces
                        grid2.setHgap(10);
                        grid2.setVgap(10);
                        Text scenetitle = new Text("Welcome " + userTextField.getText() + "!");
                        // Add text to grid 0,0 span 2 columns, 1 row
                        grid2.add(scenetitle, 0, 0, 2, 1);
                        Scene scene = new Scene(grid2, 500, 400);
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        // If Invalid Ask user to try again
                    } else {                    
                        final Text actiontarget = new Text();
                        grid.add(actiontarget, 1, 7);
                        actiontarget.setFill(Color.FIREBRICK);
                        actiontarget.setText("Please try again.");
                        logAttempt(username);
                        failedAttempts++;
                    }
                } else {
                    grid.setVisible(false);
                    GridPane grid3 = new GridPane();
                    // Align to Center
                    // Note Position is geometric object for alignment
                    grid3.setAlignment(Pos.CENTER);
                     // Set gap between the components
                    // Larger numbers mean bigger spaces
                    grid3.setHgap(10);
                    grid3.setVgap(10);
                    Text scenetitle = new Text("You have exceeded the number of allowable login attempts.");
                    // Add text to grid 0,0 span 2 columns, 1 row
                    grid3.add(scenetitle, 0, 0, 2, 1);
                    Scene scene = new Scene(grid3, 500, 400);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }

            }
        });
        
        // Set the Action when button 2 is clicked
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                    //Welcome the guest user
                    grid.setVisible(false);
                    GridPane grid2 = new GridPane();
                    // Align to Center
                    // Note Position is geometric object for alignment
                    grid2.setAlignment(Pos.CENTER);
                     // Set gap between the components
                    // Larger numbers mean bigger spaces
                    grid2.setHgap(10);
                    grid2.setVgap(10);
                    Text scenetitle = new Text("Welcome Guest! Limited access granted.");
                    // Add text to grid 0,0 span 2 columns, 1 row
                    grid2.add(scenetitle, 0, 0, 2, 1);
                    Scene scene = new Scene(grid2, 500, 400);
                    primaryStage.setScene(scene);
                    primaryStage.show();
            }
        });
        
        // Set the size of Scene
        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param user the username entered
     * @param pword the password entered
     * @param usercode the keyfob code entered
     * @return isValid true for authenticated
     */
    public boolean authenticate(String user, String pword, long usercode) {
        boolean isValid = false;
        String code = "";
        
        //Turn the password into a code used to check validity
        for (int i = 0; i < pword.length(); ++i) {
            char ch = pword.charAt(i);
            int n = (int)ch - (int)'a' + 1;
            code += String.valueOf(n);
        }
        //Remove spaces and negative signs
        code = code.replaceAll("\\s+","");
        code = code.replaceAll("-","");
        //Change the string to a long integer
        long codeInt = Long.parseLong(code);
        
        //The algorithm that creates the code
        //Divides it down to a manageable size, and incorporates the date
        codeInt = codeInt / 5000000;
        String day = new SimpleDateFormat("dd").format(new Date());
        int dayInt = Integer.parseInt(day);
        codeInt += dayInt;
        //Correct code is 88928726 + the current day of the month
        //On the 1st is is 88928727, the 12th is 88928738, etc.
        
        if (user.equalsIgnoreCase("sdevadmin")
                && pword.equals("425!pass") 
                && codeInt == usercode){
            isValid = true;
        }
        return isValid;
    }
    
    public static void logAttempt(String username) {
        
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        int currentMins = timestampMinutes();
        
        // declaring variables of log and initializing the buffered writer
        List<String> log = new ArrayList<String>();
        String logItem = "Username: " + username + ", Failed login time: " + timestamp + " -" +
                        currentMins + "\n";
        log.add(logItem);
        BufferedWriter writer = null;
        
        //write the log variable using the bufferedWriter to log.txt
        try {
            writer = new BufferedWriter(new FileWriter("log.txt"));
            writer.write(log);
        }
        //print error message if there is one
        catch (IOException io) {
            System.out.println("File IO Exception" + io.getMessage());
        }
        //close the file
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }
            //print error message if there is one
            catch (IOException io) {
                System.out.println("Issue closing the file." + io.getMessage());
            }
        }
        
    }
     
    public static boolean checkLog(String username) {
        
        boolean recentAttempt = true;
        int attempts = 0;
        int currentMins = timestampMinutes();
        System.out.println(currentMins) + "\n";
        
        BufferedReader inputStream = null;
        String fileLine;
        File filename = new File("log.txt");
        
        try {
            inputStream = new BufferedReader(new FileReader(filename));

            // Read one Line using BufferedReader
            while ((fileLine = inputStream.readLine()) != null) {
              
                //Check for recent login attempts by the same user
                if (fileLine.contains(username)) {
                    System.out.println("Previous attempt found\n");
                    String attemptMins = fileLine.substring(fileLine.lastIndexOf('-') + 1);
                    System.out.println(attemptMins) + "\n";
                    int attemptMinsInt = Integer.parseInt(attemptMins);
                    if ((currentMins - attemptMinsInt) < 30) {
                        attempts++;
                    }
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
        if (attempts == 0) {
            recentAttempt = false;
        } 
        return recentAttempt;
    } 
    
    public static int timestampMinutes() {
        //Express the current time in minutes
        //String year = new SimpleDateFormat("yyyy").format(new Date());
        String year = new SimpleDateFormat("yy").format(new Date());
        String month = new SimpleDateFormat("MM").format(new Date());
        String day = new SimpleDateFormat("dd").format(new Date());
        String min = new SimpleDateFormat("mm").format.(new Date());
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);
        int dayInt = Integer.parseInt(day);
        int minInt = Integer.parseInt(min);
        int totalMin = (currentYearInt * 525600) + (currentMonthInt * 43800) +
                        (currentDayInt * 1440) + currentMinInt;
        return totalMin;    
    }
}
