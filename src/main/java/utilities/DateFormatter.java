package utilities;

import java.text.SimpleDateFormat;

public class DateFormatter {
    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(new java.util.Date());
    }
}
