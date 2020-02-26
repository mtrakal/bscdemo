# demo project for BSC

Demo should be almost done. I'm really not GUI designer at all, so that's the reason why my demo app looks like how it looks :).
Your requirement `created from standard and native components` it a bit tricky, because standard library for most devs (I think) is a huge amount of well known and often used libs from github as well.

But provided **API is static** and didn't return updated data.
For visual check, that app do what should do is there logging of requests with tag: `Response`.

## Installation APK to device
- connect just one real device with enabled ADB and developer mode
- just clone repo
- run commands (Windows)
```
gradlew assembleDebug
adb -d install -r -t ./app/build/outputs/apk/debug/app-debug.apk
```


## Tests
There are just a small tests on [NoteTest.kt: Line 7](model/src/test/java/cz/mtrakal/bscdemo/model/NoteTest.kt#L7) and [ResponseDataTest.kt: Line 14](network/src/test/java/cz/mtrakal/bscdemo/network/ResponseDataTest.kt#L14)
### Running tests
```
gradlew test
```
Output of tests is stored on `./[module]/build/reports/tests/testDebugUnitTest/index.html`

