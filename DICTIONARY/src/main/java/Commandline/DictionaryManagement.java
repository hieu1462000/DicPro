package Commandline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Collections;
import java.util.Scanner;

public class DictionaryManagement {

    public void insertFromCommandline(Dictionary dictionary) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Target");
        String t = sc.nextLine();
        System.out.println("Explain");
        String e = sc.nextLine();
        Word word = new Word(t, e);
        dictionary.add(word);
    }

    public void insertFromFile(Dictionary dictionary, String path) {

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader buf = new BufferedReader(fileReader);
            String englishWord = buf.readLine().replace("@", "");
            buf.readLine();
            String line;
            while ((line = buf.readLine()) != null) {
                String meaning = "";
                String[] parts = englishWord.split("/", 2);
                if (parts.length > 1) {
                    meaning = parts[0] + "/" + parts[1] + "\n";
                } else {
                    meaning = parts[0] + "\n";
                }
                meaning += line + "\n";
                while ((line = buf.readLine()) != null) {
                    if (!line.startsWith("@")) {
                        meaning += line + "\n";
                    } else {
                        englishWord = line.replace("@", "");
                        break;
                    }
                }
                Word newWord = new Word(parts[0], meaning);
                dictionary.add(newWord);
            }
            System.out.println("Successfully");
            buf.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void exportToFile(Dictionary dictionary, String link){
        try{
            FileWriter fw = new FileWriter("DICTIONARY/src/main/resources/text/data.txt");
            BufferedWriter buf = new BufferedWriter(fw);
            for (int i = 0; i < dictionary.size(); i++) {
                Word word = dictionary.get(i);
                buf.write("@" + word.getWord_explain());
            }
            buf.newLine();
            buf.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public int dictionaryLookup(Dictionary dictionary, String keyWord) {
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).getWord_target().equals(keyWord)) {
                return i;
            }
        }
        return -1;
    }

    public ObservableList<String> searcher(Dictionary dictionary, String searchKey){
        ObservableList<String> list = FXCollections.observableArrayList();
        int limit = 0;
        for (int i = 0; i < dictionary.size() && limit < 20; i++) {
            if (dictionary.get(i).getWord_target().startsWith(searchKey)) {
                list.add(dictionary.get(i).getWord_target());
                limit++;
            }
        }
        return list;
    }

    public void deleteWords(Dictionary dictionary, Word word) {
       for (int i = 0; i < dictionary.size(); i++) {
          if (dictionary.get(i).equals(word)) {
               dictionary.remove(dictionary.get(i));
           }
        }
    }
    public int binarySearch(Dictionary arr , String s){
        sort(arr);
        int l = 0;
        int r = arr.size()- 1;
        while(l <= r){
            int mid  = l + (r - l) / 2;
            int result = arr.get(mid).getWord_target().compareTo(s);
            if(result == 0){
                return mid;
            }
            if (result <= 0 ){
                l = mid + 1;
            }
            if (result > 0){
                r = mid - 1 ;
            }
        }
        return -1;
    }
    public void sort(Dictionary dictionary)
    {
        Collections.sort(dictionary,(o1, o2) -> o1.getWord_target().compareTo(o2.getWord_target()));
    }
}

