package com.bananeexport.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.bananeexport.entity.Commande;
import com.bananeexport.entity.Destinataire;
import com.bananeexport.entity.Produit;


@Configuration
public class ApplicationDataRestConfig implements RepositoryRestConfigurer {

	@Autowired	
	private EntityManager entityManager;

	@Value("${allowed.origins}")
	private String[] origines;



	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

		HttpMethod[] unsupportedActions = {HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PATCH};
		//Disable
		disableHttpMethods(Produit.class,config, unsupportedActions);
		disableHttpMethods(Destinataire.class,config, unsupportedActions);
		disableHttpMethods(Commande.class,config, unsupportedActions);

		//Call an internal helper methode to expose IDs
		exposeIds(config);
		cors.addMapping(config.getBasePath()+ "/**")
		.allowedOrigins(origines);
		//.allowedHeaders("*");

		//config.setReturnBodyForPutAndPost(true);
	}







	/**
	 * 
	 * @param theclass the class we want to desable the acces from REST
	 * @param config
	 * @param unsupportedActions
	 */
	private void disableHttpMethods(Class<?> theclass, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
		config.getExposureConfiguration()
		.forDomainType(theclass)
		.withItemExposure((metData,httpMethods)-> httpMethods.disable(unsupportedActions))
		.withCollectionExposure((metData,httpMethods)-> httpMethods.disable(unsupportedActions));
	}


	private void exposeIds(RepositoryRestConfiguration config) {
		// expose ids

		// get list if all entity classes from entity manager 

		Set<EntityType<?>>  entities = entityManager.getMetamodel().getEntities();

		// create an array of the entity types

		List<Class<?>> entityClasses = new ArrayList<>();

		// - get the entityTypes for the entity

		for ( EntityType<?> tempEntitype : entities  ) {
			entityClasses.add(tempEntitype.getJavaType());
		}

		// expose the entity ids for the array of entity?domain  types
		Class[] domainTypes = entityClasses.toArray(new Class[0]);

		config.exposeIdsFor(domainTypes);
	}


}
