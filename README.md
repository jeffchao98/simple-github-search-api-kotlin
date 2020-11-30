# Simple helper for search Github repo

- This project provide the simple helper that returns a list of GitHub repository information with the given `organization` and `platform`.

## Setup

- [MUST] You should add the tag `<uses-library android:name="org.apache.http.legacy" android:required="false" />` in `AndroidManifest.xml` , as the following example

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.yourapp">
    <application
    ...
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher">
        ...
        <uses-library android:name="org.apache.http.legacy" android:required="false" />
    </application>
</manifest>

```

### Manually import the SDK

- After compile the whole project( including make the module GithubSearcherKotlin ), you can find the `.aar` file in the path `${repo root path}/RepoSearcherKotlin/GithubSearcherKotlin/build/outputs/aar`
- Copy-paste the aar file to the path `${project root path}/app/libs` your project ( you can create `libs` folder if not created yet )
- In the gradle script of your project ( app level ) add the `repositories` for include the `libs` folder before the `dependencies` section, as the followng example:

```gradle
repositories {
    flatDir {
        dirs 'libs'
    }
}
dependencies {
...
```

- In the `dependencies` section of the app-level gradle script, add the followinf three implementation statements import this SDK and the related resources, assume the filename of the aar file is `GithubSearcherKotlin-debug`

```gradle
dependencies {
    ...
    implementation 'com.mcxiaoke.volley:library:1.0.19'
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation(name: 'GithubSearcherKotlin-debug', ext: 'aar')
    ...
}
```

## Usage

### Constructor

> GitRepoSearcher(context: Context)

- When your application start, you should initialize the library using the constructor as the followng sameple:

```Kotlin
        val searcher =  GitRepoSearcher(this);
```

### Search method

> fun searchWith(
>       platform: String,
>       organization: String,
>       callback: ((success: Boolean, items: List<RepoItem>) -> Unit))

- For execute search, you can use the method after initial the library.
- The first two string element represent the required input props `platform` and `organization`
- In order to listen the search result and access the detail data in the logic, you need to setup and implement `callback` for the third input prop, which will return two attribtues:
  - The Boolean value `success` indicate if the search via github api is success or not
  - The `RepoItem` list indicate the fetched items, attributes as the following

| Name | Type | description |
| --- | --- | --- |
| name | String | name of the fetched repo info |
| fullName | String | full name of the fetched repo info |
| description | String | description of the fetched repo info |
| language | String | using language of the fetched repo info |
| privateStatus | String | private status of the fetched repo info |

- For using the method, sameple as the follwoing

```Kotlin
        searcher.searchWith("android", "google") { success, list ->
            if(success) {
                Log.i("TEST", list.size.toString())
            }
        }
```

