package com.loterie.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.joda.time.DateTime;

public class TokenManager {
	
	private static final TokenManager TOKEN_MANAGER = new TokenManager();
	private List<String> tokens = null;
	private Map<String, String> indexes = null;
	private Map<String, TokenEnvelope> objects = null;
	
	static class TokenEnvelope {
		private Object obj;
		private DateTime expiryDate;
		
		public TokenEnvelope(Object o) {
			obj = o;
			expiryDate = new DateTime().plusMinutes(1);
		}
		
		public Object getObject() {
			return obj;
		}
		
		public DateTime getExpiryDate() {
			return expiryDate;
		}
	}
	
	private TokenManager() {
		tokens = new ArrayList<String>();
		indexes = new HashMap<String, String>();
		objects = new HashMap<String, TokenEnvelope>();
	}
	
	public static String newToken(Object o) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String token = "";
		String cert = "";
		
		while (token.length() == 0 || TOKEN_MANAGER.tokens.contains(token) || 
				cert.length() == 0 || TOKEN_MANAGER.indexes.containsKey(cert)) {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(UUID.randomUUID().toString().getBytes("UTF-8"));
			token = Tools.bytesToHex(md.digest());
			md.update(UUID.randomUUID().toString().getBytes("UTF-8"));
			cert = Tools.bytesToHex(md.digest());
		}
		TOKEN_MANAGER.tokens.add(token);
		TOKEN_MANAGER.indexes.put(token, cert);
		TOKEN_MANAGER.objects.put(cert, new TokenEnvelope(o));
		
		return token;
	}
	
	public static Object retrieve(String token) {
		DateTime now = new DateTime();
		String index = TOKEN_MANAGER.indexes.get(token);

		TokenEnvelope tknEnv = TOKEN_MANAGER.objects.get(index);
		delete(token);
		
		if (null != tknEnv) {
			if (tknEnv.getExpiryDate().isBefore(now)) {
				return null;
			} else {
				return tknEnv.getObject();
			}
		}
			
		return null;
	}
	
	public static void delete(String token) {
		String index = TOKEN_MANAGER.indexes.get(token);
		TOKEN_MANAGER.tokens.remove(token);
		TOKEN_MANAGER.indexes.remove(token);
		TOKEN_MANAGER.objects.remove(index);
	}
	
	public static void list() {
		System.out.println("Liste des tokens :");
		
		for (String token : TOKEN_MANAGER.tokens) {
			System.out.println("  - " + token);
		}
		System.out.println("------------------");
		System.out.println("Liste des objets :");
		Iterator<Entry<String, TokenEnvelope>> it = TOKEN_MANAGER.objects.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry<String, TokenEnvelope> obj = (Entry<String, TokenEnvelope>)it.next();
			String cert = obj.getKey();
			TokenEnvelope tknEnv = obj.getValue();
			
			System.out.println("  - " + cert + " : " + tknEnv.getObject() + " (valid until " + tknEnv.getExpiryDate() + ")");
		}
	}
}
