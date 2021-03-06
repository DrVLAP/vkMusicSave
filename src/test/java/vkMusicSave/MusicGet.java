package vkMusicSave;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by First on 27.08.2016.
 */
public class MusicGet {

    private String vkGetMusicJsonString;
    private String vkGetMusicMusicCount;
    private JSONParser jsonParser = new JSONParser();
    private List<String> audioNameUrl = new ArrayList<String>();

    //Method to get number of audio.
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
        JSONObject jsonAudioCount = (JSONObject) jsonParser.parse(vkGetMusicMusicCount);
        vkGetMusicMusicCount = String.valueOf(jsonAudioCount.get("response"));
        System.out.println("vkGetMusicMusicCount: "+vkGetMusicMusicCount);
    }

    //Method to build report with only audio titles and url's
    public void GetMusicArrayForReport() throws ParseException, IOException {
        FileWriter AudioArrayFileWriter = new FileWriter("C:/vkmusicarray.txt", true);

        JSONObject JSONObject = (JSONObject) jsonParser.parse(vkGetMusicJsonString);
        JSONObject jsonAudioArray = (JSONObject) JSONObject.get("response");
        JSONArray audio = (JSONArray) jsonAudioArray.get("items");

        Iterator i = audio.iterator();
        while (i.hasNext()){
            JSONObject music = (JSONObject) i.next();

            //Удаление некорректных символов из названия композиции
            String result = (music.get("artist"))+" - "+String.valueOf(music.get("title"));
            String[] forbiddenSymbols = new String[] {"<", ">", ":", "\"", "/", "\\", "|", "?", "*", ".", "(", ")"};
            for (String forbiddensymbol: forbiddenSymbols)
            {
                result = StringUtils.replace(result, forbiddensymbol, "");
            }
            audioNameUrl.add(result+"|"+String.valueOf(music.get("url")));
            AudioArrayFileWriter.write(String.valueOf(music.get("artist"))+": ");
            AudioArrayFileWriter.write(String.valueOf(music.get("title"))+";");
            AudioArrayFileWriter.write("\n");
            AudioArrayFileWriter.write(String.valueOf(music.get("url")));
            AudioArrayFileWriter.write("\n");
            AudioArrayFileWriter.write("--------------------------");
            AudioArrayFileWriter.write("\n");
            AudioArrayFileWriter.flush();
        }
    }

    //Request to the vk api with method audio.get to get all requiered audio
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

    public void AudioSave() throws IOException {
        for (String audioUrl: audioNameUrl) {
            System.out.println(audioUrl);
            String[] audioNameUrlSplit = audioUrl.split("\\|");
            String result =  audioNameUrlSplit[0];

            System.out.println(audioNameUrlSplit[1]);
            URL url = new URL(audioNameUrlSplit[1]);
            System.out.println(url);
//        File destination = new File("E:\\VK Music Collection\\"+audiosplit[0]+".mp3");
            File destination = new File("E:\\VK Music Collection\\"+result+".mp3");
            FileUtils.copyURLToFile(url, destination);
        }
    }


}
