package elements;

import java.awt.*;
import java.io.Serializable;

public class Ghost extends Element implements Serializable {
    protected Ghost(String imageName) {
        super(imageName);
        this.isTransposable = false;
        this.isMortal = true;
    }

    @Override
    public void autoDraw(Graphics g) {

    }
}
