package game.dev;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

class Board extends JPanel {
    Timer timer;
    BufferedImage backgroundImage;
    bucket bucket;
    egg eggs[] = new egg[3];
    hen hens[] = new hen[3];
    int score = 0;

    public Board() {
        setSize(1500, 950);
        loadBackgroundImage();
        bucket = new bucket();
        loadEggs();
        loadHens();
        gameloop();
        BindEvents();
        setFocusable(true);
    }

    private void BindEvents() {
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    bucket.speed = 10;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    bucket.speed1 = 10;
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
                    bucket.speed = 0;
                    bucket.speed1 = 0;
                }
            }
        });
    }

    private void incrementscore() {
        for (egg egg : eggs) {
            if (egg.visible && egg.isCollide(bucket)) {
                System.out.println("Collision detected. Incrementing score.");
                score++;
                System.out.println("Score: " + score);
                egg.visible = false; // Mark the egg as not visible
                System.out.println("Egg visibility: " + egg.visible);
            }
        }
    }


    private boolean isCollide(egg egg) {
        int xDistance = Math.abs(bucket.x - egg.x);
        int yDistance = Math.abs(bucket.y - egg.y);
        int maxH = Math.max(bucket.h, egg.h);
        int maxW = Math.max(bucket.w, egg.w);
        return xDistance <= maxW && yDistance <= maxH;
    }

    private void loadEggs() {
        int x = 400;
        int gap = 250;
        int speed = 5;
        for (int i = 0; i < eggs.length; i++) {
            eggs[i] = new egg(x, speed);
            x = x + gap;
            speed = speed + 5;
        }
    }

    private void loadHens() {
        int x = 400;
        int gap = 250;
        for (int i = 0; i < hens.length; i++) {
            hens[i] = new hen(x);
            x = x + gap;
        }
    }

    private void gameloop() {
        timer = new Timer(50, (e) -> {
            repaint();
        });
        timer.start();
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(this.getClass().getResource("background.jpg"));
        } catch (IOException e) {
            System.out.println("Background image not found");
            System.exit(1);
            e.printStackTrace();
        }
    }

    private void printHens(Graphics pen) {
        for (hen hen : hens) {
            hen.draw(pen);
        }
    }

    private void printEggs(Graphics pen) {
        for (egg egg : eggs) {
            egg.draw(pen);
            egg.move();
        }
    }

    public void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        pen.drawImage(backgroundImage, 0, 0, 1500, 950, null);
        bucket.draw(pen);
        printEggs(pen);
        bucket.move();
        printHens(pen);
        incrementscore();
        pen.setFont(new Font("Times", Font.BOLD, 30));
        pen.setColor(Color.RED);
        pen.drawString(String.valueOf(score),1300,100);
        pen.drawString("Your score -", 1120, 100);
    }
}


