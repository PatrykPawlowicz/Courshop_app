package com.example.courshop_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.courshop_app.R;
import com.example.courshop_app.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;

    ImageView detailedImg;
    TextView price, rating_str, description;
    RatingBar rating;
    Button AddToCart;
    ImageView AddItem, RemoveItem;
    Toolbar toolbar;
    ViewAllModel viewAllModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");

        if(object instanceof ViewAllModel){

            viewAllModel = (ViewAllModel) object;

        }


        detailedImg = findViewById(R.id.detail_img);
        AddItem = findViewById(R.id.det_add);
        RemoveItem = findViewById(R.id.det_remove);
        price = findViewById(R.id.price_det);
        rating = findViewById(R.id.det_ratingBar);
        rating_str = findViewById(R.id.det_rating_num);
        description = findViewById(R.id.det_description);
        quantity = findViewById(R.id.det_quantity);

        if(viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            rating_str.setText(viewAllModel.getRating_str());
            rating.setRating(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("Price "+viewAllModel.getPrice()+" zł");

            totalPrice = viewAllModel.getPrice() * totalQuantity;
        }

        AddToCart = findViewById(R.id.det_addToCart);
        AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addedToCart();
            }
        });
        AddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                }

            }
        });
        RemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                }

            }
        });


    }

    private void addedToCart() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("courseName",viewAllModel.getName());
        cartMap.put("coursePrice",price.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this, "Added to a cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });




    }
}