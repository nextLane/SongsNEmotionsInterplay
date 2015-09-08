package songsays.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface AnalyzeServiceAsync {
	void analyzeSongs(String[] songs, AsyncCallback<Result> callback)
			throws IllegalArgumentException;
}
