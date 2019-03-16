import java.text.DecimalFormat;

public class Formats {
    // decimal format for currency text fields
    public static final DecimalFormat CURRENCY = new DecimalFormat("\u20ac ###,###,##0.00" );
    // decimal format for active currency text field
    public static final DecimalFormat TEXT_FIELD = new DecimalFormat("\u20ac ###,###,##0.00" );
}