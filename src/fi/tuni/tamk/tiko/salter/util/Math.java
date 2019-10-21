package fi.tuni.tamk.tiko.salter.util;
/**
 * Math class does simple mathmatical tasks. 
 * 
 * This class holds methods that are needed in calculus
 * regarding lottery machine. 
 * 
 * @author Terhi Salonen  * 
 */
public class Math {

      /**
     * Returns random integer between the given range.
     * @param min is the argument, which sets the lowest end of the range
     * @param max is the argument, which sets the highest end of the range
     * @return is random integer between given range
     */
    public static int getRandom( int min, int max ) {
        return min + ( int ) ( java.lang.Math.random() * ( ( max - min ) + 1 ) );
    }
}