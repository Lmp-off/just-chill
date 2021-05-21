package com.example.illthinkaboutit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends AppCompatActivity {
    private static ViewPager pager;
    private ImageView imageView;

    private Button buttonF;
    private Button buttonS;
    private Button menuButton;

    private static GoogleSignInAccount account;
    private GoogleSignInOptions gso;
    private GoogleSignInClient client;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBManager manager = new DBManager();

        Intent in = this.getIntent();
        Bundle bundle = in.getExtras();

        account = GoogleSignIn.getLastSignedInAccount(getApplication());
        gso = (GoogleSignInOptions) bundle.get("GSO");
        client = GoogleSignIn.getClient(getApplicationContext(), gso);

    }

    //peremestit'
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();

        pager = findViewById(R.id.viewPager);
        menuButton = findViewById(R.id.button5);
        buttonF = findViewById(R.id.btn_first);
        buttonS = findViewById(R.id.btn_second);

        menuButton.setText(account.getDisplayName());

        MainFragmentCollectionAdapter adapter = new MainFragmentCollectionAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @SuppressLint("ResourceAsColor")
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    buttonS.setBackgroundResource(R.color.aureolin);
                    buttonF.setBackgroundResource(R.color.Dark_aureolin);
                } else {
                    buttonF.setBackgroundResource(R.color.aureolin);
                    buttonS.setBackgroundResource(R.color.Dark_aureolin);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        pager.setCurrentItem(1);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
                Log.i("Loga", "d" + pager.getTransitionName());
            }
        });
        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0);
            }
        });
        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
            }
        });
    }
    public static void showTaskPage(){
        pager.setCurrentItem(1);
    }
    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.optional_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //Money.transaction(notify user:false,value$:1000);
                switch (item.getItemId()) {
                    case R.id.logOut:
                        LogOutGoogle();
                        break;
                    case R.id.support:
                        break;

                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MYTESTPause", "true");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MYTESTResume", "true");
    }

    private void LogOutGoogle() {
        client.signOut();
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }
    public static String getAccountId(){
        return account.getId();
    }
    public static String getDisplayedName(){
        return account.getDisplayName();
    }

}
