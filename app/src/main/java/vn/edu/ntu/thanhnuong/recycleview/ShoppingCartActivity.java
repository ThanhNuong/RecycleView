package vn.edu.ntu.thanhnuong.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import vn.edu.ntu.thanhnuong.controllor.ICartControllor;
import vn.edu.ntu.thanhnuong.model.product;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtShoppingCart;
    Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //nút quay lại
        addViews();
    }

    private  void addViews(){
        txtShoppingCart = (TextView) findViewById(R.id.txtShoppingCart);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        showShoppingCart();
    }

    private  void  showShoppingCart(){
        ICartControllor controllor = (ICartControllor) getApplication();
        List<product> shoppingCart = controllor.getShoppingCart();
        StringBuilder builder = new StringBuilder(); //
        for (product p:shoppingCart){ //c2
             builder.append(p.getName()).append("\t\t\t").append(p.getPrice()).append("\n");
        }
        if (builder.toString().length() > 0)
            txtShoppingCart.setText(builder.toString());
        else
            txtShoppingCart.setText("Không có mặt hàng nào!");
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnCancel: cancel();
                break;
            case  R.id.btnSubmit: submit();
                break;
        }
    }


    private  void cancel(){

    }

    private  void submit(){

    }
}
