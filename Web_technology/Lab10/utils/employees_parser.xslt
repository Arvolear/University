<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:import href="template_parser.xslt"/>

    <xsl:template match="employees">
        <div class="top_employees_main">
            Список сотрудников отдела: <xsl:value-of select="@name"/>
        </div>

        <xsl:apply-templates select="*"/>
    </xsl:template>

    <xsl:template match="employee">
        <div>
            <xsl:value-of select="@name"/>&#160;<xsl:value-of select="@surname"/>
        </div>
    </xsl:template>   

</xsl:stylesheet>
