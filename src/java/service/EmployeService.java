/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Employe;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author RevSt
 */
public class EmployeService extends AbstractFacade<Employe>{
    
    @Override
    protected Class<Employe> getEntityClass() {
        return Employe.class;
    }
    
    public List<Employe> getByChef(Employe chef) {
        List<Employe> employes = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        employes = session.createQuery("FROM Employe e WHERE e.employe = :employe")
                .setParameter("employe", chef)
                .list();

        session.getTransaction().commit();
        return employes;
    }
    
}
