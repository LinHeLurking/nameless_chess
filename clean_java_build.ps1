Write-Host "Removing java build directory directly"
if(Test-Path .\target){
    Remove-Item -r .\target -force
}


