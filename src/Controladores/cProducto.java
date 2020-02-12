package Controladores;
/**
 * Controlador de Producto
 * @author Anthony Contreras
 * @date 08/02/2020
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

public class cProducto {
	//arreglo dinámico de objetos. Arraylist es una coleccion predefinida en JAVA
	ArrayList<Producto> Lista = new ArrayList<Producto>();

	//encabezados de columnas de la tabla

	//codigo, nombre, descripcion, cantidad, precio, marca, categoria, iva
	public String[] columnName = {"No.", "Codigo", "Nombre", "Cantidad",
			                      "precio", "Marca", "Categoria", "iva"};

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
	 * Registra un nuevo Producto
	 */
	public void nuevo(Producto e) throws IOException{
		int pos=localizar(e.codigo);
		if(pos==-1){//si codigo no esta registrado, se agrega nuevo Producto
			Lista.add(e);
		}
		else{
			throw new RuntimeException("Codigo ya asignado a otro Producto");
		}
	}

	/**
	 * Modificar datos de un Producto existente
	 */
	public void modificar(Producto e, String cod) throws IOException{
		int pos=localizar(cod);
		if(pos>-1){//si Producto está registrado se modifica
			Lista.set(pos, e);
		}
		else{
			throw new RuntimeException("No existe un Producto registrado con el codigo ingresado");
		}
	}

	/**
	 * Eliminar un Producto
	 */
	public void eliminar(String codigo) throws IOException{
		int pos=localizar(codigo);
		if(pos>-1){//si Producto está registrado se elimina
			Lista.remove(pos);
		}
		else{
			throw new RuntimeException("No existe un Producto registrado con el codigo ingresado");
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
        	Producto e=(Producto)Lista.get(i);
        	Object[] row={
        			//codigo, nombre, descripcion, cantidad, precio, marca, categoria, iva
        			new Integer(i+1), e.codigo, e.nombre, e.cantidad, e.precio,
        			e.marca, e.categoria, e.iva
        			};
            tabla.addRow(row);
        }
        return tabla;
    }

	/**
	 * Algoritmo de busqueda secuencial en el arreglo Lista
	 * @param codigo
	 * @return posicion en el arreglo del Producto encontrado
	 */
	public int localizar(String codigo){//int codigo
		int pos=-1; //se retorna -1 si no se encuentra en el arreglo
		for(int i=0; i<Lista.size(); i++){
			Producto e=(Producto)Lista.get(i);
			if(codigo.equals(e.codigo)){ //codigo==e.codigo
				pos=i; //posicion encontrada
				break; //finaliza el ciclo for
			}
		}
		return pos;
	}

	/**
	 * Algoritmo de busqueda secuencial mediante criterio de codigo parcial en el arreglo Lista
	 * @param codigo
	 * @return cProducto
	 * @throws IOException
	 */
	public cProducto buscar_codigo(String codigo) throws IOException{
		cProducto ob=new cProducto();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Producto e=(Producto)Lista.get(i);
			if(e.codigo.length() >= codigo.length()){
				cad=e.codigo.substring(0, codigo.length());
				if(cad.equalsIgnoreCase(codigo)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}

	/**
	 * Algoritmo de busqueda secuencial mediante criterio nombre del producto parcial en el arreglo Lista
	 * @param nombre del producto
	 * @return cProducto
	 * @throws IOException
	 */
	public cProducto buscar_nombre(String nombre) throws IOException{
		cProducto ob=new cProducto();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Producto e=(Producto)Lista.get(i);
			if(e.nombre.length() >= nombre.length()){
				cad=e.nombre.substring(0, nombre.length());
				if(cad.equalsIgnoreCase(nombre)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}
	//implementar mas metodos de busqueda por otros parametros
	/**
	 * Algoritmo de busqueda secuencial mediante criterio marca del producto parcial en el arreglo Lista
	 * @param marca del producto
	 * @return cProducto
	 * @throws IOException
	 */
	public cProducto buscar_marca(String marca) throws IOException{
		cProducto ob=new cProducto();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Producto e=(Producto)Lista.get(i);
			if(e.marca.length() >= marca.length()){
				cad=e.marca.substring(0, marca.length());
				if(cad.equalsIgnoreCase(marca)){
					ob.nuevo(e);
				}
			}
		}
		return ob;
	}
	public cProducto buscar_categoria(String categoria) throws IOException{
		cProducto ob=new cProducto();
		for(int i=0; i<Lista.size(); i++){
			String cad="";
			Producto e=(Producto)Lista.get(i);
			if(e.marca.length() >= categoria.length()){
				cad=e.marca.substring(0, categoria.length());
				if(cad.equalsIgnoreCase(categoria)){
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
	public Producto getProducto(int pos){
		Producto ob=null;
		if(pos>=0 && pos<Lista.size()){
			ob=Lista.get(pos);
		}
		return ob;
	}

	// Leer datos desde un archivo de texto en formato csv

	public static final String SEPARADOR=";";
    public static final String QUOTE="\"";

	public void leer() throws IOException{
		BufferedReader br = null;
	    try {
	    	String path = Global.getPath()+"\\Recursos\\dataProductos.csv";
	    	br =new BufferedReader(new FileReader(path));
	        String line = br.readLine(); //lee una linea de texto del archivo
	        Clear(); //limpiar lista de objetos
	        line = br.readLine(); // linea del encabezado
	        while (line!=null) {
	           String [] row = line.split(SEPARADOR);
	           removeTrailingQuotes(row);
	         //codigo, nombre, descripcion, cantidad, precio, marca, categoria, iva
	           Producto ob=new Producto();
	           ob.codigo=row[0];
	           ob.nombre=row[1];
	           ob.descripcion=row[2];
	           ob.cantidad=Double.parseDouble(row[3]);
	           ob.precio=Double.parseDouble(row[4]);
	           ob.marca=row[5];
	           ob.categoria=row[6];
	           ob.iva=Integer.parseInt(row[7]);
	           nuevo(ob);//agregar a la lista

	           //System.out.println(ob.nombre);
	           System.out.println(Arrays.toString(row));

	           line = br.readLine(); //leer el siguiente registro
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

	// guardar datos de un archivo de texto
	public void guardar() throws IOException{
		FileWriter file;
	    try {
	    	String path = Global.getPath()+"\\Recursos\\dataProductos.csv";
	    	file = new FileWriter(path);
	    	final String NEXT_LINE = "\n";

	    	file.append("Codigo").append(SEPARADOR);
			file.append("Nombre").append(SEPARADOR);
			file.append("Descripcion").append(SEPARADOR);
			file.append("Cantigaf").append(SEPARADOR);
			file.append("Precio").append(SEPARADOR);
			file.append("Marca").append(SEPARADOR);
			file.append("Categoria").append(SEPARADOR);
			file.append("Iva").append(NEXT_LINE); //Next_Line uno solo al final

			 //codigo, nombre, descripcion, cantidad, precio, marca, categoria, iva
			for(int i=0; i<Lista.size();i++){
	        	Producto ob=(Producto)Lista.get(i);
				file.append(ob.codigo).append(SEPARADOR);
				file.append(ob.nombre).append(SEPARADOR);
				file.append(ob.descripcion).append(SEPARADOR);
				file.append(ob.cantidad + "").append(SEPARADOR);
				file.append(ob.precio + "").append(SEPARADOR);
				file.append(ob.marca).append(SEPARADOR);
				file.append(ob.categoria).append(SEPARADOR);
				file.append(ob.iva + "").append(NEXT_LINE);

	        }
	    	file.flush();
			file.close();
	     } catch (Exception e) {
	    	 System.out.print(e.getMessage());
	     }
	}
}