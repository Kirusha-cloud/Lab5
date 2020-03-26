package Lab5.FlatUnderClasses;

/**
 * Служебный класс, необходимый для создания экземпляра класса Flat.
 */
public class House {
    private String name; //Поле не может быть null
    private Long year; //Значение поля должно быть больше 0
    private int numberOfLifts; //Значение поля должно быть больше 0

    public House(String name, Long year, int numberOfLifts) {
        this.name = name;
        this.year = year;
        this.numberOfLifts = numberOfLifts;
    }
}
