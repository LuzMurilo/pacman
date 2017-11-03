package control;

import elements.Element;
import elements.Pacman;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class GameController {
    public void drawAllElements(ArrayList<Element> elemArray, Graphics g){
        for(int i=0; i<elemArray.size(); i++){
            elemArray.get(i).autoDraw(g);
        }
    }
    public void processAllElements(ArrayList<Element> e){
        if(e.isEmpty())
            return;
        
        Pacman lPacman = (Pacman)e.get(0);
        if (!isValidPosition(e, lPacman)) {
            lPacman.backToLastPosition();
            lPacman.setMovDirection(Pacman.STOP);
            return;
        }
        
        Element eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(lPacman.overlap(eTemp))
                if(eTemp.isTransposable())
                    e.remove(eTemp);
        }
        
        lPacman.move();
    }
    public boolean isValidPosition(ArrayList<Element> elemArray, Element elem){
        Element elemAux;
        for(int i = 1; i < elemArray.size(); i++){
            elemAux = elemArray.get(i);            
            if(!elemAux.isTransposable())
                if(elemAux.overlap(elem))
                    return false;
        }        
        return true;
    }
}
