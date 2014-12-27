package src;


/**
 * This class constructs a Instrument of type MultiStringInstrument and passes pressed
 * keys to be processed
 * 
 * @author (Jesse Nelson) 
 * @version (September 11, 2013 : Windows 7(x64) Java 1.7)
 */
public class Musician {
   public static void main(String[] args) {
    
       Instrument instr = new MultiStringInstrument();
       while (true) {
            // Check if the user has typed a key; if so, process it.   
            if (StdDraw.hasNextKeyTyped()) {
                char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                try {
                    instr.pluck(key);
                }   catch(IllegalArgumentException e) {
                        System.out.println("You have entered an invalid key: " + key);
                    }
            }
            instr.play();
            instr.tic();
        }
    }
}
