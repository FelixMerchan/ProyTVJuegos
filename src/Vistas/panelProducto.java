package Vistas;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

import Controladores.cProducto;
import Modelos.Producto;

import java.awt.Point;
import java.io.IOException;

import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class panelProducto extends JPanel {
	//datos globales
	public cProducto list=new cProducto();  //  @jve:decl-index=0:
	String ced=""; //para editar  //  @jve:decl-index=0:

	private static final long serialVersionUID = 1L;
	private JButton btGuardar = null;
	private JPanel paneldatos = null;
	private JLabel lbCodigo = null;
	private JLabel lbNombre = null;
	private JLabel lbDescripcion = null;
	private JTextField txtCodigo = null;
	private JTextField txtNombre = null;
	private JTextField txtDescripcion = null;
	private JLabel lbMarca = null;
	private JTextField txtMarca = null;
	private JLabel lbCategoria = null;
	private JLabel lbCantidad = null;
	private JTextField txtCategoria = null;
	private JTextField txtCantidad = null;


	private JButton btNuevo = null;
	private JButton btBuscar = null;
	private JLabel lbTitulo = null;
	private JScrollPane ScrollPane = null;
	private JTable tabla = null;
	private JPanel panelBuscar = null;
	private JLabel lbBuscar = null;
	private JTextField txtDato = null;
	private JButton btListar = null;
	private JPanel panelBotones = null;
	private JButton btEliminar = null;
	private JButton btEditar = null;
	private JButton btCancelar = null;
	private JLabel lbPrecio = null;
	private JTextField txtPrecio = null;
	private JLabel lbIva = null;
	private JTextField txtIva = null;


	/**********************METODOS PERSONALIZADOS ********************/
	/*
	 * Leer datos del formulario y guardar en un objeto
	 */
    public Producto leer()
    {
    	Producto ob=null;
        if(form_validado()){
            ob=new Producto();
            ob.codigo=txtCodigo.getText();
            ob.nombre=txtNombre.getText();
            ob.descripcion=txtDescripcion.getText();
            ob.marca=txtMarca.getText();
            ob.cantidad=txtCantidad.getText();
            ob.categoria=txtCategoria.getText();
            System.out.print(ob.toString());
        }
        return ob;
    }

    /*
     * Validar formulario
     */
    public boolean form_validado(){
        boolean ok=true;
        String men="Campos con errores";
        //validar requerido
        if(!Validaciones.esCedula(txtCodigo)){
            ok=false;
            men += ", Cedula";
        }

        if(!Validaciones.esLetras(txtDescripcion)){
            ok=false;
            men += ", Nombre";
        }

        if(!Validaciones.esLetras(txtNombre)){
            ok=false;
            men += ", Apellido";
        }

        if(!Validaciones.esRequerido(txtMarca)){
            ok=false;
            men += ", Dirección";
        }

        if(!ok)frmPrincipal.lbMensaje.setText(men);
        else frmPrincipal.lbMensaje.setText("");
        //validar más controles
        return ok;
    }

    /*
     * Metodo para limpiar cajas de texto
     */
    public void limpiar_textos()
    {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtMarca.setText("");
        txtCantidad.setText("");
        txtCategoria.setText("");
        txtCodigo.requestFocus();  //envia curso o enfoque a la caja de texto cedula
        frmPrincipal.lbMensaje.setText("");
    }

    /*
     * quitar validaciones
     */

    public void quitar_validaciones(){
    	Validaciones.pinta_text(txtCodigo);
    	Validaciones.pinta_text(txtNombre);
    	Validaciones.pinta_text(txtDescripcion);
    	Validaciones.pinta_text(txtMarca);
    	Validaciones.pinta_text(txtCantidad);
    	Validaciones.pinta_text(txtCategoria);
    	frmPrincipal.lbMensaje.setText("");
    }

    /*
     * Ver registro
     */
    public void ver_registro(int pos)
    {
        if(pos>=0 && pos<list.Count())
        {
            Producto ob=list.getProducto(pos);
            txtCodigo.setText(ob.codigo);
            txtNombre.setText(ob.nombre);
            txtDescripcion.setText(ob.descripcion);
            txtMarca.setText(ob.marca);
            txtCantidad.setText(ob.cantidad);
            txtCategoria.setText(ob.categoria);

        }
    }

    /*
     * Buscar datos segun cedula
     */
    public void buscar(){
    	try{
            int pos=list.localizar(txtCodigo.getText());
            if (pos>-1)
            {
                ver_registro(pos);
            }
            else frmPrincipal.lbMensaje.setText("Registro no encontrado");
        }catch(Exception ex){
        	frmPrincipal.lbMensaje.setText(ex.getMessage());
        }
    }

    /*
     * Guardar Producto, cuando es nuevo o se modifica un existente
     */
    public void guardar(){
    	Producto ob=leer();
		try{
			if(ob!=null){
				if (ced.equals("")){//guardar un nuevo Producto
					list.nuevo(ob);
				}
				else{//guardar datos de Producto editado
					list.modificar(ob,ced);
				}
				frmPrincipal.lbMensaje.setText("Registro guardado exitosamente");
				list.guardar(); //guarda en el archivo csv
				tabla.setModel(list.getTabla());
				//deshabilitar textos
		        habilitar_textos(false);
		        //habilitar botones
		        habilitar_botones(true);
			}
		}catch(Exception ex){
			frmPrincipal.lbMensaje.setText(ex.getMessage());
			System.out.println(ex.getMessage());
		}
    }

    /*
     * Buscar datos por criterios codigo y nombre
     */
    public void buscar_varios()
    {
        try{
            cProducto p=list.buscar_codigo(txtDato.getText()); //busca por codigo
            if(p.Count()==0)p=list.buscar_nombre(txtDato.getText()); //buscar por nombre
            tabla.setModel(p.getTabla());
        }catch(Exception ex){frmPrincipal.lbMensaje.setText(ex.getMessage());}
    }

    /*
     * Eliminar datos de un Producto
     */
    public void eliminar(){
    	try{
			list.eliminar(txtCodigo.getText());
			list.guardar();
			tabla.setModel(list.getTabla());
			frmPrincipal.lbMensaje.setText("Registro eliminado");
        }catch(Exception ex){frmPrincipal.lbMensaje.setText(ex.getMessage());}
    }


    /*
     * Habilitar o desabilitar textos
     */
    public void habilitar_textos(Boolean ok)
    {
    	txtCodigo.setEditable(ok);
        txtNombre.setEditable(ok);
        txtDescripcion.setEditable(ok);
        txtMarca.setEditable(ok);
        txtCantidad.setEditable(ok);
        txtCategoria.setEditable(ok);
    }

    /*
     * Habilitar o desabilitar botones
     */

    public void habilitar_botones(Boolean ok)
    {
        btNuevo.setEnabled(ok);
        btEditar.setEnabled(ok);
        //btBuscar.setEnabled(ok);
        btEliminar.setEnabled(ok);

        //hacen lo contrario de los otros botones
        btGuardar.setEnabled(!ok);
        btCancelar.setEnabled(!ok);
    }


    /**********************METODOS AUTOGENERADOS******************/

	/**
	 * This is the default constructor
	 * @throws IOException
	 */
	public panelProducto() throws IOException {
		super();
		initialize();
		list.leer();
		//formato de la tabla
		tabla.getTableHeader().setBackground(new Color(100, 200, 200));
        tabla.getTableHeader().setForeground(Color.BLACK);

        //desabilitar textos
        habilitar_textos(false);
        //habilitar botones
        habilitar_botones(true);

	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		lbTitulo = new JLabel();
		lbTitulo.setBounds(new Rectangle(14, 15, 748, 35));
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 14));
		lbTitulo.setForeground(Color.blue);
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
		lbTitulo.setText("Gestión de Productos");
		lbTitulo.setBackground(Color.white);
		this.setSize(785, 661);
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setOpaque(true);
		this.add(getPaneldatos(), null);
		this.add(lbTitulo, null);
		this.add(getScrollPane(), null);
		this.add(getPanelBuscar(), null);
		this.add(getPanelBotones(), null);
	}

	/**
	 * This method initializes btGuardar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtGuardar() {
		if (btGuardar == null) {
			btGuardar = new JButton();
			btGuardar.setIcon(new ImageIcon(getClass().getResource("/Recursos/save.png")));
			btGuardar.setLocation(new Point(39, 152));
			btGuardar.setSize(new Dimension(120, 40));
			btGuardar.setText("Guardar");
			btGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e){
					guardar();

				}
			});
		}
		return btGuardar;
	}

	/**
	 * This method initializes paneldatos
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPaneldatos() {
		if (paneldatos == null) {
			lbIva = new JLabel();
			lbIva.setBounds(new Rectangle(329, 114, 58, 24));
			lbIva.setText("Iva:");
			lbPrecio = new JLabel();
			lbPrecio.setBounds(new Rectangle(287, 173, 118, 26));
			lbPrecio.setText("Precio de Venta:");
			lbCantidad = new JLabel();
			lbCantidad.setBounds(new Rectangle(16, 174, 109, 25));
			lbCantidad.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbCantidad.setText("Cantidad en Stock");
			lbCategoria = new JLabel();
			lbCategoria.setBounds(new Rectangle(16, 142, 84, 25));
			lbCategoria.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbCategoria.setText("Categoria");
			lbMarca = new JLabel();
			lbMarca.setBounds(new Rectangle(17, 113, 81, 25));
			lbMarca.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbMarca.setText("Marca:");
			lbDescripcion = new JLabel();
			lbDescripcion.setBounds(new Rectangle(18, 84, 82, 24));
			lbDescripcion.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbDescripcion.setText("Descripcion:");
			lbNombre = new JLabel();
			lbNombre.setBounds(new Rectangle(19, 54, 82, 23));
			lbNombre.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbNombre.setText("Nombre:");
			lbCodigo = new JLabel();
			lbCodigo.setText("Código:");
			lbCodigo.setLocation(new Point(20, 27));
			lbCodigo.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbCodigo.setSize(new Dimension(80, 20));
			paneldatos = new JPanel();
			paneldatos.setLayout(null);
			paneldatos.setBounds(new Rectangle(19, 62, 546, 219));
			paneldatos.setBorder(BorderFactory.createTitledBorder(null, "Datos de Productos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			paneldatos.add(lbCodigo, null);
			paneldatos.add(lbNombre, null);
			paneldatos.add(lbDescripcion, null);
			paneldatos.add(getTxtCodigo(), null);
			paneldatos.add(getTxtNombre(), null);
			paneldatos.add(getTxtDescripcion(), null);
			paneldatos.add(lbMarca, null);
			paneldatos.add(getTxtMarca(), null);

			paneldatos.add(lbCategoria, null);
			paneldatos.add(lbCantidad, null);




			paneldatos.add(getTxtCategoria(), null);
			paneldatos.add(getTxtCantidad(), null);
			paneldatos.add(getBtBuscar(), null);
			paneldatos.add(lbPrecio, null);
			paneldatos.add(getTxtPrecio(), null);
			paneldatos.add(lbIva, null);
			paneldatos.add(getTxtIva(), null);
		}
		return paneldatos;
	}

	/**
	 * This method initializes txtCodigo
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCodigo() {
		if (txtCodigo == null) {
			txtCodigo = new JTextField();
			txtCodigo.setLocation(new Point(179, 25));
			txtCodigo.setPreferredSize(new Dimension(5, 25));
			txtCodigo.setFont(new Font("Dialog", Font.PLAIN, 12));
			txtCodigo.setSize(new Dimension(176, 25));
			txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esCedula(txtCodigo)) Validaciones.pinta_text(txtCodigo);
				}
			});
		}
		return txtCodigo;
	}

	/**
	 * This method initializes txtNombre
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setSize(new Dimension(330, 25));
			txtNombre.setLocation(new Point(179, 54));
			txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esLetras(txtNombre)) Validaciones.pinta_text(txtNombre);
				}
			});
		}
		return txtNombre;
	}

	/**
	 * This method initializes txtDescripcion
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDescripcion() {
		if (txtDescripcion == null) {
			txtDescripcion = new JTextField();
			txtDescripcion.setLocation(new Point(179, 82));
			txtDescripcion.setSize(new Dimension(330, 25));
			txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esLetras(txtDescripcion)) Validaciones.pinta_text(txtDescripcion);
				}
			});
		}
		return txtDescripcion;
	}

	/**
	 * This method initializes txtMarca
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtMarca() {
		if (txtMarca == null) {
			txtMarca = new JTextField();
			txtMarca.setLocation(new Point(179, 114));
			txtMarca.setSize(new Dimension(137, 25));
			txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esRequerido(txtMarca)) Validaciones.pinta_text(txtMarca);
				}
			});
		}
		return txtMarca;
	}

	/**
	 * This method initializes txtCategoria
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCategoria() {
		if (txtCategoria == null) {
			txtCategoria = new JTextField();
			txtCategoria.setLocation(new Point(179, 142));
			txtCategoria.setSize(new Dimension(133, 25));
		}
		return txtCategoria;
	}

	/**
	 * This method initializes txtCantidad
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCantidad() {
		if (txtCantidad == null) {
			txtCantidad = new JTextField();
			txtCantidad.setLocation(new Point(179, 172));
			txtCantidad.setSize(new Dimension(97, 25));
		}
		return txtCantidad;
	}

	/**
	 * This method initializes btNuevo
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtNuevo() {
		if (btNuevo == null) {
			btNuevo = new JButton();
			btNuevo.setIcon(new ImageIcon(getClass().getResource("/Recursos/nuevo.png")));
			btNuevo.setHorizontalAlignment(SwingConstants.LEFT);
			btNuevo.setHorizontalTextPosition(SwingConstants.RIGHT);
			btNuevo.setLocation(new Point(39, 11));
			btNuevo.setSize(new Dimension(120, 40));
			btNuevo.setText("Nuevo");
			btNuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					quitar_validaciones();
					limpiar_textos();
					ced=""; //nuevo
					//habilitar textos
			        habilitar_textos(true);
			        //deshabilitar botones
			        habilitar_botones(false);

				}
			});
		}
		return btNuevo;
	}

	/**
	 * This method initializes btBuscar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtBuscar() {
		if (btBuscar == null) {
			btBuscar = new JButton();
			btBuscar.setText("Buscar");
			btBuscar.setBounds(new Rectangle(365, 13, 114, 40));
			btBuscar.setIcon(new ImageIcon(getClass().getResource("/Recursos/buscar.png")));
			btBuscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					buscar();
				}
			});
		}
		return btBuscar;
	}

	/**
	 * This method initializes ScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getScrollPane() {
		if (ScrollPane == null) {
			ScrollPane = new JScrollPane();
			ScrollPane.setBounds(new Rectangle(15, 403, 751, 245));
			ScrollPane.setViewportView(getTabla());
		}
		return ScrollPane;
	}

	/**
	 * This method initializes tabla
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTabla() {
		if (tabla == null) {
			tabla = new JTable();
			tabla.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int pos= tabla.getSelectedRow();
				    if(pos>=0 && pos <list.Count())
				       ver_registro(pos);
				}
			});
			tabla.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					int pos= tabla.getSelectedRow();
				    if(pos>=0 && pos <list.Count())
				       ver_registro(pos);
				}
			});
		}
		return tabla;
	}

	/**
	 * This method initializes panelBuscar
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelBuscar() {
		if (panelBuscar == null) {
			lbBuscar = new JLabel();
			lbBuscar.setBounds(new Rectangle(12, 12, 124, 27));
			lbBuscar.setText("Dato a Buscar:");
			panelBuscar = new JPanel();
			panelBuscar.setLayout(null);
			panelBuscar.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panelBuscar.setBackground(SystemColor.control);
			panelBuscar.setSize(new Dimension(546, 47));
			panelBuscar.setLocation(new Point(19, 304));
			panelBuscar.add(lbBuscar, null);
			panelBuscar.add(getTxtDato(), null);
		}
		return panelBuscar;
	}

	/**
	 * This method initializes txtDato
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDato() {
		if (txtDato == null) {
			txtDato = new JTextField();
			txtDato.setBounds(new Rectangle(145, 13, 361, 26));
			txtDato.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					buscar_varios();
				}
			});
		}
		return txtDato;
	}

	/**
	 * This method initializes btListar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtListar() {
		if (btListar == null) {
			btListar = new JButton();
			btListar.setText("Listar");
			btListar.setLocation(new Point(39, 246));
			btListar.setSize(new Dimension(120, 40));
			btListar.setIcon(new ImageIcon(getClass().getResource("/Recursos/tabla.png")));
			btListar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					tabla.setModel(panelProducto.this.list.getTabla());
					tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
					frmPrincipal.lbMensaje.setText("");
				}
			});
		}
		return btListar;
	}

	/**
	 * This method initializes panelBotones
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(null);
			panelBotones.setBounds(new Rectangle(570, 55, 193, 295));
			panelBotones.add(getBtNuevo(), null);
			panelBotones.add(getBtGuardar(), null);
			panelBotones.add(getBtListar(), null);
			panelBotones.add(getBtEliminar(), null);
			panelBotones.add(getBtEditar(), null);
			panelBotones.add(getBtCancelar(), null);
		}
		return panelBotones;
	}

	/**
	 * This method initializes btEliminar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton();
			btEliminar.setText("Eliminar");
			btEliminar.setSize(new Dimension(120, 40));
			btEliminar.setLocation(new Point(39, 199));
			btEliminar.setIcon(new ImageIcon(getClass().getResource("/Recursos/eliminar.png")));
			btEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!txtCodigo.getText().trim().equals("")){
					    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminiar este registro?","Sistema Académico", JOptionPane.YES_NO_OPTION);
					    if (respuesta == JOptionPane.YES_OPTION) {
					    	eliminar();
					    }
					}
					else frmPrincipal.lbMensaje.setText("Seleccione el registro a eliminar");
				}
			});
		}
		return btEliminar;
	}

	/**
	 * This method initializes btEditar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtEditar() {
		if (btEditar == null) {
			btEditar = new JButton();
			btEditar.setHorizontalTextPosition(SwingConstants.RIGHT);
			btEditar.setIcon(new ImageIcon(getClass().getResource("/Recursos/modificar.png")));
			btEditar.setText("Editar");
			btEditar.setLocation(new Point(39, 58));
			btEditar.setSize(new Dimension(120, 40));
			btEditar.setHorizontalAlignment(SwingConstants.LEFT);
			btEditar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (!txtCodigo.getText().trim().equals("")){
						ced=txtCodigo.getText().trim(); //captura la cédula antes de modificar
						txtCodigo.requestFocus();  //envia curso o enfoque a la caja de texto cedula
				        frmPrincipal.lbMensaje.setText("");
						//habilitar textos
				        habilitar_textos(true);
				        //deshabilitar botones
				        habilitar_botones(false);
				        quitar_validaciones();
					}
					else frmPrincipal.lbMensaje.setText("Seleccione o busque un registro a editar");
				}
			});
		}
		return btEditar;
	}

	/**
	 * This method initializes btCancelar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton();
			btCancelar.setHorizontalTextPosition(SwingConstants.RIGHT);
			btCancelar.setIcon(new ImageIcon(getClass().getResource("/Recursos/undo.png")));
			btCancelar.setText("Cancelar");
			btCancelar.setLocation(new Point(39, 105));
			btCancelar.setSize(new Dimension(120, 40));
			btCancelar.setHorizontalAlignment(SwingConstants.LEFT);
			btCancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//quitar validaciones
					quitar_validaciones();
					//desabilitar textos
			        habilitar_textos(false);
			        //habilitar botones
			        habilitar_botones(true);
			        //cargar registro anterior a la modificación
			        int pos=list.localizar(ced);
			        if(pos>=0)ver_registro(pos);
			        else limpiar_textos();
				}
			});
		}
		return btCancelar;
	}

	/**
	 * This method initializes txtPrecio
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtPrecio() {
		if (txtPrecio == null) {
			txtPrecio = new JTextField();
			txtPrecio.setSize(new Dimension(115, 26));
			txtPrecio.setLocation(new Point(418, 175));
		}
		return txtPrecio;
	}

	/**
	 * This method initializes txtIva
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtIva() {
		if (txtIva == null) {
			txtIva = new JTextField();
			txtIva.setSize(new Dimension(114, 28));
			txtIva.setLocation(new Point(418, 113));
		}
		return txtIva;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
