FROM httpd:2.4

RUN sed -i '/LoadModule proxy_module/s/^#//g' /usr/local/apache2/conf/httpd.conf
RUN sed -i '/LoadModule proxy_http_module/s/^#//g' /usr/local/apache2/conf/httpd.conf

RUN sed -i 's/Listen 80/Listen 8000/' /usr/local/apache2/conf/httpd.conf

RUN echo "IncludeOptional /etc/apache2/sites-enabled/*.conf" >> /usr/local/apache2/conf/httpd.conf

#COPY ./public-html/ /usr/local/apache2/htdocs/
COPY ./sites-enabled/ /etc/apache2/sites-enabled/
