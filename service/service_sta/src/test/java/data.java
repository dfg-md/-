import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2023-01-02 13:00
 **/
public class data {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        l = l - (24 * 60 * 60 * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(l);
        System.out.println(format);

    }
}
