package ir.ac.kntu.jamesbond;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Validator {
    String dictionaryURL;
    HashMap<String, Set<String>> dictionary;

    public Validator(String dictionaryURL) {
        this.dictionaryURL = dictionaryURL;
        this.dictionary = new HashMap<>();
        loadDictionary();
    }

    private void loadDictionary() {
        Scanner scanner = null;
        String tempSoundex;
        HashSet<String> wordSet;
        try {
            scanner = new Scanner(new FileInputStream(dictionaryURL));
            while(scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                tempSoundex = soundex(word.toLowerCase());
                wordSet = (HashSet<String>) dictionary.get(tempSoundex);
                if (word.length() < 2) {
                    continue;
                }
                if (wordSet != null) {
                    wordSet.add(word.toLowerCase());
                } else {
                    wordSet = new HashSet<>();
                    wordSet.add(word.toLowerCase());
                }
                dictionary.put(soundex(word.toLowerCase()), wordSet);

            }
        } catch(IOException e) {
            System.err.println(e.toString());
            e.printStackTrace();
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }
    }

    private static String getCode(char c){
        switch(c){
            case 'B': case 'F': case 'P': case 'V':
                return "1";
            case 'C': case 'G': case 'J': case 'K':
            case 'Q': case 'S': case 'X': case 'Z':
                return "2";
            case 'D': case 'T':
                return "3";
            case 'L':
                return "4";
            case 'M': case 'N':
                return "5";
            case 'R':
                return "6";
            default:
                return "";
        }
    }

    public static String soundex(String s){
        String code, previous, soundex;
        code = s.toUpperCase().charAt(0) + "";
        previous = "7";
        for(int i = 1;i < s.length();i++){
            String current = getCode(s.toUpperCase().charAt(i));
            if(current.length() > 0 && !current.equals(previous)){
                code = code + current;
            }
            previous = current;
        }
        soundex = (code + "0000").substring(0, 4);
        return soundex;
    }

    public boolean valid(String word) {
        HashSet<String> wordSet = (HashSet<String>) dictionary.get(soundex(word.toLowerCase()));
        if (wordSet == null){
            return false;
        } else {
            return wordSet.contains(word.toLowerCase());
        }
    }
}
