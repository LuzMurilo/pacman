package elements;

import utils.Drawing;

import java.awt.*;
import java.io.Serializable;

public class Ghost extends Element implements Serializable {
    public Ghost(String imageName) {
        super(imageName);
        this.isTransposable = false;
        this.isMortal = true;
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }
}
