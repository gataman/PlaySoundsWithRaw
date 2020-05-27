package com.gurcanataman.playsoundsinraw;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPlay = findViewById(R.id.btnPlay);
        spinner = findViewById(R.id.spinner);
        setSpinner();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null)
                mediaPlayer.start();
            }
        });


    }

    private void setSpinner() {
        final String[] soundNames = new String[]{"sound1", "sound2", "sound3", "sound4"};
        ArrayAdapter<String> soundAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, soundNames);
        spinner.setAdapter(soundAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                setMediaPlayer(soundNames[pos]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setMediaPlayer(String soundName) {
        // Buradaki soundName kısmı dinamik olarak sqlite'tan çekilecek
        int res_sound_id = getResources().getIdentifier(soundName, "raw", getPackageName());
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + res_sound_id);

        if(mediaPlayer!=null){
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this,uri);
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onDestroy();

    }
}
