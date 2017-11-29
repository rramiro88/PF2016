package utilidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ConexionJPA {
    
    private static ConexionJPA gestor = null;
    private EntityManagerFactory emf;
    
    private ConexionJPA(){
         emf = Persistence.createEntityManagerFactory("test");
    }
    

    public static ConexionJPA getInstance(){
        if(gestor==null) gestor = new ConexionJPA();
        return gestor;
    }
    
    public EntityManager em(){        
        return emf.createEntityManager();
    }

}
