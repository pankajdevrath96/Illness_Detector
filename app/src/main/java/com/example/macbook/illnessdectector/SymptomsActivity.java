package com.example.macbook.illnessdectector;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class SymptomsActivity extends AppCompatActivity
{
    ListView listView;
    Button Show_Diseases;
    DatabaseHelper db;
    //String[] listContent = {"Cold","Fever","Headache","Loosemotion"};
    ArrayList<String> listContent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        db = new DatabaseHelper(getApplicationContext());
        try {
            db.createDatabase();
            db.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listContent = db.getSymptoms();

        Show_Diseases=findViewById(R.id.buttonB);
        listView=findViewById(R.id.sym_list);
      //  db=new DatabaseHelper();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,listContent);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        Show_Diseases.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String selected = "";
                int cntChoice = listView.getCount();

                ArrayList<String> symptoms = new ArrayList<>();

                SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
                for(int i = 0; i < cntChoice; i++){
                    if(sparseBooleanArray.get(i)) {
                        //selected += listView.getItemAtPosition(i).toString() + "\n";
                        symptoms.add(listView.getItemAtPosition(i).toString());
                    }
                }

                Set<String> disease = db.getDiseaseFromSymptoms(symptoms);

//                Toast.makeText(SymptomsActivity.this,
//                        selected,
//                        Toast.LENGTH_LONG).show();

                openDialog(disease);

            }
        }
            );
    }

    private void openDialog(Set<String> diseases) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogue_view = inflater.inflate(R.layout.dialogue_box, null);
        dialogBuilder.setView(dialogue_view);
        dialogBuilder.setTitle("Possible Diseases : \n");
        String result = "";

        for(String dis : diseases) {
            result = result  + dis + "\n";
        }
        //result=result+"You can have anyone of above diseases according to your symptoms"+"\n";
        dialogBuilder.setMessage(result);
        AlertDialog box = dialogBuilder.create();
        box.show();
    }
}
