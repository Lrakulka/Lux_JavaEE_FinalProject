package ws.soap;

import javax.jws.WebService;

/**
 * User: Schuller Tom
 */
@WebService
public class SoapCalculator {
    /*
    http://localhost:8080/tp4/SoapCalculator?wsdl
     */
    public int sum(int add1, int add2) {
        return add1 + add2;
    }

    public int multiply(int mul1, int mul2) {
        return mul1 * mul2;
    }
}