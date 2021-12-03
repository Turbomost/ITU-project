package com.example.wis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_ID = "USER_ID";
    public static final String COLUMN_USER_LOGIN = "USER_LOGIN";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_CLASS = "USER_CLASS";

    public static final String SUBJECT_TABLE = "SUBJECT_TABLE";
    public static final String COLUMN_SUBJECT_ID = "SUBJECT_ID";
    public static final String COLUMN_SUBJECT_NAME = "SUBJECT_NAME";
    public static final String COLUMN_SUBJECT_SHORTCUT = "SUBJECT_SHORTCUT";
    public static final String COLUMN_SUBJECT_CLASS = "SUBJECT_CLASS";

    public static final String DEADLINE_TABLE = "DEADLINE_TABLE";
    public static final String COLUMN_DEADLINE_ID = "DEADLINE_ID";
    public static final String COLUMN_DEADLINE_NAME = "DEADLINE_NAME";
    public static final String COLUMN_DEADLINE_TIME = "DEADLINE_TIME";

    public static final String LECTURE_TABLE = "LECTURE_TABLE";
    public static final String COLUMN_LECTURE_ID = "LECTURE_ID";
    public static final String COLUMN_LECTURE_START = "LECTURE_START";
    public static final String COLUMN_LECTURE_END = "LECTURE_END";
    public static final String COLUMN_LECTURE_TYPE = "LECTURE_TYPE";

    public static final String USER_SUBJECT_TABLE = "USER_SUBJECT_TABLE";
    public static final String COLUMN_USER_SUBJECT_ID = "USER_SUBJECT_ID";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "tables.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_LOGIN + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_CLASS + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE " + SUBJECT_TABLE + " (" + COLUMN_SUBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SUBJECT_NAME + " TEXT, " + COLUMN_SUBJECT_SHORTCUT + " TEXT," + COLUMN_SUBJECT_CLASS + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE " + DEADLINE_TABLE + " (" + COLUMN_DEADLINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DEADLINE_NAME + " TEXT, " + COLUMN_DEADLINE_TIME + " TEXT," + COLUMN_SUBJECT_ID + " TEXT, FOREIGN KEY (" + COLUMN_SUBJECT_ID + ") REFERENCES " + SUBJECT_TABLE + " (" + COLUMN_SUBJECT_ID + ") )";
        sqLiteDatabase.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE " + LECTURE_TABLE + " (" + COLUMN_LECTURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LECTURE_START + " TEXT, " + COLUMN_LECTURE_END + " TEXT," + COLUMN_LECTURE_TYPE + " TEXT," + COLUMN_SUBJECT_ID + " TEXT, FOREIGN KEY (" + COLUMN_SUBJECT_ID + ") REFERENCES " + SUBJECT_TABLE + " (" + COLUMN_SUBJECT_ID + ") )";
        sqLiteDatabase.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE " + USER_SUBJECT_TABLE + " (" + COLUMN_USER_SUBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_ID + " INTEGER," + COLUMN_SUBJECT_ID + " INTEGER, FOREIGN KEY (" + COLUMN_USER_ID + ") REFERENCES " + USER_TABLE + " (" + COLUMN_USER_ID + ") , FOREIGN KEY (" + COLUMN_SUBJECT_ID + ") REFERENCES " + SUBJECT_TABLE + " (" + COLUMN_SUBJECT_ID + ") )";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SUBJECT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DEADLINE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LECTURE_TABLE);
        onCreate(sqLiteDatabase);

    }

    public boolean insertUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, user.getUser_id());
        values.put(COLUMN_USER_LOGIN, user.getUser_login());
        values.put(COLUMN_USER_PASSWORD, user.getUser_password());
        values.put(COLUMN_USER_NAME, user.getUser_name());
        values.put(COLUMN_USER_CLASS, user.getUser_class());

        long insert = db.insert(USER_TABLE, null, values);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertSubject(SubjectModel subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECT_ID, subject.getSubject_id());
        values.put(COLUMN_SUBJECT_NAME, subject.getSubject_name());
        values.put(COLUMN_SUBJECT_SHORTCUT, subject.getSubject_shortcut());
        values.put(COLUMN_SUBJECT_CLASS, subject.getSubject_class());

        long insert = db.insert(SUBJECT_TABLE, null, values);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertDeadline(DeadlineModel deadline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DEADLINE_ID, deadline.getDeadline_id());
        values.put(COLUMN_DEADLINE_NAME, deadline.getDeadline_name());
        values.put(COLUMN_DEADLINE_TIME, deadline.getDeadline_time());
        values.put(COLUMN_SUBJECT_ID, deadline.getSubject_id());

        long insert = db.insert(DEADLINE_TABLE, null, values);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertLecture(LectureModel lecture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LECTURE_ID, lecture.getLecture_id());
        values.put(COLUMN_LECTURE_START, lecture.getLecture_start());
        values.put(COLUMN_LECTURE_END, lecture.getLecture_end());
        values.put(COLUMN_LECTURE_TYPE, lecture.getLecture_type());
        values.put(COLUMN_SUBJECT_ID, lecture.getSubject_id());

        long insert = db.insert(LECTURE_TABLE, null, values);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertUserSubject(UserSubjectModel usersubject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_SUBJECT_ID, usersubject.getUser_subject_id());
        values.put(COLUMN_USER_ID, usersubject.getUser_id());
        values.put(COLUMN_SUBJECT_ID, usersubject.getSubject_id());

        long insert = db.insert(USER_SUBJECT_TABLE, null, values);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_TABLE, null, null);
        db.close();
    }

    public void deleteAllSubjects() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SUBJECT_TABLE, null, null);
        db.close();
    }

    public void deleteAllDeadlines() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DEADLINE_TABLE, null, null);
        db.close();
    }
    public void deleteAllLectures() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LECTURE_TABLE, null, null);
        db.close();
    }

    public void deleteAllUserSubjects() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_SUBJECT_TABLE, null, null);
        db.close();
    }

    //vsetci users
    public List<UserModel> getAllUsers() {

        List<UserModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int user_id = cursor.getInt(0);
                String user_login = cursor.getString(1);
                String user_password = cursor.getString(2);
                String user_name = cursor.getString(3);
                String user_class = cursor.getString(4);

                UserModel newmodel = new UserModel(user_id, user_login, user_password, user_name, user_class);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }

        cursor.close();
        db.close();
        return returnList;

    }

    //vsetky predmety
    public List<SubjectModel> getAllSubjects() {

        List<SubjectModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + SUBJECT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int subject_id = cursor.getInt(0);
                String subject_name = cursor.getString(1);
                String subject_shortcut = cursor.getString(2);
                String subject_class = cursor.getString(3);

                SubjectModel newmodel = new SubjectModel(subject_id, subject_name, subject_shortcut, subject_class);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }

        cursor.close();
        db.close();
        return returnList;

    }

    //vsetky terminy
    public List<DeadlineModel> getAllDeadlines() {

        List<DeadlineModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + DEADLINE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int deadline_id = cursor.getInt(0);
                String deadline_name = cursor.getString(1);
                String deadline_time = cursor.getString(2);
                int subject_id = cursor.getInt(3);

                DeadlineModel newmodel = new DeadlineModel(deadline_id, deadline_name, deadline_time, subject_id);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }

        cursor.close();
        db.close();
        return returnList;

    }
    //vsetky terminy konkretneho predmetu
    public List<DeadlineModel> getSubjectDeadlines(int sub_id) {
        List<DeadlineModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + DEADLINE_TABLE + " WHERE (" + COLUMN_SUBJECT_ID + " = " + sub_id + ")";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int deadline_id = cursor.getInt(0);
                String deadline_name = cursor.getString(1);
                String deadline_time = cursor.getString(2);
                int subject_id = cursor.getInt(3);

                DeadlineModel newmodel = new DeadlineModel(deadline_id, deadline_name, deadline_time, subject_id);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }
        cursor.close();
        db.close();
        return returnList;
    }

    //vsetky prednasky/cvika
    public List<LectureModel> getAllLectures() {

        List<LectureModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + LECTURE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int lecture_id = cursor.getInt(0);
                String lecture_start = cursor.getString(1);
                String lecture_end = cursor.getString(2);
                String lecture_type = cursor.getString(3);
                int subject_id = cursor.getInt(4);

                LectureModel newmodel = new LectureModel(lecture_id, lecture_start, lecture_end, lecture_type, subject_id);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }

        cursor.close();
        db.close();
        return returnList;

    }

    //vsetky prednasky/cvika konkretneho predmetu
    public List<LectureModel> getSubjectLectures(int sub_id) {
        List<LectureModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + LECTURE_TABLE + " WHERE (" + COLUMN_SUBJECT_ID + " = " + sub_id + ")";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int lecture_id = cursor.getInt(0);
                String lecture_start = cursor.getString(1);
                String lecture_end = cursor.getString(2);
                String lecture_type = cursor.getString(3);
                int subject_id = cursor.getInt(4);

                LectureModel newmodel = new LectureModel(lecture_id, lecture_start, lecture_end, lecture_type, subject_id);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }

        cursor.close();
        db.close();
        return returnList;
    }

    //vsetky predmety konkretneho usera
    public List<SubjectModel> getAllUserSubjects(int user_id) {

        List<SubjectModel> returnList = new ArrayList<>();

        String queryString = "SELECT " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + ", " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_NAME + ", " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_SHORTCUT + ", " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_CLASS +  " FROM " + SUBJECT_TABLE+ " INNER JOIN " +USER_SUBJECT_TABLE + " ON " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " = " + USER_SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " WHERE " + USER_SUBJECT_TABLE + "." + COLUMN_USER_ID + " = " + user_id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int subject_id = cursor.getInt(0);
                String subject_name = cursor.getString(1);
                String subject_shortcut = cursor.getString(2);
                String subject_class = cursor.getString(3);

                SubjectModel newmodel = new SubjectModel(subject_id, subject_name, subject_shortcut, subject_class);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }

        cursor.close();
        db.close();
        return returnList;

    }

    //vsetky prednasky/cvika konkretneho usera
    public List<LectureModel> getAllUserLectures(int user_id) {

        List<LectureModel> returnList = new ArrayList<>();

        String queryString = "SELECT " + LECTURE_TABLE + "." + COLUMN_LECTURE_ID + ", " + LECTURE_TABLE + "." + COLUMN_LECTURE_START + ", " + LECTURE_TABLE + "." + COLUMN_LECTURE_END + ", " + LECTURE_TABLE + "." + COLUMN_LECTURE_TYPE  + ", " + LECTURE_TABLE + "." + COLUMN_SUBJECT_ID  +  " FROM " + LECTURE_TABLE + " INNER JOIN " + USER_SUBJECT_TABLE + " ON " + LECTURE_TABLE + "." + COLUMN_SUBJECT_ID + " = " + USER_SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " WHERE " + USER_SUBJECT_TABLE + "." + COLUMN_USER_ID + " = " + user_id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int lecture_id = cursor.getInt(0);
                String lecture_start = cursor.getString(1);
                String lecture_end = cursor.getString(2);
                String lecture_type = cursor.getString(3);
                int subject_id = cursor.getInt(4);

                LectureModel newmodel = new LectureModel(lecture_id, lecture_start, lecture_end, lecture_type, subject_id);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }

        cursor.close();
        db.close();
        return returnList;

    }

    //predmety konkretneho rocnika
    public List<SubjectModel> getClassSubjects(String sub_class) {

        List<SubjectModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + SUBJECT_TABLE + " WHERE (" + COLUMN_SUBJECT_ID + " = \"" + sub_class + "\")";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int subject_id = cursor.getInt(0);
                String subject_name = cursor.getString(1);
                String subject_shortcut = cursor.getString(2);
                String subject_class = cursor.getString(3);

                SubjectModel newmodel = new SubjectModel(subject_id, subject_name, subject_shortcut, subject_class);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }

        cursor.close();
        db.close();
        return returnList;

    }

    public void insertSampleData(Context context) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        UserModel user = new UserModel();
        SubjectModel subject = new SubjectModel();
        DeadlineModel deadline = new DeadlineModel();
        LectureModel lecture = new LectureModel();
        UserSubjectModel usersubject = new UserSubjectModel();


        dataBaseHelper.deleteAllSubjects();
        dataBaseHelper.deleteAllUsers();
        dataBaseHelper.deleteAllDeadlines();
        dataBaseHelper.deleteAllLectures();
        dataBaseHelper.deleteAllUserSubjects();

        //pridavanie userov
        user.setUser_id(1);
        user.setUser_login("xbella01");
        user.setUser_password("heslo123");
        user.setUser_name("Magdaléna Bellayová");
        user.setUser_class("3BIT");
        dataBaseHelper.insertUser(user);

        //pridavanie predmetov
        subject.setSubject_id(1);
        subject.setSubject_name("Systémy a Signály");
        subject.setSubject_shortcut("ISS");
        dataBaseHelper.insertSubject(subject);

        subject.setSubject_id(2);
        subject.setSubject_name("Matematicka analyza 1");
        subject.setSubject_shortcut("IMA1");
        dataBaseHelper.insertSubject(subject);

        subject.setSubject_id(3);
        subject.setSubject_name("Formalne jazyky");
        subject.setSubject_shortcut("IFJ");
        dataBaseHelper.insertSubject(subject);

        //pridavanie deadlinov
        deadline.setDeadline_id(1);
        deadline.setDeadline_name("Polsemestralna pisomka");
        deadline.setDeadline_time("15-10-2021 10:00:00");
        deadline.setSubject_id(1);
        dataBaseHelper.insertDeadline(deadline);

        deadline.setDeadline_id(2);
        deadline.setDeadline_name("Domaca uloha");
        deadline.setDeadline_time("20-10-2021 00:00:00");
        deadline.setSubject_id(2);
        dataBaseHelper.insertDeadline(deadline);

        //pridavanie lectures
        lecture.setLecture_id(1);
        lecture.setLecture_start("08:00");
        lecture.setLecture_end("11:00");
        lecture.setSubject_id(1);
        dataBaseHelper.insertLecture(lecture);

        lecture.setLecture_id(2);
        lecture.setLecture_start("10:00");
        lecture.setLecture_end("12:00");
        lecture.setSubject_id(2);
        dataBaseHelper.insertLecture(lecture);

        //pridavanie user subjects
        usersubject.setUser_subject_id(1);
        usersubject.setUser_id(1);
        usersubject.setSubject_id(1);
        dataBaseHelper.insertUserSubject(usersubject);

        usersubject.setUser_subject_id(2);
        usersubject.setUser_id(1);
        usersubject.setSubject_id(2);
        dataBaseHelper.insertUserSubject(usersubject);

    }

    public boolean checkUserExist(Context context, String username, String password) {
        /*
        String[] columns = {"username"};
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = "username=? and password = ?";
        String[] selectionArgs = {username, password};

        */

        String queryString = "SELECT * FROM " + USER_TABLE + " WHERE (" + COLUMN_USER_LOGIN + " = \"" + username + "\") AND (" + COLUMN_USER_PASSWORD + " = \"" + password + "\")";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        int count = cursor.getCount();

        cursor.close();
        close();
        db.close();

        if (count > 0) {
            SharedPref.saveSharedSetting(context, "SharedPref", "true");
            return true;
        } else {
            return false;
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + SUBJECT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db!=null){
            cursor = db.rawQuery(query,null);

        }
        return cursor;
    }

    public Cursor getUsername(String username){
        String query = "SELECT * FROM " + USER_TABLE + " WHERE (" + COLUMN_USER_LOGIN + " = \"" + username + "\")";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db!=null){
            cursor = db.rawQuery(query,null);

        }
        return cursor;
    }
}




    /*
    public  boolean deleteOne(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + LOGIN_TABLE + " WHERE " + COLUMN_ID + " = " + userModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }

    }
    */

