import java.io.*;  
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Reads 151,671 surnames in the U.S. from the Census 2000.
 * Then asks the user to enter a surname, 
 * which then computes the soundex (key), 
 * and shows the neighboring soundex entries
 */
public class SoundexKey
{
    /**
     * 
     */
    public static void main(String [] args) throws Exception
    {
        Scanner key = new Scanner(System.in);
        Scanner sc = new Scanner(new File("surnames.csv"));  
        sc.useDelimiter("\n");
        
        ArrayList<String> surnamesList = new ArrayList<String>();
        ArrayList<String> surnamesKeys = new ArrayList<String>();
        
        //Create soundex keys for each surname
        while (sc.hasNext()) {
            String next = sc.next();
            surnamesList.add(next);
            surnamesKeys.add(SoundexKeyMaker.keyMaker(next));
        }
        
         // Prompt the user to enter a surname
        System.out.print("Enter a surname: ");
        String userSurname = key.nextLine();

        // Compute the Soundex key for the entered surname
        String userSoundexKey = SoundexKeyMaker.keyMaker(userSurname);
        
        // Display neighboring surnames / soundex entries
        System.out.println("\nNeighboring Surnames:");

        ArrayList<String> neighboringNames = getNeighboringNames(surnamesList, surnamesKeys, 
                        userSoundexKey);
        //Display the names
        for (String name : neighboringNames) {
            System.out.println(name);
        }
    }
    
    private static ArrayList<String> getNeighboringNames(ArrayList<String> names, 
                            ArrayList<String> soundexKeys, String userSoundex) {
        ArrayList<String> neighboringNames = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            String currentSoundex = soundexKeys.get(i);

            // Check if the Soundex keys are within range
            if (isWithinRange(userSoundex, currentSoundex)) {
                neighboringNames.add(names.get(i));
            }
        }
        return neighboringNames;
    }
    
    private static boolean isWithinRange(String user, String current) {
        if (user.compareTo(current)==0) {
            return true;
        } else {
            return false;
            }
    }
}