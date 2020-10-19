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
                    Список студентов, имеющих 5:
                </div>
                <div class="content">
                    <xsl:for-each select="*">
                        <xsl:if test="subject[1]/@value = 5 and 
                                    subject[2]/@value = 5 and 
                                    subject[3]/@value = 5 and 
                                    subject[4]/@value = 5">
                            <div class="student">
                                <xsl:value-of select="@surname"/>&#160;<xsl:value-of select="@name"/>&#160;<xsl:value-of select="@middlename"/>
                            </div>
                        </xsl:if>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
