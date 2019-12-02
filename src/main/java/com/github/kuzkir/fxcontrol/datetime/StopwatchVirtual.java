/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kuzkir.fxcontrol.datetime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author kuzkir
 */
public class StopwatchVirtual {

    private final Map<String, Long> startMap;
    private final Map<String, Long> stopMap;
    private final Map<String, List<Long>> lapMap;

    public StopwatchVirtual() {
        startMap = new ConcurrentHashMap<>();
        stopMap = new ConcurrentHashMap<>();
        lapMap = new ConcurrentHashMap<>();
    }

    public void clear() {
        startMap.clear();;
        stopMap.clear();
        lapMap.clear();
    }

    public void remove(String... stopwatchIds) {
        for (String id : stopwatchIds) {
            startMap.remove(id);
            stopMap.remove(id);
            lapMap.remove(id);
        }
    }

    public void start(String... stopwatchIds) {
        long time = Calendar.getInstance().getTimeInMillis();

        for (String id : stopwatchIds) {
            startMap.put(id, time);
            stopMap.put(id, Long.MIN_VALUE);
        }
    }

    public void stop(String... stopwatchIds) {
        long time = Calendar.getInstance().getTimeInMillis();

        for (String id : stopwatchIds) {
            stopMap.put(id, time);
        }
    }

    public void lap(String... stopwatchIds) {
        long time = Calendar.getInstance().getTimeInMillis();

        for (String id : stopwatchIds) {
            stopMap.put(id, time);
            if (lapMap.containsKey(id)) {
                lapMap.get(id).add(time);
            } else {
                List<Long> laps = new ArrayList<>();
                laps.add(time);
                lapMap.put(id, laps);
            }
        }
    }

    public long get(String stopwatchId) {
        if (!startMap.containsKey(stopwatchId)) {
            return 0;
        }

        Long start = startMap.get(stopwatchId);
        Long stop = stopMap.get(stopwatchId);

        if (stop.equals(Long.MIN_VALUE)) {
            return Calendar.getInstance().getTimeInMillis() - start;
        }
        return stop - start;
    }

    public List<Long> getLaps(String stopwatchId) {
        if (!startMap.containsKey(stopwatchId) || !lapMap.containsKey(stopwatchId)) {
            return new ArrayList<Long>();
        }
        return lapMap.get(stopwatchId);
    }

    public String getFormated(String stapwatchId, FormatProperties prop) {
        return prop.getFormat(get(stapwatchId));
    }

    public static class FormatProperties {

        private boolean leadZero;
        private boolean showNano;
        private String separator;
        private LeadPart leadPart;

//        public static final String LEAD_SECOND = "second";
//        public static final String LEAD_MINUTE = "minute";
//        public static final String LEAD_HOUR = "hour";
//        public static final String LEAD_DAY = "day";
        public FormatProperties() {
            leadZero = true;
            showNano = false;
            separator = ":";
            leadPart = LeadPart.MINUTE;
        }

        public boolean isLeadZero() {
            return leadZero;
        }

        public boolean isShowNano() {
            return showNano;
        }

        public String getSeparator() {
            return separator;
        }

        public LeadPart getLeadPart() {
            return leadPart;
        }

        public FormatProperties setLeadZero(boolean value) {
            leadZero = value;
            return this;
        }

        public FormatProperties setShowNano(boolean value) {
            showNano = value;
            return this;
        }

        public FormatProperties setSeparator(String value) {
            separator = value;
            return this;
        }

        public FormatProperties setLeadPart(LeadPart value) {
            leadPart = value;
            return this;
        }

        private String getFormat(long millis) {
            int nano = (int) millis % 1000;
            long sec = millis / 1000;

            if (leadPart.equals(LeadPart.SECOND)) {
                return (leadZero
                    ? String.format("%02d", sec)
                    : String.format("%d", sec))
                    + (showNano
                        ? String.format(".%03d", nano)
                        : "");
            }
            long min = sec / 60;
            sec = sec % 60;

            if (leadPart.equals(LeadPart.MINUTE)) {
                return (leadZero
                    ? String.format("%02d%s%02d", min, separator, sec)
                    : String.format("%d%s%d", min, separator, sec))
                    + (showNano
                        ? String.format(".%03d", nano)
                        : "");
            }
            long hour = min / 60;
            min = min % 60;

            if (leadPart.equals(LeadPart.HOUR)) {
                return (leadZero
                    ? String.format("%02d%s%02d%s%02d", hour, separator, min, separator, sec)
                    : String.format("%d%s%d%s%d", hour, separator, min, separator, sec))
                    + (showNano
                        ? String.format(".%03d", nano)
                        : "");
            }
            long day = hour / 24;
            hour = hour % 24;

            if (leadPart.equals(LeadPart.DAY)) {
                return (leadZero
                    ? String.format("%d%s%02d%s%02d%s%02d", day, separator, hour, separator, min, separator, sec)
                    : String.format("%d%s%d%s%d%s%d", day, separator, hour, separator, min, separator, sec))
                    + (showNano
                        ? String.format(".%03d", nano)
                        : "");
            }

            return "";
        }

    }

    public enum LeadPart {
        SECOND, MINUTE, HOUR, DAY
    }

}
