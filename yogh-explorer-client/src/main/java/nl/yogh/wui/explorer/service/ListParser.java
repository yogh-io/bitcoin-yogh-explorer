package nl.yogh.wui.explorer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.google.gwt.user.client.rpc.AsyncCallback;

import nl.aerius.wui.service.ForwardedAsyncCallback;
import nl.aerius.wui.service.json.JSONArrayHandle;
import nl.aerius.wui.service.json.JSONObjectHandle;

public class ListParser {
  public static final <T> AsyncCallback<String> of(final Function<JSONObjectHandle, T> converter, final AsyncCallback<List<T>> callback) {
    return new ElementalCallback<T>(converter, callback);
  }

  public static final class ElementalCallback<T> extends ForwardedAsyncCallback<List<T>, String> {
    private final Function<JSONObjectHandle, T> converter;

    public ElementalCallback(final Function<JSONObjectHandle, T> converter, final AsyncCallback<List<T>> callback) {
      super(callback);
      this.converter = converter;
    }

    @Override
    public final List<T> convert(final String content) {
      final List<T> lst = new ArrayList<>();

      JSONArrayHandle.fromText(content)
          .forEach(v -> lst.add(converter.apply(v)));

      return lst;
    }
  }
}
