package ir.dealit.restful.util.event;

import org.springframework.context.ApplicationEvent;

public class StartupEvent extends ApplicationEvent {
    public StartupEvent(Object source) {
        super(source);
    }
}
