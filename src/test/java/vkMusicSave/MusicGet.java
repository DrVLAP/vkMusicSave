package vkMusicSave;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by First on 27.08.2016.
 */
public class MusicGet {

    private String vkGetMusicJsonString = new String();
    private String vkGetMusicMusicCount = new String();
    private JSONParser jsonParser = new JSONParser();
    JSONObject jsonAudioCount;
    JSONObject jsonAudioArray;
    JSONArray audio;



    public void GetMusicCount(String token) throws InterruptedException, IOException, ParseException {
        String url = "https://api.vk.com/method/audio.getCount";
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params= new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("owner_id","16930562"));
        params.add(new BasicNameValuePair("v","3.0"));
        params.add(new BasicNameValuePair("access_token",token));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse response = client.execute(httpPost);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String line;
        while ((line = bufferedReader.readLine())!=null){
            vkGetMusicMusicCount = line;}
        System.out.println("vkGetMusicMusicCount: " + vkGetMusicMusicCount + ";");

        GetMusicCountParse();
    }

    // get a String with number of audio from the JSON object
    public void GetMusicCountParse() throws ParseException {
        jsonAudioCount = (JSONObject) jsonParser.parse(vkGetMusicMusicCount);
        vkGetMusicMusicCount = String.valueOf(jsonAudioCount.get("response"));
        System.out.println("vkGetMusicMusicCount: "+vkGetMusicMusicCount);
    }

    public void GetMusicArrayForReport() throws ParseException, IOException {
        FileWriter AudioArrayFileWriter = new FileWriter("C:/vkmusicarray.txt", true);


        jsonAudioArray = (JSONObject) jsonParser.parse(vkGetMusicJsonString);
        audio = (JSONArray) jsonAudioArray.get("response");
        Iterator i = audio.iterator();

        while (i.hasNext()){
            JSONObject music=(JSONObject) i.next();

            AudioArrayFileWriter.write(String.valueOf(music.get("artist"))+": ");
            AudioArrayFileWriter.write(String.valueOf(music.get("title"))+";");
            AudioArrayFileWriter.write("\n");
            AudioArrayFileWriter.write(String.valueOf(music.get("url")));
            AudioArrayFileWriter.write("--------------------------");
            AudioArrayFileWriter.flush();
        }
    }

    public void GetMusicList(String token) throws InterruptedException, IOException {

    String url = "https://api.vk.com/method/audio.get";
    HttpClient client = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(url);
    List<NameValuePair> params= new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("owner_id","16930562"));
    params.add(new BasicNameValuePair("need_user","0"));
    params.add(new BasicNameValuePair("count",vkGetMusicMusicCount));
    params.add(new BasicNameValuePair("v","5.53"));
    params.add(new BasicNameValuePair("access_token",token));
    httpPost.setEntity(new UrlEncodedFormEntity(params));
    HttpResponse response = client.execute(httpPost);

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));


    //Next code is sending request results to the file
    FileWriter httpFileWriter = new FileWriter("C:/vkmusicpost.txt", true);
    StringBuffer result = new StringBuffer();
    String line;
    while ((line = bufferedReader.readLine())!=null){
        vkGetMusicJsonString = line;
        System.out.println(line);
        httpFileWriter.write(line);
        httpFileWriter.write("\n");
        result.append(line);
        httpFileWriter.flush();
    }
    bufferedReader.close();
}
}
