package com.battery;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    TextView tvPerc;
    private Timer timer = new Timer();
    private TimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) throws IllegalStateException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewFlipper flipper = (ViewFlipper)findViewById(R.id.flipper);
        flipper.startFlipping();

        tvPerc=(TextView)findViewById(R.id.tvPerc);

tvPerc.setText("20");

       /* timerTask = new TimerTask() {
            @Override
            public void run() {
                //refresh your textview
                Integer per = Integer.parseInt(tvPerc.getText().toString());
                if (per < 100) {
                    per = per + 20;
                } else {
                    per = 0;
                }
                tvPerc.setText(per.toString());
            }
        };
        timer.schedule(timerTask, 0, 1000);*/

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                Integer per = Integer.parseInt(tvPerc.getText().toString());
                                if (per < 100) {
                                    per = per + 20;
                                } else {
                                    per = 20;
                                }
                                tvPerc.setText(per.toString());
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();


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
