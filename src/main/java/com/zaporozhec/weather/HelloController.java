
    package com.zaporozhec.weather;

    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.net.URL;
    import java.net.URLConnection;
    import javafx.fxml.FXML;
    import javafx.scene.control.Button;
    import javafx.scene.control.TextField;
    import javafx.scene.text.Text;
    import org.json.JSONObject;

    public class HelloController {


        @FXML
        private Text bar;

        @FXML
        private TextField city;

        @FXML
        private Button getData;

        @FXML
        private Text temp_feel;

        @FXML
        private Text temp_info;

        @FXML
        private Text temp_max;

        @FXML
        private Text temp_min;


        @FXML
        void initialize() {
            getData.setOnAction(actionEvent -> {
                        String getUserCity = city.getText().trim();
                        if (!getUserCity.equals("")) {
                            String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=API_ID=metric");
                            if (!output.isEmpty()) {
                                JSONObject obj = new JSONObject(output);
                                temp_info.setText("Температура: " + obj.getJSONObject("main").getDouble("temp"));
                                temp_feel.setText("Відчувається: " + obj.getJSONObject("main").getDouble("feels_like"));
                                temp_max.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
                                temp_min.setText("Мінімум: " + obj.getJSONObject("main").getDouble("temp_min"));
                                bar.setText("Тиск: " + obj.getJSONObject("main").getDouble("pressure"));
                            }
                        }

            });
        }

        private static String getUrlContent(String urlAdress) {
            StringBuffer content = new StringBuffer();

            try {
                URL url = new URL(urlAdress);
                URLConnection urlConn = url.openConnection();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String line;

                while((line = bufferedReader.readLine()) != null) {
                    content.append(line + "\n");
                }
                bufferedReader.close();
            } catch(Exception e) {
                System.out.println("Місто не знайдено");
            }
            return content.toString();
        }
    }
