package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the viewsurunlerjoin database table.
 * 
 */
@Entity
@NamedQuery(name="Viewsurunlerjoin.findAll", query="SELECT v FROM Viewsurunlerjoin v")
public class Viewsurunlerjoin implements Serializable {
	private static final long serialVersionUID = 1L;

	private String kadi;

	private String uadi;

	@Id
	private int uid;

	private String uresim;

	public Viewsurunlerjoin() {
	}

	public String getKadi() {
		return this.kadi;
	}

	public void setKadi(String kadi) {
		this.kadi = kadi;
	}

	public String getUadi() {
		return this.uadi;
	}

	public void setUadi(String uadi) {
		this.uadi = uadi;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUresim() {
		return this.uresim;
	}

	public void setUresim(String uresim) {
		this.uresim = uresim;
	}

}