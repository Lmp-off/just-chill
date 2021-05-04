package com.example.illthinkaboutit;

import androidx.fragment.app.Fragment;

public class DBInitThread extends Thread{
    RvFragment fragment;

    public DBInitThread(RvFragment fragment) {
        this.fragment=fragment;
    }

    @Override
    public void run() {
        A a = new A(fragment);
        a.start();
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {

            }
        }
        DBManager dbManager = new DBManager();
        dbManager.getAllTasksData();

    }
}
class A extends Thread{
    RvFragment fragment;

    public A(RvFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void run() {
        synchronized (this){
        DBManager dbManager = new DBManager();
        dbManager.AccountCheck(MainActivity.account.getId());
        //TODO make  notify by complete task
            try {
                wait(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify();
        }
    }
}
