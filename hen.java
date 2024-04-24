package game.dev;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class hen {
	int x;
	int y;
	int w;
	int h;
	ImageIcon image;
	public hen(int x) {
		this.x=x;
		y=30;
		w=150;
		h=150;
		image=new ImageIcon(hen.class.getResource("hen.gif"));
		
	}
		
	public void draw(Graphics pen) {
		pen.drawImage(image.getImage(),x,y,w,h,null);
		
		
	}

}


	
