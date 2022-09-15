package JavaFXGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * class used to set the hangman diagram and details for the game.
 */
public class Hangman {


    /**Store the life status of the hangman.Ranges from 0-5.At 5, hangman will be hanged and dead**/
    private int life=0;

    /**store the alive status of the hangman.Stores boolean values.True for alive and false means dead**/
    private boolean aliveStatus=true;


    /**
     * used to pass the alive status of the hangman.
     * @return boolean.true for alive and false iff dead.
     */
    public boolean isAliveStatus() {
        return aliveStatus;
    }

    /**
     * method used to set the alive status of hangman
     * @param aliveStatus passes the status
     */
    public void setAliveStatus(boolean aliveStatus) {
        this.aliveStatus = aliveStatus;
    }

    /**
     * method used to get the life status of the hangman.
     * @return life ranges as int type.
     */
    public int getLife() {
        return life;
    }

    /**
     * method used to set the life
     * @param life used to set the life.
     */
    public void setLife(int life) {
        if (aliveStatus==true){
            this.life = life;}
    }

    /**
     * method to draw the hangman on the canvas.
     * @param gc passes graphicsContext.
     */
    public void draw(GraphicsContext gc){
        gc.setLineWidth(5);//setting the line width

        gc.setStroke(Color.rgb(252, 170, 129));
        gc.strokeLine(270, 80, 270, 140);
        gc.setStroke(Color.rgb(61, 21, 1));
        gc.setLineWidth(20);//setting the line width as 20
        gc.strokeLine(70, 500, 230, 500);//strokeLine will draw the line
        gc.strokeLine(150, 500, 150, 80);
        gc.strokeLine(150, 80, 270, 80);
        gc.setLineWidth(10);
        gc.strokeLine(150, 150, 220, 80);




        gc.setStroke(Color.BLACK);//setting the line color as black
        if (getLife()==1){
            gc.strokeOval(200,140,140,140);
        }
        if (getLife()==2){
            gc.strokeOval(200,140,140,140);
            gc.strokeLine(270, 280, 270, 400);
        }
        if (getLife()==3){
            gc.strokeOval(200,140,140,140);
            gc.strokeLine(270, 280, 270, 400);
            gc.strokeLine(220, 320, 320, 320);
        }
        if (getLife()==4){
            gc.strokeOval(200,140,140,140);
            gc.strokeLine(270, 280, 270, 400);
            gc.strokeLine(220, 320, 320, 320);
            gc.strokeLine(270,400, 200, 450);
        }
        if (getLife()==5){
            gc.strokeOval(200,140,140,140);
            gc.strokeLine(230, 180, 250, 200);
            gc.strokeLine(230, 200, 250, 180);
            gc.strokeLine(300, 180, 320, 200);
            gc.strokeLine(320, 180, 300, 200);
            gc.strokeOval(255,230,30,30);
            gc.strokeLine(270, 280, 270, 400);
            gc.strokeLine(220, 320, 320, 320);
            gc.strokeLine(270,400, 200, 450);
            gc.strokeLine(270, 400, 340, 450);
            setAliveStatus(false);//setting the alive status as dead

        }




    }


}
