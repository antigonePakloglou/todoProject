package com.estiam.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.estiam.todo.room.Todo;
import com.estiam.todo.room.TodoDatabase;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_TODO = "todo";
    private String todo;
    private MenuItem todoAdd;

    private TextView todoElement;

    private RecyclerView rvTodos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoAdd = findViewById(R.id.todoAdd);
        rvTodos = findViewById(R.id.rvTodo);

        // test si le bundle existe
        if (savedInstanceState != null) {
            // récupère le todoo  sauvegardé dans le bundle
            todo = savedInstanceState.getString(KEY_TODO);
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // sauvegarde dans le bundle le todoo
        outState.putString(KEY_TODO,todo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // crée le menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // test lid de l'item sélectionné
        if (item.getItemId() == R.id.todoAdd) {
            // crée un Intent
            Intent intent = new Intent(getApplicationContext(), AddTodoActivity.class);

            // enregistre dans l'intent les todos en cours ?
            // intent.putExtra(KEY_QUESTION, questions.get(indexQuestion));

            // démare l'activity
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
       //Récupére la liste des Todos en base de données
        super.onStart();

        //recupération des todos en bdd
        List<Todo> todos = TodoDatabase.getDb(getApplicationContext()).todoDao().list();
        for (Todo todo : todos){
            Log.d("Todo : ", todo.getName());
        }


    }

}