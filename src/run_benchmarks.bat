@echo off
REM Script to run the Network Flow algorithm on all benchmarks

echo Compiling Java files...
javac *.java

echo.
echo Running benchmarks from ../benchmarks directory...
java Main ../benchmarks

echo.
echo Done.
pause