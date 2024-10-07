package com.jonathan.endevinaelnum;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Records extends AppCompatActivity {

    ArrayList<Record> recordsList; // Lista para recibir los records

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        recordsList = (ArrayList<Record>) getIntent().getSerializableExtra("recordsList");

        if (recordsList != null && !recordsList.isEmpty()) {
            Collections.sort(recordsList, Comparator.comparingInt(Record::getAttempts));

            // Mostrar los registros en el TableLayout
            TableLayout tableLayout = findViewById(R.id.tableLayoutRecords);
            for (int i = 0; i < recordsList.size(); i++) {
                Record record = recordsList.get(i);

                // Crear una nueva fila
                TableRow tableRow = new TableRow(this);

                // Crear los TextViews para cada columna
                TextView positionView = new TextView(this);
                positionView.setText(String.valueOf(i + 1));  // Posición

                TextView nameView = new TextView(this);
                nameView.setText(record.getName());  // Nombre del jugador

                TextView attemptsView = new TextView(this);
                attemptsView.setText(String.valueOf(record.getAttempts()));  // Número de intentos

                // Añadir las vistas a la fila
                tableRow.addView(positionView);
                tableRow.addView(nameView);
                tableRow.addView(attemptsView);

                // Añadir la fila al TableLayout
                tableLayout.addView(tableRow);
            }
        }
    }
}
