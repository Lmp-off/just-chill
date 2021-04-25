package com.example.illthinkaboutit;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DBManager{

    private static FirebaseFirestore firebaseFirestore;
    private FirebaseAnalytics analytics;
    private CollectionReference collectionReference;
    ArrayList<Item> arrayList = new ArrayList<>();
    static {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }
    public synchronized ArrayList<Item> getAllTasksData(RvFragment fragment){
//Todo:add oncompletelitsener nad make fragment manager
        Task<QuerySnapshot> querySnapshotTask =firebaseFirestore
                .collection(DBCollections.Tasks.getName())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot doc:task.getResult()
                            ) {
                                try {
                                    Log.d("MyFireBaseA", doc.get(DBFields.TITLE.getName()).toString());
                                    arrayList.add(new Item(1,
                                            doc.get(DBFields.TITLE.getName()).toString(),
                                            doc.get(DBFields.TASK.getName()).toString(),
                                            false,
                                            Integer.parseInt(String.valueOf(doc.get(DBFields.STARS.getName()))),
                                            new Author("a", "1")));
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                        }
                        //Todo:remove kostyl'
                        fragment.setAdapter(arrayList);
                    }
                });
        return this.arrayList;
    }
    public static void add(DBItem item){
        firebaseFirestore.collection(DBCollections.Tasks.getName()).add(item);
    }
    public Task<QuerySnapshot> getData(DBCollections collections,DBFields dbFields){
        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection(collections.name()).orderBy(DBFields.STARS.name()).limit(10);
        return query.get();
    }
}
enum DBFields{
    TITLE("title"),
    DESCRIPTION("description"),
    TASK("task"),
    STARS("stars");
    String text;
    DBFields(String text) {
        this.text=text;
    }
    public String getName(){
        return text;
    }
}
enum DBCollections{
    Tasks("tasks");
    String text;
    DBCollections(String tasks) {
        this.text=tasks;
    }

    public String getName(){
        return text;
    }
}
