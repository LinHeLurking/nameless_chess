Write-Host "Make a build directory if it does not exist"
if (!(Test-Path .\build)){
    New-Item -Name build -ItemType Directory
}
Write-Host ""

Write-Host "Generating JNI headers"

javac -h .\src\main\cpp .\src\main\java\online\ruin_of_future\nameless_chess\CppSync.java -d .\build\
Write-Host ""

Write-Host "Building via Cmake"

cd .\build

cmake ..\src\main\cpp\ -A x64 -T host=x64

cmake --build . 

cd ..