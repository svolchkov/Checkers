/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

/**
 * Main class
 * 
 * @author sergeyv
 */
public class Checkers {

    /**
     * This is the main class of the Checkers application that
     * creates an instance of the main program window.
     * 
     * @param args the command line arguments
     * @see Board
     * @since 1.0
     */
    public static void main(String[] args) {
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Board().setVisible(true);
            }
        });
    }
    
}
