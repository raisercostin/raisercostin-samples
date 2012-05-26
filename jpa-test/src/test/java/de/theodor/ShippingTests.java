package de.theodor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.theodor.business.Piece;
import de.theodor.business.PieceShipping;
import de.theodor.service.ShippingService;

@ContextConfiguration(locations = "classpath:ShippingTests-context.xml")
public class ShippingTests
// extends AbstractJUnit4SpringContextTests {
		extends AbstractTransactionalJUnit4SpringContextTests {

	private static final Logger LOG = Logger.getLogger(ShippingTests.class);

	@Autowired
	protected ShippingService shippingService;

	@Test
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void insertShipping() {
		LOG.info("start test");
		try {
			Assert.assertEquals(0, countRowsInTable("versandteile"));
			Assert.assertEquals(0, countRowsInTable("versand"));

			PieceShipping obj = new PieceShipping();
			obj.setName("TEST NAME");
			Piece piece = new Piece();
			piece.setOwner(obj);
			piece.setPieceName("bla bla");
			piece.setPieceNumber("1");
			List<Piece> pieces = new ArrayList<Piece>();
			pieces.add(piece);
			obj.setPieces(pieces);
			
			//now it fails because i added database constraints ... which is great
			obj = this.shippingService.save(obj);
			Assert.assertEquals(33300, obj.getId());
			Assert.assertEquals("TEST NAME", obj.getName());
			Assert.assertEquals(1, obj.getPieces().size());
			Assert.assertEquals("bla bla", obj.getPieces().get(0)
					.getPieceName());
			Assert.assertEquals(33300, obj.getPieces().get(0).getOwner()
					.getId());
			Assert.assertEquals("TEST NAME", obj.getPieces().get(0).getOwner()
					.getName());
			Assert.assertEquals("1", obj.getPieces().get(0).getPieceNumber());

			Assert.assertEquals(1, countRowsInTable("versandteile"));
			Assert.assertEquals(1, countRowsInTable("versand"));
			Assert.assertEquals(33300, this.simpleJdbcTemplate
					.queryForLong("select vid from versand"));
			
			//we need a fk that should fail
			Assert.assertEquals(33300, this.simpleJdbcTemplate
					.queryForLong("select vid from versandteile"));

			Assert.assertEquals(33300, obj.getPieces().get(0).getOwnerId());
		} finally {
			LOG.info("end test");
		}
	}

}
