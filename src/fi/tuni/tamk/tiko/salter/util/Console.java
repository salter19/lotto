package fi.tuni.tamk.tiko.salter.util;


import java.util.Scanner;

/**
 * Console class is the GUI of this lottery software.
 * It is used to collect input from the user.
 */
public class Console {

    final static String emptyStr = "";

    final static String input_a = "a";
    final static String input_b = "b";
    final static String input_c = "c";
    final static String input_n = "n";
    final static String input_y = "y";

    final static String lottotype_regular = "regular";
    final static String lottotype_bigWin = "bigWin";
    final static String lottotype_testRun = "testRun";


    /**
     * Method getLottoType asks user to choose which type of
     * lotto game user wants to play. It keeps asking until 
     * valid option has been chosen.
     * 
     * @return int array with the parameters for chosen game type
     */
    public static String getLottoType() {

        boolean invalid = true;
        String lottoType = emptyStr;
        Scanner s = new Scanner (System.in);

        Output.printChoices();        

        while ( invalid ) {

            String input = s.nextLine();

            if ( input.equals( input_a ) || input.equals( input_b ) || input.equals( input_c ) ) {

                switch ( input ) {
                    case input_a: 
                        lottoType = lottotype_regular;
                        break;
                    case input_b:
                        lottoType = lottotype_bigWin;
                        break;
                    case input_c:
                        lottoType = lottotype_testRun;
                        break;
                }
                invalid = false;

            } else {
                Output.printJustChoices();
            }            
        }
        return lottoType;
    }

    /**
     * Method showRows checks if user wants to see the weekly rows.
     * @return boolean value depending on the answer
     */
    public static Boolean showRows() {
        boolean showOrNot = false;
        Scanner s = new Scanner (System.in);
        boolean invalid = true;

        Output.printAskLottery();
           
        while ( invalid ) {

            String show = s.nextLine();

            if ( show.equals( input_y ) || show.equals( input_n ) ) {

                switch ( show ) {
                    case input_y:
                        showOrNot = true;
                        break;
                    case input_n:
                        break;
                }                
                invalid = false;

            } else {
                Output.printShowRowsChoices();
            }         
        }
        return showOrNot;
    }
     
    /**
     * Method readInt reads user input and returns it if it's of valid form.
     * @return int type input
     */
    public static int readInt() {

        Scanner s = new Scanner( System.in );
        boolean invalid = true;

        int input = -1;
        
        while ( invalid ) {
            try {
                input = Integer.parseInt( s.nextLine() );
                invalid = false;
                
            } catch ( NumberFormatException e) {
                Output.printErrorNotNumber();
            }
        }
        return input;
    }

    /**
     * Method readInt calls another method named readInt, which will in turn 
     * get valid type of user input (int). Then this readInt will check that the
     * given input sets inside given range, if not, it outputs an error message
     * regarding the situation and then loops another try.
     *
     * @param min argument, which sets the lower end of the range
     * @param max argument, which sets the upper end of the range
     * @return int type input
     */
    public static int readInt( int min, int max ) {
        
        int input = -1;
        boolean invalid = true;        

        // loop until valid input is reached
        while ( invalid ) {
            Output.askNumber( min, max );         
            input = readInt( );

            if ( input >= min && input <= max ) {
                invalid = false;
                return input;
            } else {
                Output.printErrorOutOfRange();
            }
        }
        return input;
    }

    /**
     * Method getUserRow gets the lotto row user wants.
     * It checks that user enters numbers within the set pool, and that
     * user gives unique numbers for the row. With the valid input the method 
     * compiles a lottery rowsize for the user.
     * 
     *@param size argument that takes in the length of the lottery row for the user
     *@param poolSize is the argument, that tells about the range where user can do 
     * the picking
     * @return int array that represents user's lotto row 
     */
    public static int [] getUserRow( int size, int poolSize ) {

        int [] row = new int[size];
        int nr = -1;
        int min = 1;
        int rowSize = size;
        int validCount = 0;

        while ( validCount < rowSize ) {
            
            nr = readInt( min, poolSize );
                              
            if ( Arrays.contains( nr, row ) ) {
                Output.printErrorNotUnique();
                
            } else {
                row[validCount] = nr;
                validCount ++;
            }            
        }  
        return row;      
    }    
}