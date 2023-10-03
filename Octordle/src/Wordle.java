/*
2D array project (Mar 10) - wordles: create a game with any specified number of wordles

Wordle class - 5 letter word solution. User inputs and tries to guess the word. Letters in the right position are printed as green, letters in the word but wrong positioni as yellow, and letters not in the word as default text color. 
 */
import java.util.*;

public class Wordle {
    private static ArrayList<String> dictionary = new ArrayList<String>(); //dictionary with list of 5 letter words
    private String word; //solution
    private int currentTry; //what guess is the user on
    private String[] guesses; //all of user's guesses (with corresponding colors)
    private boolean correct; //has it been solved
    private static final String RESET = "\u001b[0m", GREEN = "\u001B[32m", YELLOW = "\u001B[33m"; //colors for ouput
    
    public Wordle(int num){
        word = dictionary.get((int)(Math.random() * dictionary.size()));
        currentTry = 0;
        guesses = new String[num];
        correct = false;
    }
    
    //adds words to dictionary 
    public static void addToDictionary(String word){
        dictionary.add(word);
    }
    
    //checks if word exists (if dictionary contains word)
    public static boolean isWord (String w){
        return dictionary.contains(w);
    }    
    
    //check if letters are in the right spot and changes the color accordingly. returns if the word was correct or not.
    public boolean check(String guess){
        String c;
        currentTry++;
        correct = true;
        guesses[currentTry-1] = "\t";
        for (int i = 0; i < guess.length(); i++){
            c = guess.substring(i, i+1);
            if (c.equalsIgnoreCase(word.substring(i, i+1))){
            //green if it's in the right position
                guesses[currentTry-1] += (GREEN + c + RESET);
            } else if (word.contains(c)){
                //yellow if wrong position
                guesses[currentTry-1] += (YELLOW + c + RESET);
                correct = false;
            } else {
                //don't change color if entirely wrong
                guesses[currentTry-1] += ( c );
                correct = false;
            }
        }
        return correct;
    }
    
    //returns if the user has completed the wordle
    public boolean isCorrect(){
        return correct;
    }
    
    //returns a certain guess
    public String getGuess(int num){
        return guesses[num];
    }
    
    //returns the solution of wordle 
    public String getWord(){
        return word;
    }
        
    //sets a new solution
    public void setNewWord(){
        word = dictionary.get((int)(Math.random() * dictionary.size()));        
    }
    
    //returns # of tries taken
    public int getCurrentTry(){
        return currentTry;
    }
}
