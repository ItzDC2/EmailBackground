package email;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmailModel {

	private StringProperty nombreServidor = new SimpleStringProperty();
	private StringProperty emailRemitente = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private StringProperty emailDestinatario = new SimpleStringProperty();
	private StringProperty asuntoEmail = new SimpleStringProperty();
	private StringProperty mensajeEmail = new SimpleStringProperty();
	
	private BooleanProperty conexionSSL = new SimpleBooleanProperty();
	private IntegerProperty puerto = new SimpleIntegerProperty();
	
	public StringProperty nombreServidorProperty() {
		return this.nombreServidor;
	}
	
	public String getNombreServidor() {
		return this.nombreServidorProperty().get();
	}
	
	public void setNombreServidor(final String nombreServidor) {
		this.nombreServidorProperty().set(nombreServidor);
	}
	
	public StringProperty emailRemitenteProperty() {
		return this.emailRemitente;
	}
	
	public String getEmailRemitente() {
		return this.emailRemitenteProperty().get();
	}
	
	public void setEmailRemitente(final String emailRemitente) {
		this.emailRemitenteProperty().set(emailRemitente);
	}
	
	public StringProperty passwordProperty() {
		return this.password;
	}
	
	public String getPassword() {
		return this.passwordProperty().get();
	}
	
	public void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	
	public StringProperty asuntoEmailProperty() {
		return this.asuntoEmail;
	}
	
	public String getAsuntoEmail() {
		return this.asuntoEmailProperty().get();
	}
	
	public void setAsuntoEmail(final String asuntoEmail) {
		this.asuntoEmailProperty().set(asuntoEmail);
	}
	
	public StringProperty mensajeEmailProperty() {
		return this.mensajeEmail;
	}
	
	public String getMensajeEmail() {
		return this.mensajeEmailProperty().get();
	}
	
	public void setMensajeEmail(final String mensajeEmail) {
		this.mensajeEmailProperty().set(mensajeEmail);
	}
	
	public BooleanProperty conexionSSLProperty() {
		return this.conexionSSL;
	}
	
	public boolean enabledConexionSSL() {
		return this.conexionSSLProperty().get();
	}
	
	public void setConexionSSL(final boolean conexionSSL) {
		this.conexionSSLProperty().set(conexionSSL);
	}
	
	public IntegerProperty puertoProperty() {
		return this.puerto;
	}
	
	public int getPuerto() {
		return this.puertoProperty().get();
	}
	
	public void setPuerto(final int puerto) {
		this.puertoProperty().set(puerto);
	}

	public StringProperty emailDestinatarioProperty() {
		return this.emailDestinatario;
	}
	

	public String getEmailDestinatario() {
		return this.emailDestinatarioProperty().get();
	}
	

	public void setEmailDestinatario(final String emailDestinatario) {
		this.emailDestinatarioProperty().set(emailDestinatario);
	}
	
	
}