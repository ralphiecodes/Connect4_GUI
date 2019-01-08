package Connect4_GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class connect_4_GUI extends JFrame{
	
	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 48);
	private JPanel jpMain, scoreBoard;
	private JLabel turnDis;
	private JLabel Chat;
	Connect4Board jpBoard;
	
	private JButton[] topBttns;
	private String bttnName[] = {"CLICK"};

	private Player currentPlayer;
	private Player Player1;
	private Player Player2;

	
	public connect_4_GUI() {

		
		Player1 = new Player("Megaman", "M");
		Player2 = new Player("Bass" , "B");
		currentPlayer = Player1;
		
		jpMain = new JPanel();
		
		jpMain.setLayout(new BorderLayout());
		
		
		jpBoard = new Connect4Board();
		
		scoreBoard = new JPanel();
		turnDis = new JLabel("Megaman Score= " + Player1.getNumWins()+ "     " + "Bass Score= " + Player2.getNumWins() +"     " + "Total Games Played= " + 0);
		Chat = new JLabel("Hello there! Lets play Connect 4!");
		scoreBoard.add(turnDis);
		scoreBoard.add(Chat);
		
		add(scoreBoard, BorderLayout.NORTH);
		scoreBoard.setBackground(Color.YELLOW);
		scoreBoard.setLayout(new GridLayout(5,5));
		setVisible(true);
		
		jpMain.add(BorderLayout.CENTER, jpBoard);
		add(jpMain);
		setSize (1000,1000);
		setVisible (true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


		
	

	private class Connect4Board extends JPanel implements Connect_4_Interface, Connect_4_Player_Interface, ActionListener{
		private JLabel [][] board;
		private  final int ROWS = 6;
		private final int COLS = 7;
		private int [] tracker = {5,5,5,5,5,5,5};
		
		
			
		
		
		public Connect4Board() {
			
			topBttns = new JButton[7]; 
			for (int j= 0; j<topBttns.length; j++) {
				topBttns[j] = new JButton(bttnName[0]);
				topBttns[j].addActionListener(this);
				add(topBttns[j]);
			}
			
			setLayout (new GridLayout(ROWS+1, COLS));
			board = new JLabel [ROWS][COLS];
			displayBoard();
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
		 JButton btnClicked = (JButton) e.getSource();
		
		 btnClicked.setEnabled(true);
		 if (btnClicked.equals(topBttns[0])) {
			 Chat.setText("It's your turn: " + currentPlayer.getPlayerName());
		 }
		 else if(btnClicked.equals(topBttns[1])) {
			 Chat.setText("It's your turn: " + currentPlayer.getPlayerName());
		 }
		 else if(btnClicked.equals(topBttns[2])) {
			 Chat.setText("It's your turn: " + currentPlayer.getPlayerName());
		 }
		 else if(btnClicked.equals(topBttns[3])) {
			 Chat.setText("It's your turn: " + currentPlayer.getPlayerName());
		 }
		 else if(btnClicked.equals(topBttns[4])) {
			 Chat.setText("It's your turn: " + currentPlayer.getPlayerName());
		 }
		 else if(btnClicked.equals(topBttns[5])) {
			 Chat.setText("It's your turn: " + currentPlayer.getPlayerName());
		 }
		 else if(btnClicked.equals(topBttns[6])) {
			 Chat.setText("It's your turn: " + currentPlayer.getPlayerName());
		 }
		
		 
		 
		 
		 for(int j= 0; j< 7; j++) {
			 if ( topBttns[j]==e.getSource()) {
				 int rowToCol =tracker[j];
				 board[rowToCol][j].setText(currentPlayer.getSymbol());
				 tracker[j]--;
				 
				 if (tracker[j]<0) {
					 topBttns[j].setEnabled(false);
					 
				 }
			 } 
		 }
		 
		 int draw=0;
		 if (isWinner()) {
			 JOptionPane.showMessageDialog(null, "WINNER = " + currentPlayer.getPlayerName());
			 currentPlayer.addNumWins();
			 clearBoard();
			 
		 }
		 else if (isFull()){
			 draw++;
			 JOptionPane.showMessageDialog(null, "Is full! draw!!");	
			 clearBoard();
		 }
		 int totalGamesPlayed = Player1.getNumGames() + Player2.getNumGames()+ draw;
		 turnDis.setText("Megaman Score= " + Player1.getNumWins()+ "     " + "Bass Score= " + Player2.getNumWins() +"     " + "Total Games Played= " + totalGamesPlayed);
		 
			takeTurn();
		}
		@Override
		public boolean isWinner() {
			if(isWinnerInRow() || isWinnerInCol() || isWinnerInMainDiag() || isWinnerInSecDiag()) {
				return true;
			}
			return false;
		}
		
		public boolean isWinnerInRow() {
			String symbol = currentPlayer.getSymbol();
			for (int row = 0; row< board.length; row++) {
				int numMatchesInRow = 0 ; // this will reset on the next row
				for( int col = 0; col< board[row].length; col++) {
					if (board [row][col].getText().trim().equalsIgnoreCase(symbol)) {
						numMatchesInRow++;
						if (numMatchesInRow ==4) {
							return true;
						} 
					} 
					else {
						numMatchesInRow=0;
					}
				}
				
			}
			return false;
		} //This is where Winner in row ends
		
		public boolean isWinnerInCol() {
			String symbol = currentPlayer.getSymbol();
			for (int col =0; col<7; col++) {
				int numMatchesInCol = 0 ;
				for (int row = 0; row <6; row++) {
					if (board[row][col].getText().trim().equalsIgnoreCase(symbol)) { //this will scan for symbol in the grid
						numMatchesInCol++;
						if (numMatchesInCol ==4) {
							return true;
					}
				}
					else {
						numMatchesInCol = 0;
					}
				}
			}
			return false;
		} //This is where the Win in Col ends
		
		public boolean isWinnerInMainDiag() {
			String symbol = currentPlayer.getSymbol();
			int matchesInMainDiag = 0;
			int row = board.length-1;
			int col = 0;
			for (int i = board.length-1; i >=0; i--) {
				row = i;
				col = 0;
				matchesInMainDiag = 0;
				while(row<board.length && col <board[row].length) {
					if( board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
						matchesInMainDiag++;		
					}
					else {
						matchesInMainDiag = 0;
					}
					if(matchesInMainDiag ==4) {
						return true;
					}
					col++;
					row--;
					if (row<0) {
						row = 0;
						break;
					}
				}
			}
			
			for (int j = 0; j<board[row].length; j++) {
				col = j;
				row = board.length-1;
				matchesInMainDiag = 0;
				while( row<board.length && col <board[row].length) {
					if(board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
						matchesInMainDiag++;
					}
					else {
						matchesInMainDiag = 0;
					}
					if (matchesInMainDiag == 4) {
						return true;
					}
					row--;
					col++;
					if(row<0) {
						row =0;
						break;
					}
				}
			}
			return false;
		} //this is where the matches in the main Diag ends
		
		
		public boolean isWinnerInSecDiag() {
			String symbol = currentPlayer.getSymbol();
			int matchesInSecDiag = 0;
			int row = 0;
			int col =0;
			for(int showRow = 0; showRow < board.length; showRow++) {
				row = showRow;
				col = 0;
				matchesInSecDiag = 0;
				while(row<board.length && col < board[row].length) {
					if(board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
						matchesInSecDiag++;
					}else {
						matchesInSecDiag=0;
					}
					if(matchesInSecDiag == 4) {
						return true;
					}
					row++;
					col++;
				}
				
			}
			row = 0;
			for(int showCol = 0; showCol < board[row].length; showCol++) {
				row = 0;
				col = showCol;
				matchesInSecDiag = 0;
				while(row< board.length && col < board[row].length) {
					if(board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
						matchesInSecDiag++;
					}else {
						matchesInSecDiag =0;
					}
					if(matchesInSecDiag == 4) {
						return true;
					}
					row++;
					col++;
					if(row>5) {
						row =5;
						break;
					}
				}
			}
			return false;
		}    //This is where winner in Second Diag will win at
		
		
		@Override
		public void takeTurn() {
			if (currentPlayer.equals(Player1)) {
				currentPlayer = Player2;
			}
			else {
				currentPlayer = Player1;
			}
		}
		@Override
		public void displayBoard() {
			
			Font bigFont = new Font (Font.SANS_SERIF, Font.BOLD, 40);
			Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD,20);
			Border coolBorder = BorderFactory.createLineBorder(Color.RED);
			
		
		
			    for(int col = 0; col<topBttns.length; col++) {
			    	topBttns[col].setOpaque(true);
			    	topBttns[col].setBackground(Color.RED);
			    	topBttns[col].setFont(buttonFont);
			    	add(topBttns[col]);
			    }
				
				for(int row= 0; row<board.length; row++) {
					for (int col= 0; col<board[row].length; col++) {
						board[row][col]= new JLabel();
						board[row][col].setOpaque(true);
						board[row][col].setBackground(Color.BLUE);
						board[row][col].setFont(bigFont);
						board[row][col].setBorder(coolBorder);
						board[row][col].setHorizontalAlignment((int) CENTER_ALIGNMENT);
					    add(board[row][col]);
						
					}
					
				}
				
				
				
			
			
		}
		@Override
		public void clearBoard() {
			for(int row = 0; row<board.length; row++) {
				for (int col = 0 ; col<board[row].length; col++) {
					board[row][col].setText("");
					board[row][col].setEnabled(true);
					
				}
			}
			for (int i =0; i<7; i++) {
				tracker[i] = 5;
				topBttns[i].setEnabled(true);
			}
			
		}
		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isFull() {
		for (int row = 0; row<board.length; row++) {
			for (int col = 0; col<board[row].length; col++) {
				String cellContent = board[row][col].getText().trim();
				if(cellContent.isEmpty()) {
					return false;
				}
			}
			
		}
			return true;
		}
		
		
	}
	
	

}
