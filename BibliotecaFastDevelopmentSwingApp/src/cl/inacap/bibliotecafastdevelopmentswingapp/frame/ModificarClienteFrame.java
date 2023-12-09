package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.ClienteDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Cliente;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.FechaGringaUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarClienteFrame extends JInternalFrame {
	private JTextField textFieldModificar;
	private JList listClientes;
	private JButton btnModificar;
	private JComboBox<String> comboBoxAtributos;
	private List<Cliente> clientes = new ArrayList<Cliente>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarClienteFrame frame = new ModificarClienteFrame();
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
	public ModificarClienteFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				
				clientes = new ClienteDAO().getAll();
				if (clientes.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay clientes registrados en el sistema");
					dispose();
				}
				else {
					DefaultListModel mo = new DefaultListModel();
					
					for (Cliente cl : clientes) {
						mo.addElement(cl.toString());
					}
					
					listClientes.setModel(mo);
					
					comboBoxAtributos.addItem("Nombre");
					comboBoxAtributos.addItem("Apellido paterno");	
					comboBoxAtributos.addItem("Apellido materno");	
					comboBoxAtributos.addItem("Fecha de naciemiento");	
					
				}
			}
		});
		setTitle("Modificar Cliente");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		textFieldModificar = new JTextField();
		textFieldModificar.setBounds(10, 33, 127, 20);
		getContentPane().add(textFieldModificar);
		textFieldModificar.setColumns(10);
		
		comboBoxAtributos = new JComboBox<String>();
		comboBoxAtributos.setBounds(147, 33, 127, 20);
		getContentPane().add(comboBoxAtributos);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnModificar.setIcon(new ImageIcon(ModificarClienteFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/set.png")));
		btnModificar.addActionListener(e -> modificarCliente(e));
		btnModificar.setBounds(284, 32, 115, 23);
		getContentPane().add(btnModificar);
		
		listClientes = new JList();
		listClientes.setBounds(10, 67, 664, 337);
		getContentPane().add(listClientes);

	}

	private void modificarCliente(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		try {
			boolean validado = false;
			String mensaje = "";
			Cliente clienteSeleccionado = clientes.get(listClientes.getSelectedIndex());
			String atributoSeleccionado = (String)comboBoxAtributos.getSelectedItem();
			String valorModificado = textFieldModificar.getText();
			
			switch (atributoSeleccionado) {
			case "Nombre":
				clienteSeleccionado.setNombre(valorModificado);
				if(clienteSeleccionado.getNombre().trim().isEmpty() == false) {
					validado = true;
				}
				else {
					mensaje = "- Nombre no valido";
				}
				break;
			case "Apellido paterno":
				clienteSeleccionado.setApellidoPaterno(valorModificado);
				if (clienteSeleccionado.getApellidoPaterno().trim().isEmpty() == false) {
					validado = true;
				} 
				else {
					mensaje = "- Apellido paterno no valido";
				}
				break;
			case "Apellido materno":
				clienteSeleccionado.setApellidoMaterno(valorModificado);
				if (clienteSeleccionado.getApellidoMaterno().trim().isEmpty() == false) {
					validado = true;
				} 
				else {
					mensaje = "- Apellido materno no valido";
				}
				break;
			case "Fecha de naciemiento":
				clienteSeleccionado.setFechaDeNacimiento(valorModificado);
				if (new FechaGringaUtil().validarFechaGringa(clienteSeleccionado.getFechaDeNacimiento())) {
					validado = true;
				}
				else {
					mensaje = "- Fecha no valida";
				}
				break;
			}
			
			if (validado) {
				new ClienteDAO().update(clienteSeleccionado);
				JOptionPane.showMessageDialog(null, "cliente modificado correctamente");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, mensaje, "Error de validación", JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "- No a seleccionado ningún cliente", "Error de validación", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
