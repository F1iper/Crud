call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startbrowser
echo.
echo runcrud had problem
goto fail

:startbrowser
start http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto finish
echo.
echo Cannot start browser
goto fail

:fail
echo.
echo There were errors

:finish
echo.
echo Work is finished