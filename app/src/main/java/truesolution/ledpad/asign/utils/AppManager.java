package truesolution.ledpad.asign.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.Stack;

import truesolution.ledpad.asign.MDEBUG;

public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager(){
    }

    public static AppManager getInstance() {
        if(instance == null)
            instance = new AppManager();
        return instance;
    }

    public void addActivity(Activity ac) {
        if(activityStack == null)
            activityStack = new Stack<Activity>();
        activityStack.add(ac);
    }

    public Activity getCurrentActivity(){
        if(activityStack == null)
            return null;
        return activityStack.lastElement();
    }

    public void finishActivity(Activity ac) {
        if(activityStack != null && ac != null){
            activityStack.remove(ac);
            ac.finish();
            ac = null;
        }
    }

    public void finishActivity() {
        if(activityStack != null) {
            Activity ac = activityStack.lastElement();
            finishActivity(ac);
        }
    }

    public void finishActivity(Class<?> cls) {
        if(activityStack == null)
            return;
        for(Activity ac: activityStack){
            if(ac.getClass().equals(cls)){
                finishActivity(ac);
            }
        }
    }

    public void finishAllActivity(){
        if(activityStack == null)
            return;
        Activity ac;
        for(int i = 0, size = activityStack.size(); i < size; i ++) {
            ac = activityStack.get(i);
            if(ac != null) {
                ac.finish();
            }
        }
        activityStack.clear();
    }
    public void finishOverActivity(){
        if(activityStack == null)
            return;
        Activity ac;
        for(int i = activityStack.size(); i > 1; i--) {
            ac = activityStack.pop();
            if(ac != null) {
                ac.finish();
            }
        }
        MDEBUG.debug(" activityStack.size() : " +  activityStack.size());
    }
    public void appExit(Context context) {
        try {
            finishAllActivity();
            System.exit(0);
        } catch (Exception e) {
            Log.e("AppManager", "exit error:" + e.getMessage());
        }
    }
}
