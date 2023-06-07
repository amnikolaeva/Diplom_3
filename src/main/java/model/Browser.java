package model;

public enum Browser {

    CHROME("Chrome", System.getenv("HOME") + "/Apps/WebDriver/bin/chromedriver"),

    YANDEX("Yandex", System.getenv("HOME") + "/WebDriver/bin/yandexdriver");

    /**
     * Имя браузера
     */
    private String name;

    /**
     * Путь к драйверу
     */
    private String path;

    Browser(String name, String path) {
        this.name = name;
        this.path = path;
    }

    /**
     * Получить путь к драйверу по имени браузера
     * @param browserName имя браузера
     * @return путь к драйверу
     */
    public static String getPath(String browserName) {
        for (Browser browser : Browser.values()) {
            if (browser.name.equals(browserName)) {
                return browser.path;
            }
        }
        return null;
    }
}
