package com.industrialmaster.im;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.industrialmaster.im.connection.NetConnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PWForget extends AppCompatActivity {

    EditText etemail,etoldpw,etnewpw;
    static String email,oldpw,newpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadeera);

        etemail = (EditText)findViewById(R.id.etemail);
        etoldpw = (EditText)findViewById(R.id.etoldpw);
        etnewpw = (EditText)findViewById(R.id.etnewpw);
    }

public  void send_mail (View v){

    email = etemail.getText().toString();
    oldpw = etoldpw.getText().toString();
    newpw = etnewpw.getText().toString();

//    if (isValidEmail(email)){
//        etemail.setError("Invalid Email");
//    }else
    if (isValidPassword(newpw)){
        etnewpw.setError("Lenth is short");
    }else {

        new Chengepw().execute();
    }

}

//    private boolean isValidEmail(String email) {
//        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }

    private boolean isValidPassword(String userpass) {
        if (userpass != null && userpass.length() > 4) {
            return true;
        }
        return false;
    }


    class Chengepw extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {

            return NetConnection.call("changepw.php?email="+PWForget.email+"&password="+PWForget.oldpw+"&newpassword="+PWForget.newpw);
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.equals("success")){
                Toast.makeText(getApplicationContext(),"Passwerd reseted",Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(getApplicationContext(),"Passwerd reset  fail",Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }
    }
}
