package com.example.hazards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;


/**
 * Created by Reuben on 8/07/2016.
 */
public class CreateDB extends SQLiteOpenHelper {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Projects.db";
    private int i;
    private int h;
    public static String[][] result;
    private static final String TAG = CreateDB.class.getSimpleName();
    public static int count;
    public static String max;


    public CreateDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";




    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_PROJECTS = "projects";
        public static final String COL_EMAIL_ADDRESS = "Email_Address";
        public static final String COL_SITENAME = "Site_Name";
        public static final String COL_CONTRACTORS_NAME = "Contractor_Name";
        public static final String COL_SITE_ADDRESS = "Site_Address";
        public static final String COL_CONCTRACTOR_COMPANY = "Contractor_Company";
        public static final String COL_PROJECT_ID = "Project_Id";
        public static final String COL_WORKTYPE = "Work_Type";


        //Hazard table
        public static final String TABLE_HAZARDS = "hazards";
        public static final String _ID = "ID";
        public static final String GUID = "GUID";
        public static final String COL_HAZARD_ID = "HAZARD_ID";
        public static final String COL_HAZARD_NAME = "Hazard_Name";
        public static final String COL_HAZARD_DESCRIPTION = "Hazard_Description";
        public static final String COL_HAZARD_MITIGATION = "Hazard_Mitigation";


    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        String SQL_CREATE_PROJECT_TABLE =
                "CREATE TABLE " + FeedEntry.TABLE_PROJECTS + " (" +
                        FeedEntry._ID + " integer PRIMARY KEY," +
                        FeedEntry.GUID + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COL_EMAIL_ADDRESS + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COL_CONTRACTORS_NAME + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COL_SITENAME + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COL_SITE_ADDRESS + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COL_CONCTRACTOR_COMPANY + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COL_PROJECT_ID + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COL_WORKTYPE + TEXT_TYPE +

                        // Any other options for the CREATE command
                        " )";
        String SQL_CREATE_HAZARD_TABLE =
                "CREATE TABLE " + FeedEntry.TABLE_HAZARDS + " (" +
                        FeedEntry._ID + " integer PRIMARY KEY ," +
                        FeedEntry.GUID + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COL_HAZARD_NAME + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COL_HAZARD_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COL_HAZARD_MITIGATION + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COL_HAZARD_ID + TEXT_TYPE +
                        ")";

        db.execSQL(SQL_CREATE_PROJECT_TABLE);
        db.execSQL(SQL_CREATE_HAZARD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + FeedEntry.TABLE_PROJECTS);
// Creating tables again
        onCreate(db);
    }

    public void insertHazard(String[] mHazardArray
                             //    , String GUID
    ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // cv.put(FeedEntry._ID, GUID);
        cv.put(String.valueOf(FeedEntry.GUID), mHazardArray[0]);
        cv.put(FeedEntry.COL_HAZARD_ID, mHazardArray[1]);
        cv.put(FeedEntry.COL_HAZARD_NAME, mHazardArray[2]);
        cv.put(FeedEntry.COL_HAZARD_DESCRIPTION, mHazardArray[3]);
        cv.put(FeedEntry.COL_HAZARD_MITIGATION, mHazardArray[4]);
        db.insert(FeedEntry.TABLE_HAZARDS, null, cv);
        //   db.close();
    }

    public void insertProject(String[] mJobArray
                              //, int GUID
    ) {
        Log.e(TAG, "insertProject:" + mJobArray[1]);
        Log.e(TAG, "insertProjectguid:" + mJobArray[0]);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FeedEntry.GUID, mJobArray[0]);
        cv.put(FeedEntry.COL_EMAIL_ADDRESS, mJobArray[1]);
        cv.put(FeedEntry.COL_SITENAME, mJobArray[2]);
        cv.put(FeedEntry.COL_CONTRACTORS_NAME, mJobArray[3]);
        cv.put(FeedEntry.COL_SITE_ADDRESS, mJobArray[4]);
        cv.put(FeedEntry.COL_CONCTRACTOR_COMPANY, mJobArray[5]);
        cv.put(FeedEntry.COL_PROJECT_ID, mJobArray[6]);
        cv.put(FeedEntry.COL_WORKTYPE, mJobArray[7]);
        db.insert(FeedEntry.TABLE_PROJECTS, null, cv);

    }

    public void updateProject (String[] mJobArray, String _ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String[] sqlArgs = new String[]{_ID};
        cv.put(FeedEntry.GUID, mJobArray[0]);
        cv.put(FeedEntry.COL_EMAIL_ADDRESS, mJobArray[1]);
        cv.put(FeedEntry.COL_SITENAME, mJobArray[2]);
        cv.put(FeedEntry.COL_CONTRACTORS_NAME, mJobArray[3]);
        cv.put(FeedEntry.COL_SITE_ADDRESS, mJobArray[4]);
        cv.put(FeedEntry.COL_CONCTRACTOR_COMPANY, mJobArray[5]);
        cv.put(FeedEntry.COL_PROJECT_ID, mJobArray[6]);
        cv.put(FeedEntry.COL_WORKTYPE, mJobArray[7]);
        db.update(FeedEntry.TABLE_PROJECTS, cv, FeedEntry._ID + " = " + _ID, null);

    }


    public void updateHazard(String[] mHazardArray, String _ID, String COL_HAZARD_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String[] sqlArgs = new String[]{_ID, COL_HAZARD_ID};
        cv.put(String.valueOf(FeedEntry.GUID), mHazardArray[1]);
        cv.put(FeedEntry.COL_HAZARD_NAME, mHazardArray[2]);
        cv.put(FeedEntry.COL_HAZARD_DESCRIPTION, mHazardArray[3]);
        cv.put(FeedEntry.COL_HAZARD_MITIGATION, mHazardArray[4]);
        db.update(FeedEntry.TABLE_HAZARDS, cv, FeedEntry._ID + "=?",
                sqlArgs);
    }

    //  public int updateProject(String[] mJobArray, int GUID);

    public String[][] queryDB(
            // int hazardId,
            String GUID, String tableName, String hazardId) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;

        //implement cursor.movetofirst

        String[] mHazardArray = {FeedEntry.GUID,
                FeedEntry.COL_HAZARD_NAME,
                FeedEntry.COL_HAZARD_DESCRIPTION,
                FeedEntry.COL_HAZARD_MITIGATION
        };
        if (GUID == "blank") {
            c = db.rawQuery(
                    "select * from " + tableName, null);
        }
        else if (hazardId != null){
            c = db.rawQuery(
                    "select * from " + tableName + " where GUID = " + GUID + " and Hazard_ID = "+ hazardId, null);
        }
        else {
            c = db.rawQuery(
                    "select * from " + tableName + " where GUID = " + GUID, null);
        }
        Log.e(TAG, "add project guid" + GUID);
        Log.e(TAG, "query:" + tableName + GUID);


        // + " and COL_HAZARD_ID = "
        //  + hazardId

        count = c.getCount();
        result = new String[count][8];

        h= 0;

        if (c != null && c.getCount() > 0) {

            if (tableName == "PROJECTS") {
                c.moveToFirst();
                Log.e(TAG, "get results" + c.getString(4));
                Log.e(TAG, "db count:" + count);
                Log.e(TAG, "cursor:" + c.getString(0));
                Log.e(TAG, "guid:" + GUID);
                Log.e(TAG, "cursor:" + c.getString(6));
                String data = c.getString(c.getColumnIndex("GUID"));
                Log.e(TAG, "get GUID " + c.getString(c.getColumnIndex("GUID")));
                for (h = 0; h < count; h++) {
                    // while (i < c.getColumnCount()) {result[i] = c.getString(i); i++;
                    // result[h][0] = c.getString(0);
                    result[h][0] = c.getString(c.getColumnIndex("GUID"));
                    result[h][1] = c.getString(c.getColumnIndex("Email_Address"));
                    result[h][2] = c.getString(c.getColumnIndex("Site_Name"));
                    result[h][3] = c.getString(c.getColumnIndex("Contractor_Name"));
                    result[h][4] = c.getString(c.getColumnIndex("Site_Address"));
                    result[h][5] = c.getString(c.getColumnIndex("Contractor_Company"));
                    result[h][6] = c.getString(c.getColumnIndex("Project_Id"));
                    result[h][7] = c.getString(c.getColumnIndex("Work_Type"));

                    if (h < count) {
                        c.moveToNext();
                    }
                    //  if (i < count) {c.moveToNext();}
                }


            } else {
                c.moveToFirst();
                Log.e(TAG, "table_name: " + tableName);
//            Log.e(TAG, "cursor:" + c.getString(0));
                // Log.e(TAG, "cursor:" + c.getString(1));
               // String data = c.getString(c.getColumnIndex("Email_Address"));
               // Log.e(TAG, "get emailaddress " + data);
                while (h < count) {
                    // while (i < c.getColumnCount()) {result[i] = c.getString(i); i++;
                    result[h][0] = c.getString(c.getColumnIndex("GUID"));
                    result[h][0] = c.getString(c.getColumnIndex("Hazard_Name"));
                    result[h][1] = c.getString(c.getColumnIndex("Hazard_Description"));
                    result[h][2] = c.getString(c.getColumnIndex("Hazard_Mitigation"));
                    h++;
                    if (h < count) {
                        c.moveToNext();
                    }
                    //  if (i < count) {c.moveToNext();}
                }
            }

//            Log.e(TAG, "DB results456" + result[1]);


               /* String[] result = {
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)
                }; */
            // do {} while (c.moveToNext());
            c.close();
        }


        return result;
    }


    public int getCount(String GUID, String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(
                "select COUNT(GUID) from " + tableName + " where GUID = " + GUID, null);

        if (c != null && c.getCount() > 0) {
            // c.moveToFirst();
            // String[] result = {c.getString(0)};
            //  int count = Integer.valueOf(result[0]);
            count = c.getCount();
        }

        Log.e(TAG, "countblah" + count);
        return count;
    }

    public String getMAX(
            // int hazardId,
            String column, String tableName) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;


        c = db.rawQuery(
                "select MAX(" + column + ") from " + tableName, null);


        // + " and COL_HAZARD_ID = "
        //  + hazardId
        c.moveToFirst();
        Log.e(TAG, "max = " + c.getString(0));
        if (c.getString(0) != null) {
            max = c.getString(0);
        } else {
            max = "0";
        }
        c.close();

        Log.e(TAG, "DB results" + max);
        return max;
    }

}


// Insert the new row, returning the primary key value of the new row
/*    long newRowId;
    newRowId = db.insert(
    CreateDB.FeedEntry.TABLE_NAME,
    CreateDB.FeedEntry.COLUMN_NAME_NULLABLE,
    values);
} */


