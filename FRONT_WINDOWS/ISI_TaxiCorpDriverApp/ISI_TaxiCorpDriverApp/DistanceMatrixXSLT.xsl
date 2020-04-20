<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="2.0"
				xmlns:exsl="http://exslt.org/common" extension-element-prefixes="exsl">  
  <xsl:import-schema schema-location="DistanceMatrixSchema.xsd" />
  <xsl:template match="/">
  <DISTANCE>
     <xsl:for-each select="DistanceMatrixResponse/row/element/distance">
        <xsl:value-of select="text"/>
      </xsl:for-each>
  </DISTANCE>
  </xsl:template>
 
</xsl:stylesheet>