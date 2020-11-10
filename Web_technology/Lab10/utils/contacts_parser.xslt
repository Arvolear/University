<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:import href="template_parser.xslt"/>

    <xsl:template match="company">
        <link rel="stylesheet" href="../utils/styles_contacts.css"/>

        <top_main>
            Контакты компании: <xsl:value-of select="@name"/>
        </top_main>

        <xsl:apply-templates select="*"/>
    </xsl:template>

    <xsl:template match="address">
        <address>
            Адрес: <xsl:value-of select="text()"/>
        </address>
    </xsl:template>

    <xsl:template match="number">
        <number>
            Телефон: <xsl:value-of select="text()"/>
        </number>
    </xsl:template>

    <xsl:template match="email">
        <email>
            Электронная почта: <xsl:value-of select="text()"/>
        </email>
    </xsl:template>

</xsl:stylesheet>
