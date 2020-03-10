package org.tat.gginl.api.common;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.tat.gginl.api.domains.Township;

@Embeddable
public class PermanentAddress implements Serializable {

	private static final long serialVersionUID = 1L;
	private String permanentAddress;
	@OneToOne
	@JoinColumn(name = "PERMANENTTOWNSHIPID", referencedColumnName = "ID",insertable = false, updatable = false)
	private Township township;

	public PermanentAddress() {
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Township getTownship() {
		return this.township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	public void getFullTownShip() {

	}

	@Transient
	public String getFullAddress() {
		if (permanentAddress == null || township == null) {
			return "";
		}
		return permanentAddress + ", " + township.getFullTownShip();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permanentAddress == null) ? 0 : permanentAddress.hashCode());
		result = prime * result + ((township == null) ? 0 : township.hashCode());
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
		PermanentAddress other = (PermanentAddress) obj;
		if (permanentAddress == null) {
			if (other.permanentAddress != null)
				return false;
		} else if (!permanentAddress.equals(other.permanentAddress))
			return false;
		if (township == null) {
			if (other.township != null)
				return false;
		} else if (!township.equals(other.township))
			return false;
		return true;
	}

}
