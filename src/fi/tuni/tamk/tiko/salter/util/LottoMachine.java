package fi.tuni.tamk.tiko.salter.util;

import fi.tuni.tamk.tiko.salter.util.Console;
import fi.tuni.tamk.tiko.salter.util.Math;
import fi.tuni.tamk.tiko.salter.util.Arrays;

/**
 * Class LottoMachine holds different methods regarding lottery process. 
 * 
 * LottoMachine sets lotto type, it gets lotto type and calculates (= draws) lotto.
 * It also gets and sets and gets again the user's row.
 */
public class LottoMachine {

    final static int [] lottoTypeRegular = { 7, 40 };
    final static int [] lottoTypeBigWin =  { 9, 80 };
    final static int [] lottoTypeTestRun = { 5, 15 };

    final static String regular = "regular";
    final static String bigWin = "bigWin";
    final static String testRun = "testRun";
    final static String empty = "";

    static int lottoRowLength;
    static int poolSize;
    static int [] userRow;

    /**
     * Method setLottoType sets both lotto row's length and
     * the number pool's top limit.
     * 
     * The lotto types, which these numbers depend on
     * are predefined. User get's to choose from them at the start of the game.
     * Choices for user at the moment are 'regular lotto' and ' Big Win lotto'. 
     * 
     * Number pool's range starts by default from 1.
     * 
     * @param lottoT argument, which tells if the lotto type has been figured 
     * already on commandline. If so, sets lotto type based on this.
     */
    public static void setLottoType( String lottoT ) {

        String lottoType;

        if ( lottoT.length() > 0 ) {
            lottoType = lottoT;

        } else {
            // ask user to select between different types of lotto
            lottoType = Console.getLottoType();

        }

        switch ( lottoType ) {

            case regular:
                    lottoRowLength = lottoTypeRegular[0];
                    poolSize = lottoTypeRegular[1];
                    break;
            case bigWin:
                    lottoRowLength = lottoTypeBigWin[0];
                    poolSize = lottoTypeBigWin[1];
                    break;
            case testRun:            
                    lottoRowLength = lottoTypeTestRun[0];
                    poolSize = lottoTypeTestRun[1];
                    break;            
        }
    }

    /**
     * Method setLotto defines lotto type by the given arguments at commandline.
     * If there are none given, the program proceeds accordingly.
     * If something has been inserted on the commandline,
     * method cheks it's validity and if input is valid, figures the lotto type based
     * on it.
     * 
     * @param cmdInput, argument, which holds the possible commandline input
     */
    public static void setLotto( String [] cmdInput ) {

        String lottoType;
        boolean isIntArray = false;
        boolean isUnique = false;
        int [] temp = new int [cmdInput.length];

        try {
            temp = Arrays.toIntArray( cmdInput );
            isIntArray = true;
            isUnique = Arrays.unique( temp );


        } catch ( NumberFormatException e ) {
            Output.printErrorNotNumber();
            lottoType = getEmpty();
        }
        

        if ( isIntArray && isUnique ) { 
            
            temp = Arrays.toIntArray( cmdInput );

            if ( cmdInput.length == lottoTypeRegular[0] && Arrays.inRange( temp, lottoTypeRegular[1] ) ) {
                       
                lottoType = regular;      

            } else if ( cmdInput.length == lottoTypeBigWin[0]  && Arrays.inRange( temp, lottoTypeBigWin[1] ) ) {
                        
                lottoType = bigWin; 

            } else if ( cmdInput.length == lottoTypeTestRun[0] && Arrays.inRange( temp, lottoTypeTestRun[1] ) ) {                
                  
                lottoType = testRun; 

            } else {
               lottoType = getEmpty(); 
               Output.printErrorOutOfRange();          
            }
        } else {
            lottoType = getEmpty();  
            Output.printErrorNotUnique();          
        }
        setLottoType( lottoType );        
        setUserRow( lottoType, cmdInput );
    }

    /**
     * Method setUserRow sets user row depending either via commandline input
     * or by asking it number by number from the user.
     * 
     * @param lottoType argument, which tells how many numbers and from what 
     * range are needed for user row
     * @param cmdInput argument, that gives the user row given in the commandline
     */
    public static void setUserRow( String lottoType, String [] cmdInput ) {

        if ( lottoType.length() >= lottoTypeTestRun[0]) {

            switch ( lottoType ) {
                case regular:
                case bigWin:
                case testRun:
                        userRow = Arrays.toIntArray( cmdInput );                        
                        Arrays.printIntArray( userRow );
            }

        } else {            
            userRow = Console.getUserRow( getLottoRowLength(), getPoolSize() );
        }
    }

    /**
     * Method getUserRow returns the user row.
     * @return int array
     */
    public static int [] getUserRow() {
        return userRow;
    }

    /**
     * Method to retrieve string which has no chars inside (is empty).
     * @return String 
     */
    public static String getEmpty() { 
        return empty;
    }

    /**
     * Method getLottoRowLength is used to return the length of 
     * current chosen lottorow.
     * @return int
     */
    public static int getLottoRowLength() {
        return lottoRowLength;
    }

    /**
     * Method getPoolSize is used to return the size of 
     * current chosen lotto number pool.
     * @return int
     */
    public static int getPoolSize() {
        return poolSize;
    }
 
    /**
     * Method that calculates ( or more accurately raffles ) lotto row from given
     * range of numbers ( poolSize ).
     * @param poolSize argument, which defines the number pool
     * @param lottoRowLength argument, which defines the number of numbers in lotto row
     * @return int array
     */
    public static int [] calculateLotto( int poolSize, int lottoRowLength) {

        int [] numberPool = getNumberPool( poolSize );
        int [] lottoRow = getLottoRow( lottoRowLength, numberPool );

        return lottoRow;
    }

    /**
     * Method getLottoRow creates lotto row from unique random numbers, 
     * from given number pool. It uses multiple arrays to work through the process.
     * 
     * Array org is the ( chosen ) lotto type's number pool. Array temp is 
     * a helper array for removing the raffled index ( and it's content ) 
     * from array org. Array lottoRow is the place where 
     * the removed index's contents go.
     * 
     * @param size argument, that defines the number of numbers in lotto row 
     * @param numberPool argument, which defines the number pool from which to pick lottery numbers
     * @return int array consisting of lottery numbers 
     */

    private static int [] getLottoRow( int size, int [] numberPool ) {

        int [] org = numberPool;
        int [] temp = new int [org.length -1];
        int [] lottoRow = new int [size];
        int index = -1;
        int number = -1;

        // loop until lottoRow has the amount of numbers it needs
        for ( int i = 0; i < size; i++ ) {

            // get random index
            index = Math.getRandom( 0, org.length -1 );

            // get number from the original array and insert it into lotto row
            number = org[index];
            lottoRow[i] = number;
            
            // remove number from numberPool
            temp = Arrays.removeIndex( org, index );
            org = temp;
        }
        return lottoRow;
    }

    /**
     * Method getNumberPool creates number pool for the lottery.
     * 
     * Number pool's range starts by default from 1.
     * 
     * @param size argument that defines the size of the pool starting from 1 and ending with size.
     * @return int array (numberPool)
     */
    private static int [] getNumberPool( int size ) {

        int [] numberPool = new int [size];

        for (int i = 0; i < size; i++ ) {
            numberPool[i] = i + 1;
        }
        return numberPool;
    }
}