/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import utils.Drawing;


public class Fruit extends Element {
	public int points;

    public Fruit(String imageName) {
        super(imageName);
        this.isTransposable = true;
        if(imageName == "cherry.png") {
        	points = 100;
        } else if(imageName == "strawberry.png") {
        	points = 300;
        }
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }
    
    public int getPoints() {
    	return points;
    }
}
