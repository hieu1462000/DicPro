package Commandline;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//class DictionaryCommandLine {
//    Scanner s = new Scanner(System.in);
//
//    public void showAllWords(Dictionary d) {
//        System.out.printf("%-5s%-20s%-20s\n", "No", "English", "Vietnameses");
//        for (int i = 0; i < d.arrWords.size(); i++) {
//            System.out.printf("%-5d%-20s%-20s\n", i + 1, d.arrWords.get(i).getWord_target(), d.arrWords.get(i).getWord_explain());
//        }
//    }
//
//    public void dictionaryBasic(DictionaryManagement d1, Dictionary d) {
//        System.out.println("Nhap cac su lua chon");
//        System.out.println("0.Exit");
//        System.out.println("1.Add Words");
//        System.out.println("2.Show all words");
//        int c = s.nextInt();
//        switch (c) {
//            case 1:
//            {
//                d1.insertFromCommandline();
//                break;
//            }
//            case 0: {
//                System.out.println("Exiting");
//                break;
//            }
//            case 2: {
//                this.showAllWords(d);
//                break;
//            }
//        }
//    }
//    public void dictionaryAdvanced(DictionaryManagement d1,Dictionary d2) throws IOException
//    {
//        System.out.println("Nhap cac su lua chon");
//        System.out.println("0.Exit");
//        System.out.println("1.insertFromFile");
//        System.out.println("2.Show all words");
//        System.out.println("3.Lookup");
//        int c = s.nextInt();
//        s.nextLine();
//        switch (c) {
//            case 1:
//            {
//                d2=d1.insertFromFile();
//                break;
//            }
//            case 0: {
//                System.out.println("Exiting");
//                break;
//            }
//            case 2: {
//                this.showAllWords(d2);
//                break;
//            }
//            case 3:
//            {
//                d1.dictionaryLookup(d2);
//                break;
//            }
//        }
//    }
//    public void dictionarySearcher(Dictionary dictionary)
//    {
//        String a="";
//        System.out.println("Nhap tu can tim :");
//        a= s.nextLine();
//        int n=0;
//        System.out.printf("%-5s%-20s%-20s\n", "No", "English", "Vietnameses");
//        for(int i=0;i<dictionary.arrWords.size();i++)
//        {
//            if(dictionary.arrWords.get(i).getWord_target().startsWith(a))
//            {
//                System.out.printf("%-5d%-20s%-20s\n", n + 1, dictionary.arrWords.get(i).getWord_target(), dictionary.arrWords.get(i).getWord_explain());
//                n++;
//            }
//        }
//    }
//}
public class DictionaryCommandLine{
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.insertFromFile(dictionary, "DICTIONARY/src/data.txt");
    }
}