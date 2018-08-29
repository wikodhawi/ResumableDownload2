package resumabledownload2.indocyber.com.resumabledownload2.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import resumabledownload2.indocyber.com.resumabledownload2.DemoApplication;

public class Helper extends SQLiteOpenHelper{
    public Helper(Context context) {
        super(context, DemoApplication.DATABASE_NAME, null, DemoApplication.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
