package com.calisma.urunkategori;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import Utils.HibernateUtil;
import model.FileMeta;
import model.Kategoriler;
import model.Urunler;
import model.Viewsurunlerjoin;

@Controller
public class HomeController {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<Kategoriler> katls = katDoldur();
		List<Viewsurunlerjoin> urunls = urunDoldur();

		model.addAttribute("katls", katls);
		model.addAttribute("urunls", urunls);
		return "home";
	}

	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public String homer(Model model) {
		List<Kategoriler> katls = katDoldur();
		List<Viewsurunlerjoin> urunls = urunDoldur();

		model.addAttribute("katls", katls);
		model.addAttribute("urunls", urunls);
		return "register";
	}
	
	public List<Kategoriler> katDoldur() {
		List<Kategoriler> katls = new ArrayList<Kategoriler>();
		Session sesi = sf.openSession();
		katls = sesi.createQuery("from Kategoriler").list();
		sesi.close();
		return katls;
	}

	public List<Viewsurunlerjoin> urunDoldur() {
		List<Viewsurunlerjoin> urunls = new ArrayList<Viewsurunlerjoin>();
		Session sesi = sf.openSession();
		urunls = sesi.createQuery("from Viewsurunlerjoin").list();
		sesi.close();
		return urunls;
	}

	@ResponseBody
	@RequestMapping(value = "/katEkle", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public String katEkle(Kategoriler kt) {
		try {

			Session sesi = sf.openSession();
			Transaction tr = sesi.beginTransaction();
			kt.setKid(Integer.MAX_VALUE);
			int ekleid = (Integer) sesi.save(kt);
			tr.commit();
			sesi.close();
			System.out.println("eklenen id:" + ekleid);
			String veri = "<option value=\"" + kt.getKid() + "\">" + kt.getKadi() + " </option>";
			return veri;
		} catch (Exception e) {
			System.err.println("ekleme hatasi:" + e);
		}
		return "";
	}

	@ResponseBody
	@RequestMapping(value = "/urunEkle", method = RequestMethod.POST)
	public String urunEkle(Urunler ur) {
		try {
			String resimAdi = fileMeta.getFileName();
			System.out.println("resimAdi:" + resimAdi);
			Session sesi = sf.openSession();
			Transaction tr = sesi.beginTransaction();
			ur.setUid(Integer.MAX_VALUE);
			ur.setUresim(resimAdi);
			int uId = (Integer) sesi.save(ur);
			tr.commit();
			sesi.close(); 
			try {
				// copy file to local disk (make sure the path "e.g. D:/temp/files" exists)
				FileCopyUtils.copy(fileMeta.getBytes(),
						new FileOutputStream("C:\\xampp\\htdocs\\UrunResimleri\\" + fileMeta.getFileName()));
			} catch (Exception e) {
				System.err.println("dosya ekleme hatasi" + e);
			}
			Session sesi1 = sf.openSession();
			Transaction tr1 = sesi1.beginTransaction();
			Kategoriler kt = (Kategoriler) sesi1.createQuery("from Kategoriler where kid ='" + ur.getUkid() + "'")
					.list().get(0);
			tr1.commit();
			sesi1.close();
			String veri = "<tr>\n" + "<th scope=\"row\">" + uId + "</th>\n" + "<td>" + ur.getUadi() + "</td>\n" + "<td>"
					+ kt.getKadi() + "</td>\n" + "<td><img src='http://localhost:8080/UrunResimleri/" + ur.getUresim()
					+ "' width='100'/></td>\n" + "<td> <button onclick=\"urunSil(" + ur.getUid()
					+ ")\" type=\"button\" " + "class=\"btn btn-outline-danger btn-sm\">SIL</button> </td>" + "</tr>";

			return veri;

		} catch (Exception e) {
			System.err.println("urunEkle:" + e);
		}

		return "";
	}

	/*
	 * Linkedlist istenilen elemanina ulasilabilen degisiklik yapilabilen ve
	 * listenin herhangi bir konumuna eleman ekleme ve silme islemi yapilabilen bir
	 * liste turudur
	 */
	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	FileMeta fileMeta = null;

	@RequestMapping(value = "/admin/uploadmi", method = RequestMethod.POST)
	public @ResponseBody LinkedList<FileMeta> upload(MultipartHttpServletRequest request) {

		/*
		 * 1. build an iterator icinde gezinile bilen list turu bu tur ile next hasnext
		 * previous gibi ozellikler kullanilabilir.
		 */
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (itr.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! " + files.size());
			String tur = ".jpg";
			if (mpf.getContentType() == "image/jpeg") {
				tur = ".jpg";
			}
			String dosyaAdi = getDateTime() + tur;
			System.out.println("dosya Adi:" + dosyaAdi);

			// 2.2 if files > 10 remove the first from the list
			if (files.size() >= 10)
				files.pop();

			// 2.3 create new fileMeta
			fileMeta = new FileMeta();
			fileMeta.setFileName(dosyaAdi);
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());

			try {
				fileMeta.setBytes(mpf.getBytes());
			} catch (Exception e) {
				System.err.println("Ekleme Hatasý " + e);
			}
			// 2.4 add to files
			files.add(fileMeta);

		}

		return files;

	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	@ResponseBody
	@RequestMapping(value = "/urunSil", method = RequestMethod.POST)
	public String urunSil(Urunler ur) {
		String sonuc = "";
		try {
			Session sesi1 = sf.openSession();
			Transaction tr1 = sesi1.beginTransaction();
			Urunler ur1 = (Urunler) sesi1.createQuery("from Urunler where uid = " + ur.getUid() + "").list().get(0);
			String path = ur1.getUresim();
			File file = new File("C:\\xampp\\htdocs\\UrunResimleri\\" + path);
			file.delete();
			tr1.commit();
			sesi1.close();

			Session sesi = sf.openSession();
			Transaction tr = sesi.beginTransaction();
			sesi.delete(ur);
			tr.commit();
			sesi.close();

			sonuc = "" + ur.getUid();

		} catch (Exception e) {
			System.err.println("silme islemi hatasi" + e);
			sonuc = "";
		}

		return sonuc;
	}

}
