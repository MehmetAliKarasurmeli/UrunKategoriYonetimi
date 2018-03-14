package com.calisma.urunkategori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Utils.HibernateUtil;
import model.Viewsurunlerjoin;

@RestController
@RequestMapping(value = "/api")
public class UserApiController {
		
	SessionFactory sf=  HibernateUtil.getSessionFactory();
	
	// kullanici kayit islemi
	@RequestMapping(value = { "/jsongit", "/kayitgoster" }, method = RequestMethod.POST)
	public HashMap<String, Object> jsongit() {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		List<Viewsurunlerjoin> uLs = new ArrayList<Viewsurunlerjoin>();
		try {
			Session sesi = sf.openSession();
			Transaction tr = sesi.beginTransaction();
			uLs = sesi.createQuery("from Viewsurunlerjoin").list();
			tr.commit();
			sesi.close();
			if (uLs.size()>0) {
				hm.put("durum", true);
				hm.put("mesaj", "Tablo getirme basarili");
				hm.put("URUNLER",uLs);
			} else {
				hm.put("durum", true);
				hm.put("mesaj", "Tablo bos getirildi");
				hm.put("URUNLER",uLs);
			}
		} catch (Exception e) {
			hm.put("durum", false);
			hm.put("mesaj", "Tablo getirme basarisiz");
			System.err.println("kayit hatasi:" + e);
		}

		return hm;
	}

	
	
	
	
	@RequestMapping(value = "/admins", method = RequestMethod.GET)
	public HashMap<String, Object> allAdmins() {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		
		try {
			hm.put("durum", true);
			hm.put("mesaj", "admin tablosu getirme basarili");
		} catch (Exception e) {
			hm.put("durum", false);
			hm.put("mesaj", "admin tablosu getirme basarisiz");
			System.err.println("HATA:" + e);
		}
		return hm;

	}
	
	
	
}
