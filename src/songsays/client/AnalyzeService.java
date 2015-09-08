package songsays.client;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("message")
public interface AnalyzeService extends RemoteService{
	
	Result analyzeSongs(String[] songs);

}
