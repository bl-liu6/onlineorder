package com.laioffer.onlineorder.dao;

import com.laioffer.onlineorder.entity.Authorities;
import com.laioffer.onlineorder.entity.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//DAO;data tire相关的（Data access object）,与数据库进行交互
//@Repository == @component
@Repository
public class CustomerDao {
    @Autowired
    private SessionFactory sessionFactory;
    public void signUp(Customer customer) {
        Authorities authorities = new Authorities();
        authorities.setEmail(customer.getEmail());
        authorities.setAuthorities("ROLE_USER");

        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
            if(session != null) session.getTransaction().rollback();
        }finally {
            if(session !=null){
                session.close();
            }
        }
    }

    public Customer getCustomer(String email) {
        Customer customer = null;
        try (Session session = sessionFactory.openSession()) {     //这样些好处：创建完后会auto close
            customer = session.get(Customer.class, email);    //拿到用户email
        } catch (Exception ex) {
            ex.printStackTrace();   //打印读取失败的原因
        }
        return customer;
    }
}
