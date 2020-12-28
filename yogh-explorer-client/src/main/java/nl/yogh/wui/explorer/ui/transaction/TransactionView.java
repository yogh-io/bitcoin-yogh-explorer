package nl.yogh.wui.explorer.ui.transaction;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Computed;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.google.web.bindery.event.shared.EventBus;

import jsinterop.annotations.JsMethod;

import nl.yogh.wui.explorer.component.fields.SimpleField;
import nl.yogh.wui.explorer.component.hex.HexViewer;
import nl.yogh.wui.explorer.component.hex.interpreters.TransactionInterpreter;
import nl.yogh.wui.explorer.component.links.AddressLink;
import nl.yogh.wui.explorer.component.links.BlockLink;
import nl.yogh.wui.explorer.component.links.TransactionLink;
import nl.yogh.wui.explorer.context.BlockContext;
import nl.yogh.wui.explorer.context.TransactionContext;
import nl.yogh.wui.explorer.service.ElectrServiceAsync;
import nl.yogh.wui.explorer.service.domain.TransactionInformation;
import nl.yogh.wui.util.EllipsisUtil;

@Component(components = {
    BlockLink.class,
    TransactionLink.class,
    AddressLink.class,
    HexViewer.class,
    SimpleField.class
})
public class TransactionView implements IsVueComponent {
  @Prop EventBus eventBus;

  @Inject ElectrServiceAsync service;

  @Data @Inject TransactionContext context;
  @Data @Inject BlockContext blockContext;

  @Data @Inject TransactionInterpreter txInterpreter;

  @Computed
  public TransactionInformation getTransaction() {
    return context.transactionInformation;
  }

  @Computed
  public String getRaw() {
    return context.raw;
  }

  @JsMethod
  public String formatPayload(final String payload) {
    return EllipsisUtil.applyInnerPayload(payload);
  }
}
