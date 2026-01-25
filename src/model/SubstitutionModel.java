package model;

import java.util.*;

public class SubstitutionModel {

    public String encrypt(String msg, String key) {

        Map<Character, Character> keyMap = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            char origU = (char) ('A' + i);
            char origL = (char) ('a' + i);
            char mappedU = Character.toUpperCase(key.charAt(i));
            char mappedL = Character.toLowerCase(key.charAt(i));
            keyMap.put(origU, mappedU);
            keyMap.put(origL, mappedL);
        }

        StringBuilder output = new StringBuilder();
        for (char c : msg.toCharArray()) {
            output.append(keyMap.getOrDefault(c, c));
        }
        return output.toString();
    }

    public String decrypt(String msg, String key) {

        Map<Character, Character> reverseMap = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            char codeU = Character.toUpperCase(key.charAt(i));
            char codeL = Character.toLowerCase(key.charAt(i));
            reverseMap.put(codeU, (char) ('A' + i));
            reverseMap.put(codeL, (char) ('a' + i));
        }

        StringBuilder output = new StringBuilder();
        for (char c : msg.toCharArray()) {
            output.append(reverseMap.getOrDefault(c, c));
        }
        return output.toString();
    }

    public boolean isValidKey(String key) {
        if (key == null || key.length() != 26) return false;
        key = key.toLowerCase();
        return key.chars().distinct().count() == 26 && key.chars().allMatch(Character::isLetter);
    }

    public String generateRandomKey() {
        List<Character> alphabetList = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabetList.add(c);
        }
        Collections.shuffle(alphabetList);
        StringBuilder keyBuilder = new StringBuilder();
        for (char c : alphabetList) {
            keyBuilder.append(c);
        }
        return keyBuilder.toString();
    }
}
