package Lab5.ProgrammOrg;

import Lab5.FlatUnderClasses.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс, отвечающий за заполнение полей экземпляра класса Flat с возможностью ввода допустимых полей с клавиатуры.
 */
class Filler {
    private Flat flat;
    private Scanner cin = new Scanner(System.in);

    Filler(Flat flat) {
        this.flat = flat;
    }

    String fillName() {
        System.out.println("Какое название у квартиры:");
        String name = cin.nextLine();
        try {
            if (name == null || name.equals(""))
                throw new InputMismatchException();
        } catch (InputMismatchException inputExceptions) {
            System.out.println("Некорректный ввод, введите название заново!");
            fillName();
        }
        return name;
    }

    Coordinates fillCoordinates() throws InputMismatchException {
        try {
            System.out.println("Введите координату X квартиры: " + "(Она должна быть больше -897)");
            int coordinateX = cin.nextInt();
            System.out.println("Введите координату У квартиры: ");
            double coordinateY = cin.nextDouble();
            if (coordinateX <= -897 || Integer.valueOf(coordinateX).equals(-0)) {
                throw new IOException();
            }
            return new Coordinates(coordinateX, coordinateY);
        } catch (IOException e) {
            System.out.println("Некорректный ввод. Введите координаты заново!");
            fillCoordinates();
            return null;
        }
        catch (InputMismatchException e){
            System.out.println("Некорректный ввод. Введите координаты заново!");
            cin.nextLine();
            fillCoordinates();
            return null;
        }
    }

    long fillArea() {
        System.out.println("Введите area квартиры: (area должна быть больше 0)");
        try {
            long area = cin.nextLong();
            if (area <= 0)
                throw new IOException();
            return area;
        } catch (IOException e) {
            System.out.println("Некорректный ввод, введите заново!");
            fillArea();
            return 0;
        }
          catch (InputMismatchException e){
              System.out.println("Некорректный ввод, введите заново!");
              cin.nextLine();
              fillArea();
              return 0;
          }
    }

    Long fillNumberOfRooms() {
        System.out.println("Сколько комнат в квартире: (Число комнат должно быть больше 0)");
        try {
            Long nof = cin.nextLong();
            if (nof <= 0)
                throw new IOException();
            return nof;
        } catch (IOException e) {
            System.out.println("Некорректный ввод, введите число комнат заново!");
            fillNumberOfRooms();
            return 0L;
        }
        catch (InputMismatchException e){
            System.out.println("Некорректный ввод, введите число комнат заново!");
            cin.nextLine();
            fillNumberOfRooms();
            return 0L;
        }
    }

    boolean fillFurniture() {
        try {
            System.out.println("Введите мебель, которая находится в квартире: (True/False)");
            return cin.nextBoolean();
        }
        catch (InputMismatchException e){
            System.out.println("Неверный ввод, введите значение поля мебель заново!");
            //cin.skip(cin.nextLine());
            cin.nextLine();
            fillFurniture();
            return false;
        }
    }

    View fillView() {
        System.out.println("Введите вид, который открывается из окон квартиры: ");
        System.out.println("Список доступных вариантов: ");
        for (View s : View.values()) {
            System.out.println(s);
        }
        try {
            return View.valueOf(cin.next());
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Некорректный ввод, введите заново!");
            cin.nextLine();
            fillView();
            return null;
        }
    }

    Transport fillTransport() {
        System.out.println("Введите один из вариантов количества транспорта, который проезжает мимо квратиры: ");
        System.out.println("Список доступных вариантов: ");
        for (Transport s : Transport.values()) {
            System.out.println(s);
        }
        try {
            return Transport.valueOf(cin.next());
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Некорректный ввод, введите заново!");
            cin.nextLine();
            fillTransport();
            return null;
        }
    }

    House fillHouse() {
        try {
            System.out.println("Необходимо ввести данные дома, в к котором находится квартира.");
            System.out.println("Введите название дома: ");
            String name = cin.next();
            System.out.println("Введите год, в котором дом был построен: (год должен быть больше 0)");
            Long year = cin.nextLong();
            System.out.println("Введите число лифтов, находящихся в доме: (число лифтов должно быть больше 0" );
            int lifts = cin.nextInt();
            House house = new House(name, year, lifts);
            if (name == null || year <= -0 || lifts <= -0 || name.equals(""))
                throw new IOException();
            return house;
        } catch (IOException e) {
            System.out.println("Некорректный ввод, введите данные для дома заново!");
            cin.nextLine();
            fillHouse();
            return null;
        }
        catch (InputMismatchException e){
            System.out.println("Некорректный ввод, введите заново!");
            cin.nextLine();
            fillHouse();
            return null;
        }
    }
}
