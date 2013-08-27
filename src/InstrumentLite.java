package src;

/** This sample class implements the Instrument interface. */
public class InstrumentLite implements Instrument {
    private InstrumentString stringA;
    private InstrumentString stringB;
    private InstrumentString stringC;

    /** Creates two steel strings, for concert A and C. */
    public InstrumentLite() {
        double CONCERT_A = 440.0;
        double CONCERT_B = CONCERT_A * Math.pow(2, 2.0/12.0);
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0);  
        this.stringA = new SteelString(CONCERT_A);
        this.stringB = new SineString(CONCERT_B);
        this.stringC = new SteelString(CONCERT_C);
    }

    /**
     * Indicates if the parameter corresponds to a string in the instrument.
     * @param key a character that may correspond to a string
     * @return true if there is a string that corresponds to the parameter;
     *         false otherwise
     */
    public boolean hasString(char string) {
        return (string == 'a' || string == 'b' || string == 'c');
    }

    /**
     * Plucks the string associated with the parameter.
     * @param key a character corresponding to a key to be plucked
     */
    public void pluck(char string) {
        if (string == 'a') {
            this.stringA.pluck();
        } else if (string == 'b') {
            this.stringB.pluck();
        } else if (string == 'c') {
            this.stringC.pluck();
        }
    }

    /** Plays the current sound (sum of all strings). */
    public void play() {
        // Compute the superposition of samples.
        double sample = stringA.sample() + stringB.sample() + stringC.sample();
        // Send the result to the sound player.
        StdAudio.play(sample);
    }

    /** Advances the simulation by invoking each string's tic method. */
    public void tic() {
        this.stringA.tic();
        this.stringB.tic();
        this.stringC.tic();
    }
}
