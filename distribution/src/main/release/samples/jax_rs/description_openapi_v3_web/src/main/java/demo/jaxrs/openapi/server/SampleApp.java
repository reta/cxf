package demo.jaxrs.openapi.server;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.cxf.jaxrs.openapi.OpenApiFeature;
import org.apache.cxf.jaxrs.provider.MultipartProvider;
import org.apache.cxf.jaxrs.swagger.ui.SwaggerUiConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@ApplicationPath("/")
public class SampleApp extends Application {
    @Override
    public Set<Object> getSingletons() {
        final OpenApiFeature feature = new OpenApiFeature();
        
        feature.setSwaggerUiConfig(
            new SwaggerUiConfig()
                .url("/app/openapi.json")); /* please change that */
        
        final Set<Object> singletons = new HashSet<>();
        singletons.add(new Sample());
        singletons.add(feature);
        singletons.add(new MultipartProvider());
        singletons.add(new JacksonJsonProvider());
        singletons.add(new ApiOriginFilter());
        return singletons;
    }
}
