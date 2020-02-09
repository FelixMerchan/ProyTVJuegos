package Controladores;
/**
 * Clase Comun contiene los metodos globales a todos los controladores
 * @author Bertha Mazón
 * @date 2018/08/04
 * @version 1.0
 */
import java.io.File;

public class Global {

	//obtiene ruta del código fuente del Proyecto
    public static String getPath(){
        //extraer ruta del proyecto de forma dinámica
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        //eliminar los dos caracteres del final del path
        path=path.substring(0,path.length()-2);
        System.out.println("Path del Proyecto " + path);
        return path+="\\src\\";
    }



}
