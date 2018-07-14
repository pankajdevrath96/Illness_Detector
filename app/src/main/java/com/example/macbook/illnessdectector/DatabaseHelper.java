package com.example.macbook.illnessdectector;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DatabaseHelper extends SQLiteOpenHelper{
    String db_path = "";
    public static String db_name = "Disease_Symp.db";
    SQLiteDatabase myDatabase;
    Context context;

    public  DatabaseHelper(Context context){
        super(context, db_name, null, 10);
        this.context = context;
        this.db_path = context.getDatabasePath(db_name).toString();
        Log.e("Path of Database",db_path);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            try{
                copyData();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    public Cursor fetchAll(String table, String [] columns, String selection, String [] selectionArgs, String groupBy, String having, String orderBy){
        return myDatabase.query(table,columns,selection, selectionArgs, groupBy, having, orderBy);
    }


    public void createDatabase() throws IOException{
        boolean dbExists = checkDataBase();
        if(dbExists){

        }else{
            this.getReadableDatabase();

            try{
                copyData();
            }catch (IOException e){
                throw new Error("Error Copying Database");
            }
        }
    }

    public boolean checkDataBase(){
        SQLiteDatabase db = null;
        try{
            String myPath = db_path;
            Log.e("Databse path :",db_path);
            db = SQLiteDatabase.openDatabase(myPath,null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLException e){
            Log.e("Error at Check databse",e.toString());
        }

        return db != null? true : false;

    }


    public void  copyData() throws IOException{
        InputStream myInput = context.getAssets().open(db_name);
        Log.e("File opened","true");
        String outFileName = db_path + db_name;
        Log.e("File Name is ",outFileName);
        OutputStream outFile = new FileOutputStream(outFileName);

        byte[] buffer = new byte[10];
        int length;

        while((length = myInput.read(buffer)) > 0){
            outFile.write(buffer, 0, length);
        }
        outFile.flush();
        outFile.close();
        myInput.close();

    }


    public void openDatabase() throws SQLException{
        String myPath = db_path + db_name;
        Log.e("openDatabase(),Db path",myPath);
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    public void close(){
        if(myDatabase != null)
            myDatabase.close();
        super.close();
    }

    public ArrayList<String> getDiseases() {

        ArrayList<String> diseases = new ArrayList<>();
        try{

            Cursor c = fetchAll("Disease_Table", null, null, null, null, null, null);
            if(c.moveToFirst()) {
                do {
                    int index = c.getColumnIndex("Disease");

                    String disease = c.getString(index);
                    diseases.add(disease);
                } while (c.moveToNext());
                Toast.makeText(context, "Diseases got successfully", Toast.LENGTH_LONG).show();
            }
        }catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "No Diseases Available", Toast.LENGTH_LONG).show();
        }

        return  diseases;
    }

    public ArrayList<String> getSymptoms() {

        ArrayList<String> symptoms = new ArrayList<>();
        try{

            Log.d("query", "start");
            Cursor c = myDatabase.rawQuery("SELECT * FROM Symptoms_table", null);

            Log.d("query", "ran Successfully");

            if(c.moveToFirst()) {
                do {
                    int index = c.getColumnIndex("Symptoms");

                    String symptom = c.getString(index);
                    symptoms.add(symptom);
                } while (c.moveToNext());
                Toast.makeText(context, "Symptoms got successfully", Toast.LENGTH_LONG).show();
            }
        }catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "No Symptoms Available", Toast.LENGTH_LONG).show();
        }

        return  symptoms;
    }

    public ArrayList<String> getSymptomsFromDisease(String disease) {

        ArrayList<String> symptoms = new ArrayList<>();
        try{

            Log.d("query", "start");
            Cursor c = myDatabase.rawQuery("SELECT Symptoms, Disease FROM Symptoms_table a INNER JOIN Map b on a.Sym_id=b.sym_id INNER JOIN Disease_table c ON b.dis_id=c.Dis_id ORDER BY Disease ", null);

            Log.d("query", "ran Successfully");

            if(c.moveToFirst()) {
                do {
                    int index = c.getColumnIndex("Symptoms");
                    String symptom = c.getString(index);

                    index = c.getColumnIndex("Disease");
                    String dis = c.getString(index);

                    if(dis.equals(disease))
                        symptoms.add(symptom);
                } while (c.moveToNext());
                Toast.makeText(context, "Symptoms got successfully", Toast.LENGTH_LONG).show();
            }
        }catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "No Symptoms Available", Toast.LENGTH_LONG).show();
        }

        return  symptoms;
    }


    public Set<String> getDiseaseFromSymptoms(ArrayList<String> symptoms) {

        //ArrayList<String> diseases = new ArrayList<>();
//        ArrayList<String> dis = new ArrayList<>();
//        ArrayList<String> sym = new ArrayList<>();
        Set<String> diseases = new HashSet<>();

        try{

            Log.d("query", "start");
            Cursor c  = myDatabase.rawQuery("SELECT Symptoms, Disease FROM Symptoms_table a INNER JOIN Map b on a.Sym_id=b.sym_id INNER JOIN Disease_table c ON b.dis_id=c.Dis_id ORDER BY Disease ", null);

            Log.d("query", "ran Successfully");

            //Cursor c = myDatabase.rawQuery("SELECT Disease from disease_symptoms")

            if(c.moveToFirst()) {



                do {
                    int index = c.getColumnIndex("Symptoms");
                    String symptom = c.getString(index);
                    //sym.add(symptom);

                    if(symptoms.contains(symptom)) {

                        index = c.getColumnIndex("Disease");
                        String disease = c.getString(index);
                        diseases.add(disease);
                    }



                    //dis.add(disease);


                } while (c.moveToNext());
                Toast.makeText(context, "Disesaes got successfully", Toast.LENGTH_LONG).show();
            }
        }catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "No Diseases Available", Toast.LENGTH_LONG).show();
        }

        return diseases;
    }




}
