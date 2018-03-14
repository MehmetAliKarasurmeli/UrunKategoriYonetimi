package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the kategoriler database table.
 * 
 */
@Entity
@NamedQuery(name="Kategoriler.findAll", query="SELECT k FROM Kategoriler k")
public class Kategoriler implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int kid;

	private String kadi;

	public Kategoriler() {
	}

	public int getKid() {
		return this.kid;
	}

	public void setKid(int kid) {
		this.kid = kid;
	}

	public String getKadi() {
		return this.kadi;
	}

	public void setKadi(String kadi) {
		this.kadi = kadi;
	}

}