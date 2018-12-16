package com.loterie.entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "privilege")
public class Privilege {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String privilege;
	@Transient
	private Map<String, String> privileges = null;
	
	public String getPrivilege() {
		if (null == privileges) {
			privileges = new HashMap<String, String>();
			
			String[] parsePrivileges = privilege.split(";"); 
			
			for (String parsePrivilege : parsePrivileges) {
				String[] parse = parsePrivilege.split("=");
				
				if (parse.length < 2) {
					System.out.println("Privilège incorrect : '" + parsePrivilege + "'");
				} else {
					privileges.put(parse[0], parse[1]);
				}
			}
		}
		return privilege;
	}
	
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	public Long getId() {
		return id;
	}
	
	public boolean checkPrivilege(String key, String val) {
		if (null == privileges) return false;
		if (!privileges.containsKey(key)) return false;
		
		return privileges.get(key).equalsIgnoreCase(val); 
	}
	
	public boolean checkPrivilege(String key) {
		return checkPrivilege(key, "1") || checkPrivilege(key, "true"); 
	}
}
