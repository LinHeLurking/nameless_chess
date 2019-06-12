if (!(Test-Path .\build)){
    New-Item -Name build -ItemType Directory
}

javac -h .\src\main\cpp .\src\main\java\online\ruin_of_future\nameless_chess\CppSync.java -d .\build\

cd .\build

cmake ..\src\main\cpp\

cd ..