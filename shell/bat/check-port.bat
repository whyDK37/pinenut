@echo off

rem check all port
netstat -ano

rem check specific port
netstat -aon|findstr "1090"

