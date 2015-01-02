package juanco.services.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase simulada para reemplazar un servicio LDAP.
 * 
 * @author Juan C. Orozco
 *
 */
public class UserService {

	private List<Usuario> dataSource;
	
	private static final Random rnd = new Random();
	
	public UserService() {
		dataSource = new ArrayList<Usuario>();
		dataSource.add(new Usuario("juan.orozco", "1234"));
	}
	
	public boolean autenticar(String usuario, String password) throws InterruptedException {
		Thread.sleep(rnd.nextInt(3000));
		for(Usuario u : dataSource) {
			if(u.getUsuario().equals(usuario) && u.getPassWord().equals(password))
				return true;
		}
		return false;
	}
}
