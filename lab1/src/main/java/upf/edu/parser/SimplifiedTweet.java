package upf.edu.parser;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

import java.io.Serializable;
import java.util.Optional;

public class SimplifiedTweet {

  private static JsonParser parser = new JsonParser();

  private final long tweetId;			  // the id of the tweet ('id')
  private final String text;  		      // the content of the tweet ('text')
  private final long userId;			  // the user id ('user->id')
  private final String userName;		  // the user name ('user'->'name')
  private final String language;          // the language of a tweet ('lang')
  private final long timestampMs;		  // seconds from epoch ('timestamp_ms')

  public SimplifiedTweet(long tweetId, String text, long userId, String userName,
                         String language, long timestampMs) {

	this.tweetId = tweetId;
	this.text = text;
	this.userId = userId;
	this.userName = userName;
	this.language = language;
	this.timestampMs = timestampMs;

  }

    public long getTweetId() {
        return tweetId;
    }

    public String getText() {
        return text;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getLanguage() {
        return language;
    }

    public long getTimestampMs() {
        return timestampMs;
    }
  
  
  /**
   * Returns a {@link SimplifiedTweet} from a JSON String.
   * If parsing fails, for any reason, return an {@link Optional#empty()}
   *
   * @param jsonStr
   * @return an {@link Optional} of a {@link SimplifiedTweet}
   */
  public static Optional<SimplifiedTweet> fromJson(String jsonStr) {

	long tweetId = 0;		 
	String text = "";      
  	long userId = 0;		  
 	String userName = "";	  
 	String language = "";     
  	long timestampMs = 0;

	JsonElement je = SimplifiedTweet.parser.parse(jsonStr);
	JsonObject jo = je.getAsJsonObject();

	if (jo.has("lang"))
	{

		if (jo.has("id")){tweetId = jo.get("id").getAsLong();}
		if (jo.has("text")){text = jo.get("text").getAsString();}
		if (jo.has("user")){
			JsonObject userObj = jo.get("user").getAsJsonObject();
			if (userObj.has("id")){userId = userObj.get("id").getAsLong();}
			if (userObj.has("name")){userName = userObj.get("name").getAsString();}
		}
		language = jo.get("lang").getAsString();
		if(jo.has("timestamp_ms")){timestampMs = jo.get("timestamp_ms").getAsLong();}

		SimplifiedTweet tweet = new SimplifiedTweet(tweetId, text, userId, userName, language, timestampMs);

		return Optional.ofNullable(tweet);

  	}
	else
	{
		return Optional.ofNullable(null);
	}
  }
}
