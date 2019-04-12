package com.example.wetatch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBProcesses extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cityes.db"; // Название БД
    public static final int DATABASE_VERSION = 2; // Версия базы данных
    static final String TABLE_CITIES = "cities"; // Название таблицы в БД
    // Названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CITY_NAME = "city";
    public static final String COLUMN_NOTE_TEMPERATURE = "temperature";
    public static final String COLUMN_NOTE_PRESSURE = "pressure";
    public static final String COLUMN_NOTE_WIND_SPEED = "wind_speed";

    public DBProcesses(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Вызывается при попытке доступа к базе данных, когда она еще не создана
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CITIES + " (" + COLUMN_ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CITY_NAME + " TEXT,"
                + COLUMN_NOTE_TEMPERATURE + " INTEGER,"  + COLUMN_NOTE_PRESSURE + " INTEGER," + COLUMN_NOTE_WIND_SPEED + " INTEGER);" );
    }

    // Вызывается, когда необходимо обновление базы данных
    //  ??  не понимаю когда и зачем, почему запрос именно такой, если изменить можно что-то другое ??
    //  ??  делается по материалу методички ??
    // Будем считать, что метод onUpgrade() апгрейдит базу, добавляя столбец title
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if ((oldVersion == 1) && (newVersion == 2)) {
            String upgradeQuery = "ALTER TABLE " + TABLE_CITIES + " ADD COLUMN "
                    + COLUMN_NOTE_WIND_SPEED + " TEXT DEFAULT 'title'";
            db.execSQL(upgradeQuery);
        }
    }
}
