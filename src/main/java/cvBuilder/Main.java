package cvBuilder;

import java.io.*;
import java.util.*;
import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<String> parsedMessages = collectAllMessages();
        ArrayList<String> filteredMessages = splitAndFilterCollectedMessages(parsedMessages);
        HashMap<String, Integer> rangedWords = rangeAllWordsInMessages(filteredMessages);
        System.out.println(rangedWords.size());
        printRating(rangedWords);
    }

    public static ArrayList<String> collectAllMessages() throws IOException {
        String jsonPath = "C:\\Nikita_Java\\jsonParseCVs\\jsonParseCVs\\src\\main\\resources\\result.json";
        Gson gson = new Gson();
        Reader reader = new FileReader(jsonPath);
        TelegramMessages archive = gson.fromJson(reader, TelegramMessages.class);
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < archive.messages.size(); i++) {
            for (int j = 0; j < archive.messages.get(i).text_entities.size(); j++) {
                if (archive.messages.get(i).text_entities.get(j).type.equals("plain")) {
                    result.add(archive.messages.get(i).text_entities.get(j).text);
                }
            }
        }
        return result;
    }

    public static ArrayList<String> splitAndFilterCollectedMessages(ArrayList<String> list) {

        //Разбиваем String[] на части и добавляем его в ArrayList<String>;
        String[] wordByWord = list.toString().split(" | \n" );
        ArrayList<String> deserializedArray = new ArrayList<>();
        for (String s : wordByWord) {
            String filteredWord =
                    s.replaceAll("[\\[!,.\\n;:\")(•☑●✔]+", "")
                            .replaceAll("-$", "")
                            .replaceAll("^-", "");
            if (filteredWord.length() > 2) deserializedArray.add(filteredWord.toLowerCase());
        }
        return deserializedArray;
    }

    public static HashMap<String, Integer> rangeAllWordsInMessages(ArrayList<String> list) {
        HashMap<String, Integer> rangeTable = new HashMap<>();
        for (String s : list) {
            if (rangeTable.containsKey(s)) {
                int previousValue = rangeTable.get(s);
                rangeTable.put(s, previousValue + 1);
            } else {
                rangeTable.put(s, 1);
            }
        }
            return rangeTable;
        }

    public static void printRating(HashMap<String, Integer> map) {
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(System.out::println);
    }

}