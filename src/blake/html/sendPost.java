package blake.html;
// Imported libraries
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
/*******************************************************************
 *  sendPost Class with main method
 *  Description: Send a url post request and print response
 *  I used ideas and layout from:
 *  https://mkyong.com/java/how-to-send-http-request-getpost-in-java/
 *******************************************************************/
public class sendPost {
    // Java main method
    public static void main(String[] args) throws Exception {
        sendPost obj = new sendPost();
        System.out.println("Send Http POST request");
        // Catch bad url through IOException
        try {
            obj.sendPostReq();
        } catch (IOException e) {
            System.out.println("Bad Url?");
        }
    }
    // Send post request
    private void sendPostReq() throws Exception {
        // Good url
        String url = "https://httpbin.org/post";
        // Bad url
        //String url = "https://www.isthisrealandstuff.com/post";
        // Establish url and open connection
        HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();
        // Set type of request and add header
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        // Required headers for Httpbin site
        httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        // Post specific parameters
        String urlParameters = "sn=C02G8416DRJM&cn=2344324&locale=kansascity&caller=jeremy&num=5554321";
        // Send post request
        httpClient.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }
        // Variable for response code
        int responseCode = httpClient.getResponseCode();
        // Print connection to screen
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            // Print result to screen
            System.out.println(response.toString());
        }
    }
}
