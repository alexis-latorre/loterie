package com.loterie.forms;

import java.util.ArrayList;
import java.util.List;

import com.loterie.business.LogHTML;
import com.loterie.dao.LogDao;
import com.loterie.entities.Log;

public class LogRecuperationForm {
	private LogDao logDao;
	
	public LogRecuperationForm(LogDao logDao) {
		this.logDao = logDao;
	}
	
	public List<LogHTML> parseLogs() {
		List<Log> logs = logDao.trouver();
		List<LogHTML> logsHTML = new ArrayList<LogHTML>();
		
		for (Log log : logs) {
			logsHTML.add(new LogHTML(log));
		}
		return logsHTML;
	}
}
