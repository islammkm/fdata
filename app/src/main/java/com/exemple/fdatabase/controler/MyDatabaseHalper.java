package com.exemple.fdatabase.controler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.exemple.fdatabase.Utils.Util;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Colie;
import com.exemple.fdatabase.models.Fourniseur;
import com.exemple.fdatabase.models.Livreur;
import com.exemple.fdatabase.models.Path;
import com.exemple.fdatabase.models.User;
import com.exemple.fdatabase.models.Vec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyDatabaseHalper extends SQLiteOpenHelper {
    public MyDatabaseHalper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null,Util.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(Util.CREATE_TABLE_USERS);
            db.execSQL(Util.CREATE_TABLE_CMND);
            db.execSQL(Util.CREATE_TABLE_COLIE);
            db.execSQL(Util.CREATE_TABLE_LIVREUR);
            db.execSQL(Util.CREATE_TABLE_PATH);
            db.execSQL(Util.CREATE_TABLE_VEC);
            db.execSQL(Util.CREATE_TABLE_FOURNISSEUR);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_COLIE);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_CMND);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_COLIE);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_LIVREUR);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_PATH);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_VEC);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_FOURNISSEUR);
    }
    public User getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;
        String[] columns = {Util.KEY_ID_USER, Util.KEY_FULLNAME, Util.KEY_USERNAME, Util.KEY_PASSWORD, Util.KEY_EMAIL};
        String selection =Util.KEY_USERNAME + "=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(Util.TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_USER)));
            user.setFullName(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_FULLNAME)));
            user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_PASSWORD)));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_EMAIL)));
            cursor.close();
        }
        return user;
    }

    public long insertUser(String fullname, String username, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_FULLNAME, fullname);
        values.put(Util.KEY_USERNAME, username);
        values.put(Util.KEY_PASSWORD, password);
        values.put(Util.KEY_EMAIL, email);
        long id = db.insert(Util.TABLE_USERS, null, values);
        db.close();
        return id;
    }

    public long insertLivreurs(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_USERNAME, username);
        values.put(Util.KEY_PASSWORD, password);
        long id = db.insert(Util.TABLE_LIVREUR, null, values);
        db.close();
        return id;
    }

    public long insertVec(String color, String nom, String date, int idLivreurs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_COLOR, color);
        values.put(Util.KEY_NOM, nom);
        values.put(Util.KEY_DATE, date);
        values.put(Util.KEY_ID_LIVREUR, idLivreurs);
        long id = db.insert(Util.TABLE_VEC, null, values);
        db.close();
        return id;
    }

    public long insertCMND(int userId, int livreurId, String adresse, String etat, int coteCMND, int nPhone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_USER_ID, userId);
        values.put(Util.KEY_LIVREUR_ID, livreurId);
        values.put(Util.KEY_ADRESSE, adresse);
        values.put(Util.KEY_ETAT, etat);
        values.put(Util.KEY_COTE_CMND, coteCMND);
        values.put(Util.KEY_NPHONE, nPhone);
        long id = db.insert(Util.TABLE_CMND, null, values);
        db.close();
        return id;
    }

    public long insertColie(int cmndId, int pathId, int fournisseurId, String date, String type, String lastPosition, String code, int coteColie, int poids) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_CMND_ID, cmndId);
        values.put(Util.KEY_ID_PATH, pathId);
        values.put(Util.KEY_ID_FOURNISSEUR, fournisseurId);
        values.put(Util.KEY_DATE, date);
        values.put(Util.KEY_TYPE, type);
        values.put(Util.KEY_LAST_POSITION, lastPosition);
        values.put(Util.KEY_CODE, code);
        values.put(Util.KEY_COTE_COLIE, coteColie);
        values.put(Util.KEY_POIDS, poids);
        long id = db.insert(Util.TABLE_COLIE, null, values);
        db.close();
        return id;
    }

    public long insertFournisseur(String nom, String adresse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NOM_F, nom);
        values.put(Util.KEY_ADRESSE_F, adresse);
        long id = db.insert(Util.TABLE_FOURNISSEUR, null, values);
        db.close();
        return id;
    }

    public long insertPath(String address, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_ADRESS_P, address);
        values.put(Util.KEY_DATE_P, date);
        long id = db.insert(Util.TABLE_PATH, null, values);
        db.close();
        return id;
    }
    public void insertPath(int idColie, String path,String address, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // Add more columns if needed
        values.put(Util.KEY_ADRESS_P, address);
        values.put(Util.KEY_DATE_P, date);
        // Insert the values into the table
        db.insert(Util.TABLE_PATH, null, values);
        db.close();
    }



    ///////////////get
    public Colie getColieById(int colieId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Colie colie = null;
        String[] columns = {
                Util.KEY_ID_COLIE,
                Util.KEY_CMND_ID,
                Util.KEY_ID_PATH,
                Util.KEY_ID_FOURNISSEUR,
                Util.KEY_DATE,
                Util.KEY_TYPE,
                Util.KEY_LAST_POSITION,
                Util.KEY_CODE,
                Util.KEY_COTE_COLIE,
                Util.KEY_POIDS
        };
        String selection = Util.KEY_ID_COLIE + "=?";
        String[] selectionArgs = {String.valueOf(colieId)};
        Cursor cursor = db.query(
                Util.TABLE_COLIE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            colie = new Colie(
                    cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_COLIE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_CMND_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_PATH)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_FOURNISSEUR)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_TYPE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_LAST_POSITION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_CODE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_COTE_COLIE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_POIDS))
            );
            cursor.close();
        }
        db.close();
        return colie;
    }
    public List<Cmd> getCMNDsByUserId(int userId) {
        List<Cmd> cmndList = new ArrayList<>();

        // استعلام SQL لاسترجاع كل CMND للمستخدم المحدد
        String selectQuery = "SELECT * FROM " + Util.TABLE_CMND + " WHERE " + Util.KEY_USER_ID + " = " + userId;

        // تنفيذ الاستعلام واسترجاع النتائج
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // التنقل عبر النتائج وإضافتها إلى قائمة ال CMNDs
        if (cursor.moveToFirst()) {
            do {
                Cmd cmnd = new Cmd();
                cmnd.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_CMND)));
                cmnd.setAdresse(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_ADRESSE)));
                cmnd.setEtat(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_ETAT)));
                cmnd.setCote(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_COTE_CMND)));
                cmnd.setNphone(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_NPHONE)));
                // يمكنك إضافة المزيد من البيانات إذا لزم الأمر

                // إضافة CMND إلى قائمة ال CMNDs
                cmndList.add(cmnd);
            } while (cursor.moveToNext());
        }
        // إغلاق الاتصالات
        cursor.close();
        db.close();

        // إرجاع قائمة ال CMNDs
        return cmndList;
    }

    public List<Colie> getColiesByCMNDId(int cmndId) {
        List<Colie> coliesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + Util.TABLE_COLIE +
                " WHERE " + Util.KEY_CMND_ID + " = " + cmndId;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // يتم تحويل البيانات من النتائج المسترجعة إلى كائنات Colie وإضافتها إلى قائمة coliesList
        if (cursor.moveToFirst()) {
            do {
                Colie colie = new Colie();
                colie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_COLIE)));
                colie.setCmndId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_CMND_ID)));
                colie.setFournisseurId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_FOURNISSEUR)));
                colie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_DATE)));
                colie.setType(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_TYPE)));
                colie.setLastPosition(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_LAST_POSITION)));
                colie.setCode(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_CODE)));
                colie.setCote(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_COTE_COLIE)));
                colie.setPoids(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_POIDS)));

                coliesList.add(colie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return coliesList;
    }

    public Colie getColieByCode(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Colie colie = null;

        String[] columns = {
                Util.KEY_ID_COLIE,
                Util.KEY_CMND_ID,
                Util.KEY_ID_PATH,
                Util.KEY_ID_FOURNISSEUR,
                Util.KEY_DATE,
                Util.KEY_TYPE,
                Util.KEY_LAST_POSITION,
                Util.KEY_CODE,
                Util.KEY_COTE_COLIE,
                Util.KEY_POIDS
        };

        // تحديد الشرط
        String selection = Util.KEY_CODE + " = ?";
        // قيمة الشرط
        String[] selectionArgs = {code};

        Cursor cursor = db.query(Util.TABLE_COLIE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            colie = new Colie();
            colie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_COLIE)));
            colie.setCmndId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_CMND_ID)));
            colie.setFournisseurId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_FOURNISSEUR)));
            colie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_DATE)));
            colie.setType(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_TYPE)));
            colie.setLastPosition(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_LAST_POSITION)));
            colie.setCode(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_CODE)));
            colie.setCote(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_COTE_COLIE)));
            colie.setPoids(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_POIDS)));

            cursor.close();
        }

        return colie;
    }
    public void updateColieLastPosition(int colieId, String newLastPosition) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_LAST_POSITION, newLastPosition);

        String selection = Util.KEY_ID_COLIE + " = ?";
        String[] selectionArgs = {String.valueOf(colieId)};

        int count = db.update(Util.TABLE_COLIE, values, selection, selectionArgs);
        db.close();
    }

    public Fourniseur getFournisseurById(int fournisseurId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Fourniseur fournisseur = null;
        String[] columns = {Util.KEY_ID_FOURNISSEUR, Util.KEY_NOM_F, Util.KEY_ADRESSE_F};
        String selection = Util.KEY_ID_FOURNISSEUR + " = ?";
        String[] selectionArgs = {String.valueOf(fournisseurId)};
        String sortOrder = null; // يمكنك تحديد ترتيب البيانات هنا إذا لزم الأمر

        Cursor cursor = db.query(
                Util.TABLE_FOURNISSEUR,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            String nom = cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_NOM_F));
            String adresse = cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_ADRESSE_F));

            fournisseur = new Fourniseur(nom, adresse);
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return fournisseur;
    }
    public Colie getColieById2(int colieId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Colie colie = null;

        String selectQuery = "SELECT * FROM " + Util.TABLE_COLIE + " WHERE " +  Util.KEY_ID_COLIE + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(colieId)});

        if (cursor != null && cursor.moveToFirst()) {
            colie = new Colie();
            colie.setId(cursor.getInt(cursor.getColumnIndexOrThrow( Util.KEY_ID_COLIE)));
            colie.setCmndId(cursor.getInt(cursor.getColumnIndexOrThrow( Util.KEY_CMND_ID)));
            colie.setFournisseurId(cursor.getInt(cursor.getColumnIndexOrThrow( Util.KEY_ID_FOURNISSEUR)));
            colie.setDate(cursor.getString(cursor.getColumnIndexOrThrow( Util.KEY_DATE)));
            colie.setType(cursor.getString(cursor.getColumnIndexOrThrow( Util.KEY_TYPE)));
            colie.setLastPosition(cursor.getString(cursor.getColumnIndexOrThrow( Util.KEY_LAST_POSITION)));
            colie.setCode(cursor.getString(cursor.getColumnIndexOrThrow( Util.KEY_CODE)));
//            colie.setCote(cursor.getInt(cursor.getColumnIndexOrThrow( Util.KEY_COTE_COLIE)));
            colie.setPoids(cursor.getInt(cursor.getColumnIndexOrThrow( Util.KEY_POIDS)));
        }

        if (cursor != null) {
            cursor.close();
        }
        return colie;
    }
    public Cmd getCmndByCmndId(int cmndId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cmd cmnd = null;

        String selectQuery = "SELECT * FROM " + Util.TABLE_CMND + " WHERE " + Util.KEY_ID_CMND + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(cmndId)});

        if (cursor != null && cursor.moveToFirst()) {
            cmnd = new Cmd();
            cmnd.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_CMND)));
            cmnd.setUserid(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_USER_ID)));
            cmnd.setLivreurId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_LIVREUR_ID)));
            cmnd.setAdresse(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_ADRESSE)));
            cmnd.setEtat(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_ETAT)));
            cmnd.setCote(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_COTE_CMND)));
            cmnd.setNphone(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_NPHONE)));
        }

        if (cursor != null) {
            cursor.close();
        }
        return cmnd;
    }
    public Livreur getLivreurById(int livreurId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Livreur livreur = null;

        String selectQuery = "SELECT * FROM " + Util.TABLE_LIVREUR + " WHERE " + Util.KEY_ID_LIVREUR + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(livreurId)});

        if (cursor != null && cursor.moveToFirst()) {
            livreur = new Livreur();
            livreur.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_LIVREUR)));
            livreur.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_USERNAME)));
            livreur.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_PASSWORD)));
        }

        if (cursor != null) {
            cursor.close();
        }
        return livreur;
    }
    public List<Path> getAllPaths() {
        List<Path> paths = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Util.TABLE_PATH;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                Path path = new Path(
                        cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_PATH)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_ADRESS_P)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_DATE_P))
                );
                paths.add(path);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return paths;
    }
    public void insertSamplePaths() {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < 10; i++) {
            ContentValues values = new ContentValues();
            values.put(Util.KEY_ADRESS_P, "Sample Address " + i);
            values.put(Util.KEY_DATE_P, "2024-05-07"); // Assuming date format is "YYYY-MM-DD"

            // Inserting Row
            db.insert(Util.TABLE_PATH, null, values);
        }

        // Close the database connection
        db.close();
    }
    public List<Vec> getVecByID_LIVREUR(int id_livreur) {
        List<Vec> vectorList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Util.TABLE_VEC + " WHERE " + Util.KEY_ID_LIVREUR + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id_livreur)});

        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                Vec vector = new Vec();
                vector.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_VEC)));
                vector.setColor(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_COLOR)));
                vector.setNom(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_NOM)));
                vector.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_DATE)));
                vector.setIdlivreur(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_LIVREUR)));
                // Add vector to list
                vectorList.add(vector);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return vectorList;
    }

    public void insertTenVec() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for (int i = 0; i < 10; i++) {
            // Generate dummy data for Vec
            String color = "Color " + i;
            String nom = "Nom " + i;
            String date = "Date " + i;
            int idLivreur = 3; // Assuming you have 3 livreurs, assign each Vec randomly to one of them

            // Put dummy data into ContentValues
            values.put(Util.KEY_COLOR, color);
            values.put(Util.KEY_NOM, nom);
            values.put(Util.KEY_DATE, date);
            values.put(Util.KEY_ID_LIVREUR, idLivreur);

            // Insert Vec record into the database
            db.insert(Util.TABLE_VEC, null, values);
        }

        // Close the database connection
        db.close();
    }



}
