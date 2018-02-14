# anagrams </br>

This repository contains two approaches to find anagrams of a word from dictionary. In the first approach, dictionary is stored in a key-value form where key is the sorted version of the word and value is list of all words with same sorted representation. In the second approach, dictionary is stored as a list of words and for each incoming word, count of characters is calculated and this is compared with count of characters of all words in the dictionary.</br>



##### Analysis of time and space complexities</br>
For the first approach time complexity to create dictionary is O(n * mlogm) where n is total number of words in dictionary and m is average length of the words. Space required to store the dictionary is O(n + k) where n is total number of words in directionary and k is number of unique keys. Lookup time for a word will be O(1) in average/best case, however, in the worst case it will O(n) if collisions are chained. Time complexity for dictionary creation in the second approach will be O(n) as each word is directly added to the list and space required to store dictionary will also be O(n). Lookup time for this approach will be O(n * m) because all characters of each word are traversed in order to find the character count for every input word.
