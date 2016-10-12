package com.industrialmaster.im;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.industrialmaster.im.connection.NetConnection;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class KasunActivity extends AppCompatActivity{

    EditText editTextName;
    EditText editTextEmail;
    EditText editTextPassword;
    Button btnRegister;
    Button btnCancel;

    static String name;
    static String email;
    static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasun);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnCancel = (Button) findViewById(R.id.btnCancel);

    }

    public void register(View v){
        try{
            name = editTextName.getText().toString().trim();
            email = editTextEmail.getText().toString().trim();
            password = editTextPassword.getText().toString();
        }catch(Exception e){
            Toast.makeText(v.getContext(), "Have some error check inputs...", Toast.LENGTH_SHORT);
            editTextName.requestFocus();
            e.printStackTrace();
            return;
        }
        if(isStringEmpty(name) && isStringEmpty(email) && isStringEmpty(password)){
            new RegisterTask().execute();
        }

    }

    public void cancel(View v){
        editTextName.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
    }

    private boolean isStringEmpty(String s){
        if(s == null || s.trim().equals("")){
            return false;
        }else{
            return true;
        }
    }

    class RegisterTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params){
            //String urlString = "http://idex.tk/FileUpload.php";
            try{
                /*URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setUseCaches(false);

                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setRequestProperty("name", KasunActivity.name);
                httpURLConnection.setRequestProperty("email", KasunActivity.email);
                httpURLConnection.setRequestProperty("password", KasunActivity.password);

                int responseCode = httpURLConnection.getResponseCode();
                return httpURLConnection.getResponseMessage();*/
                return NetConnection.call("register.php?name="+KasunActivity.name+"&email="+KasunActivity.email+"&password="+KasunActivity.password);

            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s){
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        }
    }

}
