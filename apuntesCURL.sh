
# curl permite trabajar con urls, acer peticiones y hacer pruebas con apis

# visualizar los headers
> curl -v localhost:8080/status

# visualizar los header mas resumido
> curl -i localhost:8080/status

# enviar headers
>curl -v --header 'clave:valor' www.google.com

# header y datos
>curl -v --header 'clave:valor' www.google.com

>curl -X POST -d 'datos' www.google.com


borrame
