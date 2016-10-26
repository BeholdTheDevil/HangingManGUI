import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import java.io.*;
import java.util.Arrays;

public class hangingmanGUI extends JFrame {

	/*
	----------!            NUMBER OF FRAMES REQUIRED FOR HANGING = 11             !---------------
	*/
	public static int guesses = 11;
	public static int guessCount = 0;

	public static JFrame mainframe;
	public static JTextPane wordList;
	public static JTextPane animation;
	public static JTextField answerField;
	public static JTextField userInput;
	public static JButton submit;
	public static JButton reset;
	public static GridBagConstraints c;
	public static Container panel;
	public static char[][] savedWordArray;
	public static char[] printedWord;
	public static char[] answerWord;

	public static void main(String[] args) {
		//Get the randomized word from the dictionary
		answerWord = "hej".toCharArray();//getLine.getWord().toCharArray();
		//Initialize GUI
		initGUI(answerWord.length);
	}

	public static GridBagConstraints gbc() {
		//Zero out constraint preset so as to not accidently re-use the same constraints for multiple objects 
		GridBagConstraints constraints = new GridBagConstraints();
		return constraints;
	}

	public static void initGUI(int length) {
		initMainframe();
		mainframe.setContentPane(panel);	
		panel.setBackground(Color.darkGray);
		//Create an array with the size [guesses][length of word];
		savedWordArray = new char[guesses][length];

		initHangMan();
		initAnswerField();
		initWordList();
		initUserInput();
		initSubmit();
		initReset();

		//Create the placebo word
		printedWord = new char[length*2];
		printedWord[0] = '_';
		for(int i = 2; i < length*2; i += 2) {
			printedWord[i-1] = ' ';
			printedWord[i] = '_';
		}
		answerField.setText(String.valueOf(printedWord));

		//Pack up and locate window at center of screen
		mainframe.pack();	
   	    mainframe.setLocationRelativeTo(null);		
		mainframe.setVisible(true);
	}

	public static void initMainframe() {
		//Initialize mainframe with the title "Hanging Man"
		mainframe = new JFrame("Hanging Man");  
		//Set default exit operation
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set base mainframe size (will change dynamically with content)
		mainframe.setSize(200, 200);
		//Set layoutmanager to gridbaglayout
		mainframe.setLayout(new GridBagLayout());

		//Assign the main contentpane
		panel = mainframe.getContentPane();
	}

	public static void initSubmit() {
		//Create submit button
		submit = new JButton("Submit");
		//Add action event listener
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitActionPerformed(e);
			}
		});


		//The following is the nulling of predefined constraints and the definition of new ones
		c = gbc();
		c.gridx = 3;
		c.gridy = 2;
		c.weightx = 1;
		c.insets = new Insets(0, 10, 0, 10);
		c.anchor = GridBagConstraints.FIRST_LINE_START;

		panel.add(submit, c);
	}

	public static void initReset() {
		//Create the reset button
		reset = new JButton("Reset");
		//Add action event listener
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});

		//The following is the nulling of predefined constraints and the definition of new ones
		c = gbc();
		c.gridx = 4;
		c.gridy = 2;
		c.weightx = 1;
		c.insets = new Insets(0, 10, 0, 30);
		c.anchor = GridBagConstraints.FIRST_LINE_START;

		panel.add(reset, c);
	}

	public static void resetActionPerformed(ActionEvent e) throws NullPointerException {
		//Reset button to start over 
		//answerWord = getLine.getWord().toCharArray();

		// ----------------------! TEMPORARY , FIX LATER !--------------------------

		//Generate new word   		 OBS PLACEHOLDER ATM
		answerWord = "hejsan".toCharArray();
		//Create a new savedWordArray for comparison with the size of the new word
		savedWordArray = new char[guesses][answerWord.length];
		//Reset wordlist textfield
		wordList.setText("Words guessed:");
		guessCount = 0;

		//Generate new placeholder characters '_' for new wordlength
		printedWord = new char[answerWord.length*2];
		printedWord[0] = '_';
		for(int i = 2; i < answerWord.length*2; i += 2) {
			printedWord[i-1] = ' ';
			printedWord[i] = '_';
		}
		answerField.setText(String.valueOf(printedWord));
	}

	public static void submitActionPerformed(ActionEvent e) throws NullPointerException {
		//On actionevent trigger get current answer from answerfield
		char[] currentAns = userInput.getText().toCharArray();
		//Check for valid guess 
		if(currentAns.length > 1 && compareToWordList(currentAns)) {

			//Check if the char[] currentAns is equal to the char[] answerWord
			if(Arrays.equals(currentAns, answerWord)) {
				printedWord = answerWord;
				//If it is you win the game
				//	youWon();
			} else {
				//If it isn't, append the word to the wordlist box and animate hanging
				
			//	animateHanging();
			}

			//If char[] is a single char
		} else if(currentAns.length == 1) {
			//Check if answer contains that character
			for(int i = 0; i < answerWord.length; i++) {
				if(answerWord[i] == currentAns[0]) {
					//Check if that completes the word
					printedWord[i*2] = currentAns[0];
					if(String.valueOf(printedWord).replaceAll("\\s+","") == String.valueOf(answerWord)) {
						//You have won!
						//youWon();
					}
				}
			}
		}
		//Add your guess to the list of guesses
		wordList.setText(wordList.getText() + "\n" + String.valueOf(currentAns));
		//Update the printed characters
		answerField.setText(String.valueOf(printedWord));
		//Remove characters from input box
		userInput.setText("");
	}

	public static boolean compareToWordList(char[] currentAns) {
		//Function for comparing to already guessed words
		for(int i = 0; i < savedWordArray.length; i++) {
			if(savedWordArray[i] == currentAns) {
				return false;
			}			
		}
		return true;
	}

	public static void initWordList() {
		wordList = new JTextPane();
		wordList.setText("Words guessed:");
		wordList.setPreferredSize(new Dimension(200, 240));
		wordList.setBackground(new Color(230, 230, 230));
		wordList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		wordList.setEditable(false);
		//Create some internal padding in the textpane using borders and BorderFactory 
		wordList.setBorder(
  			javax.swing.BorderFactory.createCompoundBorder(
      		javax.swing.BorderFactory.createTitledBorder(
      			null, " ", 
      			javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
      			javax.swing.border.TitledBorder.DEFAULT_POSITION,
         		new java.awt.Font("Verdana", 2, 2)), javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)
      			)
      		);

		//The following is the nulling of predefined constraints and the definition of new ones
		c = gbc();
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 2;
		c.gridwidth = 6;
		c.weighty = 1.0;
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 1.0;
		c.insets = new Insets(10, 10, 10, 50);

		panel.add(wordList, c);
	}

	public static void initHangMan() {
		animation = new JTextPane();
		animation.setPreferredSize(new Dimension(200, 200));
		animation.setBackground(new Color(230, 230, 230));
		animation.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		animation.setEditable(false);

		//The following is the nulling of predefined constraints and the definition of new ones
		c = gbc();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(50, 100, 10, 60);
		c.anchor = GridBagConstraints.CENTER;

		panel.add(animation, c);
	}

	public static void initAnswerField() {
		answerField = new JTextField(2);
		answerField.setPreferredSize(new Dimension(400, 25));
		answerField.setBackground(new Color(180, 180, 180));
		answerField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		answerField.setHorizontalAlignment(JTextField.CENTER);
		answerField.setEditable(false);

		//The following is the nulling of predefined constraints and the definition of new ones
		c = gbc();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 50, 50, 10);
		c.anchor = GridBagConstraints.CENTER;

		panel.add(answerField, c);
	}

	public static void initUserInput() {
		userInput = new JTextField(2);
		userInput.setPreferredSize(new Dimension(200, 25));
		userInput.setBackground(new Color(230, 230, 230));
		userInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		userInput.setEditable(true);

		//The following is the nulling of predefined constraints and the definition of new ones
		c = gbc();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 2;
		c.weightx = 1;
		c.insets = new Insets(0, 10, 10, 10);
		c.anchor = GridBagConstraints.CENTER;

		panel.add(userInput, c);
	}
}