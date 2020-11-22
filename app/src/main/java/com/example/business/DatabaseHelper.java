package com.example.business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "PrestoData.db";
    public static final String TABLE_NAME = "User";
    public static final String COL_TABLE_ID = "_id"; //This SQL name is mandatory
    public static final String COL_TABLE_TYPE = "Type";
    public static final String COL_TABLE_NAME = "Name";
    public static final String COL_TABLE_USERNAME = "Username";
    public static final String COL_TABLE_PASSWORD = "Password";
    public static final String COL_TABLE_BNAME = "BusinessName";
    public static final String COL_TABLE_LOCATION = "Location";
    public static final String COL_TABLE_BTYPE = "BusinessType";
    public static final String COL_TABLE_BSTAGE = "BusinessStage";
    public static final String COL_TABLE_INVESTMENTTYPE = "InvestmentType";
    public static final String COL_TABLE_INVESTORTYPE = "InvestorType";
    public static final String COL_TABLE_BGOALS = "BusinessGoals";
    public static final String COL_TABLE_BEXPERIENCE = "BusinessExperience";
    public static final String COL_TABLE_SKILLS = "Skills";



    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TABLE_TYPE + ", "
                + COL_TABLE_NAME + ", " + COL_TABLE_USERNAME + ", " + COL_TABLE_PASSWORD  + ", " + COL_TABLE_BNAME + ", " +  COL_TABLE_LOCATION + ", " + COL_TABLE_BTYPE + ", "
                + COL_TABLE_BSTAGE + ", " + COL_TABLE_INVESTMENTTYPE + ", " + COL_TABLE_INVESTORTYPE + ", " + COL_TABLE_BGOALS + ", " + COL_TABLE_BEXPERIENCE + ", " + COL_TABLE_SKILLS +")");



    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    /**
     * Inserts specified record into task table.
     *
     *
     * @return true if successful
     */
    public boolean insertTask(String uname, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TABLE_USERNAME, uname);
        contentValues.put(COL_TABLE_PASSWORD, pass);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    /**
     * Returns all data from task table.
     *
     * @return all data from task table
     */
    public Cursor getAllTasks(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT *FROM " + TABLE_NAME, null);
        return result;
    }


    public boolean updateEntrepreneur(String username, String type, String name, String businessName, String location, String businessType, String stage, String investmentType, String investorType, String goals) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TABLE_TYPE, type);
        contentValues.put(COL_TABLE_NAME, name);
        contentValues.put(COL_TABLE_USERNAME, username);
        contentValues.put(COL_TABLE_BNAME, businessName);
        contentValues.put(COL_TABLE_LOCATION, location);
        contentValues.put(COL_TABLE_BTYPE, businessType);
        contentValues.put(COL_TABLE_BSTAGE, stage);
        contentValues.put(COL_TABLE_INVESTMENTTYPE, investmentType);
        contentValues.put(COL_TABLE_INVESTORTYPE, investorType);
        contentValues.put(COL_TABLE_BGOALS, goals);
        db.update(TABLE_NAME, contentValues, "Username = ?", new String[]{username});
        return true;
    }

    public boolean updateInvestor(String username, String type, String name, String location, String businessType, String investmentType, String experience, String skills) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TABLE_TYPE, type);
        contentValues.put(COL_TABLE_NAME, name);
        contentValues.put(COL_TABLE_USERNAME, username);
        contentValues.put(COL_TABLE_LOCATION, location);
        contentValues.put(COL_TABLE_BTYPE, businessType);
        contentValues.put(COL_TABLE_INVESTMENTTYPE, investmentType);
        contentValues.put(COL_TABLE_BEXPERIENCE, experience);
        contentValues.put(COL_TABLE_SKILLS, skills);
        db.update(TABLE_NAME, contentValues, "Username = ?", new String[]{username});

        return true;
    }










    public Integer deleteTask(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_TABLE_USERNAME + " = ?", new String[]{user});
    }





    /**
     * Removes record from task table.
     *
     * @param id identifier of record to remove
     * @return number of rows removed
     */





    public Integer deleteTask(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_TABLE_ID + " = ?", new String[]{String.valueOf(id)});
    }













    public Cursor getTask(String user) {
        Cursor c;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_TABLE_ID, COL_TABLE_TYPE, COL_TABLE_NAME, COL_TABLE_USERNAME, COL_TABLE_PASSWORD, COL_TABLE_BNAME, COL_TABLE_LOCATION, COL_TABLE_BTYPE, COL_TABLE_BSTAGE, COL_TABLE_INVESTMENTTYPE, COL_TABLE_INVESTORTYPE, COL_TABLE_BGOALS, COL_TABLE_BEXPERIENCE, COL_TABLE_SKILLS};
        String where = COL_TABLE_TYPE + "= 'INVESTOR'";
        c = db.query(
                true,
                TABLE_NAME,
                columns,
                where,
                null,
                null,
                null,
                null,
                null);

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getETask(String user) {
        Cursor c;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_TABLE_ID, COL_TABLE_TYPE, COL_TABLE_NAME, COL_TABLE_USERNAME, COL_TABLE_PASSWORD, COL_TABLE_BNAME, COL_TABLE_LOCATION, COL_TABLE_BTYPE, COL_TABLE_BSTAGE, COL_TABLE_INVESTMENTTYPE, COL_TABLE_INVESTORTYPE, COL_TABLE_BGOALS, COL_TABLE_BEXPERIENCE, COL_TABLE_SKILLS};
        String where = COL_TABLE_TYPE + "= 'ENTREPRENEUR'";
        c = db.query(
                true,
                TABLE_NAME,
                columns,
                where,
                null,
                null,
                null,
                null,
                null);

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    /**
     *
     * @return
     *
     */




    public String[] getAllColumns() {
        return new String[]  {COL_TABLE_ID, COL_TABLE_TYPE, COL_TABLE_NAME, COL_TABLE_USERNAME, COL_TABLE_PASSWORD, COL_TABLE_BNAME, COL_TABLE_LOCATION, COL_TABLE_BTYPE, COL_TABLE_BSTAGE, COL_TABLE_INVESTMENTTYPE, COL_TABLE_INVESTORTYPE, COL_TABLE_BGOALS, COL_TABLE_BEXPERIENCE, COL_TABLE_SKILLS};
    }


    /**
     *
     * @return
     */
    private long getSize(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return count;
    }

    public boolean checkUser(String user, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;
        c =db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Username ='" + user +"' AND Password ='" + pass +"'", null);
        if (c.getCount() <= 0){
            return true;
        }else{
            return false;
        }


    }

    public Cursor getType(String user, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;
        c =db.rawQuery("SELECT Type, Username, Password FROM " + TABLE_NAME + " WHERE Username ='" + user +"' AND Password ='" + pass +"'", null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;


    }
}
