package com.android.oblig.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.oblig.R;
import com.android.oblig.modules.AppDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends AppCompatActivity {

    protected Map<String, Object> actions = new HashMap<>();

    public final static String debugTag = "ViewSamples";
    public static AppDatabase db;

    void prepareMenu() {
        addMenuItem("AddPerson", AddPerson.class);
        addMenuItem("Database", PersonList.class);
        addMenuItem("Quiz", Quiz.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        db = Room.databaseBuilder(getBaseContext(),
                AppDatabase.class, "AppDatabase").allowMainThreadQueries().build();

        prepareMenu();
        String[] keys = actions.keySet().toArray(new String[actions.keySet().size()]);

        ListView av = (ListView) findViewById(R.id.menu_list);
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, keys);

        av.setAdapter(adapter);
        av.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                String key = (String) parent.getItemAtPosition(position);
                startActivity((Intent) actions.get(key));
            }
        });
    }

    public void addMenuItem(String label, Class<?> cls) {
        actions.put(label, new Intent(this, cls));
    }

}

