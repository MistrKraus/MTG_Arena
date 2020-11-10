package cz.zcu.krausp.ups.utils;

public class General {

    private static final char PACKAGE_CLASS_SEPAR = '.';

    /**
     * Return class name without packages
     *
     * @param o any object
     * @return class name
     */
    public static String getClassName(Object o)
    {
        String cls = o.getClass().toString();

        if (cls.contains(String.valueOf(PACKAGE_CLASS_SEPAR))) {
            return cls.substring(cls.lastIndexOf('.') + 1);
        }

        return cls;
    }
}
