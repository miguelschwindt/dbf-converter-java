package miguel.schwindt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFUtils;
/**
 * <pre>Clase para pasar de un archivo DBFConverter a un TXT separado por tabulaciones o XLS como extensión. El archivo resultado abrirlo con EXCEL para
 * ver sus columnas.
Debe ingresar 3 parámetros si elije la opción 1; y 4 parametros si elije la opción 2 o 3
1) Parametro 1: Opcion - Los valores permitidos son 1, 2 o 3(masivo)
Si seleeciona opcion 1 entonces en los parametros 2 y 3 debe ingresar la ruta completa de sus archivos origen y destino incluyendo la extensión
Si seleeciona opcion 2 entonces en el parametros 2 debe ingresar la ruta a la Carpeta que contiene el archivo DBFConverter. 
Si seleeciona opcion 3 (masivo) se hará una creación masiva de archivos en base a todos los archivos de la extension de valor en el parámetro 3 del Directorio indicado en parametro 2");

2) Parametro 2:
 Si eligió la opción 1 debe ingresar la ruta completa al archivo de ORIGEN
 Si eligió la opción 2 debe ingresar la ruta al Directorio donde se encuentra el archivo a transformar ( Con barra final, / o \\ si esta en windows) 
 Si eligió la opción 3 (masivo) debe ingresar la ruta al Directorio donde se encuentra el archivo a transformar ( Con barra final, / o \\ si esta en windows) 

3) Parametro 3:
 Si eligió la opción 1 debe ingresar la ruta del archivo destino
 Si eligió la opción 2 debe ingresar el nombre del archivo a leer incluyendo la extension, ejemplo: cliente.dbf
 Si eligió la opción 3 (masivo) debe ingresar la extensión del archivo a leer, por ejemplo, .dbf

4) Parametro 4: Formato de los archivos de salida. Por ejemplo .txt, .xls, etc. Complete UNICAMENTE este parámetro si selecciona las opciones 2 o 3 en el parámetro 1

EJEMPLO:
OPCION 1: java -jar dbf.jar 1 /usr/archivos/cliente.dbf /usr/archivos/cliente.txt
OPCION 2: java -jar dbf.jar 2 /usr/archivos/ cliente.dbf .txt
Esta opción genera un archivo llamado /usr/archivos/cliente.txt
OPCION 3: java -jar dbf.jar 3 /usr/archivos/ .dbf .xls
Esta opción genera un archivo con extensión .xls para cada archivo dbf que encuentre en la carpeta dada
</pre>

 * @author Miguel Angel Schwindt
 * @version v1
 * @since 9 SEP 2017 - 15h25 
 *
 */
public class DBFConverter {

	public static void main(String[] args) {
		//Deshabilite las líneas comentadas abajo para ejecutar un ejemplo, cambie la ruta del Directorio a su propia máquina
//		String[] ar = new String[4];
//		ar[0] = "2";
//		ar[1] = "/Users/miguel/Desktop/col_med_tablas/";
//		ar[2] = "ARANCELES.DBF";
//		ar[3] = ".xls";
//		args = ar;
		Integer opcion = new Integer(args[0]);
		
		if ( args.length == 3 && opcion == 1){
			convertir(args);
		}
		if ( args.length == 4 && opcion == 2){
			convertir(args);
		}
		else if ( args.length == 4 && opcion == 3){
			File[] archivos = finder( args[1], args[2] );
			for (int i = 0; i < archivos.length; i++ ){
				String[] arr = new String[4];
				arr[0] = "2"; 
				arr[1] = args[1];
				arr[2] = archivos[i].getName();
				arr[3] = args[3];
				convertir(arr);
			}	
		}	
		else{
			System.out.println("Debe ingresar 3 parámetros si elije la opción 1; y 4 parametros si elije la opción 2 o 3");
			System.out.println("1) Parametro 1: Opcion - Los valores permitidos son 1, 2 o 3(masivo)");
			System.out.println("\tSi seleeciona opcion 1 entonces en los parametros 2 y 3 debe ingresar la ruta completa de sus archivos origen y destino incluyendo la extensión");
			System.out.println("\tSi seleeciona opcion 2 entonces en el parametros 2 debe ingresar la ruta a la Carpeta que contiene el archivo DBFConverter. ");
			System.out.println("\tSi seleeciona opcion 3 (masivo) se hará una creación masiva de archivos en base a todos los archivos de la extension de valor en el parámetro 3 del Directorio indicado en el parametro 2");
			
			System.out.println("2) Parametro 2:");
			System.out.println("\t Si eligió la opción 1 debe ingresar la ruta completa al archivo de ORIGEN");
			System.out.println("\t Si eligió la opción 2 debe ingresar la ruta al Directorio donde se encuentra el archivo a transformar ( Con barra final, / o \\ si esta en windows) ");
			System.out.println("\t Si eligió la opción 3 (masivo) debe ingresar la ruta al Directorio donde se encuentra el archivo a transformar ( Con barra final, / o \\ si esta en windows) ");
			
			System.out.println("3) Parametro 3:");
			System.out.println("\t Si eligió la opción 1 debe ingresar la ruta del archivo destino");
			System.out.println("\t Si eligió la opción 2 debe ingresar el nombre del archivo a leer incluyendo la extension, ejemplo: cliente.dbf");
			System.out.println("\t Si eligió la opción 3 (masivo) debe ingresar la extensión del archivo a leer, por ejemplo, .dbf");
			
			System.out.println("4) Parametro 4: Formato de los archivos de salida. Por ejemplo .txt, .xls, etc. Complete UNICAMENTE este parámetro si selecciona las opciones 2 o 3 en el parámetro 1");
			
			System.out.println("\nEJEMPLO:");
			System.out.println("\tOPCION 1: java -jar dbf.jar 1 /usr/archivos/cliente.dbf /usr/archivos/cliente.txt");
			System.out.println("\tOPCION 2: java -jar dbf.jar 2 /usr/archivos/ cliente.dbf .txt");
			System.out.println("\t\tEsta opción genera un archivo llamado /usr/archivos/cliente.txt");
			System.out.println("\tOPCION 3: java -jar dbf.jar 3 /usr/archivos/ .dbf .xls");
			System.out.println("\t\tEsta opción genera un archivo con extensión .xls para cada archivo dbf que encuentre en la carpeta dada");
		}			
	}
	
	/**
	 * Devuelve todos los archivos con la extensión dada
	 * @param dirName - Nombre del Direcotrio
	 * @param extension - txt, dbf, etc.
	 * @return
	 */
	public static File[] finder( String dirName, String extension){
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() { 
                 public boolean accept(File dir, String filename)
                      { return filename.endsWith( extension.toUpperCase()) || filename.endsWith(extension.toLowerCase()); }
        } );

    }
	
	public static void convertir(String[] args){
		
		DBFReader reader = null;
		try {
			Integer opcion = new Integer(args[0]); // Si opcion es 1, entonces las rutas las tomo tal cual son, sino el argumento 1 es la carpeta y 
									// el arg 2 es el nombre del archivo, La extensión va a ser DBFConverter para entrada y TXT para salida
			String rutaOrigen = args[1]; 
			String rutaDestino = args[2];
			
			if (opcion == 2){
				String ext = args[3];
				String ruta = rutaOrigen;
				String nombre_archivo = rutaDestino;
				rutaOrigen = rutaOrigen +  nombre_archivo;
				nombre_archivo = nombre_archivo.substring(0, (nombre_archivo.length() - 4)) + ext;
				rutaDestino = ruta + nombre_archivo;  
			}
			
			reader = new DBFReader(new FileInputStream(rutaOrigen));
			int numberOfFields = reader.getFieldCount();
			List<String> datos = new ArrayList<String>();
			String cabecera = "";
			String fila = "";
			for (int i = 0; i < numberOfFields; i++) {
				DBFField field = reader.getField(i);
				cabecera += field.getName();
				if (numberOfFields != (i+1))
					cabecera += "\t";
//				else
//					cabecera += "\n";
			}
			System.out.println("INICIANDO letura de archivo DBFConverter con origen en la ruta: "+rutaOrigen);
			System.out.println("CABECERA: "+cabecera);
			datos.add(cabecera);
			System.out.println("Iniciando parseo de datos (esto puedo tardar varios segundos/minutos)...");
			
			Object[] rowObjects;
			while ((rowObjects = reader.nextRecord()) != null) {
				fila = "";
				for (int i = 0; i < rowObjects.length; i++) {
					fila += rowObjects[i];
					if (numberOfFields != (i+1))
						fila += "\t";
//					else
//						fila += "\n";
				}
				if (!fila.equals(""))
					datos.add(fila);
			}
			
			System.out.println("FIN del parseo de datos - OK");
			System.out.println("CANTIDAD DE FILAS PARSEADAS: "+datos.size());
			
			exportarStringAArchivoTXT(datos, rutaDestino, "UTF-8");

		} catch (DBFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			DBFUtils.close(reader);
		}		
	}
	
	/**
	 * Recibe un String y lo pasa a un archivo txt especificado en la ruta dada. Tira una información de las filas que se omitieron 
	 * porque son NULL, si es que las hay.
	 * @param datos - Datos a generar el archivo TXT
	 * @param ruta - ruta del archivo
	 * @param charset - Tipo de datos. "UTF-8" si no sabes que poner
	 */
	public static void exportarStringAArchivoTXT(List<String> datos, String ruta, String charset){
		try{
			System.out.println("Iniciando proceso de creación de archivo...");
			PrintWriter writer = new PrintWriter(ruta, charset);
		    int valoresNull = 0;
		    for(int i=0; i< datos.size(); i++){
		    	if (datos.get(i) != null){
		    		writer.println(datos.get(i));
		    	}
		    	else{
		    		valoresNull++;
		    	}	    	
		    }
		    writer.close();
		    System.out.println("Valores en NULL: "+valoresNull);
		    System.out.println("FIN proceso de creación de archivo - OK");
		    System.out.println("Su archivo se encuentra en la ruta: "+ruta);
		} catch (IOException e) {
		   System.out.println(e.getMessage());
		}
	}
}
