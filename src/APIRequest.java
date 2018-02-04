import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class APIRequest {

    private HTMLRequest htmlRequest;
    private WeatherFrame frame;

    public APIRequest(HTMLRequest htmlRequest, WeatherFrame frame) {
        this.htmlRequest = htmlRequest;
        this.frame = frame;
    }

    public void update() throws Exception {
        String response = htmlRequest.get();
        if (response == null) {
            System.err.println("ERROR: response = null");
            return;
        }
        JSONParser parser = new JSONParser();
        JSONObject tree = (JSONObject) parser.parse(response);

        //WEATHER
        JSONObject weather = (JSONObject)((JSONArray)tree.get("weather")).get(0);
        frame.setStatus((String)weather.get("main"));

        //MAIN
        JSONObject main = (JSONObject) tree.get("main");
        frame.setTemperature((double)main.get("temp"));
        frame.setHumidity((long)main.get("humidity"));

        //WIND
        JSONObject wind = (JSONObject) tree.get("wind");
        String speed = String.valueOf(wind.get("speed"));
        frame.setWindSpeed(Double.parseDouble(speed));

        //SYS
        JSONObject sys = (JSONObject) tree.get("sys");
        String country = (String)sys.get("country");
        frame.setSunrise((long)sys.get("sunrise"));
        frame.setSunset((long)sys.get("sunset"));

        //NAME
        frame.setCityName((String)tree.get("name")+", "+country);

    }

    public HTMLRequest getHtmlRequest() {
        return htmlRequest;
    }

    public void setHtmlRequest(HTMLRequest htmlRequest) {
        this.htmlRequest = htmlRequest;
    }
}
