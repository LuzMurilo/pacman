package control;

import elements.Pacman;
import elements.Element;
import elements.Ghost;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameScreen extends javax.swing.JFrame implements KeyListener, Serializable {
    
    private GameStage stage;
    private int intStage = 1;

    private Pacman pacman;
    private ArrayList<Element> elemArray;
    private GameController controller = new GameController();

    private boolean isLoadAllowed = true;
    
    public GameScreen() {
        Drawing.setGameScreen(this);
        initComponents();
        fixLabelsPosition();

        this.addKeyListener(this);
        this.setSize(Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().left + getInsets().right,
                21 * Consts.CELL_SIZE + getInsets().top + getInsets().bottom);

        loadStage(intStage);
    }

    private void loadStage(int i) {
        elemArray = new ArrayList<Element>();
        addMainChars();

        stage = new GameStage();
        stage.loadWallsFromFile(elemArray, "walls" + i + ".txt");
        stage.loadDotsFromFile(elemArray, "dots" + i + ".txt");
        stage.loadPowerPelletsFromFile(elemArray, "powerpellets" + i + ".txt");
    }

    private void addMainChars() {
        pacman = new Pacman("pacman.png");
        pacman.setPosition(16, 9);
        this.addElement(pacman);

        // fix me - os fantasmas tem que ir pra um lugar mais dinamico quando forem se mover, fora da caixa central
        //      vc pode remover eles do array de elementos usando o index especifico deles qdo for usar cada um
        // EXEMPLO -    this.elemArray.remove(1);  // remove blinky do array
                   //   this.elemArray.remove(3);   // remove inky
        //  E DEPOIS... (new blinky) na nova posicao, indo atras do pacman
        Ghost blinky = new Ghost("blinky.png");
        blinky.setPosition(9, 8);
        this.addElement(blinky);

        Ghost clide = new Ghost("clide.png");
        clide.setPosition(10, 8);
        this.addElement(clide);

        Ghost inky = new Ghost("inky.png");
        inky.setPosition(9, 11);
        this.addElement(inky);

        Ghost pinky = new Ghost("pinky.png");
        pinky.setPosition(10, 11);
        this.addElement(pinky);
    }
    
    
    private void endGame(boolean pacmanWon) {
        // end of game, show something different if won or lost
        if (pacmanWon) {
            //...
            // wait for user to press a key maybe
        }
        exit(1);
    }
    
    public final void addElement(Element elem) {
        elemArray.add(elem);
    }

    public void removeElement(Element elem) {
        elemArray.remove(elem);
    }

    /**
     * @brief Method that comes from javax.swing.JFrame and is called at every loop.
     */
    @Override
    public void paint(Graphics gOld) {
        
        Graphics g = getBufferStrategy().getDrawGraphics();
        Graphics g2 = g.create(getInsets().right, getInsets().top, getWidth() - getInsets().left, getHeight() - getInsets().bottom);

        stage.drawBackground(g2);

        this.controller.drawAllElements(elemArray, g2);
        this.controller.processAllElements(elemArray, stage);
        
        if (stage.isCompleted()) {
            intStage = intStage + 1;
            if (intStage < 4) {
                loadStage(intStage);
            } else {
                // pacman won all stages
                endGame(true);
            }
        }
        // update labels
        labelStage.setText("STAGE: " + this.intStage);
        // implement here logic of label that counts life
        labelScore.setText("SCORE: " + this.controller.getScore());

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    public void go() {
        TimerTask task = new TimerTask() {

            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.DELAY);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pacman.setMovDirection(Pacman.MOVE_UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pacman.setMovDirection(Pacman.MOVE_DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pacman.setMovDirection(Pacman.MOVE_LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pacman.setMovDirection(Pacman.MOVE_RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            pacman.setMovDirection(Pacman.STOP);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            (new MemoryHelper()).saveGame(labelSaveGame, this);
        } else if (e.getKeyCode() == KeyEvent.VK_L) {
            if (isLoadAllowed) {
                (new MemoryHelper()).loadGame(labelLoadGame, this);
            }
        }

        // load is only allowed before a game starts
        isLoadAllowed = false;

        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLives = new java.awt.Label();
        labelStage = new java.awt.Label();
        labelScore = new java.awt.Label();
        labelSaveGame = new java.awt.Label();
        labelLoadGame = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0604 - Pacman");
        setBackground(new java.awt.Color(153, 153, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(20, 20));
        setResizable(false);

        labelLives.setBackground(new java.awt.Color(153, 153, 153));
        labelLives.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelLives.setForeground(new java.awt.Color(0, 0, 1));
        labelLives.setText("LIVES: 5");

        labelStage.setBackground(new java.awt.Color(153, 153, 153));
        labelStage.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelStage.setForeground(new java.awt.Color(0, 0, 0));
        labelStage.setText("STAGE: 1");

        labelScore.setBackground(new java.awt.Color(153, 153, 153));
        labelScore.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelScore.setForeground(new java.awt.Color(0, 0, 0));
        labelScore.setText("SCORE: 10000000");

        labelSaveGame.setBackground(new java.awt.Color(0, 0, 0));
        labelSaveGame.setFont(new java.awt.Font("Lucida Sans Typewriter", 1, 18)); // NOI18N
        labelSaveGame.setForeground(new java.awt.Color(255, 255, 0));
        labelSaveGame.setText("SAVE GAME");

        labelLoadGame.setBackground(new java.awt.Color(0, 0, 0));
        labelLoadGame.setFont(new java.awt.Font("Lucida Sans Typewriter", 1, 18)); // NOI18N
        labelLoadGame.setForeground(new java.awt.Color(255, 255, 0));
        labelLoadGame.setText("LOAD GAME");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelStage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(labelLives, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(labelScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelSaveGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelLoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSaveGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 446, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelStage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLives, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelScore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label labelLives;
    private java.awt.Label labelLoadGame;
    private java.awt.Label labelSaveGame;
    private java.awt.Label labelScore;
    private java.awt.Label labelStage;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    public Pacman getPacman() {
        return pacman;
    }

    public ArrayList<Element> getElemArray() {
        return elemArray;
    }

    public GameController getController() {
        return controller;
    }
    
    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public void setElemArray(ArrayList<Element> elemArray) {
        this.elemArray = elemArray;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }
    
    public int getIntStage() {
        return intStage;
    }

    public void setIntStage(int intStage) {
        this.intStage = intStage;
    }

    public void setIsLoadAllowed(boolean isLoadAllowed) {
        this.isLoadAllowed = isLoadAllowed;
    }
    
    /**
     * @brief Cosmethic method
     */
    private void fixLabelsPosition() {
        getContentPane().setLayout(null);
        labelStage.setLocation(40, 600);
        labelLives.setLocation(250, 600);
        labelScore.setLocation(450, 600);
        labelSaveGame.setLocation(0, 0);
        labelSaveGame.setVisible(false);
        labelLoadGame.setLocation(0, 0);
        labelLoadGame.setVisible(false);
    }
}
