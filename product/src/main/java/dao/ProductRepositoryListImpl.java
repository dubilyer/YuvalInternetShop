package dao;

import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("productRepository")
public class ProductRepositoryListImpl implements ProductRepository{
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void addProduct(Product product) {
        getSession().persist(product);
    }

    @Override
    public void deleteProduct(Product product) {
        getSession().delete(product);
    }
}
