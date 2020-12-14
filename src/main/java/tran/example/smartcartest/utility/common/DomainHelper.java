package tran.example.smartcartest.utility.common;

import tran.example.smartcartest.model.domain.ApplicationUser;
import tran.example.smartcartest.model.domain.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods used across different services and other locations.
 */
public class DomainHelper {

    public static List<Role> roleIterableToList(Iterable<Role> iterable) {
        List<Role> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    public static List<ApplicationUser> userIterableToList(Iterable<ApplicationUser> iterable) {
        List<ApplicationUser> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }
}
