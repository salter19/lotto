package fi.tuni.tamk.tiko.salter.util;

import fi.tuni.tamk.tiko.salter.util.Output;

/**
 * Arrays class works with given arrays, mostly creating them
 * and comparing them to each other.
 */
public class Arrays {

    final static String emptyStr = "";
    final static String separatorWithSpace = ", ";

    /**
     * Method toIntArray converts given String array into an int one.
     * @param array argument to be converted 
     * @return int conversion of the argument given 
     */    
    public static int [] toIntArray( String [] array ) {

        int size = array.length;
        int [] temp = new int [size];
        
        for ( int i = 0; i < size; i++ ) {

            try {
                temp[i] = Integer.parseInt( array[i] );
                
            } catch ( NumberFormatException e ) {
                Output.printNotInt();
            }
        }
        return temp;
    } 

    /**
     * Method contains checks if given value is included in given array.
     * @param value argument to be looked for
     * @param array argument where to look
     * @return boolean value of the result of the check up
     */ 
    public static Boolean contains( int value, int [] array ) {
        boolean containsOrNot = false;
        int size = array.length;

        for ( int i = 0; i < size && !containsOrNot; i++ ) {
            if ( array[i] == value ) {
                containsOrNot = true;
            }
        }
        return containsOrNot;
    }

    /**
     * Method containsSameValues compares two int arrays and counts the values, 
     * which can be found in both given arrays.
     * @param array1 argument, that holds one of the two given arrays
     * @param array2 argument, that holds one of the two given arrays
     * @return int value of the count
     */

    public static int containsSameValues( int [] array1, int [] array2 ) {
        int sameValCount = 0;
        int [] lottoRow = array1;
        int [] userRow = array2;       

        // Increase sameValCount for each time same number is been found in both arrays.
        for ( int i = 0; i < lottoRow.length; i++ ) {

            if ( contains( lottoRow[i], userRow ) ) {
                sameValCount ++;
            }            
        }  
        return sameValCount;
    }

    /**
     * Method removeIndex removes given index value from given array. 
     * Copies numbers from the original list to the temp one until iteration 
     * reaches the index given. When reaches index, skips to the next index 
     * at the original list and continues copy from there on. Thus, the temp
     * array is one shorter than original one.
     * 
     * @param original argument, that gives the array from where to remove a number
     * @param index argument shows, which index is to be cleaned out
     * @return int array one index smaller than the one that was given as a parameter 
     */
    public static int [] removeIndex( int [] original, int index ) {

        int [] temp = new int [original.length - 1];
        int num = -1;

        for ( int i = 0; i < temp.length; i++ ) {

            // if index is reached, from there on out copy  the inerts 
            // of index one step further on the array
            if ( i >= index ) {
                num = original[i + 1];
            } else {
                num = original[i];
            }

            temp[i] = num;
        }
        return temp;
    }

    /**
     * Method sort is used for sorting int array in ascending order.
     * 
     * Method sorts the given array by repeatedly finding the minimum element
     * from the unsorted part of the array. In every iteration of the method,
     * the minimum element from the unsorted part is picked and moved to the
     * index at hand in sorted part. 
     * 
     * @param array argument, that holds the int array to be sorted.
     * @return given int array after it has been sorted.
     */
    public static int [] sort( int [] array ) {

        int [] sortThis = array;

        for ( int i = 0; i < sortThis.length - 1; i++ ) {

            int min_index = i;

            for ( int j = i + 1; j < sortThis.length; j++ ) {
                
                if (sortThis[j] < sortThis[min_index]) {
                    min_index = j;
                }
            }

            int temp = sortThis[min_index];
            sortThis[min_index] = sortThis[i];
            sortThis[i] = temp;
        }        
        return sortThis;
    }

    /**
     * Method createStringArr is a method to create String arrays.
     * 
     * Method adds prefix to numbers, which are under ten.
     * 
     * @param array argument, which contains the array to be converted
     * @param prefixLength argument, which tells the length of the prefix
     * @return String array
     */
    public static String [] createStringArr( int [] array, int prefixLength ) {

        String [] temp = new String [array.length];
        String strUnderTen = emptyStr;
        int prefix = 0;

        for ( int i = 0; i < prefixLength; i++ ) {
            strUnderTen += prefix;
        }

        for ( int i = 0; i < array.length; i++ ) {            
            
            if ( array[i] < 10 ) {
                temp[i] = strUnderTen + ( array[i] );
            } else {
                temp[i] = emptyStr + array[i];
            }
        }
        return temp;
    }

    /**
     * Method intArrIntoString converts int array into string format.
     * 
     * Method converts int array into String array by calling method createStringArr
     * and then it iterates through that String array, and adds every array item with
     * suffix into a string.  
     *  
     * @param array an argument, which contains the int array to be converted 
     * @param prefixLength an argument, which tells how many chars are in the prefix
     * @return String
     */
    public static String intArrIntoString ( int [] array, int prefixLength ) {

        String [] arr = createStringArr( array, prefixLength );
        String row = emptyStr;

        for ( int i = 0; i < arr.length; i++ ) {
            

            if ( i == arr.length - 1 ) {
                row += arr[i];
            } else {
                row += arr[i] + separatorWithSpace;
            }
        }
        return row;
    }

    /**
     * Method checkPrior is used for checking the right numbers so far.
     * 
     * It iterates through given int array from given index backwards toward 
     * the array's first index. If it comes across default value (-1) it changes 
     * that index's value into the given years.
     * 
     * @param backUp is argument, that tells the backing up start point
     * @param howManyYears is the given int array
     * @param years is the years that will be added to the default bearing index
     * @return int array with fixed index values
     */
    public static int [] checkPrior( int backUp, int [] howManyYears, int years ) {
        

        for ( int i = backUp; i >= 0; i-- ) {
            
            if ( howManyYears[i] == -1 ) {
                howManyYears[i] = years;                
            } 
        }
        return howManyYears;
    }

    /**
     * Method to help printing an int array. Uses decimal point and space
     * as a splitter.
     * 
     * @param array argument, which is the given array to be printed
     */
    public static void printIntArray( int [] array ) {

        for ( int i = 0; i < array.length; i++ ) {

            if ( i == array.length - 1) {                
                Output.print(  emptyStr + array[i] );

            } else {
                Output.print(  array[i] + separatorWithSpace );
            }
        }
        Output.printLn( emptyStr );;
    }

    /**
     * Method isIntArray checks, if given String array is convertable into int one.
     * 
     * @param array is an argument, which holds the String array to be tested
     * @return boolean value of the test result
     */
    public static Boolean isIntArray( String [] array ) {

        try {            
            int [] temp = toIntArray( array );
            return true;
            
        }  catch ( Exception e ) {
            return false;
        }
    }

    /**
     * Method inRange checks that the given int array values are within given range.
     * Minimum value of range is always 1.
     * @param array argument given array
     * @param max argument given high end of the range
     * @return boolean value
     */
    public static boolean inRange ( int [] array, int max ) {

        int min = 1;

        for ( int i = 0; i < array.length; i++ ) {
            
            if ( array[i] < min ) {
                return false;

            } else if ( array[i] > max ) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method unique checks that the values in given array are unique.
     * 
     * @param array given int array
     * @return boolean value
     */
    public static boolean unique( int [] array ) {
        boolean unique = true;

        for ( int i = 0; i < array.length && unique ; i++ ) {

            int number = array[i];
            int [] temp = removeIndex( array, i );

            if (contains( number, temp ) ) {
                unique = false;
            }             
            array = temp;
        }
        return unique;
    }
}