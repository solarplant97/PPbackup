package com.puppy.admin.statistics.dao;

import java.util.List;
import java.util.Map;

public interface StatisticsDAO {

	public List<Map<String, Integer>> adminJoinStatistics();

	public List<Map<String, Integer>> adminReservationStatistics();

	public List<Map<String, Integer>> adminSalesStatistics();

}
