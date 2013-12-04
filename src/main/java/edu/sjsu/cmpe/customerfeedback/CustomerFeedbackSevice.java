package edu.sjsu.cmpe.customerfeedback;

import java.util.concurrent.ConcurrentHashMap;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import edu.sjsu.cmpe.customerfeedback.api.resources.OwnerResource;
import edu.sjsu.cmpe.customerfeedback.api.resources.ProductResource;
import edu.sjsu.cmpe.customerfeedback.api.resources.ReviewResource;
import edu.sjsu.cmpe.customerfeedback.api.resources.RootResource;
import edu.sjsu.cmpe.customerfeedback.config.CustomerFeedbackServiceConfiguration;
import edu.sjsu.cmpe.customerfeedback.domain.Owner;
import edu.sjsu.cmpe.customerfeedback.domain.Product;
import edu.sjsu.cmpe.customerfeedback.domain.Review;
import edu.sjsu.cmpe.customerfeedback.repository.OwnerRepository;
import edu.sjsu.cmpe.customerfeedback.repository.OwnerRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.repository.ProductRepository;
import edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.repository.ReviewRepository;
import edu.sjsu.cmpe.customerfeedback.repository.ReviewRepositoryInterface;



public class CustomerFeedbackSevice extends Service<CustomerFeedbackServiceConfiguration> {
	//private final Logger log =  LoggerFactory.getLogger(getClass());
	
    public static void main(String[] args) throws Exception {
	new CustomerFeedbackSevice().run(args);
    }
    
    @Override
    public void initialize(Bootstrap<CustomerFeedbackServiceConfiguration> bootstrap) {
	bootstrap.setName("customerfeedback-service");
    }

    @Override
    public void run(CustomerFeedbackServiceConfiguration configuration,
	    Environment environment) throws Exception {
	/** Root API */
	environment.addResource(RootResource.class);
	/** Owners APIs */
	OwnerRepositoryInterface ownerRepository = new OwnerRepository(new ConcurrentHashMap<Integer, Owner>());
	environment.addResource(new OwnerResource(ownerRepository));
	/** Products APIs */
	ProductRepositoryInterface productRepository = new ProductRepository(new ConcurrentHashMap<Integer, Product>());
	environment.addResource(new ProductResource(productRepository));	
	/** Reviews APIs **/
	ReviewRepositoryInterface reviewRepository = new ReviewRepository(new ConcurrentHashMap<Integer, Review>());
	environment.addResource(new ReviewResource(reviewRepository));	
	/** Add new resources here */
	
	//log.debug(configuration.toString());
	//System.out.println(configuration.toString());
    }

	

}
