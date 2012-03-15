package edu.fhda.argos;

/**
 * Describes an error returned from the MAPS server including the HTTP status code, and the body of the response. Use
 * getMessage(...) to get the response body as a String.
 * @version 1.0
 * @author Matt Rapczynski
 */
public class MAPSException extends Exception {
    
    private int httpStatusCode;
    
    public MAPSException(String httpResponse, int statusCode) {
        super("\n" + "HTTP Status Code: " + Integer.toString(statusCode) + "\n" + httpResponse);
        this.httpStatusCode = statusCode;
    }
    
    public int getHTTPStatus() {
        return  httpStatusCode;
    }
    
}
