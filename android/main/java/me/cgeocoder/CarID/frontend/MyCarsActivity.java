package me.cgeocoder.CarID.frontend;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import me.cgeocoder.CarID.R;


public class MyCarsActivity extends AppCompatActivity {
    ImageButton imageButton_ArrowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_my_cars);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.my_cars_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageButton_ArrowBack = findViewById(R.id.imageButton_ArrowBack);

        imageButton_ArrowBack.setOnClickListener(v -> {
            finish();
        });
    }
}
