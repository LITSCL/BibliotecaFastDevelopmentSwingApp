package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.LibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Libro;

public class MostrarLibroFrame extends JInternalFrame {
	private JTable tableMostrarLibros;
	private List<Libro> libros = new ArrayList<Libro>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarLibroFrame frame = new MostrarLibroFrame();
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
	public MostrarLibroFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				libros = new LibroDAO().getAll();
				
				if (libros.isEmpty() == false) {
					cargarTabla();
				}
				else {
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null, "No hay libros registrados en el sistema");
					dispose();
				}
				
			}

		});
		setTitle("Mostrar Libros");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 664, 393);
		getContentPane().add(scrollPane);
		
		tableMostrarLibros = new JTable();
		tableMostrarLibros.setEnabled(false);
		scrollPane.setViewportView(tableMostrarLibros);

	}

	private void cargarTabla() {
		DefaultTableModel mo = new DefaultTableModel();
		
		mo.addColumn("Número de serie");
		mo.addColumn("ISBN");
		mo.addColumn("Titulo");
		mo.addColumn("Número de páginas");
		mo.addColumn("Precio de referencia");
		mo.addColumn("Fecha de publicación");
		mo.addColumn("Código de estado");
		mo.addColumn("Código de editorial");
		
		for (Libro li : libros) {
			Object filas[] = new Object[8];
			filas[0] = li.getNumeroDeSerie();
			filas[1] = li.getIsbn();
			filas[2] = li.getTitulo();
			filas[3] = li.getNumeroDePaginas();
			filas[4] = li.getPrecioDeReferencia();
			filas[5] = li.getFechaDePublicacion();
			filas[6] = li.getEstadoLibroFK();
			filas[7] = li.getEditorialFK();
			
			mo.addRow(filas);
		}
		
		this.tableMostrarLibros.setModel(mo);
	}

}
