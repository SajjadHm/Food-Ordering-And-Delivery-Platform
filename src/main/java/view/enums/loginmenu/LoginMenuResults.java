package view.enums.loginmenu;

import java.util.regex.Matcher;

public enum LoginMenuResults {
    ADMIN_LOGIN,
    USER_LOGIN,
    BACK,
    END,
    ;

    Matcher matcher;

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }
}
