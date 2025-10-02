package G50.pdp.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityDelegatingProxy extends AbstractSecurityWebApplicationInitializer {
    @Override
    protected boolean isAsyncSecuritySupported() {
        return super.isAsyncSecuritySupported();
    }

    @Override
    protected boolean enableHttpSessionEventPublisher() {
            return super.enableHttpSessionEventPublisher();
    }
}
