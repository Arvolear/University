<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:import href="template_parser.xslt"/>

    <xsl:template match="company">
        <div class="top_contracts_main">
            Контакты компании: <xsl:value-of select="@name"/>
        </div>

        <xsl:apply-templates select="*"/>
    </xsl:template>

    <xsl:template match="address">
        <div>
            Адрес: <xsl:value-of select="text()"/>
        </div>
    </xsl:template>

    <xsl:template match="number">
        <div>
            Телефон: <xsl:value-of select="text()"/>
        </div>
    </xsl:template>

    <xsl:template match="email">
        <div>
            Электронная почта: <xsl:value-of select="text()"/>
        </div>
    </xsl:template>

</xsl:stylesheet>
