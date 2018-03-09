package ftn.isa.model.korisnici;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "friend")
@AssociationOverrides({
		@AssociationOverride(name = "pk.sender",
			joinColumns = @JoinColumn(name = "SENDER_ID")),
		@AssociationOverride(name = "pk.reciever",
			joinColumns = @JoinColumn(name = "RECIEVER_ID")) })
public class Prijatelj implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9123619930003574284L;

	@EmbeddedId
	PrijateljId pk = new PrijateljId();
	
	@Column(name = "STATUS", nullable = false)
	private boolean status;
	
	@Transient
	private Posetilac getSender(){
		return pk.getSender();
	}
	
	@Transient
	public Posetilac getReciever(){
		return pk.getReciever();
	}
	
	public void setSender(Posetilac sender){
		pk.setSender(sender);
	}
	
	public void setReciever(Posetilac reciever){
		pk.setReciever(reciever);
	}

	public PrijateljId getPk() {
		return pk;
	}

	public void setPk(PrijateljId pk) {
		this.pk = pk;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
