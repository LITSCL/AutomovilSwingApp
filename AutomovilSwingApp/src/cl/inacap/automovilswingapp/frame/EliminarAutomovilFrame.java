package cl.inacap.automovilswingapp.frame;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import cl.inacap.automovilswingappmodelo.dao.AutomovilDAO;
import cl.inacap.automovilswingappmodelo.dto.Automovil;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class EliminarAutomovilFrame extends JInternalFrame {
	private JComboBox<Automovil> comboBoxSeleccionarAutomovilEliminar;
	private JButton btnEliminar;
	private AutomovilDAO daoAutomovil = new AutomovilDAO();

	//Proceso de creación del frame.
	public EliminarAutomovilFrame() {
		setTitle("Eliminar Automovil"); //Es es el titulo del frame, se muestra al lado del icono cuando se ejecuta el programa. 
		setClosable(true);
		
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			//Todo este código se ejecuta antes de que se abra el JInternalFrame (Momento ideal para cargar el JComboBox).
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Automovil> automovilesIngresados = daoAutomovil.getAll(); //Aca se trae la lista de automoviles.
				
				if (automovilesIngresados.isEmpty() == true) { //Aca se revisa si hay automoviles en la lista, de no ser así se ejecuta el código.
					JOptionPane.showMessageDialog(null, "No hay automoviles registrados en el sistema"); //El primer parámetro corresponde a la ubicación del mensaje y el segundo parámetro corresponde al texto del mensaje.
					dispose(); //Esta instrucción cierra la ventana (JInternal Frame llamado (Eliminar Automovil)).
				}
				else { //Si la lista tiene automoviles, entonces se ejecuta éste código.
					for (Automovil i:automovilesIngresados) { //Aca se recorre la lista de automoviles.
						comboBoxSeleccionarAutomovilEliminar.addItem(i); //Se añaden los objetos de tipo Automovil que están almacenados en la lista al JComboBox.
					}
				}
			}
		});
				
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		comboBoxSeleccionarAutomovilEliminar = new JComboBox<>();
		comboBoxSeleccionarAutomovilEliminar.setBounds(10, 75, 664, 20);
		getContentPane().add(comboBoxSeleccionarAutomovilEliminar);
		
		//Aca se crea el botón llamado (Eliminar).
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(e -> eliminarAutomovil()); //Aca se añade el listener al botón, el cual llama al método llamado eliminarAutomovil().
		btnEliminar.setBounds(294, 106, 89, 23);
		getContentPane().add(btnEliminar);
		
		JLabel lblSeleccioneAutomovilA = new JLabel("Seleccione Automovil a Eliminar");
		lblSeleccioneAutomovilA.setBounds(243, 50, 211, 14);
		getContentPane().add(lblSeleccioneAutomovilA);
	}

	//Codigo a ejecutar cuando se pulsa el boton Eliminar.
	private Object eliminarAutomovil() {
		int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el estudiante?","Eliminar Estudiante", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); //El primer parámetro indica el mensaje de la ventana, el segundo parámetro indica el nombre de la ventana, el tercer parámetro indica el tipo de opción y el cuarto parámetro indica el icono del tipo de mensaje (Esta instrucción retorna un dato de tipo int (0=Si/1=No)).
		
		if (respuesta == JOptionPane.YES_OPTION) { //Aca se consulta si la variable respuesta es igual a 1 (En otras palabras si el usuario dio click a "YES" en el botón de la ventana).
			Automovil eliminado = (Automovil)comboBoxSeleccionarAutomovilEliminar.getSelectedItem(); //la variable eliminada almacena lo que está seleccionado en el JCombobox (Se esta haciendo down casting sinonimo de refundición de dato).
			daoAutomovil.delete(eliminado); //Aca se elimina de la lista el automovil que está seleccionado en el JCombobox.
			JOptionPane.showMessageDialog(null, "Automovil Eliminado"); //Aca se muestra el mensaje cuando se elimina un Automovil.
			this.dispose(); //Esta instrucción cierra la ventana (JInternal Frame llamado (Eliminar Automovil)).
		}
		return null;
	}
}
