package ***.***.geomap.model_view;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geomap.MainActivity;
import  com.example.geomap.exceptions.GeoRangeException;

public class MapDemoActivity {

    private final int LATITUDE_MAX_ABS_VALUE = 90;
    private final int LONGITUDE_MAX_ABS_VALUE = 180;
    private final String BASE_PATH = "https://www.google.com/maps/@";
    private MainActivity mainActivity;
    private TextView latitude;
    private TextView longitude;
    private Button show_map;
    private WebView map;

    /**
     * Constructor
     * @param mainActivity MainActivity
     * @param latitude  TextView
     * @param longitude TextView
     * @param show_map  Button
     * @param map   WebView
     */
    public MapDemoActivity(MainActivity mainActivity, TextView latitude, TextView longitude,
                           Button show_map, WebView map) {
        this.mainActivity = mainActivity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.show_map = show_map;
        this.map = map;
    }

    /**
     * Sets a click listener button event
     */
    public void callMap() {
        show_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLocation();
            }
        });
    }

    /**
     * checks latitude and longitude input values
     * @return boolean
     */
    private boolean isGeo() {
        return checkValues(latitude, "latitude", LATITUDE_MAX_ABS_VALUE) &&
               checkValues(longitude, "longitude", LONGITUDE_MAX_ABS_VALUE);
    }

    /**
     * Verifies that input values can be parsed to Double
     * and checks that the same input values are in between
     * given min and max values (min == -max)
     * @param txt TextView
     * @param name  String
     * @param absVal int
     * @return  boolean
     */
    private boolean checkValues(TextView txt, String name, int absVal) {
        try {
            double d = Double.parseDouble(txt.getText().toString());
            checkGeoRange(d, absVal);
        } catch (NumberFormatException e) {
            errorMessage(e.getMessage());
            return false;
        } catch (GeoRangeException e) {
            errorMessage(String.format("%1$s must be -%2$d <= %1$s <= %2$d", name, absVal));
            return false;
        }
        return true;
    }

    /**
     * Sets Log and Toast to rise in a catch block
     * @param msg String
     */
    private void errorMessage(String msg) {
        Log.e(mainActivity.TAG, msg);
        Toast.makeText(mainActivity, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Checks for max and min input values
     * @param d double
     * @param absVal int
     * @throws GeoRangeException
     */
    private void checkGeoRange(double d, int absVal) throws GeoRangeException {
        if (Math.abs(d) > absVal) throw new GeoRangeException();
    }

    /**
     * REQUIRES: nothing
     * MODIFIES: WebView
     * EFFECT: shows the loaded url in a WebView
     */
    private void showLocation() {
        if (isGeo()) {
            try {
                String s = BASE_PATH + latitude.getText().toString() +
                        "," + longitude.getText().toString() + ",6z";
                map.setWebViewClient(new WebViewClient());
                map.getSettings().setJavaScriptEnabled(true);
                map.loadUrl(s);
            } catch (Exception e) {
                Log.e(mainActivity.TAG, e.getMessage());
                Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
