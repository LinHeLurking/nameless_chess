javac -h .\c_src\  .\j_src\main.java

cmake .
cmake --build . --config release

$mk=1
foreach ($i in $(Get-ChildItem)){
    # echo $i.Name
    if($i.Name -eq "lib"){
        $mk=0
        break
    }
}
# echo $mk
if($mk -eq 1){
    mkdir lib
}
cp .\Release\nameless_chess_cpp.dll .\lib\nameless_chess_cpp.dll

echo ""

echo 'you should run the java class by "java "-Djava.library.path=..\lib" Main" in the built Main.class directory'