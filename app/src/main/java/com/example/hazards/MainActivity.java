package com.example.hazards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Reuben on 19/07/2016.
 */
public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mProjectButton;
    public static String GUID;
    private String hazardId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CreateDB newDB = new CreateDB(this);
        setContentView(R.layout.activity_main);



        mProjectButton = (Button) findViewById(R.id.add_project_button);

        mProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddProject();
            }
        });

        newDB.queryDB("blank", "PROJECTS", hazardId);
        if (newDB.count != 0) {
            String[][] mresult = newDB.result;
            Log.e(TAG, "queryDB result = " + mresult[0]);

        }





    }

    private void startAddProject() {
        CreateDB newDB = new CreateDB(this);
        Intent intent = new Intent(this, AddProject.class);
        newDB.getMAX("ID", "PROJECTS");
        Log.e(TAG,"projects max" + newDB.max);
       // if (newDB.max != null){
            int tempmax = Integer.valueOf(newDB.max);
        Log.e(TAG, "tempmax =" + tempmax);
            GUID = (tempmax + 1 + "");
        Log.e(TAG, "GUID =" + GUID);

        /*}
            else {
            GUID = "1";
        }*/

            intent.putExtra("GUID", GUID);
        startActivity(intent);
    }
}

