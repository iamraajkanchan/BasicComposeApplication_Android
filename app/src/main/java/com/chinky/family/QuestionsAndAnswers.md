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
16. How do you unregister a BroadcastReceiver, and why is it important to do so