App name : NewsFeed

This application responsible for showing most viewed popular news from NewYork times based on API network call.

=> HLD(HIGH LEVEL DESIGN):
-------------------------
Used best architecture and design in order to achieve quality, reusability, maintainability  & testability, have
Implemented based on MVVM architecture (Model View ViewModel).

Model:
-----
ApiCallInterfaceService.java
NetworkConstants.java
NewsFeedRepository

The model is class contains business logic, and responsible for creating, managing and storing the data.
There is no way to communicate to UI directly. This model can be provide the data to ViewModel based on request.
Model consisting class above classes. This class is API interface used to query to server and get the response.
In order choose a model, this app is follows repository pattern as well. In future data may consume either from
cloud or ROOM/SqLite database. The differentiation logic can write inside the repository class(NewsFeedRepository)



View:
----
NewsFeedActivity.kt
PopularNewsFragment.kt 

View basically responsible for showing UI content and make user interaction. And this providing result to user 
based on interaction. View consisting of both activity and fragment to display the content, in this project may
require only Activity, even though added fragment for code integrity. In future many fragment can be added for
populating multiple views. Based on above classed have implemented set of UI as below.

-> In NewsFeedActivity.kt

Navigation drawer
Tool bar view
Frame layout for adding or replacing fragment
Dynamically adding snack bar

-> In PopularNewsFragment.java

Swipe to refresh
Recycler view
Progress bar view



ViewModel:
---------
This ViewModel is a class especially designed for storing, managing UI related data info. Most generally LiveData
is a part of ViewModel. The best usecase of ViewModel is help us survive the data from any configuration change
like orientation, language change etc. 

Similarly LiveData having many advantage among that best one is it's providethe data to UI automatically in case
any updation  happened, only when UI is active state.

class used:

Response.java
ResultItem.java
MediaItem.java
MediaMetaDataItem
These are(2, 3, 4, 5) pojo class to are holding data content, which is proving to UI based on query. Pojo class
has been filled with data by using RxJava, Retrofit and GSON. Once retrofit provided response, same time data 
storing into POJO class by Gson. After that Observable return the pojo class object asynchronously. So that 
observer will notify once the data emitted from observable.

NewsFeedViewModel : This is ViewModel class getting calling from view based on request. That request are passed
to model to get updated data, for this need server call. Using observer pattern we are notifying UI with updated 
content. Main responsibility is exposing data to UI.





=> LLD(LOW LEVEL DESIGN):
------------------------

Platform used: Android

Language: Kotlin and Java

Library used: AndroidX, Jetpack, Retrofit, RxJava, Okhttp, Gson & Piccaso

Description:

AndroidX & Jetpack
ViewModel is part of jetpack, with help of jetpack we can avoid bloated class functionality implementation(all in
one) problem and avoid boiler plate codes. ViewModel & LiveData is part of Android Jetpack.

Retrofit is a type safe http client used to getting data from server, it calling to server by using java interface
with many request methods and request params. Okhttp used for network call. RxJava is another library added in this
to get asynchronous call. This is working based on observer and observable concept. Now a days RxJava is more important
as this is having good feature like able to perform network call, perform synchronous and asynchronous call, performing background listening.

Piccaso : Image loading, caching , error handling etc

