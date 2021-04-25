package com.example.illthinkaboutit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
//todo: save instance if u have't internet connection
public class MainActivity extends AppCompatActivity {
    Button button;
    ViewPager pager;
    static GoogleSignInAccount account;
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
        account = (GoogleSignInAccount) bundle.get("Account");
        gso = (GoogleSignInOptions) bundle.get("GSO");
        client = GoogleSignIn.getClient(getApplicationContext(),gso);

        DBManager manager = new DBManager();

        pager = findViewById(R.id.viewPager);
        button = findViewById(R.id.button5);
        button.setText(account.getDisplayName());
        FragmentCollectionAdapter adapter = new FragmentCollectionAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
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
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //Money.transaction(notify user:false,value$:1000);
                switch (item.getItemId()) {
                    case R.id.logOut:
                        LogOutGoogle();
                        break;


                }
                return false;
            }
        });
        popupMenu.show();
    }
//Todo:Ref(func has more than 1 action)
    private void LogOutGoogle() {
        client.signOut();
        Intent intent = new Intent(this,AccountActivity.class);
        startActivity(intent);
    }
}