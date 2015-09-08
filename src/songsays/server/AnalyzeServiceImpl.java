package songsays.server;


import songsays.client.AnalyzeService;
import songsays.client.Result;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.Random;

public class AnalyzeServiceImpl extends RemoteServiceServlet  implements AnalyzeService{
   
	public Result analyzeSongs(String[] songs) {

		return  new AnalyzeSentiment().analyzePlaylist(songs);
  }

}
