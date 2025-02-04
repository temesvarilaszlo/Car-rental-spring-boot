# Autókölcsönző alkalmazás

A szerver alapértelmezetten a localhost:8080-as porton indul el. Az adatbázis konfigurációját az application.properties 
fájlban lehet megadni, ezen paraméterekkel:
* `spring.datasource.url`
* `spring.datasource.username`
* `spring.datasource.password`

## Végpontok
`GET`
* `/`: ha nem adminként vagyunk bejelentkezve akkor ez a kezdőoldal, ahonnan kiválaszthatjuk a foglalás időpontját 
datepickerből
* `/admin`: adminisztrációs oldal, ami csak akkor érhető el, ha adminként vagyunk bejelentkezve
* `/cars/free`: a query paraméterekben megadott időszakban szabad autókat jeleníti meg
* `/cars/create`: új autó létrehozása form, csak admin joggal
* `/cars/{id}/edit`: autó szerkesztése form, csak admin joggal
* `/reservations/create`: új foglalás létrehozása form
* `/rest-api/cars/free`: a query paraméterekben megadott időszakban szabad autókat adja vissza (REST API)

`POST`
* `/cars`: új autó létrehozása, csak admin joggal
* `/cars/{id}/activate`: autó deaktiválása/aktiválása, csak admin joggal
* `/reservations`: új foglalás létrehozása
* `/rest-api/reservations`: új foglalás létrehozása (REST API)

`PUT`
* `/cars/{id}`: autó szerkesztése, csak admin joggal
