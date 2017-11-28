/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Label;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MemoryHelper {
    
    public void saveGame(Label label, GameScreen screen) {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("savedgame.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            
            label.setVisible(true);
            out.writeObject(screen);
            out.close();
            label.setVisible(false);
            
            
        } catch (Exception ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileOut.close();
            } catch (IOException ex) {
                Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loadGame(Label label, GameScreen screen) {
        ObjectInputStream in = null;
        try {
            GameScreen loadedGame = null;
            FileInputStream fileIn = new FileInputStream("savedgame.txt");
            in = new ObjectInputStream(fileIn);
            
            label.setVisible(true);
            
            loadedGame = (GameScreen) in.readObject();
            screen.setPacman(loadedGame.getPacman());
            screen.setElemArray(loadedGame.getElemArray());
            screen.setController(loadedGame.getController());
            screen.setIntStage(loadedGame.getIntStage());
            // fix me
            // set no. of lives when implemented
            screen.addKeyListener(loadedGame);
            
            in.close();
            fileIn.close();
            
            label.setVisible(false);
            
        } catch (IOException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
