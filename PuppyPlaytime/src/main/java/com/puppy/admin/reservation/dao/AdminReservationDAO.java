package com.puppy.admin.reservation.dao;

import java.util.List;

import com.puppy.client.reservation.vo.ReservationVO;

public interface AdminReservationDAO {

	public List<ReservationVO> newReservationList(ReservationVO param);

	public List<ReservationVO> reservationList(ReservationVO param);

	public ReservationVO reservationDetail(int parseInt);

	public int reservationApproval(ReservationVO param);

	public int reservationCancel(ReservationVO param);



}
