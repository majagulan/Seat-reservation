package ftn.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "BIOSKOPSKO_MESTO", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "RED", "KOLONA", "SEGMENT_ID" }) })
public class Mesto {
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Segment segment;


	@Min(1)
	@Max(20)
	@NotNull
	@Column(name = "RES_CHAIR")
	private int brojMesta;


	@Column(name = "MESTO_ROW")
	private int redMesta;

	@Column(name = "MESTO_COLUMN")
	private int redKolone;

	public Mesto() {
		this.id = null;
	}


	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public Long getId() {
		return id;
	}

	public Segment getSegment() {
		return segment;
	}

	
	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	public int getMestoRed() {
		return redMesta;
	}

	public void setMestoRed(int red) {
		this.redMesta = red;
	}

	public int getMestoKolona() {
		return redKolone;
	}

	public void setMestoKolona(int kolona) {
		this.redKolone = kolona;
	}

}
