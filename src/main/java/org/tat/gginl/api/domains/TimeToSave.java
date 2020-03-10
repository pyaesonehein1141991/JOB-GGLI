package org.tat.gginl.api.domains;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.tat.gginl.api.common.TableName;

import lombok.Data;

@Entity
@Data
@Table(name = TableName.RUNTIMETABLE)
@TableGenerator(name = "RUNTIME_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "RUNTIME_GEN", allocationSize = 10)
@Access(value = AccessType.FIELD)
public class TimeToSave implements Serializable {
	private static final long serialVersionUID = -6982490830051621004L;

	@Transient
	private String id;

	private Date runtime;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RUNTIME_GEN")
	@Access(value = AccessType.PROPERTY)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;

	}

	public void setRuntime(Date runtime) {
		this.runtime = runtime;
	}

	public Date getRuntime() {
		return runtime;
	}

}
