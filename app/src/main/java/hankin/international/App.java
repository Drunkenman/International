package hankin.international;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

/**
 *
 * Created by Hankin on 2017/10/16.
 * @email hankin.huan@gmail.com
 */

public class App extends Application {
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("mydebug---", "App onConfigurationChanged");
    }
}
