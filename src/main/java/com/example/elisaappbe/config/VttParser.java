package com.example.elisaappbe.config;

import com.example.elisaappbe.model.EnglishSubtitle;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VttParser {

    // Regex để bắt định dạng thời gian: 00:00:01.500
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{3})");

    public static List<EnglishSubtitle> parse(String vttContent) {
        List<EnglishSubtitle> subs = new ArrayList<>();
        String[] lines = vttContent.split("\n");

        long currentStart = 0;
        long currentEnd = 0;
        boolean isTimeLine = false;

        for (String line : lines) {
            line = line.trim();
            if (line.contains("-->")) {
                // Đây là dòng chứa thời gian
                String[] parts = line.split(" --> ");
                if (parts.length == 2) {
                    currentStart = parseTime(parts[0]);
                    currentEnd = parseTime(parts[1]);
                    isTimeLine = true;
                }
            } else if (isTimeLine && !line.isEmpty()) {
                // Đây là dòng nội dung ngay sau dòng thời gian
                EnglishSubtitle sub = new EnglishSubtitle();
                sub.setStartTime(currentStart);
                sub.setEndTime(currentEnd);
                sub.setContent(line);
                subs.add(sub);
                isTimeLine = false; // Reset chờ dòng thời gian tiếp theo
            }
        }
        return subs;
    }

    // Chuyển "00:01:02.500" -> mili-giây (Long)
    private static long parseTime(String timeString) {
        Matcher m = TIME_PATTERN.matcher(timeString);
        if (m.find()) {
            long hours = Long.parseLong(m.group(1));
            long minutes = Long.parseLong(m.group(2));
            long seconds = Long.parseLong(m.group(3));
            long millis = Long.parseLong(m.group(4));
            return (hours * 3600000) + (minutes * 60000) + (seconds * 1000) + millis;
        }
        return 0;
    }
}