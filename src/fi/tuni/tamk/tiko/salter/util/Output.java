package fi.tuni.tamk.tiko.salter.util;

public class Output {

    // misc
    final static String charLinebreak = "\n";
    final static String charSeparator = "-";
    final static String congratulations = " Congratulations!";
    final static String youWon = charLinebreak + " YOU WON!";    
    final static String moreThanLifetime = " It took more than lifetime though, so let's try it again!";

    // arrays
    final static String arrays_errorMsgNotInt = "Error occured int toIntArray. Couldn't parse into integer.";

    // console lotto type
    final static String console_askChoices = " Do you want to play regular lotto" 
                                            + charLinebreak
                                            + " or do you want play the Big Win lotto?" 
                                            + charLinebreak
                                            + " You can also do a test run.";
    
    final static String console_choices = " Choose [a] for regular lotto."
                                        + charLinebreak
                                        + " Choose [b] for Big Win lotto." 
                                        + charLinebreak
                                        + " Choose [c] for test run.";

    // console weekly output
    final static String console_lotteryEachWeek = " Do you wish to see the lottery for each week?"; 
    final static String console_showRowChoices = " Choose [y] for yes and [n] for no.";
    
    // console get numbers
    final static String console_rangeBegin = " Please give unique number between [";
    final static String console_rangeMiddle = ", ";
    final static String console_rangeStop = "]";
    
    // console error messages
    final static String console_errorMsgNonNumeric = " Please give a number.";
    final static String console_errorMsgNotUnique = " Not a UNIQUE number!";
    final static String console_errorMsgNotInRange = " The number you gave is out of range.";

    // game statistics
    final static String gameStat_begin = " Got ";
    final static String gameStat_middle = " right! Took ";
    final static String gameStat_stop = " years.";
    
    // rows 
    final static String row_userBegin = "  Your row: [";
    final static String row_lottoBegin = " Lotto row: [";
    final static String row_stop = "]";

    // separators
    final static int separatorLength_medium = 6;
    final static int separatorLength_long = 8;
    final static int separatorLength_short = separatorLength_long / 2;
     
    /**
     * Method printWin prints the output when the user gets all the numbers
     * right at once. Depending on how long it took to gain this, the output varys.
     * If the correct row has been played ( user has won ) within a lifetime 
     * ( here 120 years ) the output is slightly different than when winning takes
     * more than a single lifetime. 
     * 
     * @param howManyYears argument that tells how many years it took to get certain
     * amount of correct numbers all at once
     * @param withinLifetime argument that tells if all the numbers where correct at
     * once with in a given lifetime ( here 120 years ).
     */
    public static void printWin( int [] howManyYears, boolean withinLifetime ) {

        for ( int i = 0; i < howManyYears.length; i++ ) {
            printLn( gameStat_begin + ( i+1 ) 
                    + gameStat_middle + howManyYears[i] 
                    + gameStat_stop );
        }

        if ( withinLifetime ) {
            printLn( youWon );
            printLn( congratulations );
            printSeparator( 1, 0, separatorLength_medium );

        } else  {            
            printLn( youWon );            
            printLn( moreThanLifetime );
            printSeparator( 1, 1, separatorLength_medium );            
        }
    }

    /**
     * Method printRowsAndRight is a method, which prints out both rows, 
     * the one that the user chose and the one that was raffled as lotto row. The
     * method also prints out the number of right numbers user had chosen and the 
     * time it has taken so far.
     * 
     * @param userRow argument, that holds user's chosen row
     * @param lotteryR argument, that holds the official lotto row
     * @param rightNos argument, that holds the number of correct numbers
     * @param years argument, that holds the number of years so far
     */
    public static void printRowsAndRight( int [] userRow, int [] lotteryR, int rightNos, int years ) {

        int prefixLength = 1;

        // sort rows
        int [] sortedUR = Arrays.sort( userRow );
        int [] sortedLR = Arrays.sort( lotteryR );

        // turn rows (int arrays) into String arrays and 
        // give 'em prefix of zeros, with given amount
        String userR = Arrays.intArrIntoString( sortedUR, prefixLength );
        String lottoR = Arrays.intArrIntoString( sortedLR, prefixLength );

        // print rows
        printSeparator( 0, 0, separatorLength_medium );

        printLn( row_userBegin + userR + row_stop );
        printLn( row_lottoBegin + lottoR + row_stop );

        printLn( charLinebreak + gameStat_begin + rightNos 
                + gameStat_middle + years 
                + gameStat_stop );
        
        printSeparator( 0, 0, separatorLength_medium );
    }

    /**
     * Method printUserRow prints out user's row.
     * @param userRow argument, which holds the int array of user's numbers
     * @param prefixLength argument, which sets prefix length
     */
    public static void printUserRow( int [] userRow, int prefixLength ) {
        // sort row
        int [] sortedUR = Arrays.sort( userRow );

        // turn row (int array) into String array and 
        // give 'em numbers prefix of zeros, with given amount
        String userR = Arrays.intArrIntoString( sortedUR, prefixLength );

        // print rows
        printSeparator( 0, 0, separatorLength_medium );
        printLn( row_userBegin + userR + row_stop );        
        printSeparator( 0, 0, separatorLength_medium );
    }

    /**
     * Method printSeparator is use to format the output. It prints out a separator
     * line in given length and with optional linebreaks above, below or both.
     * 
     * @param breakAbove argument, which tells whether to insert linebreak above
     * @param breakBelow argument, which tells whether to insert linebreak below
     * @param length argument, which tells the length of the separator
     */
    public static void printSeparator( int breakAbove, int breakBelow, int length ) {

        String separator = "";

        for ( int i = 0; i < length; i++ ) {
            separator += charSeparator;
        }

        if ( breakAbove == 1 && breakBelow == 1 ) {

            separator = charLinebreak + separator + charLinebreak;

        } else if ( breakAbove == 1 ) {

            separator = charLinebreak + separator;

        } else if ( breakBelow == 1 ) {

            separator += charLinebreak;
        }
        printLn( separator );
    }

    /**
     * Method printChoices is used to print game options to console.
     */
    public static void printChoices() {

        printSeparator( 1, 0, separatorLength_long );
        printLn( console_askChoices );
        printSeparator( 0, 0, separatorLength_short );
        printLn( console_choices );
        printSeparator( 0, 0, separatorLength_short );
    }

    /**
     * Method printJustChoices prints only the game options.
     */
    public static void printJustChoices() {
        printSeparator( 0, 0, separatorLength_medium );
        printLn( console_choices );
        printSeparator( 0, 0, separatorLength_short );
    }

    /**
     * Method askNumber is used to ask user for numbers for the user's row.
     * 
     * @param min argument, which tells the beginning number of the range.
     * @param max argument, which tells the last number in the range.
     */
    public static void askNumber( int min, int max ) {
        printLn( console_rangeBegin + min 
                + console_rangeMiddle + max 
                + console_rangeStop );
    }

    /**
     * Method printErrorOutOfRange is used to print out error message when
     * number is out of given range.
     */
    public static void printErrorOutOfRange() {
        printLn( console_errorMsgNotInRange );
    }

    
    /**
     * Method printErrorNotNumber is used to print out error message when
     * input is not (type) interger.
     */
    public static void printErrorNotNumber() {
        printLn( console_errorMsgNonNumeric );
    }

    
    /**
     * Method printErrorNotUnique is used to print out error message when
     * number given is not unique.
     */
    public static void printErrorNotUnique() {
        printLn( console_errorMsgNotUnique );
    }
 
    /**
     * Method printAskLottery is used to ask user if he / she wants to see weekly
     * drawn lotto rows.
     */
    public static void printAskLottery() {
        printSeparator( 0, 0, separatorLength_long );
        printLn( console_lotteryEachWeek );
        printLn( console_showRowChoices );
        
    }

    /**
     * Method printShowRowsChoices is used to print out the options of showing weekly
     * rows.
     */
    public static void printShowRowsChoices() {
        printLn( console_showRowChoices );
    }

    /**
     * Method printNotInt prints out error message.
     */
    public static void printNotInt() {
        printLn( arrays_errorMsgNotInt );
    }

    /**
     * Method printLn is a quick tool for output.
     * Method prints String line with break.
     * 
     * @param i is the argument to be printed
     */
    public static void printLn( String i ) {
        System.out.println( i );
    }
   
    /**
     * Method print is a quick tool for output.
     * Method prints given String without break in the end.
     * 
     * @param i is the argument to be printed
     */
    public static void print( String i ) {
        System.out.print( i );
    }    
}