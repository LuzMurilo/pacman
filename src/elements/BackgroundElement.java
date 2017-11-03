package elements;

import java.awt.*;
import java.io.Serializable;

public class BackgroundElement extends Element implements Serializable {
    protected BackgroundElement(String imageName) {
        super(imageName);
    }

    @Override
    public void autoDraw(Graphics g) {

    }
}
