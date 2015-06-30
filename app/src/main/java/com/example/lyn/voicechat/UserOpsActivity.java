package com.example.lyn.voicechat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class
        UserOpsActivity extends ActionBarActivity {
    public static final String EXTRA_GROUPID = "com.example.lyn.voicechat.groupid";
    private int mConferenceId;
    private Button mConferenceStartButton;
    private Button mConferenceFinishButton;
    private static final String TAG = UserOpsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ops);

        mConferenceId = getIntent().getIntExtra(EXTRA_GROUPID, 2);
        Log.e(TAG, mConferenceId + "mConferenceId = ");

        mConferenceStartButton = (Button)findViewById(R.id.conference_start);
        mConferenceStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print(mConferenceId);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_ops, menu);
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
