package com.jonathan.endevinaelnum;

import static java.lang.Character.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    CharSequence text;
    int duration;
    int count;
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
                // Esto coge el numero y lo transforma de un String a un int
                EditText editText = findViewById(R.id.textNum);
                String numText = String.valueOf(editText.getText());
                int num = Integer.parseInt(numText);

                if (numadivinar == num) {
                    count ++;
                    text = "Efectivamente, el numero es: "+numadivinar+" "+count;

                    // Crear un Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("¡Felicidades!");
                    builder.setMessage("Has adivinado el número " + numadivinar);

                    // Añadir botones de acción al diálogo
                    builder.setPositiveButton("OK", (dialog, which) -> {
                        int numadivinar = (int) (Math.random()*(100-1)+1);
                        dialog.dismiss();
                    });
                    // Ejecuta el dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    count ++;
                    if (num > numadivinar) {
                        text = "El numero es mes petit que "+num+" "+count;
                    } else {
                        text = "El numero es mes gran que "+num+" "+count;
                    }

                }
                Toast toast = Toast.makeText(MainActivity.this, text,Toast.LENGTH_SHORT);
                toast.show();


            }
        });
    }
}