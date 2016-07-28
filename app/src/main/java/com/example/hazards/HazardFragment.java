package com.example.hazards;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HazardFragment extends Fragment {


    private static final String TAG = AddProject.class.getSimpleName();
    public String GUID;
    private String hazardId;
    private LinearLayout layout;
    private CreateDB newDB = new CreateDB(getActivity());



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //create db context needs to be initialised after the fragment is attached (oncreateview)
        CreateDB newDB = new CreateDB(getActivity());
        View v;
        v = inflater.inflate(R.layout.hazardfragment, container, false);
        layout=(LinearLayout)v.findViewById(R.id.hazard_layout);
       // LinearLayout layout1 = new LinearLayout(getActivity());
       // layout1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));


       // GUID = AddProject.GUID;
        GUID = getActivity().getIntent().getExtras().getString("GUID");
;
        Log.e(TAG, "HazardFragment GUID" + GUID);
        newDB.queryDB(GUID, "HAZARDS", hazardId);
        String[][] mresult = newDB.result;

        int count = newDB.count;
        Log.e(TAG, "queryDB count = " + count);
        //String[] test = new String[];
        //test[0] = newDB.result[0];
        int i = 0;

        if (mresult.length != 0) {

            Log.e(TAG, "queryDB result = " + mresult[0]);
            final TextView[] hazardView = new TextView[count]; // create an empty array;

            for (i = 0; i < count; i++) {
                // create a new textview

                TextView rowTextView = new TextView(getActivity());
                rowTextView.setId(i);
                rowTextView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                // set some properties of rowTextView or something
                rowTextView.setText(mresult[i][3]);

                GUID = mresult[i][0];

                // add the textview to the linearlayout
                layout.addView(rowTextView);
                Log.e(TAG, "queryDB hazardviewArray = " + rowTextView);

                // save a reference to the textview for later
                hazardView[i] = rowTextView;
             //   ((LinearLayout) v).addView(hazardView[i]);
                hazardView[i].setOnClickListener(new OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        readHazard(GUID, "HAZARDS");
                        startHazard(GUID, hazardId);
                    }
                });
            }
/*
            TextView Paper = new TextView(v.getContext());
            layout1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            Paper.setText("Inserted TestText");
            ((LinearLayout) v).addView(Paper);
*/
        }
        return v;
    }

    private void startHazard(String GUID, String hazardId) {
        Intent intent = new Intent(getActivity(), AddHazard.class);
        intent.putExtra("GUID", GUID);
        intent.putExtra(GUID, hazardId);
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

}



