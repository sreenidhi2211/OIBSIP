import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuess extends JFrame{
    private int randomNo;
    private int guesses;
    private int maxGuesses=5;
    private int Score=0;
    private JTextField guessInput;
    private JLabel msgDisplay;
    private JLabel scrDisplay;
    private JButton submitButton;
    private JButton restartButton;

    public NumberGuess(){
        //setting window
        setTitle("Guess the Number");
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        gameReset();
        add(Box.createVerticalGlue());
        //setting Label for displaying message
        msgDisplay=new JLabel("Guess a Number between 1 and 100");
        msgDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(50));
        add(msgDisplay);
        //setting guessInput textfield
        JPanel inputPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        guessInput=new JTextField();
        guessInput.setPreferredSize(new Dimension(200,30));
        guessInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(guessInput);
        add(Box.createVerticalStrut(10));
        add(inputPanel);
        //Creating Panel for buttons
       JPanel buttonPanel=new JPanel();
       buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
       //creating submitButton
       submitButton=new JButton("Submit");
       submitButton.setBackground(Color.GREEN);
       submitButton.setForeground(Color.WHITE);
       buttonPanel.add(submitButton);
       //creating restart button
       restartButton=new JButton("Restart");
       restartButton.setBackground(Color.RED);
       restartButton.setForeground(Color.WHITE);
       restartButton.setEnabled(false);
       buttonPanel.add(restartButton);
       add(buttonPanel);
       add(Box.createVerticalStrut(10));
       buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
       //setting Label for displaying score
       scrDisplay=new JLabel("Your Score is: "+Score);
       scrDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
       add(Box.createVerticalStrut(10));
       add(scrDisplay);
       add(Box.createVerticalGlue());

       //triggering button actions
       submitButton.addActionListener(new SubmitButtonListener());
       restartButton.addActionListener( new RestartButtonListener());
       setVisible(true);
    }
       //For Reset
       private void gameReset() {
        Random r =new Random();
        randomNo=r.nextInt(100)+1;
        guesses=0;
    }
    private class SubmitButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                int userGuess=Integer.parseInt(guessInput.getText());
                guesses++;
                if(userGuess==randomNo){
                    msgDisplay.setText("You won!!! Your guess is correct");
                    Score +=(maxGuesses-guesses+1)*10;
                    submitButton.setEnabled(false);
                    restartButton.setEnabled(true);
                }
                else if(guesses>=maxGuesses){
                    msgDisplay.setText("Out attempts ! The Number : "+randomNo);
                    submitButton.setEnabled(false);
                    restartButton.setEnabled(true);
                }
                else if (userGuess > randomNo) {
                    msgDisplay.setText("Too high! Try again.");
                } else {
                    msgDisplay.setText("Too low! Try again.");
                }
            }
            catch (NumberFormatException ex) {
                msgDisplay.setText("Please enter a valid number!");
            }
        }    
    }
    private class RestartButtonListener implements ActionListener {
            @Override
           public void actionPerformed(ActionEvent e) {
        gameReset();
        msgDisplay.setText("Guess a number between 1 and 100:");
        guessInput.setText("");
        submitButton.setEnabled(true);
        restartButton.setEnabled(false);
    }
}
public static void main(String[] args) {
    new NumberGuess();
}
}
