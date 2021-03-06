package com.ecommerce.shopping.config;

import com.ecommerce.shopping.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] allowedOrigins;
    @Autowired
    private EntityManager entityManager;
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportedActions = {HttpMethod.PUT,HttpMethod.POST,HttpMethod.DELETE,HttpMethod.PATCH};
        //disabling PUT,POST,DELETE,PATCH
        disableHttpMethods(ProductCategory.class,config, unsupportedActions);
        disableHttpMethods(Product.class,config, unsupportedActions);
        disableHttpMethods(State.class,config, unsupportedActions);
        disableHttpMethods(Country.class,config, unsupportedActions);
        disableHttpMethods(Order.class,config,unsupportedActions);
        //calling internal helper method to expose id's
        exposeId(config);
        //configure cors mapping
        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(allowedOrigins);
    }

    private void disableHttpMethods(Class className ,RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(className)
                .withItemExposure((metadata,httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metadata,httpMethods) -> httpMethods.disable(unsupportedActions));
    }

    public void exposeId(RepositoryRestConfiguration repositoryRestConfiguration){
        //get list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        //create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();
        entities.forEach((entityClass) -> entityClasses.add(entityClass.getJavaType()));

        //Expose the entity id's for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        repositoryRestConfiguration.exposeIdsFor(domainTypes);
    }
}