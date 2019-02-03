package com.android.oblig.activities;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.android.oblig.R;
import com.android.oblig.modules.AppDatabase;
import com.android.oblig.modules.Preferences;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends AppCompatActivity {

    protected Map<String, Object> actions = new HashMap<>();

    public static AppDatabase db;


    void prepareMenu() {
        Resources res = getResources();
        addMenuItem((res.getString(R.string.Person_List_Menu_choice)), PersonList.class);
        addMenuItem((res.getString(R.string.Quiz_Menu_choice)), Quiz.class);
    }

    // Preferences
    String userName;
    Preferences preferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Preferences
        preferences = new Preferences(this);
        String userName = preferences.getUserName();
        if(userName=="default") {
            //Do popup - create/login as user if no user recorded
            newUserDialogBox(this,preferences);
        }


        db = Room.databaseBuilder(getBaseContext(),
                AppDatabase.class, "AppDatabase").allowMainThreadQueries().build();

        prepareMenu();
        String[] keys = actions.keySet().toArray(new String[actions.keySet().size()]);

        ListView av = (ListView) findViewById(R.id.menu_list);
        MainMenuAdapter adapter = new MainMenuAdapter(this,keys);

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

    /** Dialog box for new users
     * 
     * @param context
     * @param preferences
     */
    public void newUserDialogBox(final Context context, final Preferences preferences){
        LayoutInflater li = LayoutInflater.from(this);
        View prompt = (View) li.inflate(R.layout.login_popup_fragment,null);
        AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(this);

        final EditText userEdit = (EditText) prompt.findViewById(R.id.user_name_input);

        alertDialogbuilder.setView(prompt);
        //alertDialogbuilder.setTitle(preferences.getUserName());
        alertDialogbuilder.setCancelable(false).setPositiveButton(R.string.confirm_username, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userName = userEdit.getText().toString();
                // check for valid input
                if(userName != null){
                    int minLength = 6;
                    int maxLength = 12;
                    boolean isCorrectLength = userName.length() < minLength || userName.length() > maxLength;
                    if(!isCorrectLength) {
                        Toast.makeText(context,"Invalid username",Toast.LENGTH_SHORT);
                        newUserDialogBox(context, preferences);
                    }else{
                        preferences.setUserName(userName);
                        dialog.dismiss();
                    }
                }
            }
        });
        alertDialogbuilder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialogbuilder.show();
    }

    public void addMenuItem(String label, Class<?> cls) {
        actions.put(label, new Intent(this, cls));
    }

}

