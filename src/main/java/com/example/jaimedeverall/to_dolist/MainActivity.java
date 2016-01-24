package com.example.jaimedeverall.to_dolist;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> itemsArray;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsArray = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsArray);
        ListView list = (ListView) findViewById(R.id.the_list);
        list.setAdapter(adapter);
        setupListEventListener(list);
    }

    public void addItemToList(View view) {
        EditText itemField = (EditText) findViewById(R.id.new_item_field);
        String itemToAdd = itemField.getText().toString();
        itemsArray.add(itemToAdd);
        adapter.notifyDataSetChanged();
        itemField.setText("");
    }


    private void setupListEventListener(ListView list){
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                itemsArray.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        //to get suggestions from the ide, click control space or something and then space.
    }

    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);

       bundle.putStringArrayList("itemsArray", itemsArray);
    }

    public void onRestoreInstanceState(Bundle bundle){
        super.onRestoreInstanceState(bundle);
        itemsArray = bundle.getStringArrayList("itemsArray");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsArray);
        ListView list = (ListView) findViewById(R.id.the_list);
        list.setAdapter(adapter);
    }

    //control + fn +  F12 to rotate android emulator for mac.


}//class
