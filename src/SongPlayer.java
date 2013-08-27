package src;

/*************************************************************************
 *  This is a data-driven program that plays InstrumentString tones from
 *  the notes on the chromatic scale, specified on by their distance from
 *  concert A.
 *  
 *  Thanks to Stuart Reges for addition of a JFileChooser, a Scanner for
 *  input and to play the tones ignoring notes that it cannot play.
 *  This version brings up a file chooser that defaults to the current
 *  directory.
 *
 *  Data files
 *  ----------
 *  http://www.cs.princeton.edu/introcs/21function/elise.txt
 *  http://www.cs.princeton.edu/introcs/21function/99luftballons.txt
 *  http://www.cs.princeton.edu/introcs/21function/freebird.txt
 *  http://www.cs.princeton.edu/introcs/21function/Ascale.txt
 *  http://www.cs.princeton.edu/introcs/21function/National_Anthem.txt
 *  http://www.cs.princeton.edu/introcs/21function/looney.txt
 *  http://www.cs.princeton.edu/introcs/21function/StairwayToHeaven.txt
 *  http://www.cs.princeton.edu/introcs/21function/entertainer.txt
 *  http://www.cs.princeton.edu/introcs/21function/old-nassau.txt
 *  http://www.cs.princeton.edu/introcs/21function/arabesque.txt
 *  http://www.cs.princeton.edu/introcs/21function/firstcut.txt 
 *  http://www.cs.princeton.edu/introcs/21function/tomsdiner.txt
 *
 *************************************************************************/
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SongPlayer {
    /** Keyboard layout. */
    public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) throws FileNotFoundException {

        JFileChooser chooser = new JFileChooser(new File("."));
        FileNameExtensionFilter filter =
            new FileNameExtensionFilter("music files", "txt");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION)
            return;
        Scanner input = new Scanner(chooser.getSelectedFile());

        Instrument instr = new MultiStringInstrument();
        int time = 0;

        // Repeat as long as there are more integers to read.
        while (input.hasNextInt()) {
            // Read in the pitch, where 0 = Concert A (A4).
            int pitch = input.nextInt();
            // Convert the pitch to the appropriate string index.
            int index = pitch + 12;
            // If the note is included in our keyboard, play it.
            if (index >= 0 && index < KEYBOARD.length()) {
                instr.pluck(KEYBOARD.charAt(index));
            }

            // Read in duration in seconds and convert to a tic count.
            double duration = input.nextDouble();
            int target = time + 
                (int) Math.round(duration * StdAudio.SAMPLE_RATE);

            // Keep playing the instrument for that duration.
            while (time < target) {
                instr.play();
                instr.tic();
                time++;
            }
        }
    }
}
