package com.example.lolaabudu.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

//create a Broadcasting class that extends BroadcastReceiver to receive and send broadcasts(for system broadcast and custom broadcast
public class CustomReceiver extends BroadcastReceiver {

    //create both in your MainActivity and your CustomReceiver class. You'll use this variable as the broadcast Intent action.
       private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        //BELOW IS FOR SYSTEM BROADCAST
        //this Class is receiving a system broadcast for the PowerReceiver(whether the phone is connected or disconnected to the charger)

        String intentAction = intent.getAction();

        if (intentAction != null) {
            String toastMessage = "unknown intent action";
            switch (intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power connected!";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power disconnected!";
                    break;
                //BELOW CASE IS FOR LOCAL BROADCAST
                case ACTION_CUSTOM_BROADCAST:
                    toastMessage = "Custom Broadcast Received";
                    break;
            }
            Toast.makeText(context, toastMessage,Toast.LENGTH_SHORT).show();



        }
    }
}
