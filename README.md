# Wag Code Challenge

### Objective
Fetch and display the first page of data from the Stackoverlow Users endpoint.

### Functionality
If the device is connected to a network, the app fetches the first page of data from the Stackoverflow API and displays the username, gravatar, and badge counts for each user in a RecyclerView. Everytime the app fetches data from the API, it saves the new set of user data locally, deleting the old list of users if any are present. If the device is offline, it fetches and displays the info from the locally stored dataset.

### Third-party libraries used
* [Retrofit](https://github.com/square/retrofit) - HTTP client used to make network calls to the Stackoverflow API. Easy to integrate with very minor setup/overhead. This library  supports asynchronous requests, meaning the UI can remain responsive as data is being fetched.
* [Glide](https://github.com/bumptech/glide) - An image loading and caching library. Also incredibly easy to integrate, with basically no overhead. This library handles media caching so that the user images are only downloaded once and stored for offline usage, even between app sessions.
* [Realm](https://github.com/realm/realm-java) - Mobile database system for local data storage to allow for offline usage. Minor integration overhead - object models can easily be exposed as database models, automatically creating tables and removing the need to build the database schema manually using standard SQLite. Database transactions can be done asychronously to keep the UI responsive. 