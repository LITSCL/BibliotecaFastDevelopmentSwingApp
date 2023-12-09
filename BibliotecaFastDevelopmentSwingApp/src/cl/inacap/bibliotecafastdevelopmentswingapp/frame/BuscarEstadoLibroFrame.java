package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EstadoLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.IdiomaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.EstadoLibro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Idioma;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BuscarEstadoLibroFrame extends JInternalFrame {
	private JTextField textFieldIEstadoLibro;
	private JTable tableEstadoLibro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarEstadoLibroFrame frame = new BuscarEstadoLibroFrame();
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
	public BuscarEstadoLibroFrame() {
		setTitle("Buscar Estado");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(10, 33, 61, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldIEstadoLibro = new JTextField();
		textFieldIEstadoLibro.setBounds(51, 30, 86, 20);
		getContentPane().add(textFieldIEstadoLibro);
		textFieldIEstadoLibro.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBuscar.setIcon(new ImageIcon(BuscarEstadoLibroFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/find.png")));
		btnBuscar.addActionListener(e -> buscarEstadoLibro(e));
		btnBuscar.setBounds(147, 29, 115, 23);
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 664, 346);
		getContentPane().add(scrollPane);
		
		tableEstadoLibro = new JTable();
		tableEstadoLibro.setEnabled(false);
		scrollPane.setViewportView(tableEstadoLibro);
		
		DefaultTableModel mo = new DefaultTableModel();
		
		String nombreColumnas[] = {"Código","Nombre"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		tableEstadoLibro.setModel(mo);
	}

	private void buscarEstadoLibro(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		DefaultTableModel mo = new DefaultTableModel();
		String clavePrimariaEstadoLibro = this.textFieldIEstadoLibro.getText();
		List<EstadoLibro> estadoLibro = new EstadoLibroDAO().filtrarEstadoLibro(clavePrimariaEstadoLibro);
		
		String nombreColumnas[] = {"Código", "Nombre"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
		}
		
		try {
			
			Object []fila = new Object[2];
			
			fila[0] = estadoLibro.get(0).getCodigo();
			fila[1] = estadoLibro.get(0).getDescripcion();
			
			mo.addRow(fila);
			tableEstadoLibro.setModel(mo);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "El estado no existe en el sistema");
		}
	}
	
}
