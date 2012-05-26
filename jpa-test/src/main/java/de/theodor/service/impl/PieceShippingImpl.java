package de.theodor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.theodor.business.PieceShipping;
import de.theodor.dao.ShippingDao;
import de.theodor.service.ShippingService;

@Service
public class PieceShippingImpl implements ShippingService {

	@Autowired
	private ShippingDao shippingDao;
	
	@Transactional
	public PieceShipping save(PieceShipping obj) {
		return shippingDao.save(obj);
	}

}
