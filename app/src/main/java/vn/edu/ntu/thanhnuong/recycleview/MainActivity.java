package vn.edu.ntu.thanhnuong.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //nút quay lại
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
    private  class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{ //ProductViewHolder: chứa các widget để hiển thị
        TextView txtName, txtPrice, txtDesc;
        ImageView imvAddToCart;
        product p;

        //lớp adapter
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            //khai báo dữ liệu
            txtName = this.itemView.findViewById(R.id.txtName);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDesc= this.itemView.findViewById(R.id.txtDesc);
            imvAddToCart = this.itemView.findViewById(R.id.imvAddToCart);
            imvAddToCart.setOnClickListener(this);
        }

        //hàm hiển thị dữ liệu
        public void bind(product p){
            this.p = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());//chuyển đổi integer -> chuỗi
            txtDesc.setText(p.getDesc());
        }

        @Override
        public void onClick(View view) {
            ICartControllor controllor = (ICartControllor) getApplication();
            if (controllor.addToCart(p))
                Toast.makeText(MainActivity.this, "Đã thêm :" + p.getName() +" vào giỏ hàng", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this,  p.getName() +" đã có ở giỏ hàng", Toast.LENGTH_SHORT).show();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu_mymenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.mnuClose : finish();
            case R.id.mnuGioHang: showShoppingCart();//phương thức
        }
        return super.onOptionsItemSelected(item);
    }

    private void showShoppingCart(){
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);

    }
}
