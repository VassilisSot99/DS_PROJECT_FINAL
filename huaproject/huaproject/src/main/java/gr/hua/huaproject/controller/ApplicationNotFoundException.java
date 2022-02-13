package gr.hua.huaproject.controller;

public class ApplicationNotFoundException extends RuntimeException {

    public  ApplicationNotFoundException(String exception) {
        super(exception);
    }
}
