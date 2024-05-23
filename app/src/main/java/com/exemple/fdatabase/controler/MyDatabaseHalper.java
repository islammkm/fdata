package com.exemple.fdatabase.controler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.Nullable;

import com.exemple.fdatabase.Utils.Util;
import com.exemple.fdatabase.models.CMNDDetails;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Colie;
import com.exemple.fdatabase.models.Fourniseur;
import com.exemple.fdatabase.models.Livreur;
import com.exemple.fdatabase.models.Path;
import com.exemple.fdatabase.models.Pic;
import com.exemple.fdatabase.models.User;
import com.exemple.fdatabase.models.Vec;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;


public class MyDatabaseHalper extends SQLiteOpenHelper{
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
        db.execSQL(Util.CREATE_TABLE_CMND_DETAILS);
        db.execSQL(Util.CREATE_TABLE_PIC);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_COLIE);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_CMND);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_COLIE);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_LIVREUR);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_PATH);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_VEC);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_CMND_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_FOURNISSEUR);
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_PIC);

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
                colie.setIdPath(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_PATH)));

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
        Cursor cursor = null;
        String selectQuery = "SELECT * FROM " + Util.TABLE_CMND + " WHERE " + Util.KEY_ID_CMND + " = ?";
        cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(cmndId)});
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
        cursor.close();
        return cmnd;
    }
    public Cmd getCmdById(int idCmnd) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + Util.TABLE_CMND + " WHERE " + Util.KEY_ID_CMND + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(idCmnd)});

        if (cursor != null)
            cursor.moveToFirst();

        Cmd cmd = new Cmd();
        cmd.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_CMND)));
        cmd.setUserid(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_USER_ID)));
        cmd.setLivreurId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_LIVREUR_ID)));
        cursor.close();
        db.close();

        return cmd;
    }
    public Livreur getLivreurByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Livreur livreur = null;

        String selectQuery = "SELECT * FROM " + Util.TABLE_LIVREUR + " WHERE " + Util.KEY_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{username});

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
    public Path getPathByAddress2(String address) {
        Path path = null;

        SQLiteDatabase db = getReadableDatabase(); // Open a new connection to the database

        String[] projection = {
                Util.KEY_ID_PATH,
                Util.KEY_ADRESS_P,
                Util.KEY_DATE_P
        };

        String selection = Util.KEY_ADRESS_P + " = ?";
        String[] selectionArgs = { address };

        Cursor cursor = db.query(
                Util.TABLE_PATH,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve data from the cursor and create a Path object
            int pathId = cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_PATH));
            String pathAddress = cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_ADRESS_P));
            String pathDate = cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_DATE_P));

            path = new Path(pathId, pathAddress, pathDate);

            cursor.close();
        }

        db.close(); // Close the database connection

        return path;
    }
    public void updateColie(Colie colie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Extract values from the Colie object
        values.put(Util.KEY_TYPE, colie.getType());
        // Define the where clause
        String whereClause = Util.KEY_ID_COLIE + " = ?";
        String[] whereArgs = {String.valueOf(colie.getId())};

        // Execute the update query
        int rowsAffected = db.update(Util.TABLE_COLIE, values, whereClause, whereArgs);

        // Check if the update was successful
        if (rowsAffected > 0) {
            Log.d("DB_UPDATE", "Colie information updated successfully.");
        } else {
            Log.d("DB_UPDATE", "Failed to update colie information.");
        }

        // Close the database connection
        db.close();
    }

    public Livreur getLivreurById2(int livreurId) {
        Livreur livreur = null;

        SQLiteDatabase db = getReadableDatabase(); // Open a new connection to the database

        String[] projection = {
                Util.KEY_ID_LIVREUR,
                Util.KEY_USERNAME,
                Util.KEY_PASSWORD
        };

        String selection = Util.KEY_ID_LIVREUR + " = ?";
        String[] selectionArgs = { String.valueOf(livreurId) };

        Cursor cursor = db.query(
                Util.TABLE_LIVREUR,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve data from the cursor and create a Livreur object
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_LIVREUR));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_PASSWORD));

            livreur = new Livreur(id, username, password);

            cursor.close();
        }

        db.close(); // Close the database connection

        return livreur;
    }
    public Cmd getCmndById(int cmndId) {
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

    public long insertCMNDDetails(String cityClient, String cityFournisseur, String site, String dateCmnd, String nomCmnd, int cmndId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_CITY_CLIENT, cityClient);
        values.put(Util.KEY_CITY_FOURNISSEUR, cityFournisseur);
        values.put(Util.KEY_SITE, site);
        values.put(Util.KEY_DATE_CMND, dateCmnd);
        values.put(Util.KEY_NOM_CMND, nomCmnd);
        values.put(Util.KEY_CMND_ID, cmndId);
        long id = db.insert(Util.TABLE_CMND_DETAILS, null, values);
        db.close();
        return id;
    }

    public CMNDDetails getCMNDDetailsByCMNDId(int cmndId) {
        SQLiteDatabase db = this.getReadableDatabase();
        CMNDDetails cmndDetails = null;

        String selectQuery = "SELECT * FROM " + Util.TABLE_CMND_DETAILS + " WHERE " + Util.KEY_CMND_ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(cmndId)});

        if (cursor != null && cursor.moveToFirst()) {
            cmndDetails = new CMNDDetails();
            cmndDetails.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID)));
            cmndDetails.setCityc(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_CITY_CLIENT)));
            cmndDetails.setCityf(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_CITY_FOURNISSEUR)));
            cmndDetails.setSite(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_SITE)));
            cmndDetails.setDatecmnd(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_DATE_CMND)));
            cmndDetails.setNomcmnd(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_NOM_CMND)));
            cmndDetails.setCmndid(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_CMND_ID)));
        }

        if (cursor != null) {
            cursor.close();
        }
        if(cmndDetails != null){return cmndDetails;}else {return null;}
    }
    public long insertPic(String name, byte[] imageBytes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, name);
        values.put(Util.KEY_IMAGE, imageBytes);
        long ins = db.insertOrThrow(Util.TABLE_PIC, null, values);
        // Inserting Row
        db.close(); // Closing database connection
        return ins;
    }
    public Bitmap getImageByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies the columns from the table we care about
        String[] projection = {Util.KEY_IMAGE};

        // Filter results WHERE "name" = name
        String selection = Util.KEY_NAME + " = ?";
        String[] selectionArgs = {name};

        // Query the database
        Cursor cursor = db.query(
                Util.TABLE_PIC,             // The table to query
                projection,            // The array of columns to return (pass null to get all)
                selection,             // The columns for the WHERE clause
                selectionArgs,         // The values for the WHERE clause
                null,                  // don't group the rows
                null,                  // don't filter by row groups
                null                   // don't sort order
        );

        Bitmap imageBitmap = null;

        if (cursor != null && cursor.moveToFirst()) {
            byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(Util.KEY_IMAGE));
            cursor.close();
            if (imageBytes != null) {
                imageBitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(imageBytes));
            }
        }
        db.close();
        return imageBitmap;
    }
//    public List<User> getAllUsers() {
//        List<User> userList = new ArrayList<>();
//
//        // Select All Query
//        String selectQuery = "SELECT * FROM " + Util.TABLE_USERS;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // Loop through all rows and add to list
//        if (cursor.moveToFirst()) {
//            do {
//                User user = new User();
//                user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID)));
//                user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_USERNAME)));
//                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_EMAIL)));
//                user.setFullName(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_FULLNAME)));
//                // Add user to list
//                userList.add(user);
//            } while (cursor.moveToNext());
//        }
//
//        // Close cursor and database connection
//        cursor.close();
//        db.close();
//
//        // Return user list
//        return userList;
//    }
    public List<User> getAllUsers2() {
        List<User> users = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Util.TABLE_USERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_USER)));
                user.setFullName(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_FULLNAME)));
                user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_PASSWORD)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_EMAIL)));
                users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return users;
    }
    public List<Cmd> getAllCommands() {
        List<Cmd> commands = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Util.TABLE_CMND;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to the list
        if (cursor.moveToFirst()) {
            do {
                Cmd command = new Cmd();
                command.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_CMND)));
                command.setUserid(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_USER_ID)));
                command.setLivreurId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_LIVREUR_ID)));
                command.setAdresse(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_ADRESSE)));
                command.setEtat(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_ETAT)));
                command.setCote(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_COTE_CMND)));
                command.setNphone(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_NPHONE)));
                commands.add(command);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return commands;
    }
    public void updateCmdEtat(int cmdId, String newEtat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_ETAT, newEtat);

        // Updating row
        int rowsAffected = db.update(Util.TABLE_CMND, values, Util.KEY_ID_CMND + " = ?", new String[]{String.valueOf(cmdId)});
        if (rowsAffected > 0) {
            Log.d("MyDatabaseHalper", "CMD etat updated successfully");
        } else {
            Log.d("MyDatabaseHalper", "CMD etat update failed");
        }

        db.close(); // Closing database connection
    }
    public Bitmap getPicById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Bitmap bitmap = null;

        Cursor cursor = db.query(Util.TABLE_PIC, new String[]{Util.KEY_IMAGE},
                "id_pic=?", new String[]{String.valueOf(id)},
                null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            byte[] imageByteArray = cursor.getBlob(cursor.getColumnIndexOrThrow(Util.KEY_IMAGE));
            bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            cursor.close();
        }
        db.close();
        return bitmap;
    }
    public String getPicnameById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String name = null;

        Cursor cursor = db.query(Util.TABLE_PIC, new String[]{Util.KEY_NAME},
                "id_pic=?", new String[]{String.valueOf(id)},
                null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_NAME));
        }
        db.close();
        return name;
    }

    public void updateColieEtat(int colieid,String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_DATE, date);

        // Updating row
        int rowsAffected = db.update(Util.TABLE_COLIE, values, Util.KEY_ID_COLIE + " = ?", new String[]{String.valueOf(colieid)});
        if (rowsAffected > 0) {
            Log.d("MyDatabaseHalper", "CMD etat updated successfully");
        } else {
            Log.d("MyDatabaseHalper", "CMD etat update failed");
        }

        db.close(); // Closing database connection
    }

    public void updateColieType(int colieId, String newType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_TYPE, newType);

        // Updating row
        db.update(Util.TABLE_COLIE, values, Util.KEY_ID_COLIE + " = ?",
                new String[]{String.valueOf(colieId)});
        db.close();
    }
    public Colie getColieById8(int id_colie) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_COLIE,
                new String[]{Util.KEY_ID_COLIE, Util.KEY_CMND_ID, Util.KEY_ID_PATH, Util.KEY_ID_FOURNISSEUR, Util.KEY_DATE, Util.KEY_TYPE, Util.KEY_LAST_POSITION, Util.KEY_CODE, Util.KEY_COTE_COLIE, Util.KEY_POIDS},
                Util.KEY_ID_COLIE + "=?",
                new String[]{String.valueOf(id_colie)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Colie colie = new Colie(
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
        return colie;
    }

    public List<Colie> getAllColiesWithType(String type) {
        List<Colie> coliesList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + Util.TABLE_COLIE + " WHERE " + Util.KEY_TYPE + " = '" + type + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Colie colie = new Colie();
                colie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_COLIE)));
                colie.setCmndId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_CMND_ID)));
                colie.setIdPath(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_PATH)));
                colie.setFournisseurId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_FOURNISSEUR)));
                colie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_DATE)));
                colie.setType(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_TYPE)));
                colie.setLastPosition(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_LAST_POSITION)));
                colie.setCode(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_CODE)));
                colie.setCote(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_COTE_COLIE)));
                colie.setPoids(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_POIDS)));

                // Adding colie to list
                coliesList.add(colie);
            } while (cursor.moveToNext());
        }

        // close cursor and database
        cursor.close();
        db.close();

        // return colies list
        return coliesList;
    }
    public List<Colie> getAllColiesWithTypeAndCmndId(String type, int cmndId) {
        List<Colie> coliesList = new ArrayList<>();

        // Select Query
        String selectQuery = "SELECT * FROM " + Util.TABLE_COLIE + " WHERE " + Util.KEY_TYPE + " = '" + type + "' AND " + Util.KEY_CMND_ID + " = " + cmndId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Colie colie = new Colie();
                colie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_COLIE)));
                colie.setCmndId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_CMND_ID)));
                colie.setIdPath(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_PATH)));
                colie.setFournisseurId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_FOURNISSEUR)));
                colie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_DATE)));
                colie.setType(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_TYPE)));
                colie.setLastPosition(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_LAST_POSITION)));
                colie.setCode(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_CODE)));
                colie.setCote(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_COTE_COLIE)));
                colie.setPoids(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_POIDS)));

                // Adding colie to list
                coliesList.add(colie);
            } while (cursor.moveToNext());
        }

        // close cursor and database
        cursor.close();
        db.close();

        // return colies list
        return coliesList;
    }

    public void deletePath(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_PATH, Util.KEY_ID_PATH + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }
    public Fourniseur getSupplierByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {Util.KEY_ID_FOURNISSEUR, Util.KEY_NOM_F, Util.KEY_ADRESSE_F};
        String selection = Util.KEY_NOM_F + " = ?";
        String[] selectionArgs = {name};
        Cursor cursor = db.query(Util.TABLE_FOURNISSEUR, columns, selection, selectionArgs, null, null, null);
        Fourniseur fournisseur = null;
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_FOURNISSEUR));
            String nom = cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_NOM_F));
            String adresse = cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_ADRESSE_F));
            fournisseur = new Fourniseur( nom, adresse,id);
            cursor.close();
        }
        db.close();
        return fournisseur;
    }

    public ArrayList<Fourniseur> getAllFournisseurs() {
        ArrayList<Fourniseur> fournisseursList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Util.TABLE_FOURNISSEUR;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Fourniseur fournisseur = new Fourniseur();
                fournisseur.setId(cursor.getInt(cursor.getColumnIndexOrThrow( Util.KEY_ID_FOURNISSEUR)));
                fournisseur.setNom(cursor.getString(cursor.getColumnIndexOrThrow( Util.KEY_NOM_F)));
                fournisseur.setAdresse(cursor.getString(cursor.getColumnIndexOrThrow( Util.KEY_ADRESSE_F)));

                // Adding fournisseur to list
                fournisseursList.add(fournisseur);
            } while (cursor.moveToNext());
        }

        // close the cursor and database
        cursor.close();
        db.close();

        // return list of fournisseurs
        return fournisseursList;
    }

    public ArrayList<CMNDDetails> getAllCommandDetails() {
        ArrayList<CMNDDetails> commandDetailsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Util.TABLE_CMND_DETAILS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CMNDDetails commandDetails = new CMNDDetails();
                commandDetails.setId(cursor.getInt(cursor.getColumnIndexOrThrow( Util.KEY_ID)));
                commandDetails.setCityc(cursor.getString(cursor.getColumnIndexOrThrow( Util.KEY_CITY_CLIENT)));
                commandDetails.setCityf(cursor.getString(cursor.getColumnIndexOrThrow( Util.KEY_CITY_FOURNISSEUR)));
                commandDetails.setSite(cursor.getString(cursor.getColumnIndexOrThrow( Util.KEY_SITE)));
                commandDetails.setDatecmnd(cursor.getString(cursor.getColumnIndexOrThrow( Util.KEY_DATE_CMND)));
                commandDetails.setNomcmnd(cursor.getString(cursor.getColumnIndexOrThrow( Util.KEY_NOM_CMND)));
                commandDetails.setCmndid(cursor.getInt(cursor.getColumnIndexOrThrow( Util.KEY_CMND_ID)));

                // Adding commandDetails to list
                commandDetailsList.add(commandDetails);
            } while (cursor.moveToNext());
        }

        // close the cursor and database
        cursor.close();
        db.close();

        // return list of commandDetails
        return commandDetailsList;
    }

    public ArrayList<Livreur> getAllLivreurs() {
        ArrayList<Livreur> livreursList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Util.TABLE_LIVREUR;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Livreur livreur = new Livreur();
                livreur.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_LIVREUR)));
                livreur.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_USERNAME)));
                livreur.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_PASSWORD)));

                // Adding livreur to list
                livreursList.add(livreur);
            } while (cursor.moveToNext());
        }

        // close the cursor and database
        cursor.close();
        db.close();

        // return list of livreurs
        return livreursList;
    }

    public ArrayList<Vec> getAllVECs() {
        ArrayList<Vec> vecList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Util.TABLE_VEC;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Vec vec = new Vec();
                vec.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_VEC)));
                vec.setColor(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_COLOR)));
                vec.setNom(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_NOM)));
                vec.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_DATE)));
                vec.setIdlivreur(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_LIVREUR)));

                // Adding vec to list
                vecList.add(vec);
            } while (cursor.moveToNext());
        }

        // close the cursor and database
        cursor.close();
        db.close();

        // return list of vecs
        return vecList;
    }
    public ArrayList<Pic> getAllPics() {
        ArrayList<Pic> picList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Util.TABLE_PIC;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pic pic = new Pic();
                pic.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_PIC)));
                pic.setName(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_NAME)));
                pic.setImage(cursor.getBlob(cursor.getColumnIndexOrThrow(Util.KEY_IMAGE)));

                // Adding pic to list
                picList.add(pic);
            } while (cursor.moveToNext());
        }

        // close the cursor and database
        cursor.close();
        db.close();

        // return list of pics
        return picList;
    }


    public ArrayList<Colie> getAllColies() {
        ArrayList<Colie> colieList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Util.TABLE_COLIE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Colie colie = new Colie();
                colie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_COLIE)));
                colie.setCmndId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_CMND_ID)));
                colie.setIdPath(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_PATH)));
                colie.setFournisseurId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_FOURNISSEUR)));
                colie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_DATE)));
                colie.setType(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_TYPE)));
                colie.setLastPosition(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_LAST_POSITION)));
                colie.setCode(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_CODE)));
                colie.setCote(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_COTE_COLIE)));
                colie.setPoids(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_POIDS)));

                // Adding colie to list
                colieList.add(colie);
            } while (cursor.moveToNext());
        }

        // close the cursor and database
        cursor.close();
        db.close();

        // return list of colies
        return colieList;
    }

    public ArrayList<Cmd> getAllCMNDs() {
        ArrayList<Cmd> cmndList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Util.TABLE_CMND;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Cmd cmnd = new Cmd();
                cmnd.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_CMND)));
                cmnd.setUserid(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_USER_ID)));
                cmnd.setLivreurId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_LIVREUR_ID)));
                cmnd.setAdresse(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_ADRESSE)));
                cmnd.setEtat(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_ETAT)));
                cmnd.setCote(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_COTE_CMND)));
                cmnd.setNphone(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_NPHONE)));

                // Adding cmnd to list
                cmndList.add(cmnd);
            } while (cursor.moveToNext());
        }

        // close the cursor and database
        cursor.close();
        db.close();

        // return list of cmnds
        return cmndList;
    }
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Util.TABLE_USERS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.KEY_ID_USER)));
                user.setFullName(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_FULLNAME)));
                user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_PASSWORD)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(Util.KEY_EMAIL)));

                // Adding user to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // close the cursor and database
        cursor.close();
        db.close();

        // return list of users
        return userList;
    }


    public void saveCMNDDetails(List<CMNDDetails> cmndDetailsList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (CMNDDetails cmndDetails : cmndDetailsList) {
                ContentValues values = new ContentValues();
                values.put(Util.KEY_CITY_CLIENT, cmndDetails.getCityc());
                values.put(Util.KEY_CITY_FOURNISSEUR, cmndDetails.getCityf());
                values.put(Util.KEY_SITE, cmndDetails.getSite());
                values.put(Util.KEY_DATE_CMND, cmndDetails.getDatecmnd());
                values.put(Util.KEY_NOM_CMND, cmndDetails.getNomcmnd());
                values.put(Util.KEY_CMND_ID, cmndDetails.getCmndid());
                db.insert(Util.TABLE_CMND_DETAILS, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // Handle the exception
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public void saveLivreur(List<Livreur> livreurList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (Livreur livreur : livreurList) {
                ContentValues values = new ContentValues();
                values.put(Util.KEY_USERNAME, livreur.getUsername());
                values.put(Util.KEY_PASSWORD, livreur.getPassword());
                db.insert(Util.TABLE_LIVREUR, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // Handle the exception
        } finally {
            db.endTransaction();
            db.close();
        }
    }
    public void saveVec(List<Vec> vecList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (Vec vec : vecList) {
                ContentValues values = new ContentValues();
                values.put(Util.KEY_COLOR, vec.getColor());
                values.put(Util.KEY_NOM, vec.getNom());
                values.put(Util.KEY_DATE, vec.getDate());
                values.put(Util.KEY_ID_LIVREUR, vec.getIdlivreur()); // Assuming Livreur ID is stored in Vec object
                db.insert(Util.TABLE_VEC, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // Handle the exception
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public void saveUser(List<User> userList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (User user : userList) {
                ContentValues values = new ContentValues();
                values.put(Util.KEY_FULLNAME, user.getFullName());
                values.put(Util.KEY_USERNAME, user.getUsername());
                values.put(Util.KEY_PASSWORD, user.getPassword());
                values.put(Util.KEY_EMAIL, user.getEmail());
                db.insert(Util.TABLE_USERS, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // Handle the exception
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public void saveCmd(List<Cmd> cmdList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (Cmd cmd : cmdList) {
                ContentValues values = new ContentValues();
                values.put(Util.KEY_USER_ID, cmd.getUserid());
                values.put(Util.KEY_LIVREUR_ID, cmd.getLivreurId());
                values.put(Util.KEY_ADRESSE, cmd.getAdresse());
                values.put(Util.KEY_ETAT, cmd.getEtat());
                values.put(Util.KEY_COTE_CMND, cmd.getCote());
                values.put(Util.KEY_NPHONE, cmd.getNphone());
                db.insert(Util.TABLE_CMND, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // Handle the exception
        } finally {
            db.endTransaction();
            db.close();
        }
    }
    public void saveColie(List<Colie> colieList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (Colie colie : colieList) {
                ContentValues values = new ContentValues();
                values.put(Util.KEY_CMND_ID, colie.getCmndId());
                values.put(Util.KEY_ID_PATH, colie.getIdPath());
                values.put(Util.KEY_ID_FOURNISSEUR, colie.getFournisseurId());
                values.put(Util.KEY_DATE, colie.getDate());
                values.put(Util.KEY_TYPE, colie.getType());
                values.put(Util.KEY_LAST_POSITION, colie.getLastPosition());
                values.put(Util.KEY_CODE, colie.getCode());
                values.put(Util.KEY_COTE_COLIE, colie.getCote());
                values.put(Util.KEY_POIDS, colie.getPoids());
                db.insert(Util.TABLE_COLIE, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // Handle the exception
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public void saveFournisseur(List<Fourniseur> fournisseurList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (Fourniseur fournisseur : fournisseurList) {
                ContentValues values = new ContentValues();
                values.put(Util.KEY_NOM_F, fournisseur.getNom());
                values.put(Util.KEY_ADRESSE_F, fournisseur.getAdresse());
                db.insert(Util.TABLE_FOURNISSEUR, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // Handle the exception
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    public void savePath(List<Path> pathList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (Path path : pathList) {
                ContentValues values = new ContentValues();
                values.put(Util.KEY_ADRESS_P, path.getAddress());
                values.put(Util.KEY_DATE_P, path.getDate());
                db.insert(Util.TABLE_PATH, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // Handle the exception
        } finally {
            db.endTransaction();
            db.close();
        }
    }
    private DatabaseListener listener;

    public void setDatabaseListener(DatabaseListener listener) {
        this.listener = listener;
    }

    // Other methods in your database helper class...

    // Method to detect changes in the local database and notify the listener
    private void detectChanges() {
        // Check for changes in the database
        if (listener != null) {
            listener.onDatabaseChanged();
        }
    }

    interface DatabaseListener {
        void onDatabaseChanged();
    }
}
