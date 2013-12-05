package edu.sjsu.cmpe.customerfeedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;

import edu.sjsu.cmpe.customerfeedback.api.resources.OwnerResource;
import edu.sjsu.cmpe.customerfeedback.api.resources.ProductResource;
import edu.sjsu.cmpe.customerfeedback.api.resources.ReviewResource;
import edu.sjsu.cmpe.customerfeedback.api.resources.RootResource;
import edu.sjsu.cmpe.customerfeedback.config.CustomerFeedbackServiceConfiguration;
import edu.sjsu.cmpe.customerfeedback.repository.OwnerRepository;
import edu.sjsu.cmpe.customerfeedback.repository.OwnerRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.repository.ProductRepository;
import edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.repository.ReviewRepository;
import edu.sjsu.cmpe.customerfeedback.repository.ReviewRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.repository.UserRepository;
import edu.sjsu.cmpe.customerfeedback.repository.UserRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.ui.resources.HomeResource;
import edu.sjsu.cmpe.customerfeedback.ui.resources.OwnerViewResource;
import edu.sjsu.cmpe.customerfeedback.ui.resources.ProductViewResource;
import edu.sjsu.cmpe.customerfeedback.ui.resources.CustomerViewResource;
import edu.sjsu.cmpe.customerfeedback.ui.resources.ReviewsViewResource;

public class CustomerFeedbackSevice extends Service<CustomerFeedbackServiceConfiguration> {
	private final Logger log =  LoggerFactory.getLogger(getClass());
	
    public static void main(String[] args) throws Exception {
	new CustomerFeedbackSevice().run(args);
    }
    
    @Override
    public void initialize(Bootstrap<CustomerFeedbackServiceConfiguration> bootstrap) {
	bootstrap.setName("customerfeedback-service");
	bootstrap.addBundle(new ViewBundle());
	bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(CustomerFeedbackServiceConfiguration configuration,
	    Environment environment) throws Exception {
	/** Root API */
    ProductRepositoryInterface productRepository = new ProductRepository();
    environment.addResource(new RootResource(productRepository));
	/** Owners APIs */
	OwnerRepositoryInterface ownerRepository = new OwnerRepository();
	environment.addResource(new OwnerResource(ownerRepository));
	/** Products APIs */	
	environment.addResource(new ProductResource(productRepository));	
	/** Reviews APIs **/
	ReviewRepositoryInterface reviewRepository = new ReviewRepository();
	environment.addResource(new ReviewResource(reviewRepository));	
	/** UI Resources */
	UserRepositoryInterface userRepository = new UserRepository();
	environment.addResource(new HomeResource(userRepository, ownerRepository));
	environment.addResource(new OwnerViewResource(productRepository,reviewRepository));
	environment.addResource(new CustomerViewResource(productRepository, reviewRepository, userRepository));
	environment.addResource(new ReviewsViewResource(reviewRepository));
	/** Add new resources here */
		
	log.debug(configuration.toString());
	//System.out.println(configuration.toString());
    }
}
