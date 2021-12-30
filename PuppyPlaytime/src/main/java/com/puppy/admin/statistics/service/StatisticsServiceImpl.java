package com.puppy.admin.statistics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puppy.admin.statistics.dao.StatisticsDAO;

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private StatisticsDAO StatisticsDAO;
	
	@Override
	public List<Map<String, Integer>> adminJoinStatistics() {
		
		return StatisticsDAO.adminJoinStatistics();
	}

	@Override
	public List<Map<String, Integer>> adminReservationStatistics() {
	
		return StatisticsDAO.adminReservationStatistics();
	}

	@Override
	public List<Map<String, Integer>> adminSalesStatistics() {
		// TODO Auto-generated method stub
		return StatisticsDAO.adminSalesStatistics();
	}

}
