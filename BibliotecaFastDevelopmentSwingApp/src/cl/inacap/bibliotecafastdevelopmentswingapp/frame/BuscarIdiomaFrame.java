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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.IdiomaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Idioma;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BuscarIdiomaFrame extends JInternalFrame {
	private JTextField textFieldIdioma;
	private JTable tableIdioma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarIdiomaFrame frame = new BuscarIdiomaFrame();
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
	public BuscarIdiomaFrame() {
		setTitle("Buscar Idioma");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(10, 33, 61, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldIdioma = new JTextField();
		textFieldIdioma.setBounds(51, 30, 86, 20);
		getContentPane().add(textFieldIdioma);
		textFieldIdioma.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBuscar.setIcon(new ImageIcon(BuscarIdiomaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/find.png")));
		btnBuscar.addActionListener(e -> buscarIdioma(e));
		btnBuscar.setBounds(147, 29, 115, 23);
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 664, 346);
		getContentPane().add(scrollPane);
		
		tableIdioma = new JTable();
		tableIdioma.setEnabled(false);
		scrollPane.setViewportView(tableIdioma);
		
		DefaultTableModel mo = new DefaultTableModel();
		
		String nombreColumnas[] = {"C�digo", "Nombre"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		tableIdioma.setModel(mo);
	}

	private void buscarIdioma(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		DefaultTableModel mo = new DefaultTableModel();
		String clavePrimariaIdioma = this.textFieldIdioma.getText();
		List<Idioma> idioma = new IdiomaDAO().filtrarIdioma(clavePrimariaIdioma);
		
		String nombreColumnas[] = {"C�digo", "Nombre"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		try {
			
			Object []fila = new Object[2];
			
			fila[0] = idioma.get(0).getCodigo();
			fila[1] = idioma.get(0).getNombre();
			
			mo.addRow(fila);
			tableIdioma.setModel(mo);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "El idioma no existe en el sistema");
		}
	}
	
}
