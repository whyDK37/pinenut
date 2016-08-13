@echo off

set dir=%CD%
for /f "delims=" %%i in ('dir /ad/b "%dir%"') do (
    echo updating %dir%\%%i
	cd %dir%\%%i
	git pull   
	cd ..
)
pause