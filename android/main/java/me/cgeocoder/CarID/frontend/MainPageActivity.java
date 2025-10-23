package me.cgeocoder.CarID.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import me.cgeocoder.CarID.R;

public class MainPageActivity extends AppCompatActivity {
    ImageButton imageButton_Profile;
    Button add_guest_button;
    Button my_cars_button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        add_guest_button = findViewById(R.id.button_AddGuest);
        my_cars_button = findViewById(R.id.button_MyCars);
        imageButton_Profile = findViewById(R.id.imageButton_Profile);

        add_guest_button.setOnClickListener(view -> {
            startActivity(new Intent(MainPageActivity.this, AddGuestActivity.class));
        });

        my_cars_button.setOnClickListener(view -> {
            startActivity(new Intent(MainPageActivity.this, MyCarsActivity.class));
        });

        imageButton_Profile.setOnClickListener(view -> {
            startActivity(new Intent(MainPageActivity.this, ProfileActivity.class));
        });
    }
}