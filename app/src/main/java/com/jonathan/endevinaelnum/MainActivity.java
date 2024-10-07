package com.jonathan.endevinaelnum;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CharSequence text;
    int count = 0;
    TextView textHistorial;
    ScrollView scrollView;
    int numadivinar;
    static ArrayList<Record> records = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textHistorial = findViewById(R.id.tv);
        scrollView = findViewById(R.id.Scroll);
        textHistorial.setMovementMethod(new ScrollingMovementMethod());

        nuevoNumeroAdivinar();

        final Button button = findViewById(R.id.buttonconfirm);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = findViewById(R.id.textNum);
                String numText = String.valueOf(editText.getText());
                if (numText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Introduce un número", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.parseInt(numText);
                count++;
                if (numadivinar == num) {
                    text = "Efectivamente, el número es: " + numadivinar + " (Intentos: " + count + ")\n";
                    mostrarDialogo(numadivinar);
                } else {
                    if (num > numadivinar) {
                        text = "El número es más pequeño que " + num + " (Intentos: " + count + ")\n";
                    } else {
                        text = "El número es más grande que " + num + " (Intentos: " + count + ")\n";
                    }
                    agregarTextoAlHistorial(text);
                }
            }
        });
    }

    private void agregarTextoAlHistorial(CharSequence nuevoTexto) {
        textHistorial.append(nuevoTexto);
        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
    }

    private void nuevoNumeroAdivinar() {
        numadivinar = (int) (Math.random() * (100 - 1) + 1);
    }

    private void mostrarDialogo(int numadivinar) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("¡Felicidades!");
        builder.setMessage("Has adivinado el número " + numadivinar + "\n¿Cuál es tu nombre?");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String playerName = input.getText().toString().trim();
            if (!playerName.isEmpty()) {
                // Crear un nuevo Record y agregarlo a la lista
                Record newRecord = new Record(playerName, count);
                records.add(newRecord);

                // Crear un Intent para abrir RecordsActivity
                Intent intent = new Intent(MainActivity.this, Records.class);
                intent.putExtra("recordsList", records);  // Envía la lista de records
                startActivity(intent);

            } else {
                Toast.makeText(MainActivity.this, "Debes introducir un nombre para entrar en el ranking", Toast.LENGTH_SHORT).show();
            }

            // Reiniciar el juego
            nuevoNumeroAdivinar();
            count = 0;
            textHistorial.setText(null);
            agregarTextoAlHistorial("¡Juego reiniciado! Nueva ronda.\n");
            dialog.dismiss();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }



}
