package auxiliary;

import objects.Product;

public class UpdateHandler {

    private static Product oldProduct;

    public static Product getOldProduct(){
        return UpdateHandler.oldProduct;
    }

    public static void setProduct(Product product){
        UpdateHandler.oldProduct = product;
    }

    public static void setVoid(){
        UpdateHandler.oldProduct = null;
    }

    public static Boolean isVoid(){
        return getOldProduct() == null;
    }

}
