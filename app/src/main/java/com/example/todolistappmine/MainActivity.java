package com.example.todolistappmine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;

    private List<Tasking> misTareas;
    private static SQLiteDatabase myDatabase;
    private static int tama = 0;
    private static Cursor iteratorDB;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        misTareas = new ArrayList<>();

        myDatabase = this.openOrCreateDatabase("myDB", MODE_PRIVATE, null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS tareas (nombre VARCHAR, prioridad INTEGER, id INTEGER PRIMARY KEY)");

        taskAdapter = new TaskAdapter(this, misTareas);
        recyclerView.setAdapter(taskAdapter);

        iteratorDB = myDatabase.rawQuery("SELECT * FROM tareas", null);
        tama = iteratorDB.getCount();

        if (tama > 0) {
            recuperarData();
        }

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newTask = new Intent(getApplicationContext(), AddNewTask.class);
                startActivity(newTask);
            }
        });

        String nameAux = getIntent().getStringExtra("nombre");
        int prioAux = getIntent().getIntExtra("prioridad", -1);

        addNewTasking(nameAux, prioAux);

    }

    private void addNewTasking(String aa, int pp){

        if ((aa != null) && (pp != -1)){
            Toast.makeText(getApplicationContext(), "Data stored", Toast.LENGTH_LONG).show();

            myDatabase.execSQL("INSERT INTO tareas (nombre, prioridad, id) VALUES ('" + aa + "','" + pp +"','"+ tama+"')");
            misTareas.add( new Tasking(aa, pp));

            tama++;
            taskAdapter.notifyDataSetChanged();

        }



    }

    private void recuperarData(){

            int nameIndex = iteratorDB.getColumnIndex("nombre");
            int prioIndex = iteratorDB.getColumnIndex("prioridad");

            iteratorDB.moveToFirst();

            while (!iteratorDB.isLast()){
                String nameAux = iteratorDB.getString(nameIndex);
                int prioAux = iteratorDB.getInt(prioIndex);

                System.out.println("================================");
                System.out.println("NOMBRE: " + nameAux);
                System.out.println("PRIORIDAD:" + prioAux);
                System.out.println("================================");

                misTareas.add(new Tasking(nameAux, prioAux));
                iteratorDB.moveToNext();

            }

    }

}
