package Modelos;
/**
 * Modelo de la entidad Cliente
 * @author Kevin Zhune
 * @date 08/02/2020
 * @version 1.1
 */
public class Cliente {

	//cedula, apellido,  nombre, telefono, email, ciudad, direccion, obseracion
	//datos globales

	public int cedula;
	public String apellido;
	public String nombre;
	public int telefono;
	public String email;
	public String ciudad;
	public String direccion;
	public String observacion;

	//construcor al vacio
	public Cliente() {}

	//constructor comun
	public Cliente(int cedula, String apellido, String nombre, int telefono,
			        String email, String ciudad, String direccion, String observacion)
	{
		this.cedula=cedula;
		this.apellido=apellido;
		this.nombre=nombre;
		this.telefono=telefono;
		this.email=email;
		this.ciudad=ciudad;
		this.direccion=direccion;
		this.observacion=observacion;
	}

}