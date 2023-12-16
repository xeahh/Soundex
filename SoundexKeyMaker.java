
public class SoundexKeyMaker
{
    /**
     * Takes a String and returns a soundex key based on the string
     * @param name, the name that will be made into a soundex key
     * @return result, the key produced from the string provided
     */
    public static String keyMaker(String name){
       StringBuilder result=new StringBuilder();
       /*1. Entire name is translated into a series of digit character*/
       for (int i = 0; i<name.length();i++) {
          int digit = letterCheck(name.charAt(i));
          result.append(digit);
       }
       /*2. Remove or skip double digits*/
       StringBuilder result2 = new StringBuilder();
       for (int i = 0; i<result.length()-1;i++) {
           if (result.charAt(i) != result.charAt(i+1)) {
              result2.append(result.charAt(i));
           }
       }
       result2.append(result.charAt(result.length()-1));
       /*3. Replace the first digit with the first letter of the name*/
       result2.setCharAt(0, Character.toLowerCase(name.charAt(0)));
       /*4. Remove all 7s*/
      result = new StringBuilder(result2.toString().replace("7", ""));
       
       /*5. Shorten or lengthen the keys to 4 in length*/
      while (result.length()<4) {
         result.append('0');
      }
      while (result.length()>4) {
         result.deleteCharAt(result.length()-1);
      }
       
       return result.toString();
    }
    /**
     * Takes a character and returns the number to change it to soundex
     * @param character, character to be translated
     * @return number the character matchs to in soundex translation
     */
    public static int letterCheck(char character){
       /*return the int matching the char in accordance with soundex translation*/
      switch (Character.toLowerCase(character)) {
         case 'b':
         case 'p':
         case 'f': 
         case 'v':
            return 1;
         case 'c': 
         case 's':
         case 'k':
         case 'g':
         case 'j':
         case 'q':
         case 'x':
         case 'z':
            return 2;
         case 'd':
         case 't':
            return 3;
         case 'l':
            return 4;
         case 'm':
         case 'n':
            return 5;
         case 'r':
            return 6;
         default:
            return 7;
      }
    }
}