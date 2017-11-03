package elements;

import java.awt.*;
import java.io.Serializable;

/* screenshots from https://gamefabrique.com/screenshots/pac-man/ */

public class BackgroundElement extends Element implements Serializable {
    public BackgroundElement(String imageName) {
        super(imageName);
    }

    @Override
    public void autoDraw(Graphics g) {
    }

    String s = "Key - Value";
    String[] arr = s.split("-");
    String a = arr[0].trim();
    String b = arr[1].trim();

}
