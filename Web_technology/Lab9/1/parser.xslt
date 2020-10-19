<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" indent="yes"/>

    <xsl:template match="group">
        <html>
            <head>
                <link rel="stylesheet" href="styles.css"/>
            </head>
            <body>
                <div class="title">
                    Список студентов, сдавших Информатику:
                </div>
                <div class="content">
                    <xsl:apply-templates select="*"/>
                </div>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="student">
        <xsl:apply-templates select="subject[@name = 'Информатика' and @value != 0]"/>
    </xsl:template>

    <xsl:template match="subject">
        <div class="student">        
            <xsl:value-of select="../@surname"/>&#160;<xsl:value-of select="../@name"/>&#160;<xsl:value-of select="../@middlename"/>
        </div>
    </xsl:template>
</xsl:stylesheet>
