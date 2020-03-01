import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import service.AccountService;

import java.util.logging.Logger;

public class Application extends ResourceConfig {
    public Application(){
        //注册JSON解析器
        register(JacksonJsonProvider.class);
        register(Logger.class);
    }
}
