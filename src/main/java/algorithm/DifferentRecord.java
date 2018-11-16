package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DifferentRecord {
    public String[] reorderLogFiles(String[] logs) {
        List<StringLog> sLog = new ArrayList<>();
        List<String> nLog = new ArrayList<>();
        for(String log : logs){
            if(isStringLog(log)){
                sLog.add(new StringLog(log));
            }
            else {
                nLog.add(log);
            }
        }

        Collections.sort(sLog);
        String[] result = new String[logs.length];

        int loc = 0;
        for(StringLog stringLog : sLog){
            result[loc++] = stringLog.toString();
        }

        for(String numberLog : nLog){
            result[loc++] = numberLog;
        }

        return result;
    }

    private boolean isStringLog(String log) {
        char c = log.split(" ")[1].charAt(0);
        return c >= 'a' && c <= 'z';
    }
}

class StringLog implements Comparable<StringLog> {
    String identifier;
    String content;

    public StringLog(String s){
        identifier = s.split(" ")[0];
        content = s.substring(identifier.length() + 1);
    }
    @Override
    public int compareTo(StringLog o) {
        int contentResult = content.compareTo(o.content);
        return contentResult == 0 ? identifier.compareTo(o.identifier) : contentResult;
    }

    @Override
    public String toString() {
        return identifier + " " + content;
    }
}