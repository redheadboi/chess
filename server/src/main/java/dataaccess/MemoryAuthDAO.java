package dataaccess;

import model.AuthData;

import java.util.HashMap;
import java.util.UUID;

public class MemoryAuthDAO implements AuthDAO {

    private final HashMap<String, AuthData> data = new HashMap<>();


    @Override
    public AuthData createAuth(String username) throws DataAccessException {
        String authToken = UUID.randomUUID().toString();
        AuthData authData = new AuthData(username, authToken);
        data.put(authToken, authData);
        return authData;
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        AuthData authData = data.get(authToken);
        if (authData == null) {
            throw new DataAccessException("Cannot getAuth: authToken is not in database");
        } else {
            return authData;
        }
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {
        AuthData success = data.remove(authToken);
        if (success == null) {
            throw new DataAccessException("authToken is not in database");
        }
    }

    @Override
    public void clear() {
        data.clear();
    }


}