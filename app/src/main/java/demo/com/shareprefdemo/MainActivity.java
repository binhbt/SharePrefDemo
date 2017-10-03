package demo.com.shareprefdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String PREF_NAME = "PREF_NAME";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";

    private SharedPreferences sharedPreferences;
    private EditText txtName;
    private EditText txtEmail;
    private Button btnSave;
    private Button btnRetreive;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = (EditText) findViewById(R.id.etName);
        txtEmail = (EditText) findViewById(R.id.etEmail);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnRetreive = (Button) findViewById(R.id.btnRetr);
        btnClear = (Button) findViewById(R.id.btnClear);
        sharedPreferences = getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        if (sharedPreferences.contains(NAME)) {
            txtName.setText(sharedPreferences.getString(NAME, ""));
        }
        if (sharedPreferences.contains(EMAIL)) {
            txtEmail.setText(sharedPreferences.getString(EMAIL, ""));

        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePref();
            }
        });
        btnRetreive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPref();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPref();
            }
        });
    }

    public void savePref() {
        String n = txtName.getText().toString();
        String e = txtEmail.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME, n);
        editor.putString(EMAIL, e);
        editor.commit();
        txtName.setText("");
        txtEmail.setText("");
    }

    public void clearPref() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
/*        editor.remove(NAME); // will delete key name
        editor.remove(EMAIL); // will delete key email
        editor.commit(); // commit changes*/
        editor.clear();
        editor.commit(); // commit changes
        getPref();

    }

    public void getPref() {
        sharedPreferences = getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        txtName.setText(sharedPreferences.getString(NAME, ""));
        txtEmail.setText(sharedPreferences.getString(EMAIL, ""));
    }

}