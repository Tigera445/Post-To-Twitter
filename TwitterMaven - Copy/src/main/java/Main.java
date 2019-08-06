// Thanks to Dr. Im for help with code
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class Main {

    public static final String consumerKeyStr = System.getenv(CONSUMER_KEY);
    public static final String consumerSecretStr = System.getenv(CONSUMER_SECRET);
    public static final String accessTokenStr = System.getenv(ACCESS_TOKEN);
    public static final String accessTokenSecretStr = System.getenv(ACCESS_TOKEN_SECRET);

    public static void main(String[] args) throws Exception {
        postUpdate();
    }

    public static void searchTweets() throws Exception {
        OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr, consumerSecretStr);
        oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);

        HttpGet httpGet = new HttpGet(
                "https://api.twitter.com/1.1/search/tweets.json?q=%23ggc&count=4");

        oAuthConsumer.sign(httpGet);

        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGet);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':'
                + httpResponse.getStatusLine().getReasonPhrase());
        System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));
    }

    public static void postUpdate() throws Exception {
        OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr, consumerSecretStr);
        oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);

        HttpPost httpPost = new HttpPost(
                "https://api.twitter.com/1.1/statuses/update.json?status=The%20World%20Is%20a%20Book%20and%20Those%20Who%20Do%20Not%20Travel%20Read%20Only%20One%20Page.");

        oAuthConsumer.sign(httpPost);

        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpPost);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':'
                + httpResponse.getStatusLine().getReasonPhrase());
        System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));
    }
}
