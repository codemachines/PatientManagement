package data;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import models.Test;

@Database(entities = {Test.class},version = 2,exportSchema = true)
public abstract class TestDatabase extends RoomDatabase {

    public static TestDatabase getInstance(Application application) {
        return null;
    }

    public abstract TestDao testDao();

    private static TestDatabase INSTANCE;

    public static TestDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (TestDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                            context, TestDatabase.class, "TEST DATABASE")
                            .fallbackToDestructiveMigration()
                            .build();

                }

            }

        }

        return INSTANCE;

    }
}
