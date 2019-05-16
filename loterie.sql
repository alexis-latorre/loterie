-- --------------------------------------------------------
-- Hôte :                        51.255.49.30
-- Version du serveur:           10.1.37-MariaDB-0+deb9u1 - Debian 9.6
-- SE du serveur:                debian-linux-gnu
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Export de la structure de la base pour loterie_dev
CREATE DATABASE IF NOT EXISTS `loterie_dev` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `loterie_dev`;

-- Export de la structure de la table loterie_dev. alerte
CREATE TABLE IF NOT EXISTS `alerte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('I','Q','W','E') NOT NULL DEFAULT 'W',
  `message` mediumtext NOT NULL,
  `acquittee` tinyint(4) NOT NULL DEFAULT '0',
  `fk_utilisateur_id` int(11) DEFAULT NULL,
  `fk_grille_id` int(11) DEFAULT NULL,
  `fk_lgu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_alerte_utilisateur` (`fk_utilisateur_id`),
  KEY `FK_alerte_grille` (`fk_grille_id`),
  KEY `FK_alerte_lien_grille_utilisateur` (`fk_lgu_id`),
  CONSTRAINT `FK_alerte_grille` FOREIGN KEY (`fk_grille_id`) REFERENCES `grille` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_alerte_lien_grille_utilisateur` FOREIGN KEY (`fk_lgu_id`) REFERENCES `lien_grille_utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_alerte_utilisateur` FOREIGN KEY (`fk_utilisateur_id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de la table loterie_dev. banque
CREATE TABLE IF NOT EXISTS `banque` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_creation` date DEFAULT NULL,
  `fonds` double DEFAULT NULL,
  `mises` double DEFAULT NULL,
  `gains` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de l'évènement loterie_dev. checkJeux
DELIMITER //
CREATE DEFINER=`root`@`%` EVENT `checkJeux` ON SCHEDULE EVERY 1 DAY STARTS '2019-05-16 00:00:00' ON COMPLETION PRESERVE ENABLE DO BEGIN
	SET @maintenant = CURDATE();
	
	-- Si on est mardi
	IF WEEKDAY(@maintenant) = 1 THEN
		SET @mardi = DATE_FORMAT(@maintenant, '%Y-%m-%d 20:00:00');
		
		-- Si on a depasse l'heure de validation, on prend le mardi suivant
		IF @maintenant > @mardi THEN
			SET @mardi = @maintenant + INTERVAL 8 - WEEKDAY(@maintenant) DAY;
		END IF;
	-- Si on est apres de mardi
	ELSEIF WEEKDAY(@maintenant) > 1 THEN
		SET @mardi = @maintenant + INTERVAL 8 - WEEKDAY(@maintenant) DAY;
	-- Si on est avant de mardi
	ELSE
		SET @mardi = @maintenant + INTERVAL 1 - WEEKDAY(@maintenant) DAY;
	END IF;
	
	-- Si on est vendredi
	IF WEEKDAY(@maintenant) = 4 THEN
		SET @vendredi = DATE_FORMAT(@maintenant, '%Y-%m-%d 20:00:00');
		
		-- Si on a depasse l'heure de validation, on prend le vendredi suivant
		IF @maintenant > @vendredi THEN
			SET @vendredi = @maintenant + INTERVAL 11 - WEEKDAY(@maintenant) DAY;
		END IF;
	-- Si on est apres de vendredi
	ELSEIF WEEKDAY(@maintenant) > 4 THEN
		SET @vendredi = @maintenant + INTERVAL 11 - WEEKDAY(@maintenant) DAY;
	-- Si on est avant de vendredi
	ELSE
		SET @vendredi = @maintenant + INTERVAL 4 - WEEKDAY(@maintenant) DAY;
	END IF;
	
	-- On insere l'alerte dans la base uniquement si c'est une nouvelle entree
	INSERT INTO alerte (`type`, message, acquittee, fk_grille_id, fk_lgu_id)
	SELECT DISTINCT 'E', 'La grille ''%g'' va bientôt expirer', 0, l.fk_grille_id, l.id FROM lien_grille_utilisateur l 
	WHERE l.id NOT IN (
		SELECT DISTINCT j.fk_lien_gu_id FROM jour j WHERE j.date_jour > @mardi AND j.date_jour > @vendredi
	) AND (
		SELECT COUNT(id) FROM alerte WHERE fk_grille_id = l.fk_grille_id AND fk_lgu_id = l.id
	) = 0;
END//
DELIMITER ;

-- Export de la structure de la vue loterie_dev. gains_par_joueur
-- Création d'une table temporaire pour palier aux erreurs de dépendances de VIEW
CREATE TABLE `gains_par_joueur` (
	`id` VARCHAR(36) NOT NULL COLLATE 'utf8_general_ci',
	`utilisateur_id` INT(11) NOT NULL,
	`date_jour` DATETIME NOT NULL,
	`nom` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_general_ci',
	`gains` DOUBLE NOT NULL
) ENGINE=MyISAM;

-- Export de la structure de la table loterie_dev. grille
CREATE TABLE IF NOT EXISTS `grille` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL DEFAULT 'Grille sans nom',
  `numeros` set('1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50') NOT NULL,
  `etoiles` set('1','2','3','4','5','6','7','8','9','10','11','12') NOT NULL,
  `etoile_plus` tinyint(4) NOT NULL DEFAULT '0',
  `mymillion` varchar(50) DEFAULT NULL,
  `fk_createur` int(11) DEFAULT NULL,
  `fk_jeu_id` int(11) NOT NULL,
  `fk_banque_id` int(11) DEFAULT NULL,
  `publique` tinyint(1) NOT NULL DEFAULT '0',
  `visible` tinyint(1) NOT NULL DEFAULT '1',
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nom_fk_createur` (`nom`,`fk_createur`),
  KEY `FK_grille_utilisateur` (`fk_createur`),
  KEY `FK_grille_jeu` (`fk_jeu_id`),
  KEY `FK_grille_banque` (`fk_banque_id`),
  CONSTRAINT `FK_grille_banque` FOREIGN KEY (`fk_banque_id`) REFERENCES `banque` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_grille_jeu` FOREIGN KEY (`fk_jeu_id`) REFERENCES `jeu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_grille_utilisateur` FOREIGN KEY (`fk_createur`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de la table loterie_dev. jeu
CREATE TABLE IF NOT EXISTS `jeu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `tirage_par_semaine` int(2) NOT NULL,
  `jour_de_tirage` set('1','2','3','4','5','6','7') NOT NULL,
  `prix_par_tirage` double NOT NULL,
  `heure_validation` time NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nom` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de la table loterie_dev. jour
CREATE TABLE IF NOT EXISTS `jour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_jour` datetime NOT NULL,
  `fk_lien_gu_id` int(4) DEFAULT NULL,
  `paye` tinyint(1) NOT NULL DEFAULT '0',
  `gains` double NOT NULL DEFAULT '0',
  `gains_redistribues` double NOT NULL DEFAULT '0',
  `nb_joueurs` int(11) NOT NULL DEFAULT '1',
  `groupe` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_fk_lien_gu_id` (`date_jour`,`fk_lien_gu_id`),
  KEY `FK_semaine_liens_grille_utilisateur` (`fk_lien_gu_id`),
  CONSTRAINT `FK_semaine_liens_grille_utilisateur` FOREIGN KEY (`fk_lien_gu_id`) REFERENCES `lien_grille_utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=utf8mb4;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de la table loterie_dev. lien_grille_utilisateur
CREATE TABLE IF NOT EXISTS `lien_grille_utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_grille_id` int(11) NOT NULL,
  `fk_utilisateur_id` int(11) NOT NULL,
  `fonds` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_grille_id_fk_utilisateur_id` (`fk_grille_id`,`fk_utilisateur_id`),
  KEY `FK_liens_grille_utilisateur_utilisateur` (`fk_utilisateur_id`),
  CONSTRAINT `FK_liens_grille_utilisateur_grille` FOREIGN KEY (`fk_grille_id`) REFERENCES `grille` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_liens_grille_utilisateur_utilisateur` FOREIGN KEY (`fk_utilisateur_id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de la table loterie_dev. log
CREATE TABLE IF NOT EXISTS `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `utc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `jour` date NOT NULL,
  `heure` time NOT NULL,
  `niveau` enum('I','A','E','S','F') NOT NULL DEFAULT 'I',
  `type` enum('FINANCE','COMPTE','ADMINISTRATION','GRILLE','SERVEUR') NOT NULL DEFAULT 'SERVEUR',
  `fk_declencheur` int(11) DEFAULT NULL,
  `fk_joueur_lie` int(11) DEFAULT NULL,
  `fk_grille_liee` int(11) DEFAULT NULL,
  `message` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_declencheur` (`fk_declencheur`),
  KEY `fk_joueur_lie` (`fk_joueur_lie`),
  KEY `FK_log_grille` (`fk_grille_liee`),
  CONSTRAINT `FK_log_grille` FOREIGN KEY (`fk_grille_liee`) REFERENCES `grille` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_log_utilisateur_declencheur` FOREIGN KEY (`fk_declencheur`) REFERENCES `utilisateur` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_log_utilisateur_lie` FOREIGN KEY (`fk_joueur_lie`) REFERENCES `utilisateur` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8mb4;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de la table loterie_dev. portefeuille
CREATE TABLE IF NOT EXISTS `portefeuille` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_creation` date NOT NULL,
  `fonds` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de la table loterie_dev. privilege
CREATE TABLE IF NOT EXISTS `privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `privileges` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de la table loterie_dev. resultat_euromillions
CREATE TABLE IF NOT EXISTS `resultat_euromillions` (
  `numero_tirage` varchar(50) NOT NULL,
  `date_tirage` varchar(50) NOT NULL,
  `boule1` tinyint(3) unsigned NOT NULL,
  `boule2` tinyint(3) unsigned NOT NULL,
  `boule3` tinyint(3) unsigned NOT NULL,
  `boule4` tinyint(3) unsigned NOT NULL,
  `boule5` tinyint(3) unsigned NOT NULL,
  `etoile1` tinyint(3) unsigned NOT NULL,
  `etoile2` tinyint(3) unsigned NOT NULL,
  `gagnant_rang1` mediumint(8) unsigned NOT NULL,
  `gains_rang1` double unsigned NOT NULL,
  `gagnant_rang2` mediumint(8) unsigned NOT NULL,
  `gains_rang2` double unsigned NOT NULL,
  `gagnant_rang3` mediumint(8) unsigned NOT NULL,
  `gains_rang3` double unsigned NOT NULL,
  `gagnant_rang4` mediumint(8) unsigned NOT NULL,
  `gains_rang4` double unsigned NOT NULL,
  `gagnant_rang5` mediumint(8) unsigned NOT NULL,
  `gains_rang5` double unsigned NOT NULL,
  `gagnant_rang6` mediumint(8) unsigned NOT NULL,
  `gains_rang6` double unsigned NOT NULL,
  `gagnant_rang7` mediumint(8) unsigned NOT NULL,
  `gains_rang7` double unsigned NOT NULL,
  `gagnant_rang8` mediumint(8) unsigned NOT NULL,
  `gains_rang8` double unsigned NOT NULL,
  `gagnant_rang9` mediumint(8) unsigned NOT NULL,
  `gains_rang9` double unsigned NOT NULL,
  `gagnant_rang10` mediumint(8) unsigned NOT NULL,
  `gains_rang10` double unsigned NOT NULL,
  `gagnant_rang11` mediumint(8) unsigned NOT NULL,
  `gains_rang11` double unsigned NOT NULL,
  `gagnant_rang12` mediumint(8) unsigned NOT NULL,
  `gains_rang12` double unsigned NOT NULL,
  `gagnant_rang13` mediumint(8) unsigned NOT NULL,
  `gains_rang13` double unsigned NOT NULL,
  `mymillion` varchar(100) NOT NULL,
  PRIMARY KEY (`numero_tirage`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de la table loterie_dev. retard
CREATE TABLE IF NOT EXISTS `retard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_joueur_id` int(11) NOT NULL DEFAULT '0',
  `date_constat` datetime DEFAULT NULL,
  `date_relance` datetime DEFAULT NULL,
  `relance` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_joueur` (`fk_joueur_id`),
  CONSTRAINT `FK_retard_utilisateur` FOREIGN KEY (`fk_joueur_id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de la table loterie_dev. utilisateur
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(50) DEFAULT '0',
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `grain_de_sel` varchar(50) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `niveau` int(11) NOT NULL DEFAULT '5',
  `fk_portefeuille_id` int(11) DEFAULT NULL,
  `fk_privilege_id` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nom_prenom` (`nom`,`prenom`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `pseudo` (`pseudo`),
  KEY `FK_utilisateur_portefeuille` (`fk_portefeuille_id`),
  KEY `FK_utilisateur_privilege` (`fk_privilege_id`),
  CONSTRAINT `FK_utilisateur_portefeuille` FOREIGN KEY (`fk_portefeuille_id`) REFERENCES `portefeuille` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_utilisateur_privilege` FOREIGN KEY (`fk_privilege_id`) REFERENCES `privilege` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de déclencheur loterie_dev. portefeuille_after_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `portefeuille_after_update` AFTER UPDATE ON `portefeuille` FOR EACH ROW BEGIN
	IF (OLD.fonds < 0 AND NEW.fonds >= 0 AND (SELECT COUNT(r.id) FROM retard r WHERE r.fk_joueur_id = (SELECT u.id FROM utilisateur u WHERE u.fk_portefeuille_id = NEW.id)) > 0) THEN
		DELETE FROM retard WHERE retard.fk_joueur_id = (SELECT u.id FROM utilisateur u WHERE u.fk_portefeuille_id = NEW.id);
	END IF;
	IF (OLD.fonds >= 0 AND NEW.fonds < 0 AND (SELECT COUNT(r.id) FROM retard r WHERE r.fk_joueur_id = (SELECT u.id FROM utilisateur u WHERE u.fk_portefeuille_id = NEW.id)) = 0) THEN
		INSERT INTO retard (fk_joueur_id, date_constat, date_relance, relance) VALUES ((SELECT utilisateur.id FROM utilisateur WHERE utilisateur.fk_portefeuille_id = NEW.id), CURRENT_TIMESTAMP(), NULL, 0);
	END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Export de la structure de la vue loterie_dev. gains_par_joueur
-- Suppression de la table temporaire et création finale de la structure d'une vue
DROP TABLE IF EXISTS `gains_par_joueur`;
CREATE ALGORITHM=TEMPTABLE DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `gains_par_joueur` AS select uuid() AS `id`,`u`.`id` AS `utilisateur_id`,`j`.`date_jour` AS `date_jour`,`g`.`nom` AS `nom`,`j`.`gains` AS `gains` from (((`grille` `g` join `lien_grille_utilisateur` `lgu` on((`g`.`id` = `lgu`.`fk_grille_id`))) join `utilisateur` `u` on((`u`.`id` = `lgu`.`fk_utilisateur_id`))) join `jour` `j` on((`lgu`.`id` = `j`.`fk_lien_gu_id`))) where (`j`.`paye` = 1) order by `u`.`id`,`j`.`date_jour` desc;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
