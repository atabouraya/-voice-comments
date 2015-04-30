package com.robusta.recorder.activity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.robusta.recorder.R;
import com.uraroji.garage.android.mp3recvoice.RecMicToMp3;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    static final String FILENAME = "audiofile.mp3";
    Button startRecording, stopRecording, playRecording, finishButton;
    static final String DIRECTORY = Environment.getExternalStorageDirectory()
            + "/pml/";
    MediaRecorder recorder;
    MediaPlayer player;
    private RecMicToMp3 mRecMicToMp3;
    File audioFile;
    Thread progressThread;
    Thread recordingThread;
    volatile int cP = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
