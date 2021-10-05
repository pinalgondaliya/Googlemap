package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // text to speech, speech to text
    // google map
    // alarm manager

    TextToSpeech tts;

    String message = "hello world";

    EditText e;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e = findViewById(R.id.editTextTextPersonName);
        t = findViewById(R.id.textView);

        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
            }
        });
    }

    public void speack(View view) {

        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

//                Voice v = new Voice();
//
//                for (Voice tmpVoice : tts.getVoices()) {
//                    if (tmpVoice.getName().equals("ar-XA-Standard-A")) {
//                       v = tmpVoice;
//                        break;
//                    }
//                }
//
//                if (v != null){
//                    tts.setVoice(v);
//                }

                tts.speak(e.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);

            }
        });

    }

    public void speechToText(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO},10);
        }

        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        startActivityForResult(i, 24);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 24) {
            List<String> myOuput = data.getStringArrayListExtra("android.speech.extra.RESULTS");
            Toast.makeText(MainActivity.this, "" + myOuput.get(0), Toast.LENGTH_SHORT).show();
            t.setText(myOuput.get(0));

            tts.speak(myOuput.get(0), TextToSpeech.QUEUE_FLUSH, null, null);

        }

    }
}