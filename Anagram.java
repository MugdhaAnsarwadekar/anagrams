import java.util.*;
import java.io.*;
/*
Approach:
For each word in the dictionary, sort the word and store stored 
word as key and original word as value.
For a given input, sort the input string and check if it hashmap
contains that key, if yes, then return the corresponding value
which is list of anagrams in the dictionary.
*/

public class Anagram {
    //Create HashMap to store sorted word and list of original words
    private HashMap<String, LinkedList<String>> dictionary = new HashMap<>();

    public static void main(String[] args) {
        String fileName = args[0].trim().toLowerCase();
        Anagram anagram = new Anagram();
        // Keep track of time required to load dictionary
        long startTime = System.nanoTime();
        // ReadFile will read the input dictionary and update the hashmap
        anagram.readFile(fileName);
        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime)/1000000;
        System.out.println("Dictionary loaded in " + totalTime + "ms");

        // Get the input from user and display output
        System.out.println("Enter input word: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        while(!input.equals("exit")){
            startTime = System.nanoTime();
            // Get the list of anagrams of input
            LinkedList<String> output = anagram.getAnagrams(input);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime)/1000000;
            if(output == null){
                System.out.println("No anagrams found for " + input + " in " + totalTime + "ms");
            }
            else{
                int size = output.size();
                System.out.println(size + " anagrams found for " + input + " in " + totalTime + "ms");
                System.out.println(output);
            }
            System.out.println("Enter input word: ");
            input = scanner.nextLine().trim().toLowerCase();
        }

    }

    private void readFile(String fileName){
        File file = new File(fileName);
        try{
            FileInputStream fileStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
            String word;
            while((word = reader.readLine()) != null){
                char[] chars = word.toCharArray();
                Arrays.sort(chars);
                String sortedWord = new String(chars);
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
        catch (FileNotFoundException exception){
            System.out.println("File not found");
        }
        catch (IOException IOexception){
            System.out.println("Error in reading file");
        }

    }

    private LinkedList<String> getAnagrams(String input){
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        String sortedWord = new String(chars);
        // Return list if dictionary contains anagram of input or input itself
        if(dictionary.containsKey(sortedWord)){
            return dictionary.get(sortedWord);
        }
        return null;
    }

}
