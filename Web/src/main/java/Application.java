import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import provider.GsonProvider;
import service.AccountService;

import java.util.logging.Logger;

public class Application extends ResourceConfig {
    public Application(){
        packages(AccountService.class.getPackage().getName());
        //注册JSON解析器
        register(GsonProvider.class);
        System.out.println("GsonProvider success");
        register(Logger.class);
    }
}
