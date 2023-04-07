package com.androidwavelength.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox chkSettings;
    private TextView txtInfo;
    private EditText edtInfo;


    private final int MENU_PASTE = 1;
    private final int MENU_APPEND = 2;
    private final int MENU_CUT = 3;
    private final int MENU_COPY= 4;


    private String text ="";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkSettings = findViewById(R.id.chkSettings);
        txtInfo = findViewById(R.id.txtInfo);
        edtInfo = findViewById(R.id.edtInfo);


        registerForContextMenu(txtInfo);
        registerForContextMenu(edtInfo);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        mt("onCreateContextMenu");

        if(view == txtInfo) {
            menu.add(0, MENU_PASTE, 0, "Paste");
            menu.add(0, MENU_APPEND, 0,"Append");
        }

        if(view == edtInfo) {
            menu.add(1, MENU_CUT, 0, "Cut");
            menu.add(1, MENU_COPY, 0,"Paste");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case MENU_PASTE:
                txtInfo.setText(text);
            case MENU_APPEND:
                txtInfo.append(text);
            case MENU_COPY:
                text = edtInfo.getText().toString();
                edtInfo.setText("");
                break;
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        mt("onCreateOptionsMenu");

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity, menu);

        menu.setGroupCheckable(R.id.commonGroup, true, true);
       // menu.setGroupDividerEnabled(true);

        /*MenuItem menuItem = menu.add(0, 1, 1, "Info");
        menuItem.setCheckable(true);
        menuItem.setChecked(true);
        menuItem.setAlphabeticShortcut('i');
        menuItem.setIcon(R.mipmap.ic_launcher);
        menuItem.setEnabled(true);

        menu.add(0,2,0,"Help");
        SubMenu subMenu = menu.addSubMenu(1,3,2,"Settings");
        SubMenu.add(2,11,0,"System Settings");
        SubMenu.add(2,12,0, "Phone Settings");*/

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mt("onPrepareOptionsMenu");

        //menu.findItem(3).setEnabled(chkSettings.isChecked());
        menu.findItem(R.id.menuItemSettings).setEnabled(chkSettings.isChecked());

        /*MenuItem menuItemSettings = menu.findItem(3);

        if(chkSettings.isChecked()) {
            menuItemSettings.setEnabled(true);
        }
        else {
            menuItemSettings.setEnabled(false);
        }*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuItemInfo:
                mt("Info selected");
                break;
            case R.id.menuItemHelp:
                mt("Help selected");
            case R.id.menuItemSettings:
                mt("Settings selected");
            case R.id.menuItemSystemSettings:
                mt("System Settings selected");
            case R.id.menuItemPhoneSettings:
                mt("Phone Settings selected");
                break;
        }
        /*switch (item.getItemId()) {
            case 1:
                mt("Info selected");
                break;
            case 2:
                mt("Help selected");
                break;
            case 3:
                mt("Settings selected");
                break;
            case 11:
                mt("System Settings selected");
                break;
            case 12:
                mt("Phone Settings selected");
                break;
        }*/
        return true;
    }

    protected void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}