# kotlin-coroutines-android
Useful extensions for coroutines.

Provide easier MainScope integration and auto disposable Job according to the view attached state.

## AutoDisposable

Inspired by [AutoDispose](https://github.com/uber/autodispose), auto cancel the job according to the attach state of corresponding view.

### Usage

Add dependency in gradle:

```
api "com.bennyhuo.kotlin:coroutines-android-autodisposable:$version"
```

Use `asAutoDisposable` to convert a `Job` to an `AutoDisposableJob`. Then the job will be cancelled when the view is removed.

In the sample below, `delay(1000)` will not be executed.

```kotlin
val anotherButton = Button(this)
parentView.addView(anotherButton, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

anotherButton.setOnClickListener {
    GlobalScope.launch(Dispatchers.Main) {
        log(1)
        parentView.removeView(anotherButton)
        delay(1000)
        log(2)
    }.asAutoDisposable(it)
}
```

## MainScope

Supplement for kotlinx.coroutines, providing useful extensions for android Views and easier way to integrate.

An instance of `MainScope` which use `Dispatchers.Main` as its dispatcher will be bound to the lifecycle of the corresponding `Activity`. In other words, an instance of `MainScope` will be created when an activity is created and cancelled when the activity is destroyed.

### Usage

Add dependency in gradle:

```
api 'com.bennyhuo.kotlin:coroutines-android-mainscope:1.0'
```

Initialize this library in your customized `Application`:

```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MainScope.setUp(this)
    }
}
```

Implement `BasicScoped` to include `View` extensions for android sdk, `RecyclerViewScoped` for `RecyclerView`, `DesignScoped`  for android support design library, `AppCompatScoped` for android support appcomat library. If more than one interfaces are used, just implement whatever you need.

```kotlin
class MainActivity : AppCompatActivity(), AppCompatScoped, RecyclerViewScoped {
    ...
}
```

To access the `MainScope`, just use the property `mainScope`. Be careful that it is not thread safe to use outsize the main thread.

```kotlin
class MainActivity : AppCompatActivity(), BasicScoped {
    override fun onCreate(savedInstanceState: Bundle?) {
        ...
        mainScope.launch {
            log("Hey!")
        }
        ...
    }
}
```

It is also easy to launch multi-coroutines with the `withMainScope` method:

```kotlin
...
withMainScope {
    launch {
        ...
    }

    async(Dispatchers.IO) {
        ...
    }
}
...
```

Most of the listeners borrowed from [Anko](https://github.com/kotlin/anko) are equipped with `MainScope` instead of the `GlobalScope` so that coroutines launched in these listeners will be cancelled when the corresponding activity is destroyed.

```kotlin
...
    button.onClick {
        log(1)
        delay(1000)
        log(2)
        textView.text = "Hello Coroutine!"
    }
...
```

`onClick` receives a suspend lambda as the body of the job. Once the button clicked, the lambda block will be started immediately on the main thread, and suspended at `delay(1000)`. If you leave the activity by pressing the back key, the suspend lambda won't be dispatched since the job is cancelled by the `MainScope` installed in the activity.


## Issue

Please feel free to issue and pull request.

# License

[MIT License](LICENSE)