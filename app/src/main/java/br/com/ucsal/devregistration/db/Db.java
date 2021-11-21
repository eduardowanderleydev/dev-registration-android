package br.com.ucsal.devregistration.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.ucsal.devregistration.dao.ResumeDAO;
import br.com.ucsal.devregistration.domain.Address;
import br.com.ucsal.devregistration.domain.Resume;

@Database(entities = {Address.class, Resume.class}, exportSchema = false, version = 1)
public abstract class Db extends RoomDatabase {

    private static final String DB_NAME = "DEV_REGISTRATION_DB";
    private static Db instance;

    public static synchronized Db getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context, Db.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract ResumeDAO ResumeDAO();
}
