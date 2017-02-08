package fr.pizzeria.admin.metier;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Boisson;

@Stateless
public class BoissonService {

	@PersistenceContext
	private EntityManager em;
	private Logger LOG = Logger.getLogger(getClass().getName());

	/***
	 * AJout d'une boisson en bdd
	 * 
	 * @param b
	 */
	public void createBoisson(Boisson b) {
		em.persist(b);
	}

	public List<Boisson> listerBoissons() {
		TypedQuery<Boisson> query = em.createQuery("select b from Boisson b", Boisson.class);
		List<Boisson> listerBoissons = query.getResultList();
		return listerBoissons;

	}

	public Boisson getBoisson(int id) {
		TypedQuery<Boisson> query = em.createQuery("select b from Boisson b WHERE b.id = :id ", Boisson.class);
		query.setParameter("id", id);
		Boisson boisson = query.getResultList().get(0);
		return boisson;

	}

	public void updateBoisson(Integer id, Boisson b) {
		Boisson boisson = em.find(Boisson.class, id);
		b.setId(id);
		if (boisson != null) {
			em.merge(b);
		}

	}

	public void deleteBoisson(int id) {
		Boisson boisson = em.find(Boisson.class, id);
		if (boisson != null)
			em.remove(boisson);
	}

}