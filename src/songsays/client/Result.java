package songsays.client;

import java.io.Serializable;

public class Result implements Serializable{

	String message="";
	double posSentiment;
	double negSentiment;
	String numSongs;
	double sentimentValues[];
	
	public Result()
	{
		sentimentValues =new double[6];
	}
	
	public void setNum(String noSongs)
	{
		numSongs=noSongs;
	}
	public void setPos(double pv)
	{
		posSentiment=pv;
	}
	
	public void setNeg(double nv)
	{
		negSentiment=nv;
	}
	
	public void setSentimentValues(double[] val)
	{
		for(int i=0; i<val.length; i++)
		{
			sentimentValues[i]=val[i];
		}
	}
	
	public void setMessage()
	{
		double diff= Math.abs(posSentiment-negSentiment);
		
		if(diff <10)
			message= "You have quite balanced playlist dude, great job!!";
		
		else if(posSentiment> negSentiment)
			message ="What a jolly good creature you are!! Shades of sorrow fades away!";
		
		else if(posSentiment < negSentiment)
			message="You acknowledge that sadness is a vital component of life! But feels like it's time to reshuffle the playlist a bit?! :P";
		
		message+="\n "+ numSongs+" % of your songs analyzed! We are working on the efficiency!";
		
	}
}
