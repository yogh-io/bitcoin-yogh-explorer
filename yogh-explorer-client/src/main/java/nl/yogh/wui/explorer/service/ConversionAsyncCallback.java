package nl.yogh.wui.explorer.service;

import java.util.function.Consumer;
import java.util.function.Function;

import com.google.gwt.user.client.rpc.AsyncCallback;

import nl.aerius.wui.future.AppAsyncCallback;
import nl.aerius.wui.service.ForwardedAsyncCallback;

public class ConversionAsyncCallback {
  public static <C, T> AsyncCallback<T> create(final Function<T, C> object, final Consumer<C> success, final Consumer<Throwable> failure) {
    return create(object, AppAsyncCallback.create(success, failure));
  }

  public static <C, T> AsyncCallback<T> create(final Function<T, C> object, final AsyncCallback<C> callback) {
    return new ForwardedAsyncCallback<C, T>(callback) {
      @Override
      public C convert(final T content) {
        return object.apply(content);
      }
    };
  }
}
