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

public class hangingmanGUI extends JFrame{

	public static JFrame mainframe;
	public static JTextPane wordList;
	public static JTextPane animation;
	public static JTextField answerField;
	public static JTextField userInput;
	public static JButton submit;
	public static GridBagConstraints c;
	public static Container panel;
	public static char[] printedWord;
	public static char[] answerWord;

	public static GridBagConstraints gbc() {
		GridBagConstraints constraints = new GridBagConstraints();
		return constraints;
	}

	public static void initGUI(int length) {
		initMainframe();
		mainframe.setContentPane(panel);	
		panel.setBackground(Color.darkGray);

		initHangMan();
		initUserInput();
		initAnswerField();
		initSubmit();
		initWordList();

		printedWord = new char[length*2];
		for(int i = 1; i < length*2; i += 2) {
			printedWord[i-1] = ' ';
			printedWord[i] = '_';
		}
		answerField.setText(String.valueOf(printedWord));

		mainframe.pack();	
   	    mainframe.setLocationRelativeTo(null);		
		mainframe.setVisible(true);
	}

	public static void initMainframe() {
		mainframe = new JFrame("Hanging Man");  
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setSize(200, 200);
		mainframe.setLayout(new GridBagLayout());

		panel = mainframe.getContentPane();
	}

	public static void initSubmit() {
		submit = new JButton("Submit");

		c = gbc();
		c.gridx = 3;
		c.gridy = 2;
		c.weightx = 1;
		c.insets = new Insets(0, 10, 10, 10);
		c.anchor = GridBagConstraints.FIRST_LINE_START;

		panel.add(submit, c);
	}

	public static void initWordList() {
		wordList = new JTextPane();
		wordList.setText("Words guessed:");
		wordList.setPreferredSize(new Dimension(200, 240));
		wordList.setBackground(new Color(230, 230, 230));
		wordList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		wordList.setEditable(false);
		wordList.setBorder(
  			javax.swing.BorderFactory.createCompoundBorder(
      		javax.swing.BorderFactory.createTitledBorder(
      			null, " ", 
      			javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
      			javax.swing.border.TitledBorder.DEFAULT_POSITION,
         		new java.awt.Font("Verdana", 2, 2)), javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)));

		c = gbc();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 2;
		c.gridwidth = 2;
		c.weighty = 1.0;
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 1.0;
		c.insets = new Insets(10, 10, 10, 10);

		panel.add(wordList, c);
	}

	public static void initHangMan() {
		animation = new JTextPane();
		animation.setPreferredSize(new Dimension(200, 200));
		animation.setBackground(new Color(230, 230, 230));
		animation.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		animation.setEditable(false);

		c = gbc();
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 0, 10);
		c.anchor = GridBagConstraints.CENTER;

		panel.add(animation, c);
	}

	public static void initAnswerField() {
		answerField = new JTextField(2);
		answerField.setPreferredSize(new Dimension(100, 25));
		answerField.setBackground(new Color(180, 180, 180));
		answerField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		answerField.setHorizontalAlignment(JTextField.CENTER);
		answerField.setEditable(false);

		c = gbc();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 10, 0, 10);
		c.anchor = GridBagConstraints.CENTER;

		panel.add(answerField, c);
	}

	public static void initUserInput() {
		userInput = new JTextField(2);
		userInput.setPreferredSize(new Dimension(200, 25));
		userInput.setBackground(new Color(230, 230, 230));
		userInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		userInput.setEditable(true);

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