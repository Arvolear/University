<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <link rel="stylesheet" href="../utils/styles_template.css"/>
            </head>
            <body class="no_margin">
                <header>
                    Кровля
                </header>
                <main>
                    <left>
                        <top_left>
                            Главное меню сайта
                        </top_left>
                        <bottom_left>
                            <a href="../contacts/contacts.xml">Контакты</a>
                            <a href="../sales/employees.xml">Расчетный</a>
                            <a href="../analytics/employees.xml">Аналитический</a>                            
                        </bottom_left>                        
                    </left>
                    <right>
                        <xsl:apply-templates select="/company"/>
                        <xsl:apply-templates select="/employees"/>
                    </right>
                </main>
                <footer>
                    Практическое занятие 10
                </footer>                
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
