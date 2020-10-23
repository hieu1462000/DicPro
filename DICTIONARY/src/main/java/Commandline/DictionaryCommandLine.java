package Commandline;

public class DictionaryCommandLine{
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.insertFromFile(dictionary, "DICTIONARY/src/data.txt");
        dictionaryManagement.sort(dictionary);
        dictionaryManagement.searcher(dictionary,"hello");
        dictionaryManagement.binarySearch(dictionary,"Hello");
        dictionaryManagement.exportToFile(dictionary,"DICTIONARY/src/data.txt");
        System.out.println("End");
    }
}