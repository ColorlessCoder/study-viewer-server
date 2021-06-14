# Simple Study Viewer Server

## Setting up your environment

### Java JDK
1. Install java JDK 8 (e.g 1.8.0_291)
2. Set Environment Variable `JAVA_HOME` and set value as the install directory of JDK.
   
   E.g. `C:\Program Files\Java\jdk1.8.0_291`
3. Add the jdk `bin` directory in the path variable. E.g. `C:\Program Files\Java\jdk1.8.0_291\bin`

### Gradle
1. Install gradle version 7.0.2
2. Add the bin directory to the path variable. E.g. `C:\Program Files\gradle-7.0.2\bin`
3. Check whether gradle is available in path by running `gradle -v`

### MySQL
1. Install MySql 8.0
2. Keep MySql running
3. Create a database (Don't create any tables)

## Run the project
1. cd to the Project directory
2. Open src/main/resources/application.properties in editor (E.g. Notepad)
3. Set the value for properties `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`
4. Format for datasource url is `jdbc:mysql://${host}:${port}/${database}`
5. cd to the Project directory and run command `gradle bootRun`
6. Accept firewall permission if prompted
7. The server will be available on http://localhost:8080
8. The server can be stopped by pressing ctrl + c twice.
9. If you run the application for the first time, then tables will be created automatically by hibernate on run.

## Test

### Unit Test
1.  cd to the Project directory and run command `gradle test`.
2.  If any test fails then exception will be shown.
3.  To see detailed test result, run command `gradle test -i`

## Create Executable Jar
1. Build the UI side code first following the instructions given here: [link](https://github.com/ColorlessCoder/study-viewer-ui/blob/main/README.md#build-project)
2. For convenience lets say `${clientDir}` is the path containing [Client side code](https://github.com/ColorlessCoder/study-viewer-ui) and `${serverDir}` is the path containing [Server Side code](https://github.com/ColorlessCoder/study-viewer-server). 
3. Copy all the content of the `${clientDir}/build/` directory to all the content to the `${serverDir}/src/main/resources/static/` directory. E.g. In windows
```bash
xcopy "${clientDir}\build\" "${serverDir}\src\main\resources\static\" /E/y
```
4. Set application.properties as explained int 'Run the Project' section.
5. cd to the `${serverDir}` and run command `gradle bootJar`.
6. After complete the run, the executable jar can be found in `${serverDir}/build/libs/`.
7. For executing the jar enviornemt should have jdk or jre installed.
8. Execute the jar with the following command `java -jar demo-0.0.1-SNAPSHOT.jar`
9. The server can be stopped by pressing ctrl + c twice.
10. The server will be available on http://localhost:8080
