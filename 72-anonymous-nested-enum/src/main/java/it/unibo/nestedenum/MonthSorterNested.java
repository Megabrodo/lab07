package it.unibo.nestedenum;

import java.util.Comparator;
//import java.util.Locale;
//import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month {
        JANUARY("january", 31),
        FEBRUARY("february", 28),
        MARCH("march", 31),
        APRIL("april", 30),
        MAY("may", 31),
        JUNE("june", 30),
        JULY("july", 31),
        AUGUST("august", 31),
        SEPTEMBER("september",30),
        OCTOBER("october", 31),
        NOVEMBER("november",30),
        DECEMBER("december",31);

        private final String actualName;
        private final int days;

        private Month(final String actualName, final int days){
            this.actualName=actualName;
            this.days=days;
        }

        public static Month fromString(String name){
            if (name == null){
                throw new IllegalArgumentException("Name is null");
            }
           
            String s = name.toLowerCase();
            List<Month> months = new ArrayList<>();
            for (Month m : Month.values()){
                if (m.actualName.startsWith(s)){
                    months.add(m);
                }
            }
            if(months.isEmpty()){
                throw new IllegalArgumentException("No such month name");
            }
            else if (months.size()>1){
                throw new IllegalArgumentException("Ambiguous name");
            }
            else{
                return months.getFirst();
            }
        }
    }

    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            @Override
            public int compare(String name1, String name2){
                return Integer.compare(Month.fromString(name1).days, Month.fromString(name2).days);
            }
        };
    }

    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            @Override
            public int compare(String name1, String name2){
                return Integer.compare(Month.fromString(name1).ordinal(), Month.fromString(name2).ordinal());
            }
        };
    }
}
