package cvBuilder;

import java.util.ArrayList;

public class TelegramMessages {
   public String name;
   public String type;
   public int id;
   public ArrayList<Message> messages;

    public static class Message {
        public int id;
        public String type;
        public String date;
        public String date_unixtime;
        public String edited;
        public String edited_unixtime;
        public String from;
        public String from_id;
//        public ArrayList<Text> text;
        public ArrayList<TextEntity> text_entities;
//        public ArrayList<Object> text;
//        public ArrayList<Object> text_entities;
//        public Object[] text;
//        public Object[] text_entities;
//        public String[] text;
//        public String[] text_entities;

    }

    public static class Text {
        public String ordinaryText;
        public ArrayList<InTextObject> inTextObjects = new ArrayList<>();
    }

    public static class InTextObject {
        public String type;
        public String text;
        public String href;
    }

    public static class TextEntity {
        public String type;
        public String text;
        public String href;
    }
}


