package com.nadhem.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.nadhem.entities.Game;
import com.nadhem.util.JPAutil;
//classe contenant les méthodes génériques ajouter, supprimer,
//consulter par clé primaire (Id)
public class GameDao {
private EntityManager entityManager=JPAutil.getEntityManager("MonProjetJPA");
//méthode ajouter d'une entité à la bd
public void ajouter(Game c)
{
EntityTransaction tx = entityManager.getTransaction();
tx.begin();
entityManager.persist(c);
tx.commit();
}
//méthode modifier d'une entité à partir de la bd
public void modifier(Game c)
{
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.merge(c);
	tx.commit();
}
//méthode Supprimer d'une entité à partir de la bd
public void supprimer(Game c) throws Exception
{
	
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	c=entityManager.merge(c); // important
	entityManager.remove(c);
	tx.commit();
}
//méthode Consulter d'une entité à partir de la bd
public Game consulter(Game c,Object id)
{
	return entityManager.find(c.getClass(), id);
}
//méthode pour lister tous les objets à partir de la bd
public List<Game> listerTous() {
	List<Game> Games =entityManager.createQuery("select c from Game c").getResultList();
	
	return Games;
}
//méthode pour lister tous les Game dont le name contient un
//texte donné en paramètre (pname)
public List<Game> listerParName(String name) {
	List<Game> Games=entityManager.createQuery( "select c from Game c where c.name like :pname").setParameter("pname","%"+name+"%").getResultList();

return Games; }}