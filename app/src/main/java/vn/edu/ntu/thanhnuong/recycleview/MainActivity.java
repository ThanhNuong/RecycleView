package vn.edu.ntu.thanhnuong.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.edu.ntu.thanhnuong.controllor.ICartControllor;
import vn.edu.ntu.thanhnuong.model.product;

public class MainActivity extends AppCompatActivity {
    //khai báo
    RecyclerView rvListProduct;
    ProductAdapter adapter;
    List<product> listProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();

    }

    //ánh xạ
    private  void  addViews()
    {
        rvListProduct = findViewById(R.id.rvListProduct);
        rvListProduct.setLayoutManager(new LinearLayoutManager(this));
        //setLayoutManager : chỉ định chiều hiển thị của 1 item
        //LinearLayoutManager : hiển thị Item theo chiều ngang hay chiều dọc như Scroll list.

        //lấy dữ liệu sản  phẩm
        ICartControllor controllor = (ICartControllor) getApplication();
        listProducts = controllor.getAllProduct();
        adapter = new ProductAdapter(listProducts);
        rvListProduct.setAdapter(adapter);

    }

    //chỉ dùng chung trong mainactivity nên viết adapter trong này
    private  class ProductViewHolder extends RecyclerView.ViewHolder{ //ProductViewHolder: chứa các widget để hiển thị
        TextView txtName, txtPrice, txtDesc;
        ImageView imvAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            //khai báo dữ liệu
            txtName = this.itemView.findViewById(R.id.txtName);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDesc= this.itemView.findViewById(R.id.txtDesc);
            imvAddToCart = this.itemView.findViewById(R.id.imvAddToCart);
        }

        //hàm hiển thị dữ liệu
        public void bind(product p){
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());//chuyển đổi integer -> chuỗi
            txtDesc.setText(p.getDesc());
        }
    }
    //khai báo adapter bên ngoài
    private  class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>
    {
        //cần có dữ liệu
        List<product> listProducts;

        //hàm khởi tạo
        public ProductAdapter(List<product> listProducts) {
            this.listProducts = listProducts;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            //view ta truyền vao để khởi tạoProductViewHolder chính là layout ta thiết kế chính ta layout Inflater
            LayoutInflater inflater = getLayoutInflater();  //LayoutInflater
            View view = inflater.inflate(R.layout.product, viewGroup, false); //int= resource (layout mà ta thiết kế) / viewgroup = viewGroup / boolean (gắn bằng ,ã leehj nên có giá trị F
           //view: layout product đã thiết kế
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {//i : vị trí trong adapter
            productViewHolder.bind(listProducts.get(i));
        }

        @Override
        public int getItemCount() {
            return listProducts.size();
        }
    }
}
