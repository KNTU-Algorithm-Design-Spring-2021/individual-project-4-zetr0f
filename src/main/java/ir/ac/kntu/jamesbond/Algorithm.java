package ir.ac.kntu.jamesbond;

import java.util.*;

public class Algorithm {
    public static Validator validator;

    static {
        validator = new Validator("C:\\Users\\ASUS\\Desktop\\algorithm design\\" +
                "project3\\individual-project-3-zetr0f\\src\\main\\resources\\dic.txt");
    }


    public static List<String> findWords(String s, Validator validator, Map<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        List<String> result = new ArrayList<>();
        if (s.length() != 0 && validator.valid(s)) {
            result.add(s);
        }

        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            if (validator.valid(prefix)) {
                List<String> returnStringsList = findWords(s.substring(i), validator, map);
                for (String returnString : returnStringsList) {
                    result.add(prefix + " " + returnString);
                }
            }
        }
        map.put(s, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("enter ur string to check :");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, List<String>> map = new HashMap<>();
        System.out.println(findWords(input, validator, map).toString());
    }
}
