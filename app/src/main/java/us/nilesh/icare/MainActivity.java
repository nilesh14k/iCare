package us.nilesh.icare;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home);
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onHomePressed();
//            }
//        });
        /**
         * above code use it for exiting the app
         **/

        final FloatingActionButton fab_home = findViewById(R.id.floatingActionButtonHome);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS,
                        Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET},
                PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onBackPressed() {
    }

    //    FragmentManager fragmentManager = getSupportFragmentManager();
//    MFragment mf = new MFragment();
//    fragmentManager.findFragmentById(R.id.content_frame) == mf
//
//    Fragment f = fragmentManager.findFragmentById(R.id.content_frame);
//    if(f instanceof MainFragment)
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK) {
//            Toast.makeText(MainActivity.this,"Click Home button to go on Home Page",Toast.LENGTH_LONG).show();
//            Log.i("Back Button","Clicked");
//            return false;
//        }else {
//            onExit();
//        }
//        return false;
//    }
//
//    public void onExit(){
//        AlertDialog alertox = new AlertDialog.Builder(this)
//                .setMessage("Do you want to exit application?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        finish();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                    }
//                })
//                .show();
//    }
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
        if (id == R.id.action_test) {
            onClickTest();
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClickTest(){
        Intent intentTest = new Intent(MainActivity.this, MapsFragment.class);
        startActivity(intentTest);
    }
}