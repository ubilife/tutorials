package client;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * This code requires the HttpClient library (part of Apache HttpComponents).
 *
 * http://hc.apache.org/
 *
 * You will also need Gson library:
 *
 * https://code.google.com/p/google-gson/
 *
 * Download libraries, unzip them, and add jar files under lib to to your
 * project's library path. You can also find the jar files in this project's lib
 * folder.
 *
 * @author Ubilife Lab
 */
public class RandomPersonClientDesktop {

    private final String PATH_GET = "http://localhost:8080/RandomPersonApp/webresources/person/random";
    private final String PATH_POST = "http://localhost:8080/RandomPersonApp/webresources/person/";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new RandomPersonClientDesktop().start();

    }

    public void start() {

        // first test sending (POST)
        Person p1 = new Person("John Doe", 35);
        Person p2 = new Person("Jane Doe", 25);
        Person p3 = new Person("Charles Doe", 5);
        testPOST(p1);
        testPOST(p2);
        testPOST(p3);

        // then test receiving (GET)
        p1 = testGET();
        if (p1 != null) {
            System.out.println("Received random person: " + p1.getName());
        } else {
            System.out.println("GET request failed.");
        }

    }

    private Person testGET() {

       

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(PATH_GET);

        get.setHeader("Accept", "application/json");

        HttpEntity entity;
        Person person = null;
        
        try {
            HttpResponse response = client.execute(get);

            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("GET error: " + response.getStatusLine().getStatusCode());
                return null;
            }

            // get the response data (JSON)
            entity = response.getEntity();            
            String json = EntityUtils.toString(entity);

            // convert JSON to Person
            Gson gson = new Gson();
            person = gson.fromJson(json, Person.class);
            

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return person;
    }
    
    private void testPOST(Person p) {

        // Convert Person to JSON   
        Gson gson = new Gson();
        String json = gson.toJson(p);

        // Make a HTTP POST request
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(PATH_POST);

        post.addHeader("Content-Type", "application/json");
        
        try {
            StringEntity se = new StringEntity(json);
            post.setEntity(se);

            HttpResponse response = client.execute(post);

            if (response.getStatusLine().getStatusCode() == 204) {
                System.out.println("POST request sent successfully: "+json);
            } else {
                System.out.println("Error occurred in POST: " + response.getStatusLine().getStatusCode());
            }

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
