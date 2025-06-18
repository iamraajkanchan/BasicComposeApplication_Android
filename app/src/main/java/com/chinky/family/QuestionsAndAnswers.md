# Compilation of Android Interview Questions.


# Activity
1. What is IntentService and how it is different from Foreground and Background Service?
    IntentService is a subclass of Service that handles asynchronous task in the background.
    IntentService processes each intent sequentially and stops itself once all the tasks are completed.
    IntentService is useful for short-lived background operations
    IntentService runs on a separate worker thread.
    IntentService stops automatically after completing tasks.
    IntentService is ideal for one time background tasks.
    IntentService have only one method that needs to overridden when extended i.e. onHandleIntent(intent: Intent?)
    IntentService doesn't need a context, so to use a context inside an IntentService you can use getApplicationContext() method
2. What is the livecycle of IntentService
    onCreate()
    onStartCommand()
    onStart()
    onHandleIntent()
    onDestroy()
3. What is the difference between Transient and Non-transient data in Android?
    Transient data: This data is temporary which by the help of onSaveInstanceState() and onRestoreState() overriding methods can 
    be saved and reused when the activity is recreated.
    Non Transient data: This data sticks around even the application is closed or restarted. It can be saved and reused with the help
    of SharedPreferences, Databases and Files feature. 
4. What is the difference between Serialized and Parcelized data in Android?
    Serializable:
    - It is a built-in Java interface
    - Slower: Because it uses reflection and creates more temporary objects, which can affect the application performance.
    - Easy to implement
    - More flexible: Supports class hierarchies and is easier to maintain.
   Parcelable:
    - It is Android specific, designed for high performance inter-process-communication
    - Faster: It is more efficient than Serializable because it avoids reflection and minimizes object creation.
    - Manual Implementation: You need to override writeToParcel() method and use a CREATOR object.
    - Less flexible: Doesn't support full class hierarchies as easily.
5. Can you explain the Android Activity LifeCycle and the significance of each lifeCycle method in detail?
    - Activity Lifecycle are the states of an Activity. There are seven lifecycle methods
        1. onCreate() - 
        2. onStart() - 
        3. onResume()
        4. onPause()
        5. onStop()
        6. onDestroy()
        7. onRestart()
6. What is the difference between a Service and an Activity? When would you choose to use a Service instead of an activity?
7. Describe the distance between onSaveInstanceState() and onRestoreInstanceState() methods in Android activities. When are these methods called?
8. In what scenarios would you use the launch modes: "standard", "singleTop", "singleTask" and "singleInstance"? Explain their significance and 
9. How would like to handle screen orientation changes in an Android activity, and why it is so important to manage such changes?
10. Explain the difference between startActivityForResult() and startActivity() overridden methods. When would you use one over the other?
11. Describe how would you use fragments in your Android Activity to optimize a multi-pane layout for different screen sizes or device orientations.
12. Explain the role of Intent objects in communication between Android components, especially between Activities and provide examples of different Intent types.
13. What is the purpose of persisting an Activity state and how do you use the SharedPreferences class to store data across configuration changes?
14. How do you ensure that a sensitive or resource-intensive operation is not performed while your activity is in the background or is partially obscured?
15. What is the significance of the onActivityResult() method and when it is called?
16. Can you explain how deep linking works in Android Activities, and how you would implement it in your application?
17. What are the best practices for managing application memory and battery usage when working with Activities and their lifecycle?
18. Explain the concept of task affinity and its impact on the behaviour of Android Activities.
19. Describe how you would implement a hierarchical navigation structure between activities using Intents and the Up button.
20. How do you handle runtime permissions in Android Activities and why is this an important consideration for modern Android applications.

# Broadcast Receivers
1. What is an Android Broadcast Receivers, and what are its primary use cases.
2. Explain how the BroadcastReceiver class fits within the Android application components heirarchy.
3. Can you implement BroadcastReceiver in a separate process? If so, what are the advantages and disadvantages of doing this?
4. Describe the different ways to register a BroadcastReceiver in an Android application, and the prons and cons of each approach.
5. How can an application use BroadcastReceiver to respond to system events, such as changes in network connectivity, battery level, or screen orientation?
6. What is the role of intent filters in BroadcastReceiver? Explain the importance of action, category and data elements in an IntentFilter.
7. Describe the difference between LocalBroadcastManagers and BroadcastReceivers. When should one be used over the other?
8. Explain the difference between an ordered broadcast and a normal broadcast in terms of processing and prioritization.
9. How do you ensure that a Broadcast receiver has the appropriate permissions to perform specific tasks?
10. What are some common use cases of Exported Receivers, and how do they increase the security of broadcast messages?
11. Explain the impact of a long running operation inside a BroadcastReceiver on the performance of the application.
12. Describe the role of setResultCode() and getResultCode() methods in the BroadcastReceiver process.
13. Explain the role of Context and Intent in the onReceive() method of BroadcastReceiver.
14. What are some best practices to follow while implementing Android BroadcastReceivers for optimal performance and reliability?
15. How can you handle situations where multiple BroadcastReceivers are registered for the same broadcast intent? What is the purpose of priorities in Intent Filters?
16. How do you unregister a BroadcastReceiver, and why is it important to do so?
17. Describe the difference between using PendingIntent for starting an activity, a service or a BroadcastReceiver.
18. Explain how to use BroadcastReceiver to handle app notifications across different Android Versions.
19. Describe how to use a BroadcastReceiver to interact with other application components using implicit intents.
20. How can you debug or test a BroadcastReceiver in an Android application? What tools or techniques can be used?
21. What is the use of WakefulBroadcastReceiver in ensuring the execution of a long-running background task after receiving a broadcast?
22. Can a BroadcastReceiver be used to communicate between applications? If so, explain the process and any security considerations.
23. What is the impact of system wide broadcasts on battery life, and how can it be minimized using efficient BroadcastReceiver implementations?
24. How do Android.Manifest elements like action and category affect BroadcastReceiver functionality, and when should a custom manifest element be used?
25. Explain how would you ensure backward compatibility in responding to broadcasts across different Android SDK versions using BroadcastReceiver?

# SQLite
1. What is SQLite, and how does it differ from other databases?
2. What are the key features of SQLite?
3. How does SQLite hand transactions?
4. What are the advantages and limitations of using SQLite in Android?
5. What are SQLite storage classes?
6. How do you create and manage a SQLite database in an Android application?
7. What is the role of SQLiteOpenHelper in Android?
8. How do you perform CRUD (Create-Read-Update-Delete) operations in SQLite?
9. How do you handle database version upgrades in SQLite?
10. What is the difference between SQL and SQLite?
11. How do you optimize SQLite queries for better performance?
12. What are SQLite indexes and when should they be used?
13. How do you implement database encryption in SQLite?
14. How do you handle concurrency in SQLite?
15. What are the best practices for managing large datasets in SQLite?

# Permissions
1. How does Android handle permissions before and after API level 23 (Android 6.0)?
    Before API level 23 (Android 6.0), permissions were granted at install time. After API level 23 (Android 6.0) dangerous permissions require runtime approval.
2. What is the role of the AndroidManifest.xml file in declaring permissions? 
    AndroidManifest.xml file defines required permission using the required-permission tag.
3. How can an application check if a permission has already been granted?
    Use the following method to check if a permission is already granted.
   `ContextCompat.checkSelfPermission(Context, Permission)`
4. What are runtime permissions, and why where they introduced in Android 6.0?
    Runtime permissions are used to grant access to permissions dynamically, which improves security and user control.
5. How do you request runtime permissions in an Android app?
    Use the following method to request runtime permissions
    `ActivityCompat.requestPermission(Context, ArrayOf(permission), requestCode)`
6. What is the shouldShowRequestPermissionRationale() method, and when should it be used?
    This method is used to check if an application should explain if a permission is needed before requesting that permission.
7. How do you handle the result of a permission request in an Android app?
8. What happens if a user denies a permission request multiple times?
9. How can an app guide users to enable permissions manually from settings?
10. What are the best practices for requesting runtime permissions to improve user experience?
11. How do you handle permissions in background service?
12. What are special permissions like SYSTEM_ALERT_WINDOW and WRITE_SETTINGS, and how are they requested?
13. How does Android handle permissions for accessing external storage?
14. What are foreground service permissions, and how do they differ from normal permissions?
15. How do permission work in Android 11 and later, especially regarding scoped storage?
16. How can an application request multiple permissions at once?
17. What are permission groups and how do they affect permission requests?
18. How do you handle permission revocation when an application is updated or reinstalled?
19. What are permissions in Android, and why are they needed?
    Permissions allow an application to access restricted system features and user data. Permissions provide security by preventing unauthorized access.
20. Explain the difference between normal and dangerous permissions
    - Normal Permissions: The permissions which are automatically granted. e.g. INTERNET
    - Dangerous Permissions: The permissions which require user approval. e.g. READ_CONTACTS

#