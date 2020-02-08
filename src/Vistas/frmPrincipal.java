package Vistas;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;


import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.IOException;

public class frmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelMenu = null;
	private JPanel panelCentral = null;
	private JButton btCliente = null;
	private JButton btProducto = null;
	private JLabel lbFondo = null;
	private JLabel lbimg_repre = null;
	private JLabel lbimg_estudiante = null;
	private JLabel lbimg_matri = null;
	private JButton btFactura = null;
	private JButton btHome = null;
	private JPanel panelPie = null;
	private JLabel lbPie = null;
	public static JLabel lbMensaje = null;
	private JButton btSalir = null;
	private JLabel lbimg_salir = null;
	private JLabel lbimg_curso = null;
	private JButton btVendedor = null;


	/**
	 * This is the default constructor
	 */
	public frmPrincipal() {
		super();
		initialize();
		lbFondo.setIcon(new ImageIcon(getClass().getResource("/Recursos/fondo4.jpg")));
		//this.setLocationRelativeTo(null);
		this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH); //maximizar

	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(749, 578);
		this.setFont(new Font("Dialog", Font.BOLD, 14));
		this.setPreferredSize(new Dimension(1200, 900));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Recursos/icono app.png")));
		this.setResizable(true);
		this.setContentPane(getJContentPane());
		this.setTitle("Sistema Tienda Video Juegos");
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelMenu(), BorderLayout.WEST);
			jContentPane.add(getPanelCentral(), BorderLayout.CENTER);
			jContentPane.add(getPanelPie(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelMenu
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelMenu() {
		if (panelMenu == null) {
			lbimg_curso = new JLabel();
			lbimg_curso.setBounds(new Rectangle(2, 313, 62, 65));
			lbimg_curso.setText("");
			lbimg_curso.setIcon(new ImageIcon(getClass().getResource("/Recursos/vendedor.png")));
			lbimg_salir = new JLabel();
			lbimg_salir.setBounds(new Rectangle(4, 393, 64, 56));
			lbimg_salir.setText("");
			lbimg_salir.setIcon(new ImageIcon(getClass().getResource("/Recursos/close.png")));
			lbimg_matri = new JLabel();
			lbimg_matri.setText("");
			lbimg_matri.setLocation(new Point(1, 244));
			lbimg_matri.setSize(new Dimension(64, 65));
			lbimg_matri.setIcon(new ImageIcon(getClass().getResource("/Recursos/factura11.png")));
			lbimg_estudiante = new JLabel();
			lbimg_estudiante.setText("");
			lbimg_estudiante.setSize(new Dimension(66, 68));
			lbimg_estudiante.setLocation(new Point(2, 102));
			lbimg_estudiante.setIcon(new ImageIcon(getClass().getResource("/Recursos/cliente.png")));
			lbimg_repre = new JLabel();
			lbimg_repre.setText("");
			lbimg_repre.setSize(new Dimension(64, 65));
			lbimg_repre.setLocation(new Point(1, 176));
			lbimg_repre.setIcon(new ImageIcon(getClass().getResource("/Recursos/gamepad11.png")));
			panelMenu = new JPanel();
			panelMenu.setLayout(null);
			panelMenu.setPreferredSize(new Dimension(225, 0));
			panelMenu.setBackground(new Color(5, 151, 228));
			panelMenu.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

			panelMenu.add(lbimg_estudiante, null);
			panelMenu.add(lbimg_repre, null);
			panelMenu.add(getBtCliente(), null);
			panelMenu.add(getBtProducto(), null);

			panelMenu.add(lbimg_matri, null);
			panelMenu.add(getBtFactura(), null);
			panelMenu.add(getBtHome(), null);
			panelMenu.add(lbimg_salir, null);
			panelMenu.add(getBtSalir(), null);

			panelMenu.add(lbimg_curso, null);
			panelMenu.add(getBtVendedor(), null);
		}
		return panelMenu;
	}

	/**
	 * This method initializes panelCentral
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelCentral() {
		if (panelCentral == null) {
			lbFondo = new JLabel();
			lbFondo.setText("");
			lbFondo.setBackground(new Color(204, 255, 255));
			lbFondo.setName("lbFondo");
			panelCentral = new JPanel();
			panelCentral.setLayout(new CardLayout());
			panelCentral.setBackground(Color.white);
			panelCentral.add(lbFondo, lbFondo.getName());
		}
		return panelCentral;
	}

	/**
	 * This method initializes btCliente
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtCliente() {
		if (btCliente == null) {
			btCliente = new JButton();
			btCliente.setIcon(new ImageIcon(getClass().getResource("/Recursos/boton-azul.png")));
			btCliente.setBackground(new Color(0, 51, 153));
			btCliente.setSize(new Dimension(214, 64));
			btCliente.setLocation(new Point(21, 108));
			btCliente.setPreferredSize(new Dimension(0, 0));
			btCliente.setForeground(Color.white);
			btCliente.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btCliente.setText("Cliente");
			//quitar borde
			btCliente.setBorder(null);
			//quitar recuadro de boton seleccionado
			btCliente.setFocusable(false);
			//quitar recuadro al hacer clik
			btCliente.setContentAreaFilled(false);

			btCliente.setHorizontalTextPosition(SwingConstants.CENTER);
			btCliente.setRolloverIcon(new ImageIcon(getClass().getResource("/Recursos/boton-naranja.png")));

			btCliente.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					panelCentral.removeAll();
					try{
					//panelCentral.add(new panelEstudiante(),"Estudiante");
					}catch(Exception ex){}
					panelCentral.repaint();
				}
			});
		}
		return btCliente;
	}

	/**
	 * This method initializes btProducto
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtProducto() {
		if (btProducto == null) {
			btProducto = new JButton();
			btProducto.setIcon(new ImageIcon(getClass().getResource("/Recursos/boton-azul.png")));
			btProducto.setText("Producto");
			btProducto.setSize(new Dimension(214, 64));
			btProducto.setLocation(new Point(20, 177));
			btProducto.setForeground(Color.white);
			btProducto.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btProducto.setHorizontalTextPosition(SwingConstants.CENTER);
			btProducto.setHorizontalAlignment(SwingConstants.CENTER);
			btProducto.setRolloverEnabled(true);
			btProducto.setRolloverIcon(new ImageIcon(getClass().getResource("/Recursos/boton-naranja.png")));
			btProducto.setBackground(new Color(0, 51, 153));
			btProducto.setBorder(null);
			btProducto.setActionCommand("Producto");
			btProducto.setFocusable(false);
			//quitar recuadro al hacer clik
			btProducto.setContentAreaFilled(false);
			btProducto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					panelCentral.removeAll();
					//panelCentral.add(new panelRepresentante(),"Representante");
					panelCentral.repaint();
				}
			});
		}
		return btProducto;
	}

	/**
	 * This method initializes btFactura
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtFactura() {
		if (btFactura == null) {
			btFactura = new JButton();
			btFactura.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btFactura.setForeground(Color.white);
			btFactura.setHorizontalAlignment(SwingConstants.CENTER);
			btFactura.setHorizontalTextPosition(SwingConstants.CENTER);
			btFactura.setIcon(new ImageIcon(getClass().getResource("/Recursos/boton-azul.png")));
			btFactura.setRolloverEnabled(true);
			btFactura.setRolloverIcon(new ImageIcon(getClass().getResource("/Recursos/boton-naranja.png")));
			btFactura.setText("Factura");
			btFactura.setLocation(new Point(20, 245));
			btFactura.setSize(new Dimension(214, 64));
			btFactura.setBackground(new Color(0, 51, 153));
			//quitar borde
			btFactura.setBorder(null);
			//quitar recuadro de boton seleccionado
			btFactura.setFocusable(false);
			//quitar recuadro al hacer clik
			btFactura.setContentAreaFilled(false);
			btFactura.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					panelCentral.removeAll();
					//panelCentral.add(new panelMatricula(),"Matricula");
					panelCentral.repaint();
				}
			});
		}
		return btFactura;
	}

	/**
	 * This method initializes btHome
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtHome() {
		if (btHome == null) {
			btHome = new JButton();
			btHome.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btHome.setForeground(Color.white);

			btHome.setPreferredSize(new Dimension(0, 0));
			btHome.setContentAreaFilled(false);
			btHome.setHorizontalTextPosition(SwingConstants.CENTER);
			btHome.setIcon(new ImageIcon(getClass().getResource("/Recursos/Tienda22.png")));
			btHome.setText("");
			btHome.setLocation(new Point(56, 9));
			btHome.setSize(new Dimension(100, 100));
			btHome.setBackground(new Color(0, 51, 153));
			//quitar borde
			btHome.setBorder(null);
			//quitar recuadro de boton seleccionado
			btHome.setFocusable(false);
			//quitar recuadro al hacer clik
			btHome.setContentAreaFilled(false);

			btHome.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					panelCentral.removeAll();
					lbFondo.setIcon(new ImageIcon(getClass().getResource("/Recursos/fondo4.jpg")));
					panelCentral.add(lbFondo, lbFondo.getName());
					panelCentral.repaint();

				}
			});
		}
		return btHome;
	}

	/**
	 * This method initializes panelPie
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelPie() {
		if (panelPie == null) {
			lbMensaje = new JLabel();
			lbMensaje.setForeground(Color.red);
			lbMensaje.setLocation(new Point(238, -1));
			lbMensaje.setSize(new Dimension(539, 41));
			lbMensaje.setText("");
			lbPie = new JLabel();
			lbPie.setText("2020 BlueSoft Derechos reservados");
			lbPie.setSize(new Dimension(205, 40));
			lbPie.setLocation(new Point(8, -1));
			panelPie = new JPanel();
			panelPie.setLayout(null);
			panelPie.setPreferredSize(new Dimension(0, 40));
			panelPie.add(lbPie, null);
			panelPie.add(lbMensaje, null);
		}
		return panelPie;
	}

	/**
	 * This method initializes btSalir
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtSalir() {
		if (btSalir == null) {
			btSalir = new JButton();
			btSalir.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btSalir.setForeground(Color.white);
			btSalir.setBorder(null);
			btSalir.setContentAreaFilled(false);
			btSalir.setHorizontalAlignment(SwingConstants.CENTER);
			btSalir.setHorizontalTextPosition(SwingConstants.CENTER);
			btSalir.setIcon(new ImageIcon(getClass().getResource("/Recursos/boton-azul.png")));
			btSalir.setRolloverEnabled(true);
			btSalir.setRolloverIcon(new ImageIcon(getClass().getResource("/Recursos/boton-naranja.png")));
			btSalir.setText("Salir");
			btSalir.setSize(new Dimension(214, 64));
			btSalir.setLocation(new Point(19, 390));
			btSalir.setBackground(new Color(0, 51, 153));
			btSalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de salir de la aplicación?","Sistema Académico", JOptionPane.YES_NO_OPTION);
				    if (respuesta == JOptionPane.YES_OPTION) {
				    	System.exit(0);
				    }
				}
			});
		}
		return btSalir;
	}

	/**
	 * This method initializes btVendedor
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtVendedor() {
		if (btVendedor == null) {
			btVendedor = new JButton();
			btVendedor.setBounds(new Rectangle(22, 312, 214, 72));
			btVendedor.setFont(new Font("Arial Black", Font.PLAIN, 14));
			btVendedor.setForeground(Color.white);
			btVendedor.setBorder(null);
			btVendedor.setContentAreaFilled(false);
			btVendedor.setHorizontalAlignment(SwingConstants.CENTER);
			btVendedor.setHorizontalTextPosition(SwingConstants.CENTER);
			btVendedor.setIcon(new ImageIcon(getClass().getResource("/Recursos/boton-azul.png")));
			btVendedor.setRolloverEnabled(true);
			btVendedor.setRolloverIcon(new ImageIcon(getClass().getResource("/Recursos/boton-naranja.png")));
			btVendedor.setText("Vendedor");
			btVendedor.setBackground(new Color(0, 51, 153));
			btVendedor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					panelCentral.removeAll();
					/*try {
						panelCentral.add(new panelCurso(),"Curso");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					panelCentral.repaint();*/
				}
			});
		}
		return btVendedor;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
