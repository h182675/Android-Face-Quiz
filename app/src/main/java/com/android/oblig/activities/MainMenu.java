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
import android.view.Menu;
import android.view.MenuItem;
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
    private static boolean shownWelcomeMessage = false;

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
        }else if(!shownWelcomeMessage){
            welcomeBackMessage(this,preferences);
            shownWelcomeMessage = true;
        }


        db = Room.databaseBuilder(getBaseContext(),
                AppDatabase.class, "AppDatabase").allowMainThreadQueries().build();

        prepareMenu();
        String[] keys = actions.keySet().toArray(new String[actions.keySet().size()]);

        ListView listView = (ListView) findViewById(R.id.menu_list);
        MainMenuAdapter adapter = new MainMenuAdapter(this,keys);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                String key = (String) parent.getItemAtPosition(position);
                startActivity((Intent) actions.get(key));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_action_settings:{
                startActivity(new Intent(this,Settings.class));
            }
        }

        return true;
    }


    /** Dialog box for old users
     *
     * @param context
     * @param preferences
     */
    public void welcomeBackMessage(final Context context, final Preferences preferences){
        LayoutInflater li = LayoutInflater.from(this);
        View prompt = (View) li.inflate(R.layout.welcome_message,null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle(getResources().getString(R.string.welcome_back) + ", " + preferences.getUserName() + "!");
        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.confirm_username), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogBuilder.show();
    }

    /** Dialog box for new users
     * 
     * @param context
     * @param preferences
     */
    public void newUserDialogBox(final Context context, final Preferences preferences){
        LayoutInflater li = LayoutInflater.from(this);
        View prompt = (View) li.inflate(R.layout.login_popup,null);
        AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(context);

        final EditText userEdit = (EditText) prompt.findViewById(R.id.user_name_input);

        alertDialogbuilder.setView(prompt);
        //alertDialogbuilder.setTitle(preferences.getUserName());
        alertDialogbuilder.setTitle(getResources().getString(R.string.popup_insert_description));
        alertDialogbuilder.setCancelable(false).setPositiveButton(getResources().getString(R.string.confirm_username), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userName = userEdit.getText().toString();
                // check for valid input
                    int minLength = 3;
                    int maxLength = 12;
                    boolean isCorrectLength = userName.length()>minLength && userName.length()<maxLength;// userName.length() < minLength || userName.length() > maxLength;
                    if(!isCorrectLength) {
                        Toast.makeText(context,getResources().getString(R.string.toast_invalid_input_length),Toast.LENGTH_SHORT).show();
                        newUserDialogBox(context, preferences);
                    }else{
                        preferences.setUserName(userName);
                        dialog.dismiss();
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

