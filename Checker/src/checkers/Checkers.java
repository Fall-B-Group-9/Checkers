package checkers;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Checkers extends JPanel implements ActionListener, ItemListener, MouseMotionListener, MouseListener {

	Graphics g;

	JTextArea msg = new JTextArea("Start a new game... Yellow is to move first..."); // Eli updated this 12/1/16 as a fix for B02
	ImageIcon redN = new ImageIcon(new ImageIcon(getClass().getResource("/images/red_normal.jpg")).getImage());// red_normal.jpg
	ImageIcon yellowN = new ImageIcon(new ImageIcon(getClass().getResource("/images/yellow_normal.jpg")).getImage());// yellow_normal.jpg
	ImageIcon redK = new ImageIcon(new ImageIcon(getClass().getResource("/images/red_king.jpg")).getImage());// red_king.jpg
	ImageIcon yellowK = new ImageIcon(new ImageIcon(getClass().getResource("/images/yellow_king.jpg")).getImage());// yellow_king.jpg
	ImageIcon hlp = new ImageIcon(new ImageIcon(getClass().getResource("/images/help.jpg")).getImage());// help.jpg
	ImageIcon snp = new ImageIcon(new ImageIcon(getClass().getResource("/images/sound.jpg")).getImage());// sound.jpg
	ImageIcon mup = new ImageIcon(new ImageIcon(getClass().getResource("/images/mute.jpg")).getImage());// mute.jpg

	JButton nwB = new JButton("New Game");
	JButton unB = new JButton("Undo"); // Updated by Kendra for E06
	JButton hlpB = new JButton(hlp);
	JButton snB = new JButton(snp);

	ButtonGroup players = new ButtonGroup();
	JRadioButton p1 = new JRadioButton("1-Player", true);
	JRadioButton p2 = new JRadioButton("2-Player", false);

	ButtonGroup colors = new ButtonGroup();
	JRadioButton c1 = new JRadioButton("Red", true); // Updated By Greg Schoberth To Resolve B01
	JRadioButton c2 = new JRadioButton("Yellow", false); // Updated By Greg Schoberth To Resolve B01

	Help hp = new Help();

	// Edited by Kendra to add Player 1 and Player 2 options
	JLabel mode = new JLabel("Mode");
	JLabel col = new JLabel("Colour");
	JLabel diff = new JLabel("Difficulty Level");
	JLabel rp = new JLabel();
	JLabel rpt = new JLabel("Your Piece");
	JLabel ypt = new JLabel("Opponent's Piece");
	JLabel yp = new JLabel();
	JLabel rk = new JLabel();
	JLabel rkt = new JLabel("Your King");
	JLabel ykt = new JLabel("Opponent's King");
	JLabel yk = new JLabel();
	JLabel p1n = new JLabel("Player 1");
	JLabel p1k = new JLabel("Player 1 King");
	JLabel p2n = new JLabel("Player 2");
	JLabel p2k = new JLabel("Player 2 King");

	JComboBox level = new JComboBox();

	String selectedColor;
	int selectedMode;
	int difficulty;

	public static final int redNormal = 1;
	public static final int yellowNormal = 2;
	static final int redKing = 3;
	static final int yellowKing = 4;
	static final int empty = 0;

	int currType;
	boolean movable;

	int[][] board = new int[8][8];

	int[][] preBoard1 = new int[8][8]; // for undo
	int preToMove1;
	int[][] preBoard2 = new int[8][8];
	int preToMove2;
	int[][] preBoard3 = new int[8][8];
	int preToMove3;

	int startX, startY, endX, endY;
	boolean incomplete = false;
	boolean highlight = false;

	int toMove = redNormal;
	int loser = empty;

	static boolean silent = false;

	int undoCount;

	int won = 0;

	Point winPoint;

	static Point popUp;

	Checkers() {
		setupGUI();
	}

	// Updated By Greg Schoberth To Resolve E02
	private void setupGUI() {
		setLayout(null);

		nwB.setFocusPainted(false);
		unB.setFocusPainted(false);
		c1.setFocusPainted(false);
		c2.setFocusPainted(false);
		p1.setFocusPainted(false);
		p2.setFocusPainted(false);
		hlpB.setFocusPainted(false);
		snB.setFocusPainted(false);

		diff.setFont(new Font("SansSerif", Font.PLAIN, 11));
		col.setFont(new Font("SansSerif", Font.PLAIN, 11));
		mode.setFont(new Font("SansSerif", Font.PLAIN, 11));
		c1.setFont(new Font("SansSerif", Font.PLAIN, 11));
		c2.setFont(new Font("SansSerif", Font.PLAIN, 11));
		p1.setFont(new Font("SansSerif", Font.PLAIN, 11));
		p2.setFont(new Font("SansSerif", Font.PLAIN, 11));
		nwB.setFont(new Font("SansSerif", Font.BOLD, 11));
		unB.setFont(new Font("SansSerif", Font.BOLD, 11));
		hlpB.setFont(new Font("SansSerif", Font.PLAIN, 11));
		snB.setFont(new Font("SansSerif", Font.PLAIN, 11));
		msg.setFont(new Font("SansSerif", Font.PLAIN, 11));

		nwB.setCursor(new Cursor(Cursor.HAND_CURSOR));
		unB.setCursor(new Cursor(Cursor.HAND_CURSOR));
		hlpB.setCursor(new Cursor(Cursor.HAND_CURSOR));
		snB.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nwB.addActionListener(this);
		unB.addActionListener(this);
		hlpB.addActionListener(this);
		snB.addActionListener(this);
		nwB.setBounds(405, 70, 95, 25);// 297
		this.add(nwB);
		unB.setBounds(405, 100, 95, 25);
		this.add(unB); // Updated by Kendra for E06
		hlpB.setBounds(415, 10, 25, 25);
		this.add(hlpB);
		snB.setBounds(460, 10, 25, 25);
		this.add(snB);

		mode.setBounds(420, 260, 80, 25);
		this.add(mode);
		p1.addActionListener(this);
		p2.addActionListener(this);
		p1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		p2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		players.add(p1);
		players.add(p2);
		p1.setBounds(415, 290, 80, 25);
		p2.setBounds(415, 318, 80, 25);
		this.add(p1);
		this.add(p2);

		col.setBounds(420, 360, 80, 25);
		this.add(col);
		c1.addActionListener(this);
		c2.addActionListener(this);
		c1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		c2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		colors.add(c1);
		colors.add(c2);
		c1.setBounds(420, 380, 80, 25);
		c2.setBounds(420, 408, 80, 25);
		this.add(c1);
		this.add(c2);

		level.setCursor(new Cursor(Cursor.HAND_CURSOR));
		level.addItemListener(this);
		level.addItem("Easy");
		level.addItem("Fairly Easy");
		level.addItem("Moderate");
		level.addItem("Bit Difficult");
		level.addItem("Tough");
		level.setSelectedIndex(2);
		level.setBounds(415, 200, 80, 25);
		this.add(level);

		diff.setCursor(new Cursor(Cursor.HAND_CURSOR));
		diff.setBounds(415, 170, 100, 25);
		this.add(diff);

		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		msg.setBounds(0, 405, 400, 20);
		msg.setEnabled(false);
		this.add(msg);

		// Updated by Kendra to add Player 1 and Player 2 Options
		// Changed Spacing and order of display
		// Fixes B01
		rp.setBounds(10, 440, 50, 50);
		rp.setIcon(redN);
		this.add(rp);
		rpt.setBounds(60, 450, 60, 20);
		this.add(rpt);
		p1n.setBounds(60, 450, 60, 20);
		this.add(p1n);
		p1n.setVisible(false);

		yp.setBounds(110, 440, 50, 50);
		yp.setIcon(yellowN);
		this.add(yp);
		ypt.setBounds(160, 450, 90, 20);
		this.add(ypt);
		p2n.setBounds(160, 450, 90, 20);
		this.add(p2n);
		p2n.setVisible(false);

		rk.setBounds(250, 440, 50, 50);
		rk.setIcon(redK);
		this.add(rk);
		rkt.setBounds(305, 450, 60, 20);
		this.add(rkt);
		p1k.setBounds(260, 450, 100, 20);
		this.add(p1k);
		p1k.setVisible(false);

		yk.setBounds(365, 440, 50, 50);
		yk.setIcon(yellowK);
		this.add(yk);
		ykt.setBounds(420, 450, 100, 20);
		this.add(ykt);
		p2k.setBounds(410, 450, 100, 20);
		this.add(p2k);
		p2k.setVisible(false);

	}

	// Updated by Eli 12/1/16 to resolve B12
	// Draw each row, draw all the squares
	// The bottom right square should be white
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				int x = 50 * col;
				int y = 50 * row;
				if ((row % 2) == (col % 2))
					g.setColor(new Color(255, 255, 255)); // white color
				else
					g.setColor(new Color(0, 0, 0)); // black color
				g.fillRect(x, y, 50, 50);
			}
		}

		g.drawLine(0, 400, 400, 400);
		g.drawLine(400, 0, 400, 400);
		drawCheckers();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("1-Player")) {
			new PlaySound("src//sounds//option.wav").start();
			col.setEnabled(true);
			col.setVisible(true);
			diff.setEnabled(true);
			diff.setVisible(true);
			c1.setEnabled(true);
			c1.setVisible(true);
			c2.setEnabled(true);
			c2.setVisible(true);
			level.setEnabled(true);
			level.setVisible(true);
		}
		if (e.getActionCommand().equalsIgnoreCase("2-Player")) {
			new PlaySound("src//sounds//option.wav").start();
			col.setEnabled(false);
			col.setVisible(false);
			diff.setEnabled(false);
			diff.setVisible(false);
			c1.setEnabled(false);
			c1.setVisible(false);
			c2.setEnabled(false);
			c2.setVisible(false);
			level.setEnabled(false);
			level.setVisible(false);
			c2.setSelected(true);
			
			// Updated so undo is not an option for two player
			unB.setVisible(false);
			
			// Updated by Kendra to add Player 1 and Player 2 Options
			// Changed Spacing and order of display
			// Fixes B01
			rpt.setVisible(false);
			ypt.setVisible(false);
			rkt.setVisible(false);
			ykt.setVisible(false);
			rp.setBounds(110, 440, 50, 50);
			yp.setBounds(10, 440, 50, 50);
			rk.setBounds(350, 440, 50, 50);
			yk.setBounds(210, 440, 50, 50);
			p1n.setVisible(true);
			p2n.setVisible(true);
			p1k.setVisible(true);
			p2k.setVisible(true);

		}

		// Updated By Greg Schoberth To Resolve B01
		if (e.getActionCommand().equalsIgnoreCase("red")) {
			new PlaySound("src//sounds//option.wav").start();

			/*bk.setIcon(yellowK);
			bp.setIcon(yellowN);

			rk.setIcon(redK);
			rp.setIcon(redN);*/
		}

		// Updated By Greg Schoberth To Resolve B01
		if (e.getActionCommand().equalsIgnoreCase("yellow")) {
			new PlaySound("src//sounds//option.wav").start();

			/*rk.setIcon(yellowK);
			rp.setIcon(yellowN);

			bk.setIcon(redK);
			bp.setIcon(redN);*/

		}
		if (e.getActionCommand().equalsIgnoreCase("New Game")) {
			new PlaySound("src//sounds//button.wav").start();
			newGame();
		}
		if (e.getActionCommand().equalsIgnoreCase("Undo") && undoCount > 3) {
			new PlaySound("src//sounds//button.wav").start();
			undo();
		}
		if (e.getSource() == hlpB) {
			new PlaySound("src//sounds//button.wav").start();
			hp.setVisible(true);
		}
		if (e.getSource() == snB) {
			if (silent) {
				snB.setIcon(snp);
				silent = false;
				new PlaySound("src//sounds//button.wav").start();
			} else {
				snB.setIcon(mup);
				silent = true;
			}
		}
	}

	public void newGame() { // creates a new game

		// Yellow takes the first move in both modes
		// If someone wants to move secondly, red has to be selected
		// Yellow is always at the bottom of the board

		selectedColor = c1.isSelected() ? "red" : "yellow";
		selectedMode = p1.isSelected() ? 1 : 2;
		difficulty = level.getSelectedIndex();

		unB.setEnabled(false);

		won = 0;

		undoCount = 0;

		highlight = false;
		incomplete = false;

		loser = empty;

		// Updated By Greg Schoberth To Resolve B01
		if (selectedColor == "red" && selectedMode == 1){

			yk.setIcon(yellowK);
			yp.setIcon(yellowN);

			rk.setIcon(redK);
			rp.setIcon(redN);
		}

		// Updated By Greg Schoberth To Resolve B01
		else if(selectedColor == "yellow" && selectedMode == 1){

			rk.setIcon(yellowK);
			rp.setIcon(yellowN);

			yk.setIcon(redK);
			yp.setIcon(redN);

		}

		
		for (int i = 0; i < 8; i++) // applies values to the board
		{
			for (int j = 0; j < 8; j++)
				board[i][j] = empty;

			for (int j = 0; j < 3; j++)
				if (isPossibleSquare(i, j))
					board[i][j] = redNormal;

			for (int j = 5; j < 8; j++)
				if (isPossibleSquare(i, j))
					board[i][j] = yellowNormal;
		}

		toMove = yellowNormal;

		for (int i = 0; i < 8; i++) {
			System.arraycopy(board[i], 0, preBoard1[i], 0, 8); // for undo
			System.arraycopy(preBoard1[i], 0, preBoard2[i], 0, 8);
			System.arraycopy(preBoard2[i], 0, preBoard3[i], 0, 8);
			preToMove3 = preToMove2 = preToMove1 = toMove;
		}

		if (selectedMode == 1 && selectedColor.equalsIgnoreCase("yellow")) {
			this.toMove = redNormal;
			update(getGraphics()); // Updated to resolve an issue with E03
			drawCheckers(); // Updated to resolve an issue with E03
			play();
		} else if (selectedMode == 1 && selectedColor.equalsIgnoreCase("red")) {
			this.toMove = redNormal;
			update(getGraphics()); // Updated to resolve an issue with E03
			drawCheckers(); // Updated to resolve an issue with E03
			play();
		}

		update(getGraphics());
		drawCheckers();
		showStatus();
	}

	public void drawCheckers() { // paint checkers on the board
		g = getGraphics();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == redNormal)
					g.drawImage(redN.getImage(), i * 50, j * 50, this);
				else if (board[i][j] == yellowNormal)
					g.drawImage(yellowN.getImage(), i * 50, j * 50, this);
				else if (board[i][j] == redKing)
					g.drawImage(redK.getImage(), i * 50, j * 50, this);
				else if (board[i][j] == yellowKing)
					g.drawImage(yellowK.getImage(), i * 50, j * 50, this);
			}
		}

	}

	public void undo() { // undo function
		undoCount = 1;
		for (int i = 0; i < 8; i++) {
			System.arraycopy(preBoard3[i], 0, board[i], 0, 8); // copies previous board
		}
		toMove = preToMove3;
		drawCheckers();
		update(g);

		if (selectedMode == 1) {
			play();
		}

	}

	// Updated By Greg Schoberth To Resolve E03
	// Updated by Kendra Neil to add functionality for E04
	public void play() {
		popUp = this.getLocationOnScreen();

		undoCount++;

		if (undoCount > 3) {
			if (selectedMode == 1 && difficulty != 4)
				unB.setEnabled(true);
			else if (selectedMode == 2)
				unB.setEnabled(true);
		}

		for (int i = 0; i < 8; i++) {
			System.arraycopy(preBoard2[i], 0, preBoard3[i], 0, 8);
			System.arraycopy(preBoard1[i], 0, preBoard2[i], 0, 8);
			System.arraycopy(board[i], 0, preBoard1[i], 0, 8);
		}
		preToMove3 = preToMove2;
		preToMove2 = preToMove1;
		preToMove1 = toMove;
		int tempScore;
		int[] result = new int[4];
		int[] counter = new int[1];

		counter[0] = 0;

		if (this.toMove == yellowNormal && selectedMode == 1 && selectedColor.equalsIgnoreCase("yellow")) {
			this.toMove = redNormal;
			showStatus();
			tempScore = GameEngine.MinMax(board, 0, difficulty + 2, result, this.toMove, counter);

			if (result[0] == 0 && result[1] == 0)
				loser = redNormal;
			else {
				// Start Changes
				computerHighlight(result[0], result[1]);
				computerDelay();

				// End Changes
				CheckerMove.moveComputer(board, result);
				if (loser == empty) {
					new PlaySound("src//sounds//comPlay.wav").start();
					play();
				}
				this.toMove = yellowNormal;
			}
		}

		else if (this.toMove == redNormal && selectedMode == 1 && selectedColor.equalsIgnoreCase("red")) {
			this.toMove = yellowNormal;
			showStatus();
			tempScore = GameEngine.MinMax(board, 0, difficulty + 2, result, this.toMove, counter);

			if (result[0] == 0 && result[1] == 0)
				loser = yellowNormal;
			else {
				// Start Changes
				computerHighlight(result[0], result[1]);
				computerDelay();

				CheckerMove.moveComputer(board, result);
				if (loser == empty) {
					new PlaySound("src//sounds//comPlay.wav").start();
					play();
				}

				this.toMove = redNormal;
			}
		} else {
			if (this.toMove == redNormal)
				this.toMove = yellowNormal;
			else
				this.toMove = redNormal;
		}
		if (CheckerMove.noMovesLeft(board, this.toMove)) //
		{
			if (this.toMove == redNormal)
				loser = redNormal;
			else
				loser = yellowNormal;
		}

		showStatus();
	}

	private boolean isPossibleSquare(int i, int j) {
		return (i + j) % 2 == 1;
	}

	public void itemStateChanged(ItemEvent e) {
	}

	public void mousePressed(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();
		int[] square = new int[2];

		if (x >= 0 && x <= 500 && y <= 500 && y >= 0)
			square = CheckerMove.getIndex(x, y);

		if (toMove == Checkers.redNormal
				&& (board[square[0]][square[1]] == Checkers.redNormal
						|| board[square[0]][square[1]] == Checkers.redKing)
				|| toMove == Checkers.yellowNormal && (board[square[0]][square[1]] == Checkers.yellowNormal
						|| board[square[0]][square[1]] == Checkers.yellowKing)) {

			// we don't want to lose the incomplete move info:
			// only set new start variables if !incomplete
			if (!incomplete) {
				highlight = true;
				startX = square[0];
				startY = square[1];
				update(g);
				g = getGraphics();
				g.setColor(new Color(255, 100, 30));
				g.fillRect(50 * square[0], 50 * square[1], 50, 50);
				drawCheckers();
				new PlaySound("src//sounds//clickChecker.wav").start();
			}
		} else if (highlight && (float) (square[0] + square[1]) / 2 != (square[0] + square[1]) / 2) {
			endX = square[0];
			endY = square[1];
			int status = CheckerMove.ApplyMove(board, startX, startY, endX, endY);
			switch (status) {
			case CheckerMove.legalMove:
				incomplete = false;
				highlight = false;

				update(g);
				drawCheckers();

				play();

				update(g);
				drawCheckers();

				computerDelay();
				break;
			case CheckerMove.incompleteMove:
				incomplete = true;
				highlight = true;
				// the ending square is now starting square for the next capture
				startX = square[0];
				startY = square[1];
				update(g);
				g = getGraphics();
				g.setColor(new Color(255, 100, 30));
				g.fillRect(50 * square[0], 50 * square[1], 50, 50);
				drawCheckers();
				break;
			}
		}
	}

	// Updated By Greg Schoberth To Resolve E01
	// Causes the thread to sleep for a set period of time
	// This method is used to create a delay between player and computer moves
	// to create the sense of the computer choosing its move before executing
	// it.
	public void computerDelay() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// This method takes in the selected piece the computer will move
	// and highlights the tile before executing the move. Similar to the player
	// selecting a piece on the board.
	public void computerHighlight(int x, int y) {

		int[] square = new int[2];
		// square = CheckerMove.getIndex(x, y);

		square[0] = x;
		square[1] = y;

		g = getGraphics();
		g.setColor(new Color(255, 100, 30));
		g.fillRect(50 * square[0], 50 * square[1], 50, 50);
		drawCheckers();
		new PlaySound("src//sounds//clickChecker.wav").start();

	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	private void showStatus() { // prints messages to the status bar
		if (this.toMove == redNormal) {
			msg.setText("Red to move");
		} else {
			msg.setText("Yellow to move"); // Eli updated this 12/1/16 to resolved B04
		}

		if (loser == redNormal && won == 0) {
			msg.setText("Yellow Wins!");
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new GameWin("Yellow", this.getLocationOnScreen()); // Eli updated this 12/1/16 to resolved B13
			won = 1;
			undoCount = 0;
			newGame();
		} else if (loser == yellowNormal && won == 0) {
			msg.setText("Red Wins!");
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new GameWin("Red", this.getLocationOnScreen());
			won = 1;
			undoCount = 0;
			newGame();
		}
	}

	// The AWT invokes the update() method in response to the repaint() method
	// calls that are made as a checker is dragged. The default implementation
	// of this method, which is inherited from the Container class, clears the
	// applet's drawing area to the background color prior to calling paint().
	// This clearing followed by drawing causes flicker. CheckerDrag overrides
	// update() to prevent the background from being cleared, which eliminates
	// the flicker.
	@Override
	public void update(Graphics g) {
		paint(g);
	}
}
