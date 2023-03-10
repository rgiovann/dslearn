package com.devsuperior.dslearnbds.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devsuperior.dslearnbds.entities.pk.EnrollmentPK;

@Entity
@Table(name = "tb_enrollment")
public class Enrollment implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	// Applied to a persistent field or property of an entity class or mapped superclass 
	// to denote a composite primary key that is an embeddable class. The embeddable class 
	// must be annotated as Embeddable. 
	@EmbeddedId
	private EnrollmentPK id = new EnrollmentPK();  // MUST INSTANTIATE COMPOSED KEY
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant enrollMoment;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")	
	private Instant refundMoment;
	
	private boolean available;  // true or false, Boolean true, false or null
	private boolean onlyUpdate;
	
	@ManyToMany(mappedBy = "enrollmentsDone")
	private Set<Lesson> lessonsDone = new HashSet<Lesson>();
	
	@OneToMany(mappedBy = "enrollment")
	private Set<Deliver> deliveries = new HashSet<Deliver>();
	
	public Enrollment() {
	}

	public Enrollment(User user, Offer offer, Instant enrollMoment, Instant refundMoment, boolean available, boolean onlyUpdate) {
		super();
		this.id.setUser(user);
		this.id.setOffer(offer);
		this.enrollMoment = enrollMoment;
		this.refundMoment = refundMoment;
		this.available = available;
		this.onlyUpdate = onlyUpdate;
	}


	public User getStudent()
	{
		return id.getUser();
	}
	
	public void setStudent(User user)
	{
		id.setUser(user);
	}

	public Offer getOffer()
	{
		return id.getOffer();
	}
	
	public void setOffer(Offer offer)
	{
		 id.setOffer(offer);
	}

	public Instant getEnrollMoment() {
		return enrollMoment;
	}


	public void setEnrollMoment(Instant enrollMoment) {
		this.enrollMoment = enrollMoment;
	}


	public Instant getRefundMoment() {
		return refundMoment;
	}


	public void setRefundMoment(Instant refundMoment) {
		this.refundMoment = refundMoment;
	}


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isOnlyUpdate() {
		return onlyUpdate;
	}


	public void setOnlyUpdate(boolean onlyUpdate) {
		this.onlyUpdate = onlyUpdate;
	}
	
	public Set<Lesson> getLessonsDone() {
		return lessonsDone;
	}

	public Set<Deliver> getDeliveries() {
		return deliveries;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrollment other = (Enrollment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
