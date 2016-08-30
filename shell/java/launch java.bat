@echo off
if "%OS%" == "Windows_NT" setlocal
rem ---------------------------------------------------------------------------
rem
rem Environment Variable Prequisites
rem
rem   CURRENT_DIR     current runtime dir
rem
rem   CLASSPATH       java runtime parameter [-classpath]
rem
rem   JAVA_OPTS       (Optional) Java runtime options used when the "start",
rem                   "stop", or "run" command is executed.
rem
rem   JAVA_HOME       Must point at your Java Development Kit installation.
rem                   Required to run the with the "debug" argument.
rem
rem   JRE_HOME        Must point at your Java Runtime installation.
rem                   Defaults to JAVA_HOME if empty.
rem ---------------------------------------------------------------------------

set JAVA_OPTS=-Xms32m -Xmx1024m %JAVA_OPTS%
set CURRENT_DIR=%cd%
set JAVA_ENDORSED_DIRS=%CURRENT_DIR%\common\endorsed
set CATALINA_TMPDIR=
set MAINCLASS=why.client.Print
set CMD_LINE_ARGS="test string\nss"
set ACTION=
set _EXECJAVA=JAVA

rem set runtime enviroment

rem set classpath
set CLASSPATH=
set CLASSPATH=%CLASSPATH%%CURRENT_DIR%\jar\client.jar;
set CLASSPATH=%CLASSPATH%%CURRENT_DIR%\jar\jar.jar;
set CLASSPATH=%CLASSPATH%%CURRENT_DIR%\jar\print.jar;

rem ----- Execute The Requested Command ---------------------------------------

echo Using OS:          %OS%
echo Using CURRENT_DIR: %CURRENT_DIR%
echo Using JAVA_HOME:   %JAVA_HOME%
echo Using JRE_HOME:    %JRE_HOME%
echo Using JAVA_OPTS:   %JAVA_OPTS%
echo Using CLASSPATH:   %CLASSPATH%

rem Execute Java with the applicable properties
%_EXECJAVA% %JAVA_OPTS% -Djava.endorsed.dirs=%JAVA_ENDORSED_DIRS% -Djava.io.tmpdir="%CATALINA_TMPDIR%" -classpath %CLASSPATH% %MAINCLASS% %CMD_LINE_ARGS% %ACTION%
:end
pause