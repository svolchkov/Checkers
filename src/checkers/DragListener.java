/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 * Handles movement and removal of pawns of the board.
 * 
 * @author sergeyv
 */
public class DragListener extends MouseInputAdapter
{
    Point location;
    MouseEvent pressed;
    Pawn clickedPawn;
    Square sourceSquare;
    
    public void mousePressed(MouseEvent me)
    {
        CheckersBoard c = (CheckersBoard) me.getComponent();
        pressed = me;
        clickedPawn = null;
         
        for (Pawn p : c.pawns){
            if (p.pawnEllipse.contains(me.getX(), me.getY())){
                clickedPawn = p;
                sourceSquare = p.square;
                break;
            }
        }
        if (SwingUtilities.isRightMouseButton(me)){
            c.pawns.remove(clickedPawn);
            clickedPawn.square.hasPawn = false;
            clickedPawn = null;
            c.repaint();
        }
    }
 
    public void mouseDragged(MouseEvent me)
    {
//        JComponent component = (JComponent) me.getComponent();
        CheckersBoard c = (CheckersBoard) me.getComponent();
//        location = component.getLocation(location);
//        int x = location.x - pressed.getX() + me.getX();
//        int y = location.y - pressed.getY() + me.getY();
        if (clickedPawn != null){
            clickedPawn.movePawn(me.getX(), me.getY());
            c.repaint();
        }
     }
    
    public void mouseReleased(MouseEvent me){
        CheckersBoard c = (CheckersBoard) me.getComponent();
        if (clickedPawn != null){
            
            for (Square s:c.squares){
                if (s.rectangle.contains(me.getX(), me.getY())){
                    if (s.isBlack && !s.hasPawn){
                        //System.out.println("Illegal move");
                        clickedPawn.square = s;
                        sourceSquare.hasPawn = false;
                        s.hasPawn = true;
                        if (clickedPawn.color == Color.RED){
                            Board.setNextMove(Color.BLUE);
                        }else{
                            Board.setNextMove(Color.RED);
                        }
                         
                    }                
                    clickedPawn.updatePosition();
                    clickedPawn.moving = false;
                    clickedPawn = null;
                    break;
                }
            }
            c.repaint();
        }
    }
}    

