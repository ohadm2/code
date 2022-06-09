import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectivityV2 {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "https://ppp-test.safecharge.com";

	private static final String POST_URL = "https://ppp-test.safecharge.com";

	private static final String POST_PARAMS = "";

	public static void main(String[] args) 
	{
		try
		{
			sendGET();
			System.out.println("GET DONE");
			
			sendPOST();
			System.out.println("POST DONE");
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}

	private static void sendGET() throws IOException {
		URL obj = new URL(GET_URL);
		
		System.out.println("\n\n");
		System.out.println("=============================================================================================");
		System.out.println("Trying to make an HTTP GET request to address: '" + GET_URL + "'....\n");
		System.out.println("=============================================================================================");
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		
		System.out.println("GET Response Code : " + responseCode + "\n");
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("Could not fetch data from the GET request (see the response code for the reason).\n\n");
		}

	}

	private static void sendPOST() throws IOException {
		URL obj = new URL(POST_URL);
	
		System.out.println("=============================================================================================");
		System.out.println("Trying to make an HTTP POST request to address: '" + POST_URL + "'....\n");
		System.out.println("=============================================================================================");

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		
		System.out.println("POST Response Code : " + responseCode + "\n");

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("Could not fetch data from the POST request (see the response code for the reason).");
		}
	}

}  
