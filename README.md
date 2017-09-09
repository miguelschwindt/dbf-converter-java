# dbf-converter-java
Conversor de base de datos DBF a archivo .txt separados por tabulaciones o .xls

El programa viene con el archivo .jar compilado en la carpeta libs del proyecto. Puede utilizar este dbf-converter-java.jar directamente para pasar sus archivos dbf a txt o xls. El comando que debe ejectura depende de la opci�n que elija. 
Para utilizarlo abra una terminal y ejecute alguna de las opciones que detallo debajo.

<pre>
Debe ingresar 3 par�metros si elije la opci�n 1; y 4 parametros si elije la opci�n 2 o 3
1) Parametro 1: Opcion - Los valores permitidos son 1, 2 o 3(masivo)
Si seleeciona opcion 1 entonces en los parametros 2 y 3 debe ingresar la ruta completa de sus archivos origen y destino incluyendo la extensi�n
Si seleeciona opcion 2 entonces en el parametros 2 debe ingresar la ruta a la Carpeta que contiene el archivo DBFConverter. 
Si seleeciona opcion 3 (masivo) se har� una creaci�n masiva de archivos en base a todos los archivos de la extension de valor en el par�metro 3 del Directorio indicado en parametro 2");

2) Parametro 2:
 Si eligi� la opci�n 1 debe ingresar la ruta completa al archivo de ORIGEN
 Si eligi� la opci�n 2 debe ingresar la ruta al Directorio donde se encuentra el archivo a transformar ( Con barra final, / o \\ si esta en windows) 
 Si eligi� la opci�n 3 (masivo) debe ingresar la ruta al Directorio donde se encuentra el archivo a transformar ( Con barra final, / o \\ si esta en windows) 

3) Parametro 3:
 Si eligi� la opci�n 1 debe ingresar la ruta del archivo destino
 Si eligi� la opci�n 2 debe ingresar el nombre del archivo a leer incluyendo la extension, ejemplo: cliente.dbf
 Si eligi� la opci�n 3 (masivo) debe ingresar la extensi�n del archivo a leer, por ejemplo, .dbf

4) Parametro 4: Formato de los archivos de salida. Por ejemplo .txt, .xls, etc. Complete UNICAMENTE este par�metro si selecciona las opciones 2 o 3 en el par�metro 1

EJEMPLO:
OPCION 1: java -jar dbf.jar 1 /usr/archivos/cliente.dbf /usr/archivos/cliente.txt
OPCION 2: java -jar dbf.jar 2 /usr/archivos/ cliente.dbf .txt
Esta opci�n genera un archivo llamado /usr/archivos/cliente.txt
OPCION 3: java -jar dbf.jar 3 /usr/archivos/ .dbf .xls
Esta opci�n genera un archivo con extensi�n .xls para cada archivo dbf que encuentre en la carpeta dada
</pre>
