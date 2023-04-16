package model;

import java.util.Comparator;

public class CountryReport implements Comparable<CountryReport>{
    private String name;
    private int goldMedal;
    private int silverMedal;
    private int bronzeMedal;

    private int totalMedal;

    public CountryReport(String name) {
        this.name = name;
        this.goldMedal = 0;
        this.silverMedal = 0;
        this.bronzeMedal = 0;
        this.totalMedal = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoldMedal() {
        return goldMedal;
    }

    public void setGoldMedal(int goldMedal) {
        this.goldMedal = goldMedal;
    }

    public int getSilverMedal() {
        return silverMedal;
    }

    public void setSilverMedal(int silverMedal) {
        this.silverMedal = silverMedal;
    }

    public int getBronzeMedal() {
        return bronzeMedal;
    }

    public void setBronzeMedal(int bronzeMedal) {
        this.bronzeMedal = bronzeMedal;
    }

    public int getTotalMedal() {
        return totalMedal;
    }

    public void setTotalMedal(int totalMedal) {
        this.totalMedal = totalMedal;
    }

    @Override
    public int compareTo(CountryReport o) {
        int c1 = this.goldMedal-o.getGoldMedal();
        if(c1 == 0){
            c1 = this.silverMedal-o.getSilverMedal();
            if(c1 == 0){
                c1 = this.bronzeMedal-o.getBronzeMedal();
                if(c1 == 0) {
                    return this.name.compareToIgnoreCase(o.getName());
                }
            }
        }
        return -1*c1;
    }

    public boolean countMedals(String type, int medals){
        if(type.equalsIgnoreCase("oro")){
            this.goldMedal += medals;
        } else if ( type.equalsIgnoreCase("plata" )){
            this.silverMedal += medals;
        } else if ( type.equalsIgnoreCase("bronce" )){
            this.silverMedal += medals;
        } else {
            return false;
        }
        this.totalMedal += medals;
        return true;
    }

    @Override
    public String toString() {
        return "CountryReport{" +
                "name='" + name + '\'' +
                ", goldMedal=" + goldMedal +
                ", silverMedal=" + silverMedal +
                ", bronzeMedal=" + bronzeMedal +
                ", totalMedal=" + totalMedal +
                '}';
    }
}
