package Experimental;

import com.squareup.okhttp.*;
import okio.Buffer;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import vkMusicSave.TokenGrab;

import java.io.*;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void vkGetMusicPostRequest() throws IOException, InterruptedException {

        TokenGrab token = new TokenGrab();
        //token.GivePermission();
        //System.out.println(token.getToken());
        FileWriter httpFileWriter = new FileWriter("C:/vkmusicpost.txt", true);
        //Адрес, куда будет стучаться запрос
        String url = "https://api.vk.com/method/audio.get?params[owner_id]=16930562&params[need_user]=1&params[count]=549&params[v]=3.0&access_token=15616d454e45e107dd66e5d39bb5d15caefef66a19f4de9018779f24c9649b71d873381fd4ab199c3afcf";
        //"Этот адрес использовался раньше
        //String url = "https://api.vk.com/";


        //This string is working. If will have future problems will return to this creation of the httpClient
        HttpClient client = HttpClients.createDefault();



        HttpPost httpPost = new HttpPost(url);

        //Request parameters and other properties
        List<NameValuePair> params= new ArrayList<NameValuePair>();
        /*params.add(new BasicNameValuePair("act","a_run_method"));
        params.add(new BasicNameValuePair("al","1"));

        params.add(new BasicNameValuePair("method","audio.get"));
        params.add(new BasicNameValuePair("param_count","549"));
        params.add(new BasicNameValuePair("param_need_user","0"));
        params.add(new BasicNameValuePair("param_owner_id","16930562"));
        params.add(new BasicNameValuePair("param_v","3.0"));
        params.add(new BasicNameValuePair("hash","1471494582:5097bcf11763b99ab8"));

/*
        //Adding headers
        httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,/*;q=0.8");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.setHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
        httpPost.setHeader("Connection", "keep-alive");
        //httpPost.setHeader("Content-Length", "145");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Host", "vk.com");
        httpPost.setHeader("Referer", "https://api.vk.com/method/audio.get?params[owner_id]=16930562&params[need_user]=1&params[count]=549&params[v]=3.0&access_token=15616d454e45e107dd66e5d39bb5d15caefef66a19f4de9018779f24c9649b71d873381fd4ab199c3afcf"  );//+token.getToken());
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
//        httpPost.setHeader("Connection", "keep-alive");*/

        httpPost.setEntity(new UrlEncodedFormEntity(params));
        //Request executing
        System.out.println(httpPost.getMethod());
        System.out.println("Заголовки:");
        System.out.println(Arrays.toString(httpPost.getAllHeaders()));
        System.out.println("Сущности:");
        System.out.println(httpPost.getEntity());
        System.out.println("Весь запрос целиком");
        //System.out.println(httpPost.); пока что не получается его получить
        System.out.println("Uri");
        System.out.println(httpPost.getURI());
        System.out.println("Отправление запроса;");
        HttpResponse response = client.execute(httpPost);

        //HttpEntity entity = response.getEntity();

        //Here I put BufferReader because have no idea how to do this without this
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));


        //Next code is sending request results to the file
        StringBuffer result = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine())!=null){

            System.out.println(line);
            httpFileWriter.write(line);
            httpFileWriter.write("\n");
            result.append(line);
            httpFileWriter.flush();
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
    public void ls() throws IOException {

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "act=a_run_method&al=1&method=audio.get&param_count=549&param_need_user=0&param_owner_id=16930562&param_v=3.0&hash=1471494582%3A5097bcf11763b99ab8");
        Request request = new Request.Builder()
                .url("https://vk.com/dev/")
                .post(body)
                .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .addHeader("accept-encoding", "gzip, deflate, br")
                .addHeader("accept-language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("host", "vk.com")
                .addHeader("referer", "https://vk.com/dev/")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .addHeader("connection", "keep-alive")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "449d7114-aeed-37f7-5b17-55cf7bdc71f8")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println("response request:");
        System.out.println(request.newBuilder().build().toString());
        System.out.println("response body:");
        System.out.println(response.body().string());


    }





}
