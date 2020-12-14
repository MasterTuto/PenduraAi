@echo off
:inicio
@ECHO CRIANDO LISTA DE ARQUIVOS JAVA E COMPILANDO...
@SET t=0
@GOTO countjavafiles

:continue
@ECHO COMPILANDO...
@FOR /f "delims=" %%f IN ('dir /b /s /c *.java') DO  @CALL :compileitem "%%f"
@ECHO EXECUTANDO...
@java  --module-path ..\lib --add-modules javafx.controls,javafx.fxml,javafx.base com.projetofinal.Main
@DEL /s /q *.class
@GOTO :EOF

:countjavafiles
@SET n=0 & @FOR %%f in ('dir /b /s /c *.java') DO @(set /a filesCount+=1 > nul)
@GOTO :continue

:compileitem
@SET /a t=%t%+1
@REM @ECHO COMPILANDO ARQUIVO %t%/%n%
@javac --module-path ..\lib --add-modules javafx.controls,javafx.fxml,javafx.base %1