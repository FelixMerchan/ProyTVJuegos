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
	private JLabel lbcedula = null;
	private JLabel lbapellido = null;
	private JLabel lbbombre = null;
	private JTextField txtCedula = null;
	private JTextField txtApellidos = null;
	private JTextField txtNombres = null;
	private JLabel Genero = null;
	private JTextField txtDireccion = null;
	private JRadioButton rbMasculino = null;
	private JRadioButton rbFemenino = null;
	private JLabel lbdireccion = null;
	private JLabel lbpadre = null;
	private JTextField txtPadre = null;
	private JLabel lbmadre = null;
	private JTextField txtMadre = null;


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


	/**********************METODOS PERSONALIZADOS ********************/
	/*
	 * Leer datos del formulario y guardar en un objeto
	 */
    public Producto leer()
    {
    	Producto ob=null;
        if(form_validado()){
            ob=new Producto();
            ob.Cedula=txtCedula.getText();
            ob.Apellido=txtApellidos.getText();
            ob.Nombre=txtNombres.getText();
            ob.Direccion=txtDireccion.getText();
            ob.Genero=rbMasculino.isSelected()?"Masculino":"Femenino";
            ob.Madre=txtMadre.getText();
            ob.Padre=txtPadre.getText();
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
        if(!Validaciones.esCedula(txtCedula)){
            ok=false;
            men += ", Cedula";
        }

        if(!Validaciones.esLetras(txtNombres)){
            ok=false;
            men += ", Nombre";
        }

        if(!Validaciones.esLetras(txtApellidos)){
            ok=false;
            men += ", Apellido";
        }

        if(!Validaciones.esRequerido(txtDireccion)){
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
        txtCedula.setText("");
        txtApellidos.setText("");
        txtNombres.setText("");
        txtDireccion.setText("");
        txtMadre.setText("");
        txtPadre.setText("");
        txtCedula.requestFocus();  //envia curso o enfoque a la caja de texto cedula
        frmPrincipal.lbMensaje.setText("");
    }

    /*
     * quitar validaciones
     */

    public void quitar_validaciones(){
    	Validaciones.pinta_text(txtCedula);
    	Validaciones.pinta_text(txtApellidos);
    	Validaciones.pinta_text(txtNombres);
    	Validaciones.pinta_text(txtDireccion);
    	Validaciones.pinta_text(txtMadre);
    	Validaciones.pinta_text(txtPadre);
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
            txtCedula.setText(ob.Cedula);
            txtApellidos.setText(ob.Apellido);
            txtNombres.setText(ob.Nombre);
            txtDireccion.setText(ob.Direccion);
            txtMadre.setText(ob.Madre);
            txtPadre.setText(ob.Padre);
            if(ob.Genero.equals("Femenino"))
            	rbFemenino.setSelected(true);
            else rbMasculino.setSelected(true);
        }
    }

    /*
     * Buscar datos segun cedula
     */
    public void buscar(){
    	try{
            int pos=list.localizar(txtCedula.getText());
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
     * Buscar datos por criterios cedula y apellido
     */
    public void buscar_varios()
    {
        try{
            cProducto p=list.buscar_cedula(txtDato.getText()); //busca por cedula
            if(p.Count()==0)p=list.buscar_apellido(txtDato.getText()); //buscar por apellido
            tabla.setModel(p.getTabla());
        }catch(Exception ex){frmPrincipal.lbMensaje.setText(ex.getMessage());}
    }

    /*
     * Eliminar datos de un Producto
     */
    public void eliminar(){
    	try{
			list.eliminar(txtCedula.getText());
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
    	txtCedula.setEditable(ok);
        txtApellidos.setEditable(ok);
        txtNombres.setEditable(ok);
        txtDireccion.setEditable(ok);
        txtMadre.setEditable(ok);
        txtPadre.setEditable(ok);
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
			lbmadre = new JLabel();
			lbmadre.setBounds(new Rectangle(16, 207, 84, 20));
			lbmadre.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbmadre.setText("Madre:");
			lbpadre = new JLabel();
			lbpadre.setBounds(new Rectangle(16, 174, 84, 25));
			lbpadre.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbpadre.setText("Padre:");
			lbdireccion = new JLabel();
			lbdireccion.setBounds(new Rectangle(16, 142, 84, 25));
			lbdireccion.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbdireccion.setText("Direccción:");
			Genero = new JLabel();
			Genero.setBounds(new Rectangle(17, 113, 81, 25));
			Genero.setFont(new Font("Dialog", Font.PLAIN, 12));
			Genero.setText("Género:");
			lbbombre = new JLabel();
			lbbombre.setBounds(new Rectangle(18, 84, 82, 24));
			lbbombre.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbbombre.setText("Nombre:");
			lbapellido = new JLabel();
			lbapellido.setBounds(new Rectangle(19, 54, 82, 23));
			lbapellido.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbapellido.setText("Apellidos:");
			lbcedula = new JLabel();
			lbcedula.setText("Cédula:");
			lbcedula.setLocation(new Point(20, 27));
			lbcedula.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbcedula.setSize(new Dimension(80, 20));
			paneldatos = new JPanel();
			paneldatos.setLayout(null);
			paneldatos.setBounds(new Rectangle(15, 53, 546, 242));
			paneldatos.setBorder(BorderFactory.createTitledBorder(null, "Datos de Productos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			paneldatos.add(lbcedula, null);
			paneldatos.add(lbapellido, null);
			paneldatos.add(lbbombre, null);
			paneldatos.add(getTxtCedula(), null);
			paneldatos.add(getTxtApellidos(), null);
			paneldatos.add(getTxtNombres(), null);
			paneldatos.add(Genero, null);
			paneldatos.add(getTxtDireccion(), null);

			paneldatos.add(lbdireccion, null);
			paneldatos.add(lbpadre, null);

			paneldatos.add(getRbMasculino(), null);
			paneldatos.add(getRbFemenino(), null);
			ButtonGroup grupoGenero = new ButtonGroup();
			grupoGenero.add(rbMasculino);
			grupoGenero.add(rbFemenino);


			paneldatos.add(getTxtPadre(), null);
			paneldatos.add(lbmadre, null);
			paneldatos.add(getTxtMadre(), null);
			paneldatos.add(getBtBuscar(), null);
		}
		return paneldatos;
	}

	/**
	 * This method initializes txtCedula
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCedula() {
		if (txtCedula == null) {
			txtCedula = new JTextField();
			txtCedula.setLocation(new Point(179, 24));
			txtCedula.setPreferredSize(new Dimension(5, 25));
			txtCedula.setFont(new Font("Dialog", Font.PLAIN, 12));
			txtCedula.setSize(new Dimension(176, 25));
			txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esCedula(txtCedula)) Validaciones.pinta_text(txtCedula);
				}
			});
		}
		return txtCedula;
	}

	/**
	 * This method initializes txtApellidos
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setSize(new Dimension(330, 25));
			txtApellidos.setLocation(new Point(179, 58));
			txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esLetras(txtApellidos)) Validaciones.pinta_text(txtApellidos);
				}
			});
		}
		return txtApellidos;
	}

	/**
	 * This method initializes txtNombres
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNombres() {
		if (txtNombres == null) {
			txtNombres = new JTextField();
			txtNombres.setLocation(new Point(179, 87));
			txtNombres.setSize(new Dimension(330, 25));
			txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esLetras(txtNombres)) Validaciones.pinta_text(txtNombres);
				}
			});
		}
		return txtNombres;
	}

	/**
	 * This method initializes txtDireccion
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDireccion() {
		if (txtDireccion == null) {
			txtDireccion = new JTextField();
			txtDireccion.setLocation(new Point(179, 143));
			txtDireccion.setSize(new Dimension(330, 25));
			txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(Validaciones.esRequerido(txtDireccion)) Validaciones.pinta_text(txtDireccion);
				}
			});
		}
		return txtDireccion;
	}

	/**
	 * This method initializes rbMasculino
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getRbMasculino() {
		if (rbMasculino == null) {
			rbMasculino = new JRadioButton();
			rbMasculino.setBounds(new Rectangle(283, 117, 91, 20));
			rbMasculino.setText("Masculino");
		}
		return rbMasculino;
	}

	/**
	 * This method initializes rbFemenino
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getRbFemenino() {
		if (rbFemenino == null) {
			rbFemenino = new JRadioButton();
			rbFemenino.setText("Femenino");
			rbFemenino.setSelected(true);
			rbFemenino.setBounds(new Rectangle(180, 117, 80, 24));
		}
		return rbFemenino;
	}

	/**
	 * This method initializes txtPadre
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtPadre() {
		if (txtPadre == null) {
			txtPadre = new JTextField();
			txtPadre.setLocation(new Point(179, 174));
			txtPadre.setSize(new Dimension(330, 25));
		}
		return txtPadre;
	}

	/**
	 * This method initializes txtMadre
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtMadre() {
		if (txtMadre == null) {
			txtMadre = new JTextField();
			txtMadre.setLocation(new Point(179, 203));
			txtMadre.setSize(new Dimension(330, 25));
		}
		return txtMadre;
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
			ScrollPane.setBounds(new Rectangle(15, 355, 751, 293));
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
			panelBuscar.setLocation(new Point(15, 303));
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
					if(!txtCedula.getText().trim().equals("")){
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
					if (!txtCedula.getText().trim().equals("")){
						ced=txtCedula.getText().trim(); //captura la cédula antes de modificar
						txtCedula.requestFocus();  //envia curso o enfoque a la caja de texto cedula
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


}  //  @jve:decl-index=0:visual-constraint="10,10"
