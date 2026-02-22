package backend.service;

import backend.model.User;
import backend.model.Role;

public class AccessControlService {
    
    public static boolean canEditProducts(User user) {
        // Slujitel i Administrator opravlqwat produkt
        return user.getRole() == Role.ADMIN || user.getRole() == Role.EMPLOYEE;
    }

    public static boolean canManageUsers(User user) {
        // Admin upravlqva pravata na potrebitelite
        return user.getRole() == Role.ADMIN;
    }

    public static boolean canGenerateReports(User user) {
        // Manager i admin mogat
        return user.getRole() == Role.MANAGER || user.getRole() == Role.ADMIN;
    }
}