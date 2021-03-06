package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Client;

@Stateless
public class ClientService {
	
	//http://localhost:8080/pizzeria-admin-web/clients/liste
	
	@PersistenceContext private EntityManager em;
	
	 /***CRUD***/

	/***Ajoute un client en base de donnee***/
	public void createClient(Client c){
		em.merge(c);		
	}
	
	/***Modifie un client de la base de donnee***/
	public void updateClient(Integer id, Client c){
		listerClients().forEach(Client->{
			if (Client.getId() == id){
				em.merge(c);
				}
		});
				
	}
	
	/***Supprime un client de la base de donnee***/
	public void deleteClient(Integer id){
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.id = :id", Client.class);
		query.setParameter("id", id);
		Client c = query.getSingleResult();
		
		em.remove(c);
	}
	
	
	/***Autres methodes***/
	
	/***Recherche un client en base de donnees en base de donnee en fonction de son id***/
	public Client retrieveClient(Integer id){
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.id = :id", Client.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	
	/***La methode listerClient retourne une liste exhaustive des clients de la base de donnee***/
	public List<Client> listerClients(){
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
		return query.getResultList();
	}
	
	
	
	

}
