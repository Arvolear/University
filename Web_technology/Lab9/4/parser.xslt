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
                    Список студентов со средним баллом:
                </div>
                <div class="content">
                    <xsl:for-each select="*">                                                                                                                   
                        <xsl:choose>
                            <xsl:when test="sum(subject/@value) div 4 &gt; 4">
                                <div class="student_bold">
                                    <xsl:value-of select="@surname"/>&#160;<xsl:value-of select="@name"/>&#160;<xsl:value-of select="@middlename"/>
                                    - 
                                    <xsl:value-of select="sum(subject/@value) div 4"/>
                                </div>
                            </xsl:when>
                            <xsl:when test="sum(subject/@value) div 4 &lt; 3">
                                <div class="student_italic">
                                    <xsl:value-of select="@surname"/>&#160;<xsl:value-of select="@name"/>&#160;<xsl:value-of select="@middlename"/>
                                    - 
                                    <xsl:value-of select="sum(subject/@value) div 4"/>
                                </div>
                            </xsl:when>
                            <xsl:otherwise>
                                <div class="student">
                                    <xsl:value-of select="@surname"/>&#160;<xsl:value-of select="@name"/>&#160;<xsl:value-of select="@middlename"/>
                                    - 
                                    <xsl:value-of select="sum(subject/@value) div 4"/>
                                </div>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
