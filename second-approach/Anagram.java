import java.util.*;
import java.io.*;

public class Anagram {
    LinkedList<String> dictionary;
    // total count of all characters in dictionary
    private static final int CHAR_COUNT = 27;

    public Anagram(){
        dictionary = new LinkedList<>();
    }

    public static void main(String[] args) {
        String fileName = args[0];
        Anagram anagram = new Anagram();
        // Keep track of time required to load dictionary
        long startTime = System.nanoTime();
        // ReadFile will read the input dictionary and update the hashmap
        try{
            anagram.readFile(fileName);
        } catch (IOException exception){
            System.out.println("Dictionary could not be loaded");
            System.exit(1);
        }
        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime)/1000000;
        System.out.println("Dictionary loaded in " + totalTime + "ms");

        // Get the input from user and display output
        anagram.processInput();
    }

    void processInput(){
        System.out.println("Enter input word: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.trim().equalsIgnoreCase("exit")){
            long startTime = System.nanoTime();
            // Get the list of anagrams of input
            LinkedList<String> output = getAnagrams(input);
            long endTime = System.nanoTime();
            long totalTime = (endTime - startTime)/1000000;
            // Display the output
            if(output == null){
                System.out.println("No anagrams found for " + input + " in " + totalTime + "ms");
            }
            else{
                int size = output.size();
                System.out.println(size + " anagrams found for " + input + " in " + totalTime + "ms");
                System.out.println(output);
            }
            System.out.println("Enter input word: ");
            input = scanner.nextLine();
        }
    }

    void readFile(String fileName) throws IOException{
        File file = new File(fileName.trim());
        FileInputStream fileStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
        String word;
        while((word = reader.readLine()) != null){
            String formattedWord = word.trim().toLowerCase();
            dictionary.add(formattedWord);
        }
    }

    LinkedList<String> getAnagrams(String input){
        String formattedInput = input.trim().toLowerCase();
        if(formattedInput.length() == 0) return null;
        // support words without whitespace separation
        if(formattedInput.contains(" ")) return null;

        int[] inputCharFreq = getCharFrequency(formattedInput);
        LinkedList<String> result = new LinkedList<>();
        for(String word : dictionary){
            int[] wordCharFreq = getCharFrequency(word);
            boolean equalCount = true;
            for(int i = 0; i < wordCharFreq.length; i++){
                if(inputCharFreq[i] != wordCharFreq[i]){
                    equalCount = false;
                    break;
                }
            }
            if(equalCount){
                result.add(formattedInput);
            }
        }
        return result.size() == 0 ? null : result;
    }

    int[] getCharFrequency(String word){
        int[] charFreq = new int[CHAR_COUNT];
        for(int i = 0; i < word.length(); i++){
            char currentChar = word.charAt(i);
            if(currentChar != '-'){
                charFreq[currentChar - 'a']++;
            }else{
                charFreq[CHAR_COUNT - 1]++;
            }
        }
        return charFreq;
    }
}
