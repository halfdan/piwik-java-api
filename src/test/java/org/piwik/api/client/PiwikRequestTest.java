package org.piwik.api.client;

import java.util.HashMap;
import java.util.Map;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class PiwikRequestTest extends TestCase
{
	private PiwikRequest request;
	private Map<String,String> params;
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PiwikRequestTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PiwikRequestTest.class );
    }
	
	@Before
	@Override
	public void setUp() {
		request = new PiwikRequest();
		params = new HashMap<String,String>();
		params.put("module", "API");
		params.put("method", "Actions.get");
		params.put("idSite", "1");
		params.put("period", "day");
		params.put("date", "today");
		params.put("token_auth", "anonymous");
		params.put("format", "JSON");
	}

	/**
	 * Test of generateUri method
	 */
	public void testGenerateUri() {
		String expected = "index.php?module=API&method=Actions.get&format=JSON&date=today&period=day&idSite=1&token_auth=anonymous";
		request.setParams(params);
		System.out.println(request.generateUri());
		assertEquals(expected, request.generateUri());
	}
}
