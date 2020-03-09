package com.gouzal.notebook.restapi;

import com.gouzal.notebook.exceptions.InvalidScriptException;
import com.gouzal.notebook.exceptions.UnsupportedLanguageException;
import com.gouzal.notebook.models.Command;
import com.gouzal.notebook.models.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
public class ExecutorController {

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/")
    public @ResponseBody
    String home() {
        return "Hello, in my little Jupyter Notebook!";
    }
    @GetMapping(value = "/execute", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity execute(@RequestBody Command command) throws InvalidScriptException, UnsupportedLanguageException {
        this.initialize();
        Map result = command.execute(this.getUserSession(command.getSessionId()).getContext());
        return new ResponseEntity<Map>(result, HttpStatus.OK);
    }

    /**
     * Return the User Object and its Polyglot context from HttpSession
     * @param sessionId the UserId in the HttpSession
     * @return UserSession the Object that represent the User Polyglot context in the HttpSession
     */
    public UserSession getUserSession(String sessionId) {
        UserSession userSession;
        Map usersPolyglotSessions = (Map) this.httpSession.getAttribute("usersPolyglotSessions");
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
        if (this.httpSession.getAttribute("usersPolyglotSessions") == null) {
            this.httpSession.setAttribute("usersPolyglotSessions", new HashMap<String, UserSession>());
        }
    }
}
