package auxiliary;

import objects.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Класс хранит функции для ввода и валидации полей классов.
 */
public class InputFunctions {

    /**
     * Ввод и валидация имени продукта.
     *
     * @param reader поток для чтения данных
     * @return String
     */
    public static String validateName(InputStreamReader reader) throws IOException {
        while (true) {
            output("Введите имя name:");
            StringBuilder sb = new StringBuilder();
            String name = null;
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    name = sb.toString();
                    break;
                }
                sb.append((char) ch);
            }
            if (ch == -1) {
                name = sb.toString();
            }
            try {
                if (name.trim().equals("keep") && !UpdateHandler.isVoid()){
                    return UpdateHandler.getOldProduct().getName();
                }
                if (name == null || name.isBlank()) {
                    throw new IOException("Имя не может быть пустым.");
                }
                return name.trim();
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация id продукта
     *
     * @param reader входной поток
     * @return int
     */
    public static int validateId(InputStreamReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String stringId = null;
        int ch;
        while ((ch = reader.read()) != -1) {
            if (ch == '\n') {
                stringId = sb.toString();
                break;
            }
            sb.append((char) ch);
        }
        if (ch == -1) {
            stringId = sb.toString();
        }
        try {
            if (stringId == null) {
                throw new NullPointerException();
            }
            return Integer.parseInt(stringId.trim());
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Проблема с данными в исходном файле. Последний продукт некорректен.");
            System.exit(1);
            return 0;
        }
    }

    /**
     * Ввод и валидация имени даты создания файла.
     *
     * @param reader входной поток
     * @return {@link Date}
     */
    public static Date validateDate(InputStreamReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String dateString = null;
        int ch;
        while ((ch = reader.read()) != -1) {
            if (ch == '\n') {
                dateString = sb.toString();
                break;
            }
            sb.append((char) ch);
        }
        if (ch == -1) {
            dateString = sb.toString();
        }
        try {
            if (dateString == null) {
                throw new NullPointerException();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = dateFormat.parse(dateString);
            return date;
        } catch (ParseException | NullPointerException e) {
            System.out.println("Проблема с данными в исходном файле. Последний продукт некорректен.");
            System.exit(1);
            return new Date();
        }
    }

    /**
     * Ввод и валидация цены продукта.
     *
     * @param reader входной поток
     * @return Float
     */
    public static Float validatePrice(InputStreamReader reader) throws IOException, NumberFormatException {
        while (true) {
            output("Введите цену товара (вещественное число > 0):");
            StringBuilder sb = new StringBuilder();
            String pricestring = null;
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    pricestring = sb.toString();
                    break;
                }
                sb.append((char) ch);
            }
            if (ch == -1) {
                pricestring = sb.toString();
            }
            if (pricestring.trim().equals("keep") && !UpdateHandler.isVoid()){
                return UpdateHandler.getOldProduct().getPrice();
            }
            if (pricestring == null || pricestring.isBlank()) {
                return null;
            }
            try {
                pricestring = pricestring.replace(',', '.');
                Float price = Float.parseFloat(pricestring.trim());
                if (price <= 0) {
                    throw new IOException("Цена price должна быть больше 0.");
                }
                String[] parts = pricestring.trim().split("\\.");
                if (parts.length == 1){
                    return price;
                }
                while (parts[1].length() < 4){
                    parts[1] += "0";
                }
                return Float.parseFloat(parts[0] + "." + parts[1].substring(0, 4));
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "price обязана иметь тип float.");
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация номера детали.
     *
     * @param reader входной поток
     * @return String
     */
    public static String validatePartNumber(InputStreamReader reader) throws IOException {
        while (true) {
            output("Введите partNumber (длина не меньше 24):");
            StringBuilder sb = new StringBuilder();
            String partNumber = null;
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    partNumber = sb.toString();
                    break;
                }
                sb.append((char) ch);
            }
            if (ch == -1) {
                partNumber = sb.toString();
            }
            if (partNumber.trim().equals("keep") && !UpdateHandler.isVoid()){
                return UpdateHandler.getOldProduct().getPartNumber();
            }
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
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация координат продукта.
     *
     * @param reader входной поток
     * @return {@link Coordinates}
     */
    public static Coordinates validateCoordinates(InputStreamReader reader) throws IOException{
        output("Начало ввода координаты." + "\n");
        Integer x = validateCoordinateX(reader);
        float y = validateCoordinateY(reader);
        return new Coordinates(x, y);
    }

    /**
     * Ввод и валидация координаты X продукта.
     *
     * @param reader входной поток
     * @return Integer
     */
    private static Integer validateCoordinateX(InputStreamReader reader) throws IOException {
        while (true) {
            output("Введите координату X (целое число > -352):");
            StringBuilder sb = new StringBuilder();
            String xstring = null;
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    xstring = sb.toString();
                    break;
                }
                sb.append((char) ch);
            }
            if (ch == -1) {
                xstring = sb.toString();
            }
            if (xstring.trim().equals("keep") && !UpdateHandler.isVoid()){
                return UpdateHandler.getOldProduct().getCoordinates().getX();
            }
            if (xstring == null || xstring.isBlank()) {
                System.out.println("Координата X не может быть пустой.");
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
                continue;
            }
            try {
                xstring = xstring.replace(',', '.');
                double xd = Double.parseDouble(xstring.trim());
                int x = (int)xd;
                if (x == xd){
                    if (x <= -352) {
                        throw new IOException("X должен быть больше -352");
                    }
                    return x;
                }
                else{
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "X обязана иметь тип int.");
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация координаты Y продукта.
     *
     * @param reader входной поток
     * @return float
     */
    private static float validateCoordinateY(InputStreamReader reader) throws IOException {
        while (true) {
            output("Введите координату Y (вещественное число > -765):");
            StringBuilder sb = new StringBuilder();
            String ystring = null;
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    ystring = sb.toString();
                    break;
                }
                sb.append((char) ch);
            }
            if (ch == -1) {
                ystring = sb.toString();
            }
            if (ystring.trim().equals("keep") && !UpdateHandler.isVoid()){
                return UpdateHandler.getOldProduct().getCoordinates().getY();
            }
            try {
                ystring = ystring.replace(',', '.');
                float y = Float.parseFloat(ystring.trim());
                if (y <= -765) {
                    throw new IOException("Y должен быть больше -765");
                }
                String[] parts = ystring.trim().split("\\.");
                if (parts.length == 1){
                    return y;
                }
                while (parts[1].length() < 4){
                    parts[1] += "0";
                }
                return Float.parseFloat(parts[0] + "." + parts[1].substring(0, 4));
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "Y обязан иметь тип float.");
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация единицы измерения.
     *
     * @param reader входной поток
     * @return {@link UnitOfMeasure}
     */
    public static UnitOfMeasure validateUnitOfMeasure(InputStreamReader reader) throws IOException {
        while (true) {
            output("Введите единицу измерения (возможны KILOGRAMS (1), CENTIMETERS (2), SQUARE_METERS (3)):");
            StringBuilder sb = new StringBuilder();
            String unit = null;
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    unit = sb.toString();
                    break;
                }
                sb.append((char) ch);
            }
            if (ch == -1) {
                unit = sb.toString();
            }
            if (unit.trim().equals("keep") && !UpdateHandler.isVoid()){
                return UpdateHandler.getOldProduct().getUnitOfMeasure();
            }
            try {
                if (unit == null || unit.isBlank()) {
                    throw new IOException("Единица измерения не может быть пустой.");
                }
                return UnitOfMeasure.valueOf(unit.trim());
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            } catch (IllegalArgumentException e) {
                ArrayList<String> numbers = new ArrayList<>(List.of("1", "2", "3"));
                if (numbers.contains(unit.trim())){
                    return UnitOfMeasure.values()[Integer.parseInt(unit.trim()) - 1];
                }
                System.out.println(e.getClass().getSimpleName() + ": " + "Значение обязано быть из списка.");
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация владельца продукта.
     *
     * @param reader входной поток
     * @return {@link Product}
     */
    public static Person validatePerson(InputStreamReader reader) {
        while (true) {
            output("Хотите ли вы ввести владельца продукта? (Y/N):");
            try {
                StringBuilder sb = new StringBuilder();
                String answer = null;
                int ch;
                while ((ch = reader.read()) != -1) {
                    if (ch == '\n') {
                        answer = sb.toString();
                        break;
                    }
                    sb.append((char) ch);
                }
                if (ch == -1) {
                    answer = sb.toString();
                }
                if (answer.trim().equals("keep") && !UpdateHandler.isVoid()){
                    return UpdateHandler.getOldProduct().getOwner();
                }
                if (answer.trim().toUpperCase().equals("Y")) {
                    output("Начинаю ввод владельца.");
                    String name = validateName(reader);
                    long weight = validateWeight(reader);
                    Color eyeColor = validateColor(reader, "глаза");
                    Color hairColor = validateColor(reader, "волос");
                    Country nationality = validateNationality(reader);
                    Location location = validateLocation(reader);
                    return new Person(name, weight, eyeColor, hairColor, nationality, location);
                } else if (answer.trim().toUpperCase().equals("N")) {
                    return null;
                } else {
                    throw new IOException("Ответ должен быть Y или N.");
                }
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация веса человека.
     *
     * @param reader входной поток
     * @return long
     */
    private static long validateWeight(InputStreamReader reader) throws IOException {
        while (true) {
            output("Введите вес человека:");
            StringBuilder sb = new StringBuilder();
            String weightstring = null;
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    weightstring = sb.toString();
                    break;
                }
                sb.append((char) ch);
            }
            if (ch == -1) {
                weightstring = sb.toString();
            }
            if (weightstring.trim().equals("keep") && !UpdateHandler.isVoid()){
                return UpdateHandler.getOldProduct().getOwner().getWeight();
            }
            if (weightstring == null || weightstring.trim().isEmpty()) {
                System.out.println("Вес человека не может быть null.");
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
                continue;
            }
            try {
                weightstring = weightstring.replace(',', '.');
                double weightDouble = Double.parseDouble(weightstring.trim());
                long weight = (long)weightDouble;
                if (weight == weightDouble){
                    if (weight <= 0) {
                        throw new IOException("Вес человека должен быть > 0.");
                    }
                    return weight;
                }
                else{
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "Вес обязан иметь тип long.");
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация цвета.
     *
     * @param reader входной поток
     * @param addition уточнение поля ввода
     * @return {@link Color}
     */
    public static Color validateColor(InputStreamReader reader, String addition) throws IOException {
        while (true) {
            output("Введите цвет " + addition + " (возможны RED (1), BLACK (2), BLUE (3), WHITE (4), ORANGE (5), BROWN (6)):");
            StringBuilder sb = new StringBuilder();
            String color = null;
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    color = sb.toString();
                    break;
                }
                sb.append((char) ch);
            }
            if (ch == -1) {
                color = sb.toString();
            }
            if (color.trim().equals("keep") && !UpdateHandler.isVoid()){
                if (addition.equals("глаза")){
                    return UpdateHandler.getOldProduct().getOwner().getEyeColor();
                }
                else {
                    return UpdateHandler.getOldProduct().getOwner().getHairColor();
                }
            }
            try {
                if (color == null || color.isBlank()) {
                    return null;
                }
                return Color.valueOf(color.trim());
            } catch (IllegalArgumentException e) {
                ArrayList<String> numbers = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6"));
                if (numbers.contains(color.trim())){
                    return Color.values()[Integer.parseInt(color.trim()) - 1];
                }
                System.out.println(e.getClass().getSimpleName() + ": " + "Значение обязано быть из списка.");
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация национальности человека.
     *
     * @param reader входной поток
     * @return {@link Country}
     */
    public static Country validateNationality(InputStreamReader reader) throws IOException {
        while (true) {
            output("Введите национальность (возможны UNITED_KINGDOM (1), GERMANY (2), SPAIN (3), VATICAN (4), JAPAN (5)):");
            StringBuilder sb = new StringBuilder();
            String country = null;
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    country = sb.toString();
                    break;
                }
                sb.append((char) ch);
            }
            if (ch == -1) {
                country = sb.toString();
            }
            if (country.trim().equals("keep") && !UpdateHandler.isVoid()){
                return UpdateHandler.getOldProduct().getOwner().getNationality();
            }
            try {
                if (country == null || country.isBlank()) {
                    throw new IOException("Национальность не может быть пустой.");
                }
                return Country.valueOf(country.trim());
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            } catch (IllegalArgumentException e) {
                ArrayList<String> numbers = new ArrayList<>(List.of("1", "2", "3", "4", "5"));
                if (numbers.contains(country.trim())){
                    return Country.values()[Integer.parseInt(country.trim()) - 1];
                }
                System.out.println(e.getClass().getSimpleName() + ": " + "Значение обязано быть из списка.");
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация местоположения владельца.
     *
     * @param reader входной поток
     * @return {@link Location}
     */
    public static Location validateLocation(InputStreamReader reader) {
        while (true) {
            output("Хотите ли вы ввести место нахождения человека? (Y/N):");
            try {
                StringBuilder sb = new StringBuilder();
                String answer = null;
                int ch;
                while ((ch = reader.read()) != -1) {
                    if (ch == '\n') {
                        answer = sb.toString();
                        break;
                    }
                    sb.append((char) ch);
                }
                if (ch == -1) {
                    answer = sb.toString();
                }
                if (answer.trim().equals("keep") && !UpdateHandler.isVoid()){
                    return UpdateHandler.getOldProduct().getOwner().getLocation();
                }
                if (answer.trim().toUpperCase().equals("Y")) {
                    int x = validateLocationX(reader);
                    float y = validateLocationY(reader);
                    String name = validateLocationName(reader);
                    return new Location(x, y, name);
                } else if (answer.trim().toUpperCase().equals("N")) {
                    return null;
                } else {
                    throw new IOException("Ответ должен быть Y или N.");
                }
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация координаты X местоположения
     *
     * @param reader входной поток
     * @return int
     */
    public static int validateLocationX(InputStreamReader reader) throws IOException{
        while (true) {
            output("Введите координату X (целое число int):");
            StringBuilder sb = new StringBuilder();
            String xstring = null;
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    xstring = sb.toString();
                    break;
                }
                sb.append((char) ch);
            }
            if (ch == -1) {
                xstring = sb.toString();
            }
            if (xstring.trim().equals("keep") && !UpdateHandler.isVoid()){
                return UpdateHandler.getOldProduct().getOwner().getLocation().getX();
            }
            try {
                xstring = xstring.replace(',', '.');
                double xd = Double.parseDouble(xstring.trim());
                int x = (int)xd;
                if (x == xd){
                    return x;
                }
                else{
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "X обязан иметь тип int.");
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация координаты Y местоположения.
     *
     * @param reader входной поток
     * @return float
     */
    public static float validateLocationY(InputStreamReader reader) throws IOException {
        while (true) {
            output("Введите координату Y (вещественное число float):");
            StringBuilder sb = new StringBuilder();
            String ystring = null;
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    ystring = sb.toString();
                    break;
                }
                sb.append((char) ch);
            }
            if (ch == -1) {
                ystring = sb.toString();
            }
            if (ystring.trim().equals("keep") && !UpdateHandler.isVoid()){
                return UpdateHandler.getOldProduct().getOwner().getLocation().getY();
            }
            try {
                ystring = ystring.replace(',', '.');
                float y = Float.parseFloat(ystring.trim());
                String[] parts = ystring.trim().split("\\.");
                if (parts.length == 1){
                    return y;
                }
                while (parts[1].length() < 4){
                    parts[1] += "0";
                }
                return Float.parseFloat(parts[0] + "." + parts[1].substring(0, 4));
            } catch (NumberFormatException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + "Y обязан иметь тип float.");
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Ввод и валидация названия местоположения владельца.
     *
     * @param reader входной поток
     * @return String
     */
    public static String validateLocationName(InputStreamReader reader) throws IOException{
        while (true) {
            output("Введите название локации (длина не больше 563):");
            StringBuilder sb = new StringBuilder();
            String location = null;
            int ch;
            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    location = sb.toString();
                    break;
                }
                sb.append((char) ch);
            }
            if (ch == -1) {
                location = sb.toString();
            }
            if (location.trim().equals("keep") && !UpdateHandler.isVoid()){
                return UpdateHandler.getOldProduct().getOwner().getLocation().getName();
            }
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
                if (CurrentInput.getInputStream() != System.in) {
                    System.exit(1);
                }
            }
        }
    }

    public static void output(String message) {
        if (CurrentInput.getInputStream() == System.in) {
            System.out.print(message);
        }
    }
}
