package com.example.lolaabudu.powerreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CustomReceiver mReceiver = new CustomReceiver();
    private HeadsetReceiver mReceiver2 = new HeadsetReceiver();

    //create both in your MainActivity and your CustomReceiver class. You'll use this variable as the broadcast Intent action.
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BELOW IS FOR SYSTEM BROADCAST(FOR CUSTOM RECEIVER CLASS)
        //Intent filters specify the types of intents a component can receive.
        IntentFilter filter = new IntentFilter();

        //When the system receives an Intent as a broadcast, it searches the broadcast receivers based on the action value specified in the IntentFilter object.
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);

        // Register the receiver using the MainActivity context.
        //Your receiver is active and able to receive broadcasts as long as your MainActivity is running.
        this.registerReceiver(mReceiver, filter);


        //BELOW IS FOR LOCAL BROADCAST
        //get an instance of LocalBroadcastManager and register your receiver with the custom Intent action, always register inside onCreate and unregister inside onDestroy
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,
                        new IntentFilter(ACTION_CUSTOM_BROADCAST));


        //BELOW IS FOR SYSTEM BROADCAST(FOR HEADSET RECEIVER CLASS)
        IntentFilter headsetFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        headsetFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        this.registerReceiver(mReceiver2, headsetFilter);

    }

    //unregister the receiever in onDestroy to save system resources and avoid leaks
    @Override
    protected void onDestroy() {
        //BELOW IS FOR SYSTEM BROADCAST (FOR CUSTOM RECEIVER CLASS)
        this.unregisterReceiver(mReceiver);
        super.onDestroy();

        //BELOW IS FOR LOCAL BROADCAST
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);


        //BELOW IS FOR SYSTEM BROADCAST (FOR HEADSET RECEIVER)
        unregisterReceiver(mReceiver);

    }

    //BELOW IS FOR THE LOCAL
    //created for the button's onclick method to send broadcast in my own app(aka Local Broadcast)
    public void sendCustomBroadcast(View view) {
        //passed the custom string created above as the argument
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);

        //below sends the broadcast locally within my app only
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);

    }
}


