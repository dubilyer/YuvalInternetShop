package logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

public class LoggerDecorator {
    private Class cl;

    public LoggerDecorator(Class cl) {
        this.cl = cl;
    }

    private static final Logger logger = LogManager.getLogger();

    public static Logger getLogger() {
        return logger;
    }

    public void logRequest(RequestMethod method, String url) {
        logger.log(Level.DEBUG, "controller " + cl.getSimpleName() + ": " + method.name() + ": " + url);
    }

    public void logResponse(ResponseEntity responseEntity) {
        HttpStatus status = responseEntity.getStatusCode();
        logger.log((status.value() == 200) ? Level.DEBUG : Level.ERROR,
                "controller" + cl.getSimpleName() + ": " +status.value());
    }

}
