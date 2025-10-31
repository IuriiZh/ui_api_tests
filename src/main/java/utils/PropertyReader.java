package utils;

import lombok.Getter;
import lombok.SneakyThrows;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

// Singleton pattern
public class PropertyReader {
    @Getter
    private static PropertyReader instance = new PropertyReader();
    private final Properties properties;

    public static String urlUI = PropertyReader.getInstance().getUIUrl();

    public static String urlAPI = PropertyReader.getInstance().getAPIUrl();
    public static String urlAPIKeyHeader = PropertyReader.getInstance().getAPIKeyHeader();
    public static String urlAPIKey = PropertyReader.getInstance().getAPIKey();
    public static String username = PropertyReader.getInstance().getUsername();
    public static String password = PropertyReader.getInstance().getPassword();
    public static String email = PropertyReader.getInstance().getEmail();

    public static String API_token = PropertyReader.getInstance().getAPToken();
    public static String API_errorLoginMsg = PropertyReader.getInstance().getAPIErrorLoginMsg();
    public static String id = PropertyReader.getInstance().getId();
    public static String job = PropertyReader.getInstance().getJob();

    //public static String emailErr = PropertyReader.getInstance().getEmailErr();
    //public static String errorMsgRegister = PropertyReader.getInstance().getErrorMsgRegister();

    @SneakyThrows
    public PropertyReader() {
        properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/test/resources/test.properties"))) {
            properties.load(reader);
        }
    }
    public String getHeadless() { return properties.getProperty("headless"); }
    public String getBrowser() {return properties.getProperty("browser");}

    public String getUIUrl(){ return properties.getProperty("UI_url");}
    public String getUIPassword(){
        return properties.getProperty("UI_password");
    }
    public String getUIUsername(){
        return properties.getProperty("UI_username");
    }

    public String getAPIUrl(){
        return properties.getProperty("API_url");
    }
    public String getAPIKeyHeader(){
        return properties.getProperty("API_KeyHeader");
    }
    public String getAPIKey(){
        return properties.getProperty("API_Key");
    }
    public String getAPToken() {return properties.getProperty("API_token");}

    public String getUsername() {return properties.getProperty("username");}
    public String getEmail() {return properties.getProperty("email");}
    public String getPassword() {return properties.getProperty("password");}



    public String getAPIErrorLoginMsg() {return properties.getProperty("API_errorLoginMsg");}

    public String getId() {return properties.getProperty("id");}
    public String getJob() {return properties.getProperty("job");}

    //public String getEmailErr() {return properties.getProperty("emailErr");}
    // public String getErrorMsgRegister() {return properties.getProperty("errorMsgRegister");}
}
