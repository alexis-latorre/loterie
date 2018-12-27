package com.loterie.forms;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.loterie.config.Privileges;
import com.loterie.dao.PrivilegeDao;
import com.loterie.entities.Privilege;
import com.loterie.tools.Tools;

public class PrivilegesModificationForm {
	
	private Map<String, String> erreurs = new HashMap<String, String>();
	private PrivilegeDao privilegeDao;
	private Privilege privilege;
	private HttpServletRequest req;
	private String privileges;
	
	public PrivilegesModificationForm(Privilege privilege, PrivilegeDao privilegeDao, HttpServletRequest req) {
		this.privilege = privilege;
		this.privilegeDao = privilegeDao;
		this.req = req;
	}
	
	public void valider() {
		privileges = "";
		String[] tousPrivileges = (String[]) Tools.concatenerString(Privileges.BANQUE, Privileges.GRILLE, Privileges.JEU, 
				Privileges.JOUR, Privileges.LGU, Privileges.LOG, Privileges.PORTEFEUILLE, Privileges.PRIVILEGE, 
				Privileges.RESULTAT, Privileges.RETARD, Privileges.UTILISATEUR);
		
		for (String nomPrivilege : tousPrivileges) {		
			if (null != req.getParameter(nomPrivilege)) {
				String val = this.req.getParameter(nomPrivilege);
				
				if ("on".equalsIgnoreCase(val)) {
					privileges += ";" + nomPrivilege + "=1";
				} else {
					privileges += ";" + nomPrivilege + "=0";
				}
			} else {
				privileges += ";" + nomPrivilege + "=0";
			}
		}
		
		privileges = privileges.substring(1);
	}
	
	public void maj() {
		privilege.setPrivilege(privileges);
		this.privilegeDao.maj(privilege);
	}
	
	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}
}
