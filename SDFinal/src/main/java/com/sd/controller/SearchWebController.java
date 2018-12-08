package com.sd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sd.model.Search;
import com.sd.service.ScrapeDataService;

@Controller
public class SearchWebController {
	
	@Autowired
	ScrapeDataService sdsw;
	Logger log = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value="/form", method=RequestMethod.GET)
    public String customerForm(Model model) {
        model.addAttribute("search", new Search());
        return "form";
    }
     
    
    @RequestMapping(value="/form", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute Search search, Model model) {
        
        
        String info = search.getSearchText();
        log.info(info);
        
        
        search.setResultGoogle(sdsw.getDataFromGoogle(info));        
        search.setResultBing(sdsw.getDataFromBing(info));
        search.setResultYahoo(sdsw.getDataFromYahoo(info)); 
        
        model.addAttribute("search", search);
    
         
        return "result";
    }


}
