package br.ufc.russas.n2s.chronos.util;

import java.util.Map;
import com.github.shyiko.dotenv.DotEnv;

public class Constantes {
	private static String APP_GUARDIAO_URL;
	private static String APP_URL;
	private static String LOGO_IMG_APOIO;

	private Constantes() {
	}

	static {
		Map<String, String> dotEnv = DotEnv.load();
		APP_URL = dotEnv.get("APP_URL");
		LOGO_IMG_APOIO = dotEnv.get("LOGO_IMG_APOIO");
		APP_GUARDIAO_URL = dotEnv.get("APP_GUARDIAO_URL");
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
}