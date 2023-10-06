alter table foro_alura.respuestas add activo tinyint;
update foro_alura.respuestas set respuestas.activo = 1;