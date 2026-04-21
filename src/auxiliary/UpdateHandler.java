package auxiliary;

import objects.Product;

/**
 * Класс для вспомогательных команд для update.
 */
public class UpdateHandler {

    private static Product oldProduct;

    /**
     * Вернуть старый продукт.
     */
    public static Product getOldProduct(){
        return UpdateHandler.oldProduct;
    }

    /**
     * Установить новый продукт
     * @param product новый продукт
     */
    public static void setProduct(Product product){
        UpdateHandler.oldProduct = product;
    }

    /**
     * Установить продукт в null.
     */
    public static void setVoid(){
        UpdateHandler.oldProduct = null;
    }

    /**
     * Проверка, равен ли oldProduct null.
     */
    public static Boolean isVoid(){
        return getOldProduct() == null;
    }

}
