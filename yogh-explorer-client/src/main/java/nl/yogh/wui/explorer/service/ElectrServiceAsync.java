package nl.yogh.wui.explorer.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.ImplementedBy;

import elemental2.core.JsArray;

import nl.yogh.wui.explorer.service.domain.AddressInformation;
import nl.yogh.wui.explorer.service.domain.BlockInformation;
import nl.yogh.wui.explorer.service.domain.MempoolInformation;
import nl.yogh.wui.explorer.service.domain.TransactionInformation;
import nl.yogh.wui.explorer.service.domain.TransactionSummary;
import nl.yogh.wui.explorer.service.domain.UtxoInformation;

@ImplementedBy(ElectrServiceAsyncImpl.class)
public interface ElectrServiceAsync {

  /** TRANSACTIONS **/

  void fetchTransaction(String txid, AsyncCallback<TransactionInformation> callback);

  void fetchTransactionHex(String txid, AsyncCallback<String> callback);

  void fetchRecentTransactions(AsyncCallback<TransactionSummary[]> callback);

  /** BLOCKS **/

  void fetchTip(AsyncCallback<String> callback);

  void fetchBlock(String hash, AsyncCallback<BlockInformation> callback);

  void fetchBlockAtHeight(String height, AsyncCallback<String> callback);

  void fetchRawBlock(String hash, AsyncCallback<String> callback);

  void fetchTipHeight(AsyncCallback<String> callback);

  void fetchRecentBlocks(AsyncCallback<BlockInformation[]> callback);

  void fetchRecentBlocks(String height, AsyncCallback<BlockInformation[]> callback);

  void fetchTxids(String hash, AsyncCallback<JsArray<String>> callback);

  /** ADDRESS **/

  void fetchAddress(String address, AsyncCallback<AddressInformation> callback);

  void fetchUtxos(String address, AsyncCallback<UtxoInformation[]> callback);

  /** MEMPOOL **/

  void fetchMempool(AsyncCallback<MempoolInformation> callback);
}
