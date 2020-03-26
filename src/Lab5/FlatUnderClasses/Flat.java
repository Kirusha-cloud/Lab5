package Lab5.FlatUnderClasses;

import java.util.Date;

/**
 * Класс, коллекцией экземпляров которого управляет программа.
 */
public class Flat implements Comparable<Flat> {
    private Integer id; // Не может быть null, значение > 0 + уникальное + генерируется автоматически
    private String name; // Не может быть null, строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private java.util.Date creationDate; // Поле не может быть null, значение генерируется автоматически
    private long area; // Значение > 0
    private Long numberOfRooms; // Значение > 0
    private boolean furniture;
    private View view; // Поле не может быть null
    private Transport transport; // Поле не может быть null
    private House house; // Поле не может быть null

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public Long getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public boolean isFurniture() {
        return furniture;
    }

    public void setFurniture(boolean furniture) {
        this.furniture = furniture;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    /**
     * Метод, реализующий сортировку по умолчанию.
     */
    @Override
    public int compareTo(Flat o) {
        return Integer.parseInt(o.getId().toString()) - Integer.parseInt(this.getId().toString());
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + " Id: " + this.getId();
    }
}
