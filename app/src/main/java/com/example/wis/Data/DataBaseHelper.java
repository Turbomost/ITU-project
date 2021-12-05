/*
 * DataBaseHelper.java
 * Author     : xbella01
 * Creating database, filling with data and creating function for manipulation with database
 */

package com.example.wis.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.wis.Models.DeadlineModel;
import com.example.wis.Models.LectureModel;
import com.example.wis.Models.LectureViewModel;
import com.example.wis.Models.SubjectModel;
import com.example.wis.Models.UserDeadlineModel;
import com.example.wis.Models.UserLectureModel;
import com.example.wis.Models.UserModel;
import com.example.wis.Models.UserSubjectModel;

import java.util.ArrayList;
import java.util.List;

/*
---------------CREATING AND FILLING DATABASE----------------
 */

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
    public static final String COLUMN_LECTURE_DAY = "LECTURE_DAY";
    public static final String COLUMN_LECTURE_START = "LECTURE_START";
    public static final String COLUMN_LECTURE_END = "LECTURE_END";
    public static final String COLUMN_LECTURE_NAME = "LECTURE_NAME";
    public static final String COLUMN_LECTURE_TYPE = "LECTURE_TYPE";

    public static final String USER_SUBJECT_TABLE = "USER_SUBJECT_TABLE";
    public static final String COLUMN_USER_SUBJECT_ID = "USER_SUBJECT_ID";

    public static final String USER_DEADLINE_TABLE = "USER_DEADLINE_TABLE";
    public static final String COLUMN_USER_DEADLINE_ID = "USER_DEADLINE_ID";
    public static final String COLUMN_USER_DEADLINE_STATUS = "USER_DEADLINE_STATUS";

    public static final String USER_LECTURE_TABLE = "USER_LECTURE_TABLE";
    public static final String COLUMN_USER_LECTURE_ID = "USER_LECTURE_ID";
    public static final String COLUMN_USER_LECTURE_STATUS = "USER_LECTURE_STATUS";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "tables.db", null, 1);
    }

// creating tables in database and filling with data
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_LOGIN + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_CLASS + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE " + SUBJECT_TABLE + " (" + COLUMN_SUBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SUBJECT_NAME + " TEXT, " + COLUMN_SUBJECT_SHORTCUT + " TEXT," + COLUMN_SUBJECT_CLASS + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE " + DEADLINE_TABLE + " (" + COLUMN_DEADLINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DEADLINE_NAME + " TEXT, " + COLUMN_DEADLINE_TIME + " TEXT," + COLUMN_SUBJECT_ID + " INTEGER, FOREIGN KEY (" + COLUMN_SUBJECT_ID + ") REFERENCES " + SUBJECT_TABLE + " (" + COLUMN_SUBJECT_ID + ") )";
        sqLiteDatabase.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE " + LECTURE_TABLE + " (" + COLUMN_LECTURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LECTURE_DAY + " TEXT, " + COLUMN_LECTURE_START + " TEXT, " + COLUMN_LECTURE_END + " TEXT, " + COLUMN_LECTURE_NAME + " TEXT, " + COLUMN_LECTURE_TYPE + " TEXT, " + COLUMN_SUBJECT_ID + " INTEGER, FOREIGN KEY (" + COLUMN_SUBJECT_ID + ") REFERENCES " + SUBJECT_TABLE + " (" + COLUMN_SUBJECT_ID + ") )";
        sqLiteDatabase.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE " + USER_SUBJECT_TABLE + " (" + COLUMN_USER_SUBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_ID + " INTEGER," + COLUMN_SUBJECT_ID + " INTEGER, FOREIGN KEY (" + COLUMN_USER_ID + ") REFERENCES " + USER_TABLE + " (" + COLUMN_USER_ID + ") , FOREIGN KEY (" + COLUMN_SUBJECT_ID + ") REFERENCES " + SUBJECT_TABLE + " (" + COLUMN_SUBJECT_ID + ") )";
        sqLiteDatabase.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE " + USER_DEADLINE_TABLE + " (" + COLUMN_USER_DEADLINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_ID + " INTEGER," + COLUMN_DEADLINE_ID + " INTEGER," + COLUMN_USER_DEADLINE_STATUS + " INTEGER, FOREIGN KEY (" + COLUMN_USER_ID + ") REFERENCES " + USER_TABLE + " (" + COLUMN_USER_ID + ") , FOREIGN KEY (" + COLUMN_DEADLINE_ID + ") REFERENCES " + DEADLINE_TABLE + " (" + COLUMN_DEADLINE_ID + ") )";
        sqLiteDatabase.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE " + USER_LECTURE_TABLE + " (" + COLUMN_USER_LECTURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_ID + " INTEGER," + COLUMN_LECTURE_ID + " INTEGER," + COLUMN_USER_LECTURE_STATUS + " INTEGER, FOREIGN KEY (" + COLUMN_USER_ID + ") REFERENCES " + USER_TABLE + " (" + COLUMN_USER_ID + ") , FOREIGN KEY (" + COLUMN_LECTURE_ID + ") REFERENCES " + LECTURE_TABLE + " (" + COLUMN_LECTURE_ID + ") )";
        sqLiteDatabase.execSQL(createTableStatement);

        sqLiteDatabase.execSQL("INSERT INTO " + SUBJECT_TABLE + " VALUES (1, \"Signály a systémy\", \"ISS\", \"2BIT\")");
        sqLiteDatabase.execSQL("INSERT INTO " + SUBJECT_TABLE + " VALUES (2, \"Algoritmy\", \"IAL\", \"2BIT\")");
        sqLiteDatabase.execSQL("INSERT INTO " + SUBJECT_TABLE + " VALUES (3, \"Formální jazyky a překladače\", \"IFJ\", \"2BIT\")");
        sqLiteDatabase.execSQL("INSERT INTO " + SUBJECT_TABLE + " VALUES (4, \"Matematická analýza 2\", \"IMA2\", \"2BIT\")");
        sqLiteDatabase.execSQL("INSERT INTO " + SUBJECT_TABLE + " VALUES (5, \"Návrh počitačových systémů\", \"INP\", \"2BIT\")");
        sqLiteDatabase.execSQL("INSERT INTO " + SUBJECT_TABLE + " VALUES (6, \"Pravděpodobnost a statistika\", \"IPT\", \"2BIT\")");
        sqLiteDatabase.execSQL("INSERT INTO " + SUBJECT_TABLE + " VALUES (7, \"Tvorba uživatelských rozhraní\", \"ITU\", \"2BIT\")");
        sqLiteDatabase.execSQL("INSERT INTO " + SUBJECT_TABLE + " VALUES (8, \"Diskrétní matematika\", \"IDM\", \"1BIT\")");
        sqLiteDatabase.execSQL("INSERT INTO " + USER_TABLE + " VALUES (1, \"xbella01\",\"heslo123\", \"Magdaléna Bellayová\", \"2BIT\")");
        sqLiteDatabase.execSQL("INSERT INTO " + USER_TABLE + " VALUES (2, \"xmrkvi04\",\"heslo456\", \"Janko Mrkvička\", \"1BIT\")");
        sqLiteDatabase.execSQL("INSERT INTO " + DEADLINE_TABLE + " VALUES (1, \"1. domácí úloha\",\"03-12-2021\", 2)");
        sqLiteDatabase.execSQL("INSERT INTO " + DEADLINE_TABLE + " VALUES (2, \"2. domácí úloha\",\"04-12-2021\", 2)");
        sqLiteDatabase.execSQL("INSERT INTO " + DEADLINE_TABLE + " VALUES (3, \"Půlsemestrální písemka\",\"13-01-2022\", 3)");
        sqLiteDatabase.execSQL("INSERT INTO " + DEADLINE_TABLE + " VALUES (4, \"Projekt\",\"21-01-2022\", 7)");
        sqLiteDatabase.execSQL("INSERT INTO " + DEADLINE_TABLE + " VALUES (5, \"Projekt 1\",\"11-01-2022\", 5)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (1, \"Wed\", \"08:00\", \"10:50\", \"p\", \"p\",1)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (2, \"Fri\",\"13:00\", \"15:50\", \"p\",\"p\",1)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (3, \"Wed\",\"14:00\", \"16:50\",\"p\", \"p\",2)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (4, \"Wed\",\"11:00\", \"13:50\",\"p\", \"p\",3)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (5, \"Mon\",\"09:00\", \"10:50\",\"p\", \"p\",4)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (6, \"Mon\",\"12:00\", \"13:50\",\"p\", \"p\",6)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (7, \"Thu\",\"15:00\", \"17:50\",\"p\", \"p\",7)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (8, \"Tue\",\"10:00\", \"11:50\",\"Vítovec\", \"c\",4)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (9, \"Tue\",\"12:00\", \"13:50\",\"František Grezl\", \"c\",1)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (10,\"Mon\",\"15:00\", \"16:50\",\"Martin Kocour\", \"c\",1)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (11,\"Tue\",\"14:00\", \"16:50\",\"Jiří Hanák\", \"c\",1)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (12,\"Thu\",\"12:00\", \"13:50\",\"Anna Silnova\", \"c\",1)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (13,\"Thu\",\"10:00\", \"11:50\",\"Jiři Novak\", \"c\",1)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (14,\"Fri\",\"10:00\", \"11:50\",\"Fuchs\", \"c\",4)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (15,\"Tue\",\"10:00\", \"11:50\",\"Rebenda\", \"c\",4)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (16,\"Tue\",\"12:00\", \"13:50\",\"Vítovec\", \"c\",4)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (17,\"Wen\",\"10:00\", \"11:50\",\"Fuchs\", \"c\",4)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (18,\"Wen\",\"12:00\", \"13:50\",\"Hlavičková\", \"c\",6)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (19,\"Thu\",\"07:00\", \"08:50\",\"Fusek\", \"c\",6)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (20,\"Fri\",\"08:00\", \"09:50\",\"Hlavičková\", \"c\",6)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (21,\"Tue\",\"16:00\", \"17:50\",\"Fusek\", \"c\",6)");
        sqLiteDatabase.execSQL("INSERT INTO " + LECTURE_TABLE + " VALUES (22,\"Thu\",\"16:00\", \"17:50\",\"Hlavičková\", \"c\",6)");
        sqLiteDatabase.execSQL("INSERT INTO " + USER_SUBJECT_TABLE + " VALUES (1,1,1)");
        sqLiteDatabase.execSQL("INSERT INTO " + USER_SUBJECT_TABLE + " VALUES (2,1,2)");
        sqLiteDatabase.execSQL("INSERT INTO " + USER_SUBJECT_TABLE + " VALUES (3,1,3)");
        sqLiteDatabase.execSQL("INSERT INTO " + USER_SUBJECT_TABLE + " VALUES (4,1,4)");
        sqLiteDatabase.execSQL("INSERT INTO " + USER_SUBJECT_TABLE + " VALUES (5,1,5)");
        sqLiteDatabase.execSQL("INSERT INTO " + USER_SUBJECT_TABLE + " VALUES (6,1,6)");
        sqLiteDatabase.execSQL("INSERT INTO " + USER_SUBJECT_TABLE + " VALUES (7,1,7)");
        sqLiteDatabase.execSQL("INSERT INTO " + USER_SUBJECT_TABLE + " VALUES (8,2,8)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SUBJECT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DEADLINE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LECTURE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_SUBJECT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_DEADLINE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_LECTURE_TABLE);
        onCreate(sqLiteDatabase);

    }

    /*
     ---------------FUNCTIONS FOR WORK WITH DATABASE----------------
    */

    //Inserting item to table

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
        values.put(COLUMN_LECTURE_DAY, lecture.getLecture_day());
        values.put(COLUMN_LECTURE_START, lecture.getLecture_start());
        values.put(COLUMN_LECTURE_END, lecture.getLecture_end());
        values.put(COLUMN_LECTURE_NAME, lecture.getLecture_name());
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


    public boolean insertUserDeadline(UserDeadlineModel userdeadline) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_DEADLINE_ID, userdeadline.getUser_deadline_id());
        values.put(COLUMN_USER_ID, userdeadline.getUser_id());
        values.put(COLUMN_DEADLINE_ID, userdeadline.getDeadline_id());
        values.put(COLUMN_USER_DEADLINE_STATUS, userdeadline.getUser_deadline_status());

        long insert = db.insert(USER_DEADLINE_TABLE, null, values);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertUserLecture(UserLectureModel userlecture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_LECTURE_ID, userlecture.getUser_lecture_id());
        values.put(COLUMN_USER_ID, userlecture.getUser_id());
        values.put(COLUMN_LECTURE_ID, userlecture.getLecture_id());
        values.put(COLUMN_USER_LECTURE_STATUS, userlecture.getUser_lecture_status());

        long insert = db.insert(USER_LECTURE_TABLE, null, values);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    // updating
    public void updateUserDeadlineStatus(int user_id, int deadline_id, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String queryString = "SELECT * FROM " + USER_DEADLINE_TABLE + " WHERE (" + COLUMN_USER_ID + " = \"" + user_id + "\") AND (" + COLUMN_DEADLINE_ID + " = \"" + deadline_id + "\")";
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            values.put(COLUMN_USER_DEADLINE_STATUS, status);
            db.update(USER_DEADLINE_TABLE, values,
                    COLUMN_USER_ID + " = ? AND " + COLUMN_DEADLINE_ID + " = ?",
                    new String[]{String.valueOf(user_id), String.valueOf(deadline_id)});
        } else {
            values.put(COLUMN_USER_ID, user_id);
            values.put(COLUMN_DEADLINE_ID, deadline_id);
            values.put(COLUMN_USER_DEADLINE_STATUS, status);
            db.insert(USER_DEADLINE_TABLE, null, values);
        }

    }

    public void updateUserLectureStatus(int user_id, int lecture_id, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String queryString = "SELECT * FROM " + USER_LECTURE_TABLE + " WHERE (" + COLUMN_USER_ID + " = \"" + user_id + "\") AND (" + COLUMN_LECTURE_ID + " = \"" + lecture_id + "\")";
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            values.put(COLUMN_USER_LECTURE_STATUS, status);
            db.update(USER_LECTURE_TABLE, values,
                    COLUMN_USER_ID + " = ? AND " + COLUMN_LECTURE_ID + " = ?",
                    new String[]{String.valueOf(user_id), String.valueOf(lecture_id)});
        } else {
            values.put(COLUMN_USER_ID, user_id);
            values.put(COLUMN_LECTURE_ID, lecture_id);
            values.put(COLUMN_USER_LECTURE_STATUS, status);
            db.insert(USER_LECTURE_TABLE, null, values);
        }

    }


    //deleting
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

    public void deleteAllUserDeadlines() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_DEADLINE_TABLE, null, null);
        db.close();
    }


    //List Creating
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
    public List<LectureModel> getAllLectures() {

        List<LectureModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + LECTURE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int lecture_id = cursor.getInt(0);
                String lecture_day = cursor.getString(1);
                String lecture_start = cursor.getString(2);
                String lecture_end = cursor.getString(3);
                String lecture_name = cursor.getString(4);
                String lecture_type = cursor.getString(5);
                int subject_id = cursor.getInt(6);

                LectureModel newmodel = new LectureModel(lecture_id, lecture_day, lecture_start, lecture_end, lecture_name, lecture_type, subject_id);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }

        cursor.close();
        db.close();
        return returnList;

    }

    //Deadlines for one subject
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
    public List<DeadlineModel> getUserDeadlinesList(int user_id, String time) {
        List<DeadlineModel> returnList = new ArrayList<>();

        String queryString = "SELECT " + DEADLINE_TABLE + "." + COLUMN_DEADLINE_ID + ", " + DEADLINE_TABLE + "." + COLUMN_DEADLINE_NAME + ", " + DEADLINE_TABLE + "." + COLUMN_DEADLINE_TIME + ", " + DEADLINE_TABLE + "." + COLUMN_SUBJECT_ID + " FROM " + DEADLINE_TABLE + " INNER JOIN " + USER_SUBJECT_TABLE + " ON " + DEADLINE_TABLE + "." + COLUMN_SUBJECT_ID + " = " + USER_SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " WHERE (" + USER_SUBJECT_TABLE + "." + COLUMN_USER_ID + " = " + user_id + ") AND ("+  DEADLINE_TABLE + "." + COLUMN_DEADLINE_TIME + " = \"" + time + "\")";

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

    //all lectures of one subject
    public List<LectureModel> getSubjectLectures(int sub_id) {
        List<LectureModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + LECTURE_TABLE + " WHERE (" + COLUMN_SUBJECT_ID + " = " + sub_id + ")";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int lecture_id = cursor.getInt(0);
                String lecture_day = cursor.getString(1);
                String lecture_start = cursor.getString(2);
                String lecture_end = cursor.getString(3);
                String lecture_name = cursor.getString(4);
                String lecture_type = cursor.getString(5);
                int subject_id = cursor.getInt(6);

                LectureModel newmodel = new LectureModel(lecture_id, lecture_day, lecture_start, lecture_end, lecture_name, lecture_type, subject_id);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }

        cursor.close();
        db.close();
        return returnList;
    }
    //subjects of one class
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

    //all lectures of user
    public List<LectureViewModel> getAllUserLecturesP(int user_id){

        List<LectureViewModel> returnList = new ArrayList<>();

        String queryString = "SELECT " + LECTURE_TABLE + "." + COLUMN_LECTURE_DAY + ", " + LECTURE_TABLE + "." + COLUMN_LECTURE_START + ", " + LECTURE_TABLE + "." + COLUMN_LECTURE_END + ", " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_SHORTCUT + ", " + LECTURE_TABLE + "." + COLUMN_LECTURE_TYPE + " FROM " + LECTURE_TABLE + " INNER JOIN " + USER_SUBJECT_TABLE + " ON " + LECTURE_TABLE + "." + COLUMN_SUBJECT_ID + " = " + USER_SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " INNER JOIN " + SUBJECT_TABLE + " ON " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " = " + USER_SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID +  " WHERE (" + USER_SUBJECT_TABLE + "." + COLUMN_USER_ID + " = " + user_id + ") AND (" + LECTURE_TABLE + "." + COLUMN_LECTURE_TYPE + "= \"p\") ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String lecture_day = cursor.getString(0);
                String lecture_start = cursor.getString(1);
                String lecture_end = cursor.getString(2);
                String subject_shortcut = cursor.getString(3);
                String lecture_type = cursor.getString(4);

                LectureViewModel newmodel = new LectureViewModel(lecture_day,lecture_start,lecture_end,subject_shortcut,lecture_type);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }

        cursor.close();
        db.close();
        return returnList;

    }

    //all exercises of user
    public List<LectureViewModel> getAllUserLecturesC(int user_id) {

        List<LectureViewModel> returnList = new ArrayList<>();

        String queryString = " SELECT " + LECTURE_TABLE + "." + COLUMN_LECTURE_DAY + ", " + LECTURE_TABLE + "." + COLUMN_LECTURE_START + ", " + LECTURE_TABLE + "." + COLUMN_LECTURE_END + ", " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_SHORTCUT + ", " + LECTURE_TABLE + "." + COLUMN_LECTURE_TYPE + " FROM " + USER_LECTURE_TABLE + " INNER JOIN "+ LECTURE_TABLE + " ON " +  LECTURE_TABLE + "." + COLUMN_LECTURE_ID + "=" + USER_LECTURE_TABLE + "." + COLUMN_LECTURE_ID + " INNER JOIN " + SUBJECT_TABLE + " ON " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " = " + LECTURE_TABLE + "." + COLUMN_SUBJECT_ID + " WHERE (" + COLUMN_USER_LECTURE_STATUS + " = 1) AND (" + USER_LECTURE_TABLE + "." + COLUMN_USER_ID + " = " + user_id +")";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {

                String lecture_day = cursor.getString(0);
                String lecture_start = cursor.getString(1);
                String lecture_end = cursor.getString(2);
                String subject_shortcut = cursor.getString(3);
                String lecture_type = cursor.getString(4);

                LectureViewModel newmodel = new LectureViewModel(lecture_day,lecture_start,lecture_end,subject_shortcut,lecture_type);
                returnList.add(newmodel);

            } while (cursor.moveToNext());

        } else {

            //
        }

        cursor.close();
        db.close();
        return returnList;

    }

    //getters
    public int getUserDeadlineStatus(int user_id, int deadline_id) {
        List<LectureModel> returnList = new ArrayList<>();

        String queryString = "SELECT " + COLUMN_USER_DEADLINE_STATUS + " FROM " + USER_DEADLINE_TABLE + " WHERE (" + COLUMN_USER_ID + " = \"" + user_id + "\") AND (" + COLUMN_DEADLINE_ID + " = \"" + deadline_id + "\")";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            Integer status = cursor.getInt(0);
            cursor.close();
            db.close();
            return status;
        }
        return 0;
    }

    public int getUserExcerciseStatus(int user_id, int excercise_id) {
        String queryString = "SELECT " + COLUMN_USER_LECTURE_STATUS + " FROM " + USER_LECTURE_TABLE + " WHERE (" + COLUMN_USER_ID + " = \"" + user_id + "\") AND (" + COLUMN_LECTURE_ID + " = \"" + excercise_id + "\")";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            int status = cursor.getInt(0);
            cursor.close();
            db.close();
            return status;
        }
        return 0;
    }

    public String getSubjectName(int subject_id) {
        String queryString = "SELECT " + COLUMN_SUBJECT_SHORTCUT + " FROM " + SUBJECT_TABLE + " WHERE (" + COLUMN_SUBJECT_ID + " = " + subject_id + ")";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            String subject_name = cursor.getString(0);
            cursor.close();
            db.close();
            return subject_name;
        }
        return null;
    }

    public int getSubjectId(String subject_name) {
        String queryString = "SELECT " + COLUMN_SUBJECT_ID + " FROM " + SUBJECT_TABLE + " WHERE (" + COLUMN_SUBJECT_NAME + " = \"" + subject_name + "\")";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            int subject_id = cursor.getInt(0);
            cursor.close();
            db.close();
            return subject_id;
        }
        return 0;
    }

    //checking

    public boolean checkUserExist(Context context, String username, String password) {

        String queryString = "SELECT * FROM " + USER_TABLE + " WHERE (" + COLUMN_USER_LOGIN + " = \"" + username + "\") AND (" + COLUMN_USER_PASSWORD + " = \"" + password + "\")";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            int user_id = cursor.getInt(0);
            SharedPref.saveSharedSetting(context, "UserID", Integer.toString(user_id));
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }

    }

    public boolean UserLectureStatusCheck(int subject_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String queryString = "SELECT * FROM " + USER_LECTURE_TABLE + " INNER JOIN " + LECTURE_TABLE + " ON " + USER_LECTURE_TABLE + "." + COLUMN_LECTURE_ID + " = " + LECTURE_TABLE + "." + COLUMN_LECTURE_ID + " WHERE (" + LECTURE_TABLE + "." + COLUMN_SUBJECT_ID + " = " + subject_id + ") AND (" + USER_LECTURE_TABLE + "." + COLUMN_USER_LECTURE_STATUS + " = 1) ";
        Cursor cursor = db.rawQuery(queryString, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if (count > 0) {
            return true;
        } else {
            return false;
        }

    }

    public int CheckSubjectShortcut(String shortcut, int user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "SELECT " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " FROM " + SUBJECT_TABLE + " INNER JOIN " + USER_SUBJECT_TABLE + " ON " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " = " + USER_SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " WHERE (" + SUBJECT_TABLE + "." + COLUMN_SUBJECT_SHORTCUT + "= \"" +shortcut+ "\") AND (" + USER_SUBJECT_TABLE + "." + COLUMN_USER_ID + " = " + user_id + ") ";
        Cursor cursor = db.rawQuery(queryString, null);
        int count = cursor.getCount();
        if (count > 0) {
            return cursor.getInt(0);
        } else {
            return -1;
        }

    }

    public boolean CheckDeadlineActivity(int deadline_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "SELECT " + COLUMN_USER_DEADLINE_STATUS + " FROM " + USER_DEADLINE_TABLE + " WHERE (" + COLUMN_DEADLINE_ID + "= " + deadline_id +")" ;
        Cursor cursor = db.rawQuery(queryString, null);
        int count = cursor.getCount();
        if(count>0){
            if(cursor.moveToFirst()){
                if (cursor.getInt(0)==1){
                    return false;
                }
            }
        }
        return true;
    }

    //cursor creating

    public Cursor getUserDeadlines(int user_id) {
        List<DeadlineModel> returnList = new ArrayList<>();

        String queryString = "SELECT " + DEADLINE_TABLE + "." + COLUMN_DEADLINE_ID + ", " + DEADLINE_TABLE + "." + COLUMN_DEADLINE_NAME + ", " + DEADLINE_TABLE + "." + COLUMN_DEADLINE_TIME + ", " + DEADLINE_TABLE + "." + COLUMN_SUBJECT_ID + " FROM " + DEADLINE_TABLE + " INNER JOIN " + USER_SUBJECT_TABLE + " ON " + DEADLINE_TABLE + "." + COLUMN_SUBJECT_ID + " = " + USER_SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " WHERE " + USER_SUBJECT_TABLE + "." + COLUMN_USER_ID + " = " + user_id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        return cursor;
    }

    public Cursor geActiveUserDeadlines(int user_id) {
        List<DeadlineModel> returnList = new ArrayList<>();

        String queryString = "SELECT " + DEADLINE_TABLE + "." + COLUMN_DEADLINE_ID + ", " + DEADLINE_TABLE + "." + COLUMN_DEADLINE_NAME + ", " + DEADLINE_TABLE + "." + COLUMN_DEADLINE_TIME + ", " + DEADLINE_TABLE + "." + COLUMN_SUBJECT_ID + " FROM " + DEADLINE_TABLE + " INNER JOIN " + USER_SUBJECT_TABLE + " ON " + DEADLINE_TABLE + "." + COLUMN_SUBJECT_ID + " = " + USER_SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " WHERE " + USER_SUBJECT_TABLE + "." + COLUMN_USER_ID + " = " + user_id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        return cursor;
    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + SUBJECT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }

    public Cursor getUsername(Integer user_ID) {
        String query = "SELECT * FROM " + USER_TABLE + " WHERE (" + COLUMN_USER_LOGIN + " = \"" + user_ID + "\")";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }

    public Cursor getAllUserSubjects(int user_id) {

        List<SubjectModel> returnList = new ArrayList<>();

        String queryString = "SELECT " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + ", " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_NAME + ", " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_SHORTCUT + ", " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_CLASS + " FROM " + SUBJECT_TABLE + " INNER JOIN " + USER_SUBJECT_TABLE + " ON " + SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " = " + USER_SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " WHERE " + USER_SUBJECT_TABLE + "." + COLUMN_USER_ID + " = " + user_id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }


    public Cursor getAllUserLectures(int user_id, int subject_id) {

        List<LectureModel> returnList = new ArrayList<>();

        String queryString = "SELECT " + LECTURE_TABLE + "." + COLUMN_LECTURE_ID + ", "+ LECTURE_TABLE + "." + COLUMN_LECTURE_DAY + ", " + LECTURE_TABLE + "." + COLUMN_LECTURE_START + ", " + LECTURE_TABLE + "." + COLUMN_LECTURE_END + ", "+ LECTURE_TABLE + "." + COLUMN_LECTURE_NAME + ", " + LECTURE_TABLE + "." + COLUMN_LECTURE_TYPE + " FROM " + LECTURE_TABLE + " INNER JOIN " + USER_SUBJECT_TABLE + " ON " + LECTURE_TABLE + "." + COLUMN_SUBJECT_ID + " = " + USER_SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " WHERE (" + USER_SUBJECT_TABLE + "." + COLUMN_USER_ID + " = " + user_id + ") AND (" + LECTURE_TABLE + "." + COLUMN_SUBJECT_ID + "=" + subject_id +") ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        return cursor;

    }

    public boolean getAllUserLecturesCount(int user_id, int subject_id) {

        List<LectureModel> returnList = new ArrayList<>();

        String queryString =  "SELECT " + LECTURE_TABLE + "." + COLUMN_LECTURE_TYPE + " FROM " + LECTURE_TABLE + " INNER JOIN " + USER_SUBJECT_TABLE + " ON " + LECTURE_TABLE + "." + COLUMN_SUBJECT_ID + " = " + USER_SUBJECT_TABLE + "." + COLUMN_SUBJECT_ID + " WHERE (" + USER_SUBJECT_TABLE + "." + COLUMN_USER_ID + " = " + user_id + ") AND (" + LECTURE_TABLE + "." + COLUMN_SUBJECT_ID + "=" + subject_id +") AND (" + LECTURE_TABLE + "." + COLUMN_LECTURE_TYPE + "=\"c\") " ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if (count > 0) {
            return true;
        } else {
            return false;
        }

    }

}
