package com.example.illthinkaboutit;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBManager {
    static ArrayList<String> strtasks = new ArrayList<>();
    private static FirebaseFirestore firebaseFirestore;
    private FirebaseAnalytics analytics;
    private CollectionReference collectionReference;
    ArrayList<Item> arrayList = new ArrayList<>();

    static {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    //todo:load another data
    //todo:@Ocerride OnComplete move to diff class
    public synchronized void getAllTasksData() {
        arrayList.clear();
        firebaseFirestore
                .collection(DBCollections.Tasks.getName())
                .get().addOnCompleteListener(new MyOnComplete());
    }

    //new Date().getTime();
    public void setTaskDataByDate() {
        arrayList.clear();
        firebaseFirestore.collection("tasks")
                .orderBy("created_date", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new MyOnComplete());
    }

    public void setTaskDataByPopularity() {
        arrayList.clear();
        firebaseFirestore.collection("tasks")
                .orderBy("stars", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new MyOnComplete());
    }
    public synchronized void AccountCheck(String googleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("taskid", new ArrayList<String>());

        firebaseFirestore.collection("users").document(googleId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot snapshot = task.getResult();
                if (snapshot.exists()) {
                    strtasks.clear();
                    Log.d("MYTESTACCsa", String.valueOf(task.isComplete()));
                    Log.d("MYTESTACCs", String.valueOf(strtasks.size()));
                    strtasks.addAll((ArrayList<String>) snapshot.get("taskid"));
                } else {
                    firebaseFirestore.collection("users").document(googleId).set(map, SetOptions.merge());
                    Log.d("MYTESTACC", "snap is null");
                }
            }
        });
    }
    public static void add(DBItem item) {
        firebaseFirestore.collection(DBCollections.Tasks.getName()).add(item);
    }

    public void AddStarredTesk(String TaskId, String GoogleId) {
        Map<String, Object> map = new HashMap<>();
        strtasks.add(TaskId);
        map.put("taskid", strtasks);
        Log.d("MYTESTACC", String.valueOf(map.size()));
        firebaseFirestore.collection("tasks").document(TaskId).update("stars", FieldValue.increment(1));
        firebaseFirestore.collection("users").document(GoogleId).update(map);
    }

    public void RemoveStar(String TaskId, String GoogleId) {
        Map<String, Object> map = new HashMap<>();
        strtasks.remove(TaskId);
        map.put("taskid", strtasks);
        Log.d("MYTESTACC", String.valueOf(map.size()));
        firebaseFirestore.collection("tasks").document(TaskId).update("stars", FieldValue.increment(-1));
        firebaseFirestore.collection("users").document(GoogleId).update(map);
    }

    //oncomplete task.getresoult.contains;


    public void test() {

    }

    public Task<QuerySnapshot> getData(DBCollections collections, DBFields dbFields) {
        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection(collections.name()).orderBy(DBFields.STARS.name()).limit(10);
        return query.get();
    }

    class MyOnComplete implements OnCompleteListener<QuerySnapshot>{

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            for (DocumentSnapshot doc : task.getResult().getDocuments()
            ) {
                Log.d("MYTESTTEST", "1");
                arrayList.add(new Item(doc.getId(),
                        doc.get(DBFields.TITLE.getName()).toString(),
                        doc.get(DBFields.TASK.getName()).toString(),
                        strtasks.contains(doc.getId()),
                        Integer.parseInt(String.valueOf(doc.get(DBFields.STARS.getName()))),
                        new Author("a", "1")));
            }
            FragmentFactory.getFactory().setContent(arrayList);
        }
    }
}

enum DBFields {
    TITLE("title"),
    TASK_ID("taskid"),
    USER_ID("userid"),
    DESCRIPTION("description"),
    TASK("task"),
    STARS("stars");
    String text;

    DBFields(String text) {
        this.text = text;
    }

    public String getName() {
        return text;
    }
}

enum DBCollections {
    Tasks("tasks");

    String text;

    DBCollections(String tasks) {
        this.text = tasks;
    }

    public String getName() {
        return text;
    }
}
