package nl.yogh.wui.explorer.service;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Singleton;

import nl.aerius.wui.util.RequestUtil;
import nl.yogh.wui.explorer.config.EnvironmentConfiguration;
import nl.yogh.wui.explorer.context.ConfigurationContext;
import nl.yogh.wui.explorer.service.domain.AddressInformation;
import nl.yogh.wui.explorer.service.domain.BlockInformation;
import nl.yogh.wui.explorer.service.domain.TransactionInformation;
import nl.yogh.wui.explorer.service.domain.UtxoInformation;

@Singleton
public class ElectrServiceAsyncImpl implements ElectrServiceAsync {
  @Inject EnvironmentConfiguration cfg;

  @Inject ConfigurationContext context;

  private static final String BLOCKSTREAM_HOST = "https://blockstream.info/api/";
  private static final String LOCAL_HOST = "/electr/";

  @Override
  public void fetchTip(final AsyncCallback<String> callback) {
    final String url = RequestUtil.prepareUrl(getHost(), "blocks/tip/hash");

    RequestUtil.doGet(url, callback);
  }

  @Override
  public void fetchTransaction(final String txid, final AsyncCallback<TransactionInformation> callback) {
    final String url = RequestUtil.prepareUrl(getHost(), "tx/:txid", ":txid", txid);

    InteropRequestUtil.doGet(url, callback);
  }

  @Override
  public void fetchTransactionHex(final String txid, final AsyncCallback<String> callback) {
    final String url = RequestUtil.prepareUrl(getHost(), "tx/:txid/hex", ":txid", txid);

    RequestUtil.doGet(url, callback);
  }

  @Override
  public void fetchBlock(final String hash, final AsyncCallback<BlockInformation> callback) {
    final String url = RequestUtil.prepareUrl(getHost(), "block/:hash", ":hash", hash);

    InteropRequestUtil.doGet(url, callback);
  }

  @Override
  public void fetchRawBlock(final String hash, final AsyncCallback<String> callback) {
    final String url = RequestUtil.prepareUrl(getHost(), "block/:hash/header", ":hash", hash);

    RequestUtil.doGet(url, callback);
  }

  @Override
  public void fetchTipHeight(final AsyncCallback<String> callback) {
    final String url = RequestUtil.prepareUrl(getHost(), "blocks/tip/height");

    InteropRequestUtil.doGet(url, callback);
  }

  @Override
  public void fetchRecentBlocks(final AsyncCallback<BlockInformation[]> callback) {
    final String url = RequestUtil.prepareUrl(getHost(), "blocks");

    InteropRequestUtil.doGet(url, callback);
  }

  @Override
  public void fetchRecentBlocks(final String height, final AsyncCallback<BlockInformation[]> callback) {
    final String url = RequestUtil.prepareUrl(getHost(), "blocks/:start_height", ":start_height", height);

    InteropRequestUtil.doGet(url, callback);
  }

  @Override
  public void fetchTxids(final String hash, final AsyncCallback<String[]> callback) {
    final String url = RequestUtil.prepareUrl(getHost(), "block/:hash/txids", ":hash", hash);

    InteropRequestUtil.doGet(url, callback);
  }

  @Override
  public void fetchAddress(final String address, final AsyncCallback<AddressInformation> callback) {
    final String url = RequestUtil.prepareUrl(getHost(), "address/:address", ":address", address);

    InteropRequestUtil.doGet(url, callback);
  }

  @Override
  public void fetchUtxos(final String address, final AsyncCallback<UtxoInformation[]> callback) {
    final String url = RequestUtil.prepareUrl(getHost(), "address/:address/utxo", ":address", address);

    InteropRequestUtil.doGet(url, callback);
  }

  private String getHost() {
    String host;

    switch (context.getSource()) {
    case "blockstream":
      host = BLOCKSTREAM_HOST;
      break;
    case "non":
      host = "";
      break;
    case "local":
    default:
      host = LOCAL_HOST;
      break;
    }

    return host;
  }
}
