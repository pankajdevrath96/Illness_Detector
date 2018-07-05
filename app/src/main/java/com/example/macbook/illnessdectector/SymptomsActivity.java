package com.example.macbook.illnessdectector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SymptomsActivity extends AppCompatActivity
{
    ListView listView;
    Button Show_Deseases;

    String[] listContent = {"Cold","Fever","Headache","Loosemotion"};


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        Show_Deseases=findViewById(R.id.buttonB);
        listView=findViewById(R.id.sym_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,listContent);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        Show_Deseases.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String selected = "";
                int cntChoice = listView.getCount();
                SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
                for(int i = 0; i < cntChoice; i++){
                    if(sparseBooleanArray.get(i)) {
                        selected += listView.getItemAtPosition(i).toString() + "\n";
                    }
                }
                Toast.makeText(SymptomsActivity.this,
                        selected,
                        Toast.LENGTH_LONG).show();

            }
        }
            );
    }
}
