package ftn.isa.model.korisnici;

import java.io.Serializable;

import javax.persistence.ManyToOne;


public class PrijateljId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3880640696998424535L;
	@ManyToOne
	private Posetilac sender;
	@ManyToOne
	private Posetilac reciever;
	
	public PrijateljId(){
		
	}
	
	public PrijateljId(Posetilac sender,Posetilac reciever){
		this.sender = sender;
		this.reciever = reciever;
	}

	
	public Posetilac getSender() {
		return sender;
	}
	public void setSender(Posetilac sender) {
		this.sender = sender;
	}
	public Posetilac getReciever() {
		return reciever;
	}
	public void setReciever(Posetilac reciever) {
		this.reciever = reciever;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sender == null) ? 0 : sender.hashCode());
		result = (int) (prime * result + reciever.getId());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		
		PrijateljId other = (PrijateljId) obj;

        if (sender != null ? !sender.equals(other.sender) : other.sender != null) return false;
        if (reciever != null ? !reciever.equals(other.reciever) : other.reciever != null)
            return false;

        return true;
	}

}
