package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.AutorLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.AutorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CategoriaLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CategoriaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EditorialDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EstadoLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.IdiomaLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.IdiomaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.LibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Autor;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Categoria;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Editorial;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.EstadoLibro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Idioma;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Libro;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.FechaGringaUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarLibroFrame extends JInternalFrame {
	private JTextField textFieldFechaDePublicacion;
	private JTextField textFieldPrecioDeReferencia;
	private JTextField textFieldTitulo;
	private JTextField textFieldISBN;
	private JTextField textFieldNumeroDeSerie;
	private JComboBox<String> comboBoxEstadoDelLibro;
	private JComboBox<String> comboBoxEditorial;
	private JSpinner spinnerNumeroDePaginas;
	private JComboBox<Idioma> comboBoxIdioma;
	private JList listIdioma;
	private JComboBox <Autor>comboBoxAutor;
	private JList listAutor;
	private JComboBox<Categoria> comboBoxCategoria;
	private List<Idioma> idiomas = new IdiomaDAO().getAll();
	private List<Autor> autores = new AutorDAO().getAll();
	private List<Categoria> categorias = new CategoriaDAO().getAll();
	private DefaultListModel moIdiomas = new DefaultListModel();
	private DefaultListModel moAutores = new DefaultListModel();
	private DefaultListModel moCategorias = new DefaultListModel();
	private JList listCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarLibroFrame frame = new RegistrarLibroFrame();
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
	public RegistrarLibroFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Editorial> editoriales = new ArrayList<>();
				EditorialDAO daoEditorial = new EditorialDAO();
				editoriales = daoEditorial.getAll();
				
				List<EstadoLibro> estadoLibros = new ArrayList<>();
				EstadoLibroDAO daoEstadoLibro = new EstadoLibroDAO();
				estadoLibros = daoEstadoLibro.getAll();
				
				if (idiomas.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Debe haber un idoma registrado en el sistema para poder registrar un libro");
					dispose();
				}
				
				if (categorias.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Debe haber una categoría registrada en el sistema para poder registrar un libro");
					dispose();
				}		
				
				if (autores.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Debe haber un autor registrado en el sistema para poder registrar un libro");
					dispose();
				}
				
				if (editoriales.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Debe haber una editorial registrada en el sistema para poder registrar un libro");
					dispose();
				}
				
				boolean existeEstado = false;
				for (int i = 0 ; i < estadoLibros.size() ; i++) {
					if (estadoLibros.get(i).getDescripcion().equals("No disponible")) {
					comboBoxEstadoDelLibro.addItem(estadoLibros.get(i).getCodigo());
					existeEstado = true;
					break;
					}
				}
				
				if (existeEstado == false) {
					JOptionPane.showMessageDialog(null, "No existe el estado llamado 'No disponible', por lo tanto no se puede registrar un libro");
					dispose();
				}
				
				for (int i = 0 ; i < editoriales.size() ; i++) {
					comboBoxEditorial.addItem(editoriales.get(i).getCodigo());
				}
				
				for (Idioma id : idiomas) {
					comboBoxIdioma.addItem(id);
				}
				
				for (Autor au : autores) {
					comboBoxAutor.addItem(au);
				}
				
				for (Categoria ca : categorias) {
					comboBoxCategoria.addItem(ca);
				}
				
			}
		});
		setTitle("Registrar Libro");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNmeroDeSerie = new JLabel("N\u00FAmero de serie");
		lblNmeroDeSerie.setBounds(10, 33, 107, 14);
		getContentPane().add(lblNmeroDeSerie);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(10, 58, 46, 14);
		getContentPane().add(lblIsbn);
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(10, 83, 46, 14);
		getContentPane().add(lblTtulo);
		
		JLabel lblNmeroDePginas = new JLabel("N\u00FAmero de p\u00E1ginas");
		lblNmeroDePginas.setBounds(10, 108, 124, 14);
		getContentPane().add(lblNmeroDePginas);
		
		JLabel lblPrecioDeReferencia = new JLabel("Precio de referencia");
		lblPrecioDeReferencia.setBounds(10, 133, 124, 14);
		getContentPane().add(lblPrecioDeReferencia);
		
		JLabel lblFechaDePublicacion = new JLabel("Fecha de publicaci\u00F3n");
		lblFechaDePublicacion.setBounds(10, 158, 153, 14);
		getContentPane().add(lblFechaDePublicacion);
		
		textFieldFechaDePublicacion = new JTextField();
		textFieldFechaDePublicacion.setBounds(132, 155, 86, 20);
		getContentPane().add(textFieldFechaDePublicacion);
		textFieldFechaDePublicacion.setColumns(10);
		
		textFieldPrecioDeReferencia = new JTextField();
		textFieldPrecioDeReferencia.setBounds(132, 130, 86, 20);
		getContentPane().add(textFieldPrecioDeReferencia);
		textFieldPrecioDeReferencia.setColumns(10);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(132, 80, 86, 20);
		getContentPane().add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		textFieldISBN = new JTextField();
		textFieldISBN.setBounds(132, 55, 86, 20);
		getContentPane().add(textFieldISBN);
		textFieldISBN.setColumns(10);
		
		textFieldNumeroDeSerie = new JTextField();
		textFieldNumeroDeSerie.setBounds(132, 30, 86, 20);
		getContentPane().add(textFieldNumeroDeSerie);
		textFieldNumeroDeSerie.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnRegistrar.setIcon(new ImageIcon(RegistrarLibroFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnRegistrar.addActionListener(e -> registrarLibro(e));
		btnRegistrar.setBounds(559, 381, 115, 23);
		getContentPane().add(btnRegistrar);
		
		JLabel lblEstadoDelLibro = new JLabel("Estado del libro");
		lblEstadoDelLibro.setBounds(10, 183, 107, 14);
		getContentPane().add(lblEstadoDelLibro);
		
		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(10, 208, 69, 14);
		getContentPane().add(lblEditorial);
		
		comboBoxEstadoDelLibro = new JComboBox<>();
		comboBoxEstadoDelLibro.setBounds(132, 180, 86, 20);
		getContentPane().add(comboBoxEstadoDelLibro);
		
		comboBoxEditorial = new JComboBox<>();
		comboBoxEditorial.setBounds(132, 205, 86, 20);
		getContentPane().add(comboBoxEditorial);
		
		spinnerNumeroDePaginas = new JSpinner();
		spinnerNumeroDePaginas.setBounds(132, 105, 86, 20);
		getContentPane().add(spinnerNumeroDePaginas);
		
		JLabel lblNewLabel = new JLabel("(2010-05-22)");
		lblNewLabel.setBounds(228, 158, 89, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autores");
		lblNewLabel_1.setBounds(330, 33, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		comboBoxAutor = new JComboBox<Autor>();
		comboBoxAutor.setBounds(394, 30, 180, 20);
		getContentPane().add(comboBoxAutor);
		
		listAutor = new JList();
		listAutor.setBounds(394, 57, 180, 115);
		getContentPane().add(listAutor);
		
		JLabel lblCategoras = new JLabel("Categor\u00EDas");
		lblCategoras.setBounds(330, 183, 80, 14);
		getContentPane().add(lblCategoras);
		
		comboBoxCategoria = new JComboBox<Categoria>();
		comboBoxCategoria.setBounds(394, 180, 180, 20);
		getContentPane().add(comboBoxCategoria);
		
		JLabel lblNewLabel_2 = new JLabel("Idiomas");
		lblNewLabel_2.setBounds(10, 233, 58, 14);
		getContentPane().add(lblNewLabel_2);
		
		comboBoxIdioma = new JComboBox<Idioma>();
		comboBoxIdioma.setBounds(132, 230, 86, 20);
		getContentPane().add(comboBoxIdioma);
		
		listIdioma = new JList();
		listIdioma.setBounds(132, 261, 86, 115);
		getContentPane().add(listIdioma);
		
		listCategoria = new JList();
		listCategoria.setBounds(394, 207, 180, 115);
		getContentPane().add(listCategoria);
		
		JButton btnAgregarAutor = new JButton("Agregar");
		btnAgregarAutor.addActionListener(e -> agregarAutor(e));
		btnAgregarAutor.setBounds(585, 29, 89, 23);
		getContentPane().add(btnAgregarAutor);
		
		JButton btnAgregarCategoria = new JButton("Agregar");
		btnAgregarCategoria.addActionListener(e -> agregarCategoria(e));
		btnAgregarCategoria.setBounds(585, 179, 89, 23);
		getContentPane().add(btnAgregarCategoria);
		
		JButton btnAgregarIdioma = new JButton("Agregar");
		btnAgregarIdioma.addActionListener(e -> agregarIdioma(e));
		btnAgregarIdioma.setBounds(228, 229, 89, 23);
		getContentPane().add(btnAgregarIdioma);
		
		JButton btnEliminarIdioma = new JButton("Eliminar");
		btnEliminarIdioma.addActionListener(e -> eliminarIdioma(e));
		btnEliminarIdioma.setBounds(228, 261, 89, 23);
		getContentPane().add(btnEliminarIdioma);
		
		JButton btnEliminarAutor = new JButton("Eliminar");
		btnEliminarAutor.addActionListener(e -> eliminarAutor(e));
		btnEliminarAutor.setBounds(585, 58, 89, 23);
		getContentPane().add(btnEliminarAutor);
		
		JButton btnEliminarCategoria = new JButton("Eliminar");
		btnEliminarCategoria.addActionListener(e -> eliminarCategoria(e));
		btnEliminarCategoria.setBounds(585, 208, 89, 23);
		getContentPane().add(btnEliminarCategoria);

	}
	
	private void eliminarCategoria(ActionEvent e) {
		Categoria categoriaSeleccionada = (Categoria)listCategoria.getSelectedValue();
		if (categoriaSeleccionada != null) {
			comboBoxCategoria.addItem(categoriaSeleccionada);
			
			moCategorias.remove(listCategoria.getSelectedIndex());
			listCategoria.setModel(moCategorias);
		}
	}

	private void agregarCategoria(ActionEvent e) {
		Categoria categoriaSeleccionada = (Categoria)comboBoxCategoria.getSelectedItem();
		
		moCategorias.addElement(categoriaSeleccionada);
		listCategoria.setModel(moCategorias);
		
		this.comboBoxCategoria.removeItem(categoriaSeleccionada);
	}

	private void eliminarAutor(ActionEvent e) {
		Autor autorSeleccionado = (Autor)listAutor.getSelectedValue();
		if (autorSeleccionado != null) {
			comboBoxAutor.addItem(autorSeleccionado);
			
			moAutores.remove(listAutor.getSelectedIndex());
			listAutor.setModel(moAutores);
		}
	}

	private void agregarAutor(ActionEvent e) {
		Autor autorSeleccionado = (Autor)comboBoxAutor.getSelectedItem();
		
		moAutores.addElement(autorSeleccionado);
		listAutor.setModel(moAutores);
		
		this.comboBoxAutor.removeItem(autorSeleccionado);
	}

	private void eliminarIdioma(ActionEvent e) {
		Idioma idiomaSeleccionado = (Idioma)listIdioma.getSelectedValue();
		if (idiomaSeleccionado != null) {
			comboBoxIdioma.addItem(idiomaSeleccionado);
			
			moIdiomas.remove(listIdioma.getSelectedIndex());
			listIdioma.setModel(moIdiomas);
		}

	}

	private void agregarIdioma(ActionEvent e) {
		Idioma idiomaSeleccionado = (Idioma)comboBoxIdioma.getSelectedItem();
		
		moIdiomas.addElement(idiomaSeleccionado);
		listIdioma.setModel(moIdiomas);
		
		this.comboBoxIdioma.removeItem(idiomaSeleccionado);
		
	}

	public void registrarLibro(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		List<String> errores = new ArrayList<String>();
		ListModel lmoIdiomas = listIdioma.getModel();
		
		List<Idioma> idiomasSeleccionados = new ArrayList<Idioma>();
		for (int i = 0;i < lmoIdiomas.getSize(); i++) {
			idiomasSeleccionados.add((Idioma)lmoIdiomas.getElementAt(i));
		}

		if (lmoIdiomas.getSize() <= 0) {
			errores.add("No hay ningún idioma seleccionado");
		}
		
		ListModel lmoAutores = listAutor.getModel();
		
		List<Object> autoresSeleccionados = new ArrayList<Object>();
		for (int i = 0;i < lmoAutores.getSize(); i++) {
			autoresSeleccionados.add(lmoAutores.getElementAt(i));
		}

		if (lmoAutores.getSize() <= 0) {
			errores.add("No hay ningún autor seleccionado");
		}
		
		ListModel lmoCategorias = listCategoria.getModel();
		
		List<Categoria> categoriasSeleccionadas = new ArrayList<Categoria>();
		for(int i = 0; i < lmoCategorias.getSize(); i++) {
			categoriasSeleccionadas.add((Categoria)lmoCategorias.getElementAt(i));
		}

		if (lmoCategorias.getSize() <= 0) {
			errores.add("No hay ninguna categoría seleccionada");
		}
		
		int numeroDeSerie = 0;
		try {
			numeroDeSerie = Integer.parseInt(this.textFieldNumeroDeSerie.getText().trim());
		} catch(Exception ex) {
			errores.add("Numero de serie no valido");
		}
		
		String isbn = this.textFieldISBN.getText().trim();
		if (isbn.isEmpty()) {
			errores.add("ISBN no valido");
		}
		
		String titulo = this.textFieldTitulo.getText().trim();
		if (titulo.isEmpty()) {
			errores.add("Título no valido");
		}
		
		int numeroDePaginas = 0;
		try {
			numeroDePaginas = Integer.parseInt(this.spinnerNumeroDePaginas.getValue().toString().trim());
		} catch(Exception ex) {
			errores.add("Número de páginas no valido");
		}
		
		double precioDeReferencia = 0;
		try {
			precioDeReferencia = Double.parseDouble(this.textFieldPrecioDeReferencia.getText().trim());
			if (precioDeReferencia <= 0) {
				errores.add("Precio de referencia menor o igual a 0");
			}
		}catch (Exception ex) {
			errores.add("Precio de referencia no valido");
		}
		
		String fechaDePublicacion = this.textFieldFechaDePublicacion.getText().trim();
		if(new FechaGringaUtil().validarFechaGringa(fechaDePublicacion) == false) {
			errores.add("Formato de fecha no valido");
		}
		
		String estadoLibroFK = this.comboBoxEstadoDelLibro.getSelectedItem().toString();
		String editorialFK = this.comboBoxEditorial.getSelectedItem().toString();
		
		if (errores.isEmpty() == false) {
			String mensaje = "";
			for (int i = 0 ; i < errores.size() ; i++) {
				mensaje+="\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validación", JOptionPane.WARNING_MESSAGE);
		}
		
		if (errores.isEmpty()) {
			Libro li = new Libro();
			li.setNumeroDeSerie(numeroDeSerie);
			li.setIsbn(isbn);
			li.setTitulo(titulo);
			li.setNumeroDePaginas(numeroDePaginas);
			li.setPrecioDeReferencia(precioDeReferencia);
			li.setFechaDePublicacion(fechaDePublicacion);
			li.setEstadoLibroFK(estadoLibroFK);
			li.setEditorialFK(editorialFK);
			
			LibroDAO daoLibros = new LibroDAO();
			if (daoLibros.save(li) == true) {
				IdiomaLibroDAO daoIdiomaLibro = new IdiomaLibroDAO();
				daoIdiomaLibro.save(idiomasSeleccionados, numeroDeSerie);
				
				AutorLibroDAO daoAutorLibro = new AutorLibroDAO();
				daoAutorLibro.save(autoresSeleccionados, numeroDeSerie);
				
				CategoriaLibroDAO daoCategoriaLibro = new CategoriaLibroDAO();
				daoCategoriaLibro.save(categoriasSeleccionadas, numeroDeSerie);
				
				JOptionPane.showMessageDialog(null, "Libro registrado correctamente");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "El número de serie ya fue ingresado anteriormente, ingrese uno diferente", "Error DB", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
