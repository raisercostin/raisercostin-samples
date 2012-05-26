package de.theodor.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.theodor.business.PieceShipping;

@Repository("shippingDao")
public class ShippingDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public PieceShipping save(PieceShipping obj){
		PieceShipping ps = (PieceShipping) sessionFactory.getCurrentSession().merge(obj);
		//TODO let's try without strange things
		// flush fortat, altfel nu apuc sa vad ca testu pica
		//sessionFactory.getCurrentSession().flush();
		return ps;
	}

}
