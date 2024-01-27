package com.example.gestordeturnos.api.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.function.Function;

public abstract class JpaQuerySession {
    private final static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("turnos");;

    public static <T> T inSession(Function<EntityManager, T> query){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            T result = query.apply(entityManager);
            transaction.commit();
            return result;
        }catch (Exception ex) {
            if (transaction.isActive()) transaction.rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    public static EntityManagerFactory getFactory(){
        return entityManagerFactory;
    }
}
