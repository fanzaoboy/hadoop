package hadoop.Util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ParamUtils {

	/**
	 * �������в�������ȡ����id
	 * 
	 * @param args
	 *            �����в���
	 * @return ����id
	 */
	public static Long getTaskIdFromArgs(String[] args) {
		try {
			if (args != null && args.length > 0) {
				return Long.valueOf(args[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��JSON��������ȡ����
	 * 
	 * @param jsonObject
	 *            JSON����
	 * @return ����
	 */
	public static String getParam(JSONObject jsonObject, String field) {
		JSONArray jsonArray = jsonObject.getJSONArray(field);
		if (jsonArray != null && jsonArray.size() > 0) {
			return jsonArray.getString(0);
		}
		return null;
	}
}
