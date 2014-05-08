package edu.towson.cosc.classmate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class WiFiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {     
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        
        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI) 
            Toast.makeText(context, "Wifi connected", Toast.LENGTH_SHORT).show();
        else
        	Toast.makeText(context, "Wifi disconnected", Toast.LENGTH_SHORT).show();
    }
    
}