@echo off

netstat -ano

netstat -aon|findstr "1090"