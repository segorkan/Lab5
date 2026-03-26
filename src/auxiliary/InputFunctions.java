package auxiliary;

import exceptions.ConditionsNotMetException;
import objects.*;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class InputFunctions {

    public static String validateName(Scanner sc){
        while (true) {
            output("Введите имя name:");
            String name = sc.nextLine();
            try {
                if (name == null || name.isBlank()) {
                    throw new IOException("Имя не может быть пустым.");
                }
                return name.trim();
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    public static int validateId(Scanner sc){
        String stringId = sc.nextLine();
        try {
            if (stringId == null){
                throw new NullPointerException();
            }
            int id = Integer.parseInt(stringId.trim());
            return id;
        } catch (NumberFormatException | NullPointerException e){
            System.out.println("Проблема с данными в исходном файле. Последний продукт некорректен.");
            System.exit(1);
            return 0;
        }
    }

    public static Date validateDate(Scanner sc){
        String dateString = sc.nextLine();
        try {
            if (dateString == null){
                throw new NullPointerException();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = dateFormat.parse(dateString);
            return date;
        } catch (ParseException | NullPointerException e){
            System.out.println("Проблема с данными в исходном файле. Последний продукт некорректен.");
            System.exit(1);
            return new Date();
        }
    }

    public static Float validatePrice(Scanner sc) throws IOException, NumberFormatException {
        while (true) {
            output("Введите цену товара (вещественное число > 0):");
            String pricestring = sc.nextLine();
            if (pricestring == null || pricestring.isBlank()) {
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
                return null;
            }
            try {
                Float price = Float.parseFloat(pricestring.trim());
                if (price <= 0) {
                    throw new IOException("Цена price должна быть больше 0.");
                }
                return price;
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "price обязана иметь тип float.");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    public static String validatePartNumber(Scanner sc) throws IOException {
        while (true) {
            output("Введите partNumber (длина не меньше 24):");
            String partNumber = sc.nextLine();
            try {
                if (partNumber == null || partNumber.isBlank()) {
                    throw new IOException("partNumber не может быть пустым.");
                }
                if (partNumber.trim().length() < 24) {
                    throw new IOException("Длина partNumber должна быть не меньше 24.");
                }
                return partNumber.trim();
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    public static Coordinates validateCoordinates(Scanner sc) {
        output("Начало ввода координаты." + "\n");
        Integer x = validateCoordinateX(sc);
        float y = validateCoordinateY(sc);
        return new Coordinates(x, y);
    }

    private static Integer validateCoordinateX(Scanner sc) {
        while (true) {
            output("Введите координату X (целое число > -352):");
            String xstring = sc.nextLine();
            if (xstring == null || xstring.isBlank()) {
                System.out.println("Координата X не может быть пустой.");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
                continue;
            }
            try {
                int x = Integer.parseInt(xstring.trim());
                if (x <= -352) {
                    throw new IOException("X должен быть больше -352");
                }
                return x;
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "X обязана иметь тип int.");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    private static float validateCoordinateY(Scanner sc) {
        while (true) {
            output("Введите координату Y (вещественное число > -765):");
            String ystring = sc.nextLine();
            try {
                float y = Float.parseFloat(ystring.trim());
                if (y <= -765) {
                    throw new IOException("Y должен быть больше -765");
                }
                return y;
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "Y обязан иметь тип float.");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    public static UnitOfMeasure validateUnitOfMeasure(Scanner sc) {
        while (true) {
            output("Введите единицу измерения (возможны KILOGRAMS, CENTIMETERS, SQUARE_METERS):");
            String unit = sc.nextLine();
            try {
                if (unit == null || unit.isBlank()) {
                    throw new IOException("Единица измерения не может быть пустой.");
                }
                return UnitOfMeasure.valueOf(unit.trim());
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "Значение обязано быть из списка.");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    public static Person validatePerson(Scanner sc) {
        while (true) {
            output("Хотите ли вы ввести владельца продукта? (Y/N):");
            try {
                String answer = sc.nextLine().trim();
                if (answer.equals("Y")) {
                    output("Начинаю ввод владельца.");
                    String name = validateName(sc);
                    long weight = validateWeight(sc);
                    Color eyeColor = validateColor(sc, "глаза");
                    Color hairColor = validateColor(sc, "волос");
                    Country nationality = validateNationality(sc);
                    Location location = validateLocation(sc);
                    return new Person(name, weight, eyeColor, hairColor, nationality, location);
                } else if (answer.equals("N")) {
                    return null;
                } else {
                    throw new IOException("Ответ должен быть Y или N.");
                }
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    private static long validateWeight(Scanner sc) {
        while (true) {
            output("Введите вес человека:");
            String weightstring = sc.nextLine();
            if (weightstring == null || weightstring.trim().isEmpty()) {
                System.out.println("Вес человека не может быть null.");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
                continue;
            }
            try {
                long weight = Long.parseLong(weightstring.trim());
                if (weight <= 0) {
                    throw new IOException("Вес человека должен быть > 0.");
                }
                return weight;
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "Вес обязан иметь тип long.");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    public static Color validateColor(Scanner sc, String addition) {
        while (true) {
            output("Введите цвет " + addition + " (возможны RED, BLACK, BLUE, WHITE, ORANGE, BROWN):");
            String color = sc.nextLine();
            try {
                if (color == null || color.isBlank()) {
                    return null;
                }
                return Color.valueOf(color.trim());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "Значение обязано быть из списка.");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    public static Country validateNationality(Scanner sc) {
        while (true) {
            output("Введите национальность (возможны UNITED_KINGDOM, GERMANY, SPAIN, VATICAN, JAPAN):");
            String country = sc.nextLine();
            try {
                if (country == null || country.isBlank()) {
                    throw new IOException("Национальность не может быть пустой.");
                }
                return Country.valueOf(country.trim());
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "Значение обязано быть из списка.");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    public static Location validateLocation(Scanner sc) {
        while (true) {
            output("Хотите ли вы ввести место нахождения человека? (Y/N):");
            try {
                String answer = sc.nextLine().trim();
                if (answer.equals("Y")) {
                    int x = validateLocationX(sc);
                    float y = validateLocationY(sc);
                    String name = validateLocationName(sc);
                    return new Location(x, y, name);
                } else if (answer.equals("N")) {
                    return null;
                } else {
                    throw new IOException("Ответ должен быть Y или N.");
                }
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    public static int validateLocationX(Scanner sc) {
        while (true) {
            output("Введите координату X (целое число int):");
            String xstring = sc.nextLine();
            try {
                int x = Integer.parseInt(xstring.trim());
                return x;
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "X обязан иметь тип int.");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    public static float validateLocationY(Scanner sc) {
        while (true) {
            output("Введите координату Y (вещественное число float):");
            String ystring = sc.nextLine();
            try {
                float y = Float.parseFloat(ystring.trim());
                return y;
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "Y обязан иметь тип float.");
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    public static String validateLocationName(Scanner sc) {
        while (true) {
            output("Введите название локации (длина не больше 563):");
            String location = sc.nextLine();
            try {
                if (location == null || location.isBlank()) {
                    throw new IOException("Название локации не может быть пустым.");
                }
                if (location.trim().length() > 563) {
                    throw new IOException("Длина названия локации должна быть не больше 563");
                }
                return location.trim();
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in){
                    System.exit(1);
                }
            }
        }
    }

    public static void output(String message){
        if (CurrentInput.getInputStream() == System.in){
            System.out.print(message);
        }
    }
}
