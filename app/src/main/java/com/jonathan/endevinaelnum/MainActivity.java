package com.jonathan.endevinaelnum;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int numadivinar = (int) (Math.random()*(100-1)+1);
        final Button button = findViewById(R.id.buttonconfirm);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                @SuppressLint("ResourceType") int num = getText(R.id.textNum).length();

                CharSequence text;
                int duration;
                if (numadivinar == num) {
                    text = "Vamoooos";
                    duration = Toast.LENGTH_SHORT;
                } else {
                    text = ""+numadivinar;
                    duration = Toast.LENGTH_SHORT;

                }
                Toast toast = Toast.makeText(MainActivity.this, text,duration);
                toast.show();


            }
        });
    }
}