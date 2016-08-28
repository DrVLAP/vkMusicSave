package Experimental;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * Created by First on 09.08.2016.
 */
public class Table extends Furniture {

    public String jsonresponce;

    @Test
    public void jsonArrayParse() throws ParseException {
        jsonresponce = "{\"response\":{\"count\":565,\"items\":[{\"id\":456239115,\"owner_id\":16930562,\"artist\":\"Batman\",\"title\":\"Расскажи Снегурочка, где детонатор?!\",\"duration\":25,\"date\":1472039249,\"url\":\"http:\\/\\/cs422219.vk.me\\/u15927588\\/audios\\/b345197a2350.mp3?extra=c6nE9mv5Zl54HP7qnJCFN756w1oOodPzVZFJ9GvbnYXuVHam64lzv7MLlX-K_T9tb8v78n9Km-rnVn7mNkc8P1QgfSK5k0YDHgw-nrqYXkvOyAKRY7GDMfESTDp3UvPmhXrYQPjv5io\",\"genre_id\":1001},{\"id\":456239114,\"owner_id\":16930562,\"artist\":\"Nightmare\",\"title\":\"the WORLD(Death note oppening 1)\",\"duration\":228,\"date\":1472039237,\"url\":\"http:\\/\\/cs4422.vk.me\\/u43898016\\/audios\\/438de4e446b8.mp3?extra=-BnIBNjzdviAVPcuozLRMdANo8q53UP_nyfq7QkgVjkFMmOH7ZviWE1Ni-r6wPOh61zRZOuqEEQErT7dD8usWXR6TU8ogvIdElA_ZvHUKHldutJcV0aPUGJla-HNnyg-P-d5i8apTKg\",\"lyrics_id\":16011807,\"genre_id\":18},{\"id\":456239113,\"owner_id\":16930562,\"artist\":\"Jonathan Young\",\"title\":\"The World (Full Version)\",\"duration\":229,\"date\":1472038446,\"url\":\"http:\\/\\/cs611931.vk.me\\/u8204884\\/audios\\/dc2e691cc55f.mp3?extra=FhlFeQDAYu63GpzJsm_h3T7hTOaUc7GpLrZ-O-xQGPN6OrZsYs_ZLgf8mFxawl_ewgqS0OnEzwAT6XWi7SZZ5_qZ4yIKKVHl2AfXTApS6KJG1mPZhjqSdtID45pjtzdJsp6khfYn5nI\"},{\"id\":456239111,\"owner_id\":16930562,\"artist\":\"Immediate Music\",\"title\":\"Def Con\",\"duration\":141,\"date\":1472038362,\"url\":\"http:\\/\\/cs4-2v4.vk-cdn.net\\/p19\\/50cbb2a47a16f5.mp3?extra=PSc9tFNJUPSlCLwsUFa3VTc6FwHYdAW013yWCndxRLpFV48AfLpeGIqnO-Kv2lHKUxlTcKWnM8CPOnwfK2f62UUN9MS4Hm_L4L2bmMNXnb42zzrUzwgVeg0uOdnT637ir3W4rI4CqXU\",\"lyrics_id\":218363,\"genre_id\":18},{\"id\":456239110,\"owner_id\":16930562,\"artist\":\"Batman \",\"title\":\"ГДЕ ДЕТОНАТОР?!\",\"duration\":20,\"date\":1472026747,\"url\":\"http:\\/\\/cs4-4v4.vk-cdn.net\\/p24\\/ec1505ff2721f2.mp3?extra=-g7fDNX4kHYia1f04tnqD05jvHGJGdz_qsvPmJT-y5rUURn_6dltBxM_1_yLSafJgLtnfYI_EB6XO4VtwDw_hcooBqJ0C9yjn0e-JBQH-kors-ZBnxrR8QcWLaJj0RHiVe3yfnd3k5A\",\"lyrics_id\":96348405,\"genre_id\":18}]}}";

        JSONParser jsonParser = new JSONParser();
        JSONObject JSONObject = (JSONObject) jsonParser.parse(jsonresponce);
        JSONObject audioObj = (JSONObject) JSONObject.get("response");
        JSONArray audio = (JSONArray) audioObj.get("items");

        Iterator i = audio.iterator();
        while (i.hasNext()){
            JSONObject audioObject = (JSONObject) i.next();
            System.out.println("artist:" + audioObject.get("artist"));
            System.out.println("title:" + audioObject.get("title"));
            System.out.println("url:" + audioObject.get("url"));
            System.out.println("--------------------------");

        }

    }

}
