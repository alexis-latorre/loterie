package com.loterie.tools;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.loterie.dao.LoterieDao;

public class DevTools {
	
	public static void dumpSession(HttpServletRequest req) {
		if (req != null) {
			HttpSession session = req.getSession();
			System.out.println("########################");
			System.out.println("##### DUMP SESSION #####");
			System.out.println("########################");
			int i = 0;
			String key;
			try {
				Enumeration<String> params = session.getAttributeNames();
				
				while ((key = params.nextElement()) != null) {
					System.out.println(i + ": '" + key + "' => '" + session.getAttribute(key) + "'");
					i++;
				}
			} catch (Exception e) {
			}
			System.out.println("########################");
			
		}
	}
	
	public static void clearCache(Object dao) {
		((LoterieDao) dao).clearCache();
	}
}
