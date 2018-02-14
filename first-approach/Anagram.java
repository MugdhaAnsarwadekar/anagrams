import java.util.*;
import java.io.*;

public class Anagram {
    //Create HashMap to store sorted word and list of original words
    private HashMap<String, LinkedList<String>> dictionary;

    public Anagram(){
        dictionary = new HashMap<>();
    }

    public static void main(String[] args) {
        //String fileName = args[0];
        String fileName = "src/dictionary.txt";
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
            String sortedWord;
            if(word.contains("-")){
                sortedWord = processHyphen(word);
            } else{
                sortedWord = getSortedWord(word);
            }
            LinkedList<String> list;
            // Get the list and add new word to the list
            if(dictionary.containsKey(sortedWord)){
                list = dictionary.get(sortedWord);
            }
            else{
                list = new LinkedList<>();
            }
            list.add(word);
            dictionary.put(sortedWord, list);
        }
    }

    LinkedList<String> getAnagrams(String input){
        String formattedInput = input.trim();
        if(formattedInput.length() == 0) return null;
        // support single word input only
        if(formattedInput.contains(" ")) return null;
        String sortedWord;
        if(input.contains("-")){
            sortedWord = processHyphen(input);
        }else {
            sortedWord = getSortedWord(input);
        }
        // Return list if dictionary contains anagram of input or input itself
        if(dictionary.containsKey(sortedWord)){
            return dictionary.get(sortedWord);
        }
        return null;
    }

    String getSortedWord(String word){
        // Trim, Covert to lowercase, Remove ' from the words
        String tempWord = word.trim().toLowerCase().replaceAll("'","");
        char[] temp = tempWord.toCharArray();
        Arrays.sort(temp);
        return new String(temp);
    }

    String processHyphen(String word){
        String[] splittedWord = word.split("-");
        Arrays.sort(splittedWord);
        StringBuilder stringBuilder = new StringBuilder(word.length());
        for(int i = 0; i < splittedWord.length; i++){
            stringBuilder.append(getSortedWord(splittedWord[i]));
            if(i < splittedWord.length - 1){
                stringBuilder.append("-");
            }
        }
        return stringBuilder.toString();
    }

}
