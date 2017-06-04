/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 * Board square
 * @author sergeyv
 */
public class Square extends JComponent{
   
    int x, y, row, col, index;
    final int WIDTH = 45;
    final int HEIGHT = 45;
    boolean isBlack;
    boolean hasPawn; // -1 - no pawn 0 - red 1 - blue
    Rectangle2D rectangle;
    Pawn p;
    
    /**
     * Creates a square 
     * 
     * @param index Square index
     */
    public Square(int index){
        this.index = index;
        row = this.index / 8;
        col = this.index % 8;
        x = col * WIDTH;
        y = row * HEIGHT;
        isBlack = (row + col) % 2 == 0 ? false : true;
        rectangle = new Rectangle2D.Double(x, y, WIDTH, HEIGHT);
        hasPawn = false;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //super.paintComponent(g);
        Color c = Color.WHITE;
        if (isBlack)
            c = Color.darkGray;
        g2.setColor(c);
        g2.fill(rectangle);
    }

    /**
     * Assigns a pawn to the square
     * 
     * @param p Pawn being assigned
     */
    public void setPawn(Pawn p){
        this.p = p;
        hasPawn = true;
        //this.repaint();
    }
}
