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
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.DistribuidorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Cliente;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Distribuidor;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BuscarDistribuidorFrame extends JInternalFrame {
	private JTextField textFieldDistribuidor;
	private JTable tableDistribuidor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarDistribuidorFrame frame = new BuscarDistribuidorFrame();
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
	public BuscarDistribuidorFrame() {
		setTitle("Buscar Distribuidor");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rut");
		lblNewLabel.setBounds(10, 33, 32, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldDistribuidor = new JTextField();
		textFieldDistribuidor.setBounds(31, 30, 86, 20);
		getContentPane().add(textFieldDistribuidor);
		textFieldDistribuidor.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBuscar.setIcon(new ImageIcon(BuscarDistribuidorFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/find.png")));
		btnBuscar.addActionListener(e -> buscarDistribuidor(e));
		btnBuscar.setBounds(127, 29, 115, 23);
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 664, 346);
		getContentPane().add(scrollPane);
		
		tableDistribuidor = new JTable();
		tableDistribuidor.setEnabled(false);
		scrollPane.setViewportView(tableDistribuidor);
		
		DefaultTableModel mo = new DefaultTableModel();
		
		String nombreColumnas[]= {"Rut", "Nombre", "Pa�s", "Comuna", "Calle", "N�mero", "Tel�fono", "A�os de servicio"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		tableDistribuidor.setModel(mo);
	}

	private void buscarDistribuidor(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		DefaultTableModel mo = new DefaultTableModel();
		String clavePrimariaDistribuidor = this.textFieldDistribuidor.getText();
		List<Distribuidor> distribuidor = new DistribuidorDAO().filtrarDistribuidor(clavePrimariaDistribuidor);
		
		String nombreColumnas[]= {"Rut", "Nombre", "Pa�s", "Comuna", "Calle", "N�mero", "Tel�fono", "A�os de servicio"};
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			mo.addColumn(nombreColumnas[i]);
			
		}
		
		try {
			
			Object []fila = new Object[8];
			
			fila[0] = distribuidor.get(0).getRut();
			fila[1] = distribuidor.get(0).getNombre();
			fila[2] = distribuidor.get(0).getPais();
			fila[3] = distribuidor.get(0).getComuna();
			fila[4] = distribuidor.get(0).getCalle();
			fila[5] = distribuidor.get(0).getNumero();
			fila[6] = distribuidor.get(0).getTelefono();
			fila[7] = distribuidor.get(0).getYearServicio();
			
			mo.addRow(fila);
			tableDistribuidor.setModel(mo);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "El distribuidor no existe en el sistema");
		}
	}
	
}
