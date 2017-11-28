/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import elements.Element;
import elements.PacDot;
import elements.Wall;
import elements.PowerPellet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Consts;


public class GameStage implements Serializable {

    private int nPacDots = 0;
    private int nPowerPellets = 0;
    
    private boolean pacDotsEnded = true;
    private boolean powerPelletsEnded = true;
    
    public void subtractNPacDots() {
        nPacDots--;
        
        if (nPacDots == 0) {
            pacDotsEnded = true;
        }
    }
    
    public void subtractNPowerPellets() {
        nPowerPellets--;
        
        if (nPowerPellets == 0) {
            powerPelletsEnded = true;
        }
    }
    
    public boolean isCompleted() {
        return pacDotsEnded && powerPelletsEnded;
    }
    
    public GameStage() {
    }
    
    public void loadPowerPelletsFromFile(ArrayList<Element> elemArray, String fileName) {
        try {
            Scanner in = new Scanner(new FileReader((new java.io.File(".").getCanonicalPath()) + Consts.SCENARIO_PATH + fileName));

            while (in.hasNext()) {
                String nextLine = in.nextLine();

                String[] powerPelletCoordinates = nextLine.split(",");

                try {
                    int x = Integer.parseInt(powerPelletCoordinates[0]);
                    int y = Integer.parseInt(powerPelletCoordinates[1]);

                    PowerPellet powerPellet = new PowerPellet("powerpellet.png");
                    powerPellet.setPosition(x, y);
                    elemArray.add(powerPellet);
                    nPowerPellets++;
                    powerPelletsEnded = false;

                } catch (NumberFormatException ex) {
                    // line on txt file used for comment
                }
            }

            in.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {

        }
    }
    
    public void loadDotsFromFile(ArrayList<Element> elemArray, String fileName) {
        try {
            Scanner in = new Scanner(new FileReader((new java.io.File(".").getCanonicalPath()) + Consts.SCENARIO_PATH + fileName));

            while (in.hasNext()) {
                String nextLine = in.nextLine();

                String[] pacdotCoordinates = nextLine.split(",");

                try {
                    int x = Integer.parseInt(pacdotCoordinates[0]);
                    int y = Integer.parseInt(pacdotCoordinates[1]);

                    PacDot pacdot = new PacDot("pacdot.png");
                    pacdot.setPosition(x, y);
                    elemArray.add(pacdot);
                    nPacDots++;
                    pacDotsEnded = false;

                } catch (NumberFormatException ex) {
                    // line on txt file used for comment
                }
            }

            in.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {

        }
    }
    
    public void loadWallsFromFile(ArrayList<Element> elemArray, String fileName) {
        try {
            Scanner in = new Scanner(new FileReader((new java.io.File(".").getCanonicalPath()) + Consts.SCENARIO_PATH + fileName));

            while (in.hasNext()) {
                String nextLine = in.nextLine();

                String[] wallCoordinates = nextLine.split(",");

                try {
                    int x = Integer.parseInt(wallCoordinates[0]);
                    int y = Integer.parseInt(wallCoordinates[1]);

                    Wall wall = new Wall("wall.png");
                    wall.setPosition(x, y);
                    elemArray.add(wall);

                } catch (NumberFormatException ex) {
                    // line on txt file used for comment
                }
            }

            in.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {

        }
    }
    
    public void drawBackground(Graphics g2) {
        for (int i = 0; i < Consts.NUM_CELLS; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.IMG_PATH + "background.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIZE, i * Consts.CELL_SIZE, Consts.CELL_SIZE, Consts.CELL_SIZE, null);

                } catch (IOException ex) {
                    Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
