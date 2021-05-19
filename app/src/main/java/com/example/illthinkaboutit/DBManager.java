package com.example.illthinkaboutit;

import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBManager {
    private static ArrayList<String> strtasks = new ArrayList<>();
    private static ArrayList<String> createdtasks = new ArrayList<>();
    private static FirebaseFirestore firebaseFirestore;
    private FirebaseAnalytics analytics;
    private CollectionReference collectionReference;
    private ArrayList<Item> arrayList = new ArrayList<>();

    static {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public synchronized void setAllTasksData() {
        arrayList.clear();
        Runnable task = new Runnable() {
            @Override
            public void run() {
        firebaseFirestore
                .collection(DBCollections.TASKS.getName())
                .get().addOnCompleteListener(new MyOnComplete());
            }
        };
        StartThread(task);
    }

    //new Date().getTime();
    public void setTaskDataByDate() {
        //todo make new arraylist in method
        arrayList.clear();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                firebaseFirestore.collection("tasks")
                        .orderBy("created_date", Query.Direction.DESCENDING)
                        .get()
                        .addOnCompleteListener(new MyOnComplete());
            }
        };
        StartThread(task);
    }

    //first came starred then sravnvaetsya;//fix:
    public void setMyTasks(RecyclerView view) {
        Runnable task = new Runnable() {
            public void run() {
                ArrayList<Item> item = new ArrayList<>();
                for (String id : createdtasks
                ) {
                    firebaseFirestore.collection("tasks").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot snapshot = task.getResult();
                            item.add(new Item(snapshot.getId()
                                    , snapshot.get(DBFields.TITLE.getName()).toString()
                                    , snapshot.get(DBFields.TASK.getName()).toString()
                                    , strtasks.contains(snapshot.getId())
                                    , Integer.parseInt(String.valueOf(snapshot.get(DBFields.STARS.getName())))
                                    , new Author((HashMap<String, Object>) snapshot.get("author"))));
                            view.setAdapter(new RvAdapter(item.size(), item));

                        }
                    });
                }
            }
        };
        StartThread(task);
    }

    public void setMyStarredTasks(RecyclerView view) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                ArrayList<Item> item = new ArrayList<>();
                for (String s : strtasks) {
                    firebaseFirestore.collection("tasks").document(s).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            try {
                                DocumentSnapshot snapshot = task.getResult();
                                item.add(new Item(snapshot.getId()
                                        , snapshot.get(DBFields.TITLE.getName()).toString()
                                        , snapshot.get(DBFields.TASK.getName()).toString()
                                        , true
                                        , Integer.parseInt(String.valueOf(snapshot.get(DBFields.STARS.getName())))
                                        , new Author((HashMap<String, Object>) snapshot.get("author"))));
                                view.setAdapter(new RvAdapter(item.size(), item));
                            } catch (NullPointerException e) {
                                firebaseFirestore.collection(DBCollections.TASKS.getName()).document(s).delete();
                            } catch (RuntimeExecutionException e) {

                            }
                        }
                    });
                }
            }
        };
        StartThread(task);
    }

    public void setTaskDataByPopularity() {
        arrayList.clear();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                firebaseFirestore.collection("tasks")
                        .orderBy("stars", Query.Direction.DESCENDING)
                        .get()
                        .addOnCompleteListener(new MyOnComplete());
            }
        };
        StartThread(task);
    }

    public synchronized void AccountCheck(String googleId) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                Map<String, Object> map = new HashMap<>();
                map.put("taskid", new ArrayList<String>());
                map.put("createdtaskid", new ArrayList<String>());
                firebaseFirestore.collection("users").document(googleId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot snapshot = task.getResult();
                        if (snapshot.exists()) {
                            strtasks.clear();
                            createdtasks.clear();
                            strtasks.addAll((ArrayList<String>) snapshot.get("taskid"));
                            createdtasks.addAll((ArrayList<String>) snapshot.get("createdtaskid"));
                        } else {
                            firebaseFirestore.collection("users").document(googleId).set(map, SetOptions.merge());
                        }
                    }
                });
            }
        };
        StartThread(task);
    }

    public void addItem(DBItem item) {
        firebaseFirestore.collection(DBCollections.TASKS.getName()).add(item).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                createdtasks.add(task.getResult().getId());
                firebaseFirestore.collection("users")
                        .document(MainActivity.getAccountId())
                        .update("createdtaskid", createdtasks);
            }
        });
    }

    public static void removeItem(String id) {
        firebaseFirestore.collection(DBCollections.TASKS.getName()).document(id).delete();
    }

    public void AddStarredTesk(String TaskId, String GoogleId) {
        Map<String, Object> map = new HashMap<>();
        strtasks.add(TaskId);
        map.put(DBFields.TASK_ID.getName(), strtasks);
        firebaseFirestore.collection(DBCollections.TASKS.getName()).document(TaskId).update(DBFields.STARS.getName(), FieldValue.increment(1));
        firebaseFirestore.collection("users").document(GoogleId).update(map);
    }

    public void RemoveStar(String TaskId, String GoogleId) {
        Map<String, Object> map = new HashMap<>();
        strtasks.remove(TaskId);
        map.put(DBFields.TASK_ID.getName(), strtasks);
        firebaseFirestore.collection(DBCollections.TASKS.getName()).document(TaskId).update(DBFields.STARS.getName(), FieldValue.increment(-1));
        firebaseFirestore.collection(DBCollections.USERS.getName()).document(GoogleId).update(map);
    }

    public void StartThread(Runnable runnable){
        Thread thread = new Thread(runnable);
        thread.start();
    }

    class MyOnComplete implements OnCompleteListener<QuerySnapshot> {

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            for (DocumentSnapshot doc : task.getResult().getDocuments()
            ) {
                try {
                    arrayList.add(new Item(doc.getId(),
                            doc.get(DBFields.TITLE.getName()).toString(),
                            doc.get(DBFields.TASK.getName()).toString(),
                            strtasks.contains(doc.getId()),
                            Integer.parseInt(String.valueOf(doc.get(DBFields.STARS.getName()))),
                            new Author((HashMap<String, Object>) doc.get("author"))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            FragmentFactory.getFactory().setMainFragmentContent(arrayList);
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
    TASKS("tasks"),
    USERS("users");

    String text;

    DBCollections(String tasks) {
        this.text = tasks;
    }

    public String getName() {
        return text;
    }
}
