package prnvshrn.foodwatch.appProcesses;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by admin on 12-Mar-17.
 */

public class URLfunctions
{
public static void main(String args[])
{

}

    public static void getFoodData(String item)
    {

        String appID = "";
        String appKey = "";
        String fooditem  = item.replaceAll("\\s+", "%20");
        String requesturl = "https://api.nutritionix.com/v1_1/search/"+fooditem+"?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_serving_size_qty%2Cnf_serving_size_unit%2Cnf_calories%2Cnf_total_fat%2Cnf_cholestrol%2Cnf_sodium%2Cnf_total_carbohydrate%2Cnf_dietary_fibre%2Cnf_sugars%2Cnf_protein&appId="+appID+"&appKey="+appKey;

        try
        {

            URL url = new URL(requesturl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            StringBuilder result = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sBuilder = new StringBuilder();
            String resultline = "";
            while((resultline=br.readLine())!=null)
            {

                sBuilder.append(resultline+"/n");
            }

            JSONObject jsonObject = new JSONObject(sBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("hits");
            Integer len = jsonArray.length();
            for(int i=0;i<len;i++)
            {
                JSONObject innerjsonObject = jsonArray.getJSONObject(i).getJSONObject("fields");
                                ProjectVariables.food_item = item;
                ProjectVariables.food_quantity = String.valueOf(innerjsonObject.getInt("nf_serving_size_qty"))+" "+innerjsonObject.getString("nf_serving_size_unit");
                ProjectVariables.nf_calories = "Calories (kcal): "+String.valueOf(innerjsonObject.getDouble("nf_calories"));
                ProjectVariables.nf_total_fat = "Total Fat (g): "+String.valueOf(innerjsonObject.getDouble("nf_total_fat"));

                if(innerjsonObject.toString().contains("nf_cholestrol"))
                    ProjectVariables.nf_cholestrol = "Cholestrol (g): "+String.valueOf(innerjsonObject.getDouble("nf_cholestrol"));
                else
                    ProjectVariables.nf_cholestrol = "Cholestrol (g): NA";

                if(innerjsonObject.toString().contains("nf_sodium"))
                ProjectVariables.nf_sodium = "Sodium (mg): "+String.valueOf(innerjsonObject.getDouble("nf_sodium"));
                else
                ProjectVariables.nf_sodium = "Sodium(mg): NA";

                if(innerjsonObject.toString().contains("nf_total_carbohydrate"))
                ProjectVariables.nf_total_carbohydrate = "Carbs (g): "+String.valueOf(innerjsonObject.getDouble("nf_total_carbohydrate"));
                else
                ProjectVariables.nf_total_carbohydrate = "Carbs (g): NA";

                if(innerjsonObject.toString().contains("nf_dietary_fiber"))
                ProjectVariables.nf_dietary_fibre = "Fibres (g): "+String.valueOf(innerjsonObject.getDouble("nf_dietary_fiber"));
                else
                ProjectVariables.nf_dietary_fibre = "Fibres (g): NA";

                if(innerjsonObject.toString().contains("nf_sugars"))
                ProjectVariables.nf_sugars = "Sugars (g): "+String.valueOf(innerjsonObject.getDouble("nf_sugars"));
                else
                ProjectVariables.nf_sugars = "Sugars (g): NA";

                if(innerjsonObject.toString().contains("nf_protein"))
                ProjectVariables.nf_protein = "Protein (g): "+String.valueOf(innerjsonObject.getDouble("nf_protein"));
                else
                ProjectVariables.nf_protein = "Protein (g): NA";

            break;
            }

        }
        catch(Exception ex)
        {
            System.out.println("Error found:"+ex.toString());
        }

    }
}
