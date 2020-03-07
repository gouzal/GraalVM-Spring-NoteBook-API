package com.gouzal.notebook.models;

import lombok.Getter;
import lombok.Setter;
import org.graalvm.polyglot.Context;

import java.util.Objects;

/**
 * The class that will hold and match the GraalVM context with its user sessionId
 */
@Setter
@Getter
public class UserSession {
    private String sessionId;
    private Context context;

    public UserSession(String sessionId) {
        this.sessionId = sessionId;
        this.context = Context.create();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSession)) return false;
        UserSession that = (UserSession) o;
        return Objects.equals(getSessionId(), that.getSessionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSessionId());
    }
}
