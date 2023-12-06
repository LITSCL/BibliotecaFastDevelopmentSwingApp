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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.ClienteDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Cliente;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BuscarClienteFrame extends JInternalFrame {
	private JTextField textFieldCliente;
	private JTable tableCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarClienteFrame frame = new BuscarClienteFrame();
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
	public BuscarClienteFrame() {
		setTitle("Buscar Cliente");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rut");
		lblNewLabel.setBounds(10, 33, 32, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setBounds(31, 30, 86, 20);
		getContentPane().add(textFieldCliente);
		textFieldCliente.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBuscar.setIcon(new ImageIcon(BuscarClienteFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/find.png")));
		btnBuscar.addActionListener(e -> buscarCliente(e));
		btnBuscar.setBounds(127, 29, 115, 23);
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 664, 346);
		getContentPane().add(scrollPane);
		
		tableCliente = new JTable();
		tableCliente.setEnabled(false);
		scrollPane.setViewportView(tableCliente);
		
		DefaultTableModel mo = new DefaultTableModel();
		
		String nombreColumnas[]= {"Rut", "Nombre", "Apellido paterno", "Apellido materno", "Fecha de nacimiento"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		tableCliente.setModel(mo);
	}

	private void buscarCliente(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		DefaultTableModel mo = new DefaultTableModel();
		String clavePrimariaCliente = this.textFieldCliente.getText();
		List<Cliente> cliente = new ClienteDAO().filtrarCliente(clavePrimariaCliente);
		
		String nombreColumnas[]= {"Rut", "Nombre", "Apellido paterno", "Apellido materno", "Fecha de nacimiento"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		try {
			
			Object []fila = new Object[5];
			
			fila[0] = cliente.get(0).getRut();
			fila[1] = cliente.get(0).getNombre();
			fila[2] = cliente.get(0).getApellidoPaterno();
			fila[3] = cliente.get(0).getApellidoMaterno();
			fila[4] = cliente.get(0).getFechaDeNacimiento();
			
			mo.addRow(fila);
			tableCliente.setModel(mo);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "El cliente no existe en el sistema");
		}
	}
	
}
