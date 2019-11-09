package com.tradeshift.tree.management.test;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeshift.tree.management.api.NodeHelper;
import com.tradeshift.tree.management.model.Node;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class NodeHelperTest 
    extends TestCase
{
	
	NodeHelper ex = null;
	ObjectMapper mapper = null;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public NodeHelperTest( String testName )
    {
        super( testName );
        mapper = new ObjectMapper();
		ex = new NodeHelper(NodeHelper.intializeData());
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( NodeHelperTest.class );
    }

    /**
     * Test to get descendants of root node
     */
    public void testGetDescedantsOfRoot()
    {
    	try {
	    	List<Node> results = ex.getDescedents("root");
			// Java objects to JSON string - pretty-print
			String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
			System.out.println("\nDescedants of root: "+jsonInString);
	        assertTrue( jsonInString != null );
    	}catch(Exception exp) {
    		assertTrue(false);
    	}
    }
    
    /**
     * Test to get descendants of A node
     */
    public void testGetDescedantsOfA()
    {
    	try {
	    	List<Node> results = ex.getDescedents("A");
			// Java objects to JSON string - pretty-print
			String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
			System.out.println("\nDescedants of A: "+jsonInString);
	        assertTrue( jsonInString != null );
    	}catch(Exception exp) {
    		assertTrue(false);
    	}
    }
    
    /**
     * Test to get descendants of B node
     */
    public void testGetDescedantsOfB()
    {
    	try {
	    	List<Node> results = ex.getDescedents("B");
			// Java objects to JSON string - pretty-print
			String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
			System.out.println("\nDescedants of B: "+jsonInString);
	        assertTrue( jsonInString != null );
    	}catch(Exception exp) {
    		assertTrue(false);
    	}
    }
    
    /**
     * Test to get descendants of C node
     */
    public void testGetDescedantsOfC()
    {
    	try {
	    	List<Node> results = ex.getDescedents("C");
			// Java objects to JSON string - pretty-print
			String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
			System.out.println("\nDescedants of C: "+jsonInString);
	        assertTrue( jsonInString != null );
    	}catch(Exception exp) {
    		assertTrue(false);
    	}
    }
    
    /**
     * Test to get descendants of D node
     */
    public void testGetDescedantsOfD()
    {
    	try {
	    	List<Node> results = ex.getDescedents("D");
			// Java objects to JSON string - pretty-print
			String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
			System.out.println("\nDescedants of D: "+jsonInString);
	        assertTrue( jsonInString != null );
    	}catch(Exception exp) {
    		assertTrue(false);
    	}
    }
    
    /**
     * Test to change parent from A to root for the node C
     */
    public void testChangeParentAtoRootForC()
    {
    	try {
    		//Test to change the parent
    		List<Node> results = ex.changeParent("C", "root");
		
			// Java objects to JSON string - pretty-print
	        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
	
			System.out.println("\nAfter change parent from A to root for the node C:\n "+jsonInString);
	        assertTrue( jsonInString != null );
    	}catch(Exception exp) {
    		assertTrue(false);
    	}
    }
}
