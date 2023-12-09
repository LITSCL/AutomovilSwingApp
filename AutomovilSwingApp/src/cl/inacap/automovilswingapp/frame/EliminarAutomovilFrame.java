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

	//Proceso de creaci�n del frame.
	public EliminarAutomovilFrame() {
		setTitle("Eliminar Automovil"); //Es es el titulo del frame, se muestra al lado del icono cuando se ejecuta el programa. 
		setClosable(true);
		
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			//Todo este c�digo se ejecuta antes de que se abra el JInternalFrame (Momento ideal para cargar el JComboBox).
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Automovil> automovilesIngresados = daoAutomovil.getAll(); //Aca se trae la lista de automoviles.
				
				if (automovilesIngresados.isEmpty() == true) { //Aca se revisa si hay automoviles en la lista, de no ser as� se ejecuta el c�digo.
					JOptionPane.showMessageDialog(null, "No hay automoviles registrados en el sistema"); //El primer par�metro corresponde a la ubicac�n del mensaje y el segundo par�metro corresponde al texto del mensaje.
					dispose(); //Esta instrucci�n cierra la ventana (JInternal Frame llamado (Eliminar Automovil)).
				}
				else { //Si la lista tiene automoviles, entonces se ejecuta �ste c�digo.
					for (Automovil i:automovilesIngresados) { //Aca se recorre la lista de automoviles.
						comboBoxSeleccionarAutomovilEliminar.addItem(i); //Se a�aden los objetos de tipo Automovil que est�n almacenados en la lista al JComboBox.
					}
				}
			}
		});
				
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		comboBoxSeleccionarAutomovilEliminar = new JComboBox<>();
		comboBoxSeleccionarAutomovilEliminar.setBounds(10, 75, 664, 20);
		getContentPane().add(comboBoxSeleccionarAutomovilEliminar);
		
		//Aca se crea el bot�n llamado (Eliminar).
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(e -> eliminarAutomovil()); //Aca se a�ade el listener al bot�n, el cual llama al m�todo llamado eliminarAutomovil().
		btnEliminar.setBounds(294, 106, 89, 23);
		getContentPane().add(btnEliminar);
		
		JLabel lblSeleccioneAutomovilA = new JLabel("Seleccione Automovil a Eliminar");
		lblSeleccioneAutomovilA.setBounds(243, 50, 211, 14);
		getContentPane().add(lblSeleccioneAutomovilA);

	}

	//Codigo a ejecutar cuando se pulsa el boton Eliminar.
	private Object eliminarAutomovil() {
		int respuesta = JOptionPane.showConfirmDialog(null, "�Seguro que desea eliminar el estudiante?","Eliminar Estudiante", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); //El primer par�metro indica el mensaje de la ventana, el segundo par�metro indica el nombre de la ventana, el tercer par�metro indica el tipo de opci�n y el cuarto par�metro indica el icono del tipo de mensaje (Esta instrucci�n retorna un dato de tipo int (0=Si/1=No)).
		
		if (respuesta == JOptionPane.YES_OPTION) { //Aca se consulta si la variable respuesta es igual a 1 (En otras palabras si el usuario dio click a "YES" en el bot�n de la ventana).
			Automovil eliminado = (Automovil)comboBoxSeleccionarAutomovilEliminar.getSelectedItem(); //la variable eliminada almacena lo que est� seleccionado en el JCombobox (Se esta haciendo down casting sinonimo de refundici�n de dato).
			daoAutomovil.delete(eliminado); //Aca se elimina de la lista el automovil que est� seleccionado en el JCombobox.
			JOptionPane.showMessageDialog(null, "Automovil Eliminado"); //Aca se muestra el mensaje cuando se elimina un Automovil.
			this.dispose(); //Esta instrucci�n cierra la ventana (JInternal Frame llamado (Eliminar Automovil)).
		}
		return null;
	}
}
