package com.QoreMigration.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.QoreMigration.Model.SmalltalkCenter;

@Service
public interface SmalltalkCenterService {

	public List<SmalltalkCenter> Searchs(String query);

	public List<SmalltalkCenter> pageandsearch(Long pagenumber, Long pagesize, String searchdata);
	public Object getpa_account_details();

	public Object getpartition();

}
