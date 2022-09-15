package JavaFXGame;

import javafx.scene.control.TextField;

/**
 * class used to store and pass the questions and data of the question for the game.
 */
public class QuestionClass {
    /**store the question number**/
    private int questionNumber;
    /**store the word.This will store single String item from array of words**/
    private String answerWord="";
    /**store the length of the word**/
    private int lengthOfTheWord=0;

    /**store each letters from the word**/
    private String[] arrayOfAnsLetter;

    /**stores the textField array**/
    private TextField[] textFields;

    /**stores the array of words **/
    private String[] arrayOfQuestions={"ZEBRA","DOG","MONKEY","COW","TIGER","BEAR","SHEEP","LION","PANDA","CAMEL","FOX"};

    private boolean finalQuestion;



    /**
     * constructor which used to set question number and answer of the word.
     * @param questionNumber will pass the question number from the main class.
     */
    public QuestionClass(int questionNumber) {
        this.questionNumber = questionNumber;
        setAnswerWord(questionNumber);
    }


    /**
     * method to check the textField array and answer letters are matching or not
     * @param textFields will pass the textField of word
     * @return boolean.iff true,increase the score else draw next step of hangman.
     */
    public boolean checkIdenticle(TextField[] textFields){
        int scoreCheck=0;
        this.textFields=textFields;
        for (int i = 0; i < lengthOfTheWord; i++) {
            if (textFields[i] == null) {
                continue;
            }
            if (textFields[i].getText().equalsIgnoreCase(arrayOfAnsLetter[i])) {
                scoreCheck++;
            }
        }
        if (scoreCheck == lengthOfTheWord) {
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * used to extracting and setting the word
     * @param questionNumber will pass the question number which will be the index of the array of words.Passes int type.
     */
    public void setAnswerWord(int questionNumber) {
        if (questionNumber< arrayOfQuestions.length){
            answerWord = arrayOfQuestions[questionNumber];
            setArrayOfAnsLetter(answerWord);
            setLengthOfTheWord( answerWord);
        }
        else{
            finalQuestion=true;//setting as final question
        }

    }

    /**
     * used to pass the final question status.
     * @return boolean.True means it is the final question.
     */
    public boolean isFinalQuestion() {
        return finalQuestion;
    }

    /**
     * method to get the length of the word.
     * @return the length of the word.
     */
    public int getLengthOfTheWord() {
        return lengthOfTheWord;
    }

    /**
     * method to set the length of the word
     * @param answerWord passes the answer word as String.
     */
    public void setLengthOfTheWord(String answerWord) {
        lengthOfTheWord = answerWord.length();
    }


    /**
     * method to
     * @return array of the letters of the answer word
     */
    public String[] getArrayOfAnsLetter() {
        return arrayOfAnsLetter;
    }

    /**
     * method converting the answer word into each letter and setting it into an array
     * @param answerWord passe the answer word.
     */
    public void setArrayOfAnsLetter(String answerWord) {

        arrayOfAnsLetter = new String[answerWord.length()];
        for (int i=0;i< arrayOfAnsLetter.length;i++){
            arrayOfAnsLetter[i]= String.valueOf(answerWord.charAt(i));
        }
    }

}
