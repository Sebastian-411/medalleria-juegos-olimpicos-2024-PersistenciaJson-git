package model;

import com.google.gson.Gson;
import exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    private ArrayList<CountryReport> countries;
    private String folder = "data";
    private String path = "data/data.txt";
    public Controller() {
        countries = new ArrayList<>();
    }

    public boolean addCountry(String data) throws InvalidSyntaxException {
        String[] tmp;
        int medals;
        try {
            tmp = data.split("::");
            medals = Integer.parseInt(tmp[2]);
        } catch (Exception e){
            throw new InvalidSyntaxException();
        }
        for(CountryReport country : countries){
            if ( country.getName().equalsIgnoreCase(tmp[0])){
                return country.countMedals(tmp[1], medals);
            }
        }
        CountryReport c = new CountryReport(tmp[0]);
        return c.countMedals(tmp[1], medals) && countries.add(c);
    }

    public String showCountriesMedals(){
        String tmp = String.format("| %-20s | %-20s | %-20s | %-20s | %-20s |%n", "Pais", "Medallas de Oro", "Medallas de Plata", "Medallas de Bronce", "Total medallas");
        Collections.sort(countries);
        for (CountryReport c : countries){
            tmp += String.format("| %-20s | %-20d | %-20d | %-20d | %-20d |%n", c.getName(), c.getGoldMedal(), c.getSilverMedal(), c.getBronzeMedal(), c.getTotalMedal());
        }
        return tmp;
    }

    public String showCountriesTotalMedals(){
        String tmp = String.format("| %-20s | %-20s%n", "Pais", "Total medallas");
        Collections.sort(countries, (a, b)-> {
            int c1 = a.getTotalMedal() - b.getTotalMedal();
            if(c1 == 0){
                return a.getName().compareToIgnoreCase(b.getName())*-1;
            }
            return c1*-1;
        });
        for (CountryReport c : countries){
            tmp += String.format("| %-20s | %-20d%n", c.getName(), c.getTotalMedal());
        }
        return tmp;
    }

    public String showCountriesABC(){
        String tmp = String.format("| %-20s | %-20s | %-20s | %-20s | %-20s |%n", "Pais", "Medallas de Oro", "Medallas de Plata", "Medallas de Bronce", "Total medallas");
        ArrayList<CountryReport> tmp1 = insertionSort(countries);
        for (CountryReport c : tmp1){
            tmp += String.format("| %-20s | %-20d | %-20d | %-20d | %-20d |%n", c.getName(), c.getGoldMedal(), c.getSilverMedal(), c.getBronzeMedal(), c.getTotalMedal());
        }
        return tmp;
    }

    private ArrayList<CountryReport> insertionSort(ArrayList<CountryReport> c){
        for(int i = 1; i< c.size(); i++){
            for(int j = 0; j<i; j++){
                if(c.get(i).getName().compareToIgnoreCase(c.get(j).getName())<0){
                    c.add(j, c.remove(i));
                }
            }
        }
        return c;
    }

    public void load() throws IOException {
        File file = new File(path);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String content = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
            Gson gson = new Gson();
            CountryReport[] array = gson.fromJson(content, CountryReport[].class);
            Collections.addAll(countries, array);
            fis.close();
        }else{
            File f = new File(folder);
            if(!f.exists()){
                f.mkdirs();
            }
            file.createNewFile();
        }
    }

    public void save() throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);

        Gson gson = new Gson();
        String data = gson.toJson(countries);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(data);
        writer.flush();
        fos.close();
    }


}
