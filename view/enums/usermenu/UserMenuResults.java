package view.enums.usermenu;
import java.util.regex.Matcher;
public enum UserMenuResults
{
        BACK,
        END;
        Matcher matcher;
        public Matcher getMatcher()
        {
            return matcher;
        }
        public void setMatcher(Matcher matcher)
        {
            this.matcher = matcher;
        }
}
