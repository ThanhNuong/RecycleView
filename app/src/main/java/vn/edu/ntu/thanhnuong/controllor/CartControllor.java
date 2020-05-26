package vn.edu.ntu.thanhnuong.controllor;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;
import vn.edu.ntu.thanhnuong.controllor.ICartControllor;
import vn.edu.ntu.thanhnuong.model.product;

public class CartControllor extends Application implements ICartControllor {//application : truy xuất pt này thông getapplicationcontext
    List<product> listProducts = new ArrayList<>(); //ép kiểu -- Arraylist ở dưới List
    List<product> shoppingCart = new ArrayList<>(); //chứa các sp thêm vào giỏ hàng

    public CartControllor(){
        listProducts.add(new product("Khoai lang", 25000, "khoai lang nnnn"));
        listProducts.add(new product("Khoai tím", 25000, "khoai tím mmmmm"));
        listProducts.add(new product("Khoai sọ", 25000, "khoai sọ "));
        listProducts.add(new product("Khoai tây", 25000, "khoai tây Đà Lạt"));
    }

    @Override
    public List<product> getAllProduct() {
        return listProducts;
    }

    //
    @Override
    public boolean addToCart(product p) {
        if(shoppingCart.contains(p))
            return false;
        shoppingCart.add(p);
        return true;
    }

    @Override
    public List<product> getShoppingCart() {
        return shoppingCart;
    }


    @Override
    public void clearShoppingCart() {
        shoppingCart.clear();
    }
}
