package Controladores;
/**
 * Controlador de cliente
 * @author Kevin Zhune
 * @date 07/02/2020
 * @version 1.1
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;


import Modelos.*;

public class cCliente {
	//arreglo dinámico de objetos. Arraylist es una coleccion predefinida en JAVA
	ArrayList<Cliente> Lista = new ArrayList<Cliente>();
	//encabezados de columnas de la tabla

	//cedula, apellido,  nombre, telefono, email, ciudad, direccion, observacion
	public String[] columnName = {"No.", "Cedula", "Apellido", "Nombre","Telefono", "Email", "ciudad", "Direccion", "Observacion"};

	/**
	 * Retorna la cantidad de objetos del arreglo
	 */
	public int Count(){
		return Lista.size();
	}

	/**
	 * Borrar todos los elementos del arreglo
	 */
	public void Clear(){
		Lista.clear();
	}

	/**
	 * Registra un nuevo Cliente
	 */
	public void nuevo(Cliente e) throws IOException{
		int pos=localizar(e.cedula);
		if(pos==-1){//si cedula no esta registrada, se agrega nuevo Cliente
			Lista.add(e);
		}
		else{
			throw new RuntimeException("Cliente ya asigando");
		}
	}

	/**
	 * Modificar datos de un Cliente existente
	 */
	public void modificar(Cliente e, String ced) throws IOException{
		int pos=localizar(ced);
		if(pos>-1){//si Cliente está registrado se modifica
			Lista.set(pos, e);
		}
		else{
			throw new RuntimeException("No existe un cliente con esa cédula");
		}
	}

	/**
	 * Eliminar un Cliente
	 */
	public void eliminar(String cedula) throws IOException{
		int pos=localizar(cedula);
		if(pos>-1){//si Cliente está registrado se elimina
			Lista.remove(pos);
		}
		else{
			throw new RuntimeException("No existe un Cliente con la cédula ingresada");
		}
	}

	/**
	 * Lista datos en un defaultablemodel para presentar en una tabla
	 */
    public DefaultTableModel getTabla(){
        DefaultTableModel tabla = new DefaultTableModel(columnName, 0){
        	@Override
            public boolean isCellEditable(int row, int column) {  return false; }
        };
        for(int i=0; i<Lista.size();i++){
        	Cliente e=(Cliente)Lista.get(i);
        	Object[] row={
        			//cedula, apellido,  nombre, telefono, email, ciudad, direccion, observacion
        			new Integer(i+1), e.cedula, e.apellido, e.nombre, e.telefono, e.email,
        			e.ciudad, e.direccion, e.observacion
        			};
            tabla.addRow(row);
        }
        return tabla;
    }

	/**
	 * Algoritmo de busqueda secuencial en el arreglo Lista
	 * @param cedula
	 * @return posicion en el arreglo del Cliente encontrado
	 */
	public int localizar(String cedula){
		int pos=-1; //se retorna -1 si no se encuentra en el arreglo
		for(int i=0; i<Lista.size(); i++){
			Cliente e=(Cliente)Lista.get(i);
			if(cedula.equals(e.cedula)){
				pos=i; //posicion encontrada
				break; //finaliza el ciclo for
			}
		}
		return pos;
	}

	/**
	 * Algoritmo de busqueda secuencial mediante criterio de codigo parcial en el arreglo Lista
	 * @param cedula
	 * @return cCliente
	 * @throws IOException
	 */
	public cCliente buscar_cedula(String cedula ) throws IOException{
		cCliente ob=new cCliente();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Cliente e=(Cliente)Lista.get(i);
			if(e.cedula.length() >= cedula.length()){
				cad=e.cedula.substring(0, cedula.length());
				if(cad==(cedula)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}

	/**
	 * Algoritmo de busqueda secuencial mediante criterio nombre del cliente parcial en el arreglo Lista
	 * @param apellido
	 * @return cCliente
	 * @throws IOException
	 */
	public cCliente buscar_apellido(String apellido) throws IOException{
		cCliente ob=new cCliente();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Cliente e=(Cliente)Lista.get(i);
			if(e.apellido.length() >= apellido.length()){
				cad=e.apellido.substring(0, apellido.length());
				if(cad.equalsIgnoreCase(apellido)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}

	//Implementar mas metodos de busqueda por otros critrios
	/**
	 * Algoritmo de busqueda secuencial mediante criterio nombre del producto parcial en el arreglo Lista
	 * @param nombre
	 * @return cCliente
	 * @throws IOException
	 */
	public cCliente buscar_nombre(String nombre) throws IOException{
		cCliente ob=new cCliente();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Cliente e=(Cliente)Lista.get(i);
			if(e.nombre.length() >= nombre.length()){
				cad=e.nombre.substring(0, nombre.length());
				if(cad.equalsIgnoreCase(nombre)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}






	/**
	 * Retornar un objeto del arreglo Lista
	 * @param pos es la posicion del objeto en el arreglo
	 * @return objeto encontrado
	 */
	public Cliente getCliente(int pos){
		Cliente ob=null;
		if(pos>=0 && pos<Lista.size()){
			ob=Lista.get(pos);
		}
		return ob;
	}


	//leer datos desde un archivo de texto en formato csv
	public static final String SEPARADOR=";";
    public static final String QUOTE="\"";

	public void leer() throws IOException{
		BufferedReader br = null;
	    try {
	    	String path = Global.getPath()+"\\Recursos\\dataProducto.csv";
	    	br =new BufferedReader(new FileReader(path));
	        String line = br.readLine();//linea de encabezado
	        Clear(); //limpiar lista de objetos
	        line = br.readLine();
	        while (line!=null) {
	           String [] row = line.split(SEPARADOR);
	           removeTrailingQuotes(row);
	           Cliente ob=new Cliente();
	         //cedula, apellido,  nombre, telefono, email, ciudad, direccion, observacion
	           ob.cedula=row[0];
	           ob.apellido=row[1];
	           ob.nombre=row[2];
	           ob.telefono=row[3];
	           ob.email=row[4];
	           ob.ciudad=row[5];
	           ob.direccion=row[6];
	           ob.observacion=row[7];
	           nuevo(ob);//agregar a la lista

	           //System.out.println(ob.nombre);
	           //System.out.println(Arrays.toString(row));

	           line = br.readLine();   //lee el siguiente registro
	        }
	     } catch (Exception e) {

	     } finally {
	        if (null!=br) {
	           br.close();
	        }
	     }
	}
	//eliminar comillas
	private static String[] removeTrailingQuotes(String[] fields) {

	    String result[] = new String[fields.length];

	    for (int i=0;i<result.length;i++){
	       result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
	    }
	    return result;
	 }


	//guardar datos desde un archivo de texto en formato csv
	public void guardar() throws IOException{
		FileWriter file;
	    try {
	    	String path = Global.getPath()+"\\Recursos\\dataProducto.csv";
	    	file = new FileWriter(path);
	    	final String NEXT_LINE = "\n";

	    	file.append("Cédula").append(SEPARADOR);
			file.append("Apellido").append(SEPARADOR);
			file.append("Nombre").append(SEPARADOR);
			file.append("Teléfono").append(SEPARADOR);
			file.append("Email").append(SEPARADOR);
			file.append("Ciudad").append(SEPARADOR);
			file.append("Direccion").append(SEPARADOR);
			file.append("Observación").append(NEXT_LINE);  //Next_Line uno solo al final


			//cedula, apellido,  nombre, telefono, email, ciudad, direccion, observacion
	    	for(int i=0; i<Lista.size();i++){
	        	Cliente ob=(Cliente)Lista.get(i);
				file.append(ob.cedula + "").append(SEPARADOR);
				file.append(ob.apellido).append(SEPARADOR);
				file.append(ob.nombre).append(SEPARADOR);
				file.append(ob.telefono + "").append(SEPARADOR);
				file.append(ob.email).append(SEPARADOR);
				file.append(ob.ciudad).append(SEPARADOR);
				file.append(ob.direccion).append(SEPARADOR);
				file.append(ob.observacion).append(NEXT_LINE);

	        }
	    	file.flush();
			file.close();
	     } catch (Exception e) {
	    	 System.out.print(e.getMessage());
	     }
	}
}