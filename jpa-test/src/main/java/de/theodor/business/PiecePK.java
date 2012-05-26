package de.theodor.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PiecePK implements Serializable{
	
	private static final long serialVersionUID = 1657577151873359823L;

	//TODO this should be long
	@Column(name="VID")
	private int ownerId;
	
	@Column(name="TEILNR")
	private String pieceNumber;

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getPieceNumber() {
		return pieceNumber;
	}

	public void setPieceNumber(String pieceNumber) {
		this.pieceNumber = pieceNumber;
	}

}
