/*
2D array project (Mar 10) - wordles: game with any specified number of wordles

Game class - Create game (2d array) of wordles with the input number (fixed number of columns). User can input words but must use the same words/guesses to figure out all the solutions (i.e. each word is put into each wordle)
 */
import java.util.Scanner;


public class Game {
    private Wordle[][] game; // grid of wordles
    private int numTries, curTry; //total number of attempts, current attempt number
    private static int cols = 4; //set number of columns
    
    public Game(int num){
        
        //number of rows
        int r = num/cols;
        if (num % cols != 0){
            r++;
        }
        
        //sets game with 4 columns
        game = new Wordle[r][cols];
        numTries = 5 + num;
        curTry = 1;
        
        int wordleNum = 0;
        //creates each new wordle
        for (int row = 0; row < game.length; row++){
            for (int col = 0; col < game[row].length; col++){
                game[row][col] = new Wordle(numTries);
                        
                //checks if word is already  being used
                for (int checkR = row; checkR >= 0; checkR--){
                    for (int checkC = col; checkC >= 0; checkC--){
                        if (game[row][col].getWord().equals(col)){
                            //if same as another wordle, set as a new word and check all again
                            game[row][col].setNewWord();
                            checkR = row;
                            checkC = col;
                        }
                    }
                }
                //count for number of games for if it doesn't fit with 
                wordleNum++;
                if(wordleNum >= num){
                    break;
                }
                
            }
        }
    }
    
    public void play(){
        boolean correct = false;
        curTry = 1;        
        //while the user still has guesses left and hasn't solved, continue to ask for guesses and check
        while (!correct && curTry <= numTries){
            Scanner in = new Scanner(System.in);
            System.out.print("Guess " + curTry + "/" + numTries + ": ");
            correct = check(in.next());
            curTry++;
            System.out.println("");    
        }
        
        if (correct){
            //if the user solves --> congrats message
            System.out.println("Congratulations! You got it! \nHere's an overview: ");
        } else{
            //if user doesn't solve --> display solutions
            System.out.println("You ran out of attempts. The words were:");
        }
        
        System.out.println(this);
    }
    
    //check input guess
    public boolean check(String guess){
        
        //make sure user's guess is a 5 letter word; if not, ask to input a different guess
        while (guess.length() != 5 || !Wordle.isWord(guess)){
            System.out.print("Invalid guess. Enter another word: ");
            Scanner in = new Scanner(System.in);
            guess = in.next();
        }
        
        boolean allRight = true;
        
        //check if everything is correct
        for (int r = 0; r < game.length; r++){            
            for (int c = 0; c < game[r].length ; c++){
                //check that game exists and has not been solved
                if (game[r][c] != null && !game[r][c].isCorrect() && !game[r][c].check(guess)){
                    //check if guess is correct; if incorrect, set boolean
                      allRight = false;
                    
                }
            }
        }
        
        //print out result of guesses (prints all previous plus new guess)
        int attempt = 0;
        for (int r = 0; r < game.length; r++){
            attempt = 0;
            while (attempt < curTry) {
            //check that just printing what the user has already guessed
            
                for (int c = 0; c < game[0].length; c++){
                    //for each row, print out first word of each, second of each, ...
                    if (game[r][c] != null){ 
                        if (game[r][c].getGuess(attempt)== null){
                            //if user has solved and there is no guess, add space
                            System.out.print("\t\t\t");
                        } else {
                            System.out.print(game[r][c].getGuess(attempt) + "\t\t");
                        }
                    }
                }
                    System.out.println("");
                    attempt++;
            }
            System.out.println("");
        }
        
        //return whether everything has been solved
        return allRight;
    }
    
    //returns total number of tries available
    public int numTries(){
        return numTries;
    }
    
    //returns the solutions
    public String toString(){
        String words = "\t";
        for (Wordle[] r : game){
            for (Wordle c :r ){
                if (c!=null){
                    if (c.isCorrect()){
                        words+=c.getWord()+"(Solved in " + c.getCurrentTry() + ")" + "\t";
                    } else {
                        words+=c.getWord() + "\t\t\t";
                    }
                }
            }
            words+="\n\t";
        }
        return words;
    }
    
}
