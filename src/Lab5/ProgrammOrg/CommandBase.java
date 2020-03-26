package Lab5.ProgrammOrg;

import Lab5.FlatUnderClasses.*;

import Lab5.Parser.Parser;

import java.io.*;

import java.util.ArrayList;

import java.util.Collections;

import static Lab5.Parser.Sources.path_out;

import java.util.*;

/**
 * Класс, реализующий логику команд.
 */
public class CommandBase {
    private ArrayList<String> commandList = new ArrayList<>();
    private LinkedList<String> commandQueue = new LinkedList<>();
    private PriorityQueue<Flat> p;
    private Random random = new Random();
    private Set<Integer> setId = new TreeSet<>();
    private HashSet<String> paths = new HashSet<>();
    private ArrayList<Long> numberOfRoomsList = new ArrayList<>();
    private Long minNumberOfRooms;
    private Long maxNumberOfRooms;
    private Flat s;
    private ArrayList<Flat> badObj = new ArrayList<>();


    public CommandBase(PriorityQueue<Flat> p) {
        try {
            this.p = p;
            if(!fieldChecker()) {
                System.out.println("Файл имеет неверные данные! Обновите значение данного элемента!");
                for (Flat flat : badObj) {
                    updateBadObj(flat);
                }
            }
            if (p.size() == 0) {
                minNumberOfRooms = 100000000000000L;
                maxNumberOfRooms = -1L;
            } else {
                minNumberOfRooms = p.peek().getNumberOfRooms();
                maxNumberOfRooms = p.peek().getNumberOfRooms();
            }
            Collections.addAll(commandList, "add", "add_if_max", "clear", "execute_script", "exit",
                    "group_counting_by_transport", "help", "history", "info", "min_by_furniture", "print_field_number_of_rooms",
                    "remove_by_id", "remove_greater", "save", "show", "update_id");
        } catch (NullPointerException e) {
            System.out.println("Что-то пошло не так...");
        }
    }

    public HashSet<String> getPaths() {
        return paths;
    }

    /**
     * Метод, отвечающий за корректировку экземпляров Flat, значение полей которых null.
     * @param flat - экземпляр Flat
     */
    private void updateBadObj(Flat flat){
        Filler filler = new Filler(flat);
        if (flat.getName() == null || flat.getName().equals(""))
            filler.fillName();
        if (flat.getCoordinates() == null)
            filler.fillCoordinates();
        if (flat.getCreationDate() == null)
            flat.setCreationDate(new java.util.Date());
        if (flat.getView() == null)
            filler.fillView();
        if (flat.getTransport() == null)
            filler.fillTransport();
        if (flat.getHouse() == null)
            filler.fillHouse();
    }
    private boolean fieldChecker() {
        boolean flag = true;
        for (Flat flat : p) {
                if (flat.getView() == null || flat.getId() == null || flat.getName() == null || flat.getName().equals("")
                || flat.getCoordinates() == null || flat.getTransport() == null || flat.getHouse() == null ||
                    flat.getCreationDate() == null)
                    flag = false;
                    badObj.add(flat);
        }
        return flag;
    }

    private void addToHistory(String command) {
        if (commandQueue.size() == 13) {
            commandQueue.removeLast();
            commandQueue.addFirst(command);
        } else commandQueue.addFirst(command);
    }

    public void add(String[] args) {

        p.add(creationFlat(args));
        addToHistory("add");
        System.out.println("Элемент успешно добавлен в коллекцию.");
    }

    public void addIfMax(String[] args) {
        addToHistory("add_if_max");
        Flat element = creationFlat(args);
        finderMaxNOF();
        if (element.getNumberOfRooms() > maxNumberOfRooms) {
            p.add(element);
            System.out.println("Элемент успешно добавлен в коллекцию.");
        } else System.out.println("Элемент не добавлен в коллекцию.");

    }

    public void clear() {
        addToHistory("clear");
        System.out.println("Коллекция успешно отчищена.");
        p.clear();
    }

    /**
     * Метод, реализующий считывание с файла, добавления пути в Set, проверку есть ли в файле следующий скрипт.
     *
     * @param path  - путь до файла
     * @param paths - Set, хранящий пути до файлов
     */
    public void executeScript(String path, HashSet<String> paths) {
        //execute_script C:\IdeaProj\Lab5\JsonFile\Script2.txt
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Scanner input = new Scanner(file);
            paths.add(path);
            while (input.hasNextLine()) {
                String[] args = input.nextLine().split(" ");
                if (args[0].equals("execute_script")) {
                    if (!paths.contains(args[1])) {
                        scriptRunner(args);
                        paths.clear();
                        paths.add(path);
                    } else
                        System.out.println("Скрипт вызывает зацикливание, и не может быть выполнен!");
                } else
                    scriptRunner(args);
            }
            addToHistory("execute_script");
        } catch (Exception e) {
            String output = e.getMessage().replaceAll("\\(", "");
            output = output.replaceAll("\\)", "");
            System.out.println(output);
        }
    }

    /**
     * Метод, реализующий выполнение команды execute_script
     *
     * @param args - текущая строка из файла.
     * @throws IOException
     */
    private void scriptRunner(String[] args) throws IOException {
        switch (args[0]) {
            case "help":
                System.out.println("Команда help: ");
                help();
                break;
            case "add":
                System.out.println("Команда add: ");
                p.add(adder(args));
                System.out.println("Элемент успешно добавлен в коллекцию.");
                break;
            case "add_if_max":
                System.out.println("Команда add_if_max: ");
                finderMaxNOF();
                Flat element = adder(args);
                if (element.getNumberOfRooms() > maxNumberOfRooms) {
                    p.add(element);
                    System.out.println("Элемент успешно добавлен в коллекцию.");
                } else
                    System.out.println("Элемент не добавлен в коллекцию.");
                break;
            case "clear":
                System.out.println("Команда clear: ");
                clear();
                break;
            case "execute_script":
                executeScript(args[1], paths);
                break;
            case "exit":
                System.out.println("Команда exit: ");
                exit();
                break;
            case "group_counting_by_NOF":
                System.out.println("Команда group_counting_by_NOF : ");
                groupCountingByTransport();
                break;
            case "history":
                System.out.println("Команда history: ");
                history();
                break;
            case "info":
                System.out.println("Команда info: ");
                info();
                break;
            case "min_by_furniture":
                System.out.println("Команда min_by_furniture: ");
                minByFurniture();
                break;
            case "print_field_number_of_rooms":
                System.out.println("Команда print_field_number_of_rooms: ");
                printFieldNOF();
                break;
            case "remove_by_id":
                System.out.println("Команда remove_by_id: ");
                removeById(args);
                break;
            case "remove_greater":
                System.out.println("Команда remove_greater: ");
                Flat element1 = adder(args);
                Flat element0;
                Iterator<Flat> flatIterator = p.iterator();
                int flag = 0;
                if (p.size() == 0)
                    System.out.println("Коллекция пуста.");
                while (flatIterator.hasNext()) {
                    element0 = flatIterator.next();
                    if (element0.getNumberOfRooms() - element1.getNumberOfRooms() > 0) {
                        flatIterator.remove();
                        flag = 1;
                        System.out.println("Элемент " + element0.getName() + " успешно удалён.");
                    }
                }
                if (flag == 0) System.out.println("Все элементы коллекции меньше заданного.");
                break;
            case "save":
                System.out.println("Команда save: ");
                save();
                break;
            case "show":
                System.out.println("Команда show: ");
                show();
                break;
            case "update_id":
                System.out.println("Команда update_id: ");
                for (Flat element2 : p) {
                    if (element2.getId().equals(Integer.parseInt(args[1]))) {
                        element2.setName(args[2]);
                        element2.setCoordinates(new Coordinates(Integer.parseInt(args[3]),
                                Double.parseDouble(args[4])));
                        element2.setCreationDate(new java.util.Date());
                        element2.setArea(Long.parseLong(args[5]));
                        element2.setNumberOfRooms(Long.parseLong(args[6]));
                        element2.setFurniture(Boolean.parseBoolean(args[7]));
                        element2.setView(View.valueOf(args[8]));
                        element2.setTransport(Transport.valueOf(args[9]));
                        element2.setHouse(new House(args[10], Long.parseLong(args[11]),
                                Integer.parseInt(args[12])));
                        System.out.println("Элемент коллекции успешно обновлён.");
                    }
                }
                break;
            default:
                System.out.println("Вы ввели неверную команду! Для того, чтобы увидеть список команд введите \"help\".");
        }


    }

    public void exit() {
        addToHistory("exit");
        System.out.println("Завершение программы.");
        System.exit(0);
    }

    public void help() {
        addToHistory("help");
        System.out.println("Список доступных команды: ");
        for (String command : commandList) {
            System.out.println(command);
        }
    }

    public void history() {
        System.out.println("Последние 13 использованных команд: ");
        addToHistory("history");
        for (String s : commandQueue) {
            System.out.println(s);
        }
    }

    public void info() {
        System.out.println("Класс коллекции: " + p.getClass());
        System.out.println("Время создания коллеции: " + new Date());
        addToHistory("info");
    }

    public void minByFurniture() {
        System.out.print("Элемент коллекции, с минимальным значением комнат: ");
        addToHistory("min_by_furniture");
        for (Flat flat : p) {
            if (flat.getNumberOfRooms() <= minNumberOfRooms) {
                minNumberOfRooms = flat.getNumberOfRooms();
                s = flat;
            }
        }
        System.out.println(s.toString());
    }

    public void groupCountingByTransport() { //Few, None, Little, Normal, Enough
        List<Flat> fewTransport = new ArrayList<>();
        List<Flat> noneTransport = new ArrayList<>();
        List<Flat> littleTransport = new ArrayList<>();
        List<Flat> normalTransport = new ArrayList<>();
        List<Flat> enoughTransport = new ArrayList<>();
        addToHistory("group_counting_by_transport");
        for (Flat flat : p) {
            switch (flat.getTransport()) {
                case FEW:
                    fewTransport.add(flat);
                    break;
                case NONE:
                    noneTransport.add(flat);
                    break;
                case ENOUGH:
                    enoughTransport.add(flat);
                    break;
                case LITTLE:
                    littleTransport.add(flat);
                    break;
                case NORMAL:
                    normalTransport.add(flat);
                    break;
                default:
            }
        }
        System.out.println("Количество элементов с транспортом FEW:" + fewTransport.size());
        System.out.println("Количество элементов с транспортом NONE:" + noneTransport.size());
        System.out.println("Количество элементов с транспортом ENOUGH:" + enoughTransport.size());
        System.out.println("Количество элементов с транспортом LITTLE:" + littleTransport.size());
        System.out.println("Количество элементов с транспортом NORMAL:" + normalTransport.size());
    }

    public void printFieldNOF() {
        System.out.println("Вывести значения поля NumberOfRooms в порядке убывания: ");
        addToHistory("print_field_descending_number_of_rooms");
        for (Flat flat : p) {
            numberOfRoomsList.add(flat.getNumberOfRooms());
        }
        numberOfRoomsList.sort(Comparator.reverseOrder());
        for (Long l : numberOfRoomsList) {
            System.out.println(l);
        }
        numberOfRoomsList.clear();
    }

    public void removeById(String[] args) {
        int flag = 0;
        System.out.println("Удаление элемента коллекции по ID:");
        addToHistory("remove_by_id");
        Iterator<Flat> flatIterator = p.iterator();
        Flat element3;
        if (p.size() == 0)
            System.out.println("Коллекция пуста.");
        while (flatIterator.hasNext()) {
            element3 = flatIterator.next();
            if (element3.getId().equals(Integer.parseInt(args[1]))) {
                flatIterator.remove();
                flag = 1;
                System.out.println("Элемент " + element3.getName() + " успешно удалён.");
            }
        }
        if (flag == 0)
            System.out.println("Такого элемента нет в коллекции.");
    }

    public void removeGreater(String[] args) {
        addToHistory("remove_greater");
        Flat element = creationFlat(args);
        Flat element0;
        Iterator<Flat> flatIterator = p.iterator();
        int flag = 0;
        if (p.size() == 0) {
            System.out.println("Коллекция пуста.");
            flag = 1;
        }
        while (flatIterator.hasNext()) {
            element0 = flatIterator.next();
            if (element0.getNumberOfRooms() - element.getNumberOfRooms() > 0) {
                flatIterator.remove();
                flag = 1;
                System.out.println("Элемент " + element0.getName() + " успешно удалён.");
            }
        }
        if (flag == 0) System.out.println("Все элементы коллекции меньше заданного.");
        flag = 0;
    }

    public void save() throws IOException {
        try {
            addToHistory("save");
            OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(path_out));
            output.write(Parser.parsToJson(p));
            output.flush();
            output.close();
            System.out.println("Коллекция успешно сохранена.");
        } catch (FileNotFoundException e){
            System.out.println("У файла, хранящего коллекцию нет прав за запись. Добавьте права на запись и перезапустите программу.");
        }
}

    public void show() {
        addToHistory("show");
        if (p.size() != 0) {
            for (Flat flat : p) {
                System.out.println("Имя элемента: " + flat.getName() + " Id элемента: " + flat.getId());
            }
        } else System.out.println("Коллекция пуста.");
    }

    public void update(String[] args) {
        boolean flag = false;
        for (Flat element : p) {
            if (element.getId().equals(Integer.valueOf(args[1]))) {
                Filler fill = new Filler(element);
                fill.fillName();
                fill.fillNumberOfRooms();
                fill.fillArea();
                fill.fillCoordinates();
                fill.fillFurniture();
                fill.fillHouse();
                fill.fillView();
                fill.fillTransport();
                flag = true;
                break;
            }
        }
        if (flag)
            System.out.println("Элемент коллекции успешно обновлён.");
        else
            System.out.println("Такого элемента нет в коллекции.");
        addToHistory("update");
    }

    /**
     * Метод, который создаёт экземпляр класса FLat, при вводе каждого поля отдельно.
     *
     * @return Flat element
     */
    private Flat creationFlat(String[] args) {
        Flat element = new Flat();
        Filler filler = new Filler(element);
        element.setId(returnId());
        element.setName(filler.fillName());
        try {
            element.setCoordinates(filler.fillCoordinates());
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод. Введите координаты заново.");
            element.setCoordinates(filler.fillCoordinates());
        }
        element.setHouse(filler.fillHouse());
        element.setFurniture(filler.fillFurniture());
        element.setTransport(filler.fillTransport());
        element.setView(filler.fillView());
        element.setCreationDate(new java.util.Date());
        element.setNumberOfRooms(filler.fillNumberOfRooms());
        element.setArea(filler.fillArea());
        return element;
    }

    /**
     * Метод, генерирующий уникальное id для экземпляра коллекции.
     *
     * @return id
     */
    private Integer returnId() {
        Integer id = random.nextInt(100);
        while (setId.contains(id)) {
            id = random.nextInt(100);
        }
        setId.add(id);
        return id;
    }

    /**
     * Метод, который создаёт экземпляр класса Flat по введённым данным в одну строку.
     *
     * @param args - текущая строка из файла.
     * @return Flat element
     */
    private Flat adder(String[] args) {
        Flat element = new Flat();
        element.setId(returnId());
        element.setName(args[1]);
        element.setCoordinates(new Coordinates(Integer.parseInt(args[2]), Double.parseDouble(args[3])));
        element.setCreationDate(new java.util.Date());
        element.setArea(Long.parseLong(args[4]));
        element.setNumberOfRooms(Long.parseLong(args[5]));
        element.setFurniture(Boolean.parseBoolean(args[6]));
        element.setView(View.valueOf(args[7]));
        element.setTransport(Transport.valueOf(args[8]));
        element.setHouse(new House(args[9], Long.parseLong(args[10]), Integer.parseInt(args[11])));
        return element;
    }

    private void finderMaxNOF() {
        for (Flat flat : p) {
            if (flat.getNumberOfRooms() >= maxNumberOfRooms) {
                maxNumberOfRooms = flat.getNumberOfRooms();
            }
        }
    }
}
