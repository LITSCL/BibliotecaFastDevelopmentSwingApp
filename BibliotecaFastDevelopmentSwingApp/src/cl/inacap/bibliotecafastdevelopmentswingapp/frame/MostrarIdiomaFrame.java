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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.IdiomaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Idioma;

public class MostrarIdiomaFrame extends JInternalFrame {
	private JTable tableMostrarIdiomas;
	private List<Idioma> idiomas = new ArrayList<Idioma>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarIdiomaFrame frame = new MostrarIdiomaFrame();
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
	public MostrarIdiomaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				IdiomaDAO daoIdioma = new IdiomaDAO();
				idiomas = daoIdioma.getAll();
				
				if(idiomas.isEmpty() == false) {
					cargarTabla();
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay idiomas registrados en el sistema");
					dispose();
				}
				
			}

		});
		setTitle("Mostrar Idiomas");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 664, 393);
		getContentPane().add(scrollPane);
		
		tableMostrarIdiomas = new JTable();
		tableMostrarIdiomas.setEnabled(false);
		scrollPane.setViewportView(tableMostrarIdiomas);

	}
	
	private void cargarTabla() {
		DefaultTableModel mo = new DefaultTableModel();
		
		mo.addColumn("Código");
		mo.addColumn("Nombre");
		
		for (Idioma id: idiomas) {
			Object []fila = new Object[2];
			fila[0] = id.getCodigo();
			fila[1] = id.getNombre();
			
			mo.addRow(fila);
		}
		
		this.tableMostrarIdiomas.setModel(mo);
		
	}
}
