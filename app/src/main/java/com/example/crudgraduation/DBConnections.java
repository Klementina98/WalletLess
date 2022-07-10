package com.example.crudgraduation;

import static androidx.test.InstrumentationRegistry.getContext;

import android.database.Cursor;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.crudgraduation.Model.Card;
import com.example.crudgraduation.Model.GeoLocation;

import java.util.ArrayList;

public class DBConnections {


    MyDatabaseHelper myDB;

   public boolean isEmptyData(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) { //means there is no data
            //Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

   public void storeDataInArrays(ArrayList<Card> list) {
        Cursor cursor = myDB.readAllData();
            while (cursor.moveToNext()) {
                Card card = new Card(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getBlob(3),cursor.getString(4));
                list.add(card);
            }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void storeGeolocationsInArrays(ArrayList<GeoLocation> scannedLocations) {
        Cursor cursor = myDB.readAllDataFromLocations();
        if (cursor.getCount() == 0) { //means there is no data
            //Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                GeoLocation location = new GeoLocation(cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3));
                scannedLocations.add(location);
            }
        }
    }
    public void deleteCardFromDataBase(String cardId){
        myDB.deleteCard(cardId);
    }
    public void deleteGeoLocations(String cardName){
        myDB.deleteGeoLocation(cardName);
    }
}
