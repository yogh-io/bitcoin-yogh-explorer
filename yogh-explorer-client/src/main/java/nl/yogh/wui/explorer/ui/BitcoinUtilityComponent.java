package nl.yogh.wui.explorer.ui;

import java.util.Date;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.google.gwt.i18n.client.DateTimeFormat;

import jsinterop.annotations.JsMethod;

import nl.yogh.wui.util.EllipsisUtil;

@Component(hasTemplate = false)
public class BitcoinUtilityComponent implements IsVueComponent {
  private static final double KILO = 1000D;

  private static final double SATOSHIS_IN_BITCOIN = 1D * 1000D * 1000D;

  private static final DateTimeFormat FORMAT = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");

  @JsMethod
  public double formatAmount(final int satoshi) {
    return satoshi / SATOSHIS_IN_BITCOIN;
  }

  @JsMethod
  public String formatAmountWithUnit(final int satoshi) {
    return toFixed(satoshi / SATOSHIS_IN_BITCOIN, 8) + " BTC";
  }

  @JsMethod
  public String formatTimestamp(final int timestamp) {
    return FORMAT.format(new Date(timestamp * 1000));
  }

  @JsMethod
  public String ellipsisHash(final String hash) {
    return EllipsisUtil.applyInner(hash, 48);
  }

  @JsMethod
  public String formatSizeInKb(final int size) {
    return toFixed(size / KILO, 3);
  }

  @JsMethod
  public String formatSizeInVbWithUnit(final int size) {
    return size + " vB";
  }

  @JsMethod
  public String formatSizeInMb(final int size) {
    return toFixed(size / KILO / KILO, 6);
  }

  @JsMethod
  public String formatFee(final int fee, final int vsize) {
    return String.valueOf(toFixed((double) fee / vsize, 2)) + " sat/vB";
  }
  
  public static native String toFixed(double d, int round) /*-{
    return Number(d).toLocaleString("en-US", {minimumFractionDigits: round, maximumFractionDigits: round});
  }-*/;
  
  public static native String zeroPad(Object num, int places) /*-{
    return String(num).padStart(places, '0');
  }-*/;

}
