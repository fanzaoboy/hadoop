package hadoop.Util;

public class ValidUtils {

	/**
	 * У�������е�ָ���ֶΣ��Ƿ���ָ����Χ��
	 * 
	 * @param data
	 *            ����
	 * @param dataField
	 *            �����ֶ�
	 * @param parameter
	 *            ����
	 * @param startParamField
	 *            ��ʼ�����ֶ�
	 * @param endParamField
	 *            ���������ֶ�
	 * @return У����
	 */
	public static boolean between(String data, String dataField, String parameter, String startParamField,
			String endParamField) {
		String startParamFieldStr = StringUtils.getFieldFromConcatString(parameter, "\\|", startParamField);
		String endParamFieldStr = StringUtils.getFieldFromConcatString(parameter, "\\|", endParamField);
		if (startParamFieldStr == null || endParamFieldStr == null) {
			return true;
		}

		int startParamFieldValue = Integer.valueOf(startParamFieldStr);
		int endParamFieldValue = Integer.valueOf(endParamFieldStr);

		String dataFieldStr = StringUtils.getFieldFromConcatString(data, "\\|", dataField);
		if (dataFieldStr != null) {
			int dataFieldValue = Integer.valueOf(dataFieldStr);
			if (dataFieldValue >= startParamFieldValue && dataFieldValue <= endParamFieldValue) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	/**
	 * У�������е�ָ���ֶΣ��Ƿ���ֵ������ֶε�ֵ��ͬ
	 * 
	 * @param data
	 *            ����
	 * @param dataField
	 *            �����ֶ�
	 * @param parameter
	 *            ����
	 * @param paramField
	 *            �����ֶ�
	 * @return У����
	 */
	public static boolean in(String data, String dataField, String parameter, String paramField) {
		String paramFieldValue = StringUtils.getFieldFromConcatString(parameter, "\\|", paramField);
		if (paramFieldValue == null) {
			return true;
		}
		String[] paramFieldValueSplited = paramFieldValue.split(",");

		String dataFieldValue = StringUtils.getFieldFromConcatString(data, "\\|", dataField);
		if (dataFieldValue != null) {
			String[] dataFieldValueSplited = dataFieldValue.split(",");

			for (String singleDataFieldValue : dataFieldValueSplited) {
				for (String singleParamFieldValue : paramFieldValueSplited) {
					if (singleDataFieldValue.equals(singleParamFieldValue)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * У�������е�ָ���ֶΣ��Ƿ���ָ����Χ��
	 * 
	 * @param data
	 *            ����
	 * @param dataField
	 *            �����ֶ�
	 * @param parameter
	 *            ����
	 * @param paramField
	 *            �����ֶ�
	 * @return У����
	 */
	public static boolean equal(String data, String dataField, String parameter, String paramField) {
		String paramFieldValue = StringUtils.getFieldFromConcatString(parameter, "\\|", paramField);
		if (paramFieldValue == null) {
			return true;
		}

		String dataFieldValue = StringUtils.getFieldFromConcatString(data, "\\|", dataField);
		if (dataFieldValue != null) {
			if (dataFieldValue.equals(paramFieldValue)) {
				return true;
			}
		}

		return false;
	}
}
