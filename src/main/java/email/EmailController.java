package email;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

enum Tipo {
	EXITO, ERROR
}

public class EmailController implements Initializable {
	
	private EmailModel model = new EmailModel();
	private Task<Void> task;
	
	private String errorMSG = "";
	
	@FXML
	private GridPane view;
	
	@FXML
	private CheckBox conexionSSL;
	
	@FXML 
	private TextField nombreServidor;
	
	@FXML
	private TextField puerto;
	
	@FXML
	private TextField emailRemitente;
	
	@FXML
	private PasswordField passRemitente;
	
	@FXML
	private TextField emailDestinatario;
	
	@FXML
	private TextField asunto;

	@FXML
	private TextArea mensaje;
	
	@FXML
	private Button vaciarButton;
	
	@FXML
	private Button enviarButton;
	
	@FXML
	private Button cerrarButton;
	
	public EmailController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Bindings
		
		// Model antes que el view
		model.nombreServidorProperty().bind(nombreServidor.textProperty());
		puerto.textProperty().bindBidirectional(model.puertoProperty(), new NumberStringConverter());
		model.conexionSSLProperty().bind(conexionSSL.selectedProperty());
		model.emailRemitenteProperty().bind(emailRemitente.textProperty());
		model.passwordProperty().bind(passRemitente.textProperty());
		model.emailDestinatarioProperty().bind(emailDestinatario.textProperty());
		model.asuntoEmailProperty().bind(asunto.textProperty());
		model.mensajeEmailProperty().bind(mensaje.textProperty());
		
	}
	
	@FXML
	public void onEnviarAction(ActionEvent e) throws EmailException {
		if(checkInputs()) {
			enviarEmail();
		} else {
			enviarPanel(Tipo.ERROR);
		}
	}
	
	@FXML
	public void onVaciarAction(ActionEvent e) {
		clear();
	}
	
	@FXML
	public void onCerrarAction(ActionEvent e) {
		EmailApp.primaryStage.close();
	}
	
	private void enviarEmail() {
		task = new Task<>() {

			@Override
			protected Void call() throws Exception {
				Email email = new SimpleEmail();
				if(checkInputs()) {
					email.setHostName(model.getNombreServidor());
					email.setSmtpPort(model.getPuerto());
					email.setAuthenticator(new DefaultAuthenticator(model.getEmailRemitente(), model.getPassword()));
					email.setSSLOnConnect(model.enabledConexionSSL());
					email.setFrom(model.getEmailRemitente());
					email.setSubject(model.getAsuntoEmail());
					email.setMsg(model.getMensajeEmail());
					email.addTo(model.getEmailDestinatario());
					email.send();
				}
				return null;
			}
			
		};
		
		task.setOnSucceeded(e -> enviarPanel(Tipo.EXITO));
		task.setOnFailed(e -> enviarPanel(Tipo.ERROR));
		
		new Thread(task).start();
		
	}
	
	private void enviarPanel(Tipo tipo) {
		Alert a;
		Stage alertStage;
		if(tipo == Tipo.EXITO) {
			errorMSG = "";
			a = new Alert(AlertType.INFORMATION);
			a.setTitle("Mensaje enviado");
			a.setHeaderText("Mensaje enviado con éxito a '" + model.getEmailDestinatario() + "'");
		} else {
			a = new Alert(AlertType.ERROR);
			a.setTitle("Error");
			a.setHeaderText("No se pudo enviar el email.");
			a.setContentText(errorMSG);
		}
		alertStage = (Stage) a.getDialogPane().getScene().getWindow();
		alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/email-send-icon-32x32.png")));
		a.show();
	}
	
	private void clear() {
		nombreServidor.setText("");
		puerto.setText("");
		conexionSSL.setSelected(false);
		emailRemitente.setText("");
		passRemitente.setText("");
		emailDestinatario.setText("");
		asunto.setText("");
		mensaje.setText("");
	}
	
	private boolean checkInputs() {
		boolean res = true;
		TextField[] fields = {
			nombreServidor,
			puerto,
			emailRemitente,
			passRemitente,
			emailDestinatario,
			asunto, 
		};
		for(int i = 0; i < fields.length; i++) {
			if(fields[i].getLength() <= 0 || mensaje.getLength() <= 0) {
				res = false;
				if(fields[i].getLength() <= 0) {
					switch(fields[i].getId()) {
						case "nombreServidor":
							errorMSG = "El campo de nombre del servidor está vacío.";
							break;
						case "puerto":
							errorMSG = "El campo del puerto está vacío.";
							break;
						case "emailRemitente":
							errorMSG = "El campo de email del remitente está vacío.";
							break;
						case "passRemitente":
							errorMSG = "El campo de la contraseña está vacío.";
							break;
						case "emailDestinatario":
							errorMSG = "El campo del email de destinatario está vacío.";
							break;
						case "asunto":
							errorMSG = "El asunto del email está vacío.";
							break;
						}
					i = fields.length;
				} else
					errorMSG = "El mensaje enviado está vacío";
			}
		}
		return res;
	}
	
	public GridPane getView() {
		return view;
	}
	
}