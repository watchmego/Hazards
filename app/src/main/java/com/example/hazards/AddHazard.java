package com.example.hazards;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Reuben on 8/07/2016.
 */
public class AddHazard extends FragmentActivity {

    private static final String TAG = AddHazard.class.getSimpleName();
    private static String GUID;
    private CreateDB newDB = new CreateDB(this);

    AddProject saveHazard = new AddProject();
    private EditText mHazardNameField;
    private EditText mHazardDescriptionField;
    private EditText mHazardMitigationField;
    private String mHazardIdField = "1";
    private String hazardId;
    private int hazardCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hazard_view);
     /*   setContentView(R.layout.blankpage);

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            HazardFragment fragment1 = new HazardFragment();


            //add for loop here for showing list of hazards
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment1).commit();


        }
        */
        Intent intent = getIntent();
        GUID = intent.getStringExtra("GUID");
        hazardId = intent.getStringExtra("hazardId");
        hazardCount = intent.getIntExtra("hazardCount", hazardCount);
        Log.e(TAG, "getString at start of addhazard =" + GUID);
        Log.e(TAG, "get hazard count at start of add hazard" + hazardCount);
        hazardId = hazardCount + 1 + "";

        Button saveButton = (Button) findViewById(R.id.save_button);
        mHazardNameField = (EditText) findViewById(R.id.hazard_name);
        mHazardDescriptionField = (EditText) findViewById(R.id.hazard_description);
        mHazardMitigationField = (EditText) findViewById(R.id.hazard_mitigation);

        if (hazardId != null) {
            readHazard(GUID, "HAZARDS", hazardId);
        }
            saveButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    //String hazardId = mHazardIdField;
                    String hazardNameField = mHazardNameField.getText().toString() + "";
                    String hazardDescriptionField = mHazardDescriptionField.getText().toString() + "";
                    String hazardMitigationField = mHazardMitigationField.getText().toString() + "";
                    final String[] mHazardArray = new String[]{
                            hazardId,
                            GUID,
                            hazardNameField,
                            hazardDescriptionField,
                            hazardMitigationField
                    };

                    saveHazard(mHazardArray);
                    Log.e(TAG, "ReadHazard =" + GUID);
                   readHazard(GUID, "HAZARDS");
                    Log.e(TAG,"DB results" + newDB.result);
                    String[][] result = newDB.result;
                    startAddProject(result, GUID);


                }


            });

        }



    private void startAddProject(String[][] result, String GUID) {
        Intent intent = new Intent(this, AddProject.class);
        hazardId = null;
        newDB.queryDB(GUID, "PROJECTS", hazardId);
        intent.putExtra("result", result);
        Log.e(TAG, "GUID from AddHazard = " + GUID);
        intent.putExtra("GUID", GUID);
        startActivity(intent);
    }

    private void readHazard(final String GUID, final String tableNAME) {
        new Thread(new Runnable() {



            @Override
            public void run() {
                Log.e(TAG, "start hazard insert" + GUID);
                newDB.queryDB(GUID, tableNAME, hazardId);
            }
        }).start();

    }

    private void readHazard(final String GUID, final String tableNAME, final String hazardId) {
        new Thread(new Runnable() {



            @Override
            public void run() {
                Log.e(TAG, "start hazard insert" + GUID);
                newDB.queryDB(GUID, tableNAME, hazardId);
            }
        }).start();
    }




    private void saveHazard(final String[] mHazardArray) {

        new Thread(new Runnable() {



            @Override
            public void run() {
                Log.e(TAG, "start hazard insert" + mHazardArray[0]);
            newDB.insertHazard(mHazardArray);
            }
        }).start();
    }
}
