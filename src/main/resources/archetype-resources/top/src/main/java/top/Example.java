#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.top;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.validation.ValidatorFactory;

import com.thesett.util.servlet.filter.CORSFilter;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ${package}.services.ServiceFactory;
import ${package}.config.AppConfiguration;

/**
 * Example is a DropWizard application extension point, allowing the environment to be configured,
 * additional services created, additional databse mappings to be created, and example data to be 
 * inserted.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th>
 * <tr><td> Bootstrap the DropWizard environment. </td></tr>
 * <tr><td> Add additional services. </td></tr>
 * <tr><td> Add additional Hibernate ORM mappings. </td></tr>
 * <tr><td> Create some example data. </td></tr>
 * </table></pre>
 */
public class Example {
    /**
     * Sets up some additional DropWizard modules.
     * 
     * @param bootstrap The DropWizard bootstrap configuration.
     */
    public void bootstrap(Bootstrap<AppConfiguration> bootstrap) {
    }

    /**
     * Insert some example data.
     *
     * @param serviceFactory The service factory.
     */
    public void example(ServiceFactory serviceFactory) {
    }

    /**
     * Sets up some additional non-generated serviced.
     *
     * @param appConfiguration The application configuration.
     * @param environment      The DropWizard environment.
     * @param sessionFactory   The Hibernate session factory.
     * @param validatorFactory The Hibernate Validator factory.
     * @param serviceFactory   The service factory.
     */
    public void initAdditionalServices(AppConfiguration appConfiguration, Environment environment,
        SessionFactory sessionFactory, ValidatorFactory validatorFactory, ServiceFactory serviceFactory) {
        // Add the CORS fitler to allow cross-origin browsing to this API - needed to support
        // javascript clients that are running on a different origin to the one this API is
        // being served from. Disable this for secuirty, if a javascript client is not being used
        // or is being served from the same origin.
        environment.servlets()
            .addFilter("cors", new CORSFilter())
            .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
    }

    /**
     * Adds any classes with Hibernate queries to the Hibernate configuration.
     *
     * @param configuration The Hibernate configuration.
     */
    public void addHibernateClasses(Configuration configuration) {
    }
}
