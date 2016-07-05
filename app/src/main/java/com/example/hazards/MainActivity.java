package com.example.hazards;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    public static final String TAG = MainActivity.class.getSimpleName();

    private EditText mEmailAddressField;
    private EditText mSiteNameField;
    private EditText mContractorsNameField;
    private EditText mSiteAddressField;
    private EditText mContractorCompanyField;
    private EditText mProjectIdField;
    private EditText mWorkTypeField;
    private EditText mHazardDescriptionField;
    private EditText mHazardMitigationField;
    protected String[] mJobArray;
    private CreatePDF mNewPDF = new CreatePDF();
    private Pattern pattern;
    private Matcher matcher;
    private SendEmail mSendEmail = new SendEmail();
    public static String DEST = ".pdf";
    public static String DEST2;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "WRITE_CONTACTS Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button submitButton = (Button) findViewById(R.id.submit_button);
        mEmailAddressField = (EditText) findViewById(R.id.email_address);
        mSiteNameField = (EditText) findViewById(R.id.site_name);
        mContractorsNameField = (EditText) findViewById(R.id.contractors_name);
        mSiteAddressField = (EditText) findViewById(R.id.site_address);
        mContractorCompanyField = (EditText) findViewById(R.id.contractor_company);
        mProjectIdField = (EditText) findViewById(R.id.project_id);
        mWorkTypeField = (EditText) findViewById(R.id.work_type);
        mHazardDescriptionField = (EditText) findViewById(R.id.hazard_description);
        mHazardMitigationField = (EditText) findViewById(R.id.hazard_mitigation);


        if (submitButton != null) {
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emailAddressField = mEmailAddressField.getText().toString() + "";
                    String siteNameField = mSiteNameField.getText().toString() + "";
                    String contractorsNameField = mContractorsNameField.getText().toString() + "";
                    String siteAddressField = mSiteAddressField.getText().toString() + "";
                    String contractorCompanyField = mContractorCompanyField.getText().toString() + "";
                    String projectIdField = mProjectIdField.getText().toString() + "";
                    String workTypeField = mWorkTypeField.getText().toString() + "";
                    String hazardDescriptionField = mHazardDescriptionField.getText().toString() + "";
                    String hazardMitigationField = mHazardMitigationField.getText().toString() + "";
                    final String[] mJobArray = new String[]{
                            emailAddressField,
                            siteNameField,
                            contractorsNameField,
                            siteAddressField,
                            contractorCompanyField,
                            projectIdField,
                            workTypeField,
                            hazardDescriptionField,
                            hazardMitigationField
                    };

                    createPdf(mJobArray);
                    Log.e(TAG, "pass_array");


                }

            });

        }


    }

    private void createPdf(String[] mJobArray) {

      /*  mEmailAddressField.addTextChangedListener(new TextValidator(mEmailAddressField) {
            @Override
            public void validate(TextView textView, String text) {

            }
        }); */


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED
                    ) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                // return;
            }
            // mNewPDF.startPDF(mJobArray);
        }
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/dir1/dir2");
        String dir2 = sdCard.getAbsolutePath() + "/Android/";
        final String DEST2 = dir2 + "Sitename" + mJobArray[1] + DEST;
        final String[] emailJobArray = mJobArray;


        new Thread(new Runnable() {



            @Override
            public void run() {
                try {
                    mNewPDF.createPdf(emailJobArray, DEST2);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                mSendEmail.Mail(DEST2, emailJobArray);
            }
        }).start();

    }



        /**
         * Validate hex with regular expression
         *
         * @param hex hex for validation
         * @return true valid hex, false invalid hex
         */
        public boolean validate(final String hex) {

            matcher = pattern.matcher(hex);
            return matcher.matches();
        }






        /*Bundle b = new Bundle();
        b.putStringArray("test", mJobArray);
        Intent intent = new Intent(this, CreatePDF.class);
        intent.putExtras(b);
        Log.e(TAG, "pass_array" + mJobArray[2]);
        startActivity(intent); */


    }


