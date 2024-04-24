package game.dev;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class egg {
    public int x;
    public int y;
    public int w;
    public int h;
    BufferedImage Image;
    int speed;
    boolean visible = true; // Flag to track visibility

    public egg(int x, int speed) {
        this.x = x;
        this.speed = speed;
        w = 50;
        h = 50;
        y = 0;
        try {
            Image = ImageIO.read(this.getClass().getResource("egg.png"));
        } catch (IOException e) {
            System.out.println("egg image not found");
            System.exit(1);
            e.printStackTrace();
        }
    }

    public void draw(Graphics pen) {
        if (visible) {
            pen.drawImage(Image, x, y, w, h, null);
        }
    }

    public void move() {
        if (y > 900) {
            y = 0;
            visible = true; // Reset visibility when egg moves out of range
        }
        y = y + speed;
    }

    // Method to check collision and set visibility
    public void checkCollision(bucket bucket) {
        if (visible && isCollide(bucket)) {
            visible = false; // Mark the egg as not visible
        }
    }

    boolean isCollide(bucket bucket) {
        int xDistance = Math.abs(bucket.x - x);
        int yDistance = Math.abs(bucket.y - y);
        int maxH = Math.max(bucket.h, h);
        int maxW = Math.max(bucket.w, w);
        return xDistance <= maxW - 109 && yDistance <= maxH - 109;
    }
}



