package com.example.illthinkaboutit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

//todo: save instance if u have't internet connection
public class MainActivity extends AppCompatActivity {
    Button button;
    ViewPager pager;
    Button buttonF;
    Button buttonS;
    static GoogleSignInAccount account;
    ImageView imageView;
    static ArrayList<String> userData = new ArrayList<>();
    GoogleSignInOptions gso;

    GoogleSignInClient client;
    FirebaseFirestore firebaseFirestore;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent in = this.getIntent();
        Bundle bundle = in.getExtras();
        account = GoogleSignIn.getLastSignedInAccount(getApplication());
        gso = (GoogleSignInOptions) bundle.get("GSO");
        client = GoogleSignIn.getClient(getApplicationContext(), gso);
        DBManager manager = new DBManager();
        manager.AccountCheck(account.getId());
    }

    //peremestit'
    @Override
    protected void onStart() {
        super.onStart();

        imageView = findViewById(R.id.imageView);
        pager = findViewById(R.id.viewPager);
        button = findViewById(R.id.button5);
        buttonF = findViewById(R.id.btn_first);
        buttonS = findViewById(R.id.btn_second);

        button.setText(account.getDisplayName());

        FragmentCollectionAdapter adapter = new FragmentCollectionAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    buttonS.setBackgroundColor(Color.WHITE);
                    buttonF.setBackgroundColor(Color.RED);
                } else {
                    buttonF.setBackgroundColor(Color.WHITE);
                    buttonS.setBackgroundColor(Color.RED);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
               // buttonF.setBackgroundColor(Color.GREEN);
            }
        });
        pager.setCurrentItem(1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
                Log.i("Loga", "d" + pager.getTransitionName());
            }
        });
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
                        DBManager dbManager = new DBManager();
                        dbManager.test();
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

    //Todo:Ref(func has more than 1 action)
    private void LogOutGoogle() {
        client.signOut();
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }
}
