package fi.tuni.tamk.tiko.salter;

import fi.tuni.tamk.tiko.salter.util.*;
import fi.tuni.tamk.tiko.salter.GameEngine;

public class Main {

    static int lottoRowLength;
    static int poolSize;
    static int [] userRow; 
    
    final static int separator_long = 8;
    final static int separator_short = separator_long / 2;
    final static int separator_medium = 6;

    final static int testRowLength = 5;

    /**
     * Method main is the initializer of the software.
     * 
     * @param args commandline arguments; if given valid type and amount, 
     * lotto type is chosen and user row is set by it.
     */    
    public static void main( String [] args ) {              

        // define lotto type and along with it lotto rows length and number pool's range
        LottoMachine.setLotto( args );
        lottoRowLength = LottoMachine.getLottoRowLength();
        poolSize = LottoMachine.getPoolSize();

        // get the user's row either from commandline input or by asking number by number 
        userRow = LottoMachine.getUserRow();
        Output.printUserRow( userRow, 1 );

        // Show the rows if user wants to see them.
        // Shows user's row, lotto row and the amount of right numbers per week
        boolean showRows = Console.showRows();
        
        // let the game begin
        GameEngine.playTillWin( lottoRowLength, poolSize, userRow, showRows );       
    }
}