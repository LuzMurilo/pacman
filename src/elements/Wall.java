package elements;

import utils.Drawing;
import java.awt.*;
import java.io.Serializable;

public class Wall extends Element implements Serializable {
    public Wall(String imageName) {
        super(imageName);
        this.isTransposable = false;
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }

}
