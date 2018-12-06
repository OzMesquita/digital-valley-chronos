package util;

import java.util.Map;
import com.github.shyiko.dotenv.DotEnv;

public class Constantes {
	private static String APP_GUARDIAO_URL;
	private static String APP_URL;
	private static String LOGO_IMG_APOIO;
	private static String DATABASE_CONF_DIR;

	private Constantes() {
	}

	static {
		Map<String, String> dotEnv = DotEnv.load();
		APP_URL = dotEnv.get("APP_URL");
		LOGO_IMG_APOIO = dotEnv.get("LOGO_IMG_APOIO");
		APP_GUARDIAO_URL = dotEnv.get("APP_GUARDIAO_URL");
		DATABASE_CONF_DIR = dotEnv.get("DATABASE_CONF_DIR");
	}

	/**
	 * @return the appUrl
	 */
	public static String getAppUrl() {
		return APP_URL;
	}
	
	/**
	 * @return the logoImgApoio
	 */
	public static String getLogoImgApoio() {
		return LOGO_IMG_APOIO;
	}
	/**
	 * @return the appUrl
	 */
	public static String getAppGuardiaoUrl() {
		return APP_GUARDIAO_URL;
	}

	/**
	 * @return the dATABASE_CONF_DIR
	 */
	public static String getDatabaseConfDir() {
		return DATABASE_CONF_DIR;
	}

}