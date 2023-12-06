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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.TrabajadorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Trabajador;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuscarTrabajadorFrame extends JInternalFrame {
	private JTextField textFieldTrabajador;
	private JTable tableTrabajador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarTrabajadorFrame frame = new BuscarTrabajadorFrame();
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
	public BuscarTrabajadorFrame() {
		setTitle("Buscar Trabajador");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rut");
		lblNewLabel.setBounds(10, 33, 32, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldTrabajador = new JTextField();
		textFieldTrabajador.setBounds(31, 30, 86, 20);
		getContentPane().add(textFieldTrabajador);
		textFieldTrabajador.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBuscar.setIcon(new ImageIcon(BuscarTrabajadorFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/find.png")));
		btnBuscar.addActionListener(e -> buscarTrabajador(e));
		btnBuscar.setBounds(127, 29, 115, 23);
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 664, 346);
		getContentPane().add(scrollPane);
		
		tableTrabajador = new JTable();
		tableTrabajador.setEnabled(false);
		scrollPane.setViewportView(tableTrabajador);
		
		DefaultTableModel mo = new DefaultTableModel();
		
		String nombreColumnas[]= {"Rut", "Nombre", "Apellido paterno", "Apellido materno", "Fecha de contrato"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		tableTrabajador.setModel(mo);
	}

	private void buscarTrabajador(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		DefaultTableModel mo = new DefaultTableModel();
		String clavePrimariaTrabajador = this.textFieldTrabajador.getText();
		List<Trabajador> trabajador = new TrabajadorDAO().filtrarTrabajador(clavePrimariaTrabajador);
		
		String nombreColumnas[] = {"Rut", "Nombre", "Apellido paterno", "Apellido materno", "Fecha de contrato"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		try {
			
			Object []fila = new Object[5];
			
			fila[0] = trabajador.get(0).getRut();
			fila[1] = trabajador.get(0).getNombre();
			fila[2] = trabajador.get(0).getApellidoPaterno();
			fila[3] = trabajador.get(0).getApellidoMaterno();
			fila[4] = trabajador.get(0).getFechaDeContrato();
			
			mo.addRow(fila);
			tableTrabajador.setModel(mo);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "El trabajador no existe en el sistema");
		}
	}
	
}
