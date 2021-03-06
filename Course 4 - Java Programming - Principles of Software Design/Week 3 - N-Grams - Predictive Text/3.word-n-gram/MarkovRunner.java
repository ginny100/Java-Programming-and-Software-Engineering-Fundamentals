/**
 * Run Markov Models
 * 
 * @author Ginny Dang
 * @version July 15th, 2022
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k = 0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k = 0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkovOne() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //int seed = 175;
        int seed = 139;
        int size = 120;
        MarkovWordOne markovWord = new MarkovWordOne();
        runModel(markovWord, st, size, seed); 
    } 
    
    public void runMarkovTwo() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //int seed = 549;
        int seed = 832;
        int size = 200;
        MarkovWordTwo markovWord = new MarkovWordTwo();
        runModel(markovWord, st, size, seed); 
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k = 0; k < words.length; k++){
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    }
    
    public void testing() {
        String st = "this is just a test yes this is a simple test";
        MarkovWordOne mw1 = new MarkovWordOne();
        mw1.setTraining(st);
        mw1.getRandomText(11);
    }
}