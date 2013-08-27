package src;

import java.util.*;
/**
 * Creates an instrument that includes visualization.
 */
public class VisualInstrument implements Instrument {
    /** The collection of strings, one for each known pitch. */
    private InstrumentString[] strings;
    private List<Double> samples;
    /** Keyboard layout. */
    public static final String KEYS = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    /** Number of tics between visualizer updates. */
    public static final int INTERVAL = 200;

    public VisualInstrument() {
        StdDraw.setCanvasSize(1012, 100);
        int width = 512;
        this.samples = new LinkedList<Double>();
        for (int i = 0; i < width; i++) {
            this.samples.add(0.0);
        }

        this.strings = new SteelString[KEYS.length()];
        for (int i = 0; i < this.strings.length; i++) {
            double frequency = 440 * Math.pow(2, (i - 24.0) / 12.0);
            this.strings[i] = new SteelString(frequency);
        }
    }

    /**
     * Indicates if the parameter corresponds to a string in the instrument.
     * @param string a character that may correspond to a string
     * @return true if there is a string that corresponds to the parameter;
     *         false otherwise
     */
    public boolean hasString(char string) {
        return KEYS.indexOf(string) >= 0;
    }

    /**
     * Plucks the string associated with the parameter.
     * @param string a character corresponding to a string to be plucked
     * @throws IllegalArgumentException if there is no corresponding string
     */
    public void pluck(char string) {
        if (!hasString(string)) {
            throw new IllegalArgumentException("string: " + string);
        }
        int index = KEYS.indexOf(string);
        this.strings[index].pluck();
    }

    /**
     * Plays the current sound (sum of all strings) and
     * updates the visualizer.
     */
    public void play() {
        // Compute the superimposition of samples.
        double sample = 0;
        for (InstrumentString string : this.strings) {
            sample += string.sample();
        }
  
        // Send the result to the sound player.
        StdAudio.play(sample);

        this.samples.remove(0);
        this.samples.add(sample);
        if (this.strings[0].time() % INTERVAL == 0) {
            StdDraw.show(0);
            StdDraw.clear();
            double next = this.samples.remove(0);
            this.samples.add(next);
            double prevY = next + 0.5;
            for (int i = 1; i < this.samples.size(); i++) {
                next = this.samples.remove(0);
                this.samples.add(next);
                double nextY = next + 0.5;
                StdDraw.line((i - 1) / 512.0, prevY, i / 512.0, nextY);
                prevY = nextY;
            }
        }
    }

    /** Advances the simulation by invoking each string's tic method. */
    public void tic() {
        for (InstrumentString string : this.strings) {
            string.tic();
        }
    }
}
