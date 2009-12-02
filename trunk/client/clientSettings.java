package client;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Jason
 */
public class clientSettings {

    private static final String SETTINGS_FILENAME = "settings.qc";
    private static final int SWEAR_FILTER_INDEX = 0;

    private File settingsFile = new File(SETTINGS_FILENAME);

    //setting states
    private boolean swearFilterState = false;

    public clientSettings() {
        updateSettings();   //check if we need to make a new settings file and initialize variables to saved states
    }

    /*public static void main(String args[]) {
        System.out.println("test: cons");
        new clientSettings();
    }*/

    //settings selectors***************
    public boolean isSwearFilterOn() {
        updateSettings();   //make sure variables are current
        return swearFilterState;
    }

    //settings mutators****************
    public void setSwearFilterOn(boolean on) {    //setting #0
        if (on == true) {
            changeSetting(SWEAR_FILTER_INDEX, "1");      //turn on swear filter
        } else {
            changeSetting(SWEAR_FILTER_INDEX, "0");     //turn off
        }
    }
    //

    private void changeSetting(int settingIndex, String value) {
        updateSettings();   //checks if we need to make a new settings file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(settingsFile));

            String line = null;
            //String[] settingMem = new String[10];
            ArrayList<String> settingMem = new ArrayList<String>();
            //int length = 0;
            while ((line = reader.readLine()) != null) {    //read current settings into memory for editing
                settingMem.add(line);
                //length++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(settingsFile));
            int i;
            for (i=0; i < settingIndex; i++) {  //restore current settings until we hit the changed setting
                writer.write(settingMem.get(i));
                writer.newLine();
            }
            writer.write(value);    //write the changed setting
            writer.newLine();
            for (i=settingIndex+1; i < settingMem.size(); i++) {     //restore the settings after the changed setting
                writer.write(settingMem.get(i));
                writer.newLine();
            }
            writer.close();
            updateSettings();   //update variables
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void newSettingsFile() {
        try {
            System.out.println("Creating new settings file.\n");
            BufferedWriter writer = new BufferedWriter(new FileWriter(settingsFile));

            //write swear filter on/off option
            writer.write("0");      //default off
            writer.newLine();
            //swearFilterState = false;

            writer.close();

            updateSettings();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void updateSettings() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(settingsFile));
            String line = null;

            //read SWEAR FILTER ON/OFF
            line = reader.readLine();
            if (line.compareTo("1") == 0) swearFilterState = true;
            else if (line.compareTo("0") == 0) swearFilterState = false;
            else throw new Exception("Invalid settings file");

            reader.close();
        } catch (Exception e) {
            System.err.println("No valid saved settings.\n");
            newSettingsFile();
        }
    }
}
