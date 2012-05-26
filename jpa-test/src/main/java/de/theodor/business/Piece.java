package de.theodor.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="VERSANDTEILE")
@IdClass(PiecePK.class)
public class Piece {
	
	@Id
	private int ownerId;
	
	@Id
	private String pieceNumber;
	
	@Column(name="TEIL")
	private String pieceName;
	
	@ManyToOne
	//TODO because of bug https://hibernate.onjira.com/browse/HHH-7139
	@JoinColumn(name="VID", insertable=false)
	private PieceShipping owner;

	public PieceShipping getOwner() {
		return owner;
	}

	public void setOwner(PieceShipping owner) {
		this.owner = owner;
	}

	public String getPieceNumber() {
		return pieceNumber;
	}

	public void setPieceNumber(String pieceNumber) {
		this.pieceNumber = pieceNumber;
	}

	public String getPieceName() {
		return pieceName;
	}

	public void setPieceName(String pieceName) {
		this.pieceName = pieceName;
	}
	
	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

}
