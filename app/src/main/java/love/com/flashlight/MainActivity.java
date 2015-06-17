package love.com.flashlight;

import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;

import cn.waps.AppConnect;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private ToggleButton turnBtn;
    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppConnect.getInstance(this);

        //Ad sendence
        LinearLayout adlayout =(LinearLayout)findViewById(R.id.AdLinearLayout);
        AppConnect.getInstance(this).showBannerAd(this, adlayout);

        mCamera = Camera.open();

        turnBtn = (ToggleButton) this.findViewById(R.id.turn);
        turnBtn.setChecked(true);
        turnBtn.setOnClickListener(this);


        //保持屏幕
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    @Override
    public void onClick(View view) {

        ToggleButton tb = (ToggleButton) view;
        Camera.Parameters mParaeters = mCamera.getParameters();

        if(!tb.isChecked()){
            //preView is importent 如果不添加这一句，直接无法打开 闪光灯！
            mCamera.startPreview();

            mParaeters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            turnBtn.setBackgroundColor(0x30ffffff);
            tb.setBackgroundDrawable(getResources().getDrawable(R.drawable.on));
        }else{
            mParaeters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            turnBtn.setBackgroundColor(0xffffffff);
            tb.setBackgroundDrawable(getResources().getDrawable(R.drawable.off));
        }

        mCamera.setParameters(mParaeters);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            AppConnect.getInstance(this).close();
            System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
