package com.QoreMigration.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.QoreMigration.Model.SmalltalkCenter;
import com.QoreMigration.Repository.SmalltalkCenterRepository;
import com.QoreMigration.Service.SmalltalkCenterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@RestController
public class SmalltalkCenterController {
	
	@Autowired
	private SmalltalkCenterService smcService;

	@Autowired
	public SmalltalkCenterRepository STCRepository;

	public SmalltalkCenterController(SmalltalkCenterService sSer) {
		this.smcService = sSer;
	}

	public Sort.Direction getSortDirection1(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}
	
	// Paging By JPA
	@GetMapping("/Page")
	public ResponseEntity<Map<String, Object>> getAllbooksPage(@RequestParam(required = false) Long nsmalltalkid,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size,
			@RequestParam(defaultValue = "nsmalltalkid,asc") String[] sort) {
		try {
			List<Order> orders = new ArrayList<Order>();
			if (sort[0].contains(",")) {

				for (String sortOrder : sort) {
					String[] _sort = sortOrder.split(",");
					orders.add(new Order(getSortDirection1(_sort[0]), _sort[1]));
				}
			} else {

				orders.add(new Order(getSortDirection1(sort[1]), sort[0]));
			}
			List<SmalltalkCenter> payments = new ArrayList<SmalltalkCenter>();
			Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

			Page<SmalltalkCenter> pagePayment;
			if (nsmalltalkid == null)
				pagePayment = STCRepository.findAll(pagingSort);
			else
				pagePayment = STCRepository.findByNsmalltalkid(nsmalltalkid, pagingSort);
			payments = pagePayment.getContent();
			if (payments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			Map<String, Object> response = new HashMap<>();
			response.put("SmalltalkCenter", payments);
			response.put("currentPage", pagePayment.getNumber());
			response.put("totalItems", pagePayment.getTotalElements());
			response.put("totalPages", pagePayment.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	    
	// Searching By JPA
	@GetMapping("/search")
	public ResponseEntity<?> SearchChars(SmalltalkCenter smltCenter, @Param("query") String query) {
		ResponseEntity<?> response = null;
		try {

			return new ResponseEntity<>(smcService.Searchs(query), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return response;
	}
	
	// Paging sorting and searching by Postgres Function
	@GetMapping("/getSortedData")
	public ResponseEntity<?> getsorted(@RequestParam("pagenumber") Long pagenumber,
			@RequestParam("pagesize") Long pagesize, @RequestParam("searchdata") String searchdata) {

		try {
			return new ResponseEntity<>(smcService.pageandsearch(pagenumber, pagesize, searchdata), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/getPa_account")
	public ResponseEntity<?> getpa_accountdetails() {

		try {
			return new ResponseEntity<>(smcService.getpa_account_details(), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/callpartition")
	public ResponseEntity<?> callpartition() {

		try {
			return new ResponseEntity<>(smcService.getpartition(), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
}
