package nl.yogh.wui.i18n;

import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.Messages;

import nl.yogh.wui.util.ApplicationConstants;

/**
 * GWT interface to language specific static text.
 */
@DefaultLocale(ApplicationConstants.DEFAULT_LOCALE)
public interface ApplicationMessages extends Messages {
  @Description("A fatal error occurred, most likely a bug.")
  String errorInternalFatal();

  @DefaultMessage("d MMMM y")
  String notificationDateFormat();

  @DefaultMessage("d MMMM y, HH:mm ''uur''")
  String notificationDateTimeFormat();

  @DefaultMessage("*")
  String nullDateDefault();
}
