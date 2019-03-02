package ***.***.geomap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import ***.***.geomap.model_view.MapDemoActivity;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "GEO_MAP >>> ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate()");

        EditText latitude = findViewById(R.id.editText_latitude);
        EditText longitude = findViewById(R.id.editText_longitude);
        Button show_map = findViewById(R.id.btn_show_map);
        WebView web_view = findViewById(R.id.web_view_map);

        MapDemoActivity mapDemoActivity = new MapDemoActivity(this,
                latitude, longitude, show_map, web_view);

        mapDemoActivity.callMap();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume()");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart()");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart()");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }
}
