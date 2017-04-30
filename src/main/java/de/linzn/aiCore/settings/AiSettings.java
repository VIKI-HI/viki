package de.linzn.aiCore.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import de.linzn.aiCore.App;

public class AiSettings {
	private App app;
	private String fileName = "aiCore.properties";

	public String socketHost;
	public int socketPort;

	public String sqlHostName;
	public int sqlPort;
	public String sqlDatabaseName;
	public String sqlUserName;
	public String sqlPassword;

	public AiSettings(App app) {
		App.logger("Loading AiSettings module.");
		this.app = app;
		this.init();

	}

	public void init() {
		File file = new File(this.fileName);
		if (!file.exists()) {
			create();
		}
		this.load();

	}

	public void create() {

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream(this.fileName);
			// set the properties value
			prop.setProperty("socketHost", "0.0.0.0");
			prop.setProperty("socketPort", "11102");

			prop.setProperty("sqlHostname", "127.0.0.1");
			prop.setProperty("sqlPort", "3306");
			prop.setProperty("sqlDatabaseName", "aicore_db");
			prop.setProperty("sqlUserName", "aicore");
			prop.setProperty("sqlPassword", "123asdfaas");

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void load() {

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(this.fileName);

			prop.load(input);

			this.socketHost = prop.getProperty("socketHost");
			this.socketPort = Integer.parseInt(prop.getProperty("socketPort"));

			this.sqlHostName = prop.getProperty("sqlHostname");
			this.sqlPort = Integer.parseInt(prop.getProperty("sqlPort"));
			this.sqlDatabaseName = prop.getProperty("sqlDatabaseName");
			this.sqlUserName = prop.getProperty("sqlUserName");
			this.sqlPassword = prop.getProperty("sqlPassword");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
