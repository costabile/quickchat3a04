/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


/**
 *
 * @author Jason
 */
public class localFilter {

    private static final int DEFAULT_FILTER_LIST_LENGTH = 7;

    //make private?
    public clientSettings settings = new clientSettings();

    public localFilter() {

    }

    /*public static void main(String args[]) {
        new localFilter();
    }*/

    public String filterMsg(String msg) {
        //TODO: deactivate if user has turned filter off?
        String filteredMsg = msg;
        if (settings.isSwearFilterOn()) {       //filter message if swear filter is on
            String lowCaseMsg = msg.toLowerCase();
            int i,j;
            for (i=0; i<getDefaultFilterListLength(); i++) {
                if (lowCaseMsg.contains(getDefaultFilterWord(i))) {
                    String replacementStr = "";
                    for (j=0; j<getDefaultFilterWord(i).length(); j++) {
                        replacementStr = replacementStr + "*";
                    }
                    filteredMsg = filteredMsg.replaceAll("(?i)" + getDefaultFilterWord(i), replacementStr);     //(?i) flag causes case insensitivity
                }
            }
        }                                       //else just return the unfiltered message
        return filteredMsg;
    }

    private int getDefaultFilterListLength() {
        return DEFAULT_FILTER_LIST_LENGTH;  //TODO: determine actual length
    }

    private String getDefaultFilterWord(int i) {
        switch(i) {
            case 0:
                return "ass";
            case 1:
                return "fuck";
            case 2:
                return "shit";
            case 3:
                return "bitch";
            case 4:
                return "nigger";
            case 5:
                return "cunt";
            case 6:
                return "faggot";
            default:
                break;
        }
        return "";
    }

}
