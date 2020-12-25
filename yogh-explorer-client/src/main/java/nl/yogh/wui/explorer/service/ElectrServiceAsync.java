package nl.yogh.wui.explorer.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.ImplementedBy;

import nl.yogh.wui.explorer.service.domain.BlockInformation;
import nl.yogh.wui.explorer.service.domain.TransactionInformation;

@ImplementedBy(ElectrServiceAsyncImpl.class)
public interface ElectrServiceAsync {

  void fetchTip(AsyncCallback<String> callback);

  void fetchTransaction(String txid, AsyncCallback<TransactionInformation> callback);

  void fetchBlock(String hash, AsyncCallback<BlockInformation> callback);

  void fetchRawBlock(String hash, AsyncCallback<String> callback);

  void fetchTipHeight(AsyncCallback<String> callback);

  void fetchRecentBlocks(AsyncCallback<BlockInformation[]> callback);

  void fetchRecentBlocks(String height, AsyncCallback<BlockInformation[]> callback);

  void fetchTxids(String hash, AsyncCallback<String[]> callback);
}
