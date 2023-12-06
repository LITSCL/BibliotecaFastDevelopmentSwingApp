package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.ClienteDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Cliente;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarClienteFrame extends JInternalFrame {
	private JComboBox comboBoxCliente;
	private List<Cliente> clientes = new ArrayList<Cliente>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarClienteFrame frame = new BorrarClienteFrame();
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
	public BorrarClienteFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				clientes = new ClienteDAO().getAll();
				
				if (clientes.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay clientes registrados en el sistema");
					dispose();
				}
				else {
					for (Cliente cl : clientes) {
						comboBoxCliente.addItem(cl.toString());
					}
				}

				
			}
		});
		setTitle("Borrar Cliente");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el cliente que desea borrar");
		lblNewLabel.setBounds(233, 32, 238, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxCliente = new JComboBox();
		comboBoxCliente.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxCliente);
		
		JButton btnBorrarCliente = new JButton("Borrar");
		btnBorrarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrarCliente.setIcon(new ImageIcon(BorrarClienteFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrarCliente.setBounds(289, 89, 115, 23);
		btnBorrarCliente.addActionListener(e -> borrarCliente(e));
		getContentPane().add(btnBorrarCliente);
		
	}

	private void borrarCliente(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		int clienteSeleccionado = this.comboBoxCliente.getSelectedIndex();
		new ClienteDAO().delete(clientes.get(clienteSeleccionado));
		
		this.dispose();
	}

}
