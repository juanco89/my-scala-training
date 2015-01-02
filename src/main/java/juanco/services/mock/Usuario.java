package juanco.services.mock;

public class Usuario {

	public String usuario;
	public String password;
	
	public Usuario(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassWord() {
		return password;
	}

	public void setPassWord(String password) {
		this.password = password;
	}
}
