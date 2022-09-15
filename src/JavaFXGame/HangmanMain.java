package JavaFXGame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * @author Seo James
 */
public class HangmanMain extends Application {

    // Instance Variables for View Components and Model

    /**stores graphicsContext into gc**/
    private GraphicsContext gc;

    /**store Hangman class into hangman**/
    private Hangman hangman;

    /**store score into scoreGames**/
    private Score scoreGames;

    /**store QuestionClass into qclass**/
    private QuestionClass qclass;

    /**store TextField array**/
    private TextField[] textFields;

    /**store score label**/
    private Label scoreLabel;

    /**Store pane into root**/
    private Pane root;

    /**store alive status of hangman**/
    private boolean alive=true;

    /**store life status of hangman**/
    private int life=0;

    /**store score**/
    private int score=0;

    /**Store Gameover class into gameover**/
    private Gameover gameover;

    /**store game over message label**/
    private Label gameOverMsg;

    /**store question number**/
    private int questionnumber=0;


    /**
     *
     * setting restart button
     * @param e is ActionEvent
     */
    private void setrestart(ActionEvent e){
        alive=true;//setting alive status into ture
        life=-1;//life as -1
        gameOver(false);//hiding the game over message
        hangman.setAliveStatus(true);// setting alive status of hangman as true
        Canvas canvas = new Canvas(500, 600);
        canvas.relocate(750,120);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());//clearing the hangman draw
        root.getChildren().add(canvas);//replacing new canvas where life is 0
        setHangman();//calling setHangman method with true as parameter
        increaseScore(false);//setting score as 0 using this method

    }


    /**
     * method to show game over message on the gui application.
     * @param bool passes true or false.
     */
    private void gameOver(boolean bool){
        if (bool){//if the message want to show on screen
            gameover.setGameOverMsg(true);
            gameOverMsg.setText(gameover.toString());
        }
        if (!bool){
            gameover.setGameOverMsg(false);
            gameOverMsg.setText(gameover.toString());
        }
    }


    /**
     * method to check the user typed word is matching with answer or not and setting the score when check button is pressed
     * @param event is ActionEvent
     */
    private void setscore(ActionEvent event) {
        if (alive){//checking whether hangman is already dead or not
            boolean same= qclass.checkIdenticle(textFields);
            if (same) {
                increaseScore(true);//calling to increase the score
            }
            if (!same) {
                setHangman();//calling setHangman
            }
        }
        if (!alive){
            gameOver(true);
        }
    }

    /**
     * method to set the score
     *
     * @param boool will pass true or false. True will increase the score by 5 and set new question. False will set score to 0
     */
    private void increaseScore(boolean boool){
        if (boool){
            score+=5;
            scoreGames.setScore(score);
            scoreLabel.setText(scoreGames.toString());

            //setting new question
            questionnumber+=1;
            if (qclass.isFinalQuestion()){
                gameOver(true);
                alive=false;
            }
            else{
                qclass.setAnswerWord(questionnumber);
                int xCoordinateOfTextField=100;
                int yCoordinateOfTextField=300;
                root.getChildren().removeAll(textFields);
                textFields=new TextField[qclass.getLengthOfTheWord()];
                boolean bool=true;
                for (int i = 0;i<qclass.getLengthOfTheWord();i++){
                    if (bool){
                        textFields[i]=new TextField(qclass.getArrayOfAnsLetter()[i]);
                        textFields[i].setEditable(false);
                        bool=false;
                        textFields[i].setId("textbox");
                    }
                    else{
                        textFields[i]=new TextField("");
                        bool=true;
                        textFields[i].setId("textbox1");
                    }
                    //resizing each textFields
                    textFields[i].setPrefSize(100, 60);
                    //adding each textField to the root
                    root.getChildren().add(textFields[i]);
                    //change the location of each textField
                    textFields[i].relocate(xCoordinateOfTextField, yCoordinateOfTextField);
                    //MOving right the next upcoming textField
                    xCoordinateOfTextField+=110;

                }

            }
        }
        if (!boool){
            score=0;
            scoreGames.setScore(score);
            scoreLabel.setText(scoreGames.toString());
        }
    }

    /**
     * method to drawing the hangman on canvas
     */
    private void setHangman() {
        life+=1;
        Canvas canvas = new Canvas(500, 600);
        canvas.relocate(750,120);
        root.getChildren().add(canvas);
        hangman.setLife(life);
        hangman.draw(gc);

        if (!hangman.isAliveStatus()){
            alive=false;
            gameOver(true);
        }
    }

    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception for exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        root = new Pane();
        root.setId("pane");//setting id to pane
        Scene scene = new Scene(root, 1200, 700); // setting the size here
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());//calling css external file
        stage.setTitle("Hangman_bySeoJames"); // setting the window title here
        Image icon=new Image("JavaFXGame/logo.jpg");//setting icon for application
        stage.getIcons().add(icon);
        stage.setScene(scene);

        //setting gameover label
        gameOverMsg=new Label();
        gameover=new Gameover();
        gameOverMsg.setId("gameover");
        gameover.setGameOverMsg(false);

        //setting score label
        scoreGames=new Score();
        scoreLabel=new Label(scoreGames.toString());
        scoreLabel.setId("score");

        //setting canvas for hangman
        Canvas canvas = new Canvas(500, 600);
        hangman=new Hangman();

        //Setting check button
        Button checkButton = new Button("CHECK");
        checkButton.setId("checkButton");

        //setting restart button
        Button restart = new Button("");
        restart.setId("restart");

        //Setting a Hint label
        Label hint=new Label("Hint : This is an animal name");
        hint.setId("hint");

        //Setting a copyright label
        Label copyright=new Label("© Seo James,Mohawk College,2022");


        //setting textfield for the word puzzle
        qclass=new QuestionClass(questionnumber);
        int xCoordinateOfTextField=100;//setting x coordinate for 1st textField
        int yCoordinateOfTextField=300;//setting y coordinate for 1st textField
        textFields=new TextField[qclass.getLengthOfTheWord()];//setting the length of textField array
        boolean bool=true;//this will help to make alternating 2 type of textFields
        for (int i = 0;i<qclass.getLengthOfTheWord();i++){
                if (bool){
                    textFields[i]=new TextField(qclass.getArrayOfAnsLetter()[i]);
                    textFields[i].setId("textbox");
                    //disabling the editing option.user cannot type in this field
                    textFields[i].setEditable(false);
                    bool=false;
                }
                else{
                textFields[i]=new TextField("");
                textFields[i].setId("textbox1");
                textFields[i].requestFocus();
                bool=true;
                }

                textFields[i].setPrefSize(100, 60);
                root.getChildren().add(textFields[i]);

            textFields[i].relocate(xCoordinateOfTextField, yCoordinateOfTextField);
            xCoordinateOfTextField+=110;

        }


        //adding components to the root
        root.getChildren().addAll(canvas,copyright,hint, restart, checkButton,scoreLabel,gameOverMsg);

        //Configuring the components (colors, fonts, size, location)
        gameOverMsg.relocate(80, 120);
        scoreLabel.relocate(100, 20);
        checkButton.relocate(260, 500);
        restart.relocate(1000, 50);
        canvas.relocate(750,120);
        copyright.relocate(40, 680);
        hint.relocate(120,400);
        restart.setPrefSize(100, 40);
        checkButton.setPrefSize(320, 60);

        //Adding Listeners and doing final setup
        restart.setOnAction(this::setrestart);
        checkButton.setOnAction(this::setscore);
        gc = canvas.getGraphicsContext2D();
        hangman.draw(gc);


        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
