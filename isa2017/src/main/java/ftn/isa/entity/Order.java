package ftn.isa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RES_ORD")
public class Order implements Serializable {

	private static final long serialVersionUID = -2545303099330416597L;

	@Id
	@Column(name = "RES_ORD_ID")
	@GeneratedValue
	private Long id;

	@Column(name = "RES_ORD_DATE")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "ORDER_TIME")
	private double time;
	
	@Column(name = "DISCOUNT")
	private double discount;
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Column(name = "FAST_RESERVATION")
	private boolean fastReservation;
	
	public boolean isFastReservation() {
		return fastReservation;
	}

	public void setFastReservation(boolean fastReservation) {
		this.fastReservation = fastReservation;
	}

	@ManyToOne(optional = false)
	private InstitutionTable table;
	
	@ManyToOne
	private Reservation reservation;
	

    @ManyToOne
	private Projection projection;

    public void setProjection(Projection projection) {
		this.projection = projection;
	}

    public Projection getProjection() { return projection; }

	@Column(name = "PRICE")
	private double price;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	public Order() {
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTable(InstitutionTable table) {
		this.table = table;
	}

	public void setId(Long id){
		this.id=id;
	}
	
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	public Date getDate() {
		return date;
	}

	public Long getId() {
		return id;
	}

	public InstitutionTable getTable() {
		return table;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
}
