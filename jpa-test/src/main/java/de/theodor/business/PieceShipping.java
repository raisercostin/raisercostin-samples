package de.theodor.business;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="VERSAND")
public class PieceShipping {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MVERSAND_SEQ_GEN")
	@SequenceGenerator(name="MVERSAND_SEQ_GEN", sequenceName="VERSAND_SEQ_GEN")
	@Column(name="VID")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="owner")
	private List<Piece> pieces;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	/**@Deprecated don't do this, since you might override what and how hiberante manages collections.*/
	@Deprecated
	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

}
