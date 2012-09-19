/**
 * The Instrument interface specifies a methods used to implement
 * a musical instrument that can play various notes that correspond
 * to characters such as associated with keys typed by the user or
 * read from a file.
 */
public interface Instrument {
    /**
     * Indicates if the parameter corresponds to a string in the instrument.
     * @param string a character that may correspond to a string
     * @return true if there is a string that corresponds to the parameter;
     *         false otherwise
     */
    public boolean hasString(char string);

    /**
     * Plucks the string associated with the parameter.
     * @param string a character corresponding to a string to be plucked
     */
    public void pluck(char string);

    /** Plays the current sound (sum of all strings). */
    public void play();

    /** Advances the simulation by invoking each string's tic method. */
    public void tic();
}
