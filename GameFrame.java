package game.dev;

import javax.swing.JFrame;
public class GameFrame extends JFrame {
	public GameFrame() {
		Board board=new Board();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500,950);
		add(board);
		setVisible(true);
		setResizable(false);
		setTitle("Crash Cars");
		setLocationRelativeTo(null);
	}
	public static void main(String[]args) {
		new GameFrame();
	}
}
		
		
	
		
		
		
 

