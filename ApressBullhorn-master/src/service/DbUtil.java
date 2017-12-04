//DbUtil.java
package service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DbUtil {
	public static EntityManager getEntityManager(String s) {
		return Persistence.createEntityManagerFactory(s).createEntityManager();
	}
}
//End of DbUtil.java