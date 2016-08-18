package Experimental;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.*;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by First on 09.08.2016.
 */
public class Legs{

    private final String USER_AGENT = "Mozilla/5.0";

    public void writerBlya()  throws IOException {
        FileWriter writer = new FileWriter("C:/vkmusic.txt", true);

        writer.write("jhjhkjsfgkbsh\n" + "\n");
        writer.write("o/  \\o/  " );
        writer.write("\r");
        writer.write("\\o");

        writer.flush();

        }
    @Test
    public void vkGetMusicPostRequest() throws IOException {
        CookieHandler.setDefault(new CookieManager());
        FileWriter httpFileWriter = new FileWriter("C:/vkmusicpost.txt", true);
        //Адрес, куда будет стучаться запрос
        String url = "https://vk.com/dev/";

        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        //Request parameters and other properties
        List<NameValuePair> params= new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("act","a_run_method"));
        params.add(new BasicNameValuePair("al","1"));
        params.add(new BasicNameValuePair("hash","1471494582:5097bcf11763b99ab8"));
        params.add(new BasicNameValuePair("method","audio.get"));
        params.add(new BasicNameValuePair("param_count","549")); //Здесь указал большее число чем есть записей, просто так, потому, что захотел
        params.add(new BasicNameValuePair("param_need_user","0"));
        params.add(new BasicNameValuePair("param_owner_id","16930562"));
        params.add(new BasicNameValuePair("param_v","3.0"));


        //Adding headers
        httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.setHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
        httpPost.setHeader("Connection", "keep-alive");
        //httpPost.setHeader("Content-Length", "1450");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Host", "vk.com");
        httpPost.setHeader("Referer", "https://vk.com/dev/");
//        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
//        httpPost.setHeader("Connection", "keep-alive");


        //Request executing
        HttpResponse response = client.execute(httpPost);

        //HttpEntity entity = response.getEntity();

        //Here I put BufferReader because have no idea how to do this without this
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));


        //Next code is sending
        StringBuffer result = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine())!=null){

            System.out.println(result);
            httpFileWriter.write(String.valueOf(result));
            result.append(line);

        }
        bufferedReader.close();
        System.out.println("---------------------------==============================================================");
        System.out.println(response);





//        if (entity != null){
//            InputStream instream = entity.getContent();
//            try {
//                //Some logic here, like making array of music that will hold author, name and url
//                StringWriter httpResponceWriter = new StringWriter();
//                IOUtils.copy(instream, httpResponceWriter);
//                String httpResponceString = httpResponceWriter.toString();
//                while ( != null)
//                System.out.println();
//            } finally {
//                instream.close();
//            }
//        }
    }

@Test
public void sendPost() throws Exception {

    String url = "https://selfsolve.apple.com/wcResults.do";

    HttpClient client = new DefaultHttpClient();
    HttpPost post = new HttpPost(url);

    // add header
    post.setHeader("User-Agent", USER_AGENT);

    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
    urlParameters.add(new BasicNameValuePair("cn", ""));
    urlParameters.add(new BasicNameValuePair("locale", ""));
    urlParameters.add(new BasicNameValuePair("caller", ""));
    urlParameters.add(new BasicNameValuePair("num", "12345"));

    post.setEntity(new UrlEncodedFormEntity(urlParameters));

    HttpResponse response = client.execute(post);
    System.out.println("\nSending 'POST' request to URL : " + url);
    System.out.println("Post parameters : " + post.getEntity());
    System.out.println("Response Code : " +
            response.getStatusLine().getStatusCode());

    BufferedReader rd = new BufferedReader(
            new InputStreamReader(response.getEntity().getContent()));

    StringBuffer result = new StringBuffer();
    String line = "";
    while ((line = rd.readLine()) != null) {
        result.append(line);
    }

    System.out.println(result.toString());

}



}
