package ui;

import java.io.IOException;
import java.util.Scanner;

import exceptions.*;
import model.*;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private Controller controller = new Controller();

    public static void main(String[] args) throws IOException{
        Main m = new Main();
        m.load();
        while (true) {
            m.menu();
        }
    }
    public void load() throws IOException {
        controller.load();
    }
    public void menu() throws IOException{
        System.out.println("Menu\n" +
                "\t1. Ingresar un país \n" +
                "\t2. Mostrar medallería\n" +
                "\t3. Mostrar total de medallas\n" +
                "\t4. Mostrar países\n" +
                "\t5. Salir");
        switch (Integer.parseInt(sc.nextLine())){
            case 1:
                addCountry();
                controller.save();
                break;
            case 2:
                System.out.println(controller.showCountriesMedals());
                break;
            case 3:
                System.out.println(controller.showCountriesTotalMedals());
                break;
            case 4:
                System.out.println(controller.showCountriesABC());
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Por favor, seleccione una opcion valida");

        }
    }

    public void addCountry() {
        try {
            System.out.println("Has seleccionado la opcion \"Ingresar un pais\"" +
                    "\n \t Ingresa el pais en este formato: PAIS::TIPO DE MEDALLA::CANTIDAD DE MEDALLAS" +
                    "\n \t Recuerda que estos son los tipos de medalla validos: Oro, plata y bronce");
            String data = sc.nextLine();
            if ( controller.addCountry(data) ){
                System.out.println("Los datos han sido agregados correctamente");
            }
        } catch (InvalidSyntaxException e){
            e.printStackTrace();
        }

    }
}