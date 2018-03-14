package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the urunler database table.
 * 
 */
@Entity
@NamedQuery(name="Urunler.findAll", query="SELECT u FROM Urunler u")
public class Urunler implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int uid;

	private String uadi;

	private int ukid;

	private String uresim;

	public Urunler() {
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUadi() {
		return this.uadi;
	}

	public void setUadi(String uadi) {
		this.uadi = uadi;
	}

	public int getUkid() {
		return this.ukid;
	}

	public void setUkid(int ukid) {
		this.ukid = ukid;
	}

	public String getUresim() {
		return this.uresim;
	}

	public void setUresim(String uresim) {
		this.uresim = uresim;
	}

}