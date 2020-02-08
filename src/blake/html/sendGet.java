package blake.html;
// Imported libraries
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/*******************************************************************
 *  sendGet Class with main method
 *  Description: Send a url get request and print response
 *  I used ideas and layout from:
 *  https://mkyong.com/java/how-to-send-http-request-getpost-in-java/
 *******************************************************************/
public class sendGet {
    // Java main method
    public static void main(String[] args) throws Exception {
        sendGet obj = new sendGet();
        System.out.println("Send Http GET request");
        // Catch bad url through IOException
        try {
            obj.sendGetReq();
        } catch (IOException e) {
            System.out.println("Bad Url?");
        }
    }
    // Send get request
    private void sendGetReq() throws Exception {
        String url = "https://www.google.com/search?q=jerbl";
        //String url = "https://www.isthisrealandstuff.com/search?q=jerbl";
        // Establish url and open connection
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        // Set type of request and add header
        httpClient.setRequestMethod("GET");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        // Variable for response code
        int responseCode = httpClient.getResponseCode();
        // Print connection to screen
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            // Print result to screen
            System.out.println(response.toString());
        }
    }
}
