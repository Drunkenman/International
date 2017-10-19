package hankin.international;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends Activity {

    private ImageView mImg;
    private TextView mStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("mydebug---", "onCreate : "+this.toString());
        mImg = findViewById(R.id.iv_main_img);
        mStr = findViewById(R.id.tv_main_str);
        setInfo();
    }

    private void setInfo(){
        mImg.setImageResource(R.mipmap.online);
        mStr.setText(getString(R.string.app_name));
    }

    private void changeLanguage(String language) {
        //设置应用语言类型
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (language.equals("en")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLocale(Locale.ENGLISH);
            } else {
                config.locale = Locale.ENGLISH;
            }
        } else if(language.equals("zh")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLocale(Locale.TAIWAN);
            } else {
                config.locale = Locale.TAIWAN;
            }
        }
        //只会改变此应用的Configuration，对于系统的getApplicationContext().getResources().getConfiguration().locale不变
        //在应用被强制停止前，应用的Configuration都是更改后的Configuration
        resources.updateConfiguration(config, dm);//api25后推荐使用contenxt.createConfigurationContext(config);

        //更改了应用的Configuration后，不会回调其他函数，需要手动更新UI
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("mydebug---", "MainActivity onNewIntent");
        setInfo();
    }

    public void click(View view){
        switch (view.getId()){
            case R.id.btn_main_en:
                changeLanguage("en");
                break;
            case R.id.btn_main_zh:
                changeLanguage("zh");
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("mydebug---", "MainActivity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("mydebug---", "MainActivity onResume");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("mydebug---", "MainActivity onConfigurationChanged");//会比onRestart先调用
        setInfo();
    }

}
