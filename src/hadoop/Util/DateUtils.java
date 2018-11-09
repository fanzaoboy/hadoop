package hadoop.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat EN_DATE_FORMAT = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);

	/**
	 * �ж�һ��ʱ���Ƿ�����һ��ʱ��֮ǰ yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time1 ��һ��ʱ��
	 * @param time2 �ڶ���ʱ��
	 * @return �жϽ��
	 */
	public static boolean before(String time1, String time2) {
		try {
			Date dateTime1 = TIME_FORMAT.parse(time1);
			Date dateTime2 = TIME_FORMAT.parse(time2);

			if (dateTime1.before(dateTime2)) {
				return true;
			}
		} catch (Exception e) {
			System.err.println("Error:�����time���Ͳ���ȷ!");
		}
		return false;
	}

	/**
	 * �ж�һ��ʱ���Ƿ�����һ��ʱ��֮��
	 * 
	 * @param time1 ��һ��ʱ��
	 * @param time2 �ڶ���ʱ��
	 * @return �жϽ��
	 */
	public static boolean after(String time1, String time2) {
		try {
			Date dateTime1 = TIME_FORMAT.parse(time1);
			Date dateTime2 = TIME_FORMAT.parse(time2);

			if (dateTime1.after(dateTime2)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * ����ʱ���ֵ����λΪ�룩
	 * 
	 * @param time1 ʱ��1
	 * @param time2 ʱ��2
	 * @return ��ֵ
	 */
	public static int minus(String time1, String time2) {
		try {
			Date datetime1 = TIME_FORMAT.parse(time1);
			Date datetime2 = TIME_FORMAT.parse(time2);

			long millisecond = datetime1.getTime() - datetime2.getTime();

			return Integer.valueOf(String.valueOf(millisecond / 1000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * ��ȡ�����պ�Сʱ
	 * 
	 * @param datetime ʱ�䣨yyyy-MM-dd HH:mm:ss��
	 * @return ���
	 */
	public static String getDateHour(String datetime) {
		String date = datetime.split(" ")[0];
		String hourMinuteSecond = datetime.split(" ")[1];
		String hour = hourMinuteSecond.split(":")[0];
		return date + "_" + hour;
	}

	/**
	 * ��ȡ�������ڣ�yyyy-MM-dd��
	 * 
	 * @return ��������
	 */
	public static String getTodayDate() {
		return DATE_FORMAT.format(new Date());
	}

	/**
	 * ��ȡ��������ڣ�yyyy-MM-dd��
	 * 
	 * @return ���������
	 */
	public static String getYesterdayDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_YEAR, -1);

		Date date = cal.getTime();

		return DATE_FORMAT.format(date);
	}

	/**
	 * ��ʽ�����ڣ�yyyy-MM-dd��
	 * 
	 * @param date Date����
	 * @return ��ʽ���������
	 */
	public static String formatDate(Date date) {
		return DATE_FORMAT.format(date);
	}

	/**
	 * 
	 * @author foton
	 * @desc 格式化英文时间
	 * 
	 */
	public static String formatDateEN(String date) {
		Date parse = null;
		String fin_date = null;
		try {
			parse = EN_DATE_FORMAT.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		fin_date = formatTime(parse);
		return fin_date;
	}

	/**
	 * ��ʽ��ʱ�䣨yyyy-MM-dd HH:mm:ss��
	 * 
	 * @param date Date����
	 * @return ��ʽ�����ʱ��
	 */
	public static String formatTime(Date date) {
		return TIME_FORMAT.format(date);
	}
}
