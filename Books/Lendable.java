package Books;

import Users.User;

import java.io.Serializable;

public interface Lendable extends Serializable {

    public static final long serialVersionUID = 1L;

    public boolean lend(User user);
    public void returnBook(User user);
    public boolean isAvailable();

}
