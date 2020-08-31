package at.nullpointer.hue.controller.huecontroller;

import java.io.IOException;

public class HuecontrollerApplication {

    public static void main(String[] args) {
        HueBridgeConnector bridgeConnector = new HueBridgeConnector( "http://myip/api/mykey" ) ;

        try
        {
            //bridgeConnector.setLampState( 4, "{\"on\":true}" ) ;
            //bridgeConnector.setLampState( 4, "{\"hue\":"+AvailableColors.BLUE+"}" ) ;
            bridgeConnector.setLampState(4, "{\"bri\": 254}");
        }
        catch( IOException | HueException e )
        {
            System.out.println( "Error: " + e.getMessage() ) ;
        }
    }

}
