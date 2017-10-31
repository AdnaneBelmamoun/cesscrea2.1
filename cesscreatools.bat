
@echo %windir%\system32\
@cd %windir%\system32\
@xcopy "%1\setx.exe"  /Y /G
@xcopy "%1\Shortcut.exe"  /Y /G
@xcopy "%1\SHORTCUT.DLL"  /Y /G
@cd %USERPROFILE%

