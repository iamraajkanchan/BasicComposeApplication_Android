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
    - SQLite is a lightweight, self-contained database engine which stores data in a single file. Unless MySQL or PostgreSQL, it doesn't require a separate server to run. 
2. What are the key features of SQLite?
   - Serverless Architecture
   - Zero Configuration
   - Cross-platform compatibility
   - ACID (Atomicity, Consistency, Isolation, Durability) transactions
     - A stands for Atomicity: Ensures that a transaction is treated as a single unit - either all operations succeed or none do.
     - C stands for Consistency: Guarantees that a transaction brings the database from one valid state to another, maintaining integrity.
     - I stands for Isolation: Ensures that current transactions do not interfere with each other which prevents data corruption.
     - D stands for Durability: Ensures that once a transaction is committed, it remains in the database even in case of a system failure. 
   - Uses SQL Syntax
3. How does SQLite hand transactions?
   - SQLite follows the ACID (Atomicity, Consistency, Isolation, Durability) properties.
4. What are the advantages and limitations of using SQLite in Android?
    - Advantages: Light weight, easy to use and built in Android
    - Limitations: This database is not ideal for high-concurrency scenarios. It lacks advanced features like stored procedures.  
5. What are SQLite storage classes?
   - Null: Value is null
   - INTEGER: Numeric Value
   - REAL: Floating point number
   - TEXT: String
   - BLOB: Binary Data
6. How do you create and manage a SQLite database in an Android application?
   - Create a class and extend SQLiteOpenHelper which helps in creating and managing the database. It also helps in managing database version.
7. What is the role of SQLiteOpenHelper in Android?
   - SQLiteOpenHelper helps in creating and managing the database. It also helps in upgrading and downgrading database version.
8. How do you perform CRUD (Create-Read-Update-Delete) operations in SQLite?
   - Use the following methods to perform CRUD operations.
     - Create: db.insert()
     - Read: db.query()
     - Update: db.update()
     - Delete: db.delete()
9. How do you handle database version upgrades in SQLite?
   - Override the onUpgrade() method in the class which you are extending SQLiteOpenHelper to modify schema changes. 
   `
     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     Log.d("ProductDBHelper", "onUpgrade called from version " + oldVersion + " to " + newVersion);

     if (oldVersion < 2) {
     // Upgrade from version 1 to 2: Add 'price' column to 'products' table
     Log.d("ProductDBHelper", "Upgrading from v1 to v2: Adding 'price' column");
     db.execSQL("ALTER TABLE " + TABLE_PRODUCTS_V1 + " ADD COLUMN " + COLUMN_PRODUCT_PRICE_V2 + " REAL DEFAULT 0.0;");
     }
     // If you have further upgrades, you'd add more if-conditions:
     // if (oldVersion < 3) {
     //     // Migration logic from version 2 to 3
     //     db.execSQL("ALTER TABLE ...");
     // }
     // if (oldVersion < 4) {
     //     // Migration logic from version 3 to 4
     //     // (e.g., creating a new table, moving data)
     // }
     }
    `
10. What is the difference between SQL and SQLite?
    - SQL is a language whereas SQLite is an embedded database implementing SQL.
11. How do you optimize SQLite queries for better performance?
    - With the help of indexing, query caching and efficient Where clauses, we can optimize SQLite queries.
12. What are SQLite indexes and when should they be used?
    - Indexes are speed up queries on a large datasets. Indexes may slow down write operations.
13. How do you implement database encryption in SQLite?
    - Use SQLCipher or custom encryption techniques for secure storage.
14. How do you handle concurrency in SQLite?
    - Use transactions, locking mechanisms, and manage multi-threaded access carefully.
15. What are the best practices for managing large datasets in SQLite?
    - Normalize database design
    - Optimize queries.
    - Use pagination for fetching data efficiently.

# Room Database
1. What are the main components of Android Room, and how do they interact with each other?
2. Can you explain the differences between Room, SQLite and Realm, and when you would use each for Android app development?
3. How can you handle version updates and schema migrations in Android Room?
4. What is the role of DAO (Data Access Object) in Android Room, and how do you create a DA0?
5. How do you complex relationships between entities, such as many-to-many relationships in Android Room?
6. Can you explain the LiveData integration in Android Room and its benefits in your application?
7. How do you ensure thread safety when using Android Room in a multi threaded environment?
8. What is a TypeConverter in Room and how do you implement custom TypeConverter?
9. Can you explain the difference between @Query, @Insert, @Update and @Delete annotation in DAO?
10. How can you optimize performance in Android Room using performance in Android Room using query optimization techniques?
11. What are the different types of conflict resolution strategies available in Android Room, and when would you use them?
12. How do you handle on-demand or on-change database backups and restore scenarios with Android Room?
13. How can you test your Room database in a Unit test or an Instrumented test?
14. Can you explain how Paging Library works with Room?
15. How do you implement encryption in Android Room?
16. How do you perform database transactions with Android Room, and how does it handle rollback scenarios?
17. How do you execute a raw SQL query with Room and what would be the advantages and challenges of doing so?
18. What is the FTS (Full-Text Search) support in Android Room, and how can you implement it?
19. How can you handle large datasets and improve query performance in Android Room?
20. What would be the best strategy to create a synchronized offline - first app using Android Room with a Firebase Realtime Database?
21. How can you use Dependency Injection frameworks like Dagger 2 or Hilt with Android Room?
22. Can you explain the concept of Multi-Module architecture and how Android Room fits into modularization?
    - Multi Module architecture is a design pattern that divides an application into multiple, smaller modules to improve scalability, maintainability and reusability. Each module focuses on a specific functionality or feature, allowing for independent development and testing.
    - Android Room fits into modularization by providing a robust database layer within a module
23. How do you cache data with Android Room and what kind of caching strategies can you implement?
    - To cache data with Android Room, create a local database using Room persistence library. Define entities representing tables, Data Accessing Objects (DAOs) for queries, and a RoomDatabase instance to access the database. Below are the Caching Strategies
      - Offline-First: Prioritize cached data. Fetch from network only if data is unavailable or outdated.
      - Online-First: Fetch data from network first, use cache as fallback.
      - Cache-then-refresh: Display cached data while fetching updated data from network.
      - Two-layer cache: Combine in-memory cache with Room for faster access and persistence.
      - Time-based expiration: Invalidate cache after a specific duration
      - Version-based invalidation: Invalidate cache when app version changes or server - side schema updates.
      - Smart-Sync: Sync cached data with remote source based on user actions or connectivity status.
      - An embedded entity in Room allows for flattening of object hierarchy by including fields from another class directly into the main table, while a relationship represents associations between tables using foreign keys. Embedded entities are useful when there's a one-to-one mapping an no need to query related data separately. Relationships, on the other hand, cater to one-to-many and many-to-many mappings and enable efficient querying of related data.
        - For example, consider an Address class with fields street, city and zipCode. Using @Embedded, these fields can be included in a User table without creating a separate Address table.
            @Entity
            data class User(@PrimaryKey val id: Int, val name: String, @Embedded val address: Address)
        - In contrast, relationships require defining separate tables and established connections via foreign keys. For instance, if a user has multiple addresses, we'd create a separate Address table and use @Relation annotation to establish a connection.
            @Entity
            data class User(@PrimaryKey val id: Int, val name: String)
            @Entity(foreignKeys = [ForeignKey (entity = User::class, parentColumns = ["id"], childColumns = ["userId"]))))
            data class Address(@PrimaryKey val id: Int, val userId: Int, val street: String, val city: String, val zipCode: String)
24. How would you perform concurrency control with Android Room to avoid conflicts between read and write operations?
    - To perform concurrency control with Android Room, use transactions and LiveData. Transactions ensure ACID (Atomicity, Consistency, Isolation, Durability) properties while LiveData provides real-time updates.
      - Use @Transaction annotation for complex read/write operations to guarantee ACID properties.
      - Utilize coroutines or RxJava for asynchronous execution of database operations.
      - Implement conflict resolution strategies using @Insert(onConflict = OnConflictStrategy.REPLACE) or similar annotations.
      - Employ LiveData or Flow to observe changes in the data and automatically update UI components.
      - For multi-threading, use Executions or Dispatchers to manage background threads.
      - Optimize queries by indexing columns and limiting fetched data with pagination techniques.

# Permissions
1. How does Android handle permissions before and after API level 23 (Android 6.0)?
   - Before API level 23 (Android 6.0), permissions were granted at install time. After API level 23 (Android 6.0) dangerous permissions require runtime approval.
2. What is the role of the AndroidManifest.xml file in declaring permissions? 
    - AndroidManifest.xml file defines required permission using the required-permission tag.
3. How can an application check if a permission has already been granted?
    - Use the following method to check if a permission is already granted.
   `ContextCompat.checkSelfPermission(Context, Permission)`
4. What are runtime permissions, and why where they introduced in Android 6.0?
    - Runtime permissions are used to grant access to permissions dynamically, which improves security and user control.
5. How do you request runtime permissions in an Android app?
    - Use the following method to request runtime permissions
    `ActivityCompat.requestPermission(Context, ArrayOf(permission), requestCode)`
6. What is the shouldShowRequestPermissionRationale() method, and when should it be used?
    - This method is used to check if an application should explain if a permission is needed before requesting that permission.
7. How do you handle the result of a permission request in an Android app?
    - Override the onRequestPermissionsResult() method and handle the grantResults.
8. What happens if a user denies a permission request multiple times?
    - The system stops showing the prompt to the user for the permission, then the user has to manually grant the permission from the settings.
9. How can an app guide users to enable permissions manually from settings?
    - In this case redirect the user to Settings.ACTION_APPLICATION_DETAILS_SETTINGS.
10. What are the best practices for requesting runtime permissions to improve user experience?
    - Request only necessary permissions.
    - Explain why the permissions are needed.
    - Handle the denial gracefully.
11. How do you handle permissions in background service?
    - Use ForegroundService permissions and request necessary runtime permissions.
12. What are special permissions like SYSTEM_ALERT_WINDOW and WRITE_SETTINGS, and how are they requested?
    - These permission require explicit user approval via Settings.ACTION_MANAGE_OVERLAY_PERMISSION
13. How does Android handle permissions for accessing external storage?
    - Android 10+ uses Scoped Storage, restricting direct file access.
14. What are foreground service permissions, and how do they differ from normal permissions?
    - Foreground Service require FOREGROUND_SERVICE permission and must display a notification.
15. How do permission work in Android 11 and later, especially regarding scoped storage?
    - Application must use MediaStore APIs and request MANAGE_EXTERNAL_STORAGE permission.
16. How can an application request multiple permissions at once?
    - Pass multiple permissions in the requestPermissions() method.
17. What are permission groups and how do they affect permission requests?
    - These permissions are grouped. Granting one permission will grant all the permissions in the group.
18. How do you handle permission revocation when an application is updated or reinstalled?
    - Above Android 11 system automatically revokes unused permission.
    - Below Android 11 when an application is updated or reinstalled, permissions are generally retained, unless the application undergoes a major change, such as different signature or complete uninstall. There are some key behaviours to consider
      - App Updates: If the application is updated without being uninstalled, previously granted permissions remain intact.
      - App Re-installation: If the application is uninstalled and then reinstalled, the user must grant permissions again.
      - System-level Changes: Some OEMs or security policies may reset permissions upon updates.
      - Permissions in Shared Storage: If an application access external storage, permissions may need to be re-requested after reinstalling.
19. What are permissions in Android, and why are they needed?
    - Permissions allow an application to access restricted system features and user data. Permissions provide security by preventing unauthorized access.
20. Explain the difference between normal and dangerous permissions
    - Normal Permissions: The permissions which are automatically granted. e.g. INTERNET
    - Dangerous Permissions: The permissions which require user approval. e.g. READ_CONTACTS
#