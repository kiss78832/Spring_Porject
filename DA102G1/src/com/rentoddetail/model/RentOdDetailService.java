package com.rentoddetail.model;

import java.util.List;

public class RentOdDetailService {

	private RentOdDetailDAO_interface dao;

	public RentOdDetailService() {
		dao = new RentOdDetailDAO();
	}

	public RentOdDetailVO addRentOdDetail(String rent_odnum, String eq_num, Integer quantity) {

		RentOdDetailVO rentoddetailVO = new RentOdDetailVO();

		rentoddetailVO.setRent_odnum(rent_odnum);
		rentoddetailVO.setEq_num(eq_num);
		rentoddetailVO.setQuantity(quantity);
		dao.insert(rentoddetailVO);

		return rentoddetailVO;
	}

	public RentOdDetailVO updateRentOdDetail(String rent_odnum, String eq_num, Integer quantity) {

		RentOdDetailVO rentoddetailVO = new RentOdDetailVO();

		rentoddetailVO.setRent_odnum(rent_odnum);
		rentoddetailVO.setEq_num(eq_num);
		rentoddetailVO.setQuantity(quantity);
		dao.update(rentoddetailVO);

		return rentoddetailVO;
	}

	public void deleteRentOdDetail(String rent_odnum, String eq_num) {
		dao.delete(rent_odnum, eq_num);
	}

	public RentOdDetailVO getOneRentOdDetail(String rent_odnum, String eq_num) {
		return dao.findByPrimaryKey(rent_odnum, eq_num);
	}
	
	public List<RentOdDetailVO>  getDetail(String rent_odnum) {
		return dao.findByDetail(rent_odnum);
	}

	public List<RentOdDetailVO> getAll() {
		return dao.getAll();
	}
}
