package Connect;

import java.io.InputStream;
import java.util.Properties;

public class ConnectDatabase {

	public static String getConnectionUrlFromClassPath() {
		String strCon = null;
		
		try(InputStream file = ConnectDatabase.class.getClassLoader().getResourceAsStream("db.properties"))
		{
			Properties prop = new Properties();
			prop.load(file);
			strCon = prop.getProperty("url") + prop.getProperty("serverName") + ":" + prop.getProperty("portNumber") + "; database=" + prop.getProperty("databaseName") + "; user=" + prop.getProperty("username") + "; password=" + prop.getProperty("password");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			strCon = null;
		}
		
		return strCon;
	}
	
}
