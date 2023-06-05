package petproschedulerservice.utils;

import java.util.UUID;

public class PetProUtils {

    public static boolean isValidString (String input) {
        if (input.contains("\"") || input.contains("'") || input.contains("\\")
        || input.contains("`")) {
            return false;
        }
        return true;
    }

    public static String generateId () {
        final int ID_LENGTH = 5;
        UUID uuid = UUID.randomUUID();
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        long combinedBits = mostSignificantBits ^ leastSignificantBits;
        String id = Long.toString(combinedBits, 36).toUpperCase();
        return id.substring(Math.max(id.length() - ID_LENGTH, 0));
    }
}
