.\clean_cpp_build.ps1
if(Test-Path .\target){
    Remove-Item -r .\target -force
}
