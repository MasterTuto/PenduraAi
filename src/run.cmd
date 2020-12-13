@echo off
:inicio
@ECHO CRIANDO LISTA DE ARQUIVOS JAVA E COMPILANDO...
@SET t=0
@GOTO countjavafiles

:continue
@echo QUANTIDADE === %n%
@FOR /f "delims=" %%f IN ('dir /b /s /c *.java') DO  @CALL :compileitem "%%f"
@ECHO EXECUTANDO...
@java  --module-path ..\lib --add-modules javafx.controls,javafx.fxml,javafx.base com.projetofinal.Main
@DEL /s *.class
@GOTO :EOF

:countjavafiles
@SET n=0
FOR /f "delims=" %%f IN ('dir /b /s /c *.java') DO (
    @SET /a n=%n%+1
)
@GOTO :continue

:compileitem
@javac --module-path ..\lib --add-modules javafx.controls,javafx.fxml,javafx.base %1
@SET /a t=%t%+1
@ECHO COMPILANDO ARQUIVO %t%/%n%