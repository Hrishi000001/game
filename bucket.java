package game.dev;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class bucket {
	public int x;
	public int y;
	public int w;
	public int h;
	BufferedImage image1;
	public int speed;
	public int speed1;
	public bucket() {
		w=150;
		h=150;
		x=100;
		y=600;
		try {
			
			image1=ImageIO.read(this.getClass().getResource("bucket.jpeg"));
		} catch (IOException e) {
			System.out.println("bucket image not found");
			System.exit(1);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void move() {
		x= x+speed;
		x=x-speed1;
	}
	public void draw(Graphics pen) {
		pen.drawImage(image1,x,y,w,h,null);
		
		
	}

}
