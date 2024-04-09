package pages;

import utilites.ConfProperties;

public class BasePage {
    static final String BASE_URL = new ConfProperties().getProperty("base-url");
}