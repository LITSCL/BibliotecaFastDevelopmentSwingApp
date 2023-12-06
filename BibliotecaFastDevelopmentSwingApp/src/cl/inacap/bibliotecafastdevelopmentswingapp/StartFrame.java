package cl.inacap.bibliotecafastdevelopmentswingapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cl.inacap.bibliotecafastdevelopmentswingapp.frame.*;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JButton;


public class StartFrame extends JFrame {
	private RegistrarLibroFrame registrarLibroFrame = new RegistrarLibroFrame();
	private RegistrarVentaFrame registrarVentaFrame = new RegistrarVentaFrame();
	private RegistrarCompraFrame registrarCompraFrame = new RegistrarCompraFrame();
	private RegistrarMetodoPagoFacturaFrame registrarMetodoPagoFacturaFrame = new RegistrarMetodoPagoFacturaFrame();
	private RegistrarAutorFrame registrarAutorFrame = new RegistrarAutorFrame();
	private RegistrarDistribuidorFrame registrarDistribuidorFrame = new RegistrarDistribuidorFrame();
	private RegistrarEditorialFrame registrarEditorialFrame = new RegistrarEditorialFrame();
	private RegistrarEstadoLibroFrame registrarEstadoLibroFrame = new RegistrarEstadoLibroFrame();
	private RegistrarTrabajadorFrame registrarTrabajadorFrame = new RegistrarTrabajadorFrame();
	private RegistrarIdiomaFrame registrarIdiomaFrame = new RegistrarIdiomaFrame();
	private RegistrarClienteFrame registrarClienteFrame = new RegistrarClienteFrame();
	private RegistrarCategoriaFrame registrarCategoriaFrame = new RegistrarCategoriaFrame();
	private ModificarDistribuidorFrame modificarDistribuidorFrame = new ModificarDistribuidorFrame();
	private ModificarAutorFrame modificarAutorFrame = new ModificarAutorFrame();
	private ModificarEstadoLibroFrame modificarEstadoLibroFrame = new ModificarEstadoLibroFrame();
	private ModificarCategoriaFrame modificarCategoriaFrame = new ModificarCategoriaFrame();
	private ModificarTrabajadorFrame modificarTrabajadorFrame = new ModificarTrabajadorFrame();
	private ModificarClienteFrame modificarClienteFrame = new ModificarClienteFrame();
	private ModificarIdiomaFrame modificarIdiomaFrame = new ModificarIdiomaFrame();
	private ModificarMetodoPagoFacturaFrame modificarMetodoPagoFacturaFrame = new ModificarMetodoPagoFacturaFrame();
	private ModificarMetodoPagoBoletaFrame modificarMetodoPagoBoletaFrame = new ModificarMetodoPagoBoletaFrame();
	private ModificarEditorialFrame modificarEditorialFrame = new ModificarEditorialFrame();
	private MostrarAutorFrame mostrarAutorFrame = new MostrarAutorFrame();
	private MostrarTrabajadorFrame mostrarTrabajadorFrame = new MostrarTrabajadorFrame();
	private MostrarIdiomaFrame mostrarIdiomaFrame = new MostrarIdiomaFrame();
	private MostrarLibroFrame mostrarLibroFrame = new MostrarLibroFrame();
	private MostrarEditorialFrame mostrarEditorialFrame = new MostrarEditorialFrame();
	private MostrarEstadoLibroFrame mostrarEstadoLibroFrame = new MostrarEstadoLibroFrame();
	private MostrarCategoriaFrame mostrarCategoriaFrame = new MostrarCategoriaFrame();
	private MostrarDistribuidorFrame mostrarDistribuidorFrame = new MostrarDistribuidorFrame();
	private MostrarClienteFrame mostrarClienteFrame = new MostrarClienteFrame();
	private BorrarLibroFrame borrarLibroFrame = new BorrarLibroFrame();
	private BorrarDistribuidorFrame borrarDistribuidorFrame = new BorrarDistribuidorFrame();
	private BorrarMetodoPagoFacturaFrame borrarMetodoPagoFacturaFrame = new BorrarMetodoPagoFacturaFrame();
	private BorrarMetodoPagoBoletaFrame borrarMetodoPagoBoletaFrame = new BorrarMetodoPagoBoletaFrame();
	private RegistrarMetodoPagoBoletaFrame registrarMetodoPagoBoletaFrame = new RegistrarMetodoPagoBoletaFrame();
	private BorrarClienteFrame borrarClienteFrame = new BorrarClienteFrame();
	private BorrarEstadoLibroFrame borrarEstadoLibroFrame = new BorrarEstadoLibroFrame();
	private BorrarTrabajadorFrame borrarTrabajadorFrame = new BorrarTrabajadorFrame();
	private BorrarFacturaFrame borrarFacturaFrame = new BorrarFacturaFrame();
	private BorrarBoletaFrame borrarBoletaFrame = new BorrarBoletaFrame();
	private BorrarEditorialFrame borrarEditorialFrame = new BorrarEditorialFrame();
	private BorrarAutorFrame borrarAutorFrame = new BorrarAutorFrame();
	private BorrarIdiomaFrame borrarIdiomaFrame = new BorrarIdiomaFrame();
	private BorrarCategoriaFrame borrarCategoriaFrame = new BorrarCategoriaFrame();
	private BuscarTrabajadorFrame buscarTrabajadorFrame = new BuscarTrabajadorFrame();
	private BuscarDistribuidorFrame buscarDistribuidorFrame = new BuscarDistribuidorFrame();
	private BuscarClienteFrame buscarClienteFrame = new BuscarClienteFrame();
	private BuscarAutorFrame buscarAutorFrame = new BuscarAutorFrame();
	private BuscarFolioBoletaFrame buscarFolioBoletaFrame = new BuscarFolioBoletaFrame();
	private BuscarFolioFacturaFrame buscarFolioFacturaFrame = new BuscarFolioFacturaFrame();
	private BuscarEditorialFrame buscarEditorialFrame = new BuscarEditorialFrame();
	private BuscarCategoriaFrame buscarCategoriaFrame = new BuscarCategoriaFrame();
	private BuscarIdiomaFrame buscarIdiomaFrame = new BuscarIdiomaFrame();
	private BuscarEstadoLibroFrame buscarEstadoLibroFrame = new BuscarEstadoLibroFrame();
	private GenerarFacturaFrame generarFacturaFrame = new GenerarFacturaFrame();
	private GenerarBoletaFrame generarBoletaFrame = new GenerarBoletaFrame();
	private MostrarRelacionFrame mostrarRelacion = new MostrarRelacionFrame();
	private JPanel contentPane;
	public static JDesktopPane desktopPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setTitle("BibliotecaFastDevelopmentSwingApp");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/startframe.png")));	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 652);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnLibros = new JMenu("Libros");
		mnLibros.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/libro.png")));
		menuBar.add(mnLibros);
		
		JMenu mnDespliegaLibros = new JMenu("Libros");
		mnDespliegaLibros.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/libro.png")));
		mnLibros.add(mnDespliegaLibros);
		
		JMenuItem mntmRegistrarLibro = new JMenuItem("Registrar");
		mntmRegistrarLibro.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/libro_add.png")));
		mntmRegistrarLibro.addActionListener(e -> showFrameRegistrarLibro(e));
		mnDespliegaLibros.add(mntmRegistrarLibro);
		
		JMenuItem mntmModificarIsbn = new JMenuItem("Modificar / ISBN");
		mntmModificarIsbn.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/libro_set.png")));
		mnDespliegaLibros.add(mntmModificarIsbn);
		
		JMenuItem mntmBuscarIsbn = new JMenuItem("Buscar / ISBN");
		mntmBuscarIsbn.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/libro_find.png")));
		mnDespliegaLibros.add(mntmBuscarIsbn);
		
		JMenuItem mntmMostrarTodosLosLibros = new JMenuItem("Mostrar todos");
		mntmMostrarTodosLosLibros.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/libro_list.png")));
		mntmMostrarTodosLosLibros.addActionListener(e -> showFrameMostrarLibro(e));
		mnDespliegaLibros.add(mntmMostrarTodosLosLibros);
		
		JMenuItem mntmBorrarLibro = new JMenuItem("Borrar");
		mntmBorrarLibro.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/libro_del.png")));
		mntmBorrarLibro.addActionListener(e -> showFrameBorrarLibro(e));
		mnDespliegaLibros.add(mntmBorrarLibro);
		
		JMenu mnDetallesLibros = new JMenu("Detalles");
		mnDetallesLibros.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/detalle.png")));
		mnLibros.add(mnDetallesLibros);
		
		JMenu mnAutor = new JMenu("Autor");
		mnAutor.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/autor.png")));
		mnDetallesLibros.add(mnAutor);
		
		JMenuItem mntmRegistrarAutor = new JMenuItem("Registrar");
		mntmRegistrarAutor.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/autor_add.png")));
		mntmRegistrarAutor.addActionListener(e -> showFrameRegistrarAutor(e));
		mnAutor.add(mntmRegistrarAutor);
		
		JMenuItem mntmModificarAutor = new JMenuItem("Modificar");
		mntmModificarAutor.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/autor_set.png")));
		mntmModificarAutor.addActionListener(e -> showFrameModificarAutor(e));
		mnAutor.add(mntmModificarAutor);
		
		JMenuItem mntmMostrarTodosLosAutores = new JMenuItem("Mostrar todos");
		mntmMostrarTodosLosAutores.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/autor_list.png")));
		mntmMostrarTodosLosAutores.addActionListener(e -> showFrameMostrarAutor(e));
		mnAutor.add(mntmMostrarTodosLosAutores);
		
		JMenuItem mntmBuscarCodigoAutor = new JMenuItem("Buscar / C\u00F3d. Autor");
		mntmBuscarCodigoAutor.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/autor_find.png")));
		mntmBuscarCodigoAutor.addActionListener(e -> showFrameBuscarAutor(e));
		mnAutor.add(mntmBuscarCodigoAutor);
		
		JMenuItem mntmBorrarAutor = new JMenuItem("Borrar");
		mntmBorrarAutor.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/autor_del.png")));
		mntmBorrarAutor.addActionListener(e -> showFrameBorrarAutor(e));
		mnAutor.add(mntmBorrarAutor);
		
		JMenu mnEditorial = new JMenu("Editorial");
		mnEditorial.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/editorial.png")));
		mnDetallesLibros.add(mnEditorial);
		
		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mntmRegistrar.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/editorial_add.png")));
		mntmRegistrar.addActionListener(e -> showFrameIngresarEditorial(e));
		mnEditorial.add(mntmRegistrar);
		
		JMenuItem mntmModificarEditorial = new JMenuItem("Modificar");
		mntmModificarEditorial.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/editorial_set.png")));
		mntmModificarEditorial.addActionListener(e -> showFrameModificarEditorial(e));
		mnEditorial.add(mntmModificarEditorial);
		
		JMenuItem mntmMostrarTodasLasEditoriales = new JMenuItem("Mostrar todos");
		mntmMostrarTodasLasEditoriales.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/editorial_list.png")));
		mntmMostrarTodasLasEditoriales.addActionListener(e -> showFrameMostrarEditorial(e));
		mnEditorial.add(mntmMostrarTodasLasEditoriales);
		
		JMenuItem mntmBuscarCodigoEditorial = new JMenuItem("Buscar / C\u00F3d. Editorial");
		mntmBuscarCodigoEditorial.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/editorial_find.png")));
		mntmBuscarCodigoEditorial.addActionListener(e -> showFrameBuscarEditorial(e));
		mnEditorial.add(mntmBuscarCodigoEditorial);
		
		JMenuItem mntmBorrarEditorial = new JMenuItem("Borrar");
		mntmBorrarEditorial.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/editorial_del.png")));
		mntmBorrarEditorial.addActionListener(e -> showFrameBorrarEditorial(e));
		mnEditorial.add(mntmBorrarEditorial);
		
		JMenu mnCategoria = new JMenu("Categor\u00EDa");
		mnCategoria.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/categoria.png")));
		mnDetallesLibros.add(mnCategoria);
		
		JMenuItem mntmRegistrarCategoria = new JMenuItem("Registrar");
		mntmRegistrarCategoria.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/categoria_add.png")));
		mntmRegistrarCategoria.addActionListener(e -> showFrameRegistrarCategoria(e));
		mnCategoria.add(mntmRegistrarCategoria);
		
		JMenuItem mntmModificarCategoria = new JMenuItem("Modificar");
		mntmModificarCategoria.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/categoria_set.png")));
		mntmModificarCategoria.addActionListener(e -> showFrameModificaCategoria(e));
		mnCategoria.add(mntmModificarCategoria);
		
		JMenuItem mntmMostrarTodasLasCategorias = new JMenuItem("Mostrar todos");
		mntmMostrarTodasLasCategorias.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/categoria_list.png")));
		mnCategoria.add(mntmMostrarTodasLasCategorias);
		mntmMostrarTodasLasCategorias.addActionListener(e -> showFrameMostrarCategoria(e));
		
		JMenuItem mntmBuscarCodigoCategoria = new JMenuItem("Buscar / C\u00F3d. Categor\u00EDa");
		mntmBuscarCodigoCategoria.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/categoria_find.png")));
		mntmBuscarCodigoCategoria.addActionListener(e -> showFrameBuscarCategoria(e));
		mnCategoria.add(mntmBuscarCodigoCategoria);
		
		JMenuItem mntmBorrarCategoria = new JMenuItem("Borrar");
		mntmBorrarCategoria.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/categoria_del.png")));
		mntmBorrarCategoria.addActionListener(e -> showFrameBorrar(e));
		mnCategoria.add(mntmBorrarCategoria);
		
		JMenu mnIdioma = new JMenu("Idioma");
		mnIdioma.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/idioma.png")));
		mnDetallesLibros.add(mnIdioma);
		
		JMenuItem mntmRegistrarIdioma = new JMenuItem("Registrar");
		mntmRegistrarIdioma.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/idioma_add.png")));
		mntmRegistrarIdioma.addActionListener(e -> showFrameRregistrarIdioma(e));
		mnIdioma.add(mntmRegistrarIdioma);
		
		JMenuItem mntmModificarIdioma = new JMenuItem("Modificar");
		mntmModificarIdioma.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/idioma_set.png")));
		mntmModificarIdioma.addActionListener(e -> showFrameModificarIdioma(e));
		mnIdioma.add(mntmModificarIdioma);
		
		JMenuItem mntmMostrarTodosLosIdiomas = new JMenuItem("Mostrar todos");
		mntmMostrarTodosLosIdiomas.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/idioma_list.png")));
		mntmMostrarTodosLosIdiomas.addActionListener(e -> showFrameMostrarIdioma(e));
		mnIdioma.add(mntmMostrarTodosLosIdiomas);
		
		JMenuItem mntmBuscarCodigoIdioma = new JMenuItem("Buscar / C\u00F3d. Idioma");
		mntmBuscarCodigoIdioma.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/idioma_find.png")));
		mntmBuscarCodigoIdioma.addActionListener(e -> showFrameBuscarIdioma(e));
		mnIdioma.add(mntmBuscarCodigoIdioma);
		
		JMenuItem mntmBorrarIdioma = new JMenuItem("Borrar");
		mntmBorrarIdioma.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/idioma_del.png")));
		mntmBorrarIdioma.addActionListener(e -> showFrameBorrarIdioma(e));
		mnIdioma.add(mntmBorrarIdioma);
		
		JMenu mnEstado = new JMenu("Estado");
		mnEstado.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/estado.png")));
		mnDetallesLibros.add(mnEstado);
		
		JMenuItem mntmRegistrarEstado = new JMenuItem("Registrar");
		mntmRegistrarEstado.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/estado_add.png")));
		mntmRegistrarEstado.addActionListener(e -> showFrameRegistrarEstadoLibro(e));
		mnEstado.add(mntmRegistrarEstado);
		
		JMenuItem mntmModificarEstado = new JMenuItem("Modificar");
		mntmModificarEstado.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/estado_set.png")));
		mntmModificarEstado.addActionListener(e -> showFrameModificarEstadoLibro(e));
		mnEstado.add(mntmModificarEstado);
		
		JMenuItem mntmMostrarTodosLosEstados = new JMenuItem("Mostrar todos");
		mntmMostrarTodosLosEstados.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/estado_list.png")));
		mntmMostrarTodosLosEstados.addActionListener(e -> showFrameMostrarEstadoLibro(e));
		mnEstado.add(mntmMostrarTodosLosEstados);
		
		JMenuItem mntmBuscarCodigoEstado = new JMenuItem("Buscar / C\u00F3d. Estado");
		mntmBuscarCodigoEstado.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/estado_find.png")));
		mntmBuscarCodigoEstado.addActionListener(e -> showFrameEstadoLibro(e));
		mnEstado.add(mntmBuscarCodigoEstado);
		
		JMenuItem mntmBorrarEstado = new JMenuItem("Borrar");
		mntmBorrarEstado.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/estado_del.png")));
		mntmBorrarEstado.addActionListener(e -> showFrameBorrarEstadoLibro(e));
		mnEstado.add(mntmBorrarEstado);
		
		JMenu mnDistribuidores = new JMenu("Distribuidores");
		mnDistribuidores.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/distribuidor.png")));
		menuBar.add(mnDistribuidores);
		
		JMenuItem mntmRegistrarDistribuidor = new JMenuItem("Registrar");
		mntmRegistrarDistribuidor.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/distribuidor_add.png")));
		mntmRegistrarDistribuidor.addActionListener(e -> showFrameRegistrarDistribuidor(e));
		mnDistribuidores.add(mntmRegistrarDistribuidor);
		
		JMenuItem mntmModificarDistribuidor = new JMenuItem("Modificar");
		mntmModificarDistribuidor.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/distribuidor_set.png")));
		mntmModificarDistribuidor.addActionListener(e -> showFrameModificarDistribuidor(e));
		mnDistribuidores.add(mntmModificarDistribuidor);
		
		JMenuItem mntmMostrarTodosLosDistribuidores = new JMenuItem("Mostrar todos");
		mntmMostrarTodosLosDistribuidores.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/distribuidor_list.png")));
		mntmMostrarTodosLosDistribuidores.addActionListener(e -> showFrameMostrarDistribuidor(e));
		mnDistribuidores.add(mntmMostrarTodosLosDistribuidores);
		
		JMenuItem mntmBuscarCodigoDistribuidor = new JMenuItem("Buscar / Rut. Distribuidor");
		mntmBuscarCodigoDistribuidor.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/distribuidor_find.png")));
		mntmBuscarCodigoDistribuidor.addActionListener(e -> showFrameBuscarDistribuidor(e));
		mnDistribuidores.add(mntmBuscarCodigoDistribuidor);
		
		JMenuItem mntmBorrarDistribuidor = new JMenuItem("Borrar");
		mntmBorrarDistribuidor.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/distribuidor_del.png")));
		mntmBorrarDistribuidor.addActionListener(e -> showFrameBorrarDistribuidor(e));
		mnDistribuidores.add(mntmBorrarDistribuidor);
		
		JMenu mnTrabajadores = new JMenu("Trabajadores");
		mnTrabajadores.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/trabajador.png")));
		menuBar.add(mnTrabajadores);
		
		JMenu mnTrabajadores_1 = new JMenu("Trabajadores");
		mnTrabajadores_1.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/trabajador.png")));
		mnTrabajadores.add(mnTrabajadores_1);
		
		JMenuItem mntmRegistrarTrabajador = new JMenuItem("Registrar");
		mntmRegistrarTrabajador.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/trabajador_add.png")));
		mntmRegistrarTrabajador.addActionListener(e -> showFrameRegistrarTrabajador(e));
		mnTrabajadores_1.add(mntmRegistrarTrabajador);
		
		JMenuItem mntmModificarTrabajador = new JMenuItem("Modificar");
		mntmModificarTrabajador.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/trabajador_set.png")));
		mntmModificarTrabajador.addActionListener(e -> showFrameModificarTrabajador(e));
		mnTrabajadores_1.add(mntmModificarTrabajador);
		
		JMenuItem mntmMostrarTodosLosTrabajadores = new JMenuItem("Mostrar todos");
		mntmMostrarTodosLosTrabajadores.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/trabajador_list.png")));
		mntmMostrarTodosLosTrabajadores.addActionListener(e -> showFrameMostrarTrabajador(e));
		mnTrabajadores_1.add(mntmMostrarTodosLosTrabajadores);
		
		JMenuItem mntmBuscarRutTrabajador = new JMenuItem("Buscar / Rut. Trabajador");
		mntmBuscarRutTrabajador.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/trabajador_find.png")));
		mntmBuscarRutTrabajador.addActionListener(e -> showFrameBuscarTrabajador(e));
		mnTrabajadores_1.add(mntmBuscarRutTrabajador);
		
		JMenuItem mntmBorrarTrabajador = new JMenuItem("Borrar");
		mntmBorrarTrabajador.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/trabajador_del.png")));
		mntmBorrarTrabajador.addActionListener(e -> showFrameBorrarTrabajador(e));
		mnTrabajadores_1.add(mntmBorrarTrabajador);
		
		JMenu mnDetallesTrabajadores = new JMenu("Detalles");
		mnDetallesTrabajadores.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/detalle.png")));
		mnTrabajadores.add(mnDetallesTrabajadores);
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/cliente.png")));
		menuBar.add(mnClientes);
		
		JMenu mnClientes_1 = new JMenu("Clientes");
		mnClientes_1.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/cliente.png")));
		mnClientes.add(mnClientes_1);
		
		JMenuItem mntmRegistrarCliente = new JMenuItem("Registrar");
		mntmRegistrarCliente.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/cliente_add.png")));
		mntmRegistrarCliente.addActionListener(e -> showFrameRegistrarCliente(e));
		mnClientes_1.add(mntmRegistrarCliente);
		
		JMenuItem mntmModificarCliente = new JMenuItem("Modificar");
		mntmModificarCliente.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/cliente_set.png")));	
		mntmModificarCliente.addActionListener(e -> showFrameModificarCliente(e));
		mnClientes_1.add(mntmModificarCliente);
		
		JMenuItem mntmMostrarTodosLosClientes = new JMenuItem("Mostrar todos");
		mntmMostrarTodosLosClientes.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/cliente_list.png")));
		mntmMostrarTodosLosClientes.addActionListener(e -> showFrameMostrarCliente(e));
		mnClientes_1.add(mntmMostrarTodosLosClientes);
		
		JMenuItem mntmBuscarRutCliente = new JMenuItem("Buscar / Rut. Cliente");
		mntmBuscarRutCliente.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/cliente_find.png")));
		mntmBuscarRutCliente.addActionListener(e -> showFrameBuscarCliente(e));
		mnClientes_1.add(mntmBuscarRutCliente);
		
		JMenuItem mntmBorrarCliente = new JMenuItem("Borrar");
		mntmBorrarCliente.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/cliente_del.png")));
		mntmBorrarCliente.addActionListener(e -> showFrameBorrarCliente(e));
		mnClientes_1.add(mntmBorrarCliente);
		
		JMenu mnDetalles = new JMenu("Detalles");
		mnDetalles.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/detalle.png")));
		mnClientes.add(mnDetalles);
		
		JMenu mnGestionFactura = new JMenu("Gesti\u00F3n factura");
		mnGestionFactura.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/facturaboleta.png")));
		menuBar.add(mnGestionFactura);
		
		JMenuItem mntmGenerarFactura = new JMenuItem("Generar factura");
		mntmGenerarFactura.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/facturaboleta_add.png")));
		mntmGenerarFactura.addActionListener(e -> showFrameGenerarFactura(e));
		mnGestionFactura.add(mntmGenerarFactura);
		
		JMenuItem mntmBorrarFolio = new JMenuItem("Borrar / Folio");
		mntmBorrarFolio.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/facturaboleta_del.png")));
		mntmBorrarFolio.addActionListener(e -> showFrameBorrarFactura(e));
		
		JMenuItem mntmBuscarFolioFactura = new JMenuItem("Buscar / Folio");
		mntmBuscarFolioFactura.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/facturaboleta_find.png")));
		mntmBuscarFolioFactura.addActionListener(e -> showFrameBuscarFolioFactura(e));
		mnGestionFactura.add(mntmBuscarFolioFactura);
		mnGestionFactura.add(mntmBorrarFolio);
		
		JMenu mnGestionCompra = new JMenu("Gesti\u00F3n compra");
		mnGestionCompra.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/compra.png")));
		menuBar.add(mnGestionCompra);
		
		JMenu mnMetodoDePagoFactura = new JMenu("M\u00E9todo de pago");
		mnMetodoDePagoFactura.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/metodopago.png")));
		mnGestionCompra.add(mnMetodoDePagoFactura);
		
		JMenuItem mntmRegistrarMetodoDePagoFactura = new JMenuItem("Registrar");
		mntmRegistrarMetodoDePagoFactura.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/metodopago_add.png")));
		mntmRegistrarMetodoDePagoFactura.addActionListener(e -> showFrameRegistrarMetodoPagoFactura(e));
		mnMetodoDePagoFactura.add(mntmRegistrarMetodoDePagoFactura);
		
		JMenuItem mntmModificarMetodoDePagoFactura = new JMenuItem("Modificar");
		mntmModificarMetodoDePagoFactura.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/metodopago_set.png")));
		mntmModificarMetodoDePagoFactura.addActionListener(e -> showFrameModificarMetodoPagoFactura(e));
		mnMetodoDePagoFactura.add(mntmModificarMetodoDePagoFactura);
		
		JMenuItem mntmBorrarMetodoDePagoFactura = new JMenuItem("Borrar");
		mntmBorrarMetodoDePagoFactura.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/metodopago_del.png")));
		mntmBorrarMetodoDePagoFactura.addActionListener(e -> showFrameBorrarMetodoPagoFactura(e));
		mnMetodoDePagoFactura.add(mntmBorrarMetodoDePagoFactura);
		
		JMenu mnTransaccion = new JMenu("Transacci\u00F3n");
		mnTransaccion.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/cashregister.png")));
		mnGestionCompra.add(mnTransaccion);
		
		JMenuItem mntmComprar = new JMenuItem("Comprar");
		mntmComprar.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/cashregister.png")));
		mntmComprar.addActionListener(e -> showFrameComprar(e));
		mnTransaccion.add(mntmComprar);
		
		JMenu mnBoleta = new JMenu("Gesti\u00F3n boleta");
		mnBoleta.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/facturaboleta.png")));
		menuBar.add(mnBoleta);
		
		JMenuItem mntmGenerarBoleta = new JMenuItem("Generar boleta");
		mntmGenerarBoleta.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/facturaboleta_add.png")));
		mntmGenerarBoleta.addActionListener(e -> showFrameGenerarBoletaFrame(e));
		mnBoleta.add(mntmGenerarBoleta);
		
		JMenuItem mntmBorrarFolioBoleta = new JMenuItem("Borrar / Folio");
		mntmBorrarFolioBoleta.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/facturaboleta_del.png")));
		mntmBorrarFolioBoleta.addActionListener(e -> showFrameBorrarBoleta(e));
		
		JMenuItem mntmBuscarFolioBoleta = new JMenuItem("Buscar / Folio");
		mntmBuscarFolioBoleta.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/facturaboleta_find.png")));
		mntmBuscarFolioBoleta.addActionListener(e -> showFrameBuscarFolioBoleta(e));
		mnBoleta.add(mntmBuscarFolioBoleta);
		mnBoleta.add(mntmBorrarFolioBoleta);
		
		JMenu mnGestionVenta = new JMenu("Gesti\u00F3n venta");
		mnGestionVenta.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/venta.png")));
		menuBar.add(mnGestionVenta);
		
		JMenu mnMetodoDePagoBoleta = new JMenu("M\u00E9todo de pago");
		mnMetodoDePagoBoleta.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/metodopago.png")));
		mnGestionVenta.add(mnMetodoDePagoBoleta);
		
		JMenuItem mntmRegistrarMetodoDePagoBoleta = new JMenuItem("Registrar");
		mntmRegistrarMetodoDePagoBoleta.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/metodopago_add.png")));
		mntmRegistrarMetodoDePagoBoleta.addActionListener(e -> showFrameRegistrarMetodoPagoBoleta(e));
		mnMetodoDePagoBoleta.add(mntmRegistrarMetodoDePagoBoleta);
		
		JMenuItem mntmModificarMetodoDePagoBoleta = new JMenuItem("Modificar");
		mntmModificarMetodoDePagoBoleta.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/metodopago_set.png")));
		mntmModificarMetodoDePagoBoleta.addActionListener(e -> showFrameModificarMetodoPagoBoleta(e));
		mnMetodoDePagoBoleta.add(mntmModificarMetodoDePagoBoleta);
		
		JMenuItem mntmBorrarMetodoDePagoBoleta = new JMenuItem("Borrar");
		mntmBorrarMetodoDePagoBoleta.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/metodopago_del.png")));
		mntmBorrarMetodoDePagoBoleta.addActionListener(e -> showFrameBorrarMetodoPagoBoleta(e));
		mnMetodoDePagoBoleta.add(mntmBorrarMetodoDePagoBoleta);
		
		JMenu mnTransaccionVenta = new JMenu("Transacci\u00F3n");
		mnTransaccionVenta.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/cashregister.png")));
		mnGestionVenta.add(mnTransaccionVenta);
		
		JMenuItem mntmVender = new JMenuItem("Vender");
		mntmVender.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/cashregister.png")));
		mntmVender.addActionListener(e -> showFrameVenderLibro(e));
		mnTransaccionVenta.add(mntmVender);
		
		JMenu mnNewMenu = new JMenu("Graficos");
		mnNewMenu.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/grafico.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Mostrar relaci\u00F3n");
		mntmNewMenuItem.setIcon(new ImageIcon(StartFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/grafico.png")));
		mntmNewMenuItem.addActionListener(e -> showFrameRelacion(e));
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
	}

	private Object showFrameRelacion(ActionEvent e) {
		if (this.mostrarRelacion != null) {
			desktopPane.remove(this.mostrarRelacion);
		}
		this.mostrarRelacion=new MostrarRelacionFrame();
		desktopPane.add(this.mostrarRelacion);
		this.mostrarRelacion.setVisible(true);
		return null;
	}

	private void showFrameBuscarFolioBoleta(ActionEvent e) {
		if (this.buscarFolioBoletaFrame != null) {
			desktopPane.remove(this.buscarFolioBoletaFrame);
		}
		this.buscarFolioBoletaFrame=new BuscarFolioBoletaFrame();
		desktopPane.add(this.buscarFolioBoletaFrame);
		this.buscarFolioBoletaFrame.setVisible(true);
	}

	private Object showFrameBuscarFolioFactura(ActionEvent e) {
		if (this.buscarFolioFacturaFrame != null) {
			desktopPane.remove(this.buscarFolioFacturaFrame);
		}
		this.buscarFolioFacturaFrame = new BuscarFolioFacturaFrame();
		desktopPane.add(this.buscarFolioFacturaFrame);
		this.buscarFolioFacturaFrame.setVisible(true);
		return null;
	}

	private void showFrameBorrarBoleta(ActionEvent e) {
		if (this.borrarBoletaFrame != null) {
			desktopPane.remove(this.borrarBoletaFrame);
		}
		this.borrarBoletaFrame = new BorrarBoletaFrame();
		desktopPane.add(this.borrarBoletaFrame);
		this.borrarBoletaFrame.setVisible(true);
	}

	private Object showFrameBorrarFactura(ActionEvent e) {
		if (this.borrarFacturaFrame != null) {
			desktopPane.remove(this.borrarFacturaFrame);
		}
		this.borrarFacturaFrame = new BorrarFacturaFrame();
		desktopPane.add(this.borrarFacturaFrame);
		this.borrarFacturaFrame.setVisible(true);
		return null;
	}

	private void showFrameVenderLibro(ActionEvent e) {
		if (this.registrarVentaFrame != null) {
			desktopPane.remove(this.registrarVentaFrame);
		}
		this.registrarVentaFrame = new RegistrarVentaFrame();
		desktopPane.add(this.registrarVentaFrame);
		this.registrarVentaFrame.setVisible(true);
	}

	private Object showFrameBorrarEstadoLibro(ActionEvent e) {
		if (this.borrarEstadoLibroFrame != null) {
			desktopPane.remove(this.borrarEstadoLibroFrame);
		}
		this.borrarEstadoLibroFrame = new BorrarEstadoLibroFrame();
		desktopPane.add(this.borrarEstadoLibroFrame);
		this.borrarEstadoLibroFrame.setVisible(true);
		return null;
	}

	private void showFrameComprar(ActionEvent e) {
		if (this.registrarCompraFrame != null) {
			desktopPane.remove(this.registrarCompraFrame);
		}
		this.registrarCompraFrame = new RegistrarCompraFrame();
		desktopPane.add(this.registrarCompraFrame);
		this.registrarCompraFrame.setVisible(true);
	}

	private Object showFrameGenerarBoletaFrame(ActionEvent e) {
		if (this.generarBoletaFrame != null) {
			desktopPane.remove(this.generarBoletaFrame);
		}
		this.generarBoletaFrame = new GenerarBoletaFrame();
		desktopPane.add(this.generarBoletaFrame);
		this.generarBoletaFrame.setVisible(true);
		return null;
	}

	private void showFrameBorrarMetodoPagoBoleta(ActionEvent e) {
		if (this.borrarMetodoPagoBoletaFrame != null) {
			desktopPane.remove(this.borrarMetodoPagoBoletaFrame);
		}
		this.borrarMetodoPagoBoletaFrame = new BorrarMetodoPagoBoletaFrame();
		desktopPane.add(this.borrarMetodoPagoBoletaFrame);
		this.borrarMetodoPagoBoletaFrame.setVisible(true);
	}

	private Object showFrameModificarMetodoPagoBoleta(ActionEvent e) {
		if (this.modificarMetodoPagoBoletaFrame != null) {
			desktopPane.remove(this.modificarMetodoPagoBoletaFrame);
		}
		this.modificarMetodoPagoBoletaFrame = new ModificarMetodoPagoBoletaFrame();
		desktopPane.add(this.modificarMetodoPagoBoletaFrame);
		this.modificarMetodoPagoBoletaFrame.setVisible(true);
		return null;
	}

	private void showFrameRegistrarMetodoPagoBoleta(ActionEvent e) {
		if (this.registrarMetodoPagoBoletaFrame != null) {
			desktopPane.remove(this.registrarMetodoPagoBoletaFrame);
		}
		this.registrarMetodoPagoBoletaFrame = new RegistrarMetodoPagoBoletaFrame();
		desktopPane.add(this.registrarMetodoPagoBoletaFrame);
		this.registrarMetodoPagoBoletaFrame.setVisible(true);
	}

	private Object showFrameGenerarFactura(ActionEvent e) {
		if (this.generarFacturaFrame != null) {
			desktopPane.remove(this.generarFacturaFrame);
		}
		this.generarFacturaFrame = new GenerarFacturaFrame();
		desktopPane.add(this.generarFacturaFrame);
		this.generarFacturaFrame.setVisible(true);
		return null;
	}

	private void showFrameBorrarMetodoPagoFactura(ActionEvent e) {
		if (this.borrarMetodoPagoFacturaFrame != null) {
			desktopPane.remove(this.borrarMetodoPagoFacturaFrame);
		}
		this.borrarMetodoPagoFacturaFrame = new BorrarMetodoPagoFacturaFrame();
		desktopPane.add(this.borrarMetodoPagoFacturaFrame);
		this.borrarMetodoPagoFacturaFrame.setVisible(true);
	}

	private Object showFrameModificarMetodoPagoFactura(ActionEvent e) {
		if (this.modificarMetodoPagoFacturaFrame != null) {
			desktopPane.remove(this.modificarMetodoPagoFacturaFrame);
		}
		this.modificarMetodoPagoFacturaFrame = new ModificarMetodoPagoFacturaFrame();
		desktopPane.add(this.modificarMetodoPagoFacturaFrame);
		this.modificarMetodoPagoFacturaFrame.setVisible(true);
		return null;
	}

	private void showFrameRegistrarMetodoPagoFactura(ActionEvent e) {
		if (this.registrarMetodoPagoFacturaFrame != null) {
			desktopPane.remove(this.registrarMetodoPagoFacturaFrame);
		}
		this.registrarMetodoPagoFacturaFrame = new RegistrarMetodoPagoFacturaFrame();
		desktopPane.add(this.registrarMetodoPagoFacturaFrame);
		this.registrarMetodoPagoFacturaFrame.setVisible(true);
	}

	private Object showFrameEstadoLibro(ActionEvent e) {
		if (this.buscarEstadoLibroFrame != null) {
			desktopPane.remove(this.buscarEstadoLibroFrame);
		}
		this.buscarEstadoLibroFrame = new BuscarEstadoLibroFrame();
		desktopPane.add(this.buscarEstadoLibroFrame);
		this.buscarEstadoLibroFrame.setVisible(true);
		return null;
	}

	private void showFrameBuscarIdioma(ActionEvent e) {
		if (this.buscarIdiomaFrame != null) {
			desktopPane.remove(this.buscarIdiomaFrame);
		}
		this.buscarIdiomaFrame = new BuscarIdiomaFrame();
		desktopPane.add(this.buscarIdiomaFrame);
		this.buscarIdiomaFrame.setVisible(true);
	}

	private Object showFrameBuscarCategoria(ActionEvent e) {
		if (this.buscarCategoriaFrame != null) {
			desktopPane.remove(this.buscarCategoriaFrame);
		}
		this.buscarCategoriaFrame = new BuscarCategoriaFrame();
		desktopPane.add(this.buscarCategoriaFrame);
		this.buscarCategoriaFrame.setVisible(true);
		return null;
	}

	private void showFrameBuscarEditorial(ActionEvent e) {
		if (this.buscarEditorialFrame != null) {
			desktopPane.remove(this.buscarEditorialFrame);
		}
		this.buscarEditorialFrame = new BuscarEditorialFrame();
		desktopPane.add(this.buscarEditorialFrame);
		this.buscarEditorialFrame.setVisible(true);
	}

	private Object showFrameBuscarAutor(ActionEvent e) {
		if (this.buscarAutorFrame != null) {
			desktopPane.remove(this.buscarAutorFrame);
		}
		this.buscarAutorFrame = new BuscarAutorFrame();
		desktopPane.add(this.buscarAutorFrame);
		this.buscarAutorFrame.setVisible(true);
		return null;
	}

	private void showFrameBuscarDistribuidor(ActionEvent e) {
		if (this.buscarDistribuidorFrame != null) {
			desktopPane.remove(this.buscarDistribuidorFrame);
		}
		this.buscarDistribuidorFrame = new BuscarDistribuidorFrame();
		desktopPane.add(this.buscarDistribuidorFrame);
		this.buscarDistribuidorFrame.setVisible(true);
	}

	private Object showFrameBuscarCliente(ActionEvent e) {
		if (this.buscarClienteFrame != null) {
			desktopPane.remove(this.buscarClienteFrame);
		}
		this.buscarClienteFrame = new BuscarClienteFrame();
		desktopPane.add(this.buscarClienteFrame);
		this.buscarClienteFrame.setVisible(true);
		return null;
	}

	private void showFrameBuscarTrabajador(ActionEvent e) {
		if (this.buscarTrabajadorFrame != null) {
			desktopPane.remove(this.buscarTrabajadorFrame);
		}
		this.buscarTrabajadorFrame = new BuscarTrabajadorFrame();
		desktopPane.add(this.buscarTrabajadorFrame);
		this.buscarTrabajadorFrame.setVisible(true);
	}

	private Object showFrameModificarEstadoLibro(ActionEvent e) {
		if (this.modificarEstadoLibroFrame != null) {
			desktopPane.remove(this.modificarEstadoLibroFrame);
		}
		this.modificarEstadoLibroFrame = new ModificarEstadoLibroFrame();
		desktopPane.add(this.modificarEstadoLibroFrame);
		this.modificarEstadoLibroFrame.setVisible(true);
		return null;
	}

	private void showFrameModificarIdioma(ActionEvent e) {
		if (this.modificarIdiomaFrame != null) {
			desktopPane.remove(this.modificarIdiomaFrame);
		}
		this.modificarIdiomaFrame = new ModificarIdiomaFrame();
		desktopPane.add(this.modificarIdiomaFrame);
		this.modificarIdiomaFrame.setVisible(true);
	}

	private Object showFrameModificarEditorial(ActionEvent e) {
		if (this.modificarEditorialFrame != null) {
			desktopPane.remove(this.modificarEditorialFrame);
		}
		this.modificarEditorialFrame = new ModificarEditorialFrame();
		desktopPane.add(this.modificarEditorialFrame);
		this.modificarEditorialFrame.setVisible(true);
		return null;
	}

	private void showFrameModificarCliente(ActionEvent e) {
		if (this.modificarClienteFrame != null) {
			desktopPane.remove(this.modificarClienteFrame);
		}
		this.modificarClienteFrame = new ModificarClienteFrame();
		desktopPane.add(this.modificarClienteFrame);
		this.modificarClienteFrame.setVisible(true);
	}

	private Object showFrameModificarTrabajador(ActionEvent e) {
		if (this.modificarTrabajadorFrame != null) {
			desktopPane.remove(this.modificarTrabajadorFrame);
		}
		this.modificarTrabajadorFrame = new ModificarTrabajadorFrame();
		desktopPane.add(this.modificarTrabajadorFrame);
		this.modificarTrabajadorFrame.setVisible(true);
		return null;
	}

	private void showFrameModificaCategoria(ActionEvent e) {
		if (this.modificarCategoriaFrame != null) {
			desktopPane.remove(this.modificarCategoriaFrame);
		}
		this.modificarCategoriaFrame = new ModificarCategoriaFrame();
		desktopPane.add(this.modificarCategoriaFrame);
		this.modificarCategoriaFrame.setVisible(true);
	}

	private Object showFrameBorrar(ActionEvent e) {
		if (this.borrarCategoriaFrame != null) {
			desktopPane.remove(this.borrarCategoriaFrame);
		}
		this.borrarCategoriaFrame = new BorrarCategoriaFrame();
		desktopPane.add(this.borrarCategoriaFrame);
		this.borrarCategoriaFrame.setVisible(true);
		return null;
	}

	private void showFrameBorrarIdioma(ActionEvent e) {
		if (this.borrarIdiomaFrame != null) {
			desktopPane.remove(this.borrarIdiomaFrame);
		}
		this.borrarIdiomaFrame = new BorrarIdiomaFrame();
		desktopPane.add(this.borrarIdiomaFrame);
		this.borrarIdiomaFrame.setVisible(true);
	}

	private Object showFrameBorrarEditorial(ActionEvent e) {
		if (this.borrarEditorialFrame != null) {
			desktopPane.remove(this.borrarEditorialFrame);
		}
		this.borrarEditorialFrame = new BorrarEditorialFrame();
		desktopPane.add(this.borrarEditorialFrame);
		this.borrarEditorialFrame.setVisible(true);
		return null;
	}

	private void showFrameBorrarCliente(ActionEvent e) {
		if (this.borrarClienteFrame != null) {
			desktopPane.remove(this.borrarClienteFrame);
		}
		this.borrarClienteFrame = new BorrarClienteFrame();
		desktopPane.add(this.borrarClienteFrame);
		this.borrarClienteFrame.setVisible(true);
	}

	private Object showFrameBorrarTrabajador(ActionEvent e) {
		if (this.borrarTrabajadorFrame != null) {
			desktopPane.remove(this.borrarTrabajadorFrame);
		}
		this.borrarTrabajadorFrame = new BorrarTrabajadorFrame();
		desktopPane.add(this.borrarTrabajadorFrame);
		this.borrarTrabajadorFrame.setVisible(true);
		return null;
	}

	private void showFrameMostrarTrabajador(ActionEvent e) {
		if (this.mostrarTrabajadorFrame != null) {
			desktopPane.remove(this.mostrarTrabajadorFrame);
		}
		this.mostrarTrabajadorFrame = new MostrarTrabajadorFrame();
		desktopPane.add(this.mostrarTrabajadorFrame);
		this.mostrarTrabajadorFrame.setVisible(true);
	}

	private Object showFrameMostrarLibro(ActionEvent e) {
		if (this.mostrarLibroFrame != null) {
			desktopPane.remove(this.mostrarLibroFrame);
		}
		this.mostrarLibroFrame = new MostrarLibroFrame();
		desktopPane.add(this.mostrarLibroFrame);
		this.mostrarLibroFrame.setVisible(true);
		return null;
	}

	private void showFrameMostrarEstadoLibro(ActionEvent e) {
		if (this.mostrarEstadoLibroFrame != null) {
			desktopPane.remove(this.mostrarEstadoLibroFrame);
		}
		this.mostrarEstadoLibroFrame = new MostrarEstadoLibroFrame();
		desktopPane.add(this.mostrarEstadoLibroFrame);
		this.mostrarEstadoLibroFrame.setVisible(true);
	}

	private Object showFrameMostrarIdioma(ActionEvent e) {
		if (this.mostrarIdiomaFrame != null) {
			desktopPane.remove(this.mostrarIdiomaFrame);
		}
		this.mostrarIdiomaFrame = new MostrarIdiomaFrame();
		desktopPane.add(this.mostrarIdiomaFrame);
		this.mostrarIdiomaFrame.setVisible(true);
		return null;
	}

	private void showFrameMostrarEditorial(ActionEvent e) {
		if (this.mostrarEditorialFrame != null) { 
			desktopPane.remove(this.mostrarEditorialFrame); 
		}
		this.mostrarEditorialFrame = new MostrarEditorialFrame(); 
		desktopPane.add(this.mostrarEditorialFrame); 
		this.mostrarEditorialFrame.setVisible(true); 
	}

	private Object showFrameMostrarCliente(ActionEvent e) {
		if (this.mostrarClienteFrame != null) { 
			desktopPane.remove(this.mostrarClienteFrame); 
		}
		this.mostrarClienteFrame = new MostrarClienteFrame(); 
		desktopPane.add(this.mostrarClienteFrame); 
		this.mostrarClienteFrame.setVisible(true); 
		return null;
	}

	private void showFrameMostrarCategoria(ActionEvent e) {
		if (this.mostrarCategoriaFrame != null) { 
			desktopPane.remove(this.mostrarCategoriaFrame); 
		}
		this.mostrarCategoriaFrame = new MostrarCategoriaFrame(); 
		desktopPane.add(this.mostrarCategoriaFrame); 
		this.mostrarCategoriaFrame.setVisible(true); 
	}

	private Object showFrameRegistrarCliente(ActionEvent e) {
		if (this.registrarClienteFrame != null) { 
			desktopPane.remove(this.registrarClienteFrame); 
		}
		this.registrarClienteFrame = new RegistrarClienteFrame(); 
		desktopPane.add(this.registrarClienteFrame); 
		this.registrarClienteFrame.setVisible(true); 
		return null;
	}

	private void showFrameRegistrarTrabajador(ActionEvent e) {
		if (this.registrarTrabajadorFrame != null) { 
			desktopPane.remove(this.registrarTrabajadorFrame); 
		}
		this.registrarTrabajadorFrame = new RegistrarTrabajadorFrame(); 
		desktopPane.add(this.registrarTrabajadorFrame); 
		this.registrarTrabajadorFrame.setVisible(true); 
	}

	private Object showFrameRegistrarCategoria(ActionEvent e) {
		if (this.registrarCategoriaFrame != null) { 
			desktopPane.remove(this.registrarCategoriaFrame); 
		}
		this.registrarCategoriaFrame = new RegistrarCategoriaFrame(); 
		desktopPane.add(this.registrarCategoriaFrame); 
		this.registrarCategoriaFrame.setVisible(true); 
		return null;
	}

	private void showFrameRregistrarIdioma(ActionEvent e) {
		if (this.registrarIdiomaFrame != null) { 
			desktopPane.remove(this.registrarIdiomaFrame); 
		}
		this.registrarIdiomaFrame = new RegistrarIdiomaFrame(); 
		desktopPane.add(this.registrarIdiomaFrame); 
		this.registrarIdiomaFrame.setVisible(true); 
	}

	private Object showFrameRegistrarEstadoLibro(ActionEvent e) {
		if (this.registrarEstadoLibroFrame != null) { 
			desktopPane.remove(this.registrarEstadoLibroFrame); 
		}
		this.registrarEstadoLibroFrame = new RegistrarEstadoLibroFrame(); 
		desktopPane.add(this.registrarEstadoLibroFrame); 
		this.registrarEstadoLibroFrame.setVisible(true); 
		return null;
	}

	private void showFrameIngresarEditorial(ActionEvent e) {
		if (this.registrarEditorialFrame != null) { 
			desktopPane.remove(this.registrarEditorialFrame); 
		}
		this.registrarEditorialFrame = new RegistrarEditorialFrame(); 
		desktopPane.add(this.registrarEditorialFrame); 
		this.registrarEditorialFrame.setVisible(true); 
	}

	private Object showFrameBorrarAutor(ActionEvent e) {
		if (this.borrarAutorFrame != null) { 
			desktopPane.remove(this.borrarAutorFrame); 
		}
		this.borrarAutorFrame = new BorrarAutorFrame(); 
		desktopPane.add(this.borrarAutorFrame); 
		this.borrarAutorFrame.setVisible(true); 
		return null;
	}

	private void showFrameBorrarDistribuidor(ActionEvent e) {
		if (this.borrarDistribuidorFrame != null) { 
			desktopPane.remove(this.borrarDistribuidorFrame); 
		}
		this.borrarDistribuidorFrame = new BorrarDistribuidorFrame(); 
		desktopPane.add(this.borrarDistribuidorFrame); 
		this.borrarDistribuidorFrame.setVisible(true); 
	}

	private Object showFrameBorrarLibro(ActionEvent e) {
		if (this.borrarLibroFrame != null) { 
			desktopPane.remove(this.borrarLibroFrame); 
		}
		this.borrarLibroFrame = new BorrarLibroFrame(); 
		desktopPane.add(this.borrarLibroFrame); 
		this.borrarLibroFrame.setVisible(true); 
		return null;
	}

	private void showFrameMostrarDistribuidor(ActionEvent e) {
		if (this.mostrarDistribuidorFrame != null) { 
			desktopPane.remove(this.mostrarDistribuidorFrame); 
		}
		this.mostrarDistribuidorFrame = new MostrarDistribuidorFrame(); 
		desktopPane.add(this.mostrarDistribuidorFrame); 
		this.mostrarDistribuidorFrame.setVisible(true); 
	}

	private Object showFrameMostrarAutor(ActionEvent e) {
		if (this.mostrarAutorFrame != null) { 
			desktopPane.remove(this.mostrarAutorFrame); 
		}
		this.mostrarAutorFrame = new MostrarAutorFrame(); 
		desktopPane.add(this.mostrarAutorFrame); 
		this.mostrarAutorFrame.setVisible(true); 
		return null;
	}

	private void showFrameModificarAutor(ActionEvent e) {
		if (this.modificarAutorFrame != null) { 
			desktopPane.remove(this.modificarAutorFrame); 
		}
		this.modificarAutorFrame = new ModificarAutorFrame(); 
		desktopPane.add(this.modificarAutorFrame); 
		this.modificarAutorFrame.setVisible(true); 

	}

	private Object showFrameModificarDistribuidor(ActionEvent e) {
		if (this.modificarDistribuidorFrame != null) { 
			desktopPane.remove(this.modificarDistribuidorFrame); 
		}
		this.modificarDistribuidorFrame = new ModificarDistribuidorFrame(); 
		desktopPane.add(this.modificarDistribuidorFrame); 
		this.modificarDistribuidorFrame.setVisible(true); 
		return null;
	}

	private void showFrameRegistrarDistribuidor(ActionEvent e) {
		if (this.registrarDistribuidorFrame != null) { 
			desktopPane.remove(this.registrarDistribuidorFrame); 
		}
		this.registrarDistribuidorFrame = new RegistrarDistribuidorFrame(); 
		desktopPane.add(this.registrarDistribuidorFrame); 
		this.registrarDistribuidorFrame.setVisible(true); 
	}

	private Object showFrameRegistrarAutor(ActionEvent e) {
		if (this.registrarAutorFrame != null) { 
			desktopPane.remove(this.registrarAutorFrame); 
		}
		this.registrarAutorFrame = new RegistrarAutorFrame(); 
		desktopPane.add(this.registrarAutorFrame); 
		this.registrarAutorFrame.setVisible(true); 
		return null;
	}

	private void showFrameRegistrarLibro(ActionEvent e) {
		if (this.registrarLibroFrame != null) { 
			desktopPane.remove(this.registrarLibroFrame); 
		}
		this.registrarLibroFrame = new RegistrarLibroFrame(); 
		desktopPane.add(this.registrarLibroFrame); 
		this.registrarLibroFrame.setVisible(true); 	
	}
}
