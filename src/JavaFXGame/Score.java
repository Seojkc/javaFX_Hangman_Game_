package JavaFXGame;

/**
 * class which deals with score for the hangman game.
 */
public class Score {

    /**store the score**/
    private int score=0;


    /**
     * method to set the score
     * @param score set pass the score .int type
     */
    public void setScore(int score) {
        this.score = score;
    }

    /***
     * method to give the details of score as String.
     * @return details as String type.
     */
    @Override
    public String toString() {
        return "score=" + score ;
    }
}
