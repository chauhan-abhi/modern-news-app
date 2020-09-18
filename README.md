# \[ 🚧 Work in progress 👷‍♀️⛏👷🔧️👷🔧 🚧 \] News App(2.0)

Modern News App is a simple news app 🗞️ which uses [NewsAPI](https://newsapi.org/) to fetch top news headlines for different categories from the API.
The app is a guide to build Modern Android applications for all Android Developers.
This repository includes improvements over the [News App(1.0)](https://github.com/chauhan-abhi/News-App-Assignement) repository.

The codebase focuses 👓 on following key things:
- Code structuring, style and comments
- Dependency injection 🗡
- Offline first ✈️
- Kotlin + Coroutines
- And best practices 🛠

<table>
<td>   
<img alt="NewsApp Main Page" height="450px" src="https://github.com/chauhan-abhi/modern-news-app/blob/master/art/Screenshot2_ModernNewsApp.jpg" />
    </td>
<td>
<img alt="NewsApp Detail Page" height="450px" src="https://github.com/chauhan-abhi/modern-news-app/blob/master/art/Screenshot3_ModernNewsApp.jpg" />
    </td>
</table>

## TODO
- Time formatting of published date
- Detail Screen UI completion
- Search Functionality implementation

## API key 🔑
You'll need to provide API key to fetch the news from the News Service (API). Currently the news is fetched from [NewsAPI](https://newsapi.org/)

- Generate an API key (It's only 2 steps!) from [NewsAPI](https://newsapi.org/)
- Create new file named -> `credentials.properties` in our project root folder
- Add the API key as shown below [Make sure to keep the double quotes]:
```
    NEWS_API_KEY = "<INSERT_YOUR_API_KEY>"
```
- Build the app 
- Enjoyyyyy 🎉

## Libraries and tools 🛠

News App uses libraries and tools used to build Modern Android application, mainly part of Android Jetpack 🚀

- [Kotlin](https://kotlinlang.org/) first
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) first
- Architecture components
- [Dagger Hilt](https://dagger.dev/hilt/) for dependency injection 🗡
- [Retrofit](https://square.github.io/retrofit/)
- Other [Android Jetpack](https://developer.android.com/jetpack) components

## Architecture

The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.

Read more: 
- [Building Modern Android Apps with Architecture Guidelines](https://medium.com/@aky/building-modern-apps-using-the-android-architecture-guidelines-3238fff96f14)
- [Guide to app architecture](https://developer.android.com/jetpack/docs/guide)

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)


