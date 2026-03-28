package auxiliary;

import objects.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            if (pricestring == null || pricestring.isBlank()) {
                return null;
            }
            try {
                pricestring = pricestring.replace(',', '.');
                Float price = Float.parseFloat(pricestring.trim());
                if (price <= 0) {
                    throw new IOException("Цена price должна быть больше 0.");
                }
                return price;
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
            if (xstring == null || xstring.isBlank()) {
                System.out.println("Координата X не может быть пустой.");
                if (CurrentInput.getInputStream() != System.in) {
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
            try {
                ystring = ystring.replace(',', '.');
                float y = Float.parseFloat(ystring.trim());
                if (y <= -765) {
                    throw new IOException("Y должен быть больше -765");
                }
                return y;
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
            output("Введите единицу измерения (возможны KILOGRAMS, CENTIMETERS, SQUARE_METERS):");
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
                if (answer.trim().equals("Y")) {
                    output("Начинаю ввод владельца.");
                    String name = validateName(reader);
                    long weight = validateWeight(reader);
                    Color eyeColor = validateColor(reader, "глаза");
                    Color hairColor = validateColor(reader, "волос");
                    Country nationality = validateNationality(reader);
                    Location location = validateLocation(reader);
                    return new Person(name, weight, eyeColor, hairColor, nationality, location);
                } else if (answer.trim().equals("N")) {
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
            if (weightstring == null || weightstring.trim().isEmpty()) {
                System.out.println("Вес человека не может быть null.");
                if (CurrentInput.getInputStream() != System.in) {
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
            output("Введите цвет " + addition + " (возможны RED, BLACK, BLUE, WHITE, ORANGE, BROWN):");
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
            try {
                if (color == null || color.isBlank()) {
                    return null;
                }
                return Color.valueOf(color.trim());
            } catch (IllegalArgumentException e) {
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
            output("Введите национальность (возможны UNITED_KINGDOM, GERMANY, SPAIN, VATICAN, JAPAN):");
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
                if (answer.trim().equals("Y")) {
                    int x = validateLocationX(reader);
                    float y = validateLocationY(reader);
                    String name = validateLocationName(reader);
                    return new Location(x, y, name);
                } else if (answer.trim().equals("N")) {
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
            try {
                int x = Integer.parseInt(xstring.trim());
                return x;
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
            try {
                ystring = ystring.replace(',', '.');
                float y = Float.parseFloat(ystring.trim());
                return y;
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
