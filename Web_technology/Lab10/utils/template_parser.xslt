<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <link rel="stylesheet" href="../utils/styles_template.css"/>
                <link rel="stylesheet" href="../utils/styles_contacts.css"/>
                <link rel="stylesheet" href="../utils/styles_employees.css"/>
            </head>
            <body class="no_margin">
                <header>
                    Кровля
                </header>
                <div class="main">
                    <div class="left">
                        <div class="top_left">
                            Главное меню сайта
                        </div>
                        <div class="bottom_left">
                            <a href="../contacts/contacts.xml">Контакты</a>
                            <a href="../sales/employees.xml">Расчетный</a>
                            <a href="../analytics/employees.xml">Аналитический</a>                            
                        </div> 
                    </div>
                    <div class="right">
                        <xsl:apply-templates select="/company"/>
                        <xsl:apply-templates select="/employees"/>
                    </div>
                </div>
                <footer>
                    Практическое занятие 10
                </footer>                
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
