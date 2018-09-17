package hadoop.Util;

import java.math.BigDecimal;

public class NumberUtils {

    /**
     * ��ʽ��С��
     * @param str �ַ���
     * @param scale ���������λ��
     * @return ��ʽ��С��
     */
    public static double formatDouble(double num, int scale) {
        BigDecimal bd = new BigDecimal(num);  
        return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
