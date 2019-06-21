package com.example.todolistappmine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNewTask extends AppCompatActivity {

    private EditText taskName;

    private ProgressDialog progressDialog;
    private Button addTask;
    private RadioButton radioButton;
    private int priority = -1; // High priority = 3  Medium priority = 2  Low priority = 1

    String nameTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);

        //progressDialog = new ProgressDialog()
        taskName = (EditText) findViewById(R.id.describeEdit);
        addTask = (Button) findViewById(R.id.buttonAdd);

        // Tareas = new ArrayList<Tasking>();

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameTask = taskName.getText().toString().trim();
                takingData();
            }
        });
    }

    public void takingData(){

        if ((nameTask != null ) && (nameTask != "") && (priority != -1)) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("nombre", nameTask);
            intent.putExtra("prioridad", priority);
          //  Tareas.add(new Tasking(nameTask, priority));
            startActivity(intent);

        } else {
            if ((nameTask == null)){
                if (nameTask.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please put a name Task", Toast.LENGTH_SHORT).show();
                }
            }
            if (priority == -1){
                Toast.makeText(getApplicationContext(), "Please select a type priority", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonHigh:
                if (checked)
                    priority = 3;
                    break;
            case R.id.radioButtonMedium:
                if (checked)
                    priority = 2;
                    break;

            case R.id.radioButtonLow:
                if (checked)
                    priority = 1;
                    break;
        }
    }

}
