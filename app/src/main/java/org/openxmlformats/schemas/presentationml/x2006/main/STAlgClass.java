/*
 * XML Type:  ST_AlgClass
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.STAlgClass
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML ST_AlgClass(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is an atomic type that is a restriction of org.openxmlformats.schemas.presentationml.x2006.main.STAlgClass.
 */
public interface STAlgClass extends org.apache.xmlbeans.XmlString
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(STAlgClass.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("stalgclass16d9type");
    
    org.apache.xmlbeans.StringEnumAbstractBase enumValue();
    void set(org.apache.xmlbeans.StringEnumAbstractBase e);
    
    static final Enum HASH = Enum.forString("hash");
    static final Enum INVALID = Enum.forString("invalid");
    
    static final int INT_HASH = Enum.INT_HASH;
    static final int INT_INVALID = Enum.INT_INVALID;
    
    /**
     * Enumeration value class for org.openxmlformats.schemas.presentationml.x2006.main.STAlgClass.
     * These enum values can be used as follows:
     * <pre>
     * enum.toString(); // returns the string value of the enum
     * enum.intValue(); // returns an int value, useful for switches
     * // e.g., case Enum.INT_HASH
     * Enum.forString(s); // returns the enum value for a string
     * Enum.forInt(i); // returns the enum value for an int
     * </pre>
     * Enumeration objects are immutable singleton objects that
     * can be compared using == object equality. They have no
     * public constructor. See the constants defined within this
     * class for all the valid values.
     */
    static final class Enum extends org.apache.xmlbeans.StringEnumAbstractBase
    {
        /**
         * Returns the enum value for a string, or null if none.
         */
        public static Enum forString(String s)
            { return (Enum)table.forString(s); }
        /**
         * Returns the enum value corresponding to an int, or null if none.
         */
        public static Enum forInt(int i)
            { return (Enum)table.forInt(i); }

        private Enum(String s, int i)
            { super(s, i); }

        static final int INT_HASH = 1;
        static final int INT_INVALID = 2;

        public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
            new org.apache.xmlbeans.StringEnumAbstractBase.Table
        (
            new Enum[]
            {
                new Enum("hash", INT_HASH),
                new Enum("invalid", INT_INVALID),
            }
        );
        private static final long serialVersionUID = 1L;
        private Object readResolve() { return forInt(intValue()); }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
}
