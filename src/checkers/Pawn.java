/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 * Contains methods related to drawing pawns on the game board 
 * 
 * @author sergeyv
 */
public class Pawn{
    
    Color color;
    int position, row, col;
    int x,y,width,height;
    boolean isKing;

    /**
     * Pawn diameter
     */
    public static final int DIAMETER = 31;
    Square square;
    Ellipse2D pawnEllipse;
    Ellipse2D kingEllipse;
    boolean moving;
    
    /**
     *
     * @param s  Square that the pawn is initially assigned to/
     * @param color Pawn color
     */
    public Pawn(Square s, Color color){
      
       this.square = s;
       this.width = this.height = Pawn.DIAMETER;
       isKing = false;
       updatePosition();
       this.color = color;
       moving = false;
    }

    /**
     * Sets coordinates for drawing a pawn when it is being moved
     * 
     * @param mouseX The X coordinate of the mouse click
     * @param mouseY The Y coordinate of the mouse click
     */
    public void movePawn(int mouseX, int mouseY){
        moving = true;
        int tempX = mouseX - Pawn.DIAMETER / 2;
        int tempY = mouseY - Pawn.DIAMETER / 2; 
        pawnEllipse = new Ellipse2D.Double(tempX, tempY, this.width, this.height);
        if (isKing){
           kingEllipse = new Ellipse2D.Double(pawnEllipse.getX() - 2,
                    pawnEllipse.getY() - 2, this.width + 4, this.height + 4);
       }
    }
    
    /**
     * Method to handle painting of the pawn.
     * 
     * @param g
     */
    public void paint (Graphics g) {
    Graphics2D g2 = (Graphics2D) g; 
        if (isKing){
            if (moving){
               g2.setColor(new Color(Color.YELLOW.getRed(),Color.YELLOW.getGreen(),
                       Color.YELLOW.getBlue(),180)); 
            }else{
               g2.setPaint(Color.YELLOW); 
            }
            g2.setStroke(new BasicStroke(2));
            g2.draw(kingEllipse);
        }
        if (moving){
            g2.setColor(new Color(this.color.getRed(),0, this.color.getBlue(),60));
        }else{
            g2.setPaint(this.color);
        }
            
            // a dull blue-green
        g2.fill(pawnEllipse);
    } 
    
    /**
     * Method to update the pawn's position
     */
    public void updatePosition(){
       this.x = this.square.x + (this.square.WIDTH - Pawn.DIAMETER) / 2;
       this.y = this.square.y + (this.square.HEIGHT - Pawn.DIAMETER) / 2;
       if (this.color == Color.RED && this.square.row == 0 ||
               this.color == Color.BLUE && this.square.row == 7){
                    isKing = true;
       }
       pawnEllipse = new Ellipse2D.Double(this.x,this.y,this.width,this.height);
        if (isKing){
           kingEllipse = new Ellipse2D.Double(pawnEllipse.getX() - 2,
                    pawnEllipse.getY() - 2, this.width + 4, this.height + 4);
       }
    }
    
}
