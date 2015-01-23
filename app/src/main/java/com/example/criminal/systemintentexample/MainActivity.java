package com.example.criminal.systemintentexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = (TextView)findViewById(R.id.txtConnect);
        Button btnTest = (Button)findViewById(R.id.btnTest);
        btnTest.setOnClickListener(this);


    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case(R.id.btnTest):
                //Call the method to test connectivity
                if(isConnected()) {
                    txtView.setText("YOU ARE CONNECTED TO INTERNET");
                }else{
                    //Show Toast
                    Toast.makeText(this,"YOU ARE NOT CONNECTED TO INTERNET",Toast.LENGTH_LONG).show();
                    //Open phone network wifi config
                    //Intent intent = new Intent(Intent.ACTION_MAIN);
                    //intent.setClassName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                    //startActivity(intent);

                    //Open data roaming settings
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
                    startActivity(intent);
                }

        }

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    @Override
    public void onResume(){
        super.onResume();
        if(isConnected()){
            txtView.setText("¡YOU ARE CONNECTED TO INTERNET NOW!");
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
