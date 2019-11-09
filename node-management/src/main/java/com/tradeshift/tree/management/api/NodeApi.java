/**
 * 
 */
package com.tradeshift.tree.management.api;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tradeshift.tree.management.model.ChangeParentException;
import com.tradeshift.tree.management.model.Node;
import com.tradeshift.tree.management.model.NotFoundException;


/**
 * @author bala chinna
 *
 */

@RestController
public class NodeApi {
	  
	NodeHelper nodeHlpr = new NodeHelper();
	
	/**
	 * Returns all the descendants of given node.
	 * API -- <host>/node/<nodeName>
	 * @param name
	 * @return List<Node> in json format.
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/node/{nodeName}")
	@ResponseBody
	public List<Node> findDescedants(@PathVariable("nodeName") String name) {
		
		List<Node> nodes = null;
		try {
			nodes = nodeHlpr.getDescedents(name);
			System.out.println("Total Nodes size: "+nodes.size());
		}catch (NotFoundException excep) {
	         throw new ResponseStatusException(
	           HttpStatus.NOT_FOUND, "", excep);
	    }
		return nodes;
	}
	
	
	/**
	 * Changes the parent of the given node to new parent node.
	 * API -- <host>/node/changeParent/{nodeName}/{nodeName}
	 * Note: Implemented using GET method just for the sake of demo, where as it is nice to with PUT method. To make the life easier for testing implemented using GET.
	 * @param name
	 * @param parentNode
	 * @return List<Node> in json format.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/node/changeParent/{nodeName}/{parentNode}")
	@ResponseBody
	public List<Node> changeParent(@PathVariable("nodeName") String name, @PathVariable("parentNode") String parentNode) {
		List<Node> nodes = null;
		try {
			nodes = nodeHlpr.changeParent(name, parentNode);
			System.out.println("After parent change Total Nodes size: "+nodes.size());
		}catch (NotFoundException notFoundExp) {
	         throw new ResponseStatusException(
	           HttpStatus.NOT_FOUND, "", notFoundExp);
	    }catch(ChangeParentException changeParentExp) {
	    	throw new ResponseStatusException(
	 	           HttpStatus.NOT_ACCEPTABLE, "", changeParentExp);
	    	
	    }
		return nodes;
	}
	
}


