package fi.tuni.tamk.tiko.salter;
import fi.tuni.tamk.tiko.salter.util.*;

/**
 * Class GameEngine is the class, which has the method that runs the lotto. 
 */

public class GameEngine {
    
    /**
     * Method playTillWin draws a new lotto row for week after week until it matches
     * user's row within a lifetime. If the matching rows are reached after a lifetime,
     * the game outputs a message regarding this and starts the time count and 
     * correct number count from default.
     * 
     * @param lottoRowLength is an argument, which tells how many numbers make the jackpot row
     * @param poolSize is an argument, which tells the range of the number pool where the
     * lottery numbers are being drawn from
     * @param userRow is an argument, that tells the numbers user has played
     * @param showRows is an argument, which tells whether the user wants to see rows for each week
     */
    public static void playTillWin( int lottoRowLength, int poolSize, int [] userRow, boolean showRows ) {
        
        int [] lotteryR;
        int [] howManyYears = new int [lottoRowLength];

        int weeks = 0;   
        int years = 0;
        int lifeTime = 120;    
        int rightNos = 0;
        int here = 0;
        
        boolean notAll = true;
        boolean withinLifetime = false;

        while ( notAll && !withinLifetime ) {
            
                
            for ( int i = 0; i < howManyYears.length; i++ ) {
                howManyYears[i] = -1;
            }

            while ( notAll ) {              

                // for each week raffle a new row
                lotteryR = LottoMachine.calculateLotto( poolSize, lottoRowLength );

                // get the count of matching numbers between lotto row and user's row
                rightNos = Arrays.containsSameValues( lotteryR, userRow );

                // if user wants to see the rows for each week, print them
                if ( showRows ) {
                    Output.printRowsAndRight( userRow, lotteryR, rightNos, years );
                }
            
                // increase weeks count
                weeks ++;

                // turn weeks into year and start weeks again
                if ( weeks == 52 ) {
                    years ++;
                    weeks = 0;
                }    

                // keep track of the first occurence of n right numbers 
                // ( n = count of the right no's, here = the (new) start point )
                for ( int i = here; i < howManyYears.length || ( rightNos == ( i + 1 ) ); i++ ) {
                    
                    if ( rightNos == ( i + 1 ) ) { 
                        
                        // check if the prior rightNos have been handled already
                        // if not, make them the same with this one
                        howManyYears = Arrays.checkPrior( i, howManyYears, years );
                        
                        // shift the iterations start point up a noch
                        here = i + 1;
                    }                  
                }
                
                // check if all the numbers where a match within a lifetime
                if ( rightNos == lottoRowLength && years <= lifeTime ) {
                    
                    // if so, output winning strings and terminate the game                    
                    withinLifetime = true;
                    Output.printWin( howManyYears, withinLifetime );
                    notAll = false;

                } else if ( rightNos == lottoRowLength ) {

                    // if only the numbers part is true, output the other winning strings
                    Output.printWin( howManyYears, withinLifetime );

                    // set things to default to start again
                    weeks = 0;
                    years = 0;
                    rightNos = 0;
                    here  = 0;

                    howManyYears = new int [lottoRowLength];
                    for ( int i = 0; i < howManyYears.length; i++ ) {
                        howManyYears[i] = -1;
                    }                    

                    // keep at it
                    notAll = true;
                }   
            }             
        }    
    } 
}