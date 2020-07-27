package com.techelevator.auctions.controller;

import com.techelevator.auctions.DAO.AuctionDAO;
import com.techelevator.auctions.DAO.MemoryAuctionDAO;
import com.techelevator.auctions.model.Auction;
//import com.techelevator.reservations.models.Hotel;
//import com.techelevator.reservations.models.Reservation;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
@RestController
// @RequestMapping is outside the class -  indicates the base path for all the controllers
@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDAO dao;
 // No @RequestMapping for this method so it handles the base bath (/auctions)
    public AuctionController() {
        this.dao = new MemoryAuctionDAO();
    }
    
/*@RequestMapping(path="/frank")  // handle "/auctions/" frank path++ bas	e path from above and path=
	public void FrankController() {
		// What ever the frankController does
	}
*/
    
    // ****/auctions?title_like=word
    @RequestMapping(path="", method=RequestMethod.GET)   
    public List<Auction> list(@RequestParam(defaultValue="") String title_like 
    					     ,@RequestParam(defaultValue="0") double currentBid_lte) {
    	if(!title_like.equals("") && currentBid_lte>0) {
    		return dao.searchByTitleAndPrice(title_like, currentBid_lte);
    	}
    	else if (!title_like.equals("")) {
    		return dao.searchByTitle(title_like);
        } else if(currentBid_lte>0) {
        	return dao.searchByPrice(currentBid_lte);	
        } 
		return dao.list();
    }
    
    @RequestMapping(path="/{id}", method=RequestMethod.GET)
    public Auction get(@PathVariable int id) {
        return dao.get(id);
    }
    
    
    // add reservation
    @RequestMapping(path="", method=RequestMethod.POST)
    public Auction create(@RequestBody Auction anAuction)
    {
    	return dao.create(anAuction);
    }
    

    
    
}

