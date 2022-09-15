package JavaFXGame;


/**
 * This class is to pass the gameover message to the Gui system
 */
public class Gameover {

    /**store the message**/
    String gameOverMsg="";

    /**store boolean which check whether game is over or not**/
    boolean bool=true;

    /**
     * method to set the game over message
     * @param bool passes boolean.check whether game is over or not.True means game is over.
     */
    public void setGameOverMsg(boolean bool) {
        this.bool=bool;
        if (bool==true){
            this.gameOverMsg="Game Over!!! Please Restart";
        }
        if (bool==false){
            this.gameOverMsg="";
        }
    }

    /**
     * method to return the message
     * @return return the message as string
     */
    @Override
    public String toString() {
            return gameOverMsg ;
    }
}
