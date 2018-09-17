package hadoop.Util;

public class StringUtils {

	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 * 
	 * @param str
	 *            ×Ö·û´®
	 * @return ÊÇ·ñÎª¿Õ
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}

	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñ²»Îª¿Õ
	 * 
	 * @param str
	 *            ×Ö·û´®
	 * @return ÊÇ·ñ²»Îª¿Õ
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !"".equals(str);
	}

	/**
	 * ½Ø¶Ï×Ö·û´®Á½²àµÄ¶ººÅ
	 * 
	 * @param str
	 *            ×Ö·û´®
	 * @return ×Ö·û´®
	 */
	public static String trimComma(String str) {
		if (str.startsWith(",")) {
			str = str.substring(1);
		}
		if (str.endsWith(",")) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * ²¹È«Á½Î»Êý×Ö
	 * 
	 * @param str
	 * @return
	 */
	public static String fulfuill(String str) {
		if (str.length() == 2) {
			return str;
		} else {
			return "0" + str;
		}
	}

	/**
	 * ´ÓÆ´½ÓµÄ×Ö·û´®ÖÐÌáÈ¡×Ö¶Î
	 * 
	 * @param str
	 *            ×Ö·û´®
	 * @param delimiter
	 *            ·Ö¸ô·û
	 * @param field
	 *            ×Ö¶Î
	 * @return ×Ö¶ÎÖµ
	 */
	public static String getFieldFromConcatString(String str, String delimiter, String field) {
		String[] fields = str.split(delimiter);
		for (String concatField : fields) {
			String fieldName = concatField.split("=")[0];
			String fieldValue = concatField.split("=")[1];
			if (fieldName.equals(field)) {
				return fieldValue;
			}
		}
		return null;
	}

	/**
	 * ´ÓÆ´½ÓµÄ×Ö·û´®ÖÐ¸ø×Ö¶ÎÉèÖÃÖµ
	 * 
	 * @param str
	 *            ×Ö·û´®
	 * @param delimiter
	 *            ·Ö¸ô·û
	 * @param field
	 *            ×Ö¶ÎÃû
	 * @param newFieldValue
	 *            ÐÂµÄfieldÖµ
	 * @return ×Ö¶ÎÖµ
	 */
	public static String setFieldInConcatString(String str, String delimiter, String field, String newFieldValue) {
		String[] fields = str.split(delimiter);

		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].split("=")[0];
			if (fieldName.equals(field)) {
				String concatField = fieldName + "=" + newFieldValue;
				fields[i] = concatField;
				break;
			}
		}

		StringBuffer buffer = new StringBuffer("");
		for (int i = 0; i < fields.length; i++) {
			buffer.append(fields[i]);
			if (i < fields.length - 1) {
				buffer.append("|");
			}
		}

		return buffer.toString();
	}
}
