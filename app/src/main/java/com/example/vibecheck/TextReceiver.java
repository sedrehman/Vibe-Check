package com.example.vibecheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;

public class TextReceiver extends BroadcastReceiver{

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction() == SMS_RECEIVED){
            Bundle dataBundle = intent.getExtras();
            if(dataBundle!=null){
                Object[] mypdu = (Object[])dataBundle.get("pdus");
                final SmsMessage[] message = new SmsMessage[mypdu.length];
                for(int i = 0; i < mypdu.length;i++){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        String format = dataBundle.getString("format");
                        message[i] = SmsMessage.createFromPdu((byte[])mypdu[i],format);
                    }
                    else{
                        message[i] = SmsMessage.createFromPdu((byte[])mypdu[i]);
                    }
                    Intent intentMain = new Intent(context, MainActivity.class);
                    intentMain.putExtra("Text",new String[]{message[i].getMessageBody(),message[i].getOriginatingAddress()});
                    intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if(MainActivity.active == false) {
                        context.startActivity(intentMain);
                    }
                    else{
                        MainActivity main = MainActivity.getInstance();
                        TextView textfield = (TextView)main.findViewById(R.id.editText);
                        textfield.setText(message[i].getOriginatingAddress().substring(1) + ". " + message[i].getMessageBody());
                        main.vibrate();
                    }
                }
            }
        }
    }

}
