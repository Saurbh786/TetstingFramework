package vTiger_CRM_genericFileUtility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	public String getDatafromJSON(String key) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("./ConfigAppData/appCommonData.json"));
		JSONObject map = (JSONObject)obj;
		 String data = map.get(key).toString();
		return data;
	}

}
