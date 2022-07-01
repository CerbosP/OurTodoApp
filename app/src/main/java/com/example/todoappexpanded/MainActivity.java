package com.example.todoappexpanded;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Comparator;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener {

    //Global Variables
    EditText etInput;
    Button btnAdd;
    Button btnRem;
    Button btnSrch;
    ListView lvTodos;
    //adapter -> layout
    ArrayAdapter<String> lvAdapter;
    //End Global Variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.et_input);
        btnAdd = findViewById(R.id.btn_add);
        btnRem = findViewById(R.id.btn_remove);
        btnSrch = findViewById(R.id.btn_search);
        lvTodos = findViewById(R.id.lv_todos);

        //Context and id of the layout
        //Context - current state of out application
        lvAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        lvTodos.setAdapter(lvAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = etInput.getText().toString();

                //== vs ===
                //== -> Checks the value
                //=== -> Checks the reference
                if (input.equals("")) {
                    Toast.makeText(MainActivity.this, "INVALID: Empty input to add", Toast.LENGTH_SHORT).show();
                } else {
                    addNewTodo(input);
                }
            }
        });
        btnAdd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(lvAdapter.isEmpty()){
                    Toast.makeText(MainActivity.this, "INVALID: List empty, cannot sort", Toast.LENGTH_SHORT).show();
                } else {
                    sortTodo();
                }

                etInput.getText().clear();
                return true;
            }
        });

        btnRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = etInput.getText().toString();

                if (input.equals("")) {
                    Toast.makeText(MainActivity.this, "INVALID: Empty input to remove", Toast.LENGTH_SHORT).show();
                } else if (lvAdapter.getPosition(input) < 0) {
                    Toast.makeText(MainActivity.this, "INVALID: Item not in list", Toast.LENGTH_SHORT).show();
                } else {
                    removeNewTodo(input);
                }
            }
        });
        btnRem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(!lvAdapter.isEmpty()) {
                    removeAllTodo();
                } else {
                    Toast.makeText(MainActivity.this, "INVALID: List already empty", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        btnSrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = etInput.getText().toString();

                if (input.equals("")) {
                    Toast.makeText(MainActivity.this, "INVALID: Empty input to search", Toast.LENGTH_SHORT).show();
                } else if (lvAdapter.getPosition(input) < 0) {
                    Toast.makeText(MainActivity.this, "INVALID: Item not in list", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,(input + " is on the list."), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addNewTodo(String item) {
        if(lvAdapter.getPosition(item) >= 0) {
            Toast.makeText(MainActivity.this, "INVALID: Item already in list", Toast.LENGTH_SHORT).show();
        } else {
            lvAdapter.add(item);
        }

        etInput.getText().clear();
    }

    public void sortTodo() {
        lvAdapter.sort(new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareToIgnoreCase(rhs);
            }
        });
    }

    public void removeNewTodo(String item) {
        lvAdapter.remove(item);
        etInput.getText().clear();
    }

    public void removeAllTodo() {
        lvAdapter.clear();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
}