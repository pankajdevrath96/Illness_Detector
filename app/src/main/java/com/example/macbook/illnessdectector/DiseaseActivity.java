package com.example.macbook.illnessdectector;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiseaseActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String,List<String>> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);
        expandableListView = findViewById(R.id.exp_list);
        expandableListView=findViewById(R.id.exp_list);
        ListData();
        expandableListAdapter=new ExpandableListAdapter(this,expandableListTitle,hashMap);
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void ListData(){
        expandableListTitle=new ArrayList<>();
        hashMap=new HashMap<>();
        //Adding ListHeadder
        expandableListTitle.add("ChickenPox");
        expandableListTitle.add("Jaundice");
        expandableListTitle.add("Malaria");
        expandableListTitle.add("Typhoid");
        //Adding data in List Header(Child data)

        List<String> ChickenPox=new ArrayList<>();
        ChickenPox.add("Cold");
        ChickenPox.add("Fever");
        ChickenPox.add("Headache");
        ChickenPox.add("Weakness");
        ChickenPox.add("Bodypain");
        ChickenPox.add("Loosemotion");



        List<String> Jaundice=new ArrayList<>();
        Jaundice.add("Fatigue");
        Jaundice.add("Fever");
        Jaundice.add("Vomiting");
        Jaundice.add("Dark Urine");
        Jaundice.add("Pale Stools");
        Jaundice.add("Weight Loss");
        Jaundice.add("Abdominal Pain");

        List<String> Malaria=new ArrayList<>();
        Malaria.add("Coma");
        Malaria.add("Anemia");
        Malaria.add("Nausea");
        Malaria.add("Headache");
        Malaria.add("Vomiting");
        Malaria.add("Diarrhea");
        Malaria.add("High Fever") ;
        Malaria.add("Muscle Pain");
        Malaria.add("Convulsions");
        Malaria.add("Bloody Stools");
        Malaria.add("Abdominal Pain");
        Malaria.add("Profuse Sweating");


        List<String> Typhoid =new ArrayList<>();
        Typhoid.add("Fever");
        Typhoid.add("Vomit");
        Typhoid.add("Weakness");
        Typhoid.add("Stomachache");
        Typhoid.add("LooseMotion");

        hashMap.put(expandableListTitle.get(0),ChickenPox);
        hashMap.put(expandableListTitle.get(1),Jaundice);
        hashMap.put(expandableListTitle.get(2),Malaria);
        hashMap.put(expandableListTitle.get(3),Typhoid);

    }





}

