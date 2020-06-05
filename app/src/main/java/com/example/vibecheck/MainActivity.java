package com.example.vibecheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    static boolean active = false;
    static MainActivity instance;

    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        active = true;
        instance = this;
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrate();
            }
        });

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECEIVE_SMS)){

            }
            else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS}, 0);
            }
        }


        Intent intent = getIntent();
        String[] text = intent.getStringArrayExtra("Text");
        //Log.e("Flag", "HELLO");
        //Log.e("Flag",Integer.toString(text.length));
        if(text != null){
            TextView textfield = (TextView)findViewById(R.id.editText);
            textfield.setText(text[1].substring(1) + ". " + text[0]);
            vibrate();
        }
    }

    public static MainActivity getInstance(){
        return instance;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        if (requestCode == 0) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            }
            else{
            }
        }
    }

    public void getSpeechInput(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 10);
        }else{
            Toast.makeText(this, "Device doesn't support speech input", Toast.LENGTH_SHORT).show();
        }
        
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView textResults = (TextView)findViewById(R.id.editText);
        switch (requestCode){
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.e("Flag",result.get(0));
                    textResults.setText(result.get(0));
                }
                break;
        }
    }

    public void vibrate(){
        //AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        //int CURRENT_MODE = audioManager.getMode();
        //audioManager.setRingerMode(AudioManager.RIbnxNGER_MODE_NORMAL);

        TextView textView = (TextView)findViewById(R.id.editText);
        CharSequence chars = textView.getText();
        String words = chars.toString();
        words = words.toLowerCase();
        ArrayList<Long> morseList = new ArrayList<Long>();
        morseList.add((long)0);
        for(int i = 0; i < words.length();i ++){
            char current = words.charAt(i);

            long[] morse;
            long PAUSE = 100;
            long DOT = 100;
            long DASH = 500;

            Log.e("Flag",Character.toString(current));
            switch(current) {
                case 'a':
                    morse = new long[]{DOT, PAUSE, DASH, PAUSE};
                    break;
                case 'b':
                    morse = new long[]{DASH, PAUSE, DOT, PAUSE, DOT, PAUSE, DOT, PAUSE};
                    break;
                case 'c':
                    morse = new long[]{DASH, PAUSE, DOT, PAUSE, DASH, PAUSE, DOT, PAUSE};
                    break;
                case 'd':
                    morse = new long[]{DASH, PAUSE, DOT, PAUSE, DOT, PAUSE};
                    break;
                case 'e':
                    morse = new long[]{DOT, PAUSE};
                    break;
                case 'f':
                    morse = new long[]{DOT, PAUSE, DOT, PAUSE, DASH, PAUSE, DOT, PAUSE};
                    break;
                case 'g':
                    morse = new long[]{DASH, PAUSE, DASH, PAUSE, DOT, PAUSE};
                    break;
                case 'h':
                    morse = new long[]{DOT, PAUSE, DOT, PAUSE, DOT, PAUSE, DOT, PAUSE};
                    break;
                case 'i':
                    morse = new long[]{DOT, PAUSE, DOT, PAUSE};
                    break;
                case 'j':
                    morse = new long[]{DOT, PAUSE, DASH, PAUSE, DASH, PAUSE, DASH, PAUSE};
                    break;
                case 'k':
                    morse = new long[]{DASH, PAUSE, DOT, PAUSE, DASH, PAUSE};
                    break;
                case 'l':
                    morse = new long[]{DOT, PAUSE, DASH, PAUSE, DOT, PAUSE, DOT, PAUSE};
                    break;
                case 'm':
                    morse = new long[]{DASH, PAUSE, DASH, PAUSE};
                    break;
                case 'n':
                    morse = new long[]{DASH, PAUSE, DOT, PAUSE};
                    break;
                case 'o':
                    morse = new long[]{DASH, PAUSE, DASH, PAUSE, DASH, PAUSE};
                    break;
                case 'p':
                    morse = new long[]{DOT, PAUSE, DASH, PAUSE, DASH, PAUSE, DOT, PAUSE};
                    break;
                case 'q':
                    morse = new long[]{DASH, PAUSE, DASH, PAUSE, DOT, PAUSE, DASH, PAUSE};
                    break;
                case 'r':
                    morse = new long[]{DOT, PAUSE, DASH, PAUSE, DOT, PAUSE};
                    break;
                case 's':
                    morse = new long[]{DOT, PAUSE, DOT, PAUSE, DOT, PAUSE};
                    break;
                case 't':
                    morse = new long[]{DASH, PAUSE};
                    break;
                case 'u':
                    morse = new long[]{DOT, PAUSE, DOT, PAUSE, DASH, PAUSE};
                    break;
                case 'v':
                    morse = new long[]{DOT, PAUSE, DOT, PAUSE, DOT, PAUSE, DASH, PAUSE};
                    break;
                case 'w':
                    morse = new long[]{DOT, PAUSE, DASH, PAUSE, DASH, PAUSE};
                    break;
                case 'x':
                    morse = new long[]{DASH, PAUSE, DOT, PAUSE, DOT, PAUSE, DASH, PAUSE};
                    break;
                case 'y':
                    morse = new long[]{DASH, PAUSE, DOT, PAUSE, DASH, PAUSE, DASH, PAUSE};
                    break;
                case 'z':
                    morse = new long[]{DASH, PAUSE, DASH, PAUSE, DOT, PAUSE, DOT, PAUSE};
                    break;
                case '1':
                    morse = new long[]{DOT, PAUSE, DASH, PAUSE, DASH, PAUSE, DASH, PAUSE, DASH, PAUSE};
                    break;
                case '2':
                    morse = new long[]{DOT, PAUSE, DOT, PAUSE, DASH, PAUSE, DASH, PAUSE, DASH, PAUSE};
                    break;
                case '3':
                    morse = new long[]{DOT, PAUSE, DOT, PAUSE, DOT, PAUSE, DASH, PAUSE, DASH, PAUSE};
                    break;
                case '4':
                    morse = new long[]{DOT, PAUSE, DOT, PAUSE, DOT, PAUSE, DOT, PAUSE, DASH, PAUSE};
                    break;
                case '5':
                    morse = new long[]{DOT, PAUSE, DOT, PAUSE, DOT, PAUSE, DOT, PAUSE, DOT, PAUSE};
                    break;
                case '6':
                    morse = new long[]{DASH, PAUSE, DOT, PAUSE, DOT, PAUSE, DOT, PAUSE, DOT, PAUSE};
                    break;
                case '7':
                    morse = new long[]{DASH, PAUSE, DASH, PAUSE, DOT, PAUSE, DOT, PAUSE, DOT, PAUSE};
                    break;
                case '8':
                    morse = new long[]{DASH, PAUSE, DASH, PAUSE, DASH, PAUSE, DOT, PAUSE, DOT, PAUSE};
                    break;
                case '9':
                    morse = new long[]{DASH, PAUSE, DASH, PAUSE, DASH, PAUSE, DASH, PAUSE, DOT, PAUSE};
                    break;
                case '0':
                    morse = new long[]{DASH, PAUSE, DASH, PAUSE, DASH, PAUSE, DASH, PAUSE, DASH, PAUSE};
                    break;
                case ' ':
                    morse = new long[]{0,PAUSE};
                    break;
                case '.':
                    morse = new long[]{0,PAUSE*3};
                    break;
                case ':':
                    morse = new long[]{0,PAUSE};
                    break;
                default:
                    morse = new long[]{DASH * 3};
            }
            for(int j=0;j<morse.length;j++){
                morseList.add(morse[j]);
            }
            morseList.set(morseList.size()-1,(morseList.get(morseList.size()-1)+PAUSE*3));
            //long sum = 0;
            //for(int j = 0; j < morse.length; j++){
            //    sum += morse[i];
            //}
            //long word = 2000 - sum;
            //SystemClock.sleep(word);]['

        }

        long[] morse = new long[morseList.size()];
        for(int i = 0; i < morse.length;i++){
            morse[i] = morseList.get(i);
        }
        vibrator.vibrate(morse,-1);

        //audioManager.setRingerMode(CURRENT_MODE);
    }






}
