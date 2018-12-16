package com.example.lolaabudu.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class HeadsetReceiver extends BroadcastReceiver {

    //this Class is receiving a system broadcast for the HeadsetReceiver(whether a headset is connected or disconnected)

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction != null) {
            String toastMessage = "unknown headset intent action";
            switch (intentAction){
                case Intent.ACTION_HEADSET_PLUG:
                    int state = intent.getIntExtra("state", -1);
                    if(state == 0) {
                        toastMessage = "Headset is unplugged!";
                    } else if (state == 1) {
                        toastMessage = "Headset connected!";
                    }
                    break;
//                case Intent.ACTION_POWER_DISCONNECTED:
//                    toastMessage = "Power disconnected!";
//                    break;
//                //BELOW CASE IS FOR LOCAL BROADCAST
//                case ACTION_CUSTOM_BROADCAST:
//                    toastMessage = "Custom Broadcast Received";
//                    break;
            }
            Toast.makeText(context, toastMessage,Toast.LENGTH_SHORT).show();
        }
    }
}
