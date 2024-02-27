cd C:\Users\phili\OneDrive\Desktop\GFOS-23\Frontend
call npm run build
cd "..\Backend\src\main\webapp"
xcopy /E /Y "..\..\..\..\Frontend\dist"
cd "..\..\..\..\Frontend"
rmdir /S /Q dist
pause