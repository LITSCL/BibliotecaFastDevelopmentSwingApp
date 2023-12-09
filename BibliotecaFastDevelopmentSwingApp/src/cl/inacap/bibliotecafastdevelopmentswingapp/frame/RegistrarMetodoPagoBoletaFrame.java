package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.MetodoPagoBoletaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.MetodoPagoBoleta;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarMetodoPagoBoletaFrame extends JInternalFrame {
	private JTextField textFieldCodigo;
	private JTextArea textAreaDescripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarMetodoPagoBoletaFrame frame = new RegistrarMetodoPagoBoletaFrame();
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
	public RegistrarMetodoPagoBoletaFrame() {
		setTitle("Registrar M\u00E9todo de Pago");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(10, 33, 61, 14);
		getContentPane().add(lblCdigo);
		
		JLabel lblNewLabel = new JLabel("Descripci\u00F3n");
		lblNewLabel.setBounds(10, 58, 75, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(81, 30, 86, 20);
		getContentPane().add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnNewButton.setIcon(new ImageIcon(RegistrarMetodoPagoBoletaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnNewButton.addActionListener(e -> registrarMetodoPagoBoleta(e));
		btnNewButton.setBounds(559, 381, 115, 23);
		getContentPane().add(btnNewButton);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBounds(81, 53, 273, 95);
		getContentPane().add(textAreaDescripcion);

	}

	private void registrarMetodoPagoBoleta(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		List<String> errores = new ArrayList<String>();
		
		String codigo = this.textFieldCodigo.getText().trim();
		if (codigo.isEmpty()) {
			errores.add("Código no valido");
		}
		
		String descripcion = this.textAreaDescripcion.getText().trim();
		if (descripcion.isEmpty()) {
			errores.add("Descripción no valida");
		}
		
		if (errores.isEmpty() == false) {
			String mensaje = "";
			for (int i = 0 ; i < errores.size() ; i++) {
				mensaje+= "\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validación", JOptionPane.WARNING_MESSAGE);
		}
		
		if (errores.isEmpty()) {
			MetodoPagoBoleta mpb = new MetodoPagoBoleta();
			mpb.setCodigo(codigo);
			mpb.setDescripcion(descripcion);
			
			if (new MetodoPagoBoletaDAO().save(mpb) == true) {
				JOptionPane.showMessageDialog(null, "Método de pago registrado correctamente");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "El código ya fue ingresado anteriormente, ingrese uno diferente", "Error DB", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
