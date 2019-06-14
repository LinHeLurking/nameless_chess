Write-Host "Removing cpp build directory directly"
if(Test-Path .\build){
    Remove-Item -r .\build -force
}

if(Test-Path .\lib){
    Remove-Item -r .\lib -force
}

