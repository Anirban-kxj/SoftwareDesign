package com.sd.service;



import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class ScrapeDataService{

  private static Pattern patternDomainName;
  private Matcher matcher;
  private static final String DOMAIN_NAME_PATTERN 
	= "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
  static {
	patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
  }
	
 
  public String getDomainName(String url){
		
	String domainName = "";
	matcher = patternDomainName.matcher(url);
	if (matcher.find()) {
		domainName = matcher.group(0).toLowerCase().trim();
	}
	return domainName;
		
  }
	
  public Set<String> getDataFromGoogle(String query) {
		
	Set<String> result = new HashSet<String>();		
	String request = "https://www.google.com/search?q=" + query;
	System.out.println("Sending request..." + request);
		
	try {

		// need http protocol, set this as a Google bot agent
		Document doc = Jsoup
			.connect(request)
			.userAgent(			 
					"Chrome/70.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
			.timeout(5000).get();

		// get all links
		Element ell = doc.getElementById("search");
		String[] splitted = ell.getElementsByTag("cite").text().split(" ");
		
		
		for (String link : splitted) {			
			if(link.startsWith("https:") || link.startsWith("http:") || link.startsWith("www.")){
				result.add(link);
			}
		}

	} catch (IOException e) {
		e.printStackTrace();
	}
		
	return result;
  }
  
  public Set<String> getDataFromBing(String query) {
		
	Set<String> result = new HashSet<String>();	
	String request = "https://www.bing.com/search?q=" + query;
	System.out.println("Sending request..." + request);
		
	try {

		
		Document doc = Jsoup
			.connect(request)
			.userAgent(					
					"Chrome/70.0")
			.timeout(5000).get();

		// get all links
				Element ell = doc.getElementById("b_results");
				String[] splitted = ell.getElementsByClass("b_adurl").text().split(" ");				
				//String[] splitted = ell.getElementsByTag("cite").text().split(" ");
				for (String link : splitted) {		
					if(link.startsWith("https:") || link.startsWith("http:") || link.startsWith("www.")){
						result.add(link);
					}					
				}
				
				splitted = ell.getElementsByClass("b_attribution").text().split(" ");				
				//String[] splitted = ell.getElementsByTag("cite").text().split(" ");
				for (String link : splitted) {			
					if(link.startsWith("https:") || link.startsWith("http:") || link.startsWith("www.")){
						result.add(link);
					}
				}
				

	} catch (IOException e) {
		e.printStackTrace();
	}
		
	return result;
  }
  
  public Set<String> getDataFromYahoo(String query) {
		
	Set<String> result = new HashSet<String>();	
	String request = "https://search.yahoo.com/search?q=" + query;
	System.out.println("Sending request..." + request);
		
	try {

		
		Document doc = Jsoup
			.connect(request)
			.userAgent(			 
					"Chrome/70.0").ignoreHttpErrors(true)
			.timeout(5000).get();

		// get all links
				Element ell = doc.getElementById("left");
				String[] splitted = ell.getElementsByClass(" fz-ms fw-m fc-12th wr-bw lh-17").text().split(" ");
				for (String link : splitted) {			
					if(link.startsWith("https:") || link.startsWith("http:") || link.startsWith("www.")){
						result.add(link);
					}
				}

	} catch (IOException e) {
		e.printStackTrace();
	}
		
	return result;
  }

}