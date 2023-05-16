package com.estiam.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.estiam.todo.room.Todo;
import com.estiam.todo.room.TodoDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//AFFICHE LES TODOS
public class AddTodoActivity extends AppCompatActivity {

    private TextView todoToAdd;
    private TextView selectedSpinnerItemTOCHANGE;
    private Button btnAdd;

    private Button btnCancel;

    private  Spinner spinnerUrgency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        //recuperation elements xml
        todoToAdd = findViewById(R.id.todoElement);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        spinnerUrgency = (Spinner) findViewById(R.id.spinner);

        //creation spinner
        createSpinner();

        //ajout du todo
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //si le texte saisi est inférieur à 3 caracteres
                if(todoToAdd.getText().length()< 3){
                    Toast toast = Toast.makeText( getApplicationContext(), "Bonne réponse", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    //creation de l'objet a enregistrer en bdd
                    Todo todo = new Todo();
                    todo.setName((String) todoToAdd.getText());
                    todo.setUrgency((String) spinnerUrgency.getSelectedItem().toString() );
                    TodoDatabase.getDb(getApplicationContext()).todoDao().add(todo);
                }
                // termine un activity
                finish();
            }
        });

        //annule l'ajout
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // termine un activity
                finish();
            }
        });

    }

    private void createSpinner(){
        Spinner spinner = findViewById(R.id.spinner);

        // Initializing a String Array
        String[] urgencys = new String[]{
                "Low urgency",
                "Medium urgency",
                "Hight urgeny"
        };

        // Convert array to a list
        List<String> urgencyList = new ArrayList<> (Arrays.asList(urgencys));

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                urgencyList
        );

        // Set the drop down view resource
        spinnerArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );

        // Finally, data bind the spinner object with adapter
        spinner.setAdapter(spinnerArrayAdapter);
    }
}