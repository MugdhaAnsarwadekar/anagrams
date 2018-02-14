# anagrams </br>

This repository contains two approaches to find anagrams of a word from dictionary. In the first approach, dictionary is stored in a key-value form where key is the sorted version of the word and value is list of all words with same sorted representation. In the second approach, dictionary is stored as a list of words and for each incoming word, count of characters is calculated and this is compared with count of characters of all words in the dictionary.


</br>
##### Analysis of time and space complexities</br>
The time complexity of this approach to create dictionary is O(n * mlogm) where n is the number of words in dictionary and m is average length of the words and space complexity for stori
