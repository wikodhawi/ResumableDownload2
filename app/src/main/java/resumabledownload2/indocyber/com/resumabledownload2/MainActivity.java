package resumabledownload2.indocyber.com.resumabledownload2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadMonitor;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import resumabledownload2.indocyber.com.resumabledownload2.model.Identity.Identity;
import resumabledownload2.indocyber.com.resumabledownload2.model.Identity.Identity_Table;
import resumabledownload2.indocyber.com.resumabledownload2.network.Connect;
import resumabledownload2.indocyber.com.resumabledownload2.network.Constant;
import resumabledownload2.indocyber.com.resumabledownload2.network.GlobalMonitor;
import resumabledownload2.indocyber.com.resumabledownload2.util.Helper;
import resumabledownload2.indocyber.com.resumabledownload2.util.ZipExtractor;

import static com.liulishuo.filedownloader.database.SqliteDatabaseImpl.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private int downloadId1;

    private String llsApkFilePath;
    private String llsApkDir;
    private String normalTaskFilePath;
    private String chunkedFilePath;
    protected static String fileLocation="";
    private String targetFileExtract;

    private Button startBtn1;
    private Button pauseBtn1;
    private Button deleteBtn1;
    private TextView filenameTv1;
    private TextView speedTv1;
    private ProgressBar progressBar1;
    private Button extractBtn1;
    private View parentView;
    private Button deleteDbBtn1;

    private ProgressDialog progressDialog;

    private EditText txtId;
    private Button btnSearch;

    private SQLiteDatabase db;
    SQLiteOpenHelper sqLiteOpenHelper;

    private void assignViews() {
        parentView=(View)findViewById(android.R.id.content);
        startBtn1 = (Button) findViewById(R.id.start_btn_1);
        pauseBtn1 = (Button) findViewById(R.id.pause_btn_1);
        deleteBtn1 = (Button) findViewById(R.id.delete_btn_1);
        filenameTv1 = (TextView) findViewById(R.id.filename_tv_1);
        speedTv1 = (TextView) findViewById(R.id.speed_tv_1);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar_1);
        extractBtn1 = (Button)findViewById(R.id.extract_btn_1);
        txtId=(EditText)findViewById(R.id.txtId);
        btnSearch=(Button)findViewById(R.id.btnSearch);
        deleteDbBtn1=(Button)findViewById(R.id.deleteDb_btn_1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileDownloadMonitor.setGlobalMonitor(GlobalMonitor.getImpl());

        String debug= FileDownloadUtils.getDefaultSaveRootPath();

        llsApkFilePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "tmpdir1" + File.separator +
                Constant.LIULISHUO_CONTENT_DISPOSITION_FILENAME;
        llsApkDir = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "tmpdir1";
        normalTaskFilePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "tmp2";
        chunkedFilePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "chunked_data_tmp1";

        assignViews();

        initFilePathEqualDirAndFileName();

    }

    @SuppressLint("WrongConstant")
    private void initFilePathEqualDirAndFileName() {
        db=openOrCreateDatabase("/data/data/resumabledownload2.indocyber.com.resumabledownload2/databases/resumableDb.db",
                SQLiteDatabase.CREATE_IF_NECESSARY,null);
        // task 1
        startBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadId1 = createDownloadTask(1).start();
            }
        });

        pauseBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileDownloader.getImpl().pause(downloadId1);
            }
        });

        deleteBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new File(llsApkFilePath).delete();
                new File(FileDownloadUtils.getTempPath(llsApkFilePath)).delete();
            }
        });
        extractBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                extractFile();
                new storeDb().execute( );
/*
                Identity identity=new Identity();
                identity.setId("1");
                identity.setPekerjaan("asdf");
                identity.setCitacita2kerjaan("aasdf");
                identity.setCitacita3("asdf");
                identity.setKolomsebelas("asdf");
                identity.setKolomtwelve("asdf");
                identity.setNama3("asdf");
                identity.setPekerjaan4kolom("asdf");
                identity.setCitacita4kolom("adsf");
                identity.setPekerjaan3("asdf");
                identity.setNama4kolom("asdf");
                identity.setNama("aasdf");
                insertData(identity);
*/
//                insertData();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchById();
            }
        });

        deleteDbBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(deleteTable())
//                    Snackbar.make(parentView, "success delete table", Snackbar.LENGTH_LONG).show();
//                else
//                    Snackbar.make(parentView, "failed delete table", Snackbar.LENGTH_LONG).show();
                deleteTable();
            }
        });
    }

    public void insertData(Identity identity)
    {
        String sql = "INSERT or REPLACE INTO Identity(" +
                "nama4kolom, " +
                "kolomtwelve, " +
                "id, " +
                "nama3, " +
                "citacita4kolom, " +
                "nama, " +
                "pekerjaan4kolom, " +
                "kolomsebelas," +
                "pekerjaan3, " +
                "pekerjaan, " +
                "citacita2kerjaan, " +
                "citacita3) VALUES('" +
                identity.getNama4kolom() +"','"+
                identity.getKolomtwelve()+"','"+
                identity.getId()+"','"+
                identity.getNama3()+"','"+
                identity.getCitacita4kolom()+"','"+
                identity.getNama()+"','"+
                identity.getPekerjaan4kolom()+"','"+
                identity.getKolomsebelas()+"','"+
                identity.getPekerjaan3()+"','"+
                identity.getPekerjaan()+"','"+
                identity.getCitacita2kerjaan()+"','"+
                identity.getCitacita3()+
                "')";
        try{

//            db.beginTransaction();
            db.execSQL(sql);
//            db.setTransactionSuccessful();
//            db.endTransaction();

        }catch (android.database.SQLException e)
        {
            Log.d("resultGotError", e.getMessage()+" at "+identity.getId());
//            db.endTransaction();
//            Snackbar.make(parentView, e.getMessage()+" at "+identity.getId(), Snackbar.LENGTH_LONG).show();
        }

//        Connect connect=new Connect();
//        try {
//            Connection connection=connect.connect();
//        } catch (Exception e) {
//
//        }
    }

    public void searchById()
    {
        try{
            Identity identity= SQLite.select().from(Identity.class).where(Identity_Table.id.eq(txtId.getText().toString())).querySingle();
            Snackbar.make(parentView, identity.getPekerjaan4kolom(), Snackbar.LENGTH_LONG).show();
        } catch (Exception e)
        {
            Snackbar.make(parentView, "Not found", Snackbar.LENGTH_LONG).show();
        }


    }

    public void deleteTable()
    {
        Helper helper = new Helper(MainActivity.this);
        try{
//            db = helper.getWritableDatabase();
//            db.execSQL("DELETE FROM Identity" );
//            db.close();
            SQLite.delete().from(Identity.class).execute();
            Snackbar.make(parentView, "success delete data", Snackbar.LENGTH_LONG).show();
        }catch ( Exception e)
        {
            Snackbar.make(parentView, "failed delete data", Snackbar.LENGTH_LONG).show();
        }


    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public static class ViewHolder extends MainActivity{
        private ProgressBar pb;
        private TextView detailTv;
        private TextView speedTv;
        private int position;
        private TextView filenameTv;
        public String pathLocation;

        private WeakReference<MainActivity> weakReferenceContext;

        public ViewHolder(){}

        public ViewHolder(WeakReference<MainActivity> weakReferenceContext,
                          final ProgressBar pb, final TextView detailTv, final TextView speedTv,
                          final int position) {
            this.weakReferenceContext = weakReferenceContext;
            this.pb = pb;
            this.detailTv = detailTv;
            this.position = position;
            this.speedTv = speedTv;
        }

        public void setFilenameTv(TextView filenameTv) {
            this.filenameTv = filenameTv;
        }

        private void updateSpeed(int speed) {
            speedTv.setText(String.format("%dKB/s", speed));
        }

        public void updateProgress(final int sofar, final int total, final int speed) {
            if (total == -1) {
                // chunked transfer encoding data
                pb.setIndeterminate(true);
            } else {
                pb.setMax(total);
                pb.setProgress(sofar);
            }

            updateSpeed(speed);

            if (detailTv != null) {
                detailTv.setText(String.format("sofar: %d total: %d", sofar, total));
            }
        }

        public void updatePending(BaseDownloadTask task) {
            if (filenameTv != null) {
                filenameTv.setText(task.getFilename());
            }
        }

        public void updatePaused(final int speed) {
            toast(String.format("paused %d", position));
            updateSpeed(speed);
            pb.setIndeterminate(false);
        }

        public void updateConnected(String etag, String filename) {
            if (filenameTv != null) {
                filenameTv.setText(filename);
            }
        }

        public void updateWarn() {
            toast(String.format("warn %d", position));
            pb.setIndeterminate(false);
        }

        public void updateError(final Throwable ex, final int speed) {
            toast(String.format("error %d %s", position, ex));
            updateSpeed(speed);
            pb.setIndeterminate(false);
            ex.printStackTrace();
        }

        public void updateCompleted(final BaseDownloadTask task) {

//            pathLocation = task.getPath();

            toast(String.format("completed %d %s", position, task.getTargetFilePath()));

            if (detailTv != null) {
                detailTv.setText(String.format("sofar: %d total: %d",
                        task.getSmallFileSoFarBytes(), task.getSmallFileTotalBytes()));
            }
//            try{
//                String target=task.getPath();
//                new MainActivity().unzip(new File(task.getTargetFilePath()), new File(target));
//            } catch (Exception e) {
//
//            }

            fileLocation=task.getTargetFilePath();

            new MainActivity().setFileLocation(task.getTargetFilePath());

            updateSpeed(task.getSpeed());
            pb.setIndeterminate(false);
            pb.setMax(task.getSmallFileTotalBytes());
            pb.setProgress(task.getSmallFileSoFarBytes());
        }

        private void toast(final String msg) {
            if (this.weakReferenceContext != null && this.weakReferenceContext.get() != null) {
                Snackbar.make(weakReferenceContext.get().startBtn1, msg, Snackbar.LENGTH_LONG).show();
            }
        }

    }

    private BaseDownloadTask createDownloadTask(final int position) {
        final ViewHolder tag;
        final String url;
        boolean isDir = false;
        String path;

        switch (position) {
            case 1:
                url = Constant.LIULISHUO_APK_URL;
                tag = new ViewHolder(new WeakReference<>(this), progressBar1, null, speedTv1, 1);
                path = llsApkFilePath;
                tag.setFilenameTv(filenameTv1);
                break;
//            case 2:
//                url = Constant.LIULISHUO_APK_URL;
//                tag = new ViewHolder(new WeakReference<>(this), progressBar2, null, speedTv2, 2);
//                path = llsApkDir;
//                isDir = true;
//                tag.setFilenameTv(filenameTv2);
//                break;
//            case 3:
//                url = Constant.BIG_FILE_URLS[2];
//                tag = new ViewHolder(new WeakReference<>(this), progressBar3, null, speedTv3, 3);
//                path = normalTaskFilePath;
//                break;
            default:
                url = Constant.CHUNKED_TRANSFER_ENCODING_DATA_URLS[0];
//                tag = new ViewHolder(new WeakReference<>(this), progressBar4, detailTv4, speedTv4, 4);
                tag = new ViewHolder(new WeakReference<>(this), progressBar1, null, speedTv1, 1);
                path = chunkedFilePath;
                break;

        }

        return FileDownloader.getImpl().create(url)
                .setPath(path, isDir)
                .setCallbackProgressTimes(300)
                .setMinIntervalUpdateSpeed(400)
                .setTag(tag)
                .setListener(new FileDownloadSampleListener() {

                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.pending(task, soFarBytes, totalBytes);
                        ((ViewHolder) task.getTag()).updatePending(task);
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.progress(task, soFarBytes, totalBytes);
                        ((ViewHolder) task.getTag()).updateProgress(soFarBytes, totalBytes,
                                task.getSpeed());
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        super.error(task, e);
                        ((ViewHolder) task.getTag()).updateError(e, task.getSpeed());
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                        super.connected(task, etag, isContinue, soFarBytes, totalBytes);
                        ((ViewHolder) task.getTag()).updateConnected(etag, task.getFilename());
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.paused(task, soFarBytes, totalBytes);
                        ((ViewHolder) task.getTag()).updatePaused(task.getSpeed());
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        super.completed(task);
                        ((ViewHolder) task.getTag()).updateCompleted(task);
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        super.warn(task);
                        ((ViewHolder) task.getTag()).updateWarn();
                    }
                });
    }

    private class storeDb extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Synchronizing");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });

            extractFile();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }
    }

    private class insertDb extends  AsyncTask<List<Identity>, Integer, String>
    {
        /*
        @Override
        protected Identity doInBackground(Identity... identities) {
            return identities[0];
        }

        @Override
        protected void onPostExecute(Identity identities) {

            super.onPostExecute(identities);
            String result="";
            String sql = "INSERT INTO Identity(" +
                    "nama4kolom, " +
                    "kolomtwelve, " +
                    "id, " +
                    "nama3, " +
                    "citacita4kolom, " +
                    "nama, " +
                    "pekerjaan4kolom, " +
                    "kolomsebelas," +
                    "pekerjaan3, " +
                    "pekerjaan, " +
                    "citacita2kerjaan, " +
                    "citacita3) VALUES('" +
                    identities.getNama4kolom() +"','"+
                    identities.getKolomtwelve()+"','"+
                    identities.getId()+"','"+
                    identities.getNama3()+"','"+
                    identities.getCitacita4kolom()+"','"+
                    identities.getNama()+"','"+
                    identities.getPekerjaan4kolom()+"','"+
                    identities.getKolomsebelas()+"','"+
                    identities.getPekerjaan3()+"','"+
                    identities.getPekerjaan()+"','"+
                    identities.getCitacita2kerjaan()+"','"+
                    identities.getCitacita3()+
                    "')";
            try{

                db.beginTransaction();
                db.execSQL(sql);
                db.setTransactionSuccessful();
                db.endTransaction();
                result="success";

            }catch (android.database.SQLException e)
            {
                Log.d("resultGotError", e.getMessage()+" at "+identities.getId());
                result=e.getMessage();
                db.endTransaction();
//            Snackbar.make(parentView, e.getMessage()+" at "+identity.getId(), Snackbar.LENGTH_LONG).show();
            }

            Log.d("resultInsert", result);
        }
        */

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(List<Identity>... identities) {
            String result="";
            for(Identity identity: identities[0])
            {
                String sql = "INSERT INTO Identity(" +
                        "nama4kolom, " +
                        "kolomtwelve, " +
                        "id, " +
                        "nama3, " +
                        "citacita4kolom, " +
                        "nama, " +
                        "pekerjaan4kolom, " +
                        "kolomsebelas," +
                        "pekerjaan3, " +
                        "pekerjaan, " +
                        "citacita2kerjaan, " +
                        "citacita3) VALUES('" +
                        identity.getNama4kolom() +"','"+
                        identity.getKolomtwelve()+"','"+
                        identity.getId()+"','"+
                        identity.getNama3()+"','"+
                        identity.getCitacita4kolom()+"','"+
                        identity.getNama()+"','"+
                        identity.getPekerjaan4kolom()+"','"+
                        identity.getKolomsebelas()+"','"+
                        identity.getPekerjaan3()+"','"+
                        identity.getPekerjaan()+"','"+
                        identity.getCitacita2kerjaan()+"','"+
                        identity.getCitacita3()+
                        "')";
                try{

                    db.beginTransaction();
                    db.execSQL(sql);
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    result="success "+identity.getId();

                }catch (android.database.SQLException e)
                {
                    Log.d("resultGotError", e.getMessage()+" at "+identity.getId());
                    result=e.getMessage()+" at "+identity.getId();
                    db.endTransaction();
//            Snackbar.make(parentView, e.getMessage()+" at "+identity.getId(), Snackbar.LENGTH_LONG).show();
                }
            }

//            String sql = "INSERT INTO Identity(" +
//                    "nama4kolom, " +
//                    "kolomtwelve, " +
//                    "id, " +
//                    "nama3, " +
//                    "citacita4kolom, " +
//                    "nama, " +
//                    "pekerjaan4kolom, " +
//                    "kolomsebelas," +
//                    "pekerjaan3, " +
//                    "pekerjaan, " +
//                    "citacita2kerjaan, " +
//                    "citacita3) VALUES('" +
//                    identities[0].getNama4kolom() +"','"+
//                    identities[0].getKolomtwelve()+"','"+
//                    identities[0].getId()+"','"+
//                    identities[0].getNama3()+"','"+
//                    identities[0].getCitacita4kolom()+"','"+
//                    identities[0].getNama()+"','"+
//                    identities[0].getPekerjaan4kolom()+"','"+
//                    identities[0].getKolomsebelas()+"','"+
//                    identities[0].getPekerjaan3()+"','"+
//                    identities[0].getPekerjaan()+"','"+
//                    identities[0].getCitacita2kerjaan()+"','"+
//                    identities[0].getCitacita3()+
//                    "')";
//            try{
//
//                db.beginTransaction();
//                db.execSQL(sql);
//                db.setTransactionSuccessful();
//                db.endTransaction();
//                result="success "+identities[0].getId();
//
//            }catch (android.database.SQLException e)
//            {
//                Log.d("resultGotError", e.getMessage()+" at "+identities[0].getId());
//                result=e.getMessage()+" at "+identities[0].getId();
//                db.endTransaction();
////            Snackbar.make(parentView, e.getMessage()+" at "+identity.getId(), Snackbar.LENGTH_LONG).show();
//            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("isSuccess",s);

        }

    }

    public void extractFile()
    {
//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setTitle("Loading Model");
//        progressDialog.show();

        String target=new File(fileLocation).getParent();
        targetFileExtract=target+"/data.json";

        String message="";
        try
        {
            new ZipExtractor().unzip(new File(fileLocation), new File(target));
            message="success extracted at "+ target;
        } catch (Exception e)
        {
            message=" failed to extract";
        }

//        JSONParser parser = new JSONParser();

//        try{
//            JSONArray a = (JSONArray) parser.parse(new FileReader(targetFileExtract));
//            message="sucdess parsing";
//
//
//        }
//        catch (Exception e)
//        {
//            message="failed to parsing";
//        }

//        Gson gson = new Gson();
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new FileReader(targetFileExtract));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Type type = new TypeToken<List<Identity>>(){}.getType();
//        List<Identity> models = gson.fromJson(br, type);



//        progressDialog.dismiss();

        InputStream fileInputStream=null;
        try{
            fileInputStream = new FileInputStream(targetFileExtract);
        }catch (Exception e)
        {

        }

        readStream(fileInputStream);

//        String msg=MainActivity.ViewHolder().
        Snackbar.make(parentView, message, Snackbar.LENGTH_LONG).show();
    }

    public void readStream(InputStream in) {
        db.beginTransaction();
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            Gson gson = new GsonBuilder().create();

            // Read file in stream mode
            reader.beginArray();
            int counter=1;
            List<Identity> identities=new ArrayList<>();

            while (reader.hasNext()) {
                // Read data into object model
                Identity person = gson.fromJson(reader, Identity.class);
//                Log.d("result"+counter+"", person+"");
                if (person.getId() != null ) {
//                    System.out.println("Stream mode: " + person);
                    Log.d("result"+counter+"", person.getId()+"");
//                    identities.add(person);
                }
                counter++;



                insertData(person);



//                new insertDb().execute(person);
//                if(counter%50000==0)
//                {
//                    new insertDb().execute(identities);
//                    try
//                    {
//                        TimeUnit.SECONDS.sleep((long)10);
//
//                    } catch (Exception e)
//                    {
//
//                    }
//                    identities.clear();
//
//                }

//                break;
            }
            reader.close();
            db.setTransactionSuccessful();
        } catch (UnsupportedEncodingException ex) {

        } catch (IOException ex) {

        }

        db.endTransaction();
    }

    private void saveDB(Identity items){
//        FlowManager.getDatabase(DemoApplication.class).
//                beginTransactionAsync(new ProcessModelTransaction.Builder<>(
//                        new ProcessModelTransaction.ProcessModel<Identity>() {
//                            @Override
//                            public void processModel(Identity grocery, DatabaseWrapper wrapper) {
//                                grocery.save();
//                            }
//                        }
//                ).addAll(items).build()).error(new Transaction.Error() {
//            @Override
//            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
//                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG);
//            }
//        }).success(new Transaction.Success() {
//            @Override
//            public void onSuccess(@NonNull Transaction transaction) {
//                Toast.makeText(getApplicationContext(), "Data berhasil di simpan di sqlLite", Toast.LENGTH_LONG);
//            }
//        }).build().execute();


    }
}
