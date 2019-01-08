package Connect4_GUI;

import GUI_simple.MyFirstJFrame;

public class Show_the_Connect4_board {
	
	public static void main (String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			
			@Override
			public void run() {
				connect_4_GUI gui = new connect_4_GUI();
			}
			
		});
	}

}
