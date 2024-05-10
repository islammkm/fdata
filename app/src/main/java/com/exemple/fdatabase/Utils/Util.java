package com.exemple.fdatabase.Utils;
public class Util {
    public static final String DATABASE_NAME = "PFE.db";
    public static final int DATABASE_VERSION = 1;
    public static final String ADMIN_PASSWORD = "admin";
    public static final String ADMIN_USER = "admin";

//    public static final String TABLE_NAME = "USERS";
    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_FULLNAME = "fullname";

    //
    public static final String TABLE_USERS = "users";
    public static final String KEY_ID_USER = "id_user";

    public static final String TABLE_CMND = "cmnd";
    public static final String KEY_ID_CMND= "id_cmnd";

    public static final String TABLE_COLIE = "colie";
    public static final String KEY_ID_COLIE = "id_colie";
    public static final String KEY_COLIE_ID = "colie_id";


    public static final String TABLE_LIVREUR = "livreur";
    public static final String KEY_ID_LIVREUR = "id_livreur";
    public static final String KEY_LIVREUR_ID = "livreur_id";

    public static final String TABLE_FOURNISSEUR = "fournisseur";
    public static final String KEY_ID_FOURNISSEUR = "id_fournisseur";
    public static final String KEY_FOURNISSEUR_ID = "fournisseur_id";

    public static final String TABLE_PATH = "path";

    public static final String KEY_ID_PATH = "id_path";
    public static final String KEY_PATH_ID = "path_id";


    // CMND table column names
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_ADRESSE = "adresse";
    public static final String KEY_ETAT = "etat";
    public static final String KEY_COTE_CMND = "cote";
    public static final String KEY_NPHONE = "Nphone";
    // vec
    public static final String TABLE_VEC = "vec";

    // متغيرات الأعمدة لجدول livreur
    public static final String KEY_ID_VEC = "id_vec";
    public static final String KEY_COLOR = "color";
    public static final String KEY_NOM = "nom";
    public static final String KEY_DATE = "date";

    // Colie table column names
    public static final String KEY_CMND_ID = "cmnd_id";
    public static final String KEY_TYPE = "type";
    public static final String KEY_LAST_POSITION = "last_position";
    public static final String KEY_CODE = "code";
    public static final String KEY_COTE_COLIE = "cote";
    public static final String KEY_POIDS = "poids";

    //livreur
    public static final String KEY_RM = "RM";
    public static final String KEY_NM = "NM";

    //Fournisseur
    public static final String KEY_NOM_F = "nom";
    public static final String KEY_ADRESSE_F = "adresse";

    //Path
    public static final String KEY_ADRESS_P = "adress";
    public static final String KEY_DATE_P = "date"; // اسم العمود الجديد



    public static final String CREATE_TABLE_LIVREUR = "CREATE TABLE " + TABLE_LIVREUR + "("
            + KEY_ID_LIVREUR + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USERNAME + " TEXT,"
            + KEY_PASSWORD + " TEXT"
            + ")";
    public static final String CREATE_TABLE_VEC = "CREATE TABLE IF NOT EXISTS " + TABLE_VEC + "("
            + KEY_ID_VEC + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_COLOR + " TEXT,"
            + KEY_NOM + " TEXT,"
            + KEY_DATE + " TEXT,"
            + KEY_ID_LIVREUR + " INTEGER,"
            + "FOREIGN KEY(" + KEY_ID_LIVREUR + ") REFERENCES " + TABLE_LIVREUR + "(" + KEY_ID_LIVREUR + ")"
            + ")";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + KEY_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_FULLNAME + " TEXT,"
            + KEY_USERNAME + " TEXT UNIQUE,"
            + KEY_PASSWORD + " TEXT,"
            + KEY_EMAIL + " TEXT"
            + ")";
    public static final String CREATE_TABLE_CMND = "CREATE TABLE " + TABLE_CMND + "("
            + KEY_ID_CMND + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USER_ID + " INTEGER," // مفتاح خارجي للمستخدم
            + KEY_LIVREUR_ID + " INTEGER," // مفتاح خارجي للسائق
            + KEY_ADRESSE + " TEXT,"
            + KEY_ETAT + " TEXT,"
            + KEY_COTE_CMND + " INTEGER,"
            + KEY_NPHONE + " INTEGER,"
            + "FOREIGN KEY(" + KEY_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + KEY_ID_USER + "),"
            + "FOREIGN KEY(" + KEY_LIVREUR_ID + ") REFERENCES " + TABLE_LIVREUR + "(" + KEY_ID_LIVREUR + ")"
            + ")";
    public static final String CREATE_TABLE_COLIE = "CREATE TABLE " + TABLE_COLIE + "("
            + KEY_ID_COLIE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_CMND_ID + " INTEGER,"
            + KEY_ID_PATH + " INTEGER," // مرجع لجدول path
            + KEY_ID_FOURNISSEUR + " INTEGER," // مرجع لجدول fournisseur
            + KEY_DATE + " TEXT,"
            + KEY_TYPE + " TEXT,"
            + KEY_LAST_POSITION + " TEXT,"
            + KEY_CODE + " TEXT,"
            + KEY_COTE_COLIE + " INTEGER,"
            + KEY_POIDS + " INTEGER,"
            + "FOREIGN KEY(" + KEY_CMND_ID + ") REFERENCES " + TABLE_CMND + "(" + KEY_ID_CMND + "),"
            + "FOREIGN KEY(" + KEY_ID_PATH + ") REFERENCES " + TABLE_PATH + "(" + KEY_ID_PATH + "),"
            + "FOREIGN KEY(" + KEY_ID_FOURNISSEUR + ") REFERENCES " + TABLE_FOURNISSEUR + "(" + KEY_ID_FOURNISSEUR + ")"
            + ")";

    public static final String CREATE_TABLE_FOURNISSEUR = "CREATE TABLE IF NOT EXISTS " + TABLE_FOURNISSEUR + "("
            + KEY_ID_FOURNISSEUR + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NOM_F + " TEXT,"
            + KEY_ADRESSE_F + " TEXT"
            + ")";
    public static final String CREATE_TABLE_PATH = "CREATE TABLE IF NOT EXISTS " + TABLE_PATH + "("
            + KEY_ID_PATH + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ADRESS_P + " TEXT,"
            + KEY_DATE_P + " TEXT" // إضافة العمود الجديد
            + ")";


    public static final  String EMAIL = "pfecolieeee@gmail.com";
    public static final String PASSWORD = "islamislam";
}

