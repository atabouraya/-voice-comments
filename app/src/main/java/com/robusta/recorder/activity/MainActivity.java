package com.robusta.recorder.activity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robusta.recorder.R;
import com.uraroji.garage.android.mp3recvoice.RecMicToMp3;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements
        MediaPlayer.OnCompletionListener, Runnable {
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


    ImageButton audioButton;
    LinearLayout audioMenuLinearLayout;
    LinearLayout audioRecordingMenuLinearLayout;
    LinearLayout uploadAudioMenuLinearLayout;

    TextView progressTV, progressTV2;

    ImageView audioTextViewLinearLayout;
    ImageView playStopImV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioButton = (ImageButton) findViewById(R.id.audioBtn);


        audioMenuLinearLayout = (LinearLayout) findViewById(R.id.audioMenuLinearLayout);
        audioRecordingMenuLinearLayout = (LinearLayout) findViewById(R.id.audioRecordingMenuLinearLayout);
        uploadAudioMenuLinearLayout = (LinearLayout) findViewById(R.id.uploadAudioLinearLayout);

        audioTextViewLinearLayout = (ImageView) findViewById(R.id.audioTriangle);


        playStopImV = (ImageView) findViewById(R.id.playStopImV);

        progressTV2 = (TextView) findViewById(R.id.progressTV2);
        progressTV = (TextView) findViewById(R.id.progressTV);
        File file = new File(DIRECTORY);
        file.mkdirs();
        mRecMicToMp3 = new RecMicToMp3(DIRECTORY + FILENAME, 8000);
        progressThread = new Thread(this);
        recordingThread = new Thread(new Runnable() {

            @Override
            public void run() {
                cP = 0;
                while (true) {
                    try {
                        Thread.sleep(16);
                        cP += 16;
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                    runOnUiThread(new Thread(new Runnable() {
                        public void run() {
                            String timer = "" + cP / 1000 / 60 + ":"
                                    + (cP / 1000) % 60 + ":" + (cP % 1000) / 60;
                            // progressTV2.setText(player.getCurrentPosition() *
                            // 100.0
                            // / player.getDuration() + "%");
                            progressTV.setText(timer);
                        }
                    }));

                }
            }
        });
    }

    public synchronized void audioHandler(View v) {
        progressThread.interrupt();
        recordingThread.interrupt();

        if (v.getId() == R.id.audioRecordTV) {
            mRecMicToMp3 = new RecMicToMp3(DIRECTORY + FILENAME, 8000);
            mRecMicToMp3.start();
            mRecMicToMp3.setHandle(new Handler() {
                @Override
                public synchronized void handleMessage(Message msg) {

                    switch (msg.what) {

                        case RecMicToMp3.MSG_REC_STOPPED:

                            if (player != null)
                                player.reset();
                            else {
                                player = new MediaPlayer();
                                player.setOnCompletionListener(MainActivity.this);
                            }
                            try {

                                player.setDataSource(DIRECTORY + FILENAME);

                            } catch (Exception e) {
                                e.printStackTrace();

                                throw new RuntimeException(
                                        "Exception in MediaPlayer.prepare", e);
                            }

                            audioRecordingMenuLinearLayout
                                    .setVisibility(View.INVISIBLE);
                            uploadAudioMenuLinearLayout.setVisibility(View.VISIBLE);
                            audioMenuLinearLayout.setVisibility(View.INVISIBLE);
                            progressTV2.setText("00:00:00");
                            break;
                    }

                }
            });
            recordingThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    cP = 0;
                    while (true) {
                        try {
                            Thread.sleep(16);
                            cP += 16;
                        } catch (InterruptedException e) {
                            e.printStackTrace();

                            return;
                        } catch (Exception e) {
                            e.printStackTrace();

                            return;
                        }
                        runOnUiThread(new Thread(new Runnable() {
                            public void run() {
                                String mns, secs, misec;
                                mns = ""
                                        + ((cP / 1000 / 60) > 9 ? cP / 1000 / 60
                                        : "0" + cP / 1000 / 60);
                                secs = ""
                                        + (((cP / 1000) % 60) > 9 ? (cP / 1000) % 60
                                        : "0" + (cP / 1000) % 60);
                                misec = ""
                                        + ((cP % 1000) / 60 > 9 ? (cP % 1000) / 60
                                        : "0" + (cP % 1000) / 60);
                                String timer = "" + mns + ":" + secs + ":"
                                        + misec;
                                // progressTV2.setText(player.getCurrentPosition()
                                // *
                                // 100.0
                                // / player.getDuration() + "%");
                                progressTV.setText(timer);
                            }
                        }));

                    }
                }
            });
            recordingThread.start();
            audioRecordingMenuLinearLayout.setVisibility(View.VISIBLE);
            audioMenuLinearLayout.setVisibility(View.INVISIBLE);
        }
        //
        // else if (v.getId() == R.id.existingAudioTV) {
        // Intent intent = new Intent();
        // intent.setType("audio/mpeg");
        // intent.setAction(Intent.ACTION_GET_CONTENT);
        // startActivityForResult(
        // Intent.createChooser(intent, "Select audio"),
        // ACTIVITY_SELECT_SOUND);
        //
        // }

        else if (v.getId() == R.id.uploadRecordingTV) {

//            SaveItem1
//                    .putExtra(SaveItemActivity.FILE_PATH, DIRECTORY + FILENAME);
        } else if (v.getId() == R.id.cancelUploadRecordingTV) {
            audioRecordingMenuLinearLayout.setVisibility(View.INVISIBLE);
            uploadAudioMenuLinearLayout.setVisibility(View.INVISIBLE);
            audioMenuLinearLayout.setVisibility(View.VISIBLE);
            playStopImV.setImageResource(R.drawable.half_play_button);

            if (!player.isPlaying()) {
                try {
                    player.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            player.stop();

        } else if (v.getId() == R.id.stopRecordingImV
                || v.getId() == R.id.audioRecordingTV) {
            if (cP < 1000) {
                try {
                    wait(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                    e1.printStackTrace();
                }
            }
            recordingThread.interrupt();
            mRecMicToMp3.stop();

        } else if (v.getId() == R.id.playStopImV) {
            if (player.isPlaying()) {
                playStopImV.setImageResource(R.drawable.half_play_button);
                player.seekTo(0);

                player.stop();
                progressTV2.setText("00:00:00");

            } else {
                progressTV2.setText("00:00:00");

                playStopImV.setImageResource(R.drawable.half_stop_button);

                try {

                    player.prepare();
                } catch (Exception e) {
                    e.printStackTrace();

                    throw new RuntimeException(
                            "Exception in MediaPlayer.prepare", e);
                }
                player.start();
                progressThread = new Thread(this);
                progressThread.start();
            }
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        playStopImV.setImageResource(R.drawable.half_play_button);
        progressThread.interrupt();
        player.seekTo(0);
        player.stop();
        progressTV2.setText("00:00:00");
    }


    @Override
    public void run() {
        int currentPosition = 0;
        int total = player.getDuration();
        while (player != null && currentPosition < total) {
            try {
                Thread.sleep(16);
                currentPosition = player.getCurrentPosition();
            } catch (InterruptedException e) {
                e.printStackTrace();

                return;
            } catch (Exception e) {
                e.printStackTrace();

                return;
            }
            runOnUiThread(new Thread(new Runnable() {
                public void run() {
                    String mns, secs, misec;
                    mns = ""
                            + ((player.getCurrentPosition() / 1000 / 60) > 9 ? player
                            .getCurrentPosition() / 1000 / 60 : "0"
                            + player.getCurrentPosition() / 1000 / 60);
                    secs = ""
                            + (((player.getCurrentPosition() / 1000) % 60) > 9 ? (player
                            .getCurrentPosition() / 1000) % 60 : "0"
                            + (player.getCurrentPosition() / 1000) % 60);
                    misec = ""
                            + ((player.getCurrentPosition() % 1000) / 60 > 9 ? (player
                            .getCurrentPosition() % 1000) / 60 : "0"
                            + (player.getCurrentPosition() % 1000) / 60);
                    String timer = "" + mns + ":" + secs + ":" + misec;

                    progressTV2.setText(timer);
                }
            }));

        }
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
