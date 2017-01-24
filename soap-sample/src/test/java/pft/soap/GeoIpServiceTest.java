package pft.soap;

import net.webservicex.GeoIP;
import org.testng.annotations.Test;
import net.webservicex.GeoIPService;

import static org.testng.Assert.assertEquals;

/**
 * Created by kshyniakov on 24.01.2017.
 */
public class GeoIpServiceTest {

    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("81.26.159.13");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }
}
