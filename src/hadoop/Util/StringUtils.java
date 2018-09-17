package hadoop.Util;

public class StringUtils {

	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 *            �ַ���
	 * @return �Ƿ�Ϊ��
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}

	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 *            �ַ���
	 * @return �Ƿ�Ϊ��
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !"".equals(str);
	}

	/**
	 * �ض��ַ�������Ķ���
	 * 
	 * @param str
	 *            �ַ���
	 * @return �ַ���
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
	 * ��ȫ��λ����
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
	 * ��ƴ�ӵ��ַ�������ȡ�ֶ�
	 * 
	 * @param str
	 *            �ַ���
	 * @param delimiter
	 *            �ָ���
	 * @param field
	 *            �ֶ�
	 * @return �ֶ�ֵ
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
	 * ��ƴ�ӵ��ַ����и��ֶ�����ֵ
	 * 
	 * @param str
	 *            �ַ���
	 * @param delimiter
	 *            �ָ���
	 * @param field
	 *            �ֶ���
	 * @param newFieldValue
	 *            �µ�fieldֵ
	 * @return �ֶ�ֵ
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
