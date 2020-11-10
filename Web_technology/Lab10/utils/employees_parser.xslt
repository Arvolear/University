<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:import href="template_parser.xslt"/>

    <xsl:template match="employees">
        <link rel="stylesheet" href="../utils/styles_employees.css"/>

        <top_main>
            Список сотрудников отдела: <xsl:value-of select="@name"/>
        </top_main>

        <xsl:apply-templates select="*"/>
    </xsl:template>

    <xsl:template match="employee">
        <employee>
            <xsl:value-of select="@name"/>&#160;<xsl:value-of select="@surname"/>
        </employee>
    </xsl:template>   

</xsl:stylesheet>
