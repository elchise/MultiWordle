/*
2D array project (Mar 10) - wordles: create a game with any specified number of wordles

Main class: create game, display, set up, etc.
 */
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //scanner for user input
        Scanner in = new Scanner(System.in);
        
        //read words from list to form dictionary
        try{
            URL dict = new URL("https://www-cs-faculty.stanford.edu/~knuth/sgb-words.txt");
            Scanner sc = new Scanner(dict.openStream());
            while (sc.hasNext()){
                Wordle.addToDictionary(sc.next());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        //set # of wordles
        System.out.print("Enter number of wordles: ");
        int n = in.nextInt();
        if (n < 0){
            System.out.print("Enter a positive number: ");
            n = in.nextInt();
        }
        Game g = new Game(n);
        g.play();       
    }
}
