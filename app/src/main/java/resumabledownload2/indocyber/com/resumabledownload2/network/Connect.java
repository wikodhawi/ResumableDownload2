package resumabledownload2.indocyber.com.resumabledownload2.network;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public Connection connect() {
        // SQLite connection string
        String url = "/data/data/resumabledownload2.indocyber.com.resumabledownload2/databases/resumableDb.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Log.d("connectStatus", "success");
        } catch (SQLException e) {
            Log.d("connectStatus",e.getMessage());
        }
        return conn;
    }
}
