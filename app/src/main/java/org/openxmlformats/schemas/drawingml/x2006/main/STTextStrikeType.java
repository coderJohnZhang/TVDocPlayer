/*
 * XML Type:  ST_TextStrikeType
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/main
 * Java type: org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.main;


/**
 * An XML ST_TextStrikeType(@http://schemas.openxmlformats.org/drawingml/2006/main).
 *
 * This is an atomic type that is a restriction of org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType.
 */
public interface STTextStrikeType extends org.apache.xmlbeans.XmlToken
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(STTextStrikeType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("sttextstriketype4744type");
    
    org.apache.xmlbeans.StringEnumAbstractBase enumValue();
    void set(org.apache.xmlbeans.StringEnumAbstractBase e);
    
    static final Enum NO_STRIKE = Enum.forString("noStrike");
    static final Enum SNG_STRIKE = Enum.forString("sngStrike");
    static final Enum DBL_STRIKE = Enum.forString("dblStrike");
    
    static final int INT_NO_STRIKE = Enum.INT_NO_STRIKE;
    static final int INT_SNG_STRIKE = Enum.INT_SNG_STRIKE;
    static final int INT_DBL_STRIKE = Enum.INT_DBL_STRIKE;
    
    /**
     * Enumeration value class for org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType.
     * These enum values can be used as follows:
     * <pre>
     * enum.toString(); // returns the string value of the enum
     * enum.intValue(); // returns an int value, useful for switches
     * // e.g., case Enum.INT_NO_STRIKE
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

        static final int INT_NO_STRIKE = 1;
        static final int INT_SNG_STRIKE = 2;
        static final int INT_DBL_STRIKE = 3;

        public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
            new org.apache.xmlbeans.StringEnumAbstractBase.Table
        (
            new Enum[]
            {
                new Enum("noStrike", INT_NO_STRIKE),
                new Enum("sngStrike", INT_SNG_STRIKE),
                new Enum("dblStrike", INT_DBL_STRIKE),
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
