package ws.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * User: Schuller Tom
 */
@WebService
public class SoapWebService {
    /*
    http://localhost:8080/tp4/SoapWebService?wsdl
     */
    @WebMethod(operationName = "testLogin")
    public boolean validateUsernamePassword(String username, String password) {
        return username.equals(password);
    }
}