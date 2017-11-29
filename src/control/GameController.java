package control;

import elements.Element;
import elements.Fruit;
import elements.PacDot;
import elements.Pacman;
import elements.PowerPellet;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import utils.Consts;

public class GameController implements Serializable {
    
    private int score = 0;
    
    private int cherryControl = 0;
    private int strawberryControl = 0;
    
    public int getScore() {
        return score;
    }
    
    public void drawAllElements(ArrayList<Element> elemArray, Graphics g){
        for(int i=0; i<elemArray.size(); i++){
            elemArray.get(i).autoDraw(g);
        }
        
        cherryControl++;
        strawberryControl++;
        
        if (cherryControl == Consts.TIMER_CHERRY) {
            cherryControl = 0;
            Fruit cherry = new Fruit("cherry.png");
            // fix me SET POSITION abaixo - precisa ser random de vdd, precisa saber se não tem nada em cima - parte bem dificil
            //      talvez implementar uma matriz de boolean na GameScreen pra falar se tem algo ou não na celula
            //      mas tem q ser dinamico 
            //  (ex - pacman começa em uma posiçao - a celula esta ocupada
            //      mas logo ele muda, precisa atualizar a matriz para liberar a celular anterior e ocupar a nova).
            // 
            // no fim, acho q eh mais facil do que jogar em cima de outra coisa e travar o jogo
            cherry.setPosition(3,(new Random()).nextInt(18));
            elemArray.add(cherry);
            
            // como fazer durar só 15 segundos na tela?
        }
        
        if (strawberryControl == Consts.TIMER_STRAWBERRY) {
            strawberryControl = 0;
            Fruit strawberry = new Fruit("strawberry.png");
            // fix me - mesmas coisas acima
            strawberry.setPosition(14,(new Random()).nextInt(18));
            elemArray.add(strawberry);
            
            // durar 15 segundos
        }       
    }
    
    public void processAllElements(ArrayList<Element> e, GameStage stage){
        if(e.isEmpty())
            return;
        
        Pacman lLolo = (Pacman)e.get(0);
        if (!isValidPosition(e, lLolo)) {
            lLolo.backToLastPosition();
            lLolo.setMovDirection(Pacman.STOP);
            return;
        }
        
        Element eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(lLolo.overlap(eTemp)) {
                
                // is mortal?
                //  endGame(false)               
                
                if(eTemp.isTransposable()) {
                    
                    if (eTemp instanceof PacDot) {
                        score = score + 10;
                        stage.subtractNPacDots();
                    } else if (eTemp instanceof PowerPellet) {
                        // fazer tratamento do power pellet (fantasmas azuis, lentos...)
                        score = score + 50;
                        stage.subtractNPowerPellets();
                    } else if (eTemp instanceof Fruit) {
                    	Fruit f = (Fruit) eTemp;
                        score = score + f.getPoints();
                    }
                    
                    e.remove(eTemp);
                }
            }
        }
        
        lLolo.move();
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
