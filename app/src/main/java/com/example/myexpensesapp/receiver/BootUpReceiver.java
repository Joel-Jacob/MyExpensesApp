package com.example.myexpensesapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.myexpensesapp.view.MainActivity;

public class BootUpReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        Log.d("TAG_X", "onRecieve");

        Toast.makeText(context, "Boot-Up Completed", Toast.LENGTH_LONG).show();

        Intent intentMain = new Intent(context, MainActivity.class);
        intentMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentMain);

    }
}
