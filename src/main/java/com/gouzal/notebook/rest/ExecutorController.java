package com.gouzal.notebook.rest;

import com.gouzal.notebook.exceptions.InvalidScriptException;
import com.gouzal.notebook.exceptions.UnsupportedLanguageException;
import com.gouzal.notebook.models.Command;
import com.gouzal.notebook.models.UserSession;
import com.gouzal.notebook.common.Util;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
public class ExecutorController {

    @Autowired
    HttpSession httpSession;

    @GetMapping(value = "/execute", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity execute(@RequestBody Command command) throws InvalidScriptException, UnsupportedLanguageException {
        this.initialize();
        Context context=this.getUserSession(command.getSessionId()).getContext();
        Value result = context.eval(command.getInterpreter(), command.getScript());
        Map resultResponse = new HashMap<String, String>();
        resultResponse.put("result", Util.filter(result.toString()));

        return new ResponseEntity<Map>(resultResponse, HttpStatus.OK);
    }

    /**
     * Return the User Object and its Polyglot context from HttpSession
     * @param sessionId the UserId in the HttpSession
     * @return UserSession the Object that represent the User Polyglot context in the HttpSession
     */
    public UserSession getUserSession(String sessionId) {
        UserSession userSession;
        Map usersPolyglotSessions = (Map) this.httpSession.getAttribute("UsersPolyglotSessions");
        if (usersPolyglotSessions.containsKey(sessionId)) {

            userSession = (UserSession) usersPolyglotSessions.get(sessionId);
        } else {
            userSession = new UserSession(sessionId);
            usersPolyglotSessions.put(sessionId, userSession);
        }
        return userSession;

    }

    /**
     * Initialize the Session with a Map of Users Context
     */
    private void initialize() {
        if (this.httpSession.getAttribute("UsersPolyglotSessions") == null) {
            this.httpSession.setAttribute("UsersPolyglotSessions", new HashMap<String, UserSession>());
        }
    }
}
