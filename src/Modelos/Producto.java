package Modelos;
/**
 * Modelo de la entidad Producto
 * @author Anthony Contreras
 * @date 08/02/2020
 * @version 1.1
 */
public class Producto {

	public String codigo;
	public String nombre;
	public String descripcion;
	public double cantidad;
	public double precio;
	public String marca;
	public String categoria;
	public int iva;

	public Producto(String codigo, String nombre, String descripcion, double cantidad,
			        double precio, String marca, String categoria, int iva)
	{
		this.codigo=codigo;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.cantidad=cantidad;
		this.precio=precio;
		this.marca=marca;
		this.categoria=categoria;
		this.iva=iva;
	}

}
