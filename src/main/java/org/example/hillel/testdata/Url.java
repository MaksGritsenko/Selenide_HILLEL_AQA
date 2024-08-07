package org.example.hillel.testdata;

public enum Url {

    HOME_HILLEL_URL("https://ithillel.ua/"),
    HILLEL_AUTH_URL("https://lms.ithillel.ua/auth"),
    HILLEL_REG_URL("https://lms.ithillel.ua/auth/registration/details"),
    MAILINATOR_MAIN_URL("https://www.mailinator.com/"),
    HILLEL_STORE("https://lms.ithillel.ua/store/recommendations");

    private final String url;

    Url(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
