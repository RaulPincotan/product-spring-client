package productclient.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import productclient.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl="http://localhost:8087/products/";

    public Product getProduct(int id) {
        return restTemplate.getForObject(baseUrl + id, Product.class);
    }

    public List<Product> getProducts() {
        return restTemplate.exchange(baseUrl,
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<List<Product>>() {
                }
        ).getBody();
    }

    public Product save(Product product) {
        return restTemplate.postForObject(baseUrl, product, Product.class);
    }

    public void removeProduct(int id) {
        restTemplate.delete(baseUrl+ id, Product.class);
    }
}
