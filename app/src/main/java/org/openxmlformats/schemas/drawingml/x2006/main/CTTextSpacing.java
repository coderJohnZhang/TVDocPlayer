/*
 * XML Type:  CT_TextSpacing
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/main
 * Java type: org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.main;


/**
 * An XML CT_TextSpacing(@http://schemas.openxmlformats.org/drawingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTTextSpacing extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTextSpacing.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttextspacingef87type");
    
    /**
     * Gets the "spcPct" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent getSpcPct();
    
    /**
     * True if has "spcPct" element
     */
    boolean isSetSpcPct();
    
    /**
     * Sets the "spcPct" element
     */
    void setSpcPct(org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent spcPct);
    
    /**
     * Appends and returns a new empty "spcPct" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent addNewSpcPct();
    
    /**
     * Unsets the "spcPct" element
     */
    void unsetSpcPct();
    
    /**
     * Gets the "spcPts" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint getSpcPts();
    
    /**
     * True if has "spcPts" element
     */
    boolean isSetSpcPts();
    
    /**
     * Sets the "spcPts" element
     */
    void setSpcPts(org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint spcPts);
    
    /**
     * Appends and returns a new empty "spcPts" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint addNewSpcPts();
    
    /**
     * Unsets the "spcPts" element
     */
    void unsetSpcPts();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }

        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }

        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }

        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }

        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }

        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
