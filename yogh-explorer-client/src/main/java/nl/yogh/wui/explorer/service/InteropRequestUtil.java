package nl.yogh.wui.explorer.service;

import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

import jsinterop.base.Js;

import elemental2.dom.FormData;
import elemental2.dom.ProgressEvent;
import elemental2.dom.XMLHttpRequest;

import nl.aerius.wui.service.exception.RequestClientException;

public class InteropRequestUtil {
  public static <T> void doGet(final String url, final AsyncCallback<T> callback) {
    doRequest("get", url, null, callback);
  }

  public static <T> void doPost(final String url, final AsyncCallback<T> callback) {
    doRequest("post", url, (FormData) null, callback);
  }

  private static <T> void doRequest(final String method, final String url, final FormData payload, final AsyncCallback<T> callback) {
    final XMLHttpRequest req = getRequest(method, url, callback);
    req.send(payload);
  }

  private static <T> XMLHttpRequest getRequest(final String method, final String url, final AsyncCallback<T> callback) {
    final XMLHttpRequest req = new XMLHttpRequest();

    req.addEventListener("error", evt -> {
      handleError(callback, "XHR Error: " + evt.type + " (loaded:" + ((ProgressEvent) Js.uncheckedCast(evt)).loaded + ")");
    });
    req.addEventListener("load", evt -> {
      if (req.status != 200) {
        if (req.responseText == null || req.responseText.isEmpty()) {
          handleError(callback, req.status + " > " + req.statusText);
        } else {
          handleError(callback, req.responseText);
        }
      } else {
        final String responseText = req.responseText;
        callback.onSuccess(Js.cast(JSON.parse(responseText)));
      }
    });

    req.open(method, url);
    return req;
  }

  public static native void log(Object message) /*-{
                                                console.info(message );
                                                }-*/;

  private static void handleError(final AsyncCallback<?> callback, final String responseText) {
    callback.onFailure(new RequestClientException(responseText));
  }

  public static FormData createFormData(final Map<String, String> map) {
    final FormData data = new FormData();
    map.forEach((k, v) -> data.append(k, v));
    return data;
  }

  public static String prepareUrl(final String host, final String template, final String... args) {
    if (args.length % 2 != 0) {
      throw new RuntimeException("Template args are of incorrect size: " + args.length);
    }

    String bldr = host + template;
    for (int i = 0; i < args.length; i += 2) {
      if (args[i] == null || args[i + 1] == null) {
        continue;
      }

      bldr = bldr.replaceAll(args[i], args[i + 1]);
    }

    return bldr;
  }
}
