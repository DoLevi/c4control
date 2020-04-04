package de.untenrechts.c4control.mod;

import de.untenrechts.c4control.C4Control;

public class NameUtils {

    public static String toUnlocalizedName(final String blockName) {
        return String.format("%s.%s", C4Control.MOD_ID, blockName);
    }
}
