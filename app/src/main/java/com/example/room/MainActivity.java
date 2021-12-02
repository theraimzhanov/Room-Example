package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText etName;
    Button Add, Reset;
    LinearLayoutManager layoutManager;
    UserDataBase dataBase;
     MainAdapter adapter;
  public static   List<UserInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        etName = findViewById(R.id.etName);

        Add = findViewById(R.id.bt_Add);
        Reset = findViewById(R.id.btReset);

        dataBase = UserDataBase.getInstance(this);
        list = dataBase.userDao().getAll();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainAdapter(list, MainActivity.this);
        recyclerView.setAdapter(adapter);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sName = etName.getText().toString();
                if (!sName.equals("")) {
                    UserInfo data = new UserInfo(sName);
                    list.add(data);
                    adapter.addList(list);
                    dataBase.userDao().insert(data);
                    etName.setText("");

                    dataBase.userDao().getAll();

                    adapter.notifyDataSetChanged();
                }
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBase.userDao().reset(list);
                list.clear();
                list.addAll(dataBase.userDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });
    }
}