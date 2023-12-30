package org.jsp.hospitaladmin.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.hospitaladmin.dto.Admin;

public class AdminDao {
	private EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();

	public Admin save(Admin admin) {
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(admin);
		transaction.begin();
		transaction.commit();
		return admin;
	}

	public Admin update(Admin admin) {
		EntityTransaction transaction = manager.getTransaction();
		Admin admindb = manager.find(Admin.class, admin.getId());
		if (admindb != null) {
			admindb.setName(admin.getName());
			admindb.setPhone(admin.getPhone());
			admindb.setEmail(admin.getEmail());
			admindb.setPassword(admin.getPassword());
			admindb.setHospitals(admin.getHospitals());
			transaction.begin();
			transaction.commit();
		}
		return admindb;
	}

	public Admin findAdminById(int id) {
		Admin a = manager.find(Admin.class, id);
		return a;
	}

	public Admin findAdminByPhoneAndPassword(long phone, String password) {
		String qry = "select a from Admin a where a.phone=?1 and a.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			Admin admin = (Admin) q.getSingleResult();
			return admin;
		} catch (NoResultException e) {
			return null;
		}

	}

	public Admin findAdminByEmailandPassword(String email, String password) {
		String qry = "select a from Admin a where a.email=?1 and a.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (Admin) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
