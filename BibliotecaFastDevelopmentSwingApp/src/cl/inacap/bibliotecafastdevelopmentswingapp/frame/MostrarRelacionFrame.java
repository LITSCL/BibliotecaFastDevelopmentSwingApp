package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CompraLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.VentaLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingapp.graphic.PanelFondo;
import cl.inacap.bibliotecafastdevelopmentswingapp.graphic.PanelRelacionLibros;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class MostrarRelacionFrame extends JInternalFrame {
	private JPanel fondo = new PanelFondo(664,30);
	private JPanel graficoLibros = new PanelRelacionLibros();
	private CompraLibroDAO daoCompraLibro = new CompraLibroDAO();
	private VentaLibroDAO daoVentaLibro = new VentaLibroDAO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarRelacionFrame frame = new MostrarRelacionFrame();
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
	public MostrarRelacionFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
					setTitle("Mostrar Relacion");
					setClosable(true);
					setBounds(100, 100, 700, 445);
					getContentPane().setLayout(null);
					
					graficoLibros.setBounds(10, 11, 664, 332); //Esta instruccion posisiona el panel y le da dimensiones.
					graficoLibros.setOpaque(false);
					getContentPane().add(graficoLibros);
					graficoLibros.setLayout(null);
					
					JLabel lblCantidadComprados = new JLabel(Integer.toString(new PanelRelacionLibros().getDatosLibros()[1]));
					lblCantidadComprados.setBounds(90, 318-(new PanelRelacionLibros().getDatosLibros()[0])-10, 48, 14);
					graficoLibros.add(lblCantidadComprados);
					
					JLabel lblCantidadVendidos = new JLabel(Integer.toString(new PanelRelacionLibros().getDatosLibros()[3]));
					lblCantidadVendidos.setBounds(390, 318-(new PanelRelacionLibros().getDatosLibros()[2])-10, 46, 14);
					graficoLibros.add(lblCantidadVendidos);
					
					fondo.setBounds(10, 11, 664, 332);
					getContentPane().add(fondo);
					
					JLabel lblNewLabel = new JLabel("Comprados");
					lblNewLabel.setBounds(70, 354, 113, 14);
					getContentPane().add(lblNewLabel);
					
					JLabel lblNewLabel_1 = new JLabel("Vendidos");
					lblNewLabel_1.setBounds(379, 354, 82, 14);
					getContentPane().add(lblNewLabel_1);		
			}
		});
	}
	
}
