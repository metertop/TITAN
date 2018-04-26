@echo off


for /d %%i in (titan-*) do (
	echo %%i
pushd "%%i"
copy /y "target\*.zip" "..\"
copy /y "target\*.war" "..\"
popd)

pause

