alter table foro_alura.topicos add activo tinyint;
update foro_alura.topicos set topicos.activo = 1;