package edu.ccrm.config;

// Singleton for global configuration
public class AppConfig {
    private static AppConfig instance;
    private String dataPath;

    private AppConfig() {
        this.dataPath = "data"; // default folder
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }
}
