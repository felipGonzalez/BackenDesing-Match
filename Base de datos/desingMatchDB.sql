DROP DATABASE desingMatchDB;
CREATE DATABASE desingMatchDB;

use desingMatchDB;

DROP TABLE IF EXISTS desingMatchDB.proyecto_diseno;
DROP TABLE IF EXISTS desingMatchDB.proyectos;
DROP TABLE IF EXISTS desingMatchDB.disenos;
DROP TABLE IF EXISTS desingMatchDB.companias;



CREATE TABLE companias(
	id_compania INT(4) NOT NULL,
	nombre_compania VARCHAR(60) NOT NULL,
	contrasena VARCHAR(256) NOT NULL,
	email VARCHAR(100),
    url_compania VARCHAR(60) NOT NULL,
	PRIMARY KEY (id_compania)
);

CREATE TABLE proyectos(
	id_proyecto INT(4) NOT NULL,
	nombre_proyecto VARCHAR(60) NOT NULL,
	descripcion VARCHAR(255) NOT NULL,
	valor INT NOT NULL,
	id_compania INT(4) NOT null,
	PRIMARY KEY (id_proyecto)
);

CREATE TABLE disenos(
	id_diseno INT(4) NOT NULL,
	precio INT NOT NULL,
	url_img_original VARCHAR(255),
    url_img_procesado VARCHAR(255),
	id_proyecto INT(4) NOT NULL,
	estado ENUM('P','D')  NOT NULL,
    nombre_disenador VARCHAR(60) NOT NULL,
	apellido_disenador VARCHAR(60) NOT NULL,
	email VARCHAR(100),
    fecha DATE NOT NULL,
    nombre_compania VARCHAR(255),
	PRIMARY KEY (id_diseno)
);

CREATE TABLE proyecto_diseno(
	id_proyecto INT(4) NOT NULL,
	id_diseno INT(4) NOT NULL,
	PRIMARY KEY  (id_proyecto,id_diseno)
);


 
ALTER TABLE companias ADD(
	CONSTRAINT constraint_name UNIQUE(email)
);

ALTER TABLE proyectos ADD(
	CONSTRAINT pro_fk_idcom FOREIGN KEY (id_compania) REFERENCES companias(id_compania)
);

ALTER TABLE disenos ADD(
	CONSTRAINT des_fk_iddis FOREIGN KEY (id_proyecto) REFERENCES proyectos(id_proyecto)
    ON DELETE CASCADE
);

ALTER TABLE proyecto_diseno ADD(
	CONSTRAINT des_fk_idpro FOREIGN KEY (id_proyecto) REFERENCES proyectos(id_proyecto),
	CONSTRAINT des_fk_ides foreign key (id_diseno) REFERENCES disenos(id_diseno)
);
/*


/*Insecion de prueba Compañias
INSERT INTO `desingMatchDB`.`companias` (`id_compania`, `nombre_compania`, `contrasena`, `email`,`url_compania`) VALUES ('1', 'UPTC','123456','uptc@uptc.edu.co','uptc');
INSERT INTO `desingMatchDB`.`companias` (`id_compania`, `nombre_compania`, `contrasena`, `email`,`url_compania`) VALUES ('2', 'COOPCASUR','123456','coopcasur@gmil.com', 'coopcasur');


/*Insercion de prueba Proyectos
INSERT INTO `desingMatchDB`.`proyectos` (`id_proyecto`, `nombre_proyecto`, `descripcion`, `valor`,`id_compania`) VALUES ('1', 'Revive tus momentos','lorem ipsum dolor sit amet,consectetur adipiscing elit',
'3123434', '1');
INSERT INTO `desingMatchDB`.`proyectos` (`id_proyecto`, `nombre_proyecto`, `descripcion`, `valor`,`id_compania`) VALUES ('2', 'proyecto 2','Lorem ipsum dolor sit amet,
 consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','54312345', '1');
INSERT INTO `desingMatchDB`.`proyectos` (`id_proyecto`, `nombre_proyecto`, `descripcion`, `valor`,`id_compania`) VALUES ('3', 'LOREM','Lorem ipsum dolor sit amet,
 consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','123123543', '1');
INSERT INTO `desingMatchDB`.`proyectos` (`id_proyecto`, `nombre_proyecto`, `descripcion`, `valor`,`id_compania`) VALUES ('4', 'LOREM','Lorem ipsum dolor sit amet,
 consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
 ex ea commodo consequat.','1231234354', '1');
INSERT INTO `desingMatchDB`.`proyectos` (`id_proyecto`, `nombre_proyecto`, `descripcion`, `valor`,`id_compania`) VALUES ('5', 'LOREM','Lorem ipsum dolor sit amet,
 consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','45343123', '1');
INSERT INTO `desingMatchDB`.`proyectos` (`id_proyecto`, `nombre_proyecto`, `descripcion`, `valor`,`id_compania`) VALUES ('6', 'LOREM','Lorem ipsum dolor sit amet,
 consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et  
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','546456', '1');
INSERT INTO `desingMatchDB`.`proyectos` (`id_proyecto`, `nombre_proyecto`, `descripcion`, `valor`,`id_compania`) VALUES ('7', 'LOREM','Lorem ipsum dolor sit amet,
 consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','45646456', '1');
INSERT INTO `desingMatchDB`.`proyectos` (`id_proyecto`, `nombre_proyecto`, `descripcion`, `valor`,`id_compania`) VALUES ('8', 'LOREM','Lorem ipsum dolor sit amet,
 consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','1234553', '1');
INSERT INTO `desingMatchDB`.`proyectos` (`id_proyecto`, `nombre_proyecto`, `descripcion`, `valor`,`id_compania`) VALUES ('9', 'proyecto1','Lorem ipsum dolor sit amet,
 consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim 	
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','4354213', '2');
INSERT INTO `desingMatchDB`.`proyectos` (`id_proyecto`, `nombre_proyecto`, `descripcion`, `valor`,`id_compania`) VALUES ('10', 'LOREM','Lorem ipsum dolor sit amet,
 consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','53433535', '2');*/

/* Inserciones prueba diseños*/
/*
INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'1', '43121', '1.jpeg', '1.jpeg', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/28');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'2', '43121', '2.jpg', '2.jpg', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/15');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'3', '43121', '3.png', '3.png', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/07/15');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'4', '43121', '4.jpg', '4.jpg', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/13');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'5', '43121', '5.png', '5.png', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/20');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'6', '43121', '6.png', '6.png', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/05/15');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'7', '43121', '7.jpg', '7.jpg', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/30');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'8', '43121', '8.png', '8.png', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/05/02');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'9', '43121', '9.png', '9.png', '1', 'P', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/26');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'10', '43121', '8.png', '8.png', '9', 'P', 'felipe', 'gonzalez', 'dasd@das.com', '2019/05/02');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'11', '43121', '9.png', '9.png', '9', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/26');


INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'12', '43121', '1.jpeg', '1.jpeg', '2', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/28');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'13', '43121', '2.jpg', '2.jpg', '2', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/15');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'14', '43121', '1.jpeg', '1.jpeg', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/28');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'15', '43121', '2.jpg', '2.jpg', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/07/15');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'16', '43121', '3.png', '3.png', '1', 'P', 'felipe', 'gonzalez', 'dasd@das.com', '2019/07/12');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'17', '43121', '4.jpg', '4.jpg', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/14');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'18', '43121', '5.png', '5.png', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/21');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'19', '43121', '6.png', '6.png', '1', 'P', 'felipe', 'gonzalez', 'dasd@das.com', '2019/05/12');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'20', '43121', '7.jpg', '7.jpg', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/16');

INSERT INTO `desingmatchdb`.`disenos` (`id_diseno`, `precio`, `url_img_original`, `url_img_procesado`, `id_proyecto`, `estado`, `nombre_disenador`, `apellido_disenador`, `email`, `fecha`) VALUES (
'21', '43121', '8.png', '8.png', '1', 'D', 'felipe', 'gonzalez', 'dasd@das.com', '2019/06/03');*/

