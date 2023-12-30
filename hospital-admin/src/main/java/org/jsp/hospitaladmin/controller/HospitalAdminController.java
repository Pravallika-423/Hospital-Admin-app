package org.jsp.hospitaladmin.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.hospitaladmin.dao.AdminDao;
import org.jsp.hospitaladmin.dao.HospitalAdminDao;
import org.jsp.hospitaladmin.dto.Admin;
import org.jsp.hospitaladmin.dto.Hospital;

public class HospitalAdminController {
	static Scanner sc = new Scanner(System.in);
	static HospitalAdminDao hospitaldao = new HospitalAdminDao();
	static AdminDao admindao = new AdminDao();

	public static void main(String[] args) {

		System.out.println("1.Save Admin\n2.Update Admin\n3.Find Admin By ID\n4.Find Admin by Phone and password\n"
				+ "5.Find Admin by Email and Password\n6.Save Hospital\n7.Update Hospital\n8.Find hospital by admin id\n"
				+ "9.Find Hospital by admin phone and password\n10.Find Hospital by admin email and password");
		System.out.println("select a number:");
		int choice = sc.nextInt();
		switch (choice) {
		case 1: {

			save();
			break;
		}
		case 2: {
			update();
			break;
		}
		case 3: {
			findAdminById();
			break;
		}
		case 4: {
			findAdminbyPhoneAndPassword();
			break;
		}
		case 5: {
			findAdminbyEmailAndPassword();
			break;
		}
		case 6: {
			saveHospital();
			break;
		}
		case 7: {
			updateHospital();
			break;
		}
		case 8: {
			findHospitalByAdminId();
			break;
		}
		case 9: {
			findHospitalByAdminPhoneAndPassword();
			break;
		}
		case 10: {
			findAdminbyEmailAndPassword();
			break;
		}
		}
	}

	public static void save() {
		System.out.println("enter admin name,phone,email and password: ");
		Admin a = new Admin();
		a.setName(sc.next());
		a.setPhone(sc.nextLong());
		a.setEmail(sc.next());
		a.setPassword(sc.next());
		Admin admin = admindao.save(a);
		if (admin != null) {
			System.out.println("admin saved successfully with id:"+admin.getId() );
		} else {
			System.out.println("unable to save admin");
		}

	}

	public static void update() {
		System.out.println("enter admin id,name,phone,emailand password");
		Admin a = new Admin();
		int id = sc.nextInt();
		a.setId(id);
		a.setName(sc.next());
		a.setPhone(sc.nextLong());
		a.setEmail(sc.next());
		a.setPassword(sc.next());
		Admin admin = admindao.update(a);
		if (admin != null) {
			System.out.println("admin updated successfully");
		} else {
			System.out.println("unable to update admin");
		}
	}

	public static void findAdminById() {
		System.out.println("enter admin id");
		int id = sc.nextInt();
		Admin a = admindao.findAdminById(id);
		if (a != null) {
			System.out.println("admin id:" + a.getId());
			System.out.println("name:" + a.getName());
			System.out.println("email:" + a.getEmail());
			System.out.println("phone:" + a.getPhone());
			System.out.println("password:" + a.getPassword());
		} else {
			System.out.println("no admin present with given id");
		}
	}

	public static void findAdminbyPhoneAndPassword() {
		System.out.println("enter admin phone number and password");
		long phone = sc.nextLong();
		String password = sc.next();
		Admin a = admindao.findAdminByPhoneAndPassword(phone, password);
		if (a != null) {
			System.out.println("admin id:" + a.getId());
			System.out.println("name:" + a.getName());
			System.out.println("email:" + a.getEmail());
			System.out.println("phone:" + a.getPhone());
			System.out.println("password:" + a.getPassword());
		} else {
			System.out.println("no admin present with given phone number and password");
		}
	}

	public static void findAdminbyEmailAndPassword() {
		System.out.println("enter admin email and password");
		String email = sc.next();
		String password = sc.next();
		Admin a = admindao.findAdminByEmailandPassword(email, password);
		if (a != null) {
			System.out.println("admin id:" + a.getId());
			System.out.println("name:" + a.getName());
			System.out.println("email:" + a.getEmail());
			System.out.println("phone:" + a.getPhone());
			System.out.println("password:" + a.getPassword());
		} else {
			System.out.println("no admin present with given email and password");
		}
	}

	public static void saveHospital() {
		System.out.println("enter admin id to save hospital");
		int admin_id = sc.nextInt();
		System.out.println("enter hospital name,founder,gst,year_of_estb");
		Hospital h = new Hospital();
		h.setName(sc.next());
		h.setFounder(sc.next());
		h.setGst(sc.nextDouble());
		h.setYear_of_estb(sc.nextInt());

		Hospital h1 = hospitaldao.savehospital(h, admin_id);
		if (h1 != null)
			System.out.println("hospital saved successfully with id:" + h1.getId());
		else
			System.err.println("unable to save hospital as admin id is invalid");
	}

	public static void updateHospital() {
		System.out.println("enter hospital id,name,founder,gst and year of establishment to update hospital");
		int id = sc.nextInt();
		Hospital h = new Hospital();
		h.setId(id);
		h.setName(sc.next());
		h.setFounder(sc.next());
		h.setGst(sc.nextDouble());
		h.setYear_of_estb(sc.nextInt());

		Hospital h1 = hospitaldao.updateHospital(h);
		if (h1 != null) {
			System.out.println("hospital updated successfully");
		} else {
			System.out.println("unable to update hospital");
		}
	}

	public static void findHospitalByAdminId() {
		System.out.println("enter admin id");
		int id = sc.nextInt();
		List<Hospital> hospitals = hospitaldao.findhospitalByAdminId(id);
		if (hospitals != null) {
			for (Hospital h : hospitals) {
				System.out.println("hospital id:" + h.getId());
				System.out.println("name:" + h.getName());
				System.out.println("founder:" + h.getFounder());
				System.out.println("year of establishment:" + h.getYear_of_estb());
				System.out.println("gst:" + h.getGst());
				System.out.println("---------------------------------------------");
			}
		} else {
			System.out.println("invalid admin id");
		}
	}

	public static void findHospitalByAdminPhoneAndPassword() {
		System.out.println("enter admin phone and password:");
		long phone = sc.nextLong();
		String email = sc.next();
		List<Hospital> hospitals = hospitaldao.findhospitalByPhoneAndPassword(phone, email);
		if (hospitals != null) {
			for (Hospital h : hospitals) {
				System.out.println("hospital id:" + h.getId());
				System.out.println("name:" + h.getName());
				System.out.println("founder:" + h.getFounder());
				System.out.println("year of establishment:" + h.getYear_of_estb());
				System.out.println("gst:" + h.getGst());
				System.out.println("----------------------------------------");
			}
		} else {
			System.out.println("invalid admin phone number or password");
		}
	}

	public static void findHospitalByEmailAndPassword() {
		System.out.println("enter admin email and password");
		String email = sc.next();
		String password = sc.next();
		List<Hospital> hospitals = hospitaldao.findHospitalByEmailAndPassword(email, password);
		if (hospitals != null) {
			for (Hospital h : hospitals) {
				System.out.println("hospital id:" + h.getId());
				System.out.println("name:" + h.getName());
				System.out.println("founder:" + h.getFounder());
				System.out.println("year of establishment:" + h.getYear_of_estb());
				System.out.println("gst:" + h.getGst());
				System.out.println("----------------------------------------------");
			}
		} else {
			System.out.println("invalid admin email or password");
		}
	}

}
