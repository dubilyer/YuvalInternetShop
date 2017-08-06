package dao;

import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Repository("productRepository")
public class ProductRepositoryListImpl implements ProductRepository{
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Product> getAllProducts() {
        return getSession()
                .getNamedQuery("GET_ALL_PRODUCTS")
                .list();
    }

    @Override
    public void addProduct(Product product) throws NoSuchElementException {
        getSession().persist(product);
        getProductById(product.getId());
    }

    @Override
    public void deleteProduct(long id) throws NoSuchElementException {
        Product product = getProductById(id);
        getSession().delete(product);
    }

    @Override
    public Product getProductById(long id) throws NoSuchElementException{
        List<Product> products =  getSession()
                             .getNamedQuery("GET_PRODUCT_BY_ID")
                             .setParameter("id", id)
                             .getResultList();
        if (products.size()==0){
            throw new NoSuchElementException("There's no product with id "+id);
        }
        return products.get(0);

    }
}
