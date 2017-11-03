package elements;

import java.awt.*;
import java.io.Serializable;

public class Food extends Element implements Serializable {
    protected Food(String imageName) {
        super(imageName);
    }

    @Override
    public void autoDraw(Graphics g) {

    }
}
