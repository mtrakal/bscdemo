# demo project for BSC

Demo should be almost done (expect one callback [NoteRVAdapter.kt: Line 53](app/src/main/java/cz/mtrakal/bscdemo/ui/main/NoteRVAdapter.kt#L53).

Response of requests is logged with tag: `Response`.

But provided **API is static** and didn't return updated data.

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