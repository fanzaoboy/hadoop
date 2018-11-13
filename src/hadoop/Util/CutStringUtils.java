package hadoop.Util;

public class CutStringUtils {

//	public static void main(String[] args) {
//		String line = "110.52.250.126 - - [30/May/2013:17:38:20 +0800] \"GET /data/cache/style_1_widthauto.css?y7a HTTP/1.1\" 200 1292";
//		String[] strs = parse(line);
//		for (String str : strs) {
//			System.out.println(str);
//		}
//	}
//	
	public static String[] parse (String line) {
		String IP = parseIP(line);
		String time = parseTime(line);
        String url = parseUrl(line);
        String status = parseStatus(line);
        String traffic = parseTraffic(line);
        
        return new String[] {IP , time, url, status, traffic};
	}
	
	public static String parseIP (String line) {
		String IP = line.split(" - - ")[0].trim();
		return IP;
	}
	
	public static String parseTime (String line) {
		String tmpDate = line.substring(line.indexOf("[") + 1, line.indexOf(" +0800"));
		String time = DateUtils.formatDateEN(tmpDate);
		return time;
	}
	
	public static String parseUrl (String line) {
		String Url = line.split(" ")[6];
		return Url;
	}
	
	public static String parseStatus (String line) {
		String status = line.split(" ")[8];
		return status;
	}
	
	public static String parseTraffic (String line) {
		String traffic = line.split(" ")[9];
		return traffic;
	}
}
