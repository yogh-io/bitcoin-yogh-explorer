package nl.yogh.wui.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public final class FormatUtil {
  private static final DateTimeFormat COMPACT_DATE_FORMATTER = DateTimeFormat.getFormat("HH:mm:ss");

  private FormatUtil() {}

  public static String formatCompactTime(final long time) {
    return COMPACT_DATE_FORMATTER.format(new Date(time));
  }
}
