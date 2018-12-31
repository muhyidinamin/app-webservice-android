package com.muhyidinamin.upwork;

/**
 * Created by Muhyidin Amin on 5/5/2017.
 */

public class Config {
    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://10.0.2.2/Upwork/Worker/addWorker.php";
    public static final String URL_GET_ALL = "http://10.0.2.2/Upwork/Worker/getAllWorker.php";
    public static final String URL_GET_EMP = "http://10.0.2.2/Upwork/Worker/getWorker.php?username=";
    public static final String URL_UPDATE_EMP = "http://10.0.2.2/Upwork/Worker/updateWorker.php";
    public static final String URL_DELETE_EMP = "http://10.0.2.2/Upwork/Worker/deleteWorker.php?username=";
    public static final String URL_LOGIN = "http://10.0.2.2/Upwork/LoginUser.php";
    public static final String URL_ADD_JOB="http://10.0.2.2/Upwork/Job/addJob.php";
    public static final String URL_GET_ALL_JOB = "http://10.0.2.2/Upwork/Job/getAllJob.php";
    public static final String URL_GET_JOB = "http://10.0.2.2/Upwork/Job/getJob.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_WORKER_FIRST = "depan";
    public static final String KEY_WORKER_LAST = "belakang";
    public static final String KEY_WORKER_EMAIL = "email";
    public static final String KEY_WORKER_USERNAME = "username";
    public static final String KEY_WORKER_PASSWORD = "password";
    public static final String KEY_JOB_JUDUL = "judul";
    public static final String KEY_JOB_BUDGET = "budget";
    public static final String KEY_JOB_DESC = "desc";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_FIRST = "depan";
    public static final String TAG_LAST = "belakang";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_PASSWORD = "password";

    public static final String TAG_ID = "id";
    public static final String TAG_JUDUL = "judul";
    public static final String TAG_BUDGET = "budget";
    public static final String TAG_DESC = "desc";

    //employee id to pass with intent
    public static final String WORKER_USERNAME = "username";
    public static final String JOB_ID = "id";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String USERNAME_SHARED_PREF = "username";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
