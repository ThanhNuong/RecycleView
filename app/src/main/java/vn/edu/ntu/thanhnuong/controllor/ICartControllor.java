package vn.edu.ntu.thanhnuong.controllor;

import java.util.List;

import vn.edu.ntu.thanhnuong.model.product;

public interface ICartControllor {
    public List<product> getAllProduct();
    public boolean addToCart(product p);//trả về giá trị True/False khi thêm vào giỏ hàng
    public  List<product> getShoppingCart();//thêm sản phẩm vào giỏ hàng
    public void clearShoppingCart(); //xóa giỏ hàng
}
