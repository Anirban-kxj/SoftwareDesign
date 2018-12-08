package com.sd.model;

import java.util.List;
import java.util.Set;

public class Search {
	
	private String searchText;
	private Set<String> resultGoogle;
	private Set<String> resultBing;
	private Set<String> resultYahoo;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Set<String> getResultGoogle() {
		return resultGoogle;
	}

	public void setResultGoogle(Set<String> resultGoogle) {
		this.resultGoogle = resultGoogle;
	}

	public Set<String> getResultBing() {
		return resultBing;
	}

	public void setResultBing(Set<String> resultBing) {
		this.resultBing = resultBing;
	}

	public Set<String> getResultYahoo() {
		return resultYahoo;
	}

	public void setResultYahoo(Set<String> resultYahoo) {
		this.resultYahoo = resultYahoo;
	}

	
	
	

}
