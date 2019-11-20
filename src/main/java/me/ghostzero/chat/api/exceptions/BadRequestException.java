package me.ghostzero.chat.api.exceptions;

public class BadRequestException extends APIException
{
    private static final long serialVersionUID = 2135244624443720791L;

    private String requestBody;
    private String responseBody;


    public BadRequestException(String requestBody, String responseBody)
    {
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }


    public String getRequest()
    {
        return requestBody;
    }

    public String getResponse()
    {
        return responseBody;
    }

    @Override
    public String getMessage()
    {
        return "An Error occured.\n" + "Request: " + requestBody + "\n" + "Response: " + responseBody;
    }
}
